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
import com.arjjs.ccm.modules.plm.email.entity.PlmEmailBox;
import com.arjjs.ccm.modules.plm.email.service.PlmEmailBoxService;

/**
 * 邮箱表Controller
 * @author liucong
 * @version 2018-07-24
 */
@Controller
@RequestMapping(value = "${adminPath}/email/plmEmailBox")
public class PlmEmailBoxController extends BaseController {

	@Autowired
	private PlmEmailBoxService plmEmailBoxService;
	
	@ModelAttribute
	public PlmEmailBox get(@RequestParam(required=false) String id) {
		PlmEmailBox entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmEmailBoxService.get(id);
		}
		if (entity == null){
			entity = new PlmEmailBox();
		}
		return entity;
	}
	
	@RequiresPermissions("email:plmEmailBox:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmEmailBox plmEmailBox, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmEmailBox> page = plmEmailBoxService.findPage(new Page<PlmEmailBox>(request, response), plmEmailBox); 
		model.addAttribute("page", page);
		return "plm/email/plmEmailBoxList";
	}

	@RequiresPermissions("email:plmEmailBox:view")
	@RequestMapping(value = "form")
	public String form(PlmEmailBox plmEmailBox, Model model) {
		model.addAttribute("plmEmailBox", plmEmailBox);
		return "plm/email/plmEmailBoxForm";
	}

	@RequiresPermissions("email:plmEmailBox:edit")
	@RequestMapping(value = "save")
	public String save(PlmEmailBox plmEmailBox, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmEmailBox)){
			return form(plmEmailBox, model);
		}
		plmEmailBoxService.save(plmEmailBox);
		addMessage(redirectAttributes, "保存邮箱表成功");
		return "redirect:"+Global.getAdminPath()+"/email/plmEmailBox/?repage";
	}
	
	@RequiresPermissions("email:plmEmailBox:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmEmailBox plmEmailBox, RedirectAttributes redirectAttributes) {
		plmEmailBoxService.delete(plmEmailBox);
		addMessage(redirectAttributes, "删除邮箱表成功");
		return "redirect:"+Global.getAdminPath()+"/email/plmEmailBox/?repage";
	}

}