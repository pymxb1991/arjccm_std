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
import com.arjjs.ccm.modules.pbs.vote.entity.PbsVoteUser;
import com.arjjs.ccm.modules.pbs.vote.service.PbsVoteUserService;

/**
 * 投票主题用户信息Controller
 * @author lc
 * @version 2018-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/vote/pbsVoteUser")
public class PbsVoteUserController extends BaseController {

	@Autowired
	private PbsVoteUserService pbsVoteUserService;
	
	@ModelAttribute
	public PbsVoteUser get(@RequestParam(required=false) String id) {
		PbsVoteUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsVoteUserService.get(id);
		}
		if (entity == null){
			entity = new PbsVoteUser();
		}
		return entity;
	}
	
	@RequiresPermissions("vote:pbsVoteUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsVoteUser pbsVoteUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsVoteUser> page = pbsVoteUserService.findPage(new Page<PbsVoteUser>(request, response), pbsVoteUser); 
		model.addAttribute("page", page);
		return "pbs/vote/pbsVoteUserList";
	}

	@RequiresPermissions("vote:pbsVoteUser:view")
	@RequestMapping(value = "form")
	public String form(PbsVoteUser pbsVoteUser, Model model) {
		model.addAttribute("pbsVoteUser", pbsVoteUser);
		return "pbs/vote/pbsVoteUserForm";
	}

	@RequiresPermissions("vote:pbsVoteUser:edit")
	@RequestMapping(value = "save")
	public String save(PbsVoteUser pbsVoteUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsVoteUser)){
			return form(pbsVoteUser, model);
		}
		pbsVoteUserService.save(pbsVoteUser);
		addMessage(redirectAttributes, "保存投票主题用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/vote/pbsVoteUser/?repage";
	}
	
	@RequiresPermissions("vote:pbsVoteUser:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsVoteUser pbsVoteUser, RedirectAttributes redirectAttributes) {
		pbsVoteUserService.delete(pbsVoteUser);
		addMessage(redirectAttributes, "删除投票主题用户信息成功");
		return "redirect:"+Global.getAdminPath()+"/vote/pbsVoteUser/?repage";
	}

}