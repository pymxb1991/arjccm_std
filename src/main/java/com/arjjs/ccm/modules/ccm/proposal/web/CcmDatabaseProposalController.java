/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.proposal.web;

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
import com.arjjs.ccm.modules.ccm.proposal.entity.CcmDatabaseProposal;
import com.arjjs.ccm.modules.ccm.proposal.service.CcmDatabaseProposalService;

/**
 * 公告建议管理Controller
 * @author cdz
 * @version 2019-09-16
 */
@Controller
@RequestMapping(value = "${adminPath}/proposal/ccmDatabaseProposal")
public class CcmDatabaseProposalController extends BaseController {

	@Autowired
	private CcmDatabaseProposalService ccmDatabaseProposalService;
	
	@ModelAttribute
	public CcmDatabaseProposal get(@RequestParam(required=false) String id) {
		CcmDatabaseProposal entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmDatabaseProposalService.get(id);
		}
		if (entity == null){
			entity = new CcmDatabaseProposal();
		}
		return entity;
	}
	
	@RequiresPermissions("proposal:ccmDatabaseProposal:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmDatabaseProposal ccmDatabaseProposal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmDatabaseProposal> page = ccmDatabaseProposalService.findPage(new Page<CcmDatabaseProposal>(request, response), ccmDatabaseProposal); 
		model.addAttribute("page", page);
		return "ccm/proposal/ccmDatabaseProposalList";
	}

	@RequiresPermissions("proposal:ccmDatabaseProposal:view")
	@RequestMapping(value = "form")
	public String form(CcmDatabaseProposal ccmDatabaseProposal, Model model) {
		model.addAttribute("ccmDatabaseProposal", ccmDatabaseProposal);
		return "ccm/proposal/ccmDatabaseProposalForm";
	}

	@RequiresPermissions("proposal:ccmDatabaseProposal:edit")
	@RequestMapping(value = "save")
	public String save(CcmDatabaseProposal ccmDatabaseProposal, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmDatabaseProposal)){
			return form(ccmDatabaseProposal, model);
		}
		ccmDatabaseProposalService.save(ccmDatabaseProposal);
		addMessage(redirectAttributes, "保存公告建议管理成功");
		return "redirect:"+Global.getAdminPath()+"/proposal/ccmDatabaseProposal/?repage";
	}
	
	@RequiresPermissions("proposal:ccmDatabaseProposal:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmDatabaseProposal ccmDatabaseProposal, RedirectAttributes redirectAttributes) {
		ccmDatabaseProposalService.delete(ccmDatabaseProposal);
		addMessage(redirectAttributes, "删除公告建议管理成功");
		return "redirect:"+Global.getAdminPath()+"/proposal/ccmDatabaseProposal/?repage";
	}

}