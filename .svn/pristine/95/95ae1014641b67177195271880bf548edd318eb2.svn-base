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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.exam.entity.PbsExamaction;
import com.arjjs.ccm.modules.pbs.exam.service.PbsExamactionService;

/**
 * 考试信息Controller
 * 
 * @author lx
 * @version 2018-06-11
 */
@Controller
@RequestMapping(value = "${adminPath}/exam/pbsExamaction")
public class PbsExamactionController extends BaseController {

	@Autowired
	private PbsExamactionService pbsExamactionService;

	@ModelAttribute
	public PbsExamaction get(@RequestParam(required = false) String id) {
		PbsExamaction entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsExamactionService.get(id);
		}
		if (entity == null) {
			entity = new PbsExamaction();
		}
		return entity;
	}

	@RequiresPermissions("exam:pbsExamaction:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsExamaction pbsExamaction, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsExamaction> page = pbsExamactionService.findPage(new Page<PbsExamaction>(request, response),
				pbsExamaction);
		model.addAttribute("page", page);
		return "pbs/exam/action/pbsExamactionList";
	}

	@RequiresPermissions("exam:pbsExamaction:view")
	@RequestMapping(value = "form")
	public String form(PbsExamaction pbsExamaction, Model model) {
		model.addAttribute("pbsExamaction", pbsExamaction);
		return "pbs/exam/action/pbsExamactionForm";
	}

	@RequiresPermissions("exam:pbsExamaction:edit")
	@RequestMapping(value = "save")
	public String save(PbsExamaction pbsExamaction, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsExamaction)) {
			return form(pbsExamaction, model);
		}
		pbsExamactionService.save(pbsExamaction);
		addMessage(redirectAttributes, "保存考试信息成功");
		return "redirect:" + Global.getAdminPath() + "/exam/pbsExamaction/?repage";
	}

	@RequiresPermissions("exam:pbsExamaction:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsExamaction pbsExamaction, RedirectAttributes redirectAttributes) {
		pbsExamactionService.delete(pbsExamaction);
		addMessage(redirectAttributes, "删除考试信息成功");
		return "redirect:" + Global.getAdminPath() + "/exam/pbsExamaction/?repage";
	}

	// 获取所有的对象信息
	@RequiresPermissions("exam:pbsExamaction:edit")
	@RequestMapping(value = "namelist")
	public String namelist(PbsExamaction PbsExamaction, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PbsExamaction> list = pbsExamactionService.findList(PbsExamaction);
		model.addAttribute("list", list);
		return "mapping/ExamactionList";
	}
	
}