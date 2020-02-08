/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.web;

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
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowKeyMeeting;
import com.arjjs.ccm.modules.ccm.know.service.CcmKnowKeyMeetingService;

/**
 * 重要会议Controller
 * @author liang
 * @version 2018-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/know/ccmKnowKeyMeeting")
public class CcmKnowKeyMeetingController extends BaseController {

	@Autowired
	private CcmKnowKeyMeetingService ccmKnowKeyMeetingService;
	
	@ModelAttribute
	public CcmKnowKeyMeeting get(@RequestParam(required=false) String id) {
		CcmKnowKeyMeeting entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmKnowKeyMeetingService.get(id);
		}
		if (entity == null){
			entity = new CcmKnowKeyMeeting();
		}
		return entity;
	}
	
	@RequiresPermissions("know:ccmKnowKeyMeeting:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmKnowKeyMeeting ccmKnowKeyMeeting, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmKnowKeyMeeting> page = ccmKnowKeyMeetingService.findPage(new Page<CcmKnowKeyMeeting>(request, response), ccmKnowKeyMeeting); 
		model.addAttribute("page", page);
		return "ccm/know/ccmKnowKeyMeetingList";
	}

	@RequiresPermissions("know:ccmKnowKeyMeeting:view")
	@RequestMapping(value = "form")
	public String form(CcmKnowKeyMeeting ccmKnowKeyMeeting, Model model) {
		model.addAttribute("ccmKnowKeyMeeting", ccmKnowKeyMeeting);
		return "ccm/know/ccmKnowKeyMeetingForm";
	}

	@RequiresPermissions("know:ccmKnowKeyMeeting:edit")
	@RequestMapping(value = "save")
	public String save(CcmKnowKeyMeeting ccmKnowKeyMeeting, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmKnowKeyMeeting)){
			return form(ccmKnowKeyMeeting, model);
		}
		ccmKnowKeyMeetingService.save(ccmKnowKeyMeeting);
		addMessage(redirectAttributes, "保存重要会议成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowKeyMeeting/?repage";
	}
	
	@RequiresPermissions("know:ccmKnowKeyMeeting:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmKnowKeyMeeting ccmKnowKeyMeeting, RedirectAttributes redirectAttributes) {
		ccmKnowKeyMeetingService.delete(ccmKnowKeyMeeting);
		addMessage(redirectAttributes, "删除重要会议成功");
		return "redirect:"+Global.getAdminPath()+"/know/ccmKnowKeyMeeting/?repage";
	}

}