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
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogTail;
import com.arjjs.ccm.modules.ccm.log.service.CcmLogTailService;
import com.arjjs.ccm.modules.ccm.tree.entity.CcmTree;
import com.arjjs.ccm.modules.risk.report.entity.RiskEventGreat;
import com.arjjs.ccm.modules.risk.report.entity.RiskIncident;
import com.arjjs.ccm.modules.risk.report.service.RiskIncidentService;
import com.arjjs.ccm.tool.EchartType;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 风险事件管理Controller
 * @author liang
 * @version 2018-03-31
 */
@Controller
@RequestMapping(value = "${adminPath}/report/riskIncident")
public class RiskIncidentController extends BaseController {

	@Autowired
	private RiskIncidentService riskIncidentService;
	@Autowired
	private CcmLogTailService ccmLogTailService;
	@ModelAttribute
	public RiskIncident get(@RequestParam(required=false) String id) {
		RiskIncident entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = riskIncidentService.get(id);
		}
		if (entity == null){
			entity = new RiskIncident();
		}
		return entity;
	}
	//风险事件数据分析
	@RequiresPermissions("report:riskIncident:view")
	@RequestMapping(value = "map")
	public String map(RiskIncident riskIncident, HttpServletRequest request, HttpServletResponse response, Model model) {
		EchartType echartType = new EchartType();
		echartType.setType("暂无数据");
		echartType.setValue("0");
		
		List<EchartType> list1 = riskIncidentService.findImportance(riskIncident); //查询风险事件重要程度统计
		if(list1.size()==0){
			list1.add(echartType);
		}
		List<EchartType> list2 = riskIncidentService.findUrgency(riskIncident); //查询风险事件紧急程度统计
		if(list2.size()==0){
			list2.add(echartType);
		}
		List<EchartType> list3 = riskIncidentService.findDisposeType(riskIncident); //查询风险事件处理状态统计
		if(list3.size()==0){
			list3.add(echartType);
		}
		List<EchartType> list4 = riskIncidentService.findTrend(); //查询list重大事项近几个月风险事件趋势图
		if(list4.size()==0){
			list4.add(echartType);
		}
		
		JsonConfig config = new JsonConfig();//PingJson
		config.setExcludes(new String[]{"typeO"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String listImportance = JSONArray.fromObject(list1,config).toString(); //Json查询风险事件重要程度统计
		String listUrgency = JSONArray.fromObject(list2,config).toString(); //Json查询风险事件紧急程度统计
		String listDisposeType = JSONArray.fromObject(list3,config).toString(); //Json查询风险事件处理状态统计
		String listTrend = JSONArray.fromObject(list4,config).toString(); //Json查询list重大事项近几个月风险事件趋势图
		
		model.addAttribute("listImportance", listImportance);
		model.addAttribute("listUrgency", listUrgency);
		model.addAttribute("listDisposeType", listDisposeType);
		model.addAttribute("listTrend", listTrend);
		return "risk/map/riskIncidentMap";
	}
	
	
	
	
	
	
	
	
	//
	@RequiresPermissions("report:riskIncident:view")
	@RequestMapping(value = {"list", ""})
	public String list(RiskIncident riskIncident, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RiskIncident> page = riskIncidentService.findPage(new Page<RiskIncident>(request, response), riskIncident); 
		model.addAttribute("page", page);
		return "risk/report/riskIncidentList";
	}

	@RequiresPermissions("report:riskIncident:view")
	@RequestMapping(value = "form")
	public String form(RiskIncident riskIncident, Model model) {
		// 创建 查询对象 建立查询条件
		CcmLogTail ccmLogTailDto = new CcmLogTail();
		ccmLogTailDto.setRelevanceId(riskIncident.getId());
		ccmLogTailDto.setRelevanceTable("risk_incident");
		List<CcmLogTail> ccmLogTailList = ccmLogTailService.findListByObject(ccmLogTailDto);
		// 返回查询结果
		model.addAttribute("ccmLogTailList", ccmLogTailList);
		model.addAttribute("riskIncident", riskIncident);
		return "risk/report/riskIncidentForm";
	}

	@RequiresPermissions("report:riskIncident:edit")
	@RequestMapping(value = "save")
	public String save(RiskIncident riskIncident, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, riskIncident)){
			return form(riskIncident, model);
		}
		riskIncidentService.save(riskIncident);
		addMessage(redirectAttributes, "保存风险事件管理成功");
		return "redirect:"+Global.getAdminPath()+"/report/riskIncident/?repage";
	}
	
	@RequiresPermissions("report:riskIncident:edit")
	@RequestMapping(value = "delete")
	public String delete(RiskIncident riskIncident, RedirectAttributes redirectAttributes) {
		riskIncidentService.delete(riskIncident);
		addMessage(redirectAttributes, "删除风险事件管理成功");
		return "redirect:"+Global.getAdminPath()+"/report/riskIncident/?repage";
	}
	
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<RiskEventGreat> list = riskIncidentService.findList(new RiskEventGreat());
			for (int i = 0; i < list.size(); i++) {
				RiskEventGreat r = list.get(i);
				if ((StringUtils.isBlank(extId) || (extId != null && !extId.equals(r.getId()) ) )) {
					Map<String, Object> map = Maps.newHashMap();
					map.put("id", r.getId());
					map.put("pId", "0");
					map.put("name", r.getName());
					mapList.add(map);
				}
			}
		
		return mapList;
	}

}