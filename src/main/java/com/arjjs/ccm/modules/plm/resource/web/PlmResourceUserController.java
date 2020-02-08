/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.resource.web;

import java.io.IOException;
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
import com.arjjs.ccm.modules.plm.resource.entity.PlmResourceUser;
import com.arjjs.ccm.modules.plm.resource.service.PlmResourceUserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 资源权限Controller
 * 
 * @author liucong
 * @version 2018-07-20
 */
@Controller
@RequestMapping(value = "${adminPath}/resource/plmResourceUser")
public class PlmResourceUserController extends BaseController {

	@Autowired
	private PlmResourceUserService plmResourceUserService;

	@ModelAttribute
	public PlmResourceUser get(@RequestParam(required = false) String id) {
		PlmResourceUser entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = plmResourceUserService.get(id);
		}
		if (entity == null) {
			entity = new PlmResourceUser();
		}
		return entity;
	}

	@RequiresPermissions("resource:plmResourceUser:view")
	@RequestMapping(value = { "list", "" })
	public String list(PlmResourceUser plmResourceUser, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PlmResourceUser> list = plmResourceUserService.findList(plmResourceUser);
		model.addAttribute("list", list);
		return "plm/resource/plmResourceUserList";
	}

	@RequiresPermissions("resource:plmResourceUser:view")
	@RequestMapping(value = "form")
	public String form(PlmResourceUser plmResourceUser, Model model) {
		if (plmResourceUser.getParent() != null && StringUtils.isNotBlank(plmResourceUser.getParent().getId())) {
			plmResourceUser.setParent(plmResourceUserService.get(plmResourceUser.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(plmResourceUser.getId())) {
				PlmResourceUser plmResourceUserChild = new PlmResourceUser();
				plmResourceUserChild.setParent(new PlmResourceUser(plmResourceUser.getParent().getId()));
				List<PlmResourceUser> list = plmResourceUserService.findList(plmResourceUser);
				if (list.size() > 0) {
					plmResourceUser.setSort(list.get(list.size() - 1).getSort());
					if (plmResourceUser.getSort() != null) {
						plmResourceUser.setSort(plmResourceUser.getSort() + 30);
					}
				}
			}
		}
		if (plmResourceUser.getSort() == null) {
			plmResourceUser.setSort(30);
		}
		model.addAttribute("plmResourceUser", plmResourceUser);
		return "plm/resource/plmResourceUserForm";
	}

	@RequestMapping(value = "save")
	public String save(PlmResourceUser plmResourceUser, HttpServletRequest request, HttpServletResponse response,
			Model model, RedirectAttributes redirectAttributes) throws IOException {
			plmResourceUserService.save(plmResourceUser);
		addMessage(redirectAttributes, "分享成功");
		return "redirect:" + Global.getAdminPath() + "/resource/plmResource/?repage&type=02";
	}

	@RequiresPermissions("resource:plmResourceUser:edit")
	@RequestMapping(value = "delete")
	public String delete(String rId, RedirectAttributes redirectAttributes) {
		
		plmResourceUserService.deleteRId(rId);
		addMessage(redirectAttributes, "删除资源成功");
		return "redirect:" + Global.getAdminPath() + "/resource/plmOtherResourceUser/?repage&type=03";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<PlmResourceUser> list = plmResourceUserService.findList(new PlmResourceUser());
		for (int i = 0; i < list.size(); i++) {
			PlmResourceUser e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId())
					&& e.getParentIds().indexOf("," + extId + ",") == -1)) {
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