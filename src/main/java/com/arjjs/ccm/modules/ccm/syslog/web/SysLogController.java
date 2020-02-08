/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.syslog.web;

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
import com.arjjs.ccm.modules.ccm.syslog.entity.SysLog;
import com.arjjs.ccm.modules.ccm.syslog.service.SysLogService;

/**
 * 用户登录查询Controller
 * @author liujindan
 * @version 2019-07-09
 */
@Controller
@RequestMapping(value = "${adminPath}/syslog/sysLog")
public class SysLogController extends BaseController {

	@Autowired
	private SysLogService sysLogService;
	
	@ModelAttribute
	public SysLog get(@RequestParam(required=false) String id) {
		SysLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysLogService.get(id);
		}
		if (entity == null){
			entity = new SysLog();
		}
		return entity;
	}
	
	@RequiresPermissions("syslog:sysLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysLog sysLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SysLog> page = sysLogService.findPage(new Page<SysLog>(request, response), sysLog); 
		model.addAttribute("page", page);
		return "ccm/syslog/sysLogList";
	}

	@RequiresPermissions("syslog:sysLog:view")
	@RequestMapping(value = "form")
	public String form(SysLog sysLog, Model model) {
		model.addAttribute("sysLog", sysLog);
		return "ccm/syslog/sysLogForm";
	}

	@RequiresPermissions("syslog:sysLog:edit")
	@RequestMapping(value = "save")
	public String save(SysLog sysLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysLog)){
			return form(sysLog, model);
		}
		sysLogService.save(sysLog);
		addMessage(redirectAttributes, "保存用户登录查询成功");
		return "redirect:"+Global.getAdminPath()+"/syslog/sysLog/?repage";
	}
	
	@RequiresPermissions("syslog:sysLog:edit")
	@RequestMapping(value = "delete")
	public String delete(SysLog sysLog, RedirectAttributes redirectAttributes) {
		sysLogService.delete(sysLog);
		addMessage(redirectAttributes, "删除用户登录查询成功");
		return "redirect:"+Global.getAdminPath()+"/syslog/sysLog/?repage";
	}

}