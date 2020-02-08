/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.web;

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
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowPolicy;
import com.arjjs.ccm.modules.ccm.know.service.CcmKnowPolicyService;

/**
 * 政策法规Controller
 * @author wwh
 * @version 2018-01-04
 */
@Controller
@RequestMapping(value = "${adminPath}/know/ccmKnowPolicy")
public class CcmKnowPolicyController extends BaseController {

	@Autowired
	private CcmKnowPolicyService ccmKnowPolicyService;
	
	@ModelAttribute
	public CcmKnowPolicy get(@RequestParam(required=false) String id) {
		CcmKnowPolicy entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmKnowPolicyService.get(id);
		}
		if (entity == null){
			entity = new CcmKnowPolicy();
		}
		return entity;
	}
	
	@RequiresPermissions("know:ccmKnowPolicy:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmKnowPolicy ccmKnowPolicy, String divId, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isNotEmpty(divId)) {
			ccmKnowPolicy.setContent(null);
		}
		Page<CcmKnowPolicy> page = ccmKnowPolicyService.findPage(new Page<CcmKnowPolicy>(request, response), ccmKnowPolicy); 
		model.addAttribute("page", page);
		return "ccm/know/ccmKnowPolicyList";
	}

	@RequiresPermissions("know:ccmKnowPolicy:view")
	@RequestMapping(value = "form")
	public String form(CcmKnowPolicy ccmKnowPolicy, Model model) {
		model.addAttribute("ccmKnowPolicy", ccmKnowPolicy);
		return "ccm/know/ccmKnowPolicyForm";
	}

	@RequiresPermissions("know:ccmKnowPolicy:edit")
	@RequestMapping(value = "save")
	public String save(CcmKnowPolicy ccmKnowPolicy, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmKnowPolicy)){
			return form(ccmKnowPolicy, model);
		}
		ccmKnowPolicyService.save(ccmKnowPolicy);
		addMessage(redirectAttributes, "保存政策法规成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowPolicy/?repage";
	}
	
	@RequiresPermissions("know:ccmKnowPolicy:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmKnowPolicy ccmKnowPolicy, RedirectAttributes redirectAttributes) {
		ccmKnowPolicyService.delete(ccmKnowPolicy);
		addMessage(redirectAttributes, "删除政策法规成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowPolicy/?repage";
	}

}