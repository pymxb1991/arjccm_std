/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.message.web;

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
import com.arjjs.ccm.modules.iot.message.entity.CcmMessageManage;
import com.arjjs.ccm.modules.iot.message.service.CcmMessageManageService;
import com.arjjs.ccm.modules.iot.remote.entity.CcmRemoteControl;
import com.arjjs.ccm.modules.iot.remote.service.CcmRemoteControlService;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 消息管理Controller
 * @author cby
 * @version 2019-07-24
 */
@Controller
@RequestMapping(value = "${adminPath}/message/ccmMessageManage")
public class CcmMessageManageController extends BaseController {
	
	@Autowired
	private CcmRemoteControlService ccmRemoteControlService;
	@Autowired
	private CcmMessageManageService ccmMessageManageService;
	
	@ModelAttribute
	public CcmMessageManage get(@RequestParam(required=false) String id) {
		CcmMessageManage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmMessageManageService.get(id);
		}
		if (entity == null){
			entity = new CcmMessageManage();
		}
		return entity;
	}
	
	@RequiresPermissions("remote:ccmRemoteControl:view")
	@RequestMapping(value = {"tolist"})
	public String list(CcmRemoteControl ccmRemoteControl, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmRemoteControl> page = ccmRemoteControlService.findPage(new Page<CcmRemoteControl>(request, response), ccmRemoteControl); 
		model.addAttribute("page", page);
		return "iot/message/ccmMessageControlList";
	}
	
	@RequiresPermissions("message:ccmMessageManage:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmMessageManage ccmMessageManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmMessageManage> page = ccmMessageManageService.findPage(new Page<CcmMessageManage>(request, response), ccmMessageManage); 
		model.addAttribute("page", page);
		return "iot/message/ccmMessageManageList";
	}
	
	@RequiresPermissions("message:ccmMessageManage:view")
	@RequestMapping(value = {"getlist"})
	public String getlist(String id, String num, CcmMessageManage ccmMessageManage, HttpServletRequest request, HttpServletResponse response, Model model) {
		ccmMessageManage.setEquipmentId(id);
		ccmMessageManage.setEquipmentNum(num);
		Page<CcmMessageManage> page = ccmMessageManageService.findPage(new Page<CcmMessageManage>(request, response), ccmMessageManage); 
		model.addAttribute("page", page);
		model.addAttribute("id", id);
		model.addAttribute("num", num);
		return "iot/message/ccmMessageManageList";
	}

	@RequiresPermissions("message:ccmMessageManage:view")
	@RequestMapping(value = "form")
	public String form(String id, String num, CcmMessageManage ccmMessageManage, Model model) {
		model.addAttribute("ccmMessageManage", ccmMessageManage);
		return "iot/message/ccmMessageManageForm";
	}

	@RequiresPermissions("message:ccmMessageManage:edit")
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request, HttpServletResponse response, CcmMessageManage ccmMessageManage, Model model, RedirectAttributes redirectAttributes) throws IOException {
		if (!beanValidator(model, ccmMessageManage)){
			//return form(ccmMessageManage, model);
		}
		ccmMessageManage.setSendState("03");
		logger.info(ccmMessageManage.toString());
		ccmMessageManageService.save(ccmMessageManage);
		addMessage(redirectAttributes, "保存消息管理成功");
		//return "redirect:"+Global.getAdminPath()+"/message/ccmMessageManage/?repage";
		
		PrintWriter out = response.getWriter();
		CommUtil.openWinExpDiv(out, "保存消息管理成功");
	}
	
	@RequiresPermissions("message:ccmMessageManage:edit")
	@RequestMapping(value = "send")
	public String send(String remoteid, String num, CcmMessageManage ccmMessageManage, RedirectAttributes redirectAttributes) {
		ccmMessageManage.setSendState("01");
		ccmMessageManageService.send(ccmMessageManage);
		addMessage(redirectAttributes, "发送消息成功");
		return "redirect:"+Global.getAdminPath()+"/message/ccmMessageManage/getlist?id="+remoteid+"&num="+num;
	}
	
	@RequiresPermissions("message:ccmMessageManage:edit")
	@RequestMapping(value = "delete")
	public String delete(String remoteid, String num, CcmMessageManage ccmMessageManage, RedirectAttributes redirectAttributes) {
		ccmMessageManageService.delete(ccmMessageManage);
		addMessage(redirectAttributes, "删除消息成功");
		return "redirect:"+Global.getAdminPath()+"/message/ccmMessageManage/getlist?id="+remoteid+"&num="+num;
	}

}