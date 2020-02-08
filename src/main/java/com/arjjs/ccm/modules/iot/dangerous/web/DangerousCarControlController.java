/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.dangerous.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.fence.entity.CcmElectronicFence;
import com.arjjs.ccm.modules.ccm.fence.service.CcmElectronicFenceService;
import com.arjjs.ccm.modules.iot.dangerous.entity.DangerousCarControl;
import com.arjjs.ccm.modules.iot.dangerous.service.DangerousCarControlService;
import com.arjjs.ccm.tool.CommUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 危化品车辆布控实体类Controller
 * 
 * @author lgh
 * @version 2019-06-05
 */
@Controller
@RequestMapping(value = "${adminPath}/dangerous/dangerousCarControl")
public class DangerousCarControlController extends BaseController {

	@Autowired
	private DangerousCarControlService dangerousCarControlService;

	@Autowired
	private CcmElectronicFenceService ccmElectronicFenceService;

	@ModelAttribute
	public DangerousCarControl get(@RequestParam(required = false) String id) {
		DangerousCarControl entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = dangerousCarControlService.get(id);
		}
		if (entity == null) {
			entity = new DangerousCarControl();
		}
		return entity;
	}

	@RequiresPermissions("dangerous:dangerousCarControl:view")
	@RequestMapping(value = { "list", "" })
	public String list(DangerousCarControl dangerousCarControl, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<DangerousCarControl> page = dangerousCarControlService
				.findPage(new Page<DangerousCarControl>(request, response), dangerousCarControl);
		model.addAttribute("page", page);
		return "iot/dangerous/dangerousCarControlList";
	}

	@RequiresPermissions("dangerous:dangerousCarControl:view")
	@RequestMapping(value = "form")
	public String form(DangerousCarControl dangerousCarControl, Model model) {
		List<CcmElectronicFence> fenceList = ccmElectronicFenceService.findList(new CcmElectronicFence());
		model.addAttribute("fenceList",fenceList);
		model.addAttribute("dangerousCarControl", dangerousCarControl);
		return "iot/dangerous/dangerousCarControlForm";
	}

	@RequiresPermissions("dangerous:dangerousCarControl:edit")
	@RequestMapping(value = "save")
	public void save(DangerousCarControl dangerousCarControl, HttpServletResponse response) {
		/*
		 * if (!beanValidator(model, dangerousCarControl)){ // return
		 * form(dangerousCarControl, model); }
		 */
		dangerousCarControlService.save(dangerousCarControl);

		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommUtil.openWinExpDiv(out, "保存危化品车辆布控记录成功");

	}

	@RequiresPermissions("dangerous:dangerousCarControl:edit")
	@RequestMapping(value = "delete")
	public String delete(DangerousCarControl dangerousCarControl, RedirectAttributes redirectAttributes) {
		dangerousCarControlService.delete(dangerousCarControl);
		addMessage(redirectAttributes, "删除危化品车辆布控记录成功");
		return "redirect:" + Global.getAdminPath() + "/dangerous/dangerousCarControl/?repage";
	}

}