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
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgCommonality;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmUserGroup;
import com.arjjs.ccm.modules.ccm.rest.service.CcmUserGroupService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 用户好友分组Controller
 * @author fu
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/rest/ccmUserGroup")
public class CcmUserGroupController extends BaseController {

	@Autowired
	private CcmUserGroupService ccmUserGroupService;
	
	@ModelAttribute
	public CcmUserGroup get(@RequestParam(required=false) String id) {
		CcmUserGroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmUserGroupService.get(id);
		}
		if (entity == null){
			entity = new CcmUserGroup();
		}
		return entity;
	}
	
	@RequiresPermissions("rest:ccmUserGroup:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmUserGroup ccmUserGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmUserGroup> page = ccmUserGroupService.findPage(new Page<CcmUserGroup>(request, response), ccmUserGroup); 
		model.addAttribute("page", page);
		return "ccm/rest/ccmUserGroupList";
	}

	@RequiresPermissions("rest:ccmUserGroup:view")
	@RequestMapping(value = "form")
	public String form(CcmUserGroup ccmUserGroup, Model model) {
		model.addAttribute("ccmUserGroup", ccmUserGroup);
		return "ccm/rest/ccmUserGroupForm";
	}

	@RequiresPermissions("rest:ccmUserGroup:edit")
	@RequestMapping(value = "save")
	public String save(CcmUserGroup ccmUserGroup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmUserGroup)){
			return form(ccmUserGroup, model);
		}
		ccmUserGroupService.save(ccmUserGroup);
		addMessage(redirectAttributes, "保存用户好友分组成功");
		return "redirect:"+Global.getAdminPath()+"/rest/ccmUserGroup/?repage";
	}
	
	@RequiresPermissions("rest:ccmUserGroup:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmUserGroup ccmUserGroup, RedirectAttributes redirectAttributes) {
		ccmUserGroupService.delete(ccmUserGroup);
		addMessage(redirectAttributes, "删除用户好友分组成功");
		return "redirect:"+Global.getAdminPath()+"/rest/ccmUserGroup/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<CcmUserGroup> list = ccmUserGroupService.findList(new CcmUserGroup());
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				CcmUserGroup c = list.get(i);
				if ((StringUtils.isBlank(extId) || (extId != null && !extId.equals(c.getId()) ) )) {
					Map<String, Object> map = Maps.newHashMap();
					map.put("id", c.getId());
					map.put("pId", "0");
					map.put("name", c.getGroupname());
					mapList.add(map);
				}
			}
		}
		return mapList;
	}
	

}