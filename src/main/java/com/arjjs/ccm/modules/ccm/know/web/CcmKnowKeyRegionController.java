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
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowKeyRegion;
import com.arjjs.ccm.modules.ccm.know.service.CcmKnowKeyRegionService;

/**
 * 重点地区标准Controller
 * @author liang
 * @version 2018-03-22
 */
@Controller
@RequestMapping(value = "${adminPath}/know/ccmKnowKeyRegion")
public class CcmKnowKeyRegionController extends BaseController {

	@Autowired
	private CcmKnowKeyRegionService ccmKnowKeyRegionService;
	
	@ModelAttribute
	public CcmKnowKeyRegion get(@RequestParam(required=false) String id) {
		CcmKnowKeyRegion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmKnowKeyRegionService.get(id);
		}
		if (entity == null){
			entity = new CcmKnowKeyRegion();
		}
		return entity;
	}
	
	@RequiresPermissions("know:ccmKnowKeyRegion:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmKnowKeyRegion ccmKnowKeyRegion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmKnowKeyRegion> page = ccmKnowKeyRegionService.findPage(new Page<CcmKnowKeyRegion>(request, response), ccmKnowKeyRegion); 
		model.addAttribute("page", page);
		return "ccm/know/ccmKnowKeyRegionList";
	}

	@RequiresPermissions("know:ccmKnowKeyRegion:view")
	@RequestMapping(value = "form")
	public String form(CcmKnowKeyRegion ccmKnowKeyRegion, Model model) {
		model.addAttribute("ccmKnowKeyRegion", ccmKnowKeyRegion);
		return "ccm/know/ccmKnowKeyRegionForm";
	}

	@RequiresPermissions("know:ccmKnowKeyRegion:edit")
	@RequestMapping(value = "save")
	public String save(CcmKnowKeyRegion ccmKnowKeyRegion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmKnowKeyRegion)){
			return form(ccmKnowKeyRegion, model);
		}
		ccmKnowKeyRegionService.save(ccmKnowKeyRegion);
		addMessage(redirectAttributes, "保存重点地区标准成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowKeyRegion/?repage";
	}
	
	@RequiresPermissions("know:ccmKnowKeyRegion:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmKnowKeyRegion ccmKnowKeyRegion, RedirectAttributes redirectAttributes) {
		ccmKnowKeyRegionService.delete(ccmKnowKeyRegion);
		addMessage(redirectAttributes, "删除重点地区标准成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowKeyRegion/?repage";
	}

}