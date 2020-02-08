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
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowKeyActivity;
import com.arjjs.ccm.modules.ccm.know.service.CcmKnowKeyActivityService;

/**
 * 重要活动Controller
 * @author liang
 * @version 2018-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/know/ccmKnowKeyActivity")
public class CcmKnowKeyActivityController extends BaseController {

	@Autowired
	private CcmKnowKeyActivityService ccmKnowKeyActivityService;
	
	@ModelAttribute
	public CcmKnowKeyActivity get(@RequestParam(required=false) String id) {
		CcmKnowKeyActivity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmKnowKeyActivityService.get(id);
		}
		if (entity == null){
			entity = new CcmKnowKeyActivity();
		}
		return entity;
	}
	
	@RequiresPermissions("know:ccmKnowKeyActivity:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmKnowKeyActivity ccmKnowKeyActivity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmKnowKeyActivity> page = ccmKnowKeyActivityService.findPage(new Page<CcmKnowKeyActivity>(request, response), ccmKnowKeyActivity); 
		model.addAttribute("page", page);
		return "ccm/know/ccmKnowKeyActivityList";
	}

	@RequiresPermissions("know:ccmKnowKeyActivity:view")
	@RequestMapping(value = "form")
	public String form(CcmKnowKeyActivity ccmKnowKeyActivity, Model model) {
		model.addAttribute("ccmKnowKeyActivity", ccmKnowKeyActivity);
		return "ccm/know/ccmKnowKeyActivityForm";
	}

	@RequiresPermissions("know:ccmKnowKeyActivity:edit")
	@RequestMapping(value = "save")
	public String save(CcmKnowKeyActivity ccmKnowKeyActivity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmKnowKeyActivity)){
			return form(ccmKnowKeyActivity, model);
		}
		ccmKnowKeyActivityService.save(ccmKnowKeyActivity);
		addMessage(redirectAttributes, "保存重要活动成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowKeyActivity/?repage";
	}
	
	@RequiresPermissions("know:ccmKnowKeyActivity:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmKnowKeyActivity ccmKnowKeyActivity, RedirectAttributes redirectAttributes) {
		ccmKnowKeyActivityService.delete(ccmKnowKeyActivity);
		addMessage(redirectAttributes, "删除重要活动成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowKeyActivity/?repage";
	}

}