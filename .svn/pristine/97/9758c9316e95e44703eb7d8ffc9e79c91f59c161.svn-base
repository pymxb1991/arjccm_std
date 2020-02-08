/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.pop.web;

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
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPopHR;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPopHRService;

/**
 * 户籍人口表单Controller
 * @author arj
 * @version 2017-12-27
 */
@Controller
@RequestMapping(value = "${adminPath}/pop/ccmPopHR")
public class CcmPopHRController extends BaseController {

	@Autowired
	private CcmPopHRService ccmPopHRService;
	
	@ModelAttribute
	public CcmPopHR get(@RequestParam(required=false) String id) {
		CcmPopHR entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmPopHRService.get(id);
		}
		if (entity == null){
			entity = new CcmPopHR();
		}
		return entity;
	}
	
	@RequiresPermissions("pop:ccmPopHR:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmPopHR ccmPopHR, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmPopHR> page = ccmPopHRService.findPage(new Page<CcmPopHR>(request, response), ccmPopHR); 
		model.addAttribute("page", page);
		return "ccm/pop/ccmPopHRList";
	}

	@RequiresPermissions("pop:ccmPopHR:view")
	@RequestMapping(value = "form")
	public String form(CcmPopHR ccmPopHR, Model model) {
		model.addAttribute("ccmPopHR", ccmPopHR);
		return "ccm/pop/ccmPopHRForm";
	}

	@RequiresPermissions("pop:ccmPopHR:edit")
	@RequestMapping(value = "save")
	public String save(CcmPopHR ccmPopHR, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmPopHR)){
			return form(ccmPopHR, model);
		}
		ccmPopHRService.save(ccmPopHR);
		addMessage(redirectAttributes, "保存户籍人口成功");
		return "redirect:"+Global.getAdminPath()+"/pop/ccmPopHR/?repage";
	}
	
	@RequiresPermissions("pop:ccmPopHR:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPopHR ccmPopHR, RedirectAttributes redirectAttributes) {
		ccmPopHRService.delete(ccmPopHR);
		addMessage(redirectAttributes, "删除户籍人口成功");
		return "redirect:"+Global.getAdminPath()+"/pop/ccmPopHR/?repage";
	}

}