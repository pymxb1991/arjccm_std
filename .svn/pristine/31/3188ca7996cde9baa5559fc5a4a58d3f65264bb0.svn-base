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
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionLevel;
import com.arjjs.ccm.modules.pbs.question.service.PbsQuestionLevelService;

/**
 * 考试难度级别Controller
 * 
 * @author lc
 * @version 2018-06-06
 */
@Controller
@RequestMapping(value = "${adminPath}/question/pbsQuestionLevel")
public class PbsQuestionLevelController extends BaseController {

	@Autowired
	private PbsQuestionLevelService pbsQuestionLevelService;

	@ModelAttribute
	public PbsQuestionLevel get(@RequestParam(required = false) String id) {
		PbsQuestionLevel entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsQuestionLevelService.get(id);
		}
		if (entity == null) {
			entity = new PbsQuestionLevel();
		}
		return entity;
	}

	@RequiresPermissions("question:pbsQuestionLevel:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsQuestionLevel pbsQuestionLevel, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsQuestionLevel> page = pbsQuestionLevelService.findPage(new Page<PbsQuestionLevel>(request, response),
				pbsQuestionLevel);
		model.addAttribute("page", page);
		return "pbs/question/rule/pbsQuestionLevelList";
	}

	@RequiresPermissions("question:pbsQuestionLevel:view")
	@RequestMapping(value = "form")
	public String form(PbsQuestionLevel pbsQuestionLevel, Model model) {
		model.addAttribute("pbsQuestionLevel", pbsQuestionLevel);
		return "pbs/question/rule/pbsQuestionLevelForm";
	}

	@RequiresPermissions("question:pbsQuestionLevel:edit")
	@RequestMapping(value = "save")
	public String save(PbsQuestionLevel pbsQuestionLevel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsQuestionLevel)) {
			return form(pbsQuestionLevel, model);
		}
		pbsQuestionLevelService.save(pbsQuestionLevel);
		addMessage(redirectAttributes, "保存考试难度级别成功");
		return "redirect:" + Global.getAdminPath() + "/question/pbsQuestionLevel/?repage";
	}

	@RequiresPermissions("question:pbsQuestionLevel:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsQuestionLevel pbsQuestionLevel, RedirectAttributes redirectAttributes) {
		pbsQuestionLevelService.delete(pbsQuestionLevel);
		addMessage(redirectAttributes, "删除考试难度级别成功");
		return "redirect:" + Global.getAdminPath() + "/question/pbsQuestionLevel/?repage";
	}

	// 获取所有的
	@RequiresPermissions("question:pbsQuestionLevel:view")
	@RequestMapping(value = "namelist")
	public String namelist(PbsQuestionLevel pbsQuestionLevel, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PbsQuestionLevel> list = pbsQuestionLevelService.findList(pbsQuestionLevel);
		model.addAttribute("list", list);
		return "mapping/QuestionLevelList";
	}
}