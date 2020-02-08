/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.ccmsys.web;

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
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmDomain;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmDomainService;

/**
 * 下级域服务器管理Controller
 * @author pengjianqiang
 * @version 2018-05-09
 */
@Controller
@RequestMapping(value = "${adminPath}/ccmsys/ccmDomain")
public class CcmDomainController extends BaseController {

	@Autowired
	private CcmDomainService ccmDomainService;
	
	@ModelAttribute
	public CcmDomain get(@RequestParam(required=false) String id) {
		CcmDomain entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmDomainService.get(id);
		}
		if (entity == null){
			entity = new CcmDomain();
		}
		return entity;
	}
	
	@RequiresPermissions("ccmsys:ccmDomain:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmDomain ccmDomain, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmDomain> page = ccmDomainService.findPage(new Page<CcmDomain>(request, response), ccmDomain); 
		model.addAttribute("page", page);
		return "ccm/ccmsys/ccmDomainList";
	}

	@RequiresPermissions("ccmsys:ccmDomain:view")
	@RequestMapping(value = "form")
	public String form(CcmDomain ccmDomain, Model model) {
		model.addAttribute("ccmDomain", ccmDomain);
		return "ccm/ccmsys/ccmDomainForm";
	}

	@RequiresPermissions("ccmsys:ccmDomain:edit")
	@RequestMapping(value = "save")
	public String save(CcmDomain ccmDomain, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmDomain)){
			return form(ccmDomain, model);
		}
		ccmDomainService.save(ccmDomain);
		addMessage(redirectAttributes, "保存下级域服务器成功");
		return "redirect:"+Global.getAdminPath()+"/ccmsys/ccmDomain/?repage";
	}
	
	@RequiresPermissions("ccmsys:ccmDomain:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmDomain ccmDomain, RedirectAttributes redirectAttributes) {
		ccmDomainService.delete(ccmDomain);
		addMessage(redirectAttributes, "删除下级域服务器成功");
		return "redirect:"+Global.getAdminPath()+"/ccmsys/ccmDomain/?repage";
	}
	
	@RequiresPermissions("ccmsys:ccmDomain:edit")
	@RequestMapping(value = "updateDomain")
	public String updateDomain(CcmDomain ccmDomain, RedirectAttributes redirectAttributes) {
		
		boolean isSuc = ccmDomainService.updateDomain(ccmDomain);
		if (isSuc) {
			addMessage(redirectAttributes, "获取同步下级域服务器数据成功");
		} else {
			addMessage(redirectAttributes, "获取同步下级域服务器数据失败！");
		}
		return "redirect:"+Global.getAdminPath()+"/ccmsys/ccmDomain/?repage";
	}


}