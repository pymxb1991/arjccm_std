/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.addressbook.web;

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
import com.arjjs.ccm.modules.plm.storage.entity.PlmEquipment;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.PlmTypes;

/**
 * 个人通讯录Controller
 * @author liucong
 * @version 2018-07-14
 */
@Controller
@RequestMapping(value = "${adminPath}/addressbook/plmEmployeePersonalAddress")
public class plmEmployeePersonalAddressController extends BaseController {

	@Autowired
	private PlmEmployeeService plmEmployeeService;
	PlmTypes plmTypes;
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
	
	@RequiresPermissions("addressbook:plmEmployeePersonalAddress:view")
	@RequestMapping(value = {"index"})
	public String index(PlmEquipment plmEquipment, Model model) {
		return "plm/addressbook/plmEmployeePersonalAddressIndex";
	}
	
	@RequiresPermissions("addressbook:plmEmployeePersonalAddress:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmEmployee plmEmployee, HttpServletRequest request, HttpServletResponse response, Model model) {
		plmEmployee.setUpdateBy(UserUtils.getUser());
		Page<PlmEmployee> page = plmEmployeeService.findPage(new Page<PlmEmployee>(request, response), plmEmployee); 
		model.addAttribute("page", page);
		return "plm/addressbook/plmEmployeePersonalAddressList";
	}

	@RequiresPermissions("addressbook:plmEmployeePersonalAddress:view")
	@RequestMapping(value = "form")
	public String form(PlmEmployee plmEmployee, Model model) {
		model.addAttribute("plmEmployee", plmEmployee);
		return "plm/addressbook/plmEmployeePersonalAddressForm";
	}

	@SuppressWarnings("static-access")
	@RequiresPermissions("addressbook:plmEmployeePersonalAddress:edit")
	@RequestMapping(value = "save")
	public String save(PlmEmployee plmEmployee, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmEmployee)){
			return form(plmEmployee, model);
		}
		plmEmployee.setType(plmTypes.TYPE);
		plmEmployeeService.save(plmEmployee);
		addMessage(redirectAttributes, "保存通讯录成功");
		return "redirect:"+Global.getAdminPath()+"/addressbook/plmEmployeePersonalAddress/?repage&type=1";
	}
	
	@RequiresPermissions("addressbook:plmEmployeePersonalAddress:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmEmployee plmEmployee, RedirectAttributes redirectAttributes) {
		plmEmployeeService.delete(plmEmployee);
		addMessage(redirectAttributes, "删除通讯录成功");
		return "redirect:"+Global.getAdminPath()+"/addressbook/plmEmployeePersonalAddress/?repage&type=1";
	}

}