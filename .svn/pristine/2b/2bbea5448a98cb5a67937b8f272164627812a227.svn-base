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
import com.arjjs.ccm.modules.ccm.service.entity.CcmServiceDuty;
import com.arjjs.ccm.modules.ccm.service.service.CcmServiceDutyService;

/**
 * 工作职责Controller
 * @author liang
 * @version 2018-08-02
 */
@Controller
@RequestMapping(value = "${adminPath}/service/ccmServiceDuty")
public class CcmServiceDutyController extends BaseController {

	@Autowired
	private CcmServiceDutyService ccmServiceDutyService;
	
	@ModelAttribute
	public CcmServiceDuty get(@RequestParam(required=false) String id) {
		CcmServiceDuty entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmServiceDutyService.get(id);
		}
		if (entity == null){
			entity = new CcmServiceDuty();
		}
		return entity;
	}
	
	@RequiresPermissions("service:ccmServiceDuty:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmServiceDuty ccmServiceDuty, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmServiceDuty> page = ccmServiceDutyService.findPage(new Page<CcmServiceDuty>(request, response), ccmServiceDuty); 
		model.addAttribute("page", page);
		return "ccm/service/ccmServiceDutyList";
	}

	@RequiresPermissions("service:ccmServiceDuty:view")
	@RequestMapping(value = "form")
	public String form(CcmServiceDuty ccmServiceDuty, Model model) {
		model.addAttribute("ccmServiceDuty", ccmServiceDuty);
		return "ccm/service/ccmServiceDutyForm";
	}

	@RequiresPermissions("service:ccmServiceDuty:edit")
	@RequestMapping(value = "save")
	public String save(CcmServiceDuty ccmServiceDuty, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmServiceDuty)){
			return form(ccmServiceDuty, model);
		}
		ccmServiceDutyService.save(ccmServiceDuty);
		addMessage(redirectAttributes, "保存工作职责成功");
		return "redirect:"+Global.getAdminPath()+"/service/ccmServiceDuty/?repage";
	}
	
	@RequiresPermissions("service:ccmServiceDuty:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmServiceDuty ccmServiceDuty, RedirectAttributes redirectAttributes) {
		ccmServiceDutyService.delete(ccmServiceDuty);
		addMessage(redirectAttributes, "删除工作职责成功");
		return "redirect:"+Global.getAdminPath()+"/service/ccmServiceDuty/?repage";
	}

}