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
import com.arjjs.ccm.modules.plm.storage.entity.PlmCheckDetial;
import com.arjjs.ccm.modules.plm.storage.service.PlmCheckDetialService;

/**
 * 盘点详细Controller
 * @author dongqikai
 * @version 2018-07-10
 */
@Controller
@RequestMapping(value = "${adminPath}/storage/plmCheckDetial")
public class PlmCheckDetialController extends BaseController {

	@Autowired
	private PlmCheckDetialService plmCheckDetialService;
	
	@ModelAttribute
	public PlmCheckDetial get(@RequestParam(required=false) String id) {
		PlmCheckDetial entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmCheckDetialService.get(id);
		}
		if (entity == null){
			entity = new PlmCheckDetial();
		}
		return entity;
	}
	
	@RequiresPermissions("storage:plmCheckDetial:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmCheckDetial plmCheckDetial, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmCheckDetial> page = plmCheckDetialService.findPage(new Page<PlmCheckDetial>(request, response), plmCheckDetial); 
		model.addAttribute("page", page);
		return "plm/storage/plmCheckDetialList";
	}

	@RequiresPermissions("storage:plmCheckDetial:view")
	@RequestMapping(value = "form")
	public String form(PlmCheckDetial plmCheckDetial, Model model) {
		model.addAttribute("plmCheckDetial", plmCheckDetial);
		return "plm/storage/plmCheckDetialForm";
	}

	@RequiresPermissions("storage:plmCheckDetial:edit")
	@RequestMapping(value = "save")
	public String save(PlmCheckDetial plmCheckDetial, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmCheckDetial)){
			return form(plmCheckDetial, model);
		}
		plmCheckDetialService.save(plmCheckDetial);
		addMessage(redirectAttributes, "保存盘点详细成功");
		return "redirect:"+Global.getAdminPath()+"/storage/plmCheckDetial/?repage";
	}
	
	@RequiresPermissions("storage:plmCheckDetial:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmCheckDetial plmCheckDetial, RedirectAttributes redirectAttributes) {
		plmCheckDetialService.delete(plmCheckDetial);
		addMessage(redirectAttributes, "删除盘点详细成功");
		return "redirect:"+Global.getAdminPath()+"/storage/plmCheckDetial/?repage";
	}

}