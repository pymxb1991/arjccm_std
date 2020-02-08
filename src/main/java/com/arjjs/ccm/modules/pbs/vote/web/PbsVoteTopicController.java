/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.vote.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.utils.excel.ExportExcel;
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteItem;
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteSubject;
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteTopic;
import com.arjjs.ccm.modules.pbs.vote.service.PbsVoteItemService;
import com.arjjs.ccm.modules.pbs.vote.service.PbsVoteSubjectService;
import com.arjjs.ccm.modules.pbs.vote.service.PbsVoteTopicService;

/**
 * 投票主题信息Controller
 * 
 * @author lc
 * @version 2018-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/vote/pbsVoteTopic")
public class PbsVoteTopicController extends BaseController {

	@Autowired
	private PbsVoteTopicService pbsVoteTopicService;
	@Autowired
	private PbsVoteSubjectService PbsVoteSubjectService;
	@Autowired
	private PbsVoteItemService pbsVoteItemService;

	// 调查问卷类别为 1
	//private static final String BELONGFUNCINVEST = "1";
	// 投票调查类别为 0
	//private static final String BELONGFUNCVOTE = "0";

	@ModelAttribute
	public PbsVoteTopic get(@RequestParam(required = false) String id) {
		PbsVoteTopic entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsVoteTopicService.get(id);
		}
		if (entity == null) {
			entity = new PbsVoteTopic();
		}
		return entity;
	}

	@RequiresPermissions("vote:pbsVoteTopic:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsVoteTopic pbsVoteTopic, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 投票
		pbsVoteTopic.setSBelongfunc("0");
		Page<PbsVoteTopic> page = pbsVoteTopicService.findPage(new Page<PbsVoteTopic>(request, response), pbsVoteTopic);
		model.addAttribute("page", page);
		return "pbs/vote/topic/pbsVoteTopicList";
	}

	// 投票
	@RequiresPermissions("vote:pbsVoteTopic:view")
	@RequestMapping(value = "form")
	public String form(PbsVoteTopic pbsVoteTopic, Model model) {
		model.addAttribute("pbsVoteTopic", pbsVoteTopic);
		if (StringUtils.isNoneBlank(pbsVoteTopic.getId())) {
			// 展示 主题下的题目
			PbsVoteSubject pbsVoteSubjectDto = new PbsVoteSubject();
			pbsVoteSubjectDto.setsParentid(new PbsVoteTopic(pbsVoteTopic.getId()));
			List<PbsVoteSubject> subList = PbsVoteSubjectService.findList(pbsVoteSubjectDto);
			model.addAttribute("subList", subList);
		}
		return "pbs/vote/topic/pbsVoteTopicForm";
	}

	// 调查
	@RequiresPermissions("vote:pbsVoteTopic:view")
	@RequestMapping(value = "formsurvey")
	public String formsurvey(PbsVoteTopic pbsVoteTopic, Model model) {
		model.addAttribute("pbsVoteTopic", pbsVoteTopic);
		if (StringUtils.isNoneBlank(pbsVoteTopic.getId())) {
			// 显示主题下的题目
			PbsVoteSubject pbsVoteSubjectDto = new PbsVoteSubject();
			pbsVoteSubjectDto.setsParentid(pbsVoteTopic);
			List<PbsVoteSubject> subList = PbsVoteSubjectService.findList(pbsVoteSubjectDto);
			model.addAttribute("subList", subList);
		}
		return "pbs/vote/topic/pbsVoteTopicFormSurvey";
	}

	@RequiresPermissions("vote:pbsVoteTopic:edit")
	@RequestMapping(value = "save")
	public String save(PbsVoteTopic pbsVoteTopic, Model model, RedirectAttributes redirectAttributes) {
		Date date = new Date();
		Date dtStart = pbsVoteTopic.getDtStart();
		Date dtEnd = pbsVoteTopic.getDtEnd();
		if(dtStart != null && dtStart.getTime() < date.getTime()) {
			addMessage(model, "数据验证失败：开始时间不能小于当前时间");
			if (("0").equals(pbsVoteTopic.getSBelongfunc())) {
				return form(pbsVoteTopic, model);
			} else {
				return formsurvey(pbsVoteTopic, model);
			}
		}
		if(dtStart != null && dtEnd != null && dtStart.getTime() > dtEnd.getTime()) {
			addMessage(model, "数据验证失败：开始时间不能大于结束时间");
			if (("0").equals(pbsVoteTopic.getSBelongfunc())) {
				return form(pbsVoteTopic, model);
			} else {
				return formsurvey(pbsVoteTopic, model);
			}
		}
		if (!beanValidator(model, pbsVoteTopic)) {
			if (("0").equals(pbsVoteTopic.getSBelongfunc())) {
				return form(pbsVoteTopic, model);
			} else {
				return formsurvey(pbsVoteTopic, model);
			}
		}
		pbsVoteTopicService.save(pbsVoteTopic);
		addMessage(redirectAttributes, "保存投票主题信息成功");
		// 投票
		if (("0").equals(pbsVoteTopic.getSBelongfunc())) {
			return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteTopic/?repage";
		} else {
			// 问卷调查
			return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteTopic/listsurvey?repage";
		}
	}

	@RequiresPermissions("vote:pbsVoteTopic:view")
	@RequestMapping(value = "listsurvey")
	public String listsurvey(PbsVoteTopic pbsVoteTopic, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 调查问卷
		pbsVoteTopic.setSBelongfunc("1");
		Page<PbsVoteTopic> page = pbsVoteTopicService.findPage(new Page<PbsVoteTopic>(request, response), pbsVoteTopic);
		model.addAttribute("page", page);
		return "pbs/vote/topic/pbsVoteTopicListSurvey";
	}

	//
	@RequiresPermissions("vote:pbsVoteTopic:view")
	@RequestMapping(value = "handleform")
	public String handleform(PbsVoteTopic pbsVoteTopic, Model model, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		// 基本验证
		if (!beanValidator(model, pbsVoteTopic)) {
			return form(pbsVoteTopic, model);
		}
		/**
		 * 审核撤销
		 */
		// 1 为发布 0 为 撤销发布
		if (pbsVoteTopic.getsStat().equals("0")) {
			pbsVoteTopicService.updateStat(pbsVoteTopic);
			addMessage(redirectAttributes, "更新投票主题发布状态成功");
			if (("0").equals(pbsVoteTopic.getSBelongfunc())) {
				return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteTopic/?repage";
			} else {
				// 投票类
				return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteTopic/listsurvey?repage";
			}
		}
		/**
		 * 审核通过
		 */
		// 题目查询对象
		PbsVoteSubject pbsVoteSubjectDto = new PbsVoteSubject();
		pbsVoteSubjectDto.setsParentid(new PbsVoteTopic(pbsVoteTopic.getId()));
		// 获取所有list集合
		List<PbsVoteSubject> list = PbsVoteSubjectService.findListVer(pbsVoteSubjectDto);
		// 选项数不足2个的题目
		String lackName = "";
		// 循环 list
		for (PbsVoteSubject subject : list) {
			if (subject.getItemCount() < 2) {
				lackName += StringUtils.isNotBlank(lackName) ? subject.getSName() : "," + subject.getSName();
			}
		}

		// 如果缺少的题目不为空
		if (list.size() == 0 || StringUtils.isNoneBlank(lackName)) {
			if (("0").equals(pbsVoteTopic.getSBelongfunc())) {
				// 投票
				String reason = (list.size() == 0 || list.size() > 1) ? "题数不能为空或大于一个." : lackName + " 当前的题目选项不为空.";
				addMessage(redirectAttributes, "数据验证失败：" + reason);
				return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteTopic/?repage";
			} else {
				// 调查
				String reason = list.size() == 0 ? "题数不能为空." : lackName + " 当前的题目选项不为空.";
				addMessage(redirectAttributes, "数据验证失败：" + reason);
				return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteTopic/listsurvey?repage";
			}

		} else if (list.size() > 1 && (("0").equals(pbsVoteTopic.getSBelongfunc()))) {
			String reason = (list.size() == 0 || list.size() > 1) ? "题数不能为空或大于一个." : lackName + " 当前的题目选项不为空.";
			addMessage(redirectAttributes, "数据验证失败：" + reason);
			return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteTopic/?repage";
		}
		// 更新状态
		pbsVoteTopicService.updateStat(pbsVoteTopic);
		addMessage(redirectAttributes, "更新投票主题发布状态成功");
		// 问卷调查
		if (("0").equals(pbsVoteTopic.getSBelongfunc())) {
			return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteTopic/?repage";
		} else {
			// 投票类
			return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteTopic/listsurvey?repage";
		}
	}

	@RequiresPermissions("vote:pbsVoteTopic:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsVoteTopic pbsVoteTopic, RedirectAttributes redirectAttributes) {
		pbsVoteTopicService.delete(pbsVoteTopic);
		addMessage(redirectAttributes, "删除投票主题信息成功");
		if (("0").equals(pbsVoteTopic.getSBelongfunc())) {
			// 投票
			return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteTopic/?repage";
		} else {
			// 调查
			return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteTopic/listsurvey?repage";
		}
	}

	// 获取所有 主题内容
	@RequiresPermissions("vote:pbsVoteTopic:view")
	@RequestMapping(value = "namelist")
	public String namelist(PbsVoteTopic pbsVoteTopic, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PbsVoteTopic> list = pbsVoteTopicService.findList(pbsVoteTopic);
		model.addAttribute("list", list);
		return "mapping/VoteTopics";
	}

	@RequiresPermissions("vote:pbsVoteTopic:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(PbsVoteTopic pbsVoteTopic, HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			PbsVoteItem ppbsVoteItem = new PbsVoteItem();
			ppbsVoteItem.setTopid(pbsVoteTopic);
			ppbsVoteItem.setsBelongfunc(pbsVoteTopic.getSBelongfunc());
			String fileName = "投票调查数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";

			List<PbsVoteItem> itemList = pbsVoteItemService.findList(ppbsVoteItem);
			// Page<PbsVoteItem> page = pbsVoteItemService.findPage(new
			// Page<PbsVoteItem>(request, response, -1),
			// ppbsVoteItem);
			new ExportExcel("投票调查数据", PbsVoteItem.class).setDataList(itemList).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出用户失败！失败信息：" + e.getMessage());
		}
		// 问卷调查
		// if (("0").equals(pbsVoteTopic.getSBelongfunc())) {
		return "redirect:" + Global.getAdminPath() + "/vote/pbsVoteTopic/?repage";
		// } else {
		// // 投票类
		// return "redirect:" + Global.getAdminPath() +
		// "/vote/pbsVoteTopic/listsurvey?repage";
		// }
	}

}