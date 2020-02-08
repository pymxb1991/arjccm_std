/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.equipment;

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

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.storage.entity.PlmEquipment;
import com.arjjs.ccm.modules.plm.storage.entity.PlmProvideInfo;
import com.arjjs.ccm.modules.plm.storage.service.PlmEquipmentService;
import com.arjjs.ccm.modules.plm.storage.service.PlmProvideInfoService;

/**
 * 装备物资查询Controller
 * 只有查看功能     无操作功能 
 * @author liucong
 * @version 2018-06-29
 */
@Controller
@RequestMapping(value = "${adminPath}/equipment/plmEquipmentAssets")
public class PlmEquipmentAssetsController extends BaseController {

	@Autowired
	private PlmEquipmentService plmEquipmentService;
	@Autowired
	private PlmProvideInfoService plmProvideInfoService;
	
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
	/**
	 * 装备物资
	 * 列表查询
	 */
	@RequiresPermissions("equipment:plmEquipmentAssets:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmEquipment plmEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmEquipment> page = plmEquipmentService.findPage(new Page<PlmEquipment>(request, response), plmEquipment); 
		model.addAttribute("page", page);
		model.addAttribute("provideList", getProvidList());
		return "plm/equipment/plmEquipmentAssetsList";
	}
	/**
	 * 具体查询某一种装备物资详情
	 * 
	 */
	@RequiresPermissions("equipment:plmEquipmentAssets:view")
	@RequestMapping(value = "form")
	public String form(PlmEquipment plmEquipment, Model model) {
		model.addAttribute("plmEquipment", plmEquipment);
		model.addAttribute("provideList", getProvidList());
		return "plm/equipment/plmEquipmentAssetsForm";
	}
	/**
	 * 供应商的查询
	 */
	public List<PlmProvideInfo> getProvidList() {
		PlmProvideInfo plmProvideInfo = new PlmProvideInfo();
		return plmProvideInfoService.findList(plmProvideInfo);
	}
	
}