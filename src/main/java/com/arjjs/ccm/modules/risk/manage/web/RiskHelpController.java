/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.manage.web;

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
import com.arjjs.ccm.modules.risk.manage.entity.RiskHelp;
import com.arjjs.ccm.modules.risk.manage.service.RiskHelpService;

/**
 * 帮助信息Controller
 * @author liang
 * @version 2018-03-31
 */
@Controller
@RequestMapping(value = "${adminPath}/manage/riskHelp")
public class RiskHelpController extends BaseController {

	@Autowired
	private RiskHelpService riskHelpService;
	
	@ModelAttribute
	public RiskHelp get(@RequestParam(required=false) String id) {
		RiskHelp entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = riskHelpService.get(id);
		}
		if (entity == null){
			entity = new RiskHelp();
		}
		return entity;
	}
	
	@RequiresPermissions("manage:riskHelp:view")
	@RequestMapping(value = {"list", ""})
	public String list(RiskHelp riskHelp, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RiskHelp> page = riskHelpService.findPage(new Page<RiskHelp>(request, response), riskHelp); 
		model.addAttribute("page", page);
		return "risk/manage/riskHelpList";
	}

	@RequiresPermissions("manage:riskHelp:view")
	@RequestMapping(value = "form")
	public String form(RiskHelp riskHelp, Model model) {
		model.addAttribute("riskHelp", riskHelp);
		return "risk/manage/riskHelpForm";
	}

	@RequiresPermissions("manage:riskHelp:edit")
	@RequestMapping(value = "save")
	public String save(RiskHelp riskHelp, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, riskHelp)){
			return form(riskHelp, model);
		}
		riskHelpService.save(riskHelp);
		addMessage(redirectAttributes, "保存帮助信息成功");
		return "redirect:"+Global.getAdminPath()+"/manage/riskHelp/?repage";
	}
	
	@RequiresPermissions("manage:riskHelp:edit")
	@RequestMapping(value = "delete")
	public String delete(RiskHelp riskHelp, RedirectAttributes redirectAttributes) {
		riskHelpService.delete(riskHelp);
		addMessage(redirectAttributes, "删除帮助信息成功");
		return "redirect:"+Global.getAdminPath()+"/manage/riskHelp/?repage";
	}

}