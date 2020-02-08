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
import com.arjjs.ccm.modules.ccm.service.entity.CcmServiceCivil;
import com.arjjs.ccm.modules.ccm.service.service.CcmServiceCivilService;

/**
 * 民政工作管理Controller
 * @author liang
 * @version 2018-08-02
 */
@Controller
@RequestMapping(value = "${adminPath}/service/ccmServiceCivil")
public class CcmServiceCivilController extends BaseController {

	@Autowired
	private CcmServiceCivilService ccmServiceCivilService;
	
	@ModelAttribute
	public CcmServiceCivil get(@RequestParam(required=false) String id) {
		CcmServiceCivil entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmServiceCivilService.get(id);
		}
		if (entity == null){
			entity = new CcmServiceCivil();
		}
		return entity;
	}
	
	@RequiresPermissions("service:ccmServiceCivil:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmServiceCivil ccmServiceCivil, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmServiceCivil> page = ccmServiceCivilService.findPage(new Page<CcmServiceCivil>(request, response), ccmServiceCivil); 
		model.addAttribute("page", page);
		return "ccm/service/ccmServiceCivilList";
	}

	@RequiresPermissions("service:ccmServiceCivil:view")
	@RequestMapping(value = "form")
	public String form(CcmServiceCivil ccmServiceCivil, Model model) {
		model.addAttribute("ccmServiceCivil", ccmServiceCivil);
		return "ccm/service/ccmServiceCivilForm";
	}

	@RequiresPermissions("service:ccmServiceCivil:edit")
	@RequestMapping(value = "save")
	public String save(CcmServiceCivil ccmServiceCivil, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmServiceCivil)){
			return form(ccmServiceCivil, model);
		}
		ccmServiceCivilService.save(ccmServiceCivil);
		addMessage(redirectAttributes, "保存民政工作管理成功");
		return "redirect:"+Global.getAdminPath()+"/service/ccmServiceCivil/?repage";
	}
	
	@RequiresPermissions("service:ccmServiceCivil:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmServiceCivil ccmServiceCivil, RedirectAttributes redirectAttributes) {
		ccmServiceCivilService.delete(ccmServiceCivil);
		addMessage(redirectAttributes, "删除民政工作管理成功");
		return "redirect:"+Global.getAdminPath()+"/service/ccmServiceCivil/?repage";
	}

}