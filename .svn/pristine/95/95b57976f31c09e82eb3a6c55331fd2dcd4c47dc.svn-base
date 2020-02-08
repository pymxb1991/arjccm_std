/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.planstep.web;

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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.flat.planstep.entity.BphPlanStep;
import com.arjjs.ccm.modules.flat.planstep.service.BphPlanStepService;

/**
 * 预案过程关联表Controller
 * @author liu
 * @version 2018-11-17
 */
@Controller
@RequestMapping(value = "${adminPath}/planstep/bphPlanStep")
public class BphPlanStepController extends BaseController {

	@Autowired
	private BphPlanStepService bphPlanStepService;
	
	@ModelAttribute
	public BphPlanStep get(@RequestParam(required=false) String id) {
		BphPlanStep entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bphPlanStepService.get(id);
		}
		if (entity == null){
			entity = new BphPlanStep();
		}
		return entity;
	}
	
	@RequiresPermissions("planstep:bphPlanStep:view")
	@RequestMapping(value = {"list", ""})
	public String list(BphPlanStep bphPlanStep, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BphPlanStep> page = bphPlanStepService.findPage(new Page<BphPlanStep>(request, response), bphPlanStep); 
		model.addAttribute("page", page);
		return "flat/planstep/bphPlanStepList";
	}

	@RequiresPermissions("planstep:bphPlanStep:view")
	@RequestMapping(value = "form")
	public String form(BphPlanStep bphPlanStep, Model model) {
		model.addAttribute("bphPlanStep", bphPlanStep);
		return "flat/planstep/bphPlanStepForm";
	}

	@RequiresPermissions("planstep:bphPlanStep:edit")
	@RequestMapping(value = "save")
	public String save(BphPlanStep bphPlanStep, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bphPlanStep)){
			return form(bphPlanStep, model);
		}
		bphPlanStepService.save(bphPlanStep);
		addMessage(redirectAttributes, "保存预案过程关联表成功");
		return "redirect:"+Global.getAdminPath()+"/planstep/bphPlanStep/?repage";
	}
	
	@RequiresPermissions("planstep:bphPlanStep:edit")
	@RequestMapping(value = "delete")
	public String delete(BphPlanStep bphPlanStep, RedirectAttributes redirectAttributes) {
		bphPlanStepService.delete(bphPlanStep);
		addMessage(redirectAttributes, "删除预案过程关联表成功");
		return "redirect:"+Global.getAdminPath()+"/planstep/bphPlanStep/?repage";
	}

}