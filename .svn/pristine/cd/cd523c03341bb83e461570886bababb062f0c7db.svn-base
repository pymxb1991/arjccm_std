/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.policy.web;

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
import com.arjjs.ccm.modules.plm.home.service.PlmPortalDictService;
import com.arjjs.ccm.modules.plm.policy.entity.PlmKnowPolicy;
import com.arjjs.ccm.modules.plm.policy.service.PlmKnowPolicyService;
import com.arjjs.ccm.tool.PlmTypes;

/**
 * 政策法规Controller
 * @author liu
 * @version 2018-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/policy/plmKnowPolicy")
public class PlmKnowPolicyController extends BaseController {

	@Autowired
	private PlmKnowPolicyService plmKnowPolicyService;
	@Autowired
	private PlmPortalDictService plmPortalDictService;
	PlmTypes plmTypes;
	@ModelAttribute
	public PlmKnowPolicy get(@RequestParam(required=false) String id) {
		PlmKnowPolicy entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = plmKnowPolicyService.get(id);
		}
		if (entity == null){
			entity = new PlmKnowPolicy();
		}
		return entity;
	}
	//显示到个人首页
	
		@SuppressWarnings("static-access")
		@RequiresPermissions("policy:plmKnowPolicy:view")
		@RequestMapping(value = { "view" })
		public String view(PlmKnowPolicy plmKnowPolicy, HttpServletRequest request, HttpServletResponse response, Model model,
				String height, String width, String content,String divId) {
			int line = plmPortalDictService.line(content);
			plmKnowPolicy.setTypes(plmTypes.KNOW_POLICY);
			List<PlmKnowPolicy> list = plmKnowPolicyService.findView(plmKnowPolicy);
			request.setAttribute("list", list);
			request.setAttribute("porheigh", height);
			request.setAttribute("line", line);
			request.setAttribute("porwidth", width);
			request.setAttribute("porcontent", content);
			request.setAttribute("divId", divId);
			return "plm/policy/plmKnowPolicyHomeList";
		}
		
		
	@SuppressWarnings("static-access")
	@RequiresPermissions("policy:plmKnowPolicy:view")
	@RequestMapping(value = {"list", ""})
	public String list(PlmKnowPolicy plmKnowPolicy, HttpServletRequest request, HttpServletResponse response, Model model) {
		plmKnowPolicy.setTypes(plmTypes.KNOW_POLICY);
		Page<PlmKnowPolicy> page = plmKnowPolicyService.findPage(new Page<PlmKnowPolicy>(request, response), plmKnowPolicy); 
		model.addAttribute("page", page);
		return "plm/policy/plmKnowPolicyList";
	}

	@RequiresPermissions("policy:plmKnowPolicy:view")
	@RequestMapping(value = "form")
	public String form(PlmKnowPolicy plmKnowPolicy, Model model) {
		model.addAttribute("plmKnowPolicy", plmKnowPolicy);
		return "plm/policy/plmKnowPolicyForm";
	}

	@RequiresPermissions("policy:plmKnowPolicy:view")
	@RequestMapping(value = "one")
	public String view(PlmKnowPolicy plmKnowPolicy, Model model) {
		model.addAttribute("plmKnowPolicy", plmKnowPolicy);
		return "plm/policy/plmKnowPolicyView";
	}
	
	@RequiresPermissions("policy:plmKnowPolicy:edit")
	@RequestMapping(value = "save")
	public String save(PlmKnowPolicy plmKnowPolicy, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, plmKnowPolicy)){
			return form(plmKnowPolicy, model);
		}
		plmKnowPolicyService.save(plmKnowPolicy);
		addMessage(redirectAttributes, "保存政策法规成功");
		return "redirect:"+Global.getAdminPath()+"/policy/plmKnowPolicy/?repage";
	}
	
	@RequiresPermissions("policy:plmKnowPolicy:edit")
	@RequestMapping(value = "delete")
	public String delete(PlmKnowPolicy plmKnowPolicy, RedirectAttributes redirectAttributes) {
		plmKnowPolicyService.delete(plmKnowPolicy);
		addMessage(redirectAttributes, "删除政策法规成功");
		return "redirect:"+Global.getAdminPath()+"/policy/plmKnowPolicy/?repage";
	}

	
}