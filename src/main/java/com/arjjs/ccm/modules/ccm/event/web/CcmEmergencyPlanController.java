/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.web;

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
import com.arjjs.ccm.modules.ccm.event.entity.CcmEmergencyPlan;
import com.arjjs.ccm.modules.ccm.event.service.CcmEmergencyPlanService;

/**
 * 应急预案Controller
 * 
 * @author pengjianqiang
 * @version 2018-02-23
 */
@Controller
@RequestMapping(value = "${adminPath}/event/ccmEmergencyPlan")
public class CcmEmergencyPlanController extends BaseController {

	@Autowired
	private CcmEmergencyPlanService ccmEmergencyPlanService;

	@ModelAttribute
	public CcmEmergencyPlan get(@RequestParam(required = false) String id,
			@RequestParam(required = false) String eventScale, @RequestParam(required = false) String eventType) {
		CcmEmergencyPlan entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmEmergencyPlanService.get(id);
		}
		if (entity == null) {
			entity = new CcmEmergencyPlan();
			// 为预案 添加案事件分级
			if (StringUtils.isNotBlank(eventScale)) {
				entity.setEventScale(eventScale);
			}
			// 位预案添加案事件类型
			if (StringUtils.isNotBlank(eventType)) {
				entity.setEventScale(eventType);
			}
		}
		return entity;
	}

	@RequiresPermissions("event:ccmEmergencyPlan:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmEmergencyPlan ccmEmergencyPlan, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CcmEmergencyPlan> page = ccmEmergencyPlanService.findPage(new Page<CcmEmergencyPlan>(request, response),
				ccmEmergencyPlan);
		model.addAttribute("page", page);
		return "ccm/event/ccmEmergencyPlanList";
	}

	@RequiresPermissions("event:ccmEmergencyPlan:view")
	@RequestMapping(value = "form")
	public String form(CcmEmergencyPlan ccmEmergencyPlan, Model model) {
		model.addAttribute("ccmEmergencyPlan", ccmEmergencyPlan);
		return "ccm/event/ccmEmergencyPlanForm";
	}

	@RequiresPermissions("event:ccmEmergencyPlan:edit")
	@RequestMapping(value = "save")
	public String save(CcmEmergencyPlan ccmEmergencyPlan, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmEmergencyPlan)) {
			return form(ccmEmergencyPlan, model);
		}
		ccmEmergencyPlanService.save(ccmEmergencyPlan);
		addMessage(redirectAttributes, "保存应急预案成功");
		return "redirect:" + Global.getAdminPath() + "/event/ccmEmergencyPlan/?repage";
	}

	@RequiresPermissions("event:ccmEmergencyPlan:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmEmergencyPlan ccmEmergencyPlan, RedirectAttributes redirectAttributes) {
		ccmEmergencyPlanService.delete(ccmEmergencyPlan);
		addMessage(redirectAttributes, "删除应急预案成功");
		return "redirect:" + Global.getAdminPath() + "/event/ccmEmergencyPlan/?repage";
	}

	@RequiresPermissions("event:ccmEmergencyPlan:view")
	@RequestMapping(value = "solveList")
	public String solveList(CcmEmergencyPlan ccmEmergencyPlan, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<CcmEmergencyPlan> list = ccmEmergencyPlanService.findList(ccmEmergencyPlan);
		model.addAttribute("list", list);
		model.addAttribute("ccmemergencyplan", ccmEmergencyPlan);
		if (list.size() == 1) {
			return solveForm(list.get(0), model);
		} else {
			return "/ccm/event/eventMap/EmergencyPlanMapList";
		}
	}

	@RequiresPermissions("event:ccmEmergencyPlan:view")
	@RequestMapping(value = "solveForm")
	public String solveForm(CcmEmergencyPlan ccmEmergencyPlan, Model model) {
		model.addAttribute("ccmEmergencyPlan", ccmEmergencyPlan);
		return "/ccm/event/eventMap/EmergencyPlanMapForm";
	}
}