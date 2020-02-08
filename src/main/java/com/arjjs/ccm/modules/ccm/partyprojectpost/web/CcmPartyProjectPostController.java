/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyprojectpost.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.partybuild.entity.CcmPartyOrganiz;
import com.arjjs.ccm.modules.ccm.partybuild.service.CcmPartyOrganizService;
import com.arjjs.ccm.modules.ccm.partyperson.entity.CcmPartyPerson;
import com.arjjs.ccm.modules.ccm.partyperson.service.CcmPartyPersonService;
import com.arjjs.ccm.modules.ccm.partyprojectpost.entity.CcmPartyProjectPost;
import com.arjjs.ccm.modules.ccm.partyprojectpost.service.CcmPartyProjectPostService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 认领功能Controller
 * @author cby
 * @version 2019-08-15
 */
@Controller
@RequestMapping(value = "${adminPath}/partyprojectpost/ccmPartyProjectPost")
public class CcmPartyProjectPostController extends BaseController {

	@Autowired
	private CcmPartyProjectPostService ccmPartyProjectPostService;
	@Autowired
	private CcmPartyOrganizService ccmPartyOrganizService;
	@Autowired
	private CcmPartyPersonService ccmPartyPersonService;
	
	@ModelAttribute
	public CcmPartyProjectPost get(@RequestParam(required=false) String id) {
		CcmPartyProjectPost entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmPartyProjectPostService.get(id);
		}
		if (entity == null){
			entity = new CcmPartyProjectPost();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(CcmPartyProjectPost ccmPartyProjectPost, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmPartyProjectPost> page = ccmPartyProjectPostService.findPage(new Page<CcmPartyProjectPost>(request, response), ccmPartyProjectPost); 
		model.addAttribute("page", page);
		return "ccm/partyprojectpost/ccmPartyProjectPostList";
	}

	@RequestMapping(value = "form")
	public String form(CcmPartyProjectPost ccmPartyProjectPost, Model model) {
		model.addAttribute("ccmPartyProjectPost", ccmPartyProjectPost);
		if ("1".equals(ccmPartyProjectPost.getType())) {
			List<CcmPartyOrganiz> orgPartylist = ccmPartyOrganizService.findList(new CcmPartyOrganiz());
			model.addAttribute("list", orgPartylist);
		}
		if ("2".equals(ccmPartyProjectPost.getType())) {
			List<CcmPartyPerson> perPartylist = ccmPartyPersonService.findList(new CcmPartyPerson());
			model.addAttribute("list", perPartylist);
		}
		return "ccm/partyprojectpost/ccmPartyProjectPostForm";
	}

	@RequestMapping(value = "save")
	public void save(CcmPartyProjectPost ccmPartyProjectPost, Model model, RedirectAttributes redirectAttributes,
					   HttpServletRequest request,HttpServletResponse response) throws IOException {
	/*	if (!beanValidator(model, ccmPartyProjectPost)){
			return form(ccmPartyProjectPost, model);
		}*/
		List<String> orgPartyList = ccmPartyProjectPost.getOrgPartyList();
		List<CcmPartyProjectPost> ccmPartyProjectPostList = new ArrayList<CcmPartyProjectPost>();
		for (int i = 0; i < orgPartyList.size(); i++) {
			CcmPartyProjectPost ccmPartyProjectPost1 = new CcmPartyProjectPost();
			ccmPartyProjectPost1.setType(ccmPartyProjectPost.getType());
			ccmPartyProjectPost1.setProPost(ccmPartyProjectPost.getProPost());
			ccmPartyProjectPost1.setOrgParty(ccmPartyProjectPost.getOrgParty());
			ccmPartyProjectPost1.setMore1(ccmPartyProjectPost.getMore1());
			ccmPartyProjectPost1.setMore2(ccmPartyProjectPost.getMore2());
			ccmPartyProjectPost1.setMore3(ccmPartyProjectPost.getMore3());
			ccmPartyProjectPost1.setOrgParty(orgPartyList.get(i));
			ccmPartyProjectPostList.add(ccmPartyProjectPost1);
		}
		ccmPartyProjectPostService.batchSave(ccmPartyProjectPostList);
		addMessage(redirectAttributes, "保存认领功能成功");
		//return "redirect:"+Global.getAdminPath()+"/partyprojectpost/ccmPartyProjectPost/?repage";

		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>	var index = parent.layer.getFrameIndex(window.name);parent.layer.close(index);</script>");
		out.println("<script language='javascript'>parent.parent.parent.alertInfo('" + "保存成功"
				+ "');</script>");
		out.println("<script language='javascript'>parent.parent.parent.document.getElementById('mainFrame').contentWindow.location.reload(true);</script>");
	}
	
	@RequestMapping(value = "delete")
	public String delete(CcmPartyProjectPost ccmPartyProjectPost, RedirectAttributes redirectAttributes) {
		ccmPartyProjectPostService.delete(ccmPartyProjectPost);
		addMessage(redirectAttributes, "删除认领功能成功");
		return "redirect:"+Global.getAdminPath()+"/partyprojectpost/ccmPartyProjectPost/?repage";
	}

}