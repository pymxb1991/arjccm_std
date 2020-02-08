/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.rest.web;

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
import com.arjjs.ccm.modules.ccm.rest.entity.CcmUserGroup;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmUserRelationship;
import com.arjjs.ccm.modules.ccm.rest.service.CcmUserGroupService;
import com.arjjs.ccm.modules.ccm.rest.service.CcmUserRelationshipService;
import com.arjjs.ccm.modules.ccm.view.entity.VCcmTeam;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 用户关系Controller
 * @author fu
 * @version 2018-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/rest/ccmUserRelationship")
public class CcmUserRelationshipController extends BaseController {

	@Autowired
	private CcmUserRelationshipService ccmUserRelationshipService;
	@Autowired
	private CcmUserGroupService ccmUserGroupService;
	@ModelAttribute
	public CcmUserRelationship get(@RequestParam(required=false) String id) {
		CcmUserRelationship entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmUserRelationshipService.get(id);
		}
		if (entity == null){
			entity = new CcmUserRelationship();
		}
		return entity;
	}
	
	@RequiresPermissions("rest:ccmUserRelationship:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmUserRelationship ccmUserRelationship, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmUserRelationship> pageUR=new Page<CcmUserRelationship>(request, response);
		/*if(pageUR.getPageNo() ==){
			pageUR.setPageNo(0);
			
		}*/
		Page<CcmUserRelationship> page = ccmUserRelationshipService.findPage(pageUR, ccmUserRelationship); 
		model.addAttribute("page", page);
		return "ccm/rest/ccmUserRelationshipList";
	}
     
	@RequiresPermissions("rest:ccmUserRelationship:view")
	@RequestMapping(value = {"index"})
	public String index(CcmUserRelationship ccmUserRelationship,Model model) {
		return "ccm/rest/ccmUserRelationshipIndex";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		
		List<CcmUserGroup> list = ccmUserGroupService.findList(new CcmUserGroup());
		for (int i=0; i<list.size(); i++){
			//VCcmTeam e = list.get(i);
			CcmUserGroup e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()))){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", "1");
				map.put("name", e.getGroupname());
				mapList.add(map);
			}
		}
		return mapList;
	}
	
	
	@RequiresPermissions("rest:ccmUserRelationship:view")
	@RequestMapping(value = "form")
	public String form(CcmUserRelationship ccmUserRelationship, Model model) {
		model.addAttribute("ccmUserRelationship", ccmUserRelationship);
		return "ccm/rest/ccmUserRelationshipForm";
	}

	@RequiresPermissions("rest:ccmUserRelationship:edit")
	@RequestMapping(value = "save")
	public String save(CcmUserRelationship ccmUserRelationship, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmUserRelationship)){
			return form(ccmUserRelationship, model);
		}
		CcmUserRelationship ccmuserrelation = new CcmUserRelationship();
		if(ccmUserRelationship.getUser() != null && StringUtils.isNotEmpty(ccmUserRelationship.getGroupId())) {
			ccmuserrelation.setUser(ccmUserRelationship.getUser());
			ccmuserrelation.setGroupId(ccmUserRelationship.getGroupId());
		}
		List<CcmUserRelationship> list = ccmUserRelationshipService.findList(ccmuserrelation);
		if(list.size()==0) {
			ccmUserRelationshipService.save(ccmUserRelationship);
			addMessage(redirectAttributes, "保存用户关系成功");
		}else {
			addMessage(redirectAttributes, "保存用户关系失败，用户关系已存在");
		}
		return "redirect:"+Global.getAdminPath()+"/rest/ccmUserRelationship/?repage";
	}
	
	@RequiresPermissions("rest:ccmUserRelationship:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmUserRelationship ccmUserRelationship, RedirectAttributes redirectAttributes) {
		ccmUserRelationshipService.delete(ccmUserRelationship);
		addMessage(redirectAttributes, "删除用户关系成功");
		return "redirect:"+Global.getAdminPath()+"/rest/ccmUserRelationship/?repage";
	}

}