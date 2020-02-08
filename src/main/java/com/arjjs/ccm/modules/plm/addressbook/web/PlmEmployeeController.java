/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.addressbook.web;


import java.util.List;

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
import com.arjjs.ccm.modules.plm.addressbook.entity.PlmEmployee;
import com.arjjs.ccm.modules.plm.addressbook.service.PlmEmployeeService;
import com.arjjs.ccm.modules.plm.home.service.PlmPortalDictService;
import com.arjjs.ccm.modules.plm.storage.entity.PlmEquipment;

/**
 * 通讯录Controller
 * @author liucong
 * @version 2018-07-14
 */
@Controller
@RequestMapping(value = "${adminPath}/addressbook/plmEmployee")
public class PlmEmployeeController extends BaseController {

	@Autowired
	private PlmEmployeeService plmEmployeeService;
	@Autowired
	private PlmPortalDictService plmPortalDictService;
	@ModelAttribute
	public PlmEmployee get(@RequestParam(required=false) String id) {
		PlmEmployee entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmEmployeeService.get(id);
		}
		if (entity == null){
			entity = new PlmEmployee();
		}
		return entity;
	}
	
	// 显示到个人门户
	@RequiresPermissions("addressbook:plmEmployee:view")
	@RequestMapping(value = { "view" })
	public String view(PlmEmployee plmEmployee, HttpServletRequest request, HttpServletResponse response, Model model,
			String height, String width, String content,String divId) {
		int line = plmPortalDictService.line(content);
		plmEmployee.setTypes("1");
		Page<PlmEmployee> page = plmEmployeeService.findPage(new Page<PlmEmployee>(request, response), plmEmployee); 
		request.setAttribute("page", page);
		request.setAttribute("porheigh", height);
		request.setAttribute("line", line);
		request.setAttribute("porwidth", width);
		request.setAttribute("porcontent", content);
		request.setAttribute("divId", divId);
		return "plm/addressbook/plmEmployeeHomeList";
	}
	
	@RequiresPermissions("addressbook:plmEmployee:view")
	@RequestMapping(value = {"one"})
	public String one(PlmEmployee plmEmployee,HttpServletRequest request, HttpServletResponse response, Model model) {
		List<PlmEmployee> list = plmEmployeeService.findView(plmEmployee);
		request.setAttribute("list", list);
		return "plm/addressbook/plmEmployeeView";
	}
	
	@RequiresPermissions("addressbook:plmEmployee:view")
	@RequestMapping(value = {"index"})
	public String index(PlmEquipment plmEquipment, Model model) {
		return "plm/addressbook/plmEmployeePersonalAddressIndex";
	}
	
	
	
	@RequiresPermissions("addressbook:plmEmployee:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmEmployee plmEmployee, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmEmployee> page = plmEmployeeService.findPage(new Page<PlmEmployee>(request, response), plmEmployee); 
		model.addAttribute("page", page);
		return "plm/addressbook/plmEmployeeList";
	}

	@RequiresPermissions("addressbook:plmEmployee:view")
	@RequestMapping(value = "form")
	public String form(PlmEmployee plmEmployee, Model model) {
		model.addAttribute("plmEmployee", plmEmployee);
		return "plm/addressbook/plmEmployeeForm";
	}

	@RequiresPermissions("addressbook:plmEmployee:edit")
	@RequestMapping(value = "save")
	public String save(PlmEmployee plmEmployee, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmEmployee)){
			return form(plmEmployee, model);
		}
		plmEmployeeService.save(plmEmployee);
		addMessage(redirectAttributes, "保存通讯录成功");
		return "redirect:"+Global.getAdminPath()+"/addressbook/plmEmployee/?repage&types=1";
	}
	
	@RequiresPermissions("addressbook:plmEmployee:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmEmployee plmEmployee, RedirectAttributes redirectAttributes) {
		plmEmployeeService.delete(plmEmployee);
		addMessage(redirectAttributes, "删除通讯录成功");
		return "redirect:"+Global.getAdminPath()+"/addressbook/plmEmployee/?repage&types=1";
	}

}