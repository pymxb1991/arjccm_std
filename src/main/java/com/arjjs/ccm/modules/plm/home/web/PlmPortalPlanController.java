/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.home.web;

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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.home.entity.PlmPortalDict;
import com.arjjs.ccm.modules.plm.home.entity.PlmPortalPlan;
import com.arjjs.ccm.modules.plm.home.service.PlmPortalDictService;
import com.arjjs.ccm.modules.plm.home.service.PlmPortalPlanService;

/**
 * 门户方案Controller
 * @author liuxue
 * @version 2018-07-12
 */
@Controller
@RequestMapping(value = "${adminPath}/home/plmPortalPlan")
public class PlmPortalPlanController extends BaseController {

	@Autowired
	private PlmPortalPlanService plmPortalPlanService;
	@Autowired
	private PlmPortalDictService plmPortalDictService;
	
	@ModelAttribute
	public PlmPortalPlan get(@RequestParam(required=false) String id) {
		PlmPortalPlan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmPortalPlanService.get(id);
		}
		if (entity == null){
			entity = new PlmPortalPlan();
		}
		return entity;
	}
	
	@RequiresPermissions("home:plmPortalPlan:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmPortalPlan plmPortalPlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmPortalPlan> page = plmPortalPlanService.findPage(new Page<PlmPortalPlan>(request, response), plmPortalPlan); 
		model.addAttribute("page", page);
		return "plm/home/plmPortalPlanList";
	}

	@RequiresPermissions("home:plmPortalPlan:view")
	@RequestMapping(value = "form")
	public String form(PlmPortalPlan plmPortalPlan, Model model) {
		model.addAttribute("plmPortalPlan", plmPortalPlan);
		//Content 下拉列表
		PlmPortalDict plmPortalDict =new PlmPortalDict();
		List<PlmPortalDict> plmPortalDictList=plmPortalDictService.findList(plmPortalDict);
		model.addAttribute("plmPortalDictList", plmPortalDictList);
		return "plm/home/plmPortalPlanForm";
	}

	@RequiresPermissions("home:plmPortalPlan:edit")
	@RequestMapping(value = "save")
	public String save(PlmPortalPlan plmPortalPlan, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmPortalPlan)){
			return form(plmPortalPlan, model);
		}
		plmPortalPlanService.save(plmPortalPlan);
		addMessage(redirectAttributes, "保存门户方案成功");
		return "redirect:"+Global.getAdminPath()+"/home/plmPortalPlan/?repage";
	}
	
	@RequiresPermissions("home:plmPortalPlan:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmPortalPlan plmPortalPlan, RedirectAttributes redirectAttributes) {
		plmPortalPlanService.delete(plmPortalPlan);
		addMessage(redirectAttributes, "删除门户方案成功");
		return "redirect:"+Global.getAdminPath()+"/home/plmPortalPlan/?repage";
	}

}