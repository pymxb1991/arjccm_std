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
import com.arjjs.ccm.modules.risk.manage.entity.RiskSpecialist;
import com.arjjs.ccm.modules.risk.manage.service.RiskSpecialistService;

/**
 * 专家库Controller
 * @author liang
 * @version 2018-03-31
 */
@Controller
@RequestMapping(value = "${adminPath}/manage/riskSpecialist")
public class RiskSpecialistController extends BaseController {

	@Autowired
	private RiskSpecialistService riskSpecialistService;
	
	@ModelAttribute
	public RiskSpecialist get(@RequestParam(required=false) String id) {
		RiskSpecialist entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = riskSpecialistService.get(id);
		}
		if (entity == null){
			entity = new RiskSpecialist();
		}
		return entity;
	}
	
	@RequiresPermissions("manage:riskSpecialist:view")
	@RequestMapping(value = {"list", ""})
	public String list(RiskSpecialist riskSpecialist, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RiskSpecialist> page = riskSpecialistService.findPage(new Page<RiskSpecialist>(request, response), riskSpecialist); 
		model.addAttribute("page", page);
		return "risk/manage/riskSpecialistList";
	}

	@RequiresPermissions("manage:riskSpecialist:view")
	@RequestMapping(value = "form")
	public String form(RiskSpecialist riskSpecialist, Model model) {
		model.addAttribute("riskSpecialist", riskSpecialist);
		return "risk/manage/riskSpecialistForm";
	}

	@RequiresPermissions("manage:riskSpecialist:edit")
	@RequestMapping(value = "save")
	public String save(RiskSpecialist riskSpecialist, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, riskSpecialist)){
			return form(riskSpecialist, model);
		}
		riskSpecialistService.save(riskSpecialist);
		addMessage(redirectAttributes, "保存专家库成功");
		return "redirect:"+Global.getAdminPath()+"/manage/riskSpecialist/?repage";
	}
	
	@RequiresPermissions("manage:riskSpecialist:edit")
	@RequestMapping(value = "delete")
	public String delete(RiskSpecialist riskSpecialist, RedirectAttributes redirectAttributes) {
		riskSpecialistService.delete(riskSpecialist);
		addMessage(redirectAttributes, "删除专家库成功");
		return "redirect:"+Global.getAdminPath()+"/manage/riskSpecialist/?repage";
	}

}