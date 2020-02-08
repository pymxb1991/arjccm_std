/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyvoluteer.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.partyteam.entity.CcmPartyVolunteerTeam;
import com.arjjs.ccm.modules.ccm.partyteam.service.CcmPartyVolunteerTeamService;
import com.arjjs.ccm.modules.ccm.partyvoluteer.entity.CcmPartyVolunteer;
import com.arjjs.ccm.modules.ccm.partyvoluteer.service.CcmPartyVolunteerService;
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
 * 志愿者管理Controller
 * @author maoxb
 * @version 2019-08-13
 */
@Controller
@RequestMapping(value = "${adminPath}/partyvoluteer/ccmPartyVolunteer")
public class CcmPartyVolunteerController extends BaseController {

	@Autowired
	private CcmPartyVolunteerService ccmPartyVolunteerService;

	@Autowired
	private CcmPartyVolunteerTeamService ccmPartyVolunteerTeamService;

	@ModelAttribute
	public CcmPartyVolunteer get(@RequestParam(required=false) String id) {
		CcmPartyVolunteer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmPartyVolunteerService.get(id);
		}
		if (entity == null){
			entity = new CcmPartyVolunteer();
		}
		return entity;
	}

	@RequestMapping(value = {"ccmPartyVolunteerIndex"})
	public String ccmPartyVolunteerIndex() {
		return "ccm/partyvoluteer/ccmPartyVolunteerIndex";
	}

	@RequiresPermissions("partyvoluteer:ccmPartyVolunteer:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmPartyVolunteer ccmPartyVolunteer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmPartyVolunteer> page = ccmPartyVolunteerService.findPage(new Page<CcmPartyVolunteer>(request, response), ccmPartyVolunteer); 
		model.addAttribute("page", page);
		return "ccm/partyvoluteer/ccmPartyVolunteerList";
	}

	@RequiresPermissions("partyvoluteer:ccmPartyVolunteer:view")
	@RequestMapping(value = "form")
	public String form(CcmPartyVolunteer ccmPartyVolunteer, Model model) {
		model.addAttribute("ccmPartyVolunteer", ccmPartyVolunteer);

		List<CcmPartyVolunteerTeam> list = ccmPartyVolunteerTeamService.findList(new CcmPartyVolunteerTeam());
		model.addAttribute("list", list);

		return "ccm/partyvoluteer/ccmPartyVolunteerForm";
	}

	@RequiresPermissions("partyvoluteer:ccmPartyVolunteer:edit")
	@RequestMapping(value = "save")
	public void save(CcmPartyVolunteer ccmPartyVolunteer, Model model, RedirectAttributes redirectAttributes,
					 HttpServletRequest request,HttpServletResponse response) throws IOException {
		/*if (!beanValidator(model, ccmPartyVolunteer)){
			return form(ccmPartyVolunteer, model);
		}*/
		ccmPartyVolunteerService.save(ccmPartyVolunteer);
		addMessage(redirectAttributes, "保存志愿者管理成功");
		//return "redirect:"+Global.getAdminPath()+"/partyvoluteer/ccmPartyVolunteer/?repage";
		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>	var index = parent.layer.getFrameIndex(window.name);parent.layer.close(index);</script>");
		out.println("<script language='javascript'>parent.parent.parent.alertInfo('" + "保存成功"
				+ "');</script>");
		out.println("<script language='javascript'>parent.parent.parent.document.getElementById('mainFrame').contentWindow.location.reload(true);</script>");
	}
	
	@RequiresPermissions("partyvoluteer:ccmPartyVolunteer:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPartyVolunteer ccmPartyVolunteer, RedirectAttributes redirectAttributes) {
		ccmPartyVolunteerService.delete(ccmPartyVolunteer);
		addMessage(redirectAttributes, "删除志愿者管理成功");
		return "redirect:"+Global.getAdminPath()+"/partyvoluteer/ccmPartyVolunteer/?repage";
	}

}