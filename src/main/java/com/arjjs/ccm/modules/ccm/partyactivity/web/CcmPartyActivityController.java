/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyactivity.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.partyactivity.entity.CcmPartyActivity;
import com.arjjs.ccm.modules.ccm.partyactivity.service.CcmPartyActivityService;
import com.arjjs.ccm.modules.ccm.partybuild.entity.CcmPartyOrganiz;
import com.arjjs.ccm.modules.ccm.partybuild.service.CcmPartyOrganizService;
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
 * 党员活动管理Controller
 * @author maoxb
 * @version 2019-08-13
 */
@Controller
@RequestMapping(value = "${adminPath}/partyactivity/ccmPartyActivity")
public class CcmPartyActivityController extends BaseController {

	@Autowired
	private CcmPartyActivityService ccmPartyActivityService;

	@Autowired
	private CcmPartyOrganizService ccmPartyOrganizService;
	
	@ModelAttribute
	public CcmPartyActivity get(@RequestParam(required=false) String id) {
		CcmPartyActivity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmPartyActivityService.get(id);
		}
		if (entity == null){
			entity = new CcmPartyActivity();
		}
		return entity;
	}

	@RequestMapping(value = {"ccmPartyDoubleActivityIndex"})
	public String ccmPartyDoubleActivityIndex() {
		return "ccm/partyactivity/ccmPartyDoubleActivityIndex";
	}

	@RequestMapping(value = {"ccmPartyActivityIndex"})
	public String ccmPartyActivityIndex() {
		return "ccm/partyactivity/ccmPartyActivityIndex";
	}

	@RequiresPermissions("partyactivity:ccmPartyActivity:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmPartyActivity ccmPartyActivity, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmPartyActivity> page = ccmPartyActivityService.findPage(new Page<CcmPartyActivity>(request, response), ccmPartyActivity); 
		model.addAttribute("page", page);
		CcmPartyOrganiz ccmPartyOrganiz = new CcmPartyOrganiz();
		ccmPartyOrganiz.setType(ccmPartyActivity.getType());
		List<CcmPartyOrganiz> list = ccmPartyOrganizService.findList(ccmPartyOrganiz);
		model.addAttribute("list", list);

		//return "ccm/partyactivity/ccmPartyActivityList";
		String type = ccmPartyActivity.getType();
		return  "1".equals(type) ?  "ccm/partyactivity/ccmPartyActivityList" : "ccm/partyactivity/ccmPartyDoubleActivityList";
	}

	@RequiresPermissions("partyactivity:ccmPartyActivity:view")
	@RequestMapping(value = "form")
	public String form(CcmPartyActivity ccmPartyActivity, Model model) {
		model.addAttribute("ccmPartyActivity", ccmPartyActivity);
		//return "ccm/partyactivity/ccmPartyActivityForm";
		CcmPartyOrganiz ccmPartyOrganiz = new CcmPartyOrganiz();
		ccmPartyOrganiz.setType(ccmPartyActivity.getType());
		List<CcmPartyOrganiz> list = ccmPartyOrganizService.findList(ccmPartyOrganiz);
		model.addAttribute("list", list);

		String type = ccmPartyActivity.getType();
		return  "1".equals(type) ?  "ccm/partyactivity/ccmPartyActivityForm" : "ccm/partyactivity/ccmPartyDoubleActivityForm";
	}

	@RequiresPermissions("partyactivity:ccmPartyActivity:edit")
	@RequestMapping(value = "save")
	public void save(CcmPartyActivity ccmPartyActivity, Model model, RedirectAttributes redirectAttributes,
					   HttpServletRequest request,HttpServletResponse response) throws IOException {

		ccmPartyActivityService.save(ccmPartyActivity);
		addMessage(redirectAttributes, "保存党员活动管理成功");
		//return "redirect:"+Global.getAdminPath()+"/partyactivity/ccmPartyActivity/?repage";

		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>	var index = parent.layer.getFrameIndex(window.name);parent.layer.close(index);</script>");
		out.println("<script language='javascript'>parent.parent.parent.alertInfo('" + "保存成功"
				+ "');</script>");
		out.println("<script language='javascript'>parent.parent.parent.document.getElementById('mainFrame').contentWindow.location.reload(true);</script>");
	}
	
	@RequiresPermissions("partyactivity:ccmPartyActivity:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPartyActivity ccmPartyActivity, RedirectAttributes redirectAttributes) {
		ccmPartyActivityService.delete(ccmPartyActivity);
		addMessage(redirectAttributes, "删除党员活动管理成功");
		String type = ccmPartyActivity.getType();
		return "redirect:"+Global.getAdminPath()+"/partyactivity/ccmPartyActivity/?type=" + type;
	}

}