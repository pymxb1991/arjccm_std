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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventStakeholder;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventStakeholderService;

/**
 * 案事件干系人Controller
 * @author pengjianqiang
 * @version 2018-01-30
 */
@Controller
@RequestMapping(value = "${adminPath}/event/ccmEventStakeholder")
public class CcmEventStakeholderController extends BaseController {

	@Autowired
	private CcmEventStakeholderService ccmEventStakeholderService;
	
	@ModelAttribute
	public CcmEventStakeholder get(@RequestParam(required=false) String id) {
		CcmEventStakeholder entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmEventStakeholderService.get(id);
		}
		if (entity == null){
			entity = new CcmEventStakeholder();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(CcmEventStakeholder ccmEventStakeholder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmEventStakeholder> page = ccmEventStakeholderService.findPage(new Page<CcmEventStakeholder>(request, response), ccmEventStakeholder); 
		model.addAttribute("page", page);
		model.addAttribute("incidentId", ccmEventStakeholder.getIncidentId());
		return "ccm/event/ccmEventStakeholderList";
	}

	@RequiresPermissions("event:ccmEventStakeholder:view")
	@RequestMapping(value = "form")
	public String form(CcmEventStakeholder ccmEventStakeholder, Model model) {
		model.addAttribute("ccmEventStakeholder", ccmEventStakeholder);
		return "ccm/event/ccmEventStakeholderForm";
	}

	@RequiresPermissions("event:ccmEventStakeholder:edit")
	@RequestMapping(value = "save")
	public String save(CcmEventStakeholder ccmEventStakeholder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmEventStakeholder)){
			return form(ccmEventStakeholder, model);
		}
		ccmEventStakeholderService.save(ccmEventStakeholder);
		addMessage(redirectAttributes, "保存案事件干系人成功");
		return "redirect:"+Global.getAdminPath()+"/event/ccmEventStakeholder/list?incidentId=" + ccmEventStakeholder.getIncidentId()+"&repage";
	}
	
	@RequiresPermissions("event:ccmEventStakeholder:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmEventStakeholder ccmEventStakeholder, RedirectAttributes redirectAttributes) {
		ccmEventStakeholderService.delete(ccmEventStakeholder);
		addMessage(redirectAttributes, "删除案事件干系人成功");
		return "redirect:"+Global.getAdminPath()+"/event/ccmEventStakeholder/list?incidentId=" + ccmEventStakeholder.getIncidentId()+"&repage";
	}

}