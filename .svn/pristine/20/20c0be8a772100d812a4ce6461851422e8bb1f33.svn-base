/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.web;

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
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionMajor;
import com.arjjs.ccm.modules.pbs.question.service.PbsQuestionMajorService;

/**
 * 专业信息Controller
 * @author lc
 * @version 2018-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/question/pbsQuestionMajor")
public class PbsQuestionMajorController extends BaseController {

	@Autowired
	private PbsQuestionMajorService pbsQuestionMajorService;
	
	@ModelAttribute
	public PbsQuestionMajor get(@RequestParam(required=false) String id) {
		PbsQuestionMajor entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsQuestionMajorService.get(id);
		}
		if (entity == null){
			entity = new PbsQuestionMajor();
		}
		return entity;
	}
	
	@RequiresPermissions("question:pbsQuestionMajor:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsQuestionMajor pbsQuestionMajor, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsQuestionMajor> page = pbsQuestionMajorService.findPage(new Page<PbsQuestionMajor>(request, response), pbsQuestionMajor); 
		model.addAttribute("page", page);
		return "pbs/question/pbsQuestionMajorList";
	}

	@RequiresPermissions("question:pbsQuestionMajor:view")
	@RequestMapping(value = "form")
	public String form(PbsQuestionMajor pbsQuestionMajor, Model model) {
		model.addAttribute("pbsQuestionMajor", pbsQuestionMajor);
		return "pbs/question/pbsQuestionMajorForm";
	}

	@RequiresPermissions("question:pbsQuestionMajor:edit")
	@RequestMapping(value = "save")
	public String save(PbsQuestionMajor pbsQuestionMajor, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsQuestionMajor)){
			return form(pbsQuestionMajor, model);
		}
		pbsQuestionMajorService.save(pbsQuestionMajor);
		addMessage(redirectAttributes, "保存专业信息成功");
		return "redirect:"+Global.getAdminPath()+"/question/pbsQuestionMajor/?repage";
	}
	
	@RequiresPermissions("question:pbsQuestionMajor:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsQuestionMajor pbsQuestionMajor, RedirectAttributes redirectAttributes) {
		pbsQuestionMajorService.delete(pbsQuestionMajor);
		addMessage(redirectAttributes, "删除专业信息成功");
		return "redirect:"+Global.getAdminPath()+"/question/pbsQuestionMajor/?repage";
	}
	
	

}