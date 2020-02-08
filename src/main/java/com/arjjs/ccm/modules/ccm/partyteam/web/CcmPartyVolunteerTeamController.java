/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyteam.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.partyteam.entity.CcmPartyVolunteerTeam;
import com.arjjs.ccm.modules.ccm.partyteam.service.CcmPartyVolunteerTeamService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 队伍管理Controller
 * @author maoxb
 * @version 2019-08-13
 */
@Controller
@RequestMapping(value = "${adminPath}/partyteam/ccmPartyVolunteerTeam")
public class CcmPartyVolunteerTeamController extends BaseController {

	@Autowired
	private CcmPartyVolunteerTeamService ccmPartyVolunteerTeamService;
	
	@ModelAttribute
	public CcmPartyVolunteerTeam get(@RequestParam(required=false) String id) {
		CcmPartyVolunteerTeam entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmPartyVolunteerTeamService.get(id);
		}
		if (entity == null){
			entity = new CcmPartyVolunteerTeam();
		}
		return entity;
	}

	@RequestMapping(value = {"ccmPartyVolunteerTeamIndex"})
	public String ccmPartyVolunteerTeamIndex() {
		return "ccm/partyteam/ccmPartyVolunteerTeamIndex";
	}

	@RequiresPermissions("partyteam:ccmPartyVolunteerTeam:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmPartyVolunteerTeam ccmPartyVolunteerTeam, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmPartyVolunteerTeam> page = ccmPartyVolunteerTeamService.findPage(new Page<CcmPartyVolunteerTeam>(request, response), ccmPartyVolunteerTeam); 
		model.addAttribute("page", page);
		return "ccm/partyteam/ccmPartyVolunteerTeamList";
	}

	@RequiresPermissions("partyteam:ccmPartyVolunteerTeam:view")
	@RequestMapping(value = "form")
	public String form(CcmPartyVolunteerTeam ccmPartyVolunteerTeam, Model model) {
		model.addAttribute("ccmPartyVolunteerTeam", ccmPartyVolunteerTeam);

		List<CcmPartyVolunteerTeam> list = ccmPartyVolunteerTeamService.findList(new CcmPartyVolunteerTeam());
		model.addAttribute("list", list);

		return "ccm/partyteam/ccmPartyVolunteerTeamForm";
	}

	@RequiresPermissions("partyteam:ccmPartyVolunteerTeam:edit")
	@RequestMapping(value = "save")
	public void save(CcmPartyVolunteerTeam ccmPartyVolunteerTeam, Model model, RedirectAttributes redirectAttributes,
					   HttpServletRequest request,HttpServletResponse response) throws IOException {
//		if (!beanValidator(model, ccmPartyVolunteerTeam)){
//			return form(ccmPartyVolunteerTeam, model);
//		}
		ccmPartyVolunteerTeamService.save(ccmPartyVolunteerTeam);
		addMessage(redirectAttributes, "保存队伍管理成功");
		//return "redirect:"+Global.getAdminPath()+"/partyteam/ccmPartyVolunteerTeam/?repage";

		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>	var index = parent.layer.getFrameIndex(window.name);parent.layer.close(index);</script>");
		out.println("<script language='javascript'>parent.parent.parent.alertInfo('" + "保存成功"
				+ "');</script>");
		out.println("<script language='javascript'>parent.parent.parent.document.getElementById('mainFrame').contentWindow.location.reload(true);</script>");

	}
	
	@RequiresPermissions("partyteam:ccmPartyVolunteerTeam:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPartyVolunteerTeam ccmPartyVolunteerTeam, RedirectAttributes redirectAttributes) {
		ccmPartyVolunteerTeamService.delete(ccmPartyVolunteerTeam);
		addMessage(redirectAttributes, "删除队伍管理成功");
		return "redirect:"+Global.getAdminPath()+"/partyteam/ccmPartyVolunteerTeam/?repage";
	}

}