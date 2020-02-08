/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.logistics.web;

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
import com.arjjs.ccm.modules.plm.logistics.entity.PlmRoomAttendee;
import com.arjjs.ccm.modules.plm.logistics.service.PlmRoomAttendeeService;

/**
 * 参会人员Controller
 * @author fu
 * @version 2018-06-27
 */
@Controller
@RequestMapping(value = "${adminPath}/logistics/plmRoomAttendee")
public class PlmRoomAttendeeController extends BaseController {

	@Autowired
	private PlmRoomAttendeeService plmRoomAttendeeService;
	
	@ModelAttribute
	public PlmRoomAttendee get(@RequestParam(required=false) String id) {
		PlmRoomAttendee entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmRoomAttendeeService.get(id);
		}
		if (entity == null){
			entity = new PlmRoomAttendee();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(PlmRoomAttendee plmRoomAttendee, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmRoomAttendee> page = plmRoomAttendeeService.findPage(new Page<PlmRoomAttendee>(request, response), plmRoomAttendee); 
		model.addAttribute("page", page);
		return "plm/logistics/plmRoomAttendeeList";
	}

	@RequiresPermissions("logistics:plmRoomAttendee:view")
	@RequestMapping(value = "form")
	public String form(PlmRoomAttendee plmRoomAttendee, Model model) {
		model.addAttribute("plmRoomAttendee", plmRoomAttendee);
		return "plm/logistics/plmRoomAttendeeForm";
	}

	@RequiresPermissions("logistics:plmRoomAttendee:edit")
	@RequestMapping(value = "save")
	public String save(PlmRoomAttendee plmRoomAttendee, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmRoomAttendee)){
			return form(plmRoomAttendee, model);
		}
		plmRoomAttendeeService.save(plmRoomAttendee);
		addMessage(redirectAttributes, "保存参会人员成功");
		return "redirect:"+Global.getAdminPath()+"/logistics/plmRoomAttendee/?repage";
	}
	
	@RequiresPermissions("logistics:plmRoomAttendee:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmRoomAttendee plmRoomAttendee, RedirectAttributes redirectAttributes) {
		plmRoomAttendeeService.delete(plmRoomAttendee);
		addMessage(redirectAttributes, "删除参会人员成功");
		return "redirect:"+Global.getAdminPath()+"/logistics/plmRoomAttendee/?repage";
	}

}