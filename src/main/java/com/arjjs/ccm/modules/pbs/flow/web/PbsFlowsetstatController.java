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
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowsetstat;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlowsetstatService;

/**
 * 节点操作后修改Controller
 * @author lc
 * @version 2018-04-20
 */
@Controller
@RequestMapping(value = "${adminPath}/flow/pbsFlowsetstat")
public class PbsFlowsetstatController extends BaseController {

	@Autowired
	private PbsFlowsetstatService pbsFlowsetstatService;
	
	@ModelAttribute
	public PbsFlowsetstat get(@RequestParam(required=false) String id) {
		PbsFlowsetstat entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsFlowsetstatService.get(id);
		}
		if (entity == null){
			entity = new PbsFlowsetstat();
		}
		return entity;
	}
	
	@RequiresPermissions("flow:pbsFlowsetstat:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsFlowsetstat pbsFlowsetstat, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsFlowsetstat> page = pbsFlowsetstatService.findPage(new Page<PbsFlowsetstat>(request, response), pbsFlowsetstat); 
		model.addAttribute("page", page);
		return "pbs/flow/pbsFlowsetstatList";
	}

	@RequiresPermissions("flow:pbsFlowsetstat:view")
	@RequestMapping(value = "form")
	public String form(PbsFlowsetstat pbsFlowsetstat, Model model) {
		model.addAttribute("pbsFlowsetstat", pbsFlowsetstat);
		return "pbs/flow/pbsFlowsetstatForm";
	}

	@RequiresPermissions("flow:pbsFlowsetstat:edit")
	@RequestMapping(value = "save")
	public String save(PbsFlowsetstat pbsFlowsetstat, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsFlowsetstat)){
			return form(pbsFlowsetstat, model);
		}
		pbsFlowsetstatService.save(pbsFlowsetstat);
		addMessage(redirectAttributes, "保存节点操作后修改成功");
		return "redirect:"+Global.getAdminPath()+"/flow/pbsFlowsetstat/?repage";
	}
	
	@RequiresPermissions("flow:pbsFlowsetstat:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsFlowsetstat pbsFlowsetstat, RedirectAttributes redirectAttributes) {
		pbsFlowsetstatService.delete(pbsFlowsetstat);
		addMessage(redirectAttributes, "删除节点操作后修改成功");
		return "redirect:"+Global.getAdminPath()+"/flow/pbsFlowsetstat/?repage";
	}

}