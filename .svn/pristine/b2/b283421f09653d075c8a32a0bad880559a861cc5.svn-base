/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.lane.web;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.arjjs.ccm.modules.ccm.bayonet.service.CcmCarBayonetService;
import com.arjjs.ccm.modules.ccm.lane.entity.CcmLane;
import com.arjjs.ccm.modules.ccm.lane.service.CcmLaneService;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 车道表实体类Controller
 * 
 * @author lgh
 * @version 2019-06-03
 */
@Controller
@RequestMapping(value = "${adminPath}/lane/ccmLane")
public class CcmLaneController extends BaseController {

	@Autowired
	private CcmLaneService ccmLaneService;

	@ModelAttribute
	public CcmLane get(@RequestParam(required = false) String id) {
		CcmLane entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmLaneService.get(id);
		}
		if (entity == null) {
			entity = new CcmLane();
		}
		return entity;
	}

	@RequiresPermissions("lane:ccmLane:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmLane ccmLane, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmLane> page = ccmLaneService.findPage(new Page<CcmLane>(request, response), ccmLane);
		model.addAttribute("page", page);
		return "ccm/lane/ccmLaneList";
	}

	@RequiresPermissions("lane:ccmLane:view")
	@RequestMapping(value = { "laneList" })
	public String laneList() {
		return "ccm/lane/laneList";
	}

	@RequiresPermissions("lane:ccmLane:view")
	@RequestMapping(value = "form")
	public String form(CcmLane ccmLane, Model model) {
		model.addAttribute("ccmLane", ccmLane);
		return "ccm/lane/ccmLaneForm";
	}

	@RequiresPermissions("lane:ccmLane:edit")
	@RequestMapping(value = "save")
	public void save(CcmLane ccmLane, Model model, RedirectAttributes redirectAttributes,
			HttpServletResponse response) {
		if (!beanValidator(model, ccmLane)) {
			// return form(ccmLane, model);
		}
		ccmLaneService.save(ccmLane);

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommUtil.openWinExpDiv(out, "保存车道成功");

	}

	@RequiresPermissions("lane:ccmLane:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmLane ccmLane, RedirectAttributes redirectAttributes) {
		ccmLaneService.delete(ccmLane);
		addMessage(redirectAttributes, "删除车道成功");
		return "redirect:" + Global.getAdminPath() + "/lane/ccmLane/?repage";
	}

}