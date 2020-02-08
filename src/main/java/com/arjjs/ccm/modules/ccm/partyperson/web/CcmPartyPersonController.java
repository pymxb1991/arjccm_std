/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyperson.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.partybuild.entity.CcmPartyOrganiz;
import com.arjjs.ccm.modules.ccm.partybuild.service.CcmPartyOrganizService;
import com.arjjs.ccm.modules.ccm.partyperson.entity.CcmPartyPerson;
import com.arjjs.ccm.modules.ccm.partyperson.service.CcmPartyPersonService;
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
 * 党员信息管理Controller
 * @author maoxb
 * @version 2019-08-13
 */
@Controller
@RequestMapping(value = "${adminPath}/partyperson/ccmPartyPerson")
public class CcmPartyPersonController extends BaseController {

	@Autowired
	private CcmPartyPersonService ccmPartyPersonService;

	@Autowired
	private CcmPartyOrganizService ccmPartyOrganizService;

	@ModelAttribute
	public CcmPartyPerson get(@RequestParam(required=false) String id) {
		CcmPartyPerson entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmPartyPersonService.get(id);
		}
		if (entity == null){
			entity = new CcmPartyPerson();
		}
		return entity;
	}

	@RequestMapping(value = {"ccmPartyDoublePersonIndex"})
	public String ccmPartyDoublePersonIndex() {
		return "ccm/partyperson/ccmPartyDoublePersonIndex";
	}

	@RequestMapping(value = {"ccmPartyPersonIndex"})
	public String ccmPartyPersonIndex() {
		return "ccm/partyperson/ccmPartyPersonIndex";
	}
	
	@RequiresPermissions("partyperson:ccmPartyPerson:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmPartyPerson ccmPartyPerson, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmPartyPerson> page = ccmPartyPersonService.findPage(new Page<CcmPartyPerson>(request, response), ccmPartyPerson); 
		model.addAttribute("page", page);
		//return "ccm/partyperson/ccmPartyPersonList";

		CcmPartyOrganiz ccmPartyOrganiz = new CcmPartyOrganiz();
		ccmPartyOrganiz.setType(ccmPartyPerson.getType());
		List<CcmPartyOrganiz> list = ccmPartyOrganizService.findList(ccmPartyOrganiz);
		model.addAttribute("list", list);

		String type = ccmPartyPerson.getType();
		return  "1".equals(type) ?  "ccm/partyperson/ccmPartyPersonList" : "ccm/partyperson/ccmPartyDoublePersonList";
	}

	@RequiresPermissions("partyperson:ccmPartyPerson:view")
	@RequestMapping(value = "form")
	public String form(CcmPartyPerson ccmPartyPerson, Model model) {
		model.addAttribute("ccmPartyPerson", ccmPartyPerson);
		CcmPartyOrganiz ccmPartyOrganiz = new CcmPartyOrganiz();
		ccmPartyOrganiz.setType(ccmPartyPerson.getType());
		List<CcmPartyOrganiz> list = ccmPartyOrganizService.findList(ccmPartyOrganiz);
		model.addAttribute("list", list);
		//return "ccm/partyperson/ccmPartyPersonForm";
		String type = ccmPartyPerson.getType();
		return  "1".equals(type) ?  "ccm/partyperson/ccmPartyPersonForm" : "ccm/partyperson/ccmPartyDoublePersonForm";
	}

	@RequiresPermissions("partyperson:ccmPartyPerson:edit")
	@RequestMapping(value = "save")
	public void save(CcmPartyPerson ccmPartyPerson,HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
//		if (!beanValidator(model, ccmPartyPerson)){
//			return form(ccmPartyPerson, model);
//		}
		ccmPartyPersonService.save(ccmPartyPerson);

		out.println("<script language='javascript'>	var index = parent.layer.getFrameIndex(window.name);parent.layer.close(index);</script>");
		out.println("<script language='javascript'>parent.parent.parent.alertInfo('" + "保存成功"
				+ "');</script>");
		out.println("<script language='javascript'>parent.parent.parent.document.getElementById('mainFrame').contentWindow.location.reload(true);</script>");
		//return "redirect:"+Global.getAdminPath()+"/partyperson/ccmPartyPerson/?repage";
	}
	
	@RequiresPermissions("partyperson:ccmPartyPerson:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPartyPerson ccmPartyPerson, RedirectAttributes redirectAttributes) {
		ccmPartyPersonService.delete(ccmPartyPerson);
		addMessage(redirectAttributes, "删除党员信息管理成功");
		String type = ccmPartyPerson.getType();
		return "redirect:"+Global.getAdminPath()+"/partyperson/ccmPartyPerson/?type="+ type;
	}

}