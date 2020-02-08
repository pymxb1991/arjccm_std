/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.addressbook.web;

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
import com.arjjs.ccm.modules.plm.addressbook.entity.PlmEmployeeGroups;
import com.arjjs.ccm.modules.plm.addressbook.service.PlmEmployeeGroupsService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 个人通讯录分组Controller
 * @author liucong
 * @version 2018-07-16
 */
@Controller
@RequestMapping(value = "${adminPath}/addressbook/plmEmployeeGroups")
public class PlmEmployeeGroupsController extends BaseController {

	@Autowired
	private PlmEmployeeGroupsService plmEmployeeGroupsService;
	
	@ModelAttribute
	public PlmEmployeeGroups get(@RequestParam(required=false) String id) {
		PlmEmployeeGroups entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmEmployeeGroupsService.get(id);
		}
		if (entity == null){
			entity = new PlmEmployeeGroups();
		}
		return entity;
	}
	
	@RequiresPermissions("addressbook:plmEmployeeGroups:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmEmployeeGroups plmEmployeeGroups, HttpServletRequest request, HttpServletResponse response, Model model) {
		plmEmployeeGroups.setUpdateBy(UserUtils.getUser());
		List<PlmEmployeeGroups> list = plmEmployeeGroupsService.findList(plmEmployeeGroups); 
		model.addAttribute("list", list);
		return "plm/addressbook/plmEmployeeGroupsList";
	}

	@RequiresPermissions("addressbook:plmEmployeeGroups:view")
	@RequestMapping(value = "form")
	public String form(PlmEmployeeGroups plmEmployeeGroups, Model model) {
		if (plmEmployeeGroups.getParent()!=null && StringUtils.isNotBlank(plmEmployeeGroups.getParent().getId())){
			plmEmployeeGroups.setParent(plmEmployeeGroupsService.get(plmEmployeeGroups.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(plmEmployeeGroups.getId())){
				PlmEmployeeGroups plmEmployeeGroupsChild = new PlmEmployeeGroups();
				plmEmployeeGroupsChild.setParent(new PlmEmployeeGroups(plmEmployeeGroups.getParent().getId()));
				List<PlmEmployeeGroups> list = plmEmployeeGroupsService.findList(plmEmployeeGroups); 
				if (list.size() > 0){
					plmEmployeeGroups.setSort(list.get(list.size()-1).getSort());
					if (plmEmployeeGroups.getSort() != null){
						plmEmployeeGroups.setSort(plmEmployeeGroups.getSort() + 30);
					}
				}
			}
		}
		if (plmEmployeeGroups.getSort() == null){
			plmEmployeeGroups.setSort(30);
		}
		model.addAttribute("plmEmployeeGroups", plmEmployeeGroups);
		return "plm/addressbook/plmEmployeeGroupsForm";
	}

	@RequiresPermissions("addressbook:plmEmployeeGroups:edit")
	@RequestMapping(value = "save")
	public String save(PlmEmployeeGroups plmEmployeeGroups, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmEmployeeGroups)){
			return form(plmEmployeeGroups, model);
		}
		plmEmployeeGroupsService.save(plmEmployeeGroups);
		addMessage(redirectAttributes, "保存个人通讯录分组成功");
		return "redirect:"+Global.getAdminPath()+"/addressbook/plmEmployeeGroups/?repage";
	}
	
	@RequiresPermissions("addressbook:plmEmployeeGroups:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmEmployeeGroups plmEmployeeGroups, RedirectAttributes redirectAttributes) {
		plmEmployeeGroupsService.delete(plmEmployeeGroups);
		addMessage(redirectAttributes, "删除个人通讯录分组成功");
		return "redirect:"+Global.getAdminPath()+"/addressbook/plmEmployeeGroups/?repage";
	}

	
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(PlmEmployeeGroups plmEmployeeGroups,@RequestParam(required=false) String extId, HttpServletResponse response) {
		
		List<Map<String, Object>> mapList = Lists.newArrayList();
		plmEmployeeGroups.setUpdateBy(UserUtils.getUser());
		List<PlmEmployeeGroups> list = plmEmployeeGroupsService.findList(plmEmployeeGroups);
		for (int i=0; i<list.size(); i++){
			PlmEmployeeGroups e = list.get(i);
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
	
}