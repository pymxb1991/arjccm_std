/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.email.web;

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
import com.arjjs.ccm.modules.plm.email.entity.PlmEmailServer;
import com.arjjs.ccm.modules.plm.email.service.PlmEmailServerService;

/**
 * 邮箱配置Controller
 * @author liucong
 * @version 2018-07-24
 */
@Controller
@RequestMapping(value = "${adminPath}/email/plmEmailServer")
public class PlmEmailServerController extends BaseController {

	@Autowired
	private PlmEmailServerService plmEmailServerService;
	
	@ModelAttribute
	public PlmEmailServer get(@RequestParam(required=false) String id) {
		PlmEmailServer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmEmailServerService.get(id);
		}
		if (entity == null){
			entity = new PlmEmailServer();
		}
		return entity;
	}
	
	@RequiresPermissions("email:plmEmailServer:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmEmailServer plmEmailServer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmEmailServer> page = plmEmailServerService.findPage(new Page<PlmEmailServer>(request, response), plmEmailServer); 
		model.addAttribute("page", page);
		return "plm/email/plmEmailServerList";
	}

	@RequiresPermissions("email:plmEmailServer:view")
	@RequestMapping(value = "form")
	public String form(PlmEmailServer plmEmailServer, Model model) {
		model.addAttribute("plmEmailServer", plmEmailServer);
		return "plm/email/plmEmailServerForm";
	}

	@RequiresPermissions("email:plmEmailServer:edit")
	@RequestMapping(value = "save")
	public String save(PlmEmailServer plmEmailServer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmEmailServer)){
			return form(plmEmailServer, model);
		}
		plmEmailServerService.save(plmEmailServer);
		addMessage(redirectAttributes, "保存邮箱配置成功");
		return "redirect:"+Global.getAdminPath()+"/email/plmEmailServer/?repage";
	}
	
	@RequiresPermissions("email:plmEmailServer:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmEmailServer plmEmailServer, RedirectAttributes redirectAttributes) {
		plmEmailServerService.delete(plmEmailServer);
		addMessage(redirectAttributes, "删除邮箱配置成功");
		return "redirect:"+Global.getAdminPath()+"/email/plmEmailServer/?repage";
	}

}