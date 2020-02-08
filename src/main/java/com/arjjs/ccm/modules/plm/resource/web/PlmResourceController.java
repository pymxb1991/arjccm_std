/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.resource.web;

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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.resource.entity.PlmResource;
import com.arjjs.ccm.modules.plm.resource.entity.PlmResourceUser;
import com.arjjs.ccm.modules.plm.resource.service.PlmResourceService;
import com.arjjs.ccm.modules.plm.resource.service.PlmResourceUserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 资源共享Controller
 * @author liucong
 * @version 2018-07-20
 */
@Controller
@RequestMapping(value = "${adminPath}/resource/plmResource")
public class PlmResourceController extends BaseController {

	@Autowired
	private PlmResourceService plmResourceService;
	@Autowired
	private PlmResourceUserService plmResourceUserService;
	
	@ModelAttribute
	public PlmResource get(@RequestParam(required=false) String id) {
		PlmResource entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmResourceService.get(id);
		}
		if (entity == null){
			entity = new PlmResource();
		}
		return entity;
	}
	
	@RequiresPermissions("resource:plmResource:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmResource plmResource, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmResource> page = plmResourceService.findPage(new Page<PlmResource>(request, response), plmResource); 
		model.addAttribute("page", page);
		return "plm/resource/plmResourceList";
	}

	@RequiresPermissions("resource:plmResource:view")
	@RequestMapping(value = "form")
	public String form(PlmResourceUser plmResourceUser,PlmResource plmResource, Model model) {
		if (plmResourceUser.getParent()!=null && StringUtils.isNotBlank(plmResourceUser.getParent().getId())){
			plmResourceUser.setParent(plmResourceUserService.get(plmResourceUser.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(plmResourceUser.getId())){
				PlmResourceUser plmResourceUserChild = new PlmResourceUser();
				plmResourceUserChild.setParent(new PlmResourceUser(plmResourceUser.getParent().getId()));
				List<PlmResourceUser> list = plmResourceUserService.findList(plmResourceUser); 
				if (list.size() > 0){
					plmResourceUser.setSort(list.get(list.size()-1).getSort());
					if (plmResourceUser.getSort() != null){
						plmResourceUser.setSort(plmResourceUser.getSort() + 30);
					}
				}
			}
		}
		if (plmResourceUser.getSort() == null){
			plmResourceUser.setSort(30);
		}
		model.addAttribute("plmResourceUser", plmResourceUser);
		model.addAttribute("plmResource", plmResource);
		return "plm/resource/plmResourceForm";
	}

	@RequiresPermissions("resource:plmResource:edit")
	@RequestMapping(value = "save")
	public String save(PlmResourceUser plmResourceUser,PlmResource plmResource, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmResource)){
			return form(plmResourceUser,plmResource, model);
		}
		plmResource.setType("01");
		plmResourceService.save(plmResource);
		plmResourceUser.setrId(plmResource.getId());
		plmResourceUserService.save(plmResourceUser);
		addMessage(redirectAttributes, "保存资源成功");
		return "redirect:"+Global.getAdminPath()+"/resource/plmResource/?repage&type=01";
	}
	
	@RequiresPermissions("resource:plmResource:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmResource plmResource, RedirectAttributes redirectAttributes) {
		plmResourceService.delete(plmResource);
		addMessage(redirectAttributes, "删除资源共享成功");
		return "redirect:"+Global.getAdminPath()+"/resource/plmResource/?repage&type=01";
	}
	
	@RequiresPermissions("resource:plmResource:edit")
	@RequestMapping(value = "saveUser")
	public String save(PlmResourceUser plmResourceUser,HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmResourceUser)){
			addMessage(redirectAttributes, "分享失败");
			return "redirect:"+Global.getAdminPath()+"/resource/plmResource/?repage&type=01";
		}
		String uId=request.getParameter("uId");
		plmResourceUser.setuId(uId);
		plmResourceUserService.save(plmResourceUser);
		addMessage(redirectAttributes, "分享成功");
		return "redirect:"+Global.getAdminPath()+"/resource/plmResource/?repage&type=01";
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<PlmResourceUser> list = plmResourceUserService.findList(new PlmResourceUser());
		for (int i=0; i<list.size(); i++){
			PlmResourceUser e = list.get(i);
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