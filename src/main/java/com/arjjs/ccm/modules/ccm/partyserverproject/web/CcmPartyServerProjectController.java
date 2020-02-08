/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyserverproject.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.partyserverproject.entity.CcmPartyServerProject;
import com.arjjs.ccm.modules.ccm.partyserverproject.service.CcmPartyServerProjectService;
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

/**
 * 服务项目管理Controller
 * @author cby
 * @version 2019-08-15
 */
@Controller
@RequestMapping(value = "${adminPath}/partyserverproject/ccmPartyServerProject")
public class CcmPartyServerProjectController extends BaseController {

	@Autowired
	private CcmPartyServerProjectService ccmPartyServerProjectService;
	
	@ModelAttribute
	public CcmPartyServerProject get(@RequestParam(required=false) String id) {
		CcmPartyServerProject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmPartyServerProjectService.get(id);
		}
		if (entity == null){
			entity = new CcmPartyServerProject();
		}
		return entity;
	}

	@RequestMapping(value = {"ccmPartyServerProjectindex"})
	public String ccmPartyServerProjectindex() {
		return "ccm/partyserverproject/ccmPartyServerProjectindex";
	}

	@RequiresPermissions("partyserverproject:ccmPartyServerProject:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmPartyServerProject ccmPartyServerProject, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmPartyServerProject> page = ccmPartyServerProjectService.findPage(new Page<CcmPartyServerProject>(request, response), ccmPartyServerProject); 
		model.addAttribute("page", page);
		return "ccm/partyserverproject/ccmPartyServerProjectList";
	}

	@RequiresPermissions("partyserverproject:ccmPartyServerProject:view")
	@RequestMapping(value = "form")
	public String form(CcmPartyServerProject ccmPartyServerProject, Model model) {
		model.addAttribute("ccmPartyServerProject", ccmPartyServerProject);
		return "ccm/partyserverproject/ccmPartyServerProjectForm";
	}

	@RequiresPermissions("partyserverproject:ccmPartyServerProject:edit")
	@RequestMapping(value = "save")
	public void save(CcmPartyServerProject ccmPartyServerProject, Model model, RedirectAttributes redirectAttributes,
					   HttpServletRequest request,HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		ccmPartyServerProjectService.save(ccmPartyServerProject);
		addMessage(redirectAttributes, "保存服务项目管理成功");
		//return "redirect:"+Global.getAdminPath()+"/partyserverproject/ccmPartyServerProject/?repage";
		out.println("<script language='javascript'>	var index = parent.layer.getFrameIndex(window.name);parent.layer.close(index);</script>");
		out.println("<script language='javascript'>parent.parent.parent.alertInfo('" + "保存成功"
				+ "');</script>");
		out.println("<script language='javascript'>parent.parent.parent.document.getElementById('mainFrame').contentWindow.location.reload(true);</script>");
	}
	
	@RequiresPermissions("partyserverproject:ccmPartyServerProject:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPartyServerProject ccmPartyServerProject, RedirectAttributes redirectAttributes) {
		ccmPartyServerProjectService.delete(ccmPartyServerProject);
		addMessage(redirectAttributes, "删除服务项目管理成功");
		return "redirect:"+Global.getAdminPath()+"/partyserverproject/ccmPartyServerProject/?repage";
	}

}