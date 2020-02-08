/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.appversion.web;

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
import com.arjjs.ccm.modules.ccm.appversion.entity.AppVersion;
import com.arjjs.ccm.modules.ccm.appversion.service.AppVersionService;

/**
 * app版本Controller
 * @author lijiupeng
 * @version 2019-08-13
 */
@Controller
@RequestMapping(value = "${adminPath}/appversion/appVersion")
public class AppVersionController extends BaseController {

	@Autowired
	private AppVersionService appVersionService;
	
	@ModelAttribute
	public AppVersion get(@RequestParam(required=false) String id) {
		AppVersion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = appVersionService.get(id);
		}
		if (entity == null){
			entity = new AppVersion();
		}
		return entity;
	}
	
	@RequiresPermissions("appversion:appVersion:view")
	@RequestMapping(value = {"list", ""})
	public String list(AppVersion appVersion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AppVersion> page = appVersionService.findPage(new Page<AppVersion>(request, response), appVersion);

		model.addAttribute("page", page);
		return "ccm/appversion/appVersionList";
	}

	@RequiresPermissions("appversion:appVersion:view")
	@RequestMapping(value = "form")
	public String form(AppVersion appVersion, Model model) {
		model.addAttribute("appVersion", appVersion);
		return "ccm/appversion/appVersionForm";
	}

	@RequiresPermissions("appversion:appVersion:edit")
	@RequestMapping(value = "save")
	public String save(AppVersion appVersion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, appVersion)){
			return form(appVersion, model);
		}
		appVersionService.save(appVersion);
		addMessage(redirectAttributes, "保存app版本成功");
		return "redirect:"+Global.getAdminPath()+"/appversion/appVersion/?repage";
	}
	
	@RequiresPermissions("appversion:appVersion:edit")
	@RequestMapping(value = "delete")
	public String delete(AppVersion appVersion, RedirectAttributes redirectAttributes) {
		appVersionService.delete(appVersion);
		addMessage(redirectAttributes, "删除app版本成功");
		return "redirect:"+Global.getAdminPath()+"/appversion/appVersion/?repage";
	}

}