/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.web;

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
import com.arjjs.ccm.modules.plm.storage.entity.PlmProvideInfo;
import com.arjjs.ccm.modules.plm.storage.service.PlmProvideInfoService;

/**
 * 供应商Controller
 * @author dongqikai
 * @version 2018-06-28
 */
@Controller
@RequestMapping(value = "${adminPath}/storage/plmProvideInfo")
public class PlmProvideInfoController extends BaseController {

	@Autowired
	private PlmProvideInfoService plmProvideInfoService;
	
	@ModelAttribute
	public PlmProvideInfo get(@RequestParam(required=false) String id) {
		PlmProvideInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmProvideInfoService.get(id);
		}
		if (entity == null){
			entity = new PlmProvideInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("storage:plmProvideInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmProvideInfo plmProvideInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmProvideInfo> page = plmProvideInfoService.findPage(new Page<PlmProvideInfo>(request, response), plmProvideInfo); 
		model.addAttribute("page", page);
		return "plm/storage/plmProvideInfoList";
	}

	@RequiresPermissions("storage:plmProvideInfo:view")
	@RequestMapping(value = "form")
	public String form(PlmProvideInfo plmProvideInfo, Model model) {
		model.addAttribute("plmProvideInfo", plmProvideInfo);
		return "plm/storage/plmProvideInfoForm";
	}

	@RequiresPermissions("storage:plmProvideInfo:edit")
	@RequestMapping(value = "save")
	public String save(PlmProvideInfo plmProvideInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmProvideInfo)){
			return form(plmProvideInfo, model);
		}
		plmProvideInfoService.save(plmProvideInfo);
		addMessage(redirectAttributes, "保存供应商信息成功");
		return "redirect:"+Global.getAdminPath()+"/storage/plmProvideInfo/?repage";
	}
	
	@RequiresPermissions("storage:plmProvideInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmProvideInfo plmProvideInfo, RedirectAttributes redirectAttributes) {
		plmProvideInfoService.delete(plmProvideInfo);
		addMessage(redirectAttributes, "删除供应商信息成功");
		return "redirect:"+Global.getAdminPath()+"/storage/plmProvideInfo/?repage";
	}

}