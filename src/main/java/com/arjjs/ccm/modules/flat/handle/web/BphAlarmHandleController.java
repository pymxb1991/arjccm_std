/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.handle.web;

import java.io.IOException;
import java.util.Map;

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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.flat.handle.entity.BphAlarmHandle;
import com.arjjs.ccm.modules.flat.handle.entity.BphAlarmHandleReceive;
import com.arjjs.ccm.modules.flat.handle.service.BphAlarmHandleService;

import net.sf.json.JSONArray;

/**
 * 出警信息Controller
 * @author liu
 * @version 2018-11-22
 */
@Controller
@RequestMapping(value = "${adminPath}/handle/bphAlarmHandle")
public class BphAlarmHandleController extends BaseController {

	@Autowired
	private BphAlarmHandleService bphAlarmHandleService;
	
	@ModelAttribute
	public BphAlarmHandle get(@RequestParam(required=false) String id) {
		BphAlarmHandle entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bphAlarmHandleService.get(id);
		}
		if (entity == null){
			entity = new BphAlarmHandle();
		}
		return entity;
	}
	
	@RequiresPermissions("handle:bphAlarmHandle:view")
	@RequestMapping(value = {"list", ""})
	public String list(BphAlarmHandle bphAlarmHandle, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BphAlarmHandle> page = bphAlarmHandleService.findPage(new Page<BphAlarmHandle>(request, response), bphAlarmHandle); 
		model.addAttribute("page", page);
		return "flat/handle/bphAlarmHandleList";
	}

	@RequiresPermissions("handle:bphAlarmHandle:view")
	@RequestMapping(value = "form")
	public String form(BphAlarmHandle bphAlarmHandle, Model model) {
		model.addAttribute("bphAlarmHandle", bphAlarmHandle);
		return "flat/handle/bphAlarmHandleForm";
	}

	@RequiresPermissions("handle:bphAlarmHandle:edit")
	@RequestMapping(value = "save")
	public String save(BphAlarmHandle bphAlarmHandle, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bphAlarmHandle)){
			return form(bphAlarmHandle, model);
		}
		bphAlarmHandleService.save(bphAlarmHandle);
		addMessage(redirectAttributes, "保存出警信息成功");
		return "redirect:"+Global.getAdminPath()+"/handle/bphAlarmHandle/?repage";
	}
	
	@RequiresPermissions("handle:bphAlarmHandle:edit")
	@RequestMapping(value = "delete")
	public String delete(BphAlarmHandle bphAlarmHandle, RedirectAttributes redirectAttributes) {
		bphAlarmHandleService.delete(bphAlarmHandle);
		addMessage(redirectAttributes, "删除出警信息成功");
		return "redirect:"+Global.getAdminPath()+"/handle/bphAlarmHandle/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "saveHandle")
	public void saveHandle(HttpServletRequest request, HttpServletResponse response, BphAlarmHandleReceive bphAlarmHandleReceive) throws IOException {
		response.getWriter().print(bphAlarmHandleService.saveHandle(bphAlarmHandleReceive));
	}
	
	@RequestMapping(value = "fingListByAlarmId")
	public void fingListByAlarmId(HttpServletRequest request,HttpServletResponse response,String alarmId) throws IOException {
		response.getWriter().print(bphAlarmHandleService.fingListByAlarmId(alarmId));
	}
	
	@RequestMapping(value = "findHandleTimeCount")
	public String findHandleTimeCount(HttpServletRequest request, HttpServletResponse response) {
		JSONArray jsonData = JSONArray.fromObject(bphAlarmHandleService.findHandleTimeCount());
		request.setAttribute("jsonData", jsonData);
		return "flat/home/bphHandleAlarmTimeCountList";
	}
	
	@ResponseBody
	@RequestMapping(value = "planManagerDetails")
	public void planManagerDetails(HttpServletRequest request, HttpServletResponse response, String actionId, String alarmId) throws IOException {
		response.getWriter().print(bphAlarmHandleService.planManagerDetails(actionId,alarmId));
	}
	
	/**
	 * @desc 实时警情出警
	 * @param request
	 * @param response
	 * @param bphAlarmHandleReceive
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "sendAlarmInfo")
	public String sendAlarmInfo(HttpServletRequest request, HttpServletResponse response, BphAlarmHandleReceive bphAlarmHandleReceive) throws IOException {
//		response.getWriter().print(bphAlarmHandleService.sendAlarmInfo1(bphAlarmHandleReceive));
		return bphAlarmHandleService.sendAlarmInfo1(bphAlarmHandleReceive);
	}
}