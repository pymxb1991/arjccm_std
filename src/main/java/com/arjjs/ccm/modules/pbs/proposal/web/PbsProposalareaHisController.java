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
import com.arjjs.ccm.modules.pbs.proposal.entity.PbsProposalareaHis;
import com.arjjs.ccm.modules.pbs.proposal.service.PbsProposalareaHisService;

/**
 * 建议分区历史信息Controller
 * @author lc
 * @version 2018-05-31
 */
@Controller
@RequestMapping(value = "${adminPath}/proposal/pbsProposalareaHis")
public class PbsProposalareaHisController extends BaseController {

	@Autowired
	private PbsProposalareaHisService pbsProposalareaHisService;
	
	@ModelAttribute
	public PbsProposalareaHis get(@RequestParam(required=false) String id) {
		PbsProposalareaHis entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsProposalareaHisService.get(id);
		}
		if (entity == null){
			entity = new PbsProposalareaHis();
		}
		return entity;
	}
	
	@RequiresPermissions("proposal:pbsProposalareaHis:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsProposalareaHis pbsProposalareaHis, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsProposalareaHis> page = pbsProposalareaHisService.findPage(new Page<PbsProposalareaHis>(request, response), pbsProposalareaHis); 
		model.addAttribute("page", page);
		return "pbs/proposal/pbsProposalareaHisList";
	}

	@RequiresPermissions("proposal:pbsProposalareaHis:view")
	@RequestMapping(value = "form")
	public String form(PbsProposalareaHis pbsProposalareaHis, Model model) {
		model.addAttribute("pbsProposalareaHis", pbsProposalareaHis);
		return "pbs/proposal/pbsProposalareaHisForm";
	}

	@RequiresPermissions("proposal:pbsProposalareaHis:edit")
	@RequestMapping(value = "save")
	public String save(PbsProposalareaHis pbsProposalareaHis, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsProposalareaHis)){
			return form(pbsProposalareaHis, model);
		}
		pbsProposalareaHisService.save(pbsProposalareaHis);
		addMessage(redirectAttributes, "保存建议分区历史信息成功");
		return "redirect:"+Global.getAdminPath()+"/proposal/pbsProposalareaHis/?repage";
	}
	
	@RequiresPermissions("proposal:pbsProposalareaHis:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsProposalareaHis pbsProposalareaHis, RedirectAttributes redirectAttributes) {
		pbsProposalareaHisService.delete(pbsProposalareaHis);
		addMessage(redirectAttributes, "删除建议分区历史信息成功");
		return "redirect:"+Global.getAdminPath()+"/proposal/pbsProposalareaHis/?repage";
	}

}