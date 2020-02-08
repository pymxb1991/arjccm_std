/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.scheme.web;

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

import com.alibaba.druid.sql.visitor.functions.Isnull;
import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.kpi.scheme.entity.KpiScheme;
import com.arjjs.ccm.modules.kpi.scheme.entity.KpiSchemeKpi;
import com.arjjs.ccm.modules.kpi.scheme.entity.KpiSchemeSubjectivity;
import com.arjjs.ccm.modules.kpi.scheme.service.KpiSchemeKpiService;
import com.arjjs.ccm.modules.kpi.scheme.service.KpiSchemeService;
import com.arjjs.ccm.modules.kpi.scheme.service.KpiSchemeSubjectivityService;

/**
 * 绩效考评KPIController
 * @author liang
 * @version 2018-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/scheme/kpiSchemeKpi")
public class KpiSchemeKpiController extends BaseController {

	@Autowired
	private KpiSchemeKpiService kpiSchemeKpiService;
	@Autowired
	private KpiSchemeService kpiSchemeService;
	@Autowired
	private KpiSchemeSubjectivityService kpiSchemeSubjectivityService;
	
	
	@ModelAttribute
	public KpiSchemeKpi get(@RequestParam(required=false) String id) {
		KpiSchemeKpi entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = kpiSchemeKpiService.get(id);
		}
		if (entity == null){
			entity = new KpiSchemeKpi();
		}
		return entity;
	}
	//跳转KPI页面
	@RequiresPermissions("scheme:kpiSchemeKpi:view")
	@RequestMapping(value = "formDap")
	public String formDap(KpiSchemeKpi kpiSchemeKpi, Model model,String sI) {
		String schemeId = sI;//父id
		kpiSchemeKpi = kpiSchemeKpiService.get(kpiSchemeKpi);//id获取实体
		if(kpiSchemeKpi==null){
			kpiSchemeKpi = new KpiSchemeKpi();
		}
		if(sI!=null){
			kpiSchemeKpi.setSchemeId(schemeId);
		}
		KpiScheme kpiScheme = kpiSchemeService.get(kpiSchemeKpi.getSchemeId());//获取父方案
		if(kpiScheme==null){
			kpiScheme = new KpiScheme();
		}
		//获取绩效主观list
		KpiSchemeSubjectivity kpiSchemeSubjectivity = new KpiSchemeSubjectivity();
		kpiSchemeSubjectivity.setKpiId(kpiSchemeKpi.getId());
		List<KpiSchemeSubjectivity> list = kpiSchemeSubjectivityService.findList(kpiSchemeSubjectivity);
		
		model.addAttribute("list", list);
		model.addAttribute("kpiScheme", kpiScheme);
		model.addAttribute("kpiSchemeKpi", kpiSchemeKpi);
		return "kpi/scheme/kpiSchemeKpiFormDap";
	}
	
	
	
	
	
	
	
	
	
	//
	@RequiresPermissions("scheme:kpiSchemeKpi:view")
	@RequestMapping(value = {"list", ""})
	public String list(KpiSchemeKpi kpiSchemeKpi, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<KpiSchemeKpi> page = kpiSchemeKpiService.findPage(new Page<KpiSchemeKpi>(request, response), kpiSchemeKpi); 
		model.addAttribute("page", page);
		return "kpi/scheme/kpiSchemeKpiList";
	}

	@RequiresPermissions("scheme:kpiSchemeKpi:view")
	@RequestMapping(value = "form")
	public String form(KpiSchemeKpi kpiSchemeKpi, Model model) {
		model.addAttribute("kpiSchemeKpi", kpiSchemeKpi);
		return "kpi/scheme/kpiSchemeKpiForm";
	}

	@RequiresPermissions("scheme:kpiSchemeKpi:edit")
	@RequestMapping(value = "save")
	public void save(KpiSchemeKpi kpiSchemeKpi, Model model, RedirectAttributes redirectAttributes, HttpServletResponse response) throws IOException {
		kpiSchemeKpiService.save(kpiSchemeKpi);
		addMessage(redirectAttributes, "保存绩效考评KPI成功");
		//return "redirect:"+Global.getAdminPath()+"/scheme/kpiSchemeKpi/?repage";
		//return "redirect:"+Global.getAdminPath()+"/scheme/kpiScheme/treeList?repage";
		//return "kpi/scheme/kpiSchemeKpiFormDap";
		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>parent.location.reload();</script>");//跳转上层iframe
		out.println("<script language='javascript'>top.$.jBox.tip('保存绩效考评KPI成功 ');</script>");//提示
	}
	
	
	
	@RequiresPermissions("scheme:kpiSchemeKpi:edit")
	@RequestMapping(value = "delete")
	public void delete(KpiSchemeKpi kpiSchemeKpi, RedirectAttributes redirectAttributes, HttpServletResponse response) throws IOException {
		KpiSchemeSubjectivity kpiSchemeSubjectivity = new KpiSchemeSubjectivity();
		kpiSchemeSubjectivity.setKpiId(kpiSchemeKpi.getId());
		List<KpiSchemeSubjectivity> list = kpiSchemeSubjectivityService.findList(kpiSchemeSubjectivity);
		for(KpiSchemeSubjectivity l:list){
			kpiSchemeSubjectivityService.delete(l);//删除绩效主观评分
		}
		kpiSchemeKpiService.delete(kpiSchemeKpi);//删除KPI
		addMessage(redirectAttributes, "删除绩效考评KPI成功");
		PrintWriter out = response.getWriter();
		out.println("<script language='javascript'>parent.location.reload();</script>");//跳转上层iframe
		out.println("<script language='javascript'>top.$.jBox.tip('删除绩效考评KPI成功 ');</script>");//提示
	}

}