/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.car.entity.PlmCarRepair;
import com.arjjs.ccm.modules.plm.car.service.PlmCarRepairService;
import com.arjjs.ccm.tool.Select2Type;

/**
 * 维保单位Controller
 * @author fu
 * @version 2018-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/car/plmCarRepair")
public class PlmCarRepairController extends BaseController {

	@Autowired
	private PlmCarRepairService plmCarRepairService;
	
	@ModelAttribute
	public PlmCarRepair get(@RequestParam(required=false) String id) {
		PlmCarRepair entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmCarRepairService.get(id);
		}
		if (entity == null){
			entity = new PlmCarRepair();
		}
		return entity;
	}
	
	@RequiresPermissions("car:plmCarRepair:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmCarRepair plmCarRepair, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmCarRepair> page = plmCarRepairService.findPage(new Page<PlmCarRepair>(request, response), plmCarRepair); 
		model.addAttribute("page", page);
		return "plm/car/plmCarRepairList";
	}

	@RequiresPermissions("car:plmCarRepair:view")
	@RequestMapping(value = "form")
	public String form(PlmCarRepair plmCarRepair, Model model) {
		model.addAttribute("plmCarRepair", plmCarRepair);
		return "plm/car/plmCarRepairForm";
	}

	@RequiresPermissions("car:plmCarRepair:edit")
	@RequestMapping(value = "save")
	public String save(PlmCarRepair plmCarRepair, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmCarRepair)){
			return form(plmCarRepair, model);
		}
		plmCarRepairService.save(plmCarRepair);
		addMessage(redirectAttributes, "保存维保单位成功");
		return "redirect:"+Global.getAdminPath()+"/car/plmCarRepair/?repage";
	}
	
	@RequiresPermissions("car:plmCarRepair:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmCarRepair plmCarRepair, RedirectAttributes redirectAttributes) {
		plmCarRepairService.delete(plmCarRepair);
		addMessage(redirectAttributes, "删除维保单位成功");
		return "redirect:"+Global.getAdminPath()+"/car/plmCarRepair/?repage";
	}
	@ResponseBody
	@RequestMapping(value = {"selectList"})
	public List<Select2Type> selectList(Model model,PlmCarRepair plmCarRepair) {
		List<Select2Type> list = plmCarRepairService.findSelect2Type(plmCarRepair); 
		return list;
	}
	@ResponseBody
	@RequestMapping(value = {"getById"})
	public PlmCarRepair getById(@RequestParam(required=false) String id) {
		PlmCarRepair entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmCarRepairService.get(id);
		}
		if (entity == null){
			entity = new PlmCarRepair();
		}
		return entity;
	}	
}