/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.group.web;

import java.io.IOException;
import java.io.PrintWriter;

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
import com.arjjs.ccm.modules.ccm.group.entity.CcmGroupMember;
import com.arjjs.ccm.modules.ccm.group.service.CcmGroupMemberService;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 群成员管理Controller
 * @author liuyongjian
 * @version 2019-08-07
 */
@Controller
@RequestMapping(value = "${adminPath}/group/ccmGroupMember")
public class CcmGroupMemberController extends BaseController {

	@Autowired
	private CcmGroupMemberService ccmGroupMemberService;
	
	@ModelAttribute
	public CcmGroupMember get(@RequestParam(required=false) String id) {
		CcmGroupMember entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmGroupMemberService.get(id);
		}
		if (entity == null){
			entity = new CcmGroupMember();
		}
		return entity;
	}
	
	@RequiresPermissions("group:ccmGroupMember:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmGroupMember ccmGroupMember, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmGroupMember> page = ccmGroupMemberService.findPage(new Page<CcmGroupMember>(request, response), ccmGroupMember); 
		model.addAttribute("page", page);
		return "ccm/group/ccmGroupMemberList";
	}
	
	@RequiresPermissions("group:ccmGroupMember:view")
	@RequestMapping(value = {"index"})
	public String ccmGroupMemberindex() {
		return "ccm/group/ccmGroupMemberindex";
	}

	@RequiresPermissions("group:ccmGroupMember:view")
	@RequestMapping(value = "form")
	public String form(CcmGroupMember ccmGroupMember, Model model) {
		model.addAttribute("ccmGroupMember", ccmGroupMember);
		return "ccm/group/ccmGroupMemberForm";
	}

	@RequiresPermissions("group:ccmGroupMember:edit")
	@RequestMapping(value = "save")
	public void save(CcmGroupMember ccmGroupMember, Model model, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		if (!beanValidator(model, ccmGroupMember)){
			//return form(ccmGroupMember, model);
		}
		ccmGroupMemberService.save(ccmGroupMember);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommUtil.openWinExpDiv(out, "保存群成员成功");
	}
	
	@RequiresPermissions("group:ccmGroupMember:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmGroupMember ccmGroupMember, RedirectAttributes redirectAttributes) {
		ccmGroupMemberService.delete(ccmGroupMember);
		addMessage(redirectAttributes, "删除群成员成功");
		return "redirect:"+Global.getAdminPath()+"/group/ccmGroupMember/?repage";
	}

}