/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.web;

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
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionProject;
import com.arjjs.ccm.modules.pbs.question.service.PbsQuestionProjectService;

/**
 * 科目信息Controller
 * 
 * @author lc
 * @version 2018-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/question/pbsQuestionProject")
public class PbsQuestionProjectController extends BaseController {

	@Autowired
	private PbsQuestionProjectService pbsQuestionProjectService;

	@ModelAttribute
	public PbsQuestionProject get(@RequestParam(required = false) String id) {
		PbsQuestionProject entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsQuestionProjectService.get(id);
		}
		if (entity == null) {
			entity = new PbsQuestionProject();
		}
		return entity;
	}

	@RequiresPermissions("question:pbsQuestionProject:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsQuestionProject pbsQuestionProject, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsQuestionProject> page = pbsQuestionProjectService
				.findPage(new Page<PbsQuestionProject>(request, response), pbsQuestionProject);
		model.addAttribute("page", page);
		return "pbs/question/pbsQuestionProjectList";
	}

	@RequiresPermissions("question:pbsQuestionProject:view")
	@RequestMapping(value = "form")
	public String form(PbsQuestionProject pbsQuestionProject, Model model) {
		model.addAttribute("pbsQuestionProject", pbsQuestionProject);
		return "pbs/question/pbsQuestionProjectForm";
	}

	@RequiresPermissions("question:pbsQuestionProject:edit")
	@RequestMapping(value = "save")
	public String save(PbsQuestionProject pbsQuestionProject, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsQuestionProject)) {
			return form(pbsQuestionProject, model);
		}
		pbsQuestionProjectService.save(pbsQuestionProject);
		addMessage(redirectAttributes, "保存科目信息成功");
		return "redirect:" + Global.getAdminPath() + "/question/pbsQuestionProject/?repage";
	}

	@RequiresPermissions("question:pbsQuestionProject:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsQuestionProject pbsQuestionProject, RedirectAttributes redirectAttributes) {
		pbsQuestionProjectService.delete(pbsQuestionProject);
		addMessage(redirectAttributes, "删除科目信息成功");
		return "redirect:" + Global.getAdminPath() + "/question/pbsQuestionProject/?repage";
	}

	// 返回
	@RequestMapping(value = "listPage")
	public String listPage( PbsQuestionProject pbsQuestionProject, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PbsQuestionProject> list = pbsQuestionProjectService.findList(pbsQuestionProject);
		model.addAttribute("list", list);
		return "/mapping/ProjectList";
	}
	// 返回
	@RequestMapping(value = "listPageAddAll")
	public String listPageAddAll( PbsQuestionProject pbsQuestionProject, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PbsQuestionProject> list = pbsQuestionProjectService.findList(pbsQuestionProject);
		model.addAttribute("list", list);
		return "/mapping/ProjectListAddAll";
	}
}