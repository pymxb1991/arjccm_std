/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.tree.web;

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
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.tree.entity.CcmMapCollectZtree;
import com.arjjs.ccm.modules.ccm.tree.entity.CcmTree;
import com.arjjs.ccm.modules.ccm.tree.service.CcmMapCollectZtreeService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 首页地图收藏树Controller
 * @author liuxue
 * @version 2018-09-14
 */
@Controller
@RequestMapping(value = "${adminPath}/tree/ccmMapCollectZtree")
public class CcmMapCollectZtreeController extends BaseController {

	@Autowired
	private CcmMapCollectZtreeService ccmMapCollectZtreeService;
	
	@ModelAttribute
	public CcmMapCollectZtree get(@RequestParam(required=false) String id) {
		CcmMapCollectZtree entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmMapCollectZtreeService.get(id);
		}
		if (entity == null){
			entity = new CcmMapCollectZtree();
		}
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value = {"radio"})
	public List radio(CcmMapCollectZtree ccmMapCollectZtree, HttpServletRequest request, HttpServletResponse response, Model model) {
		ccmMapCollectZtree.setType("parent");
		List<CcmMapCollectZtree> list = ccmMapCollectZtreeService.findList(ccmMapCollectZtree); 
		model.addAttribute("List", list);
		return list;
	}
   
	@RequestMapping(value = {"list", ""})
	public String list(CcmMapCollectZtree ccmMapCollectZtree, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CcmMapCollectZtree> list = ccmMapCollectZtreeService.findList(ccmMapCollectZtree); 
		model.addAttribute("List", list);
		return "ccm/tree/ccmMapCollectZtreeList";
	}
	
	
	@RequiresPermissions("tree:ccmMapCollectZtree:view")
	@RequestMapping(value = "form")
	public String form(CcmMapCollectZtree ccmMapCollectZtree, Model model) {
		model.addAttribute("ccmMapCollectZtree", ccmMapCollectZtree);
		return "ccm/tree/ccmMapCollectZtreeForm";
	}

	@ResponseBody
	@RequestMapping(value = "save")
	public String save(CcmMapCollectZtree ccmMapCollectZtree, String id, String parentId,String type,
			String name,String areaPoint,String areaMap) {
		
		ccmMapCollectZtree.setExtend1(id);
		CcmMapCollectZtree ccmMapCollectZtree2 = ccmMapCollectZtreeService.getZId(ccmMapCollectZtree);
		if(ccmMapCollectZtree2 == null) {
			id="";
		}else {
			id=ccmMapCollectZtree2.getId();
			
		}
		
		ccmMapCollectZtree.setId(id);
		
		ccmMapCollectZtree.setName(name);
		ccmMapCollectZtree.setType(type);
		ccmMapCollectZtree.setParentId(parentId);
		ccmMapCollectZtree.setAreaPoint(areaPoint);
		ccmMapCollectZtree.setAreaMap(areaMap);
		
		
		ccmMapCollectZtreeService.save(ccmMapCollectZtree);
		
		return "收藏成功";
	}
	
	@RequiresPermissions("tree:ccmMapCollectZtree:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmMapCollectZtree ccmMapCollectZtree, RedirectAttributes redirectAttributes) {
		ccmMapCollectZtreeService.delete(ccmMapCollectZtree);
		addMessage(redirectAttributes, "删除首页地图收藏树成功");
		return "redirect:"+Global.getAdminPath()+"/tree/ccmMapCollectZtree/?repage";
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "del")
	public String del(CcmMapCollectZtree ccmMapCollectZtree, RedirectAttributes redirectAttributes) {
		ccmMapCollectZtreeService.delete(ccmMapCollectZtree);	
		return "删除成功";
	}
    
	

	@ResponseBody
	@RequestMapping(value = "saveCollect")
	public String saveCollect(CcmMapCollectZtree ccmMapCollectZtree, RedirectAttributes redirectAttributes) {
		
		ccmMapCollectZtree.setParentId("collect");
		ccmMapCollectZtree.setType("parent");
		
		ccmMapCollectZtreeService.save(ccmMapCollectZtree);
		
		return "保存成功";
	}
	
	
	/***
	 * 地图标注树状图
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeDataNew")
	public List<Map<String, Object>> treeDataNew(@RequestParam(required = false) String extId,
			 HttpServletResponse response,CcmMapCollectZtree ccmMapCollectZtree) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<CcmMapCollectZtree> list = ccmMapCollectZtreeService.findList(ccmMapCollectZtree);
		for (int i = 0; i < list.size(); i++) {
			CcmMapCollectZtree e = list.get(i);
		
				// 当前的是否为被取消的内容
				if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId()))) {
					Map<String, Object> map = Maps.newHashMap();
					map.put("id", e.getId());//装备id
					map.put("zId", e.getExtend1());//id
					map.put("pId", e.getParentId());
					map.put("name", e.getName());
					map.put("point", StringUtils.isEmpty(e.getAreaPoint()) ? false : true);
					// 当前城市部件  点线面
					

					map.put("areaMap", e.getAreaMap());
					map.put("areaPoint", e.getAreaPoint());
					map.put("type", e.getType());
					map.put("icon", "");
					mapList.add(map);
				}
			}
		
		return mapList;
	}
	
}