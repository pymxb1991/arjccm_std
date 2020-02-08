/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.work.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.arjjs.ccm.modules.ccm.work.entity.CcmWorkTiming;
import com.arjjs.ccm.modules.ccm.work.service.CcmWorkTimingService;
import com.arjjs.ccm.tool.CommUtil;

/**
 * 定时提醒Controller
 * @author liang
 * @version 2018-08-02
 */
@Controller
@RequestMapping(value = "${adminPath}/work/ccmWorkTiming")
public class CcmWorkTimingController extends BaseController {

	@Autowired
	private CcmWorkTimingService ccmWorkTimingService;
	
	@ModelAttribute
	public CcmWorkTiming get(@RequestParam(required=false) String id) {
		CcmWorkTiming entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmWorkTimingService.get(id);
		}
		if (entity == null){
			entity = new CcmWorkTiming();
		}
		return entity;
	}
	
	//@RequiresPermissions("work:ccmWorkTiming:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmWorkTiming ccmWorkTiming, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmWorkTiming> page = ccmWorkTimingService.findPage(new Page<CcmWorkTiming>(request, response), ccmWorkTiming); 
		model.addAttribute("page", page);
		return "ccm/work/ccmWorkTimingList";
	}

	//@RequiresPermissions("work:ccmWorkTiming:view")
	@RequestMapping(value = "form")
	public String form(CcmWorkTiming ccmWorkTiming, Model model) {
		model.addAttribute("ccmWorkTiming", ccmWorkTiming);
		return "ccm/work/ccmWorkTimingForm";
	}

	//@RequiresPermissions("work:ccmWorkTiming:edit")
	@RequestMapping(value = "save")
	public String save(CcmWorkTiming ccmWorkTiming, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmWorkTiming)){
			return form(ccmWorkTiming, model);
		}
		ccmWorkTimingService.save(ccmWorkTiming);
		addMessage(redirectAttributes, "保存定时提醒成功");
		return "redirect:"+Global.getAdminPath()+"/work/ccmWorkTiming/?repage";
	}
	


	/**
	 * @see 用于定时提醒任务页面 （所有处理界面可以进入）
	 * @param CcmWorkTiming 事件处理原型
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws IOException 
	 */
	//@RequiresPermissions("work:ccmWorkTiming:edit")
	@RequestMapping(value = "saveTiming")
	public void saveTiming(HttpServletRequest request, HttpServletResponse response, CcmWorkTiming ccmWorkTiming, Model model, RedirectAttributes redirectAttributes) throws IOException {
		if (!beanValidator(model, ccmWorkTiming)){
//			return form(ccmWorkTiming, model);
		}
		ccmWorkTimingService.save(ccmWorkTiming);
		
		addMessage(redirectAttributes, "定时提醒建立成功");
		PrintWriter out = response.getWriter();
		CommUtil.openWinExpDiv(out, "定时提醒建立成功");
	}
	
	
	//@RequiresPermissions("work:ccmWorkTiming:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmWorkTiming ccmWorkTiming, RedirectAttributes redirectAttributes) {
		ccmWorkTimingService.delete(ccmWorkTiming);
		addMessage(redirectAttributes, "删除定时提醒成功");
		return "redirect:"+Global.getAdminPath()+"/work/ccmWorkTiming/?repage";
	}

}