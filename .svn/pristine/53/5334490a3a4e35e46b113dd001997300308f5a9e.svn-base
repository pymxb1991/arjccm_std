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
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgSyncentre;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgSyncentreService;

/**
 * 综治中心Controller
 * @author fu
 * @version 2018-01-18
 */
@Controller
@RequestMapping(value = "${adminPath}/org/ccmOrgSyncentre")
public class CcmOrgSyncentreController extends BaseController {

	@Autowired
	private CcmOrgSyncentreService ccmOrgSyncentreService;
	
	@ModelAttribute
	public CcmOrgSyncentre get(@RequestParam(required=false) String id) {
		CcmOrgSyncentre entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmOrgSyncentreService.get(id);
		}
		if (entity == null){
			entity = new CcmOrgSyncentre();
		}
		return entity;
	}
	
	@RequiresPermissions("org:ccmOrgSyncentre:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmOrgSyncentre ccmOrgSyncentre, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmOrgSyncentre> page = ccmOrgSyncentreService.findPage(new Page<CcmOrgSyncentre>(request, response), ccmOrgSyncentre); 
		model.addAttribute("page", page);
		return "ccm/org/ccmOrgSyncentreList";
	}

	@RequiresPermissions("org:ccmOrgSyncentre:view")
	@RequestMapping(value = "form")
	public String form(CcmOrgSyncentre ccmOrgSyncentre, Model model) {
		model.addAttribute("ccmOrgSyncentre", ccmOrgSyncentre);
		return "ccm/org/ccmOrgSyncentreForm";
	}

	@RequiresPermissions("org:ccmOrgSyncentre:edit")
	@RequestMapping(value = "save")
	public String save(CcmOrgSyncentre ccmOrgSyncentre, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmOrgSyncentre)){
			return form(ccmOrgSyncentre, model);
		}
		ccmOrgSyncentreService.save(ccmOrgSyncentre);
		addMessage(redirectAttributes, "保存综治中心成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgSyncentre/?repage";
	}
	
	@RequiresPermissions("org:ccmOrgSyncentre:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmOrgSyncentre ccmOrgSyncentre, RedirectAttributes redirectAttributes) {
		ccmOrgSyncentreService.delete(ccmOrgSyncentre);
		addMessage(redirectAttributes, "删除综治中心成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgSyncentre/?repage";
	}

}