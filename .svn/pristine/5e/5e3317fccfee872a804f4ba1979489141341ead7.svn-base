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
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgInfovideo;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgInfovideoService;

/**
 * 综治视联网信息中心Controller
 * @author fu
 * @version 2018-01-26
 */
@Controller
@RequestMapping(value = "${adminPath}/org/ccmOrgInfovideo")
public class CcmOrgInfovideoController extends BaseController {

	@Autowired
	private CcmOrgInfovideoService ccmOrgInfovideoService;
	
	@ModelAttribute
	public CcmOrgInfovideo get(@RequestParam(required=false) String id) {
		CcmOrgInfovideo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmOrgInfovideoService.get(id);
		}
		if (entity == null){
			entity = new CcmOrgInfovideo();
		}
		return entity;
	}
	
	@RequiresPermissions("org:ccmOrgInfovideo:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmOrgInfovideo ccmOrgInfovideo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmOrgInfovideo> page = ccmOrgInfovideoService.findPage(new Page<CcmOrgInfovideo>(request, response), ccmOrgInfovideo); 
		model.addAttribute("page", page);
		return "ccm/org/ccmOrgInfovideoList";
	}

	@RequiresPermissions("org:ccmOrgInfovideo:view")
	@RequestMapping(value = "form")
	public String form(CcmOrgInfovideo ccmOrgInfovideo, Model model) {
		model.addAttribute("ccmOrgInfovideo", ccmOrgInfovideo);
		return "ccm/org/ccmOrgInfovideoForm";
	}

	@RequiresPermissions("org:ccmOrgInfovideo:edit")
	@RequestMapping(value = "save")
	public String save(CcmOrgInfovideo ccmOrgInfovideo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmOrgInfovideo)){
			return form(ccmOrgInfovideo, model);
		}
		ccmOrgInfovideoService.save(ccmOrgInfovideo);
		addMessage(redirectAttributes, "保存综治视联网信息中心成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgInfovideo/?repage";
	}
	
	@RequiresPermissions("org:ccmOrgInfovideo:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmOrgInfovideo ccmOrgInfovideo, RedirectAttributes redirectAttributes) {
		ccmOrgInfovideoService.delete(ccmOrgInfovideo);
		addMessage(redirectAttributes, "删除综治视联网信息中心成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgInfovideo/?repage";
	}

}