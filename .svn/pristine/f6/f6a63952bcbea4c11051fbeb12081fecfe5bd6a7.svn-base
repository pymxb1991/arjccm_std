/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.worker.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.worker.entity.CcmWorkerSign;
import com.arjjs.ccm.modules.ccm.worker.service.CcmWorkerSignService;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;

/**
 * 社工签到Controller
 * @author yiqingxuan
 * @version 2019-06-17
 */
@Controller
@RequestMapping(value = "${adminPath}/worker/ccmWorkerSign")
public class CcmWorkerSignController extends BaseController {

	@Autowired
	private CcmWorkerSignService ccmWorkerSignService;

	@ModelAttribute
	public CcmWorkerSign get(@RequestParam(required=false) String id) {
		CcmWorkerSign entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmWorkerSignService.get(id);
		}
		if (entity == null){
			entity = new CcmWorkerSign();
		}
		return entity;
	}
	
	@RequiresPermissions("worker:ccmWorkerSign:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmWorkerSign ccmWorkerSign, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmWorkerSign> page = ccmWorkerSignService.findPage(new Page<CcmWorkerSign>(request, response), ccmWorkerSign); 
		model.addAttribute("page", page);
		return "ccm/worker/ccmWorkerSignList";
	}

	@RequiresPermissions("worker:ccmWorkerSign:view")
	@RequestMapping(value = "form")
	public String form(CcmWorkerSign ccmWorkerSign, Model model) {
		model.addAttribute("ccmWorkerSign", ccmWorkerSign);
		return "ccm/worker/ccmWorkerSignForm";
	}
	//签到
	@RequiresPermissions("worker:ccmWorkerSign:edit")
	@RequestMapping(value = "getform")
	public String getform(CcmWorkerSign ccmWorkerSign, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmWorkerSign)){
			return form(ccmWorkerSign, model);
		}
		User user = UserUtils.getUser();
		ccmWorkerSign.setUser(user);
		ccmWorkerSign.setContent("日常签到");
		ccmWorkerSign.setType("10");
		ccmWorkerSign.setStatus("10");
		ccmWorkerSign.setSignDate(new Date());
		ccmWorkerSignService.save(ccmWorkerSign);
		addMessage(redirectAttributes, "保存社工签到成功");
		return "redirect:"+Global.getAdminPath()+"/worker/ccmWorkerSign/?repage";
	}
	
	//签退
	@RequiresPermissions("worker:ccmWorkerSign:edit")
	@RequestMapping(value = "resform")
	public String resform(CcmWorkerSign ccmWorkerSign, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmWorkerSign)){
			return form(ccmWorkerSign, model);
		}
		User user = UserUtils.getUser();
		ccmWorkerSign.setUser(user);
		ccmWorkerSign.setContent("日常签退");
		ccmWorkerSign.setType("20");
		ccmWorkerSign.setStatus("10");
		ccmWorkerSign.setSignDate(new Date());
		ccmWorkerSignService.save(ccmWorkerSign);
		addMessage(redirectAttributes, "保存社工签退成功");
		return "redirect:"+Global.getAdminPath()+"/worker/ccmWorkerSign/?repage";
	}
	
	
	@RequiresPermissions("worker:ccmWorkerSign:edit")
	@RequestMapping(value = "save")
	public String save(CcmWorkerSign ccmWorkerSign, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmWorkerSign)){
			return form(ccmWorkerSign, model);
		}
		ccmWorkerSignService.save(ccmWorkerSign);
		addMessage(redirectAttributes, "保存社工签到成功");
		return "redirect:"+Global.getAdminPath()+"/worker/ccmWorkerSign/?repage";
	}
	
	@RequiresPermissions("worker:ccmWorkerSign:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmWorkerSign ccmWorkerSign, RedirectAttributes redirectAttributes) {
		ccmWorkerSignService.delete(ccmWorkerSign);
		addMessage(redirectAttributes, "删除社工签到成功");
		return "redirect:"+Global.getAdminPath()+"/worker/ccmWorkerSign/?repage";
	}


	//返回事件是否有位置地图
	@ResponseBody
	@RequestMapping(value = "getSignAreaPoint")
	public String getSignAreaPoint(String id) {
		// 返回对象结果
		CcmWorkerSign entity = ccmWorkerSignService.get(id);
		String areaPoint = "";
		if (entity != null && entity.getAreaPoint() != null && !"".equals(entity.getAreaPoint()) && entity.getAreaPoint().indexOf(",") != -1) {
			areaPoint = entity.getAreaPoint();
		}
		return areaPoint;
	}


	// 返回重点人员位置地图
	@RequestMapping(value = "getSignAreaPointMap", method = RequestMethod.GET)
	public String getSignAreaPointMap(Model model, String id) {
		CcmWorkerSign entity = ccmWorkerSignService.get(id);
		String areaPoint = "";
		if (entity != null && entity.getAreaPoint() != null && !"".equals(entity.getAreaPoint()) && entity.getAreaPoint().indexOf(",") != -1) {
			areaPoint = entity.getAreaPoint();
		}
		User user = UserUtils.get(entity.getUser().getId());
		CcmPeople ccmPeople = new CcmPeople();
		ccmPeople.setAreaPoint(entity.getAreaPoint());
		ccmPeople.setName(user.getName());
		model.addAttribute("areaPoint", areaPoint);
		model.addAttribute("ccmPeople", ccmPeople);
		return "modules/sys/map/mapIndex";
	}

}