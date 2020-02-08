/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.web;

import java.util.List;

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
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolPlan;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolPoint;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolPointService;

/**
 * 巡逻点位Controller
 * 
 * @author arj
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/patrol/ccmPatrolPoint")
public class CcmPatrolPointController extends BaseController {

	@Autowired
	private CcmPatrolPointService ccmPatrolPointService;

	@ModelAttribute
	public CcmPatrolPoint get(@RequestParam(required = false) String id) {
		CcmPatrolPoint entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmPatrolPointService.get(id);
		}
		if (entity == null) {
			entity = new CcmPatrolPoint();
		}
		return entity;
	}

	@RequiresPermissions("patrol:ccmPatrolPoint:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmPatrolPoint ccmPatrolPoint, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CcmPatrolPoint> page = ccmPatrolPointService.findPage(new Page<CcmPatrolPoint>(request, response),
				ccmPatrolPoint);
		model.addAttribute("page", page);
		return "ccm/patrol/ccmPatrolPointList";
	}

	@RequiresPermissions("patrol:ccmPatrolPoint:view")
	@RequestMapping(value = "form")
	public String form(CcmPatrolPoint ccmPatrolPoint, Model model) {
		model.addAttribute("ccmPatrolPoint", ccmPatrolPoint);
		return "ccm/patrol/ccmPatrolPointForm";
	}

	@RequiresPermissions("patrol:ccmPatrolPoint:edit")
	@RequestMapping(value = "save")
	public String save(CcmPatrolPoint ccmPatrolPoint, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmPatrolPoint)) {
			return form(ccmPatrolPoint, model);
		}
		ccmPatrolPointService.save(ccmPatrolPoint);
		addMessage(redirectAttributes, "保存巡逻点位成功");
		return "redirect:" + Global.getAdminPath() + "/patrol/ccmPatrolPoint/?repage";
	}

	@RequiresPermissions("patrol:ccmPatrolPoint:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPatrolPoint ccmPatrolPoint, RedirectAttributes redirectAttributes) {
		ccmPatrolPointService.delete(ccmPatrolPoint);
		addMessage(redirectAttributes, "删除巡逻点位成功");
		return "redirect:" + Global.getAdminPath() + "/patrol/ccmPatrolPoint/?repage";
	}

	// 地图添加
	@RequiresPermissions("patrol:ccmPatrolPoint:view")
	@RequestMapping(value = "mapadd")
	public String MapAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/modules/mapping/patrol/pointAdd";
	}

	// 计划点位的名称
	@RequestMapping(value = "pointNames")
	public String pointNames(@RequestParam(required = false) String type,
			@RequestParam(required = false) String purposeType, Model model) {
		List<CcmPatrolPoint> ccmPatrolPoints = ccmPatrolPointService.findList(new CcmPatrolPoint());
		model.addAttribute("point", ccmPatrolPoints);
		model.addAttribute("purposeType", purposeType);
		return "/modules/mapping/patrol/pointtype";
	}
}