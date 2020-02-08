/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.web;

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
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolRespoint;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolRespointService;

/**
 * 巡逻点位结果Controller
 * 
 * @author arj
 * @version 2018-03-16
 */
@Controller
@RequestMapping(value = "${adminPath}/patrol/ccmPatrolRespoint")
public class CcmPatrolRespointController extends BaseController {

	@Autowired
	private CcmPatrolRespointService ccmPatrolRespointService;

	@ModelAttribute
	public CcmPatrolRespoint get(@RequestParam(required = false) String id) {
		CcmPatrolRespoint entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmPatrolRespointService.get(id);
		}
		if (entity == null) {
			entity = new CcmPatrolRespoint();
		}
		return entity;
	}

	@RequiresPermissions("patrol:ccmPatrolRespoint:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmPatrolRespoint ccmPatrolRespoint, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CcmPatrolRespoint> page = ccmPatrolRespointService.findPage(new Page<CcmPatrolRespoint>(request, response),
				ccmPatrolRespoint);
		model.addAttribute("page", page);
		return "ccm/patrol/ccmPatrolRespointList";
	}

	@RequiresPermissions("patrol:ccmPatrolRespoint:view")
	@RequestMapping(value = "form")
	public String form(CcmPatrolRespoint ccmPatrolRespoint, Model model) {
		model.addAttribute("ccmPatrolRespoint", ccmPatrolRespoint);
		return "ccm/patrol/ccmPatrolRespointForm";
	}

	@RequiresPermissions("patrol:ccmPatrolRespoint:edit")
	@RequestMapping(value = "save")
	public String save(CcmPatrolRespoint ccmPatrolRespoint, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmPatrolRespoint)) {
			return form(ccmPatrolRespoint, model);
		}
		// 判断该点位是否已经添加过。
		if (StringUtils.isBlank(ccmPatrolRespoint.getId()) && ccmPatrolRespointService.exitCheck(ccmPatrolRespoint)) {
			addMessage(model, "已添加过相关的结果点位信息。");
			return form(ccmPatrolRespoint, model);
		}
		ccmPatrolRespointService.save(ccmPatrolRespoint);
		addMessage(redirectAttributes, "保存巡逻点位结果成功");
		return "redirect:" + Global.getAdminPath() + "/patrol/ccmPatrolRespoint/?repage";
	}

	@RequiresPermissions("patrol:ccmPatrolRespoint:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPatrolRespoint ccmPatrolRespoint, RedirectAttributes redirectAttributes) {
		ccmPatrolRespointService.delete(ccmPatrolRespoint);
		addMessage(redirectAttributes, "删除巡逻点位结果成功");
		return "redirect:" + Global.getAdminPath() + "/patrol/ccmPatrolRespoint/?repage";
	}

}