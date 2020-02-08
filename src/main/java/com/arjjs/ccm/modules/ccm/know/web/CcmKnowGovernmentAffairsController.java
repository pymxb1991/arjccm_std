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
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowGovernmentAffairs;
import com.arjjs.ccm.modules.ccm.know.service.CcmKnowGovernmentAffairsService;

/**
 * 民政事务Controller
 * @author liang
 * @version 2018-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/know/ccmKnowGovernmentAffairs")
public class CcmKnowGovernmentAffairsController extends BaseController {

	@Autowired
	private CcmKnowGovernmentAffairsService ccmKnowGovernmentAffairsService;
	
	@ModelAttribute
	public CcmKnowGovernmentAffairs get(@RequestParam(required=false) String id) {
		CcmKnowGovernmentAffairs entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmKnowGovernmentAffairsService.get(id);
		}
		if (entity == null){
			entity = new CcmKnowGovernmentAffairs();
		}
		return entity;
	}
	
	@RequiresPermissions("know:ccmKnowGovernmentAffairs:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmKnowGovernmentAffairs ccmKnowGovernmentAffairs, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmKnowGovernmentAffairs> page = ccmKnowGovernmentAffairsService.findPage(new Page<CcmKnowGovernmentAffairs>(request, response), ccmKnowGovernmentAffairs); 
		model.addAttribute("page", page);
		return "ccm/know/ccmKnowGovernmentAffairsList";
	}

	@RequiresPermissions("know:ccmKnowGovernmentAffairs:view")
	@RequestMapping(value = "form")
	public String form(CcmKnowGovernmentAffairs ccmKnowGovernmentAffairs, Model model) {
		model.addAttribute("ccmKnowGovernmentAffairs", ccmKnowGovernmentAffairs);
		return "ccm/know/ccmKnowGovernmentAffairsForm";
	}

	@RequiresPermissions("know:ccmKnowGovernmentAffairs:edit")
	@RequestMapping(value = "save")
	public String save(CcmKnowGovernmentAffairs ccmKnowGovernmentAffairs, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmKnowGovernmentAffairs)){
			return form(ccmKnowGovernmentAffairs, model);
		}
		ccmKnowGovernmentAffairsService.save(ccmKnowGovernmentAffairs);
		addMessage(redirectAttributes, "保存民政事务成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowGovernmentAffairs/?repage";
	}
	
	@RequiresPermissions("know:ccmKnowGovernmentAffairs:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmKnowGovernmentAffairs ccmKnowGovernmentAffairs, RedirectAttributes redirectAttributes) {
		ccmKnowGovernmentAffairsService.delete(ccmKnowGovernmentAffairs);
		addMessage(redirectAttributes, "删除民政事务成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowGovernmentAffairs/?repage";
	}

}