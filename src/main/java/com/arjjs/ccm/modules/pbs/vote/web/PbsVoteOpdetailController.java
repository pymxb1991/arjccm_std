/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.vote.web;

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
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteOpdetail;
import com.arjjs.ccm.modules.pbs.vote.service.PbsVoteOpdetailService;

/**
 * 投票个人选项信息Controller
 * @author lc
 * @version 2018-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/vote/pbsVoteOpdetail")
public class PbsVoteOpdetailController extends BaseController {

	@Autowired
	private PbsVoteOpdetailService pbsVoteOpdetailService;
	
	@ModelAttribute
	public PbsVoteOpdetail get(@RequestParam(required=false) String id) {
		PbsVoteOpdetail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsVoteOpdetailService.get(id);
		}
		if (entity == null){
			entity = new PbsVoteOpdetail();
		}
		return entity;
	}
	
	@RequiresPermissions("vote:pbsVoteOpdetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsVoteOpdetail pbsVoteOpdetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsVoteOpdetail> page = pbsVoteOpdetailService.findPage(new Page<PbsVoteOpdetail>(request, response), pbsVoteOpdetail); 
		model.addAttribute("page", page);
		return "pbs/vote/pbsVoteOpdetailList";
	}

	@RequiresPermissions("vote:pbsVoteOpdetail:view")
	@RequestMapping(value = "form")
	public String form(PbsVoteOpdetail pbsVoteOpdetail, Model model) {
		model.addAttribute("pbsVoteOpdetail", pbsVoteOpdetail);
		return "pbs/vote/pbsVoteOpdetailForm";
	}

	@RequiresPermissions("vote:pbsVoteOpdetail:edit")
	@RequestMapping(value = "save")
	public String save(PbsVoteOpdetail pbsVoteOpdetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsVoteOpdetail)){
			return form(pbsVoteOpdetail, model);
		}
		pbsVoteOpdetailService.save(pbsVoteOpdetail);
		addMessage(redirectAttributes, "保存投票个人选项信息成功");
		return "redirect:"+Global.getAdminPath()+"/vote/pbsVoteOpdetail/?repage";
	}
	
	@RequiresPermissions("vote:pbsVoteOpdetail:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsVoteOpdetail pbsVoteOpdetail, RedirectAttributes redirectAttributes) {
		pbsVoteOpdetailService.delete(pbsVoteOpdetail);
		addMessage(redirectAttributes, "删除投票个人选项信息成功");
		return "redirect:"+Global.getAdminPath()+"/vote/pbsVoteOpdetail/?repage";
	}

}