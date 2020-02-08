/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyvolutterpost.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.partyvolutterpost.entity.CcmPartyVolutterPost;
import com.arjjs.ccm.modules.ccm.partyvolutterpost.service.CcmPartyVolutterPostService;
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
 * 志愿者岗位管理Controller
 * @author cby
 * @version 2019-08-15
 */
@Controller
@RequestMapping(value = "${adminPath}/partyvolutterpost/ccmPartyVolutterPost")
public class CcmPartyVolutterPostController extends BaseController {

	@Autowired
	private CcmPartyVolutterPostService ccmPartyVolutterPostService;
	
	@ModelAttribute
	public CcmPartyVolutterPost get(@RequestParam(required=false) String id) {
		CcmPartyVolutterPost entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmPartyVolutterPostService.get(id);
		}
		if (entity == null){
			entity = new CcmPartyVolutterPost();
		}
		return entity;
	}

	@RequestMapping(value = {"ccmPartyVolutterPostindex"})
	public String ccmPartyVolutterPostindex() {
		return "ccm/partyvolutterpost/ccmPartyVolutterPostindex";
	}

	@RequiresPermissions("partyvolutterpost:ccmPartyVolutterPost:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmPartyVolutterPost ccmPartyVolutterPost, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmPartyVolutterPost> page = ccmPartyVolutterPostService.findPage(new Page<CcmPartyVolutterPost>(request, response), ccmPartyVolutterPost); 
		model.addAttribute("page", page);
		return "ccm/partyvolutterpost/ccmPartyVolutterPostList";
	}

	@RequiresPermissions("partyvolutterpost:ccmPartyVolutterPost:view")
	@RequestMapping(value = "form")
	public String form(CcmPartyVolutterPost ccmPartyVolutterPost, Model model) {
		model.addAttribute("ccmPartyVolutterPost", ccmPartyVolutterPost);
		return "ccm/partyvolutterpost/ccmPartyVolutterPostForm";
	}

	@RequiresPermissions("partyvolutterpost:ccmPartyVolutterPost:edit")
	@RequestMapping(value = "save")
	public void save(CcmPartyVolutterPost ccmPartyVolutterPost, Model model, RedirectAttributes redirectAttributes,
			 HttpServletRequest request,HttpServletResponse response) throws IOException {
//		if (!beanValidator(model, ccmPartyVolutterPost)){
//			return form(ccmPartyVolutterPost, model);
//		}
		PrintWriter out = response.getWriter();
		ccmPartyVolutterPostService.save(ccmPartyVolutterPost);
		addMessage(redirectAttributes, "保存志愿者岗位管理成功");
		//return "redirect:"+Global.getAdminPath()+"/partyvolutterpost/ccmPartyVolutterPost/?repage";

		out.println("<script language='javascript'>	var index = parent.layer.getFrameIndex(window.name);parent.layer.close(index);</script>");
		out.println("<script language='javascript'>parent.parent.parent.alertInfo('" + "保存成功"
				+ "');</script>");
		out.println("<script language='javascript'>parent.parent.parent.document.getElementById('mainFrame').contentWindow.location.reload(true);</script>");
	}
	
	@RequiresPermissions("partyvolutterpost:ccmPartyVolutterPost:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPartyVolutterPost ccmPartyVolutterPost, RedirectAttributes redirectAttributes) {
		ccmPartyVolutterPostService.delete(ccmPartyVolutterPost);
		addMessage(redirectAttributes, "删除志愿者岗位管理成功");
		return "redirect:"+Global.getAdminPath()+"/partyvolutterpost/ccmPartyVolutterPost/?repage";
	}

}