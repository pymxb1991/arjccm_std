/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.cms.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.cms.entity.CcmFontUser;
import com.arjjs.ccm.modules.cms.service.CcmFontUserService;
import com.arjjs.ccm.tool.CommUtil;
import com.arjjs.ccm.tool.PasswordUtils;
import com.google.common.collect.Maps;

/**
 * 居民用户管理Controller
 * @author liuxue
 * @version 2018-10-15
 */
@Controller
@RequestMapping(value = "${adminPath}/cms/ccmFontUser")
public class CcmFontUserController extends BaseController {

	@Autowired
	private CcmFontUserService ccmFontUserService;
	
	@ModelAttribute
	public CcmFontUser get(@RequestParam(required=false) String id) {
		CcmFontUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmFontUserService.get(id);
		}
		if (entity == null){
			entity = new CcmFontUser();
		}
		return entity;
	}
	
	@RequiresPermissions("cms:ccmFontUser:view")
	@RequestMapping(value = {"index"})
	public String index(CcmFontUser ccmFontUser,Model model) {
		return "modules/cms/ccmFontUserIndex";
	}
	
	@RequiresPermissions("cms:ccmFontUser:view")
	@RequestMapping(value = {"checkIndex"})
	public String checkIndex(CcmFontUser ccmFontUser,Model model) {
		return "modules/cms/ccmFontUserCheckIndex";
	}
	
	@RequiresPermissions("cms:ccmFontUser:view")
	@RequestMapping(value = {"cancelIndex"})
	public String cancelIndex(CcmFontUser ccmFontUser,Model model) {
		return "modules/cms/ccmFontUserCancelIndex";
	}
	
	
	@RequiresPermissions("cms:ccmFontUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmFontUser ccmFontUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmFontUser> page = ccmFontUserService.findPage(new Page<CcmFontUser>(request, response), ccmFontUser); 
		model.addAttribute("page", page);
		return "modules/cms/ccmFontUserList";
	}
	
	@RequiresPermissions("cms:ccmFontUser:view")
	@RequestMapping(value = {"Ex"})
	public String Exlist(CcmFontUser ccmFontUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmFontUser> page = ccmFontUserService.findPage(new Page<CcmFontUser>(request, response), ccmFontUser); 
		model.addAttribute("page", page);
		return "modules/cms/ccmFontUserExList";
	}
	
	@RequiresPermissions("cms:ccmFontUser:view")
	@RequestMapping(value = {"Fx"})
	public String Fxlist(CcmFontUser ccmFontUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmFontUser> page = ccmFontUserService.findPage(new Page<CcmFontUser>(request, response), ccmFontUser); 
		model.addAttribute("page", page);
		return "modules/cms/ccmFontUserFxList";
	}
	
	@RequiresPermissions("cms:ccmFontUser:view")
	@RequestMapping(value = {"checkList"})
	public String checkList(CcmFontUser ccmFontUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmFontUser> page = ccmFontUserService.findPage(new Page<CcmFontUser>(request, response), ccmFontUser); 
		model.addAttribute("page", page);
		return "modules/cms/ccmFontUserCheckList";
	}
	
	@RequiresPermissions("cms:ccmFontUser:view")
	@RequestMapping(value = {"cancelList"})
	public String cancelList(CcmFontUser ccmFontUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmFontUser> page = ccmFontUserService.findPage(new Page<CcmFontUser>(request, response), ccmFontUser); 
		model.addAttribute("page", page);
		return "modules/cms/ccmFontUserCancelList";
	}

	@RequiresPermissions("cms:ccmFontUser:view")
	@RequestMapping(value = "form")
	public String form(CcmFontUser ccmFontUser, Model model) {
		model.addAttribute("ccmFontUser", ccmFontUser);
		return "modules/cms/ccmFontUserForm";
	}
    
	@ResponseBody
	@RequiresPermissions("preview:ccmEventIncidentPreview:edit")
	@RequestMapping(value = "pass")
	public Object approve(CcmFontUser ccmFontUser, Model model,
			RedirectAttributes redirectAttributes, @RequestParam(required = true) String loginFlag,HttpServletResponse response) {
		ccmFontUser.setLoginFlag(loginFlag);
		// 审核通过后将对应数据插到事件表中
		ccmFontUserService.save(ccmFontUser);

		HashMap<String,Object> result = Maps.newHashMapWithExpectedSize(2);
		result.put("code", "200");
		
		return result;
	}
	
	
	@RequiresPermissions("cms:ccmFontUser:edit")
	@RequestMapping(value = "save")
	public void save(CcmFontUser ccmFontUser, Model model, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		if (!beanValidator(model, ccmFontUser)){
			form(ccmFontUser, model);
			return;
		}
		if(StringUtils.isNotBlank(ccmFontUser.getNewPassword())){
			ccmFontUser.setPassword(PasswordUtils.createPassword(ccmFontUser.getNewPassword()));
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
			String userId = ccmFontUser.getId();
			String loginName = ccmFontUser.getLoginName();
			boolean bool = false;
			if(StringUtils.isNotBlank(userId)) {
				CcmFontUser ccmFontUserGet = ccmFontUserService.get(userId);
				if(ccmFontUserGet != null) {
					if(!loginName.equals(ccmFontUserGet.getLoginName())){
						if(ccmFontUserService.getByLoginName(ccmFontUser) != null){
							bool = true;
						}
					}
				}
			}
			if(bool) {				
				CommUtil.openWinExpDiv(out, "该登录名已存在");
			}else {
				ccmFontUserService.save(ccmFontUser);
				CommUtil.openWinExpDiv(out, "保存居民用户管理成功");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequiresPermissions("cms:ccmFontUser:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmFontUser ccmFontUser, RedirectAttributes redirectAttributes) {
		ccmFontUserService.delete(ccmFontUser);
		addMessage(redirectAttributes, "删除居民用户管理成功");
		return "redirect:"+Global.getAdminPath()+"/cms/ccmFontUser/?repage";
	}

}