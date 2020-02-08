/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildmanageUnit;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseBuildmanageUnitService;

/**
 * 楼栋单元户信息排列Controller
 * @author liu
 * @version 2019-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/house/ccmHouseBuildmanageUnit")
public class CcmHouseBuildmanageUnitController extends BaseController {

	@Autowired
	private CcmHouseBuildmanageUnitService ccmHouseBuildmanageUnitService;
	
	@ModelAttribute
	public CcmHouseBuildmanageUnit get(@RequestParam(required=false) String id) {
		CcmHouseBuildmanageUnit entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmHouseBuildmanageUnitService.get(id);
		}
		if (entity == null){
			entity = new CcmHouseBuildmanageUnit();
		}
		return entity;
	}
	
	@RequiresPermissions("house:ccmHouseBuildmanageUnit:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmHouseBuildmanageUnit ccmHouseBuildmanageUnit, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmHouseBuildmanageUnit> page = ccmHouseBuildmanageUnitService.findPage(new Page<CcmHouseBuildmanageUnit>(request, response), ccmHouseBuildmanageUnit); 
		model.addAttribute("page", page);
		return "ccm/house/ccmHouseBuildmanageUnitList";
	}

	@ResponseBody
	@RequestMapping(value = "findListByBuildmanageId")
	public List<CcmHouseBuildmanageUnit> findListByBuildmanageId (String buildmanageId) {
		return ccmHouseBuildmanageUnitService.findListByBuildmanageId(buildmanageId);
	}
	
	@RequiresPermissions("house:ccmHouseBuildmanageUnit:view")
	@RequestMapping(value = "form")
	public String form(CcmHouseBuildmanageUnit ccmHouseBuildmanageUnit, Model model) {
		model.addAttribute("ccmHouseBuildmanageUnit", ccmHouseBuildmanageUnit);
		return "ccm/house/ccmHouseBuildmanageUnitForm";
	}

	@RequiresPermissions("house:ccmHouseBuildmanageUnit:edit")
	@RequestMapping(value = "save")
	public String save(CcmHouseBuildmanageUnit ccmHouseBuildmanageUnit, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmHouseBuildmanageUnit)){
			return form(ccmHouseBuildmanageUnit, model);
		}
		ccmHouseBuildmanageUnitService.save(ccmHouseBuildmanageUnit);
		addMessage(redirectAttributes, "保存楼栋单元户信息排列成功");
		return "redirect:"+Global.getAdminPath()+"/house/ccmHouseBuildmanageUnit/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value = "saveData")
	public int saveData(@RequestBody List<CcmHouseBuildmanageUnit>  ccmHouseBuildmanageUnitList, HttpServletRequest request, HttpServletResponse response) {
		ccmHouseBuildmanageUnitService.deleteHouseBuildmanageUnit(ccmHouseBuildmanageUnitList);
		return ccmHouseBuildmanageUnitService.saveData(ccmHouseBuildmanageUnitList);
	}
	
	@RequiresPermissions("house:ccmHouseBuildmanageUnit:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmHouseBuildmanageUnit ccmHouseBuildmanageUnit, RedirectAttributes redirectAttributes) {
		ccmHouseBuildmanageUnitService.delete(ccmHouseBuildmanageUnit);
		addMessage(redirectAttributes, "删除楼栋单元户信息排列成功");
		return "redirect:"+Global.getAdminPath()+"/house/ccmHouseBuildmanageUnit/?repage";
	}
}