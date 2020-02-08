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
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgComPop;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgComPopService;

/**
 * 公共机构人员Controller
 * @author liang
 * @version 2018-05-11
 */
@Controller
@RequestMapping(value = "${adminPath}/org/ccmOrgComPop")
public class CcmOrgComPopController extends BaseController {

	@Autowired
	private CcmOrgComPopService ccmOrgComPopService;
	
	@ModelAttribute
	public CcmOrgComPop get(@RequestParam(required=false) String id) {
		CcmOrgComPop entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmOrgComPopService.get(id);
		}
		if (entity == null){
			entity = new CcmOrgComPop();
		}
		return entity;
	}
	
	@RequiresPermissions("org:ccmOrgComPop:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmOrgComPop ccmOrgComPop, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmOrgComPop> page = ccmOrgComPopService.findPage(new Page<CcmOrgComPop>(request, response), ccmOrgComPop); 
		model.addAttribute("page", page);
		return "ccm/org/ccmOrgComPopList";
	}

	@RequiresPermissions("org:ccmOrgComPop:view")
	@RequestMapping(value = "form")
	public String form(CcmOrgComPop ccmOrgComPop, Model model) {
		model.addAttribute("ccmOrgComPop", ccmOrgComPop);
		return "ccm/org/ccmOrgComPopForm";
	}

	@RequiresPermissions("org:ccmOrgComPop:edit")
	@RequestMapping(value = "save")
	public String save(CcmOrgComPop ccmOrgComPop, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmOrgComPop)){
			return form(ccmOrgComPop, model);
		}
		ccmOrgComPopService.save(ccmOrgComPop);
		addMessage(redirectAttributes, "保存公共机构人员成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgComPop/?repage";
	}
	
	@RequiresPermissions("org:ccmOrgComPop:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmOrgComPop ccmOrgComPop, RedirectAttributes redirectAttributes) {
		ccmOrgComPopService.delete(ccmOrgComPop);
		addMessage(redirectAttributes, "删除公共机构人员成功");
		return "redirect:"+Global.getAdminPath()+"/org/ccmOrgComPop/?repage";
	}

}