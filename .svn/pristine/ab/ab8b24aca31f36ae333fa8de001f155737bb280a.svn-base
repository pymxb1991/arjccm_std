/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.report.web;

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

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.risk.report.entity.RiskEventGreat;
import com.arjjs.ccm.modules.risk.report.service.RiskEventGreatService;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.SearchTab;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 重大事项报备Controller
 * @author liang
 * @version 2018-03-30
 */
@Controller
@RequestMapping(value = "${adminPath}/report/riskEventGreat")
public class RiskEventGreatController extends BaseController {

	@Autowired
	private RiskEventGreatService riskEventGreatService;
	
	@ModelAttribute
	public RiskEventGreat get(@RequestParam(required=false) String id) {
		RiskEventGreat entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = riskEventGreatService.get(id);
		}
		if (entity == null){
			entity = new RiskEventGreat();
		}
		return entity;
	}
	
	//入库
	@RequiresPermissions("report:riskEventGreat:edit")
	@RequestMapping(value = "saveList")
	public String saveList(RiskEventGreat riskEventGreat, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, riskEventGreat)){
			return form(riskEventGreat, model);
		}
		riskEventGreat = riskEventGreatService.get(riskEventGreat);
		riskEventGreat.setIsReserve(1);
		riskEventGreatService.save(riskEventGreat);
		addMessage(redirectAttributes, "入库成功");
		return "redirect:"+Global.getAdminPath()+"/report/riskEventGreat/?repage";
	}
	//入库查询
	@RequiresPermissions("report:riskEventGreat:view")
	@RequestMapping(value = "listDatabase")
	public String listDatabase(RiskEventGreat riskEventGreat, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RiskEventGreat> page = riskEventGreatService.findListDatabasePage(new Page<RiskEventGreat>(request, response), riskEventGreat); 
		model.addAttribute("page", page);
		return "risk/report/riskEventGreatListDatabase";
	}
	//入库表单
	@RequiresPermissions("report:riskEventGreat:view")
	@RequestMapping(value = "formDatabase")
	public String formDatabase(RiskEventGreat riskEventGreat, Model model) {
		model.addAttribute("riskEventGreat", riskEventGreat);
		return "risk/report/riskEventGreatFormDatabase";
	}
	//入库删除
	@RequiresPermissions("report:riskEventGreat:edit")
	@RequestMapping(value = "deleteDatabase")
	public String deleteDatabase(RiskEventGreat riskEventGreat, RedirectAttributes redirectAttributes) {
		riskEventGreat = riskEventGreatService.get(riskEventGreat);
		riskEventGreat.setIsReserve(0);
		riskEventGreatService.save(riskEventGreat);
		addMessage(redirectAttributes, "删除库的重大事项成功");
		return "redirect:"+Global.getAdminPath()+"/report/riskEventGreat/listDatabase?repage";
	}
	//重大事项数据分析
	@RequiresPermissions("report:riskEventGreat:view")
	@RequestMapping(value = "map")
	public String map(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<SearchTab> searchTab = riskEventGreatService.findListNum();//查询list重大事项数据分析
		List<EchartType> echartType = riskEventGreatService.findListTrend();//查询list重大事项近几个月事项报备趋势图
		int sum1 =  riskEventGreatService.findSum1();//未调查
		int sum2 =  riskEventGreatService.findSum2();//已调查
		int sum3 =  riskEventGreatService.findSum3();//未提交
		int sum4 =  riskEventGreatService.findSum4();//已提交
		JsonConfig config = new JsonConfig();//PingJson
		config.setExcludes(new String[]{"typeO"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String echartTypeAll = JSONArray.fromObject(echartType,config).toString(); //Json查询list重大事项近几个月事项报备趋势图
		
		model.addAttribute("searchTab", searchTab);
		model.addAttribute("echartType", echartTypeAll);
		model.addAttribute("sum1", sum1);
		model.addAttribute("sum2", sum2);
		model.addAttribute("sum3", sum3);
		model.addAttribute("sum4", sum4);
		return "risk/map/riskEventGreatMap";
	}
	
	
	//
	@RequiresPermissions("report:riskEventGreat:view")
	@RequestMapping(value = {"list", ""})
	public String list(RiskEventGreat riskEventGreat, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RiskEventGreat> page = riskEventGreatService.findPage(new Page<RiskEventGreat>(request, response), riskEventGreat); 
		model.addAttribute("page", page);
		return "risk/report/riskEventGreatList";
	}

	@RequiresPermissions("report:riskEventGreat:view")
	@RequestMapping(value = "form")
	public String form(RiskEventGreat riskEventGreat, Model model) {
		model.addAttribute("riskEventGreat", riskEventGreat);
		return "risk/report/riskEventGreatForm";
	}

	@RequiresPermissions("report:riskEventGreat:edit")
	@RequestMapping(value = "save")
	public String save(RiskEventGreat riskEventGreat, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, riskEventGreat)){
			return form(riskEventGreat, model);
		}
		if(riskEventGreat.getIsReserve()==null){
			riskEventGreat.setIsReserve(0);
		}
		riskEventGreatService.save(riskEventGreat);
		addMessage(redirectAttributes, "保存重大事项报备成功");
		return "redirect:"+Global.getAdminPath()+"/report/riskEventGreat/?repage";
	}
	
	@RequiresPermissions("report:riskEventGreat:edit")
	@RequestMapping(value = "delete")
	public String delete(RiskEventGreat riskEventGreat, RedirectAttributes redirectAttributes) {
		riskEventGreatService.delete(riskEventGreat);
		addMessage(redirectAttributes, "删除重大事项报备成功");
		return "redirect:"+Global.getAdminPath()+"/report/riskEventGreat/?repage";
	}

}