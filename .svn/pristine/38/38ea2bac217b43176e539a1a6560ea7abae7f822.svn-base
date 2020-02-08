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
import com.arjjs.ccm.modules.risk.manage.entity.RiskAssessFlow;
import com.arjjs.ccm.modules.risk.manage.service.RiskAssessFlowService;

/**
 * 评估流程管理Controller
 * @author liang
 * @version 2018-04-04
 */
@Controller
@RequestMapping(value = "${adminPath}/manage/riskAssessFlow")
public class RiskAssessFlowController extends BaseController {

	@Autowired
	private RiskAssessFlowService riskAssessFlowService;
	
	@ModelAttribute
	public RiskAssessFlow get(@RequestParam(required=false) String id) {
		RiskAssessFlow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = riskAssessFlowService.get(id);
		}
		if (entity == null){
			entity = new RiskAssessFlow();
		}
		return entity;
	}
	
	@RequiresPermissions("manage:riskAssessFlow:view")
	@RequestMapping(value = {"list", ""})
	public String list(RiskAssessFlow riskAssessFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RiskAssessFlow> page = riskAssessFlowService.findPage(new Page<RiskAssessFlow>(request, response), riskAssessFlow); 
		model.addAttribute("page", page);
		return "risk/manage/riskAssessFlowList";
	}

	@RequiresPermissions("manage:riskAssessFlow:view")
	@RequestMapping(value = "form")
	public String form(RiskAssessFlow riskAssessFlow, Model model) {
		model.addAttribute("riskAssessFlow", riskAssessFlow);
		return "risk/manage/riskAssessFlowForm";
	}

	@RequiresPermissions("manage:riskAssessFlow:edit")
	@RequestMapping(value = "save")
	public String save(RiskAssessFlow riskAssessFlow, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, riskAssessFlow)){
			return form(riskAssessFlow, model);
		}
		riskAssessFlowService.save(riskAssessFlow);
		addMessage(redirectAttributes, "保存评估流程管理成功");
		return "redirect:"+Global.getAdminPath()+"/manage/riskAssessFlow/?repage";
	}
	
	@RequiresPermissions("manage:riskAssessFlow:edit")
	@RequestMapping(value = "delete")
	public String delete(RiskAssessFlow riskAssessFlow, RedirectAttributes redirectAttributes) {
		riskAssessFlowService.delete(riskAssessFlow);
		addMessage(redirectAttributes, "删除评估流程管理成功");
		return "redirect:"+Global.getAdminPath()+"/manage/riskAssessFlow/?repage";
	}

}