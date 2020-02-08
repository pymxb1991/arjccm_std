/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.resource.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.plm.resource.entity.PlmResource;
import com.arjjs.ccm.modules.plm.resource.entity.PlmResourceUser;
import com.arjjs.ccm.modules.plm.resource.service.PlmResourceService;
import com.arjjs.ccm.modules.plm.resource.service.PlmResourceUserService;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

/**
 * 资源共享Controller
 * @author liucong
 * @version 2018-07-20
 */
@Controller
@RequestMapping(value = "${adminPath}/resource/plmOtherResource")
public class PlmOtherResourceController extends BaseController {

	@Autowired
	private PlmResourceService plmResourceService;
	@Autowired
	PlmResourceUserService	plmResourceUserService;
	@ModelAttribute
	public PlmResourceUser get(@RequestParam(required=false) String id) {
		PlmResourceUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmResourceUserService.get(id);
		}
		if (entity == null){
			entity = new PlmResourceUser();
		}
		return entity;
	}
	
	@RequiresPermissions("resource:plmOtherResource:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmResource plmResource, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PlmResource> page = plmResourceService.findPage(new Page<PlmResource>(request, response), plmResource); 
		String userId=UserUtils.getUser().getId();
		List<PlmResource> list = plmResourceService.findListAllById(userId);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		return "plm/resource/plmOtherResourceList";
	}

	@RequiresPermissions("resource:plmOtherResource:view")
	@RequestMapping(value = "form")
	public String form(PlmResource plmResource, Model model) {
		PlmResource list = plmResourceService.findListById(plmResource);
		model.addAttribute("plmResource", list);
		return "plm/resource/plmOtherResourceForm";
	}

/*	@RequiresPermissions("resource:plmOtherResource:edit")
	@RequestMapping(value = "save")
	public String save(PlmResource plmResource, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmResource)){
			return form(plmResource, model);
		}
		plmResourceService.save(plmResource);
		addMessage(redirectAttributes, "保存资源共享成功");
		return "redirect:"+Global.getAdminPath()+"/resource/plmOtherResource/?repage&type=03";
	}*/
	
	@RequiresPermissions("resource:plmOtherResource:edit")
	@RequestMapping(value = "delete")
	public String delete(String rId, RedirectAttributes redirectAttributes) {
		/*plmResourceUserService.delete(plmResourceUser);*/
		plmResourceUserService.deleteRId(rId);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + Global.getAdminPath() + "/resource/plmOtherResource/?repage&type=03";
	}

}