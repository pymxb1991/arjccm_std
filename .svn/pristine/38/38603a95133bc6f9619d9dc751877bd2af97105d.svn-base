/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.report.web;

import java.util.List;
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
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.risk.report.entity.RiskEventGreat;
import com.arjjs.ccm.modules.risk.report.entity.RiskReport;
import com.arjjs.ccm.modules.risk.report.service.RiskReportService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 事项评估报告Controller
 * @author liang
 * @version 2018-04-02
 */
@Controller
@RequestMapping(value = "${adminPath}/report/riskReport")
public class RiskReportController extends BaseController {

	@Autowired
	private RiskReportService riskReportService;
	
	@ModelAttribute
	public RiskReport get(@RequestParam(required=false) String id) {
		RiskReport entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = riskReportService.get(id);
		}
		if (entity == null){
			entity = new RiskReport();
		}
		return entity;
	}
	
	//入库
	@RequiresPermissions("report:riskReport:edit")
	@RequestMapping(value = "saveList")
	public String saveList(RiskReport riskReport, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, riskReport)){
			return form(riskReport, model);
		}
		riskReport = riskReportService.get(riskReport);
		riskReport.setIsReserve("1");
		riskReportService.save(riskReport);
		addMessage(redirectAttributes, "入库成功");
		return "redirect:"+Global.getAdminPath()+"/report/riskReport/?repage";
	}
	//入库查询
	@RequiresPermissions("report:riskReport:view")
	@RequestMapping(value = "listDatabase")
	public String listDatabase(RiskReport riskReport, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RiskReport> page = riskReportService.findListDatabasePage(new Page<RiskReport>(request, response), riskReport); 
		model.addAttribute("page", page);
		return "risk/report/riskReportListDatabase";
	}
	//入库表单
	@RequiresPermissions("report:riskReport:view")
	@RequestMapping(value = "formDatabase")
	public String formDatabase(RiskReport riskReport, Model model) {
		model.addAttribute("riskReport", riskReport);
		return "risk/report/riskReportFormDatabase";
	}
	//入库删除
	@RequiresPermissions("report:riskReport:edit")
	@RequestMapping(value = "deleteDatabase")
	public String deleteDatabase(RiskReport riskReport, RedirectAttributes redirectAttributes) {
		riskReport = riskReportService.get(riskReport);
		riskReport.setIsReserve("0");
		riskReportService.save(riskReport);
		addMessage(redirectAttributes, "删除库的事项评估报告成功");
		return "redirect:"+Global.getAdminPath()+"/report/riskReport/listDatabase?repage";
	}
	
	
	
	
	
	//
	@RequiresPermissions("report:riskReport:view")
	@RequestMapping(value = {"list", ""})
	public String list(RiskReport riskReport, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RiskReport> page = riskReportService.findPage(new Page<RiskReport>(request, response), riskReport); 
		model.addAttribute("page", page);
		return "risk/report/riskReportList";
	}

	@RequiresPermissions("report:riskReport:view")
	@RequestMapping(value = "form")
	public String form(RiskReport riskReport, Model model) {
		model.addAttribute("riskReport", riskReport);
		return "risk/report/riskReportForm";
	}

	@RequiresPermissions("report:riskReport:edit")
	@RequestMapping(value = "save")
	public String save(RiskReport riskReport, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, riskReport)){
			return form(riskReport, model);
		}
		if(riskReport.getIsReserve()==null){
			riskReport.setIsReserve("0");
		}
		riskReportService.save(riskReport);
		addMessage(redirectAttributes, "保存事项评估报告成功");
		return "redirect:"+Global.getAdminPath()+"/report/riskReport/?repage";
	}
	
	@RequiresPermissions("report:riskReport:edit")
	@RequestMapping(value = "delete")
	public String delete(RiskReport riskReport, RedirectAttributes redirectAttributes) {
		riskReportService.delete(riskReport);
		addMessage(redirectAttributes, "删除事项评估报告成功");
		return "redirect:"+Global.getAdminPath()+"/report/riskReport/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<RiskReport> list = riskReportService.findList(new RiskReport());
			for (int i = 0; i < list.size(); i++) {
				RiskReport r = list.get(i);
				if ((StringUtils.isBlank(extId) || (extId != null && !extId.equals(r.getId()) ) )) {
					Map<String, Object> map = Maps.newHashMap();
					map.put("id", r.getId());
					map.put("pId", "0");
					map.put("name", r.getFileName());
					mapList.add(map);
				}
			}
		
		return mapList;
	}
	
	
	
}