/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.sys.web;

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
import com.arjjs.ccm.modules.pbs.sys.entity.PbsRemindMsg;
import com.arjjs.ccm.modules.pbs.sys.service.PbsRemindMsgService;

/**
 * 消息提醒信息Controller
 * @author lc
 * @version 2018-08-02
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/pbsRemindMsg")
public class PbsRemindMsgController extends BaseController {

	@Autowired
	private PbsRemindMsgService pbsRemindMsgService;
	
	@ModelAttribute
	public PbsRemindMsg get(@RequestParam(required=false) String id) {
		PbsRemindMsg entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = pbsRemindMsgService.get(id);
		}
		if (entity == null){
			entity = new PbsRemindMsg();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:pbsRemindMsg:view")
	@RequestMapping(value = {"list", ""})
	public String list(PbsRemindMsg pbsRemindMsg, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PbsRemindMsg> page = pbsRemindMsgService.findPage(new Page<PbsRemindMsg>(request, response), pbsRemindMsg); 
		model.addAttribute("page", page);
		return "pbs/sys/pbsRemindMsgList";
	}

	@RequiresPermissions("sys:pbsRemindMsg:view")
	@RequestMapping(value = "form")
	public String form(PbsRemindMsg pbsRemindMsg, Model model) {
		model.addAttribute("pbsRemindMsg", pbsRemindMsg);
		return "pbs/sys/pbsRemindMsgForm";
	}

	@RequiresPermissions("sys:pbsRemindMsg:edit")
	@RequestMapping(value = "save")
	public String save(PbsRemindMsg pbsRemindMsg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, pbsRemindMsg)){
			return form(pbsRemindMsg, model);
		}
		pbsRemindMsgService.save(pbsRemindMsg);
		addMessage(redirectAttributes, "保存消息提醒信息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/pbsRemindMsg/?repage";
	}
	
	@RequiresPermissions("sys:pbsRemindMsg:edit")
	@RequestMapping(value = "delete")
	public String delete(PbsRemindMsg pbsRemindMsg, RedirectAttributes redirectAttributes) {
		pbsRemindMsgService.delete(pbsRemindMsg);
		addMessage(redirectAttributes, "删除消息提醒信息成功");
		return "redirect:"+Global.getAdminPath()+"/sys/pbsRemindMsg/?repage";
	}

}