/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.event.entity.CcmAlarmLog;
import com.arjjs.ccm.modules.ccm.event.service.CcmAlarmLogService;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildmanage;

/**
 * 告警日志查询Controller
 * @author pengjianqiang
 * @version 2018-05-03
 */
@Controller
@RequestMapping(value = "${adminPath}/event/ccmAlarmLog")
public class CcmAlarmLogController extends BaseController {

	@Autowired
	private CcmAlarmLogService ccmAlarmLogService;
	
	@ModelAttribute
	public CcmAlarmLog get(@RequestParam(required=false) String id) {
		CcmAlarmLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmAlarmLogService.get(id);
		}
		if (entity == null){
			entity = new CcmAlarmLog();
		}
		return entity;
	}
	
	/**
	 * @see 地图跳转信息
	 * @param ccmHouseBuildmanage
	 * @param model
	 * @return
	 */
	@RequiresPermissions("event:ccmAlarmLog:view")
	@RequestMapping(value = "ToMap", method = RequestMethod.GET)
	public String ToMap(CcmAlarmLog ccmAlarmLog, Model model) {
		// 创建 查询对象 建立查询条件
		model.addAttribute("ccmAlarmLog", ccmAlarmLog);
		return "ccm/event/map/mapAlarmLog";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//
	@RequiresPermissions("event:ccmAlarmLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmAlarmLog ccmAlarmLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmAlarmLog> page = ccmAlarmLogService.findPage(new Page<CcmAlarmLog>(request, response), ccmAlarmLog); 
		model.addAttribute("page", page);
		return "ccm/event/ccmAlarmLogList";
	}

	@RequiresPermissions("event:ccmAlarmLog:view")
	@RequestMapping(value = "form")
	public String form(CcmAlarmLog ccmAlarmLog, Model model) {
		model.addAttribute("ccmAlarmLog", ccmAlarmLog);
		return "ccm/event/ccmAlarmLogForm";
	}

	@RequiresPermissions("event:ccmAlarmLog:edit")
	@RequestMapping(value = "save")
	public String save(CcmAlarmLog ccmAlarmLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmAlarmLog)){
			return form(ccmAlarmLog, model);
		}
		ccmAlarmLogService.save(ccmAlarmLog);
		addMessage(redirectAttributes, "保存告警日志成功");
		return "redirect:"+Global.getAdminPath()+"/event/ccmAlarmLog/?repage";
	}
	
	@RequiresPermissions("event:ccmAlarmLog:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmAlarmLog ccmAlarmLog, RedirectAttributes redirectAttributes) {
		ccmAlarmLogService.delete(ccmAlarmLog);
		addMessage(redirectAttributes, "删除告警日志成功");
		return "redirect:"+Global.getAdminPath()+"/event/ccmAlarmLog/?repage";
	}

}