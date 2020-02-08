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
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowentercond;
import com.arjjs.ccm.modules.pbs.flow.service.PbsFlowentercondService;

/**
 * 进入节点条件信息Controller
 * @author lc
 * @version 2018-04-20
 */
@Controller
@RequestMapping(value = "${adminPath}/flow/pbsFlowentercond")
public class PbsFlowentercondController extends BaseController {

	@Autowired
	private PbsFlowentercondService pbsFlowentercondService;
	
	@ModelAttribute
	public PbsFlowentercond get(@RequestParam(required=false) String id) {
		PbsFlowentercond entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsFlowentercondService.get(id);
		}
		if (entity == null){
			entity = new PbsFlowentercond();
		}
		return entity;
	}
	
	@RequiresPermissions("flow:pbsFlowentercond:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsFlowentercond pbsFlowentercond, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsFlowentercond> page = pbsFlowentercondService.findPage(new Page<PbsFlowentercond>(request, response), pbsFlowentercond); 
		model.addAttribute("page", page);
		return "pbs/flow/pbsFlowentercondList";
	}

	@RequiresPermissions("flow:pbsFlowentercond:view")
	@RequestMapping(value = "form")
	public String form(PbsFlowentercond pbsFlowentercond, Model model) {
		model.addAttribute("pbsFlowentercond", pbsFlowentercond);
		return "pbs/flow/pbsFlowentercondForm";
	}

	@RequiresPermissions("flow:pbsFlowentercond:edit")
	@RequestMapping(value = "save")
	public String save(PbsFlowentercond pbsFlowentercond, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsFlowentercond)){
			return form(pbsFlowentercond, model);
		}
		pbsFlowentercondService.save(pbsFlowentercond);
		addMessage(redirectAttributes, "保存进入节点条件信息成功");
		return "redirect:"+Global.getAdminPath()+"/flow/pbsFlowentercond/?repage";
	}
	
	@RequiresPermissions("flow:pbsFlowentercond:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsFlowentercond pbsFlowentercond, RedirectAttributes redirectAttributes) {
		pbsFlowentercondService.delete(pbsFlowentercond);
		addMessage(redirectAttributes, "删除进入节点条件信息成功");
		return "redirect:"+Global.getAdminPath()+"/flow/pbsFlowentercond/?repage";
	}

}