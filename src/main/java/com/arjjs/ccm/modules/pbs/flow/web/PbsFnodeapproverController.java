/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.web;

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
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFnodeapprover;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFnodeapproverService;

/**
 * 流程审核人信息Controller
 * @author lc
 * @version 2018-04-20
 */
@Controller
@RequestMapping(value = "${adminPath}/flow/pbsFnodeapprover")
public class PbsFnodeapproverController extends BaseController {

	@Autowired
	private PbsFnodeapproverService pbsFnodeapproverService;
	
	@ModelAttribute
	public PbsFnodeapprover get(@RequestParam(required=false) String id) {
		PbsFnodeapprover entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsFnodeapproverService.get(id);
		}
		if (entity == null){
			entity = new PbsFnodeapprover();
		}
		return entity;
	}
	
	@RequiresPermissions("flow:pbsFnodeapprover:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsFnodeapprover pbsFnodeapprover, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsFnodeapprover> page = pbsFnodeapproverService.findPage(new Page<PbsFnodeapprover>(request, response), pbsFnodeapprover); 
		model.addAttribute("page", page);
		return "pbs/flow/pbsFnodeapproverList";
	}

	@RequiresPermissions("flow:pbsFnodeapprover:view")
	@RequestMapping(value = "form")
	public String form(PbsFnodeapprover pbsFnodeapprover, Model model) {
		model.addAttribute("pbsFnodeapprover", pbsFnodeapprover);
		return "pbs/flow/pbsFnodeapproverForm";
	}

	@RequiresPermissions("flow:pbsFnodeapprover:edit")
	@RequestMapping(value = "save")
	public String save(PbsFnodeapprover pbsFnodeapprover, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsFnodeapprover)){
			return form(pbsFnodeapprover, model);
		}
		pbsFnodeapproverService.save(pbsFnodeapprover);
		addMessage(redirectAttributes, "保存流程审核人信息成功");
		return "redirect:"+Global.getAdminPath()+"/flow/pbsFnodeapprover/?repage";
	}
	
	@RequiresPermissions("flow:pbsFnodeapprover:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsFnodeapprover pbsFnodeapprover, RedirectAttributes redirectAttributes) {
		pbsFnodeapproverService.delete(pbsFnodeapprover);
		addMessage(redirectAttributes, "删除流程审核人信息成功");
		return "redirect:"+Global.getAdminPath()+"/flow/pbsFnodeapprover/?repage";
	}

}