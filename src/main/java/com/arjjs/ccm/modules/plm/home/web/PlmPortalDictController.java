/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.home.web;

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
import com.arjjs.ccm.modules.plm.home.entity.PlmPortalDict;
import com.arjjs.ccm.modules.plm.home.service.PlmPortalDictService;

/**
 * 门户字典Controller
 * @author liuxue
 * @version 2018-07-04
 */
@Controller
@RequestMapping(value = "${adminPath}/home/plmPortalDict")
public class PlmPortalDictController extends BaseController {

	@Autowired
	private PlmPortalDictService plmPortalDictService;
	
	@ModelAttribute
	public PlmPortalDict get(@RequestParam(required=false) String id) {
		PlmPortalDict entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmPortalDictService.get(id);
		}
		if (entity == null){
			entity = new PlmPortalDict();
		}
		return entity;
	}
	
	@RequiresPermissions("home:plmPortalDict:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmPortalDict plmPortalDict, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmPortalDict> page = plmPortalDictService.findPage(new Page<PlmPortalDict>(request, response), plmPortalDict); 
		model.addAttribute("page", page);
		return "plm/home/plmPortalDictList";
	}

	@RequiresPermissions("home:plmPortalDict:view")
	@RequestMapping(value = "form")
	public String form(PlmPortalDict plmPortalDict, Model model) {
		model.addAttribute("plmPortalDict", plmPortalDict);
		return "plm/home/plmPortalDictForm";
	}

	@RequiresPermissions("home:plmPortalDict:edit")
	@RequestMapping(value = "save")
	public String save(PlmPortalDict plmPortalDict, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmPortalDict)){
			return form(plmPortalDict, model);
		}
		plmPortalDictService.save(plmPortalDict);
		addMessage(redirectAttributes, "保存门户字典成功");
		return "redirect:"+Global.getAdminPath()+"/home/plmPortalDict/?repage";
	}
	
	@RequiresPermissions("home:plmPortalDict:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmPortalDict plmPortalDict, RedirectAttributes redirectAttributes) {
		plmPortalDictService.delete(plmPortalDict);
		addMessage(redirectAttributes, "删除门户字典成功");
		return "redirect:"+Global.getAdminPath()+"/home/plmPortalDict/?repage";
	}

}