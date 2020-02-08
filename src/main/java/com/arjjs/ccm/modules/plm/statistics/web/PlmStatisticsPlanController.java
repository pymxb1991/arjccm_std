/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.statistics.web;

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
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsPlan;
import com.arjjs.ccm.modules.plm.statistics.service.PlmStatisticsPlanService;

/**
 * 统计首页方案Controller
 * @author liuxue
 * @version 2018-07-24
 */
@Controller
@RequestMapping(value = "${adminPath}/statistics/plmStatisticsPlan")
public class PlmStatisticsPlanController extends BaseController {

	@Autowired
	private PlmStatisticsPlanService plmStatisticsPlanService;
	
	@ModelAttribute
	public PlmStatisticsPlan get(@RequestParam(required=false) String id) {
		PlmStatisticsPlan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmStatisticsPlanService.get(id);
		}
		if (entity == null){
			entity = new PlmStatisticsPlan();
		}
		return entity;
	}
	
	@RequiresPermissions("statistics:plmStatisticsPlan:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmStatisticsPlan plmStatisticsPlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmStatisticsPlan> page = plmStatisticsPlanService.findPage(new Page<PlmStatisticsPlan>(request, response), plmStatisticsPlan); 
		model.addAttribute("page", page);
		return "plm/statistics/plmStatisticsPlanList";
	}

	@RequiresPermissions("statistics:plmStatisticsPlan:view")
	@RequestMapping(value = "form")
	public String form(PlmStatisticsPlan plmStatisticsPlan, Model model) {
		model.addAttribute("plmStatisticsPlan", plmStatisticsPlan);
		return "plm/statistics/plmStatisticsPlanForm";
	}

	@RequiresPermissions("statistics:plmStatisticsPlan:edit")
	@RequestMapping(value = "save")
	public String save(PlmStatisticsPlan plmStatisticsPlan, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmStatisticsPlan)){
			return form(plmStatisticsPlan, model);
		}
		plmStatisticsPlanService.save(plmStatisticsPlan);
		addMessage(redirectAttributes, "保存统计首页方案成功");
		return "redirect:"+Global.getAdminPath()+"/statistics/plmStatisticsPlan/?repage";
	}
	
	@RequiresPermissions("statistics:plmStatisticsPlan:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmStatisticsPlan plmStatisticsPlan, RedirectAttributes redirectAttributes) {
		plmStatisticsPlanService.delete(plmStatisticsPlan);
		addMessage(redirectAttributes, "删除统计首页方案成功");
		return "redirect:"+Global.getAdminPath()+"/statistics/plmStatisticsPlan/?repage";
	}

}