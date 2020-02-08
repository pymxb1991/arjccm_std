/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.sys.web;

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
import com.arjjs.ccm.modules.sys.entity.SysCodes;
import com.arjjs.ccm.modules.sys.service.SysCodesService;

/**
 * 编码方案管理Controller
 * @author dongqikai
 * @version 2018-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysCodes")
public class SysCodesController extends BaseController {

	@Autowired
	private SysCodesService sysCodesService;
	
	@ModelAttribute
	public SysCodes get(@RequestParam(required=false) String id) {
		SysCodes entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysCodesService.get(id);
		}
		if (entity == null){
			entity = new SysCodes();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:sysCodes:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysCodes sysCodes, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysCodes> page = sysCodesService.findPage(new Page<SysCodes>(request, response), sysCodes); 
		model.addAttribute("page", page);
		return "modules/sys/sysCodesList";
	}

	@RequiresPermissions("sys:sysCodes:view")
	@RequestMapping(value = "form")
	public String form(SysCodes sysCodes, Model model) {
		model.addAttribute("sysCodes", sysCodes);
		return "modules/sys/sysCodesForm";
	}

	@RequiresPermissions("sys:sysCodes:edit")
	@RequestMapping(value = "save")
	public String save(SysCodes sysCodes, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysCodes)){
			return form(sysCodes, model);
		}
		sysCodesService.save(sysCodes);
		addMessage(redirectAttributes, "保存编码方案成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysCodes/?repage";
	}
	
	@RequiresPermissions("sys:sysCodes:edit")
	@RequestMapping(value = "delete")
	public String delete(SysCodes sysCodes, RedirectAttributes redirectAttributes) {
		sysCodesService.delete(sysCodes);
		addMessage(redirectAttributes, "删除编码方案成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysCodes/?repage";
	}

}