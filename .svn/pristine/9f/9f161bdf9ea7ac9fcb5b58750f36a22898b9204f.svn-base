/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.alarmnotify.web;

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
import com.arjjs.ccm.modules.flat.alarmnotify.entity.BphAlarmNotify;
import com.arjjs.ccm.modules.flat.alarmnotify.service.BphAlarmNotifyService;

/**
 * 警情通知记录Controller
 * @author maoxb
 * @version 2019-05-05
 */
@Controller
@RequestMapping(value = "${adminPath}/alarmnotify/bphAlarmNotify")
public class BphAlarmNotifyController extends BaseController {

	@Autowired
	private BphAlarmNotifyService bphAlarmNotifyService;
	
	@ModelAttribute
	public BphAlarmNotify get(@RequestParam(required=false) String id) {
		BphAlarmNotify entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bphAlarmNotifyService.get(id);
		}
		if (entity == null){
			entity = new BphAlarmNotify();
		}
		return entity;
	}
	
	@RequiresPermissions("alarmnotify:bphAlarmNotify:view")
	@RequestMapping(value = {"list", ""})
	public String list(BphAlarmNotify bphAlarmNotify, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BphAlarmNotify> page = bphAlarmNotifyService.findPage(new Page<BphAlarmNotify>(request, response), bphAlarmNotify); 
		model.addAttribute("page", page);
		return "flat/alarmnotify/bphAlarmNotifyList";
	}

	@RequiresPermissions("alarmnotify:bphAlarmNotify:view")
	@RequestMapping(value = "form")
	public String form(BphAlarmNotify bphAlarmNotify, Model model) {
		model.addAttribute("bphAlarmNotify", bphAlarmNotify);
		return "flat/alarmnotify/bphAlarmNotifyForm";
	}

	@RequiresPermissions("alarmnotify:bphAlarmNotify:edit")
	@RequestMapping(value = "save")
	public String save(BphAlarmNotify bphAlarmNotify, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bphAlarmNotify)){
			return form(bphAlarmNotify, model);
		}
		bphAlarmNotifyService.save(bphAlarmNotify);
		addMessage(redirectAttributes, "保存警情通知记录成功");
		return "redirect:"+Global.getAdminPath()+"/alarmnotify/bphAlarmNotify/?repage";
	}
	
	@RequiresPermissions("alarmnotify:bphAlarmNotify:edit")
	@RequestMapping(value = "delete")
	public String delete(BphAlarmNotify bphAlarmNotify, RedirectAttributes redirectAttributes) {
		bphAlarmNotifyService.delete(bphAlarmNotify);
		addMessage(redirectAttributes, "删除警情通知记录成功");
		return "redirect:"+Global.getAdminPath()+"/alarmnotify/bphAlarmNotify/?repage";
	}

}