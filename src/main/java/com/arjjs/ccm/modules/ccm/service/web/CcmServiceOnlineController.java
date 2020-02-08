/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.service.entity.CcmServiceOnline;
import com.arjjs.ccm.modules.ccm.service.service.CcmServiceOnlineService;
import com.arjjs.ccm.modules.ccm.sys.entity.CcmWorkReport;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.arjjs.ccm.tool.EchartType;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 在线办事Controller
 * @author fuxinshuang
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/service/ccmServiceOnline")
public class CcmServiceOnlineController extends BaseController {

	@Autowired
	private CcmServiceOnlineService ccmServiceOnlineService;
	
	@ModelAttribute
	public CcmServiceOnline get(@RequestParam(required=false) String id) {
		CcmServiceOnline entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmServiceOnlineService.get(id);
		}
		if (entity == null){
			entity = new CcmServiceOnline();
		}
		return entity;
	}
	
	@RequiresPermissions("service:ccmServiceOnline:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmServiceOnline ccmServiceOnline, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		ccmServiceOnline.setAreaId(UserUtils.getUser().getOffice().getArea().getId());
		Page<CcmServiceOnline> page = ccmServiceOnlineService.findPage(new Page<CcmServiceOnline>(request, response), ccmServiceOnline); 
		String etype = "1";
		model.addAttribute("page", page);
		model.addAttribute("etype", etype);
		return "ccm/service/ccmServiceOnlineList";
	}
	
	@RequiresPermissions("service:ccmServiceOnline:view")
	@RequestMapping(value = {"examinelist"})
	public String examinelist(CcmServiceOnline ccmServiceOnline, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		ccmServiceOnline.setAreaId(UserUtils.getUser().getOffice().getArea().getId());
		Page<CcmServiceOnline> page = ccmServiceOnlineService.findPage(new Page<CcmServiceOnline>(request, response), ccmServiceOnline); 
		String etype = "2";
		model.addAttribute("page", page);
		model.addAttribute("etype", etype);
		return "ccm/service/ccmServiceOnlineList";
	}
	
	@RequiresPermissions("service:ccmServiceOnline:view")
	@RequestMapping(value = {"jumplist"})
	public String jumplist(String etype, CcmServiceOnline ccmServiceOnline, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		ccmServiceOnline.setAreaId(UserUtils.getUser().getOffice().getArea().getId());
		Page<CcmServiceOnline> page = ccmServiceOnlineService.findPage(new Page<CcmServiceOnline>(request, response), ccmServiceOnline); 
		model.addAttribute("page", page);
		model.addAttribute("etype", etype);
		return "ccm/service/ccmServiceOnlineList";
	}
	/**
	 * 我的在线办事申请记录
	 */
	@RequiresPermissions("service:ccmServiceOnline:view")
	@RequestMapping(value = "self")
	public String selfList(CcmServiceOnline ccmServiceOnline, HttpServletRequest request, HttpServletResponse response, Model model) {
		ccmServiceOnline.setSelf(true);
		Page<CcmServiceOnline> page = ccmServiceOnlineService.findPage(new Page<CcmServiceOnline>(request, response), ccmServiceOnline); 
		model.addAttribute("page", page);
		return "ccm/service/ccmServiceOnlineSelfList";
	}
	@RequiresPermissions("service:ccmServiceOnline:view")
	@RequestMapping(value = "form")
	public String form(String etype, CcmServiceOnline ccmServiceOnline, Model model) {
		model.addAttribute("ccmServiceOnline", ccmServiceOnline);
		model.addAttribute("etype", etype);
		return "ccm/service/ccmServiceOnlineForm";
	}
	/**
	 * 我的在线办事申请记录
	 */
	@RequiresPermissions("service:ccmServiceOnline:view")
	@RequestMapping(value = "selfform")
	public String selfform(CcmServiceOnline ccmServiceOnline, Model model) {
		model.addAttribute("ccmServiceOnline", ccmServiceOnline);
		return "ccm/service/ccmServiceOnlineSelfForm";
	}
	@RequiresPermissions("service:ccmServiceOnline:edit")
	@RequestMapping(value = "save")
	public String save(String etype, CcmServiceOnline ccmServiceOnline, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmServiceOnline)){
			return form(etype, ccmServiceOnline, model);
		}
		ccmServiceOnlineService.save(ccmServiceOnline);
		addMessage(redirectAttributes, "保存在线办事成功");
		if("1".equals(etype)) {
			return "redirect:"+Global.getAdminPath()+"/service/ccmServiceOnline/list?repage";
		}else {
			return "redirect:"+Global.getAdminPath()+"/service/ccmServiceOnline/examinelist?repage";
		}
	}
	/**
	 * 我的在线办事申请保存
	 */
	@RequiresPermissions("service:ccmServiceOnline:edit")
	@RequestMapping(value = "selfsave")
	public String selfsave(CcmServiceOnline ccmServiceOnline, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmServiceOnline)){
			return selfform(ccmServiceOnline, model);
		}
		ccmServiceOnlineService.save(ccmServiceOnline);
		addMessage(redirectAttributes, "保存在线办事成功");
		return "redirect:"+Global.getAdminPath()+"/service/ccmServiceOnline/self/?repage";
	}
	
	@RequiresPermissions("service:ccmServiceOnline:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmServiceOnline ccmServiceOnline, RedirectAttributes redirectAttributes) {
		ccmServiceOnlineService.delete(ccmServiceOnline);
		addMessage(redirectAttributes, "删除在线办事成功");
		return "redirect:"+Global.getAdminPath()+"/service/ccmServiceOnline/?repage";
	}
	/**
	 * 在线办事事项分类分析
	 */
	@ResponseBody
	@RequestMapping(value = "getServiceType")
	public String getServiceType(Model model) {
		List<EchartType> listType = ccmServiceOnlineService.getServiceType(); //在线办事事项分类分析
		EchartType newEchartType = new EchartType();//非空判断
		newEchartType.setType("暂无数据");
		newEchartType.setValue("0");
		if(listType.size()==0){
			listType.add(newEchartType);
		}
		JsonConfig config = new JsonConfig();//PingJson
		config.setExcludes(new String[]{"typeO"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String listTypeString = JSONArray.fromObject(listType,config).toString(); //Json在线办事事项分类分析

		return listTypeString;
	}
	/**
	 * 在线办事-处理进度
	 */
	@ResponseBody
	@RequestMapping(value = "getServiceStatus")
	public String getServiceStatus(Model model) {
		List<EchartType> listStatus = ccmServiceOnlineService.getServiceStatus(); //在线办事-处理进度
		EchartType newEchartType = new EchartType();//非空判断
		newEchartType.setType("暂无数据");
		newEchartType.setValue("0");
		if(listStatus.size()==0){
			listStatus.add(newEchartType);
		}
		JsonConfig config = new JsonConfig();//PingJson
		config.setExcludes(new String[]{""});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String listStatusString = JSONArray.fromObject(listStatus,config).toString(); //Json在线办事事项分类分析
		return listStatusString;
	}

}