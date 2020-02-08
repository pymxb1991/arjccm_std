/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.web;

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
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolUser;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolUserService;

/**
 * 巡逻人员Controller
 * @author arj
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/patrol/ccmPatrolUser")
public class CcmPatrolUserController extends BaseController {

	@Autowired
	private CcmPatrolUserService ccmPatrolUserService;
	
	@ModelAttribute
	public CcmPatrolUser get(@RequestParam(required=false) String id) {
		CcmPatrolUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmPatrolUserService.get(id);
		}
		if (entity == null){
			entity = new CcmPatrolUser();
		}
		return entity;
	}
	
	@RequiresPermissions("patrol:ccmPatrolUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmPatrolUser ccmPatrolUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmPatrolUser> page = ccmPatrolUserService.findPage(new Page<CcmPatrolUser>(request, response), ccmPatrolUser); 
		model.addAttribute("page", page);
		return "ccm/patrol/ccmPatrolUserList";
	}

	@RequiresPermissions("patrol:ccmPatrolUser:view")
	@RequestMapping(value = "form")
	public String form(CcmPatrolUser ccmPatrolUser, Model model) {
		model.addAttribute("ccmPatrolUser", ccmPatrolUser);
		return "ccm/patrol/ccmPatrolUserForm";
	}

	@RequiresPermissions("patrol:ccmPatrolUser:edit")
	@RequestMapping(value = "save")
	public String save(CcmPatrolUser ccmPatrolUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmPatrolUser)){
			return form(ccmPatrolUser, model);
		}
		ccmPatrolUserService.save(ccmPatrolUser);
		addMessage(redirectAttributes, "保存巡逻人员成功");
		return "redirect:"+Global.getAdminPath()+"/patrol/ccmPatrolUser/?repage";
	}
	
	@RequiresPermissions("patrol:ccmPatrolUser:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPatrolUser ccmPatrolUser, RedirectAttributes redirectAttributes) {
		ccmPatrolUserService.delete(ccmPatrolUser);
		addMessage(redirectAttributes, "删除巡逻人员成功");
		return "redirect:"+Global.getAdminPath()+"/patrol/ccmPatrolUser/?repage";
	}

}