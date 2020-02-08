/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.web;

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
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgControl;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgControlService;

/**
 * 自治组织管理Controller
 * @author liuyongjian
 * @version 2019-08-13
 */
@Controller
@RequestMapping(value = "${adminPath}/org/ccmOrgControl")
public class CcmOrgControlController extends BaseController {

	@Autowired
	private CcmOrgControlService ccmOrgControlService;
	
	@ModelAttribute
	public CcmOrgControl get(@RequestParam(required=false) String id) {
		CcmOrgControl entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmOrgControlService.get(id);
		}
		if (entity == null){
			entity = new CcmOrgControl();
		}
		return entity;
	}
	
	@RequiresPermissions("org:ccmOrgControl:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmOrgControl ccmOrgControl, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmOrgControl> page = ccmOrgControlService.findPage(new Page<CcmOrgControl>(request, response), ccmOrgControl); 
		model.addAttribute("page", page);
		return "ccm/org/ccmOrgControlList";
	}
	
	@RequiresPermissions("org:ccmOrgControl:view")
	@RequestMapping(value = { "orgcontrolList" })
	public String orgcontrolList() {
		return "ccm/org/orgcontrolList";
	}

	@RequiresPermissions("org:ccmOrgControl:view")
	@RequestMapping(value = "form")
	public String form(CcmOrgControl ccmOrgControl, Model model) {
		model.addAttribute("ccmOrgControl", ccmOrgControl);
		return "ccm/org/ccmOrgControlForm";
	}

	@RequiresPermissions("org:ccmOrgControl:edit")
	@RequestMapping(value = "save")
	public String save(CcmOrgControl ccmOrgControl, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmOrgControl)){
			return form(ccmOrgControl, model);
		}
		ccmOrgControlService.save(ccmOrgControl);
		addMessage(redirectAttributes, "保存自治组织成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgControl/?repage";
	}
	
	@RequiresPermissions("org:ccmOrgControl:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmOrgControl ccmOrgControl, RedirectAttributes redirectAttributes) {
		ccmOrgControlService.delete(ccmOrgControl);
		addMessage(redirectAttributes, "删除自治组织成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgControl/?repage";
	}

}