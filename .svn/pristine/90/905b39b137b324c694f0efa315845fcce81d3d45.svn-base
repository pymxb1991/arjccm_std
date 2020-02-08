/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.web;

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
import com.arjjs.ccm.modules.ccm.service.entity.CcmServiceGuidance;
import com.arjjs.ccm.modules.ccm.service.service.CcmServiceGuidanceService;

/**
 * 用户指南表Controller
 * @author fuxinshuang
 * @version 2018-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/service/ccmServiceGuidance")
public class CcmServiceGuidanceController extends BaseController {

	@Autowired
	private CcmServiceGuidanceService ccmServiceGuidanceService;
	
	@ModelAttribute
	public CcmServiceGuidance get(@RequestParam(required=false) String id) {
		CcmServiceGuidance entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmServiceGuidanceService.get(id);
		}
		if (entity == null){
			entity = new CcmServiceGuidance();
		}
		return entity;
	}
	
	@RequiresPermissions("service:ccmServiceGuidance:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmServiceGuidance ccmServiceGuidance, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmServiceGuidance> page = ccmServiceGuidanceService.findPage(new Page<CcmServiceGuidance>(request, response), ccmServiceGuidance); 
		model.addAttribute("page", page);
		return "ccm/service/ccmServiceGuidanceList";
	}

	@RequiresPermissions("service:ccmServiceGuidance:view")
	@RequestMapping(value = "form")
	public String form(CcmServiceGuidance ccmServiceGuidance, Model model) {
		model.addAttribute("ccmServiceGuidance", ccmServiceGuidance);
		return "ccm/service/ccmServiceGuidanceForm";
	}

	@RequiresPermissions("service:ccmServiceGuidance:edit")
	@RequestMapping(value = "save")
	public String save(CcmServiceGuidance ccmServiceGuidance, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmServiceGuidance)){
			return form(ccmServiceGuidance, model);
		}
		ccmServiceGuidanceService.save(ccmServiceGuidance);
		addMessage(redirectAttributes, "保存用户指南信息成功");
		return "redirect:"+Global.getAdminPath()+"/service/ccmServiceGuidance/?repage";
	}
	
	@RequiresPermissions("service:ccmServiceGuidance:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmServiceGuidance ccmServiceGuidance, RedirectAttributes redirectAttributes) {
		ccmServiceGuidanceService.delete(ccmServiceGuidance);
		addMessage(redirectAttributes, "删除用户指南信息成功");
		return "redirect:"+Global.getAdminPath()+"/service/ccmServiceGuidance/?repage";
	}

}