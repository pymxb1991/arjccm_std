/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.exam.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.Collections3;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExampaper;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExampaperitem;
import com.arjjs.ccm.modules.pbs.exam.service.PbsExampaperService;
import com.arjjs.ccm.modules.pbs.exam.service.PbsExampaperitemService;

/**
 * 试卷题目Controller
 * 
 * @author lc
 * @version 2018-06-11
 */
@Controller
@RequestMapping(value = "${adminPath}/exam/pbsExampaperitem")
public class PbsExampaperitemController extends BaseController {

	@Autowired
	private PbsExampaperitemService pbsExampaperitemService;
	@Autowired
	private PbsExampaperService pbsExampaperService;

	@ModelAttribute
	public PbsExampaperitem get(@RequestParam(required = false) String id,
			@RequestParam(required = false) String sExampaper) {
		PbsExampaperitem entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsExampaperitemService.get(id);
		}
		if (entity == null) {
			entity = new PbsExampaperitem();
		}
		// 如果考试编号不为空
		if (StringUtils.isNoneBlank(sExampaper)) {
			entity.setsExampaper(new PbsExampaper(sExampaper));
		}
		return entity;
	}

	@RequiresPermissions("exam:pbsExampaperitem:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsExampaperitem pbsExampaperitem, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsExampaperitem> page = pbsExampaperitemService.findPage(new Page<PbsExampaperitem>(request, response),
				pbsExampaperitem);
		model.addAttribute("page", page);
		return "pbs/exam/paper/pbsExampaperitemList";
	}

	@RequiresPermissions("exam:pbsExampaperitem:view")
	@RequestMapping(value = "form")
	public String form(PbsExampaperitem pbsExampaperitem, Model model) {
		model.addAttribute("pbsExampaperitem", pbsExampaperitem);
		return "pbs/exam/paper/pbsExampaperitemForm";
	}

	// 添加考题信息
	@RequestMapping(value = "addform")
	public String addform(PbsExampaperitem pbsExampaperitem, Model model) {
		// 获取值
		String sExampaper =pbsExampaperitem.getsExampaper().getId();
		if (StringUtils.isNoneBlank(pbsExampaperitem.getsExampaper().getId())) {
			PbsExampaper pbsExampaper = pbsExampaperService.get(sExampaper);
			pbsExampaperitem.setsExampaper(pbsExampaper);
		}
		model.addAttribute("pbsExampaperitem", pbsExampaperitem);
		return "/pbs/exam/paper/pbsExampaperitemFormPaper";
	}

	@RequiresPermissions("exam:pbsExampaperitem:edit")
	@RequestMapping(value = "save")
	public String save(PbsExampaperitem pbsExampaperitem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsExampaperitem)) {
			return form(pbsExampaperitem, model);
		}
		// 获取当前的试卷的题目的列表
		PbsExampaperitem pbsExampaperitemDto = new PbsExampaperitem();
		pbsExampaperitemDto.setsExampaper(pbsExampaperitem.getsExampaper());
		//
		List<PbsExampaperitem> pbsExampaperitems = pbsExampaperitemService.findList(pbsExampaperitem);
		// 获取当前ids 的所有的值
		String ids = Collections3.extractToString(pbsExampaperitems, "sItem.id", ",");
		// ids 包含当前的题目则不能再次添加题目
		if (ids.contains(pbsExampaperitem.getsItem().getId())&&(StringUtils.isBlank(pbsExampaperitem.getId()))) {
			addMessage(model, "数据验证失败：该试题已经拥有该信息");
			return form(pbsExampaperitem, model);
		}
		// // 当前的顺序+1
		if (StringUtils.isEmpty(pbsExampaperitem.getISort() + "")) {
			pbsExampaperitem.setISort(pbsExampaperitems.size() + 1);
		}
		pbsExampaperitemService.save(pbsExampaperitem);
		addMessage(redirectAttributes, "保存试卷题目成功");
		return "redirect:" + Global.getAdminPath() + "/exam/pbsExampaperitem/?repage";
	}

	// 添加考试信息
	@RequiresPermissions("exam:pbsExampaperitem:edit")
	@RequestMapping(value = "addsave")
	public String addsave(PbsExampaperitem pbsExampaperitem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsExampaperitem)) {
			return form(pbsExampaperitem, model);
		}
		// 获取当前的试卷的题目的列表
		PbsExampaperitem pbsExampaperitemDto = new PbsExampaperitem();
		pbsExampaperitemDto.setsExampaper(pbsExampaperitem.getsExampaper());
		//
		List<PbsExampaperitem> pbsExampaperitems = pbsExampaperitemService.findList(pbsExampaperitem);
		// 获取当前ids 的所有的值
		String ids = Collections3.extractToString(pbsExampaperitems, "sItem.id", ",");
		// ids 包含当前的题目则不能再次添加题目
		if (ids.contains(pbsExampaperitem.getsItem().getId())&&(StringUtils.isBlank(pbsExampaperitem.getId()))) {
			addMessage(model, "数据验证失败：该试题已经拥有该信息");
			return form(pbsExampaperitem, model);
		}
		// // 当前的顺序+1
		if (pbsExampaperitem.getISort()==null) {
			pbsExampaperitem.setISort(pbsExampaperitems.size() + 1);
		}
		pbsExampaperitemService.save(pbsExampaperitem);
		addMessage(redirectAttributes, "保存试卷题目成功");
		// 获取相关的 试卷类型
		PbsExampaper pbsExampaper = pbsExampaperService.get(pbsExampaperitem.getsExampaper().getId());
		if ("2".equals(pbsExampaper.getSWay())) {
			return "redirect:" + Global.getAdminPath() + "/exam/pbsExampaper/formSynthesize?id="
					+ pbsExampaperitem.getsExampaper().getId();
		} else if ("1".equals(pbsExampaper.getSWay())) {
			return "redirect:" + Global.getAdminPath() + "/exam/pbsExampaper/formSelf?id="
					+ pbsExampaperitem.getsExampaper().getId();
		} else {
			return "redirect:" + Global.getAdminPath() + "/exam/pbsExampaper/formOnline?id="
					+ pbsExampaperitem.getsExampaper().getId();
		}
	}

	@RequiresPermissions("exam:pbsExampaperitem:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsExampaperitem pbsExampaperitem, RedirectAttributes redirectAttributes) {
		pbsExampaperitemService.delete(pbsExampaperitem);
		addMessage(redirectAttributes, "删除试卷题目成功");
		return "redirect:" + Global.getAdminPath() + "/exam/pbsExampaperitem/?repage";
	}

}