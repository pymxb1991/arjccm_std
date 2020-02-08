/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyreport.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.partybuild.entity.CcmPartyOrganiz;
import com.arjjs.ccm.modules.ccm.partybuild.service.CcmPartyOrganizService;
import com.arjjs.ccm.modules.ccm.partyperson.entity.CcmPartyPerson;
import com.arjjs.ccm.modules.ccm.partyperson.service.CcmPartyPersonService;
import com.arjjs.ccm.modules.ccm.partyreport.entity.CcmPartyReport;
import com.arjjs.ccm.modules.ccm.partyreport.service.CcmPartyReportService;
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
 * 双报道情况管理Controller
 * @author maoxb
 * @version 2019-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/partyreport/ccmPartyReport")
public class CcmPartyReportController extends BaseController {

	@Autowired
	private CcmPartyReportService ccmPartyReportService;
	@Autowired
	private CcmPartyOrganizService ccmPartyOrganizService;
	@Autowired
	private CcmPartyPersonService ccmPartyPersonService;
	
	@ModelAttribute
	public CcmPartyReport get(@RequestParam(required=false) String id) {
		CcmPartyReport entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmPartyReportService.get(id);
		}
		if (entity == null){
			entity = new CcmPartyReport();
		}
		return entity;
	}

	@RequestMapping(value = {"ccmPartyReportIndex"})
	public String ccmPartyReportIndex() {
		return "ccm/partyreport/ccmPartyReportIndex";
	}

	@RequiresPermissions("partyreport:ccmPartyReport:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmPartyReport ccmPartyReport, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmPartyReport> page = ccmPartyReportService.findPage(new Page<CcmPartyReport>(request, response), ccmPartyReport); 
		model.addAttribute("page", page);
		if(ccmPartyReport.getType().equals("2")){
			model.addAttribute("type","2");
		}else{
			model.addAttribute("type","1");
		}
		return "ccm/partyreport/ccmPartyReportList";
	}

	@RequiresPermissions("partyreport:ccmPartyReport:view")
	@RequestMapping(value = "form")
	public String form(CcmPartyReport ccmPartyReport, Model model) {
		model.addAttribute("ccmPartyReport", ccmPartyReport);

		if ("1".equals(ccmPartyReport.getType())) {
			List<CcmPartyOrganiz> orgPartylist = ccmPartyOrganizService.findList(new CcmPartyOrganiz());
			model.addAttribute("list", orgPartylist);
		}
		if ("2".equals(ccmPartyReport.getType())) {
			List<CcmPartyPerson> perPartylist = ccmPartyPersonService.findList(new CcmPartyPerson());
			model.addAttribute("list", perPartylist);
		}

		return "ccm/partyreport/ccmPartyReportForm";
	}

	@RequiresPermissions("partyreport:ccmPartyReport:edit")
	@RequestMapping(value = "save")
	public void save(CcmPartyReport ccmPartyReport, Model model, RedirectAttributes redirectAttributes,
					 HttpServletRequest request,HttpServletResponse response) throws IOException {
		/*if (!beanValidator(model, ccmPartyReport)){
			return form(ccmPartyReport, model);
		}*/
		ccmPartyReportService.save(ccmPartyReport);
		//return "redirect:"+Global.getAdminPath()+"/partyreport/ccmPartyReport/?repage";

		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>	var index = parent.layer.getFrameIndex(window.name);parent.layer.close(index);</script>");
		out.println("<script language='javascript'>parent.parent.parent.alertInfo('" + "保存成功"
				+ "');</script>");
		out.println("<script language='javascript'>parent.parent.parent.document.getElementById('mainFrame').contentWindow.location.reload(true);</script>");
	}
	
	@RequiresPermissions("partyreport:ccmPartyReport:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPartyReport ccmPartyReport, RedirectAttributes redirectAttributes) {
		ccmPartyReportService.delete(ccmPartyReport);
		addMessage(redirectAttributes, "删除双报道情况管理成功");
		return "redirect:"+Global.getAdminPath()+"/partyreport/ccmPartyReport/?repage";
	}

}