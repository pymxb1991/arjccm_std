/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.web;

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
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgLeadduty;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgLeaddutyService;

/**
 * 综治领导责任制Controller
 * @author wwh
 * @version 2018-01-23
 */
@Controller
@RequestMapping(value = "${adminPath}/org/ccmOrgLeadduty")
public class CcmOrgLeaddutyController extends BaseController {

	@Autowired
	private CcmOrgLeaddutyService ccmOrgLeaddutyService;
	
	@ModelAttribute
	public CcmOrgLeadduty get(@RequestParam(required=false) String id) {
		CcmOrgLeadduty entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmOrgLeaddutyService.get(id);
		}
		if (entity == null){
			entity = new CcmOrgLeadduty();
		}
		return entity;
	}
	
	@RequiresPermissions("org:ccmOrgLeadduty:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmOrgLeadduty ccmOrgLeadduty, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmOrgLeadduty> page = ccmOrgLeaddutyService.findPage(new Page<CcmOrgLeadduty>(request, response), ccmOrgLeadduty); 
		model.addAttribute("page", page);
		return "ccm/org/ccmOrgLeaddutyList";
	}

	@RequiresPermissions("org:ccmOrgLeadduty:view")
	@RequestMapping(value = "form")
	public String form(CcmOrgLeadduty ccmOrgLeadduty, Model model) {
		model.addAttribute("ccmOrgLeadduty", ccmOrgLeadduty);
		return "ccm/org/ccmOrgLeaddutyForm";
	}

	@RequiresPermissions("org:ccmOrgLeadduty:edit")
	@RequestMapping(value = "save")
	public String save(CcmOrgLeadduty ccmOrgLeadduty, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmOrgLeadduty)){
			return form(ccmOrgLeadduty, model);
		}
		ccmOrgLeaddutyService.save(ccmOrgLeadduty);
		addMessage(redirectAttributes, "保存综治领导责任制成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgLeadduty/?repage";
	}
	
	@RequiresPermissions("org:ccmOrgLeadduty:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmOrgLeadduty ccmOrgLeadduty, RedirectAttributes redirectAttributes) {
		ccmOrgLeaddutyService.delete(ccmOrgLeadduty);
		addMessage(redirectAttributes, "删除综治领导责任制成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgLeadduty/?repage";
	}

}