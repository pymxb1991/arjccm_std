/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.deviceuse.web;

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
import com.arjjs.ccm.modules.flat.deviceuse.entity.CcmDeviceUse;
import com.arjjs.ccm.modules.flat.deviceuse.service.CcmDeviceUseService;

/**
 * 移动设备使用记录实体类Controller
 * @author lgh
 * @version 2019-07-12
 */
@Controller
@RequestMapping(value = "${adminPath}/deviceuse/ccmDeviceUse")
public class CcmDeviceUseController extends BaseController {

	@Autowired
	private CcmDeviceUseService ccmDeviceUseService;
	
	@ModelAttribute
	public CcmDeviceUse get(@RequestParam(required=false) String id) {
		CcmDeviceUse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmDeviceUseService.get(id);
		}
		if (entity == null){
			entity = new CcmDeviceUse();
		}
		return entity;
	}
	
	@RequiresPermissions("deviceuse:ccmDeviceUse:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmDeviceUse ccmDeviceUse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmDeviceUse> page = ccmDeviceUseService.findPage(new Page<CcmDeviceUse>(request, response), ccmDeviceUse); 
		model.addAttribute("page", page);
		return "flat/deviceuse/ccmDeviceUseList";
	}

	@RequiresPermissions("deviceuse:ccmDeviceUse:view")
	@RequestMapping(value = "form")
	public String form(CcmDeviceUse ccmDeviceUse, Model model) {
		model.addAttribute("ccmDeviceUse", ccmDeviceUse);
		return "flat/deviceuse/ccmDeviceUseForm";
	}

	@RequiresPermissions("deviceuse:ccmDeviceUse:edit")
	@RequestMapping(value = "save")
	public String save(CcmDeviceUse ccmDeviceUse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmDeviceUse)){
			return form(ccmDeviceUse, model);
		}
		ccmDeviceUseService.save(ccmDeviceUse);
		addMessage(redirectAttributes, "保存移动设备使用记录成功");
		return "redirect:"+Global.getAdminPath()+"/deviceuse/ccmDeviceUse/?repage";
	}
	
	@RequiresPermissions("deviceuse:ccmDeviceUse:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmDeviceUse ccmDeviceUse, RedirectAttributes redirectAttributes) {
		ccmDeviceUseService.delete(ccmDeviceUse);
		addMessage(redirectAttributes, "删除移动设备使用记录成功");
		return "redirect:"+Global.getAdminPath()+"/deviceuse/ccmDeviceUse/?repage";
	}

}