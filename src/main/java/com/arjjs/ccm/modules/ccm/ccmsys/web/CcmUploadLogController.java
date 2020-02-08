/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.ccmsys.web;

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
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmUploadLog;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmUploadLogService;

/**
 * 待上传上级平台记录管理Controller
 * @author pengjianqiang
 * @version 2018-05-12
 */
@Controller
@RequestMapping(value = "${adminPath}/ccmsys/ccmUploadLog")
public class CcmUploadLogController extends BaseController {

	@Autowired
	private CcmUploadLogService ccmUploadLogService;
	
	@ModelAttribute
	public CcmUploadLog get(@RequestParam(required=false) String id) {
		CcmUploadLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmUploadLogService.get(id);
		}
		if (entity == null){
			entity = new CcmUploadLog();
		}
		return entity;
	}
	
	@RequiresPermissions("ccmsys:ccmUploadLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmUploadLog ccmUploadLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmUploadLog> page = ccmUploadLogService.findPage(new Page<CcmUploadLog>(request, response), ccmUploadLog); 
		model.addAttribute("page", page);
		return "ccm/ccmsys/ccmUploadLogList";
	}

	@RequiresPermissions("ccmsys:ccmUploadLog:view")
	@RequestMapping(value = "form")
	public String form(CcmUploadLog ccmUploadLog, Model model) {
		model.addAttribute("ccmUploadLog", ccmUploadLog);
		return "ccm/ccmsys/ccmUploadLogForm";
	}

	@RequiresPermissions("ccmsys:ccmUploadLog:edit")
	@RequestMapping(value = "save")
	public String save(CcmUploadLog ccmUploadLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmUploadLog)){
			return form(ccmUploadLog, model);
		}
		ccmUploadLogService.save(ccmUploadLog);
		addMessage(redirectAttributes, "保存待上传上级平台记录成功");
		return "redirect:"+Global.getAdminPath()+"/ccmsys/ccmUploadLog/?repage";
	}
	
	@RequiresPermissions("ccmsys:ccmUploadLog:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmUploadLog ccmUploadLog, RedirectAttributes redirectAttributes) {
		ccmUploadLogService.delete(ccmUploadLog);
		addMessage(redirectAttributes, "删除待上传上级平台记录成功");
		return "redirect:"+Global.getAdminPath()+"/ccmsys/ccmUploadLog/?repage";
	}

}