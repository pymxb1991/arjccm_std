/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.organization.web;

import java.io.IOException;
import java.io.PrintWriter;

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
import com.arjjs.ccm.modules.ccm.organization.entity.CcmEventOrganization;
import com.arjjs.ccm.modules.ccm.organization.service.CcmEventOrganizationService;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 调解组织管理Controller
 * @author chengdezheng
 * @version 2019-08-13
 */
@Controller
@RequestMapping(value = "${adminPath}/organization/ccmEventOrganization")
public class CcmEventOrganizationController extends BaseController {

	@Autowired
	private CcmEventOrganizationService ccmEventOrganizationService;
	
	@ModelAttribute
	public CcmEventOrganization get(@RequestParam(required=false) String id) {
		CcmEventOrganization entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmEventOrganizationService.get(id);
		}
		if (entity == null){
			entity = new CcmEventOrganization();
		}
		return entity;
	}
	
	@RequiresPermissions("organization:ccmEventOrganization:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmEventOrganization ccmEventOrganization, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmEventOrganization> page = ccmEventOrganizationService.findPage(new Page<CcmEventOrganization>(request, response), ccmEventOrganization); 
		model.addAttribute("page", page);
		return "ccm/organization/ccmEventOrganizationList";
	}

	@RequiresPermissions("organization:ccmEventOrganization:view")
	@RequestMapping(value = "index")
	public String index(CcmEventOrganization ccmEventOrganization, Model model) {
		return "ccm/organization/ccmEventOrganizationIndex";
	}
	
	@RequiresPermissions("organization:ccmEventOrganization:view")
	@RequestMapping(value = "form")
	public String form(CcmEventOrganization ccmEventOrganization, Model model) {
		model.addAttribute("ccmEventOrganization", ccmEventOrganization);
		return "ccm/organization/ccmEventOrganizationForm";
	}

	@RequiresPermissions("organization:ccmEventOrganization:edit")
	@RequestMapping(value = "save")
	public void save(CcmEventOrganization ccmEventOrganization, Model model, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		if (!beanValidator(model, ccmEventOrganization)){
			//return form(ccmEventOrganization, model);
		}
		ccmEventOrganizationService.save(ccmEventOrganization);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommUtil.openWinExpDiv(out, "保存调解组织成功");
	}
	
	@RequiresPermissions("organization:ccmEventOrganization:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmEventOrganization ccmEventOrganization, RedirectAttributes redirectAttributes) {
		ccmEventOrganizationService.delete(ccmEventOrganization);
		addMessage(redirectAttributes, "删除调解组织成功");
		return "redirect:"+Global.getAdminPath()+"/organization/ccmEventOrganization/?repage";
	}

}