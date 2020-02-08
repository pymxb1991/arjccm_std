/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.web;

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
import com.arjjs.ccm.modules.plm.car.entity.PlmCarUse;
import com.arjjs.ccm.modules.plm.car.entity.PlmCarViolation;
import com.arjjs.ccm.modules.plm.car.service.PlmCarUseService;
import com.arjjs.ccm.modules.plm.car.service.PlmCarViolationService;

/**
 * 违章记录Controller
 * @author fu
 * @version 2018-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/car/plmCarViolation")
public class PlmCarViolationController extends BaseController {

	@Autowired
	private PlmCarViolationService plmCarViolationService;
	@Autowired
	private PlmCarUseService plmCarUseService;
	
	@ModelAttribute
	public PlmCarViolation get(@RequestParam(required=false) String id) {
		PlmCarViolation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmCarViolationService.get(id);
		}
		if (entity == null){
			entity = new PlmCarViolation();
		}
		return entity;
	}
	
	@RequiresPermissions("car:plmCarViolation:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmCarViolation plmCarViolation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmCarViolation> page = plmCarViolationService.findPage(new Page<PlmCarViolation>(request, response), plmCarViolation); 
		model.addAttribute("page", page);
		return "plm/car/plmCarViolationList";
	}
	//从领用记录列表添加违章
	@RequiresPermissions("car:plmCarViolation:view")
	@RequestMapping(value = {"addFromUseList"})
	public String addFromUseList(PlmCarViolation plmCarViolation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmCarViolation> page = plmCarViolationService.findPage(new Page<PlmCarViolation>(request, response), plmCarViolation); 
		model.addAttribute("page", page);
		model.addAttribute("carUseId", plmCarViolation.getCarUseId());
		return "plm/car/plmOneUseViolationList";
	}
	@RequiresPermissions("car:plmCarViolation:view")
	@RequestMapping(value = "addform")
	public String addform(PlmCarViolation plmCarViolation, Model model) {
		//如果根据领用单新增，实体不为空，但无id，需将领用单人员信息放入实体中，实现页面默认添加
		if(StringUtils.isBlank(plmCarViolation.getId()) && StringUtils.isNotBlank(plmCarViolation.getCarUseId())){
			PlmCarUse carUse = plmCarUseService.get(plmCarViolation.getCarUseId());
			plmCarViolation.setCar(carUse.getCar());
			plmCarViolation.setDriver(carUse.getDriver());
			plmCarViolation.setUse(carUse.getUse());
		}
		model.addAttribute("plmCarViolation", plmCarViolation);
		return "plm/car/plmOneUseViolationForm";
	}	
	@RequiresPermissions("car:plmCarViolation:view")
	@RequestMapping(value = "form")
	public String form(PlmCarViolation plmCarViolation, Model model) {
		model.addAttribute("plmCarViolation", plmCarViolation);
		return "plm/car/plmCarViolationForm";
	}

	@RequiresPermissions("car:plmCarViolation:edit")
	@RequestMapping(value = "save")
	public String save(PlmCarViolation plmCarViolation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmCarViolation)){
			return form(plmCarViolation, model);
		}
		plmCarViolationService.save(plmCarViolation);
		addMessage(redirectAttributes, "保存违章记录成功");
		return "redirect:"+Global.getAdminPath()+"/car/plmCarViolation/addFromUseList?carUseId="+plmCarViolation.getCarUseId();
	}
	
	@RequiresPermissions("car:plmCarViolation:edit")
	@RequestMapping(value = "save2")
	public String save2(PlmCarViolation plmCarViolation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmCarViolation)){
			return form(plmCarViolation, model);
		}
		plmCarViolationService.save(plmCarViolation);
		addMessage(redirectAttributes, "保存违章记录成功");
		return "redirect:"+Global.getAdminPath()+"/car/plmCarViolation/?repage";
	}
	
	@RequiresPermissions("car:plmCarViolation:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmCarViolation plmCarViolation, RedirectAttributes redirectAttributes) {
		plmCarViolationService.delete(plmCarViolation);
		addMessage(redirectAttributes, "删除违章记录成功");
		return "redirect:"+Global.getAdminPath()+"/car/plmCarViolation/?repage";
	}

}