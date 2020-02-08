/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.alarmhandlelog.web;

import java.io.IOException;
import java.util.List;

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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.flat.alarmhandlelog.entity.BphAlarmHandleLog;
import com.arjjs.ccm.modules.flat.alarmhandlelog.service.BphAlarmHandleLogService;

/**
 * 处警过程记录Controller
 * @author wangyikai
 * @version 2018-11-22
 */
@Controller
@RequestMapping(value = "${adminPath}/alarmhandlelog/bphAlarmHandleLog")
public class BphAlarmHandleLogController extends BaseController {

	@Autowired
	private BphAlarmHandleLogService bphAlarmHandleLogService;
	@ModelAttribute
	public BphAlarmHandleLog get(@RequestParam(required=false) String id) {
		BphAlarmHandleLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bphAlarmHandleLogService.get(id);
		}
		if (entity == null){
			entity = new BphAlarmHandleLog();
		}
		return entity;
	}
	
	@RequiresPermissions("alarmhandlelog:bphAlarmHandleLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(BphAlarmHandleLog bphAlarmHandleLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BphAlarmHandleLog> page = bphAlarmHandleLogService.findPage(new Page<BphAlarmHandleLog>(request, response), bphAlarmHandleLog); 
		model.addAttribute("page", page);
		return "flat/alarmhandlelog/bphAlarmHandleLogList";
	}

	@RequiresPermissions("alarmhandlelog:bphAlarmHandleLog:view")
	@RequestMapping(value = "form")
	public String form(BphAlarmHandleLog bphAlarmHandleLog, Model model) {
		model.addAttribute("bphAlarmHandleLog", bphAlarmHandleLog);
		return "flat/alarmhandlelog/bphAlarmHandleLogForm";
	}

	@RequiresPermissions("alarmhandlelog:bphAlarmHandleLog:edit")
	@RequestMapping(value = "save")
	public String save(BphAlarmHandleLog bphAlarmHandleLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bphAlarmHandleLog)){
			return form(bphAlarmHandleLog, model);
		}
		bphAlarmHandleLogService.save(bphAlarmHandleLog);
		addMessage(redirectAttributes, "保存处警过程记录成功");
		return "redirect:"+Global.getAdminPath()+"/alarmhandlelog/bphAlarmHandleLog/?repage";
	}
	
	@RequiresPermissions("alarmhandlelog:bphAlarmHandleLog:edit")
	@RequestMapping(value = "delete")
	public String delete(BphAlarmHandleLog bphAlarmHandleLog, RedirectAttributes redirectAttributes) {
		bphAlarmHandleLogService.delete(bphAlarmHandleLog);
		addMessage(redirectAttributes, "删除处警过程记录成功");
		return "redirect:"+Global.getAdminPath()+"/alarmhandlelog/bphAlarmHandleLog/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value = "findHandleLog")
	public List<BphAlarmHandleLog> findHandleLog(BphAlarmHandleLog bphAlarmHandleLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<BphAlarmHandleLog> HandleLogList = bphAlarmHandleLogService.findHandleLog(bphAlarmHandleLog);
		return HandleLogList;
	}
	
	@ResponseBody
	@RequestMapping(value="sendMessage")
	public boolean sendMessage(HttpServletRequest request, HttpServletResponse response, String contactId, String contactMessageId, String alarmId,String operateDesc) throws IOException {
		return bphAlarmHandleLogService.sendMessage(contactId, contactMessageId, alarmId,operateDesc);
	}
}