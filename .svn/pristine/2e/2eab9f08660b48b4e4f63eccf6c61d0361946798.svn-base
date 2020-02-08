/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.proposal.web;

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
import com.arjjs.ccm.modules.pbs.proposal.entity.PbsProposalopt;
import com.arjjs.ccm.modules.pbs.proposal.service.PbsProposaloptService;

/**
 * 建议操作信息Controller
 * 
 * @author lc
 * @version 2018-05-31
 */
@Controller
@RequestMapping(value = "${adminPath}/proposal/pbsProposalopt")
public class PbsProposaloptController extends BaseController {

	@Autowired
	private PbsProposaloptService pbsProposaloptService;
	
	@ModelAttribute
	public PbsProposalopt get(@RequestParam(required = false) String id) {
		PbsProposalopt entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsProposaloptService.get(id);
		}
		if (entity == null) {
			entity = new PbsProposalopt();
		}
		return entity;
	}

	@RequiresPermissions("proposal:pbsProposalopt:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsProposalopt pbsProposalopt, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PbsProposalopt> page = pbsProposaloptService.findPage(new Page<PbsProposalopt>(request, response),
				pbsProposalopt);
		model.addAttribute("page", page);
		return "pbs/proposal/pbsProposaloptList";
	}

	@RequiresPermissions("proposal:pbsProposalopt:view")
	@RequestMapping(value = "form")
	public String form(PbsProposalopt pbsProposalopt, Model model) {
		model.addAttribute("pbsProposalopt", pbsProposalopt);
		return "pbs/proposal/pbsProposaloptForm";
	}

	@RequiresPermissions("proposal:pbsProposalopt:edit")
	@RequestMapping(value = "save")
	public String save(PbsProposalopt pbsProposalopt, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsProposalopt)) {
			return form(pbsProposalopt, model);
		}
		// 判断当前的内容是否存在
		pbsProposaloptService.save(pbsProposalopt);
		addMessage(redirectAttributes, "保存建议操作信息成功");
		return "redirect:" + Global.getAdminPath() + "/proposal/pbsProposalopt/?repage";
	}

	@RequiresPermissions("proposal:pbsProposalopt:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsProposalopt pbsProposalopt, RedirectAttributes redirectAttributes) {
		pbsProposaloptService.delete(pbsProposalopt);
		addMessage(redirectAttributes, "删除建议操作信息成功");
		return "redirect:" + Global.getAdminPath() + "/proposal/pbsProposalopt/?repage";
	}

}