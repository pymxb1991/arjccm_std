/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.citycomponents.web;

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
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.citycomponents.entity.CcmCityCar;
import com.arjjs.ccm.modules.ccm.citycomponents.service.CcmCityCarService;

/**
 * 特殊车辆服务管理Controller
 * @author zjb
 * @version 2018-09-07
 */
@Controller
@RequestMapping(value = "${adminPath}/citycomponents/ccmCityCar")
public class CcmCityCarController extends BaseController {

	@Autowired
	private CcmCityCarService ccmCityCarService;
	
	@ModelAttribute
	public CcmCityCar get(@RequestParam(required=false) String id) {
		CcmCityCar entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmCityCarService.get(id);
		}
		if (entity == null){
			entity = new CcmCityCar();
		}
		return entity;
	}
	
	@RequiresPermissions("citycomponents:ccmCityCar:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmCityCar ccmCityCar, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmCityCar> page = ccmCityCarService.findPage(new Page<CcmCityCar>(request, response), ccmCityCar); 
		model.addAttribute("page", page);
		return "ccm/citycomponents/ccmCityCarList";
	}

	@RequiresPermissions("citycomponents:ccmCityCar:view")
	@RequestMapping(value = "form")
	public String form(CcmCityCar ccmCityCar, Model model) {
		model.addAttribute("ccmCityCar", ccmCityCar);
		return "ccm/citycomponents/ccmCityCarForm";
	}

	@RequiresPermissions("citycomponents:ccmCityCar:edit")
	@RequestMapping(value = "save")
	public String save(CcmCityCar ccmCityCar, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmCityCar)){
			return form(ccmCityCar, model);
		}
		ccmCityCarService.save(ccmCityCar);
		addMessage(redirectAttributes, "保存特殊车辆服务管理成功");
		return "redirect:"+Global.getAdminPath()+"/citycomponents/ccmCityCar/?repage";
	}
	
	@RequiresPermissions("citycomponents:ccmCityCar:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmCityCar ccmCityCar, RedirectAttributes redirectAttributes) {
		ccmCityCarService.delete(ccmCityCar);
		addMessage(redirectAttributes, "删除特殊车辆服务管理成功");
		return "redirect:"+Global.getAdminPath()+"/citycomponents/ccmCityCar/?repage";
	}

}