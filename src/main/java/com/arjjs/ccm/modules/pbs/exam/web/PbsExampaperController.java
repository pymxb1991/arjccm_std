/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.exam.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.pbs.common.utils.EchartType;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExamaction;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExampaper;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExampaperitem;
import com.arjjs.ccm.modules.pbs.exam.service.PbsExamactionService;
import com.arjjs.ccm.modules.pbs.exam.service.PbsExampaperService;
import com.arjjs.ccm.modules.pbs.exam.service.PbsExampaperitemService;
import com.arjjs.ccm.modules.pbs.ncount.entity.PbsNcount;
import com.arjjs.ccm.modules.pbs.ncount.entity.PbsNcountdetail;
import com.arjjs.ccm.modules.pbs.ncount.service.PbsNcountService;
import com.arjjs.ccm.modules.pbs.ncount.service.PbsNcountdetailService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 试卷信息Controller
 * 
 * @author lc
 * @version 2018-06-11
 */
@Controller
@RequestMapping(value = "${adminPath}/exam/pbsExampaper")
public class PbsExampaperController extends BaseController {

	@Autowired
	private PbsExampaperService pbsExampaperService;
	@Autowired
	private PbsExampaperitemService pbsExampaperitemService;
	@Autowired
	private PbsExamactionService pbsExamactionService;
	@Autowired
	private PbsNcountService pbsNcountService;
	@Autowired
	private PbsNcountdetailService pbsNcountdetailService;
	//  考试类别
	private static final String type6 = "6";

	@ModelAttribute
	public PbsExampaper get(@RequestParam(required = false) String id) {
		PbsExampaper entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsExampaperService.get(id);
		}
		if (entity == null) {
			entity = new PbsExampaper();
		}
		return entity;
	}

	@RequiresPermissions("exam:pbsExampaper:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsExampaper pbsExampaper, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsExampaper> page = pbsExampaperService.findPage(new Page<PbsExampaper>(request, response), pbsExampaper);
		model.addAttribute("page", page);
		// 如果当前的
		if (("2").equalsIgnoreCase(pbsExampaper.getSWay())) {
			return "pbs/exam/paper/pbsExampaperListSynthesize";
		} else if (("1").equalsIgnoreCase(pbsExampaper.getSWay())) {
			return "pbs/exam/paper/pbsExampaperListSelf";
		} else {
			return "pbs/exam/paper/pbsExampaperListOnline";
		}
	}

	// way0 - 自测考试
	@RequiresPermissions("exam:pbsExampaper:view")
	@RequestMapping(value = "formSelf")
	public String formSelf(PbsExampaper pbsExampaper, Model model) {
		model.addAttribute("pbsExampaper", pbsExampaper);
		// 当前的对象不为空 为添加的时候
		if (StringUtils.isNoneBlank(pbsExampaper.getId())) {
			PbsExampaperitem pbsExampaperitemDto = new PbsExampaperitem();
			// 获取试题list
			pbsExampaperitemDto.setsExampaper(pbsExampaper);
			List<PbsExampaperitem> pbsExampaperitemList = pbsExampaperitemService.findList(pbsExampaperitemDto);
			model.addAttribute("exampaperitemList", pbsExampaperitemList);
		}
		return "pbs/exam/paper/pbsExampaperFormSelf";
	}

	// way1 - 在线考试
	@RequiresPermissions("exam:pbsExampaper:view")
	@RequestMapping(value = "formOnline")
	public String formOnline(PbsExampaper pbsExampaper, Model model) {
		model.addAttribute("pbsExampaper", pbsExampaper);
		// 当前的对象不为空 为添加的时候
		if (StringUtils.isNoneBlank(pbsExampaper.getId())) {
			PbsExampaperitem pbsExampaperitemDto = new PbsExampaperitem();
			// 获取试题list
			pbsExampaperitemDto.setsExampaper(pbsExampaper);
			List<PbsExampaperitem> pbsExampaperitemList = pbsExampaperitemService.findList(pbsExampaperitemDto);
			model.addAttribute("exampaperitemList", pbsExampaperitemList);
		}
		return "pbs/exam/paper/pbsExampaperFormOnline";
	}
	
	// way2 - 综合考试
	@RequiresPermissions("exam:pbsExampaper:view")
	@RequestMapping(value = "formSynthesize")
	public String formSynthesize(PbsExampaper pbsExampaper, Model model) {
		model.addAttribute("pbsExampaper", pbsExampaper);
		// 当前的对象不为空 为添加的时候
		if (StringUtils.isNoneBlank(pbsExampaper.getId())) {
			PbsExampaperitem pbsExampaperitemDto = new PbsExampaperitem();
			// 获取试题list
			pbsExampaperitemDto.setsExampaper(pbsExampaper);
			List<PbsExampaperitem> pbsExampaperitemList = pbsExampaperitemService.findList(pbsExampaperitemDto);
			model.addAttribute("exampaperitemList", pbsExampaperitemList);
		}
		return "pbs/exam/paper/pbsExampaperFormSynthesize";
	}
	
	@RequestMapping("paperDetail")
	public String paperDetail(PbsExampaper pbsExampaper, Model model, RedirectAttributes redirectAttributes) {
		List<PbsExamaction> pbsExamactionList = pbsExamactionService.paperDetail(pbsExampaper.getId());
		if(pbsExamactionList == null || pbsExamactionList.size() == 0) {
			addMessage(redirectAttributes, "当前试卷无党员考试，无法查看考试信息");
			if(("2").equals(pbsExampaper.getSWay())) {
				return "redirect:"+Global.getAdminPath()+"/exam/pbsExampaper/?sWay=2";
			}else {
				return "redirect:"+Global.getAdminPath()+"/exam/pbsExampaper/?sWay=0";
			}
		}
		model.addAttribute("pbsExamactionList", pbsExamactionList);
		int minScore = 0;  //最高分
		int maxScore = 0;  //最低分
		double averageScore = 0; //平均分
		for(int i=0;i<pbsExamactionList.size();i++) {
			if(pbsExamactionList.size() == 1 || i == 0) {
				averageScore = maxScore = minScore = pbsExamactionList.get(0).getIReport();
			} else {
				if(pbsExamactionList.get(i).getIReport() < minScore) {
					minScore = pbsExamactionList.get(i).getIReport();
				} else if(pbsExamactionList.get(i).getIReport() > maxScore) {
					maxScore = pbsExamactionList.get(i).getIReport();
				}
				averageScore += pbsExamactionList.get(i).getIReport();
			}
		}
		if(pbsExamactionList != null && pbsExamactionList.size() > 0) {
			averageScore = (double)Math.round(averageScore/pbsExamactionList.size()*100)/100;
		}
		model.addAttribute("minScore", minScore);
		model.addAttribute("maxScore", maxScore);
		model.addAttribute("averageScore", averageScore);
		return "pbs/exam/paper/pbsExampaperDetail";
	}
	
	@ResponseBody
	@RequestMapping("paperDetailStrut")
	public Map<String, Object> paperDetailStrut(PbsExampaper pbsExampaper, Model model) {
		List<PbsExamaction> pbsExamactionList = pbsExamactionService.paperDetail(pbsExampaper.getId());
		int lessSixty = 0; //小于60分的人数
		int moreSixty = 0; //大于60分的人数
		int moreNinety = 0; //大于90分的人数
		for(int i=0;i<pbsExamactionList.size();i++) {
			if(pbsExamactionList.get(i).getIReport() < 60) {
				lessSixty++;
			} else if (pbsExamactionList.get(i).getIReport() >= 60 && pbsExamactionList.get(i).getIReport() < 90) {
				moreSixty++;
			} else {
				moreNinety++;
			}
		}
		// 返回对象结果
		Map<String, Object> map = Maps.newHashMap();
		List<EchartType> list = Lists.newArrayList();
		if(lessSixty != 0) {
			EchartType e1 = new EchartType();
			e1.setName("小于60分");
			e1.setValue(lessSixty);
			list.add(e1);
		}
		if(moreSixty != 0) {
			EchartType e2 = new EchartType();
			e2.setName("大于等于60分小于90分");
			e2.setValue(moreSixty);
			list.add(e2);
		}
		if(moreNinety != 0) {
			EchartType e3 = new EchartType();
			e3.setName("大于90分");
			e3.setValue(moreNinety);
			list.add(e3);
		}
		map.put("党员成绩占比", list);
		return map;
	}
	@RequiresPermissions("exam:pbsExampaper:edit")
	@RequestMapping(value = "save")
	public String save(PbsExampaper pbsExampaper, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsExampaper)) {
			return formOnline(pbsExampaper, model);
		}
		pbsExampaperService.save(pbsExampaper);
		addMessage(redirectAttributes, "保存试卷信息成功");
		if (("2").equalsIgnoreCase(pbsExampaper.getSWay())) {
			return "redirect:" + Global.getAdminPath() + "/exam/pbsExampaper/?sWay=2";
		} else if (("1").equalsIgnoreCase(pbsExampaper.getSWay())) {
			return "redirect:" + Global.getAdminPath() + "/exam/pbsExampaper/?sWay=1";
		} else {
			return "redirect:" + Global.getAdminPath() + "/exam/pbsExampaper/?sWay=0";
		}
	}

	@RequiresPermissions("exam:pbsExampaper:edit")
	@RequestMapping(value = "/delete")
	public String delete(PbsExampaper pbsExampaper, RedirectAttributes redirectAttributes) {
		pbsExampaperService.delete(pbsExampaper);
		addMessage(redirectAttributes, "删除试卷信息成功");
		if (("2").equalsIgnoreCase(pbsExampaper.getSWay())) {
			return "redirect:" + Global.getAdminPath() + "/exam/pbsExampaper/?sWay=2";
		} else if (("1").equalsIgnoreCase(pbsExampaper.getSWay())) {
			return "redirect:" + Global.getAdminPath() + "/exam/pbsExampaper/?sWay=1";
		} else {
			return "redirect:" + Global.getAdminPath() + "/exam/pbsExampaper/?sWay=0";
		}
	}

	//
	@RequiresPermissions("exam:pbsExampaper:edit")
	@RequestMapping(value = "handleform")
	public String handleform(PbsExampaper pbsExampaper, Model model, RedirectAttributes redirectAttributes) {
		// 审核撤销
		if (pbsExampaper.getsStat().equals("0")) {
			pbsExampaperService.updateStat(pbsExampaper);
			addMessage(redirectAttributes, "更新考试试卷发布状态成功");
			if(("2").equals(pbsExampaper.getSWay())) {
				return "redirect:" + Global.getAdminPath() + "/exam/pbsExampaper/?sWay=2";
			}else {
				return "redirect:" + Global.getAdminPath() + "/exam/pbsExampaper/?sWay=0";
			}
		}
		PbsExampaperitem pbsExampaperitemDto = new PbsExampaperitem();
		pbsExampaperitemDto.setsExampaper(new PbsExampaper(pbsExampaper.getId()));
		List<PbsExampaperitem> pbsExamactionitemList = pbsExampaperitemService.findList(pbsExampaperitemDto);
		// 当前的考试题目数量为0 返回
		if (pbsExamactionitemList.size() == 0) {
			addMessage(redirectAttributes, "数据验证失败：当前的考试的题目数量不能为空");
			if(("2").equals(pbsExampaper.getSWay())) {
				return "redirect:" + Global.getAdminPath() + "/exam/pbsExampaper/?sWay=2";
			}else {
				return "redirect:" + Global.getAdminPath() + "/exam/pbsExampaper/?sWay=0";
			}
		}
		pbsExampaperService.updateStat(pbsExampaper);
		addMessage(redirectAttributes, "试卷发布成功");
		if(("2").equals(pbsExampaper.getSWay())) {
			return "redirect:" + Global.getAdminPath() + "/exam/pbsExampaper/?sWay=2";
		}else {
			return "redirect:" + Global.getAdminPath() + "/exam/pbsExampaper/?sWay=0";
		}
	}

	// 获取所有的对象信息
	@RequiresPermissions("exam:pbsExampaper:view")
	@RequestMapping(value = "namelist")
	public String namelist(PbsExampaper pbsExampaper, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PbsExampaper> list = pbsExampaperService.findList(pbsExampaper);
		model.addAttribute("list", list);
		return "mapping/ExampaperList";
	}
	
	@RequiresPermissions("exam:pbsExamaction:edit")
	@RequestMapping(value = "ncount")
	public String ncount(PbsExampaper pbsExampaper, RedirectAttributes redirectAttributes) {
		
		
		// 考试信息
		PbsExamaction pbsExamactionDto = new PbsExamaction();
		pbsExamactionDto.setsExampaper(pbsExampaper);
		List<PbsExamaction> pbsExamactionList = pbsExamactionService.findIntegrallist(pbsExamactionDto);
		// 统计类别
		PbsNcount pbsNcount = new PbsNcount();
		// 试卷的id
		pbsNcount.setSName(pbsExampaper.getId());
		pbsNcount.setDtDate(new Date());
		pbsNcount.setSType(type6);
		pbsNcountService.save(pbsNcount);
		
		// 统计详细信息
		List<PbsNcountdetail> pbsNcountdetailList = Lists.newArrayList();
		for(PbsExamaction examaction : pbsExamactionList){
			PbsNcountdetail pbsNcountdetail = new PbsNcountdetail();
			pbsNcountdetail.setsParentid(pbsNcount);
			pbsNcountdetail.setSName(examaction.getsExaminee().getId());
			pbsNcountdetail.setINumber(examaction.getIReport());
			// 添加列表
			pbsNcountdetailList.add(pbsNcountdetail);
		}
		pbsNcountdetailService.insertAll(pbsNcountdetailList);
		
		
		addMessage(redirectAttributes, "考试统计信息成功");
		return "redirect:" + Global.getAdminPath() + "/exam/pbsExamaction/?repage";
	}

}