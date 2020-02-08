/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.proposal.web;

import java.util.List;

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
import com.arjjs.ccm.modules.pbs.proposal.entity.PbsProposal;
import com.arjjs.ccm.modules.pbs.proposal.service.PbsProposalService;
import com.arjjs.ccm.modules.pbs.sys.entity.GeneralMethod;
import com.arjjs.ccm.modules.pbs.sys.service.PbsGeneralService;

/**
 * 建议信息Controller
 * 
 * @author lc
 * @version 2018-05-31
 */
@Controller
@RequestMapping(value = "${adminPath}/proposal/pbsProposal")
public class PbsProposalController extends BaseController {

	@Autowired
	private PbsProposalService pbsProposalService;
	@Autowired
	private PbsGeneralService pbsGeneralDao;
	
	private static final String TABLE= "pbs_proposal";
	private static final String TABLEKEY= "s_title";

	@ModelAttribute
	public PbsProposal get(@RequestParam(required = false) String id) {
		PbsProposal entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = pbsProposalService.get(id);
		}
		if (entity == null) {
			entity = new PbsProposal();
		}
		return entity;
	}

	@RequiresPermissions("proposal:pbsProposal:view")
	@RequestMapping(value = { "list", "" })
	public String list(PbsProposal pbsProposal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsProposal> page = pbsProposalService.findPage(new Page<PbsProposal>(request, response), pbsProposal);
		model.addAttribute("page", page);
		return "pbs/proposal/pbsProposalList";
	}

	@RequiresPermissions("proposal:pbsProposal:view")
	@RequestMapping(value = "form")
	public String form(PbsProposal pbsProposal, Model model) {
		model.addAttribute("pbsProposal", pbsProposal);
		return "pbs/proposal/pbsProposalForm";
	}

	@RequiresPermissions("proposal:pbsProposal:edit")
	@RequestMapping(value = "save")
	public String save(PbsProposal pbsProposal, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsProposal)) {
			return form(pbsProposal, model);
		}
		// 判断当前的内容是否存在
		GeneralMethod generalMethod = new GeneralMethod();
		generalMethod.setTabletype(TABLE);
		generalMethod.setId(pbsProposal.getId());
		generalMethod.setColumntype(TABLEKEY);
		generalMethod.setKey(pbsProposal.getSTitle());
		// 验证数据是否重复
		if (pbsGeneralDao.checkExist(generalMethod)) {
			addMessage(model, "数据验证失败：该信息已经存在");
			return form(pbsProposal, model);
		}
		pbsProposalService.save(pbsProposal);
		addMessage(redirectAttributes, "保存建议信息成功");
		return "redirect:" + Global.getAdminPath() + "/proposal/pbsProposal/?repage";
	}

	@RequiresPermissions("proposal:pbsProposal:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsProposal pbsProposal, RedirectAttributes redirectAttributes) {
		pbsProposalService.delete(pbsProposal);
		addMessage(redirectAttributes, "删除建议信息成功");
		return "redirect:" + Global.getAdminPath() + "/proposal/pbsProposal/?repage";
	}

	// 获取 所有的 类型
	@RequestMapping(value = "namelist")
	public String namelist(PbsProposal pbsProposal, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<PbsProposal> list = pbsProposalService.findList(pbsProposal);
		model.addAttribute("list", list);
		return "mapping/PbsProposalList";
	}
}