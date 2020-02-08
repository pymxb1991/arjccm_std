/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.emergencies.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjjs.ccm.modules.iot.warning.service.CcmEarlyWarningService;
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
import com.arjjs.ccm.modules.iot.warning.entity.CcmEarlyWarning;

/**
 * 突发事件Controller
 * @author lhf
 * @version 2019-07-25
 */
@Controller
@RequestMapping(value = "${adminPath}/emergencies/ccmEmergencies")
public class CcmEmergenciesController extends BaseController {

    @Autowired
    private CcmEarlyWarningService ccmEarlyWarningService;

	@ModelAttribute
	public CcmEarlyWarning get(@RequestParam(required=false) String id) {
		CcmEarlyWarning entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmEarlyWarningService.get(id);
		}
		if (entity == null){
			entity = new CcmEarlyWarning();
		}
		return entity;
	}
	
	@RequiresPermissions("emergencies:ccmEmergencies:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmEarlyWarning ccmEarlyWarning, HttpServletRequest request, HttpServletResponse response, Model model) {
		ccmEarlyWarning.setIsPeople("2");
		Page<CcmEarlyWarning> page = ccmEarlyWarningService.findPage(new Page<CcmEarlyWarning>(request, response), ccmEarlyWarning); 
		model.addAttribute("page", page);
		return "iot/emergencies/ccmEmergenciesList";
	}

	@RequiresPermissions("emergencies:ccmEmergencies:view")
	@RequestMapping(value = "form")
	public String form(CcmEarlyWarning ccmEarlyWarning, Model model) {
		model.addAttribute("ccmEarlyWarning", ccmEarlyWarning);
		return "iot/emergencies/ccmEmergenciesForm";
	}

	@RequiresPermissions("emergencies:ccmEmergencies:edit")
	@RequestMapping(value = "save")
	public String save(CcmEarlyWarning ccmEarlyWarning, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmEarlyWarning)){
			return form(ccmEarlyWarning, model);
		}
		ccmEarlyWarningService.save(ccmEarlyWarning);
		addMessage(redirectAttributes, "保存突发事件成功");
		return "redirect:"+Global.getAdminPath()+"/emergencies/ccmEmergencies/?repage";
	}
	
	@RequiresPermissions("emergencies:ccmEmergencies:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmEarlyWarning ccmEarlyWarning, RedirectAttributes redirectAttributes) {
		ccmEarlyWarningService.delete(ccmEarlyWarning);
		addMessage(redirectAttributes, "删除突发事件成功");
		return "redirect:"+Global.getAdminPath()+"/emergencies/ccmEmergencies/?repage";
	}

}