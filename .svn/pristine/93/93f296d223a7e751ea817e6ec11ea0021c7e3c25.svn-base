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
import com.arjjs.ccm.modules.ccm.group.entity.CcmGroupControl;
import com.arjjs.ccm.modules.ccm.group.service.CcmGroupControlService;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 自治群管理Controller
 * @author liuyongjian
 * @version 2019-08-06
 */
@Controller
@RequestMapping(value = "${adminPath}/group/ccmGroupControl")
public class CcmGroupControlController extends BaseController {

	@Autowired
	private CcmGroupControlService ccmGroupControlService;
	
	@ModelAttribute
	public CcmGroupControl get(@RequestParam(required=false) String id) {
		CcmGroupControl entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmGroupControlService.get(id);
		}
		if (entity == null){
			entity = new CcmGroupControl();
		}
		return entity;
	}
	
	@RequiresPermissions("group:ccmGroupControl:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmGroupControl ccmGroupControl, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmGroupControl> page = ccmGroupControlService.findPage(new Page<CcmGroupControl>(request, response), ccmGroupControl); 
		model.addAttribute("page", page);
		return "ccm/group/ccmGroupControlList";
	}
	@RequiresPermissions("group:ccmGroupControl:view")
	@RequestMapping(value = { "groupcontrolList" })
	public String groupcontrolList() {
		return "ccm/group/groupcontrolList";
	}

	@RequiresPermissions("group:ccmGroupControl:view")
	@RequestMapping(value = "form")
	public String form(CcmGroupControl ccmGroupControl, Model model) {
		model.addAttribute("ccmGroupControl", ccmGroupControl);
		return "ccm/group/ccmGroupControlForm";
	}

	@RequiresPermissions("group:ccmGroupControl:edit")
	@RequestMapping(value = "save")
	public void save(CcmGroupControl ccmGroupControl, Model model, RedirectAttributes redirectAttributes,HttpServletResponse response) {
		if (!beanValidator(model, ccmGroupControl)){
			//return form(ccmGroupControl, model);
		}
		ccmGroupControlService.save(ccmGroupControl);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommUtil.openWinExpDiv(out, "保存自治群成功");
	}
	
	@RequiresPermissions("group:ccmGroupControl:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmGroupControl ccmGroupControl, RedirectAttributes redirectAttributes) {
		ccmGroupControlService.delete(ccmGroupControl);
		addMessage(redirectAttributes, "删除自治群成功");
		return "redirect:"+Global.getAdminPath()+"/group/ccmGroupControl/?repage";
	}

}