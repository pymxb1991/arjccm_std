/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.grabber.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.iot.grabber.entity.CcmGrabberManage;
import com.arjjs.ccm.modules.iot.grabber.service.CcmGrabberManageService;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 抓拍机管理Controller
 * @author cby
 * @version 2019-07-25
 */
@Controller
@RequestMapping(value = "${adminPath}/grabber/ccmGrabberManage")
public class CcmGrabberManageController extends BaseController {

	@Autowired
	private CcmGrabberManageService ccmGrabberManageService;
	
	@ModelAttribute
	public CcmGrabberManage get(@RequestParam(required=false) String id) {
		CcmGrabberManage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmGrabberManageService.get(id);
		}
		if (entity == null){
			entity = new CcmGrabberManage();
		}
		return entity;
	}
	
	@RequiresPermissions("grabber:ccmGrabberManage:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmGrabberManage ccmGrabberManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmGrabberManage> page = ccmGrabberManageService.findPage(new Page<CcmGrabberManage>(request, response), ccmGrabberManage); 
		model.addAttribute("page", page);
		return "iot/grabber/ccmGrabberManageList";
	}

	@RequiresPermissions("grabber:ccmGrabberManage:view")
	@RequestMapping(value = "form")
	public String form(CcmGrabberManage ccmGrabberManage, Model model) {
		model.addAttribute("ccmGrabberManage", ccmGrabberManage);
		return "iot/grabber/ccmGrabberManageForm";
	}
	
	@RequiresPermissions("grabber:ccmGrabberManage:view")
	@RequestMapping(value = "synchro")
	public String synchro(CcmGrabberManage ccmGrabberManage, Model model, RedirectAttributes redirectAttributes) {
		ccmGrabberManage.setSynchroState("01");
		ccmGrabberManageService.updateState(ccmGrabberManage);
		addMessage(redirectAttributes, "同步抓拍机成功");
		return "redirect:"+Global.getAdminPath()+"/grabber/ccmGrabberManage/?repage";
	}
	
	@RequiresPermissions("grabber:ccmGrabberManage:view")
	@RequestMapping(value = "detail")
	public String detail(CcmGrabberManage ccmGrabberManage, Model model) {
		model.addAttribute("ccmGrabberManage", ccmGrabberManage);
		return "iot/grabber/ccmGrabberManageDetail";
	}
	
	@RequiresPermissions("grabber:ccmGrabberManage:view")
	@RequestMapping(value = "rule")
	public String rule(CcmGrabberManage ccmGrabberManage, Model model) {
		model.addAttribute("ccmGrabberManage", ccmGrabberManage);
		return "iot/grabber/ccmGrabberRuleForm";
	}
	
	
	@RequiresPermissions("grabber:ccmGrabberManage:edit")
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request, HttpServletResponse response, CcmGrabberManage ccmGrabberManage, Model model, RedirectAttributes redirectAttributes)throws IOException {
		if (!beanValidator(model, ccmGrabberManage)){
			/* return form(ccmGrabberManage, model); */
		}
		ccmGrabberManage.setSynchroState("02");
		ccmGrabberManageService.save(ccmGrabberManage);
		addMessage(redirectAttributes, "保存抓拍机管理成功");
		
		//return "redirect:"+Global.getAdminPath()+"/grabber/ccmGrabberManage/?repage";
		 
		PrintWriter out = response.getWriter();
		CommUtil.openWinExpDiv(out, "保存抓拍机管理成功");
	}


	//得到抓拍机数量
	@ResponseBody
	@RequestMapping(value = "getCount")
	public String getCount(CcmGrabberManage ccmGrabberManage) {
		int count = ccmGrabberManageService.getCount(ccmGrabberManage);
		return String.valueOf(count);
	}

}