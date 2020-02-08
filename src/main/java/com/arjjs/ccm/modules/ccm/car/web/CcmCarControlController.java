/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.car.web;

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
import com.arjjs.ccm.modules.ccm.car.entity.CcmCarControl;
import com.arjjs.ccm.modules.ccm.car.service.CcmCarControlService;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 车辆布控实体类Controller
 * 
 * @author lgh
 * @version 2019-06-03
 */
@Controller
@RequestMapping(value = "${adminPath}/car/ccmCarControl")
public class CcmCarControlController extends BaseController {

	@Autowired
	private CcmCarControlService ccmCarControlService;

	@ModelAttribute
	public CcmCarControl get(@RequestParam(required = false) String id) {
		CcmCarControl entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmCarControlService.get(id);
		}
		if (entity == null) {
			entity = new CcmCarControl();
		}
		return entity;
	}

	@RequiresPermissions("car:ccmCarControl:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmCarControl ccmCarControl, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CcmCarControl> page = ccmCarControlService.findPage(new Page<CcmCarControl>(request, response),
				ccmCarControl);
		model.addAttribute("page", page);
		return "ccm/car/ccmCarControlList";
	}

	@RequiresPermissions("car:ccmCarControl:view")
	@RequestMapping(value = "form")
	public String form(CcmCarControl ccmCarControl, Model model) {
		model.addAttribute("ccmCarControl", ccmCarControl);
		return "ccm/car/ccmCarControlForm";
	}

	@RequiresPermissions("car:ccmCarControl:edit")
	@RequestMapping(value = "save")
	public void save(CcmCarControl ccmCarControl, Model model, RedirectAttributes redirectAttributes,
			HttpServletResponse response) {
		if (!beanValidator(model, ccmCarControl)) {
			// return form(ccmCarControl, model);
		}
		ccmCarControlService.save(ccmCarControl);

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommUtil.openWinExpDiv(out, "保存车辆布控记录成功");

	}

	@RequiresPermissions("car:ccmCarControl:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmCarControl ccmCarControl, RedirectAttributes redirectAttributes) {
		ccmCarControlService.delete(ccmCarControl);
		addMessage(redirectAttributes, "删除车辆布控记录成功");
		return "redirect:" + Global.getAdminPath() + "/car/ccmCarControl/?repage";
	}

	//得到车辆布控数量
	@ResponseBody
	@RequestMapping(value = "getCount")
	public String getCount(CcmCarControl ccmCarControl) {
		int count = ccmCarControlService.getCount(ccmCarControl);
		return String.valueOf(count);
	}
}