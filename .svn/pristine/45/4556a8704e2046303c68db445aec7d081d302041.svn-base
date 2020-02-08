/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.scheme.web;

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
import com.arjjs.ccm.modules.kpi.scheme.entity.KpiGoalYears;
import com.arjjs.ccm.modules.kpi.scheme.service.KpiGoalYearsService;

/**
 * 部门年度目标Controller
 * @author liang
 * @version 2018-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/scheme/kpiGoalYears")
public class KpiGoalYearsController extends BaseController {

	@Autowired
	private KpiGoalYearsService kpiGoalYearsService;
	
	@ModelAttribute
	public KpiGoalYears get(@RequestParam(required=false) String id) {
		KpiGoalYears entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = kpiGoalYearsService.get(id);
		}
		if (entity == null){
			entity = new KpiGoalYears();
		}
		return entity;
	}
	
	@RequiresPermissions("scheme:kpiGoalYears:view")
	@RequestMapping(value = {"list", ""})
	public String list(KpiGoalYears kpiGoalYears, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<KpiGoalYears> page = kpiGoalYearsService.findPage(new Page<KpiGoalYears>(request, response), kpiGoalYears); 
		model.addAttribute("page", page);
		return "kpi/scheme/kpiGoalYearsList";
	}

	@RequiresPermissions("scheme:kpiGoalYears:view")
	@RequestMapping(value = "form")
	public String form(KpiGoalYears kpiGoalYears, Model model) {
		model.addAttribute("kpiGoalYears", kpiGoalYears);
		return "kpi/scheme/kpiGoalYearsForm";
	}

	@RequiresPermissions("scheme:kpiGoalYears:edit")
	@RequestMapping(value = "save")
	public String save(KpiGoalYears kpiGoalYears, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, kpiGoalYears)){
			return form(kpiGoalYears, model);
		}
		kpiGoalYearsService.save(kpiGoalYears);
		addMessage(redirectAttributes, "保存部门年度目标成功");
		return "redirect:"+Global.getAdminPath()+"/scheme/kpiGoalYears/?repage";
	}
	
	@RequiresPermissions("scheme:kpiGoalYears:edit")
	@RequestMapping(value = "delete")
	public String delete(KpiGoalYears kpiGoalYears, RedirectAttributes redirectAttributes) {
		kpiGoalYearsService.delete(kpiGoalYears);
		addMessage(redirectAttributes, "删除部门年度目标成功");
		return "redirect:"+Global.getAdminPath()+"/scheme/kpiGoalYears/?repage";
	}

}