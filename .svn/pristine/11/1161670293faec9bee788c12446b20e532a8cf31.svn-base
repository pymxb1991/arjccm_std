/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.storage.entity.PlmStorage;
import com.arjjs.ccm.modules.plm.storage.service.PlmEquipmentService;
import com.arjjs.ccm.modules.plm.storage.service.PlmStorageService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.sf.json.JSONArray;

/**
 * 仓库管理Controller
 * @author dongqikai
 * @version 2018-06-27
 */
@Controller
@RequestMapping(value = "${adminPath}/storage/plmStorage")
public class PlmStorageController extends BaseController {
	@Autowired
	private PlmEquipmentService plmEquipmentService;
	@Autowired
	private PlmStorageService plmStorageService;
	
	@ModelAttribute
	public PlmStorage get(@RequestParam(required=false) String id) {
		PlmStorage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmStorageService.get(id);
		}
		if (entity == null){
			entity = new PlmStorage();
		}
		return entity;
	}
	
	@RequiresPermissions("storage:plmStorage:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmStorage plmStorage, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<PlmStorage> list = plmStorageService.findList(plmStorage); 
		model.addAttribute("list", list);
		return "plm/storage/plmStorageList";
	}

	@RequiresPermissions("storage:plmStorage:view")
	@RequestMapping(value = "form")
	public String form(PlmStorage plmStorage, Model model) {
		if (plmStorage.getParent()!=null && StringUtils.isNotBlank(plmStorage.getParent().getId())){
			plmStorage.setParent(plmStorageService.get(plmStorage.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(plmStorage.getId())){
				PlmStorage plmStorageChild = new PlmStorage();
				plmStorageChild.setParent(new PlmStorage(plmStorage.getParent().getId()));
				List<PlmStorage> list = plmStorageService.findList(plmStorage); 
				if (list.size() > 0){
					plmStorage.setSort(list.get(list.size()-1).getSort());
					if (plmStorage.getSort() != null){
						plmStorage.setSort(plmStorage.getSort() + 30);
					}
				}
			}
		}
		if (plmStorage.getSort() == null){
			plmStorage.setSort(30);
		}
		model.addAttribute("plmStorage", plmStorage);
		return "plm/storage/plmStorageForm";
	}

	@RequiresPermissions("storage:plmStorage:edit")
	@RequestMapping(value = "save")
	public String save(PlmStorage plmStorage, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmStorage)){
			return form(plmStorage, model);
		}
		plmStorageService.save(plmStorage);
		addMessage(redirectAttributes, "保存仓库信息成功");
		return "redirect:"+Global.getAdminPath()+"/storage/plmStorage/?repage";
	}
	
	@RequiresPermissions("storage:plmStorage:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmStorage plmStorage, RedirectAttributes redirectAttributes) {
		plmStorageService.delete(plmStorage);
		addMessage(redirectAttributes, "删除仓库信息成功");
		return "redirect:"+Global.getAdminPath()+"/storage/plmStorage/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<PlmStorage> list = plmStorageService.findList(new PlmStorage());
		for (int i=0; i<list.size(); i++){
			PlmStorage e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	
	/**
	 * 仓库、物资、物资类型总数量统计
	 * @param plmStorage
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "countStorageList")
	public JSONArray countStorageList(PlmStorage plmStorage, HttpServletResponse response) {
		List<PlmStorage> list =plmStorageService.countStorageList();
		   
		Integer countStorage=list.size();
		
		Integer countEquipment=plmEquipmentService.countEquipment();
		Integer countByType=plmEquipmentService.countByType();
		List<Object> list2=new ArrayList<>();
		list2.add(countStorage);
		list2.add(countEquipment);
		list2.add(countByType);
		
		JSONArray jsondata = JSONArray.fromObject(list2); 		 
		return jsondata;
	}
	
}