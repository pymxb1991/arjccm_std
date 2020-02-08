/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.place.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.fence.entity.CcmElectronicFence;
import com.arjjs.ccm.modules.ccm.fence.service.CcmElectronicFenceService;
import com.arjjs.ccm.modules.iot.place.entity.CcmPlaceControl;
import com.arjjs.ccm.modules.iot.place.service.CcmPlaceControlService;
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
import java.io.PrintWriter;
import java.util.List;

/**
 * 场所布控Controller
 * @author yiqingxuan
 * @version 2019-07-25
 */
@Controller
@RequestMapping(value = "${adminPath}/place/ccmPlaceControl")
public class CcmPlaceControlController extends BaseController {

	@Autowired
	private CcmPlaceControlService ccmPlaceControlService;

	@Autowired
	private CcmElectronicFenceService ccmElectronicFenceService;

	@ModelAttribute
	public CcmPlaceControl get(@RequestParam(required=false) String id) {
		CcmPlaceControl entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmPlaceControlService.get(id);
		}
		if (entity == null){
			entity = new CcmPlaceControl();
		}
		return entity;
	}
	
	@RequiresPermissions("place:ccmPlaceControl:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmPlaceControl ccmPlaceControl, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmPlaceControl> page = ccmPlaceControlService.findPage(new Page<CcmPlaceControl>(request, response), ccmPlaceControl); 
		model.addAttribute("page", page);
		return "iot/place/ccmPlaceControlList";
	}

	@RequiresPermissions("place:ccmPlaceControl:view")
	@RequestMapping(value = "form")
	public String form(CcmPlaceControl ccmPlaceControl, Model model) {
		List<CcmElectronicFence> fenceList = ccmElectronicFenceService.findList(new CcmElectronicFence());
		model.addAttribute("fenceList",fenceList);
		model.addAttribute("ccmPlaceControl", ccmPlaceControl);
		return "iot/place/ccmPlaceControlForm";
	}

	@RequiresPermissions("place:ccmPlaceControl:edit")
	@RequestMapping(value = "save")
	public void save(CcmPlaceControl ccmPlaceControl, Model model, RedirectAttributes redirectAttributes
			,HttpServletResponse response) throws Exception {
		/*
		 * if (!beanValidator(model, ccmPlaceControl)){ return form(ccmPlaceControl,
		 * model); }
		 */
		ccmPlaceControlService.save(ccmPlaceControl);
		/*
		 * addMessage(redirectAttributes, "保存场所布控成功"); return
		 * "redirect:"+Global.getAdminPath()+"/place/ccmPlaceControl/?repage";
		 */
		PrintWriter out = response.getWriter(); 
		CommUtil.openWinExpDiv(out, "保存场所信息成功");
	}
	
	@RequiresPermissions("place:ccmPlaceControl:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPlaceControl ccmPlaceControl, RedirectAttributes redirectAttributes) {
		ccmPlaceControlService.delete(ccmPlaceControl);
		addMessage(redirectAttributes, "删除场所布控成功");
		return "redirect:"+Global.getAdminPath()+"/place/ccmPlaceControl/?repage";
	}

}