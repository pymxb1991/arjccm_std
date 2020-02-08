/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.duty.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.duty.entity.CcmWorkerDuty;
import com.arjjs.ccm.modules.ccm.duty.service.CcmWorkerDutyService;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 社工职责Controller
 * @author yiqingxuan
 * @version 2019-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/duty/ccmWorkerDuty")
public class CcmWorkerDutyController extends BaseController {

	@Autowired
	private CcmWorkerDutyService ccmWorkerDutyService;
	
	@ModelAttribute
	public CcmWorkerDuty get(@RequestParam(required=false) String id) {
		CcmWorkerDuty entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmWorkerDutyService.get(id);
		}
		if (entity == null){
			entity = new CcmWorkerDuty();
		}
		return entity;
	}
	
	@RequiresPermissions("duty:ccmWorkerDuty:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmWorkerDuty ccmWorkerDuty, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmWorkerDuty> page = ccmWorkerDutyService.findPage(new Page<CcmWorkerDuty>(request, response), ccmWorkerDuty); 
		model.addAttribute("page", page);
		return "ccm/duty/ccmWorkerDutyList";
	}

	@RequiresPermissions("duty:ccmWorkerDuty:view")
	@RequestMapping(value = "form")
	public String form(CcmWorkerDuty ccmWorkerDuty, Model model) {
		model.addAttribute("ccmWorkerDuty", ccmWorkerDuty);
		return "ccm/duty/ccmWorkerDutyForm";
	}

	@RequiresPermissions("duty:ccmWorkerDuty:edit")
	@RequestMapping(value = "save")
	public void save(CcmWorkerDuty ccmWorkerDuty, Model model, HttpServletResponse response) {
		if (!beanValidator(model, ccmWorkerDuty)){
//			return form(ccmWorkerDuty, model);
		}
		ccmWorkerDuty.setStatus("20");
		ccmWorkerDutyService.save(ccmWorkerDuty);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			CommUtil.openWinExpDiv(out, "社工职责登记成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequiresPermissions("duty:ccmWorkerDuty:edit")
	@RequestMapping(value = "update")
	public void update(CcmWorkerDuty ccmWorkerDuty, Model model, HttpServletResponse response) {
		if (!beanValidator(model, ccmWorkerDuty)){
			//return form(ccmWorkerDuty, model);
		}
		ccmWorkerDuty.setStatus("20");
		ccmWorkerDutyService.save(ccmWorkerDuty);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			CommUtil.openWinExpDiv(out, "社工职责修改成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return "redirect:"+Global.getAdminPath()+"/duty/ccmWorkerDuty/?repage";
	}
	
	
	@RequiresPermissions("duty:ccmWorkerDuty:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmWorkerDuty ccmWorkerDuty, RedirectAttributes redirectAttributes) {
		ccmWorkerDutyService.delete(ccmWorkerDuty);
		addMessage(redirectAttributes, "删除社工职责成功");
		return "redirect:"+Global.getAdminPath()+"/duty/ccmWorkerDuty/?repage";
	}
	
	/**
	 * 
	 * @Title: updatestatus
	 * @Description : 发布社工职责
	 * @author：
	 * @date： 2019年6月20日下午3:17:47
	 * @param ccmWorkerDuty
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("duty:ccmWorkerDuty:edit")
	@RequestMapping(value = "updatestatus")
	public String updatestatus(CcmWorkerDuty ccmWorkerDuty, Model model, RedirectAttributes redirectAttributes) {
		ccmWorkerDuty.setStatus("20");
		ccmWorkerDutyService.save(ccmWorkerDuty);
		addMessage(redirectAttributes, "发布社工职责成功");
		return "redirect:"+Global.getAdminPath()+"/duty/ccmWorkerDuty/?repage";
	}
	

}