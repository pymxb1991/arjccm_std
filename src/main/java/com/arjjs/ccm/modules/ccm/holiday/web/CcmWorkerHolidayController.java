/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.holiday.web;

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
import com.arjjs.ccm.modules.ccm.holiday.entity.CcmWorkerHoliday;
import com.arjjs.ccm.modules.ccm.holiday.service.CcmWorkerHolidayService;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 节假日管理Controller
 * @author yiqingxuan
 * @version 2019-06-18
 */
@Controller
@RequestMapping(value = "${adminPath}/holiday/ccmWorkerHoliday")
public class CcmWorkerHolidayController extends BaseController {

	@Autowired
	private CcmWorkerHolidayService ccmWorkerHolidayService;
	
	@ModelAttribute
	public CcmWorkerHoliday get(@RequestParam(required=false) String id) {
		CcmWorkerHoliday entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmWorkerHolidayService.get(id);
		}
		if (entity == null){
			entity = new CcmWorkerHoliday();
		}
		return entity;
	}
	
	@RequiresPermissions("holiday:ccmWorkerHoliday:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmWorkerHoliday ccmWorkerHoliday, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmWorkerHoliday> page = ccmWorkerHolidayService.findPage(new Page<CcmWorkerHoliday>(request, response), ccmWorkerHoliday); 
		model.addAttribute("page", page);
		return "ccm/holiday/ccmWorkerHolidayList";
	}

	@RequiresPermissions("holiday:ccmWorkerHoliday:view")
	@RequestMapping(value = "form")
	public String form(CcmWorkerHoliday ccmWorkerHoliday, Model model) {
		model.addAttribute("ccmWorkerHoliday", ccmWorkerHoliday);
		return "ccm/holiday/ccmWorkerHolidayForm";
	}

	@RequiresPermissions("holiday:ccmWorkerHoliday:edit")
	@RequestMapping(value = "save")
	public void save(CcmWorkerHoliday ccmWorkerHoliday, Model model, HttpServletResponse response) {
		if (!beanValidator(model, ccmWorkerHoliday)){
//			return form(ccmWorkerHoliday, model);
		}
		ccmWorkerHolidayService.save(ccmWorkerHoliday);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			CommUtil.openWinExpDiv(out, "保存外出登记成功");
		} catch (IOException e) {
			e.printStackTrace();
		}
//		return "redirect:"+Global.getAdminPath()+"/holiday/ccmWorkerHoliday/?repage";
	}
	
	@RequiresPermissions("holiday:ccmWorkerHoliday:edit")
	@RequestMapping(value = "update")
	public String update(CcmWorkerHoliday ccmWorkerHoliday, Model model, HttpServletResponse response) {
		if (!beanValidator(model, ccmWorkerHoliday)){
			return form(ccmWorkerHoliday, model);
		}
		ccmWorkerHolidayService.save(ccmWorkerHoliday);
		return "redirect:"+Global.getAdminPath()+"/holiday/ccmWorkerHoliday/?repage";
	}
	
	@RequiresPermissions("holiday:ccmWorkerHoliday:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmWorkerHoliday ccmWorkerHoliday, RedirectAttributes redirectAttributes) {
		ccmWorkerHolidayService.delete(ccmWorkerHoliday);
		addMessage(redirectAttributes, "删除节假日管理成功");
		return "redirect:"+Global.getAdminPath()+"/holiday/ccmWorkerHoliday/?repage";
	}

}