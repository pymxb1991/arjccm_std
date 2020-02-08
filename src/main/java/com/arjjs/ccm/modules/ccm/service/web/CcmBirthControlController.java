/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.web;

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
import com.arjjs.ccm.modules.ccm.service.entity.CcmBirthControl;
import com.arjjs.ccm.modules.ccm.service.service.CcmBirthControlService;

/**
 * 计生管理Controller
 * @author pengjianqiang
 * @version 2019-02-25
 */
@Controller
@RequestMapping(value = "${adminPath}/service/ccmBirthControl")
public class CcmBirthControlController extends BaseController {

	@Autowired
	private CcmBirthControlService ccmBirthControlService;
	
	@ModelAttribute
	public CcmBirthControl get(@RequestParam(required=false) String id) {
		CcmBirthControl entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmBirthControlService.get(id);
		}
		if (entity == null){
			entity = new CcmBirthControl();
		}
		return entity;
	}
	
	@RequiresPermissions("service:ccmBirthControl:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmBirthControl ccmBirthControl, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmBirthControl> page = ccmBirthControlService.findPage(new Page<CcmBirthControl>(request, response), ccmBirthControl); 
		model.addAttribute("page", page);
		return "ccm/service/ccmBirthControlList";
	}

	@RequiresPermissions("service:ccmBirthControl:view")
	@RequestMapping(value = "form")
	public String form(CcmBirthControl ccmBirthControl, Model model) {
		model.addAttribute("ccmBirthControl", ccmBirthControl);
		return "ccm/service/ccmBirthControlForm";
	}

	@RequiresPermissions("service:ccmBirthControl:edit")
	@RequestMapping(value = "save")
	public String save(CcmBirthControl ccmBirthControl, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmBirthControl)){
			return form(ccmBirthControl, model);
		}
		ccmBirthControlService.save(ccmBirthControl);
		addMessage(redirectAttributes, "保存计生管理成功");
		return "redirect:"+Global.getAdminPath()+"/service/ccmBirthControl/?repage";
	}
	
	@RequiresPermissions("service:ccmBirthControl:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmBirthControl ccmBirthControl, RedirectAttributes redirectAttributes) {
		ccmBirthControlService.delete(ccmBirthControl);
		addMessage(redirectAttributes, "删除计生管理成功");
		return "redirect:"+Global.getAdminPath()+"/service/ccmBirthControl/?repage";
	}

}