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
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventRequestdeal;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventRequestdealService;

/**
 * 请求处理Controller
 * 
 * @author arj
 * @version 2018-01-18
 */
@Controller
@RequestMapping(value = "${adminPath}/event/ccmEventRequestdeal")
public class CcmEventRequestdealController extends BaseController {

	@Autowired
	private CcmEventRequestdealService ccmEventRequestdealService;

	@ModelAttribute
	public CcmEventRequestdeal get(@RequestParam(required = false) String id) {
		CcmEventRequestdeal entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmEventRequestdealService.get(id);
		}
		if (entity == null) {
			entity = new CcmEventRequestdeal();
		}
		return entity;
	}

	/**
	 * @see
	 * @param ccmEventRequestdeal
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("event:ccmEventRequestdeal:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmEventRequestdeal ccmEventRequestdeal, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<CcmEventRequestdeal> page = ccmEventRequestdealService
				.findPage(new Page<CcmEventRequestdeal>(request, response), ccmEventRequestdeal);
		model.addAttribute("page", page);
		return "ccm/event/ccmEventRequestdealList";
	}

	@RequiresPermissions("event:ccmEventRequestdeal:view")
	@RequestMapping(value = "form")
	public String form(CcmEventRequestdeal ccmEventRequestdeal, Model model) {
		model.addAttribute("ccmEventRequestdeal", ccmEventRequestdeal);
		return "ccm/event/ccmEventRequestdealForm";
	}

	@RequiresPermissions("event:ccmEventRequestdeal:edit")
	@RequestMapping(value = "save")
	public String save(CcmEventRequestdeal ccmEventRequestdeal, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmEventRequestdeal)) {
			return form(ccmEventRequestdeal, model);
		}
		ccmEventRequestdealService.save(ccmEventRequestdeal);
		addMessage(redirectAttributes, "保存请求处理成功");
		return "redirect:" + Global.getAdminPath() + "/event/ccmEventRequestdeal/list?repage";
	}
	
	//请求登记的处理信息修改
	@RequiresPermissions("event:ccmEventRequestdeal:edit")
	@RequestMapping(value = "saveDeal")
	public String saveDeal(CcmEventRequestdeal ccmEventRequestdeal, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmEventRequestdeal)) {
			return form(ccmEventRequestdeal, model);
		}
		ccmEventRequestdealService.save(ccmEventRequestdeal);
		addMessage(redirectAttributes, "保存请求处理成功");
		return "redirect:" + Global.getAdminPath() + "/event/ccmEventRequest/?repage";
	}

	@RequiresPermissions("event:ccmEventRequestdeal:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmEventRequestdeal ccmEventRequestdeal, RedirectAttributes redirectAttributes) {
		ccmEventRequestdealService.delete(ccmEventRequestdeal);
		addMessage(redirectAttributes, "删除请求处理成功");
		return "redirect:" + Global.getAdminPath() + "/event/ccmEventRequestdeal/?repage";
	}

	@RequiresPermissions("event:ccmEventRequestdeal:viewRead")
	@RequestMapping(value = "readform")
	public String readform(CcmEventRequestdeal ccmEventRequestdeal, Model model) {
		model.addAttribute("ccmEventRequestdeal", ccmEventRequestdeal);
		return "ccm/event/ccmEventRequestdealFormOnlyRead";
	}

	@RequiresPermissions("event:ccmEventRequestdeal:view")
	@RequestMapping(value = "dealform")
	public String dealform(CcmEventRequestdeal ccmEventRequestdeal, Model model) {
		model.addAttribute("ccmEventRequestdeal", ccmEventRequestdeal);
		return "ccm/event/ccmEventRequestSDForm";
	}

	@RequiresPermissions("event:ccmEventRequestdeal:edit")
	@RequestMapping(value = "savedeal")
	public String savedeal(CcmEventRequestdeal ccmEventRequestdeal, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmEventRequestdeal)) {
			return dealform(ccmEventRequestdeal, model);
		}
		ccmEventRequestdealService.save(ccmEventRequestdeal);
		addMessage(redirectAttributes, "保存请求处理成功");
		return "redirect:" + Global.getAdminPath() + "/event/ccmEventRequest/?repage";
	}

}