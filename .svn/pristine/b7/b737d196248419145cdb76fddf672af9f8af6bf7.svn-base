/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.remote.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.iot.remote.entity.CcmRemoteControl;
import com.arjjs.ccm.modules.iot.remote.service.CcmRemoteControlService;
import com.arjjs.ccm.tool.CommUtil;
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
 * 远程控制Controller
 * @author cby
 * @version 2019-07-24
 */
@Controller
@RequestMapping(value = "${adminPath}/remote/ccmRemoteControl")
public class CcmRemoteControlController extends BaseController {

	@Autowired
	private CcmRemoteControlService ccmRemoteControlService;
	
	@ModelAttribute
	public CcmRemoteControl get(@RequestParam(required=false) String id) {
		CcmRemoteControl entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmRemoteControlService.get(id);
		}
		if (entity == null){
			entity = new CcmRemoteControl();
		}
		return entity;
	}
	
	@RequiresPermissions("remote:ccmRemoteControl:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmRemoteControl ccmRemoteControl, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmRemoteControl> page = ccmRemoteControlService.findPage(new Page<CcmRemoteControl>(request, response), ccmRemoteControl); 
		model.addAttribute("page", page);
		return "iot/remote/ccmRemoteControlList";
	}

	@RequiresPermissions("remote:ccmRemoteControl:view")
	@RequestMapping(value = "form")
	public String form(CcmRemoteControl ccmRemoteControl, Model model) {
		model.addAttribute("ccmRemoteControl", ccmRemoteControl);
		return "iot/remote/ccmRemoteControlForm";
	}
	
	@RequiresPermissions("remote:ccmRemoteControl:view")
	@RequestMapping(value = "close")
	public String close(HttpServletRequest request, HttpServletResponse response, CcmRemoteControl ccmRemoteControl, Model model,RedirectAttributes redirectAttributes) throws IOException {
		if(StringUtils.isNotEmpty(ccmRemoteControl.getId())) {
			ccmRemoteControl.setEquipmentState("02");
			ccmRemoteControlService.updatestate(ccmRemoteControl);
			addMessage(redirectAttributes, "关机成功");
		}
		return "redirect:"+Global.getAdminPath()+"/remote/ccmRemoteControl/?repage";
	}
	
	@RequiresPermissions("remote:ccmRemoteControl:view")
	@RequestMapping(value = "init")
	public String init(HttpServletRequest request, HttpServletResponse response, CcmRemoteControl ccmRemoteControl, Model model, RedirectAttributes redirectAttributes) throws IOException  {
		if(StringUtils.isNotEmpty(ccmRemoteControl.getId())) {
			ccmRemoteControl.setEquipmentState("01");
			ccmRemoteControlService.updatestate(ccmRemoteControl);
			addMessage(redirectAttributes, "初始化成功");
		}
		return "redirect:"+Global.getAdminPath()+"/remote/ccmRemoteControl/?repage";
	}
	
	@RequiresPermissions("remote:ccmRemoteControl:view")
	@RequestMapping(value = "detail")
	public String detail(CcmRemoteControl ccmRemoteControl, Model model) {
		model.addAttribute("ccmRemoteControl", ccmRemoteControl);
		return "iot/remote/ccmRemoteControlDetailForm";
	}

	@RequiresPermissions("remote:ccmRemoteControl:edit")
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request, HttpServletResponse response, CcmRemoteControl ccmRemoteControl, Model model, RedirectAttributes redirectAttributes) throws IOException {
		if (!beanValidator(model, ccmRemoteControl)){
			//return form(ccmRemoteControl, model);
		}
		ccmRemoteControlService.save(ccmRemoteControl);
		addMessage(redirectAttributes, "保存远程控制成功");
		//return "redirect:"+Global.getAdminPath()+"/remote/ccmRemoteControl/?repage";
		
		PrintWriter out = response.getWriter();
		CommUtil.openWinExpDiv(out, "保存远程控制成功");
	}
	
	@RequiresPermissions("remote:ccmRemoteControl:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmRemoteControl ccmRemoteControl, RedirectAttributes redirectAttributes) {
		ccmRemoteControlService.delete(ccmRemoteControl);
		addMessage(redirectAttributes, "删除远程控制成功");
		return "redirect:"+Global.getAdminPath()+"/remote/ccmRemoteControl/?repage";
	}

}