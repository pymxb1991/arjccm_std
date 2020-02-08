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

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.storage.entity.PlmEquipment;
import com.arjjs.ccm.modules.plm.storage.service.PlmEquipmentService;
import com.arjjs.ccm.modules.sys.utils.DictUtils;

/**
 * 后勤物资Controller
 * @author fu
 * @version 2018-07-10
 */
@Controller
@RequestMapping(value = "${adminPath}/logistics/plmEquipmentLogi")
public class PlmEquipmentLogiController extends BaseController {

	@Autowired
	private PlmEquipmentService plmEquipmentService;
	
	@ModelAttribute
	public PlmEquipment get(@RequestParam(required=false) String id) {
		PlmEquipment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmEquipmentService.get(id);
		}
		if (entity == null){
			entity = new PlmEquipment();
		}
		return entity;
	}
	
	@RequiresPermissions("logistics:plmEquipmentLogi:view")
	@RequestMapping(value = {"index"})
	public String index(PlmEquipment plmEquipment, Model model) {
		return "plm/logistics/plmEquipmentLogiIndex";
	}
	
	@RequiresPermissions("logistics:plmEquipmentLogi:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmEquipment plmEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmEquipment> page = plmEquipmentService.findPageForLogi(new Page<PlmEquipment>(request, response), plmEquipment);
		model.addAttribute("page", page);
		model.addAttribute("provideList", plmEquipmentService.getProvidList());
		return "plm/logistics/plmEquipmentLogiList";
	}

	@RequiresPermissions("logistics:plmEquipmentLogi:view")
	@RequestMapping(value = "form")
	public String form(PlmEquipment plmEquipment, Model model) {
		model.addAttribute("plmEquipment", plmEquipment);
		model.addAttribute("provideList", plmEquipmentService.getProvidList());
		model.addAttribute("dictList", DictUtils.getDictList("plm_equipment_type_child"));
		return "plm/logistics/plmEquipmentLogiForm";
	}
	


}