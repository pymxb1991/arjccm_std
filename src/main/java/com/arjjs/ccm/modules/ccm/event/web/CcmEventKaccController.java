/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.web;

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
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventKacc;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventKaccService;
import com.arjjs.ccm.modules.ccm.sys.entity.SysConfig;
import com.arjjs.ccm.modules.ccm.sys.service.SysConfigService;
import com.arjjs.ccm.tool.EchartType;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 重点地区排查整治Controller
 * @author arj
 * @version 2018-01-04
 */
@Controller
@RequestMapping(value = "${adminPath}/event/ccmEventKacc")
public class CcmEventKaccController extends BaseController {

	@Autowired
	private CcmEventKaccService ccmEventKaccService;
	@Autowired
	private SysConfigService sysConfigService;
	
	@ModelAttribute
	public CcmEventKacc get(@RequestParam(required=false) String id) {
		CcmEventKacc entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmEventKaccService.get(id);
		}
		if (entity == null){
			entity = new CcmEventKacc();
		}
		return entity;
	}
	
	
	@RequiresPermissions("event:ccmEventKacc:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmEventKacc ccmEventKacc, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmEventKacc> page = ccmEventKaccService.findPage(new Page<CcmEventKacc>(request, response), ccmEventKacc); 
		model.addAttribute("page", page);
		return "ccm/event/ccmEventKaccList";
	}
	
	
	//治安乱点整治报表
	@RequiresPermissions("event:ccmEventKacc:view")
	@RequestMapping(value ="map")
	public String map(CcmEventKacc ccmEventKacc, HttpServletRequest request, HttpServletResponse response, Model model) {
		//系统级别
		SysConfig sysConfig = new SysConfig();
		sysConfig = sysConfigService.get("system_level");
		String level = sysConfig.getParamStr();
		
		model.addAttribute("ccmEventKacc", ccmEventKacc);
		model.addAttribute("level", level);
		return "ccm/event/eventMap/ccmEventKaccMap";
	}
	//报表:治安乱点整治报表-治安突出问题统计
	@ResponseBody
	@RequestMapping(value = "findSafePage")
	public String findSafePage(CcmEventKacc ccmEventKacc, Model model) {
		List<EchartType> listSafe = ccmEventKaccService.findSafePage(ccmEventKacc); //治安突出问题统计
		EchartType newEchartType = new EchartType();//非空判断
		newEchartType.setType("暂无数据");
		newEchartType.setValue("0");
		if(listSafe.size()==0){
			listSafe.add(newEchartType);
		}
		JsonConfig config = new JsonConfig();//PingJson
		config.setExcludes(new String[]{"typeO"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String listSafeString = JSONArray.fromObject(listSafe,config).toString(); //Json治安突出问题统计
		model.addAttribute("listSafe", listSafeString);

		return listSafeString;
	}
	//报表:治安乱点整治报表-涉及区域类型统计
	@ResponseBody
	@RequestMapping(value = "findAreaPage")
	public String findAreaPage(CcmEventKacc ccmEventKacc, Model model) {
		List<EchartType> listArea = ccmEventKaccService.findAreaPage(ccmEventKacc); //涉及区域类型统计
		EchartType newEchartType = new EchartType();//非空判断
		newEchartType.setType("暂无数据");
		newEchartType.setValue("0");
		if(listArea.size()==0){
			listArea.add(newEchartType);
		}
		JsonConfig config = new JsonConfig();//PingJson
		config.setExcludes(new String[]{"typeO"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String listAreaString = JSONArray.fromObject(listArea,config).toString(); //Json涉及区域类型统计
		
		return listAreaString;
	}
	//报表:治安乱点整治报表-效果评估统计
	@ResponseBody
	@RequestMapping(value = "findAssessPage")
	public String findAssessPage(CcmEventKacc ccmEventKacc, Model model) {
		List<EchartType> listAssess = ccmEventKaccService.findAssessPage(ccmEventKacc); //效果评估统计
		EchartType newEchartType = new EchartType();//非空判断
		newEchartType.setType("暂无数据");
		newEchartType.setValue("0");
		if(listAssess.size()==0){
			listAssess.add(newEchartType);
		}
		JsonConfig config = new JsonConfig();//PingJson
		config.setExcludes(new String[]{"typeO"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String listAssessString = JSONArray.fromObject(listAssess,config).toString(); //Json效果评估统计
		
		return listAssessString;
	}
	//报表:治安乱点整治报表-总数统计
	@ResponseBody
	@RequestMapping(value = "findLinePage")
	public String findLinePage(CcmEventKacc ccmEventKacc, Model model) {
		List<EchartType> listLine = ccmEventKaccService.findLinePage(ccmEventKacc); //总数统计
		EchartType newEchartType = new EchartType();//非空判断
		newEchartType.setType("暂无数据");
		newEchartType.setValue("0");
		if(listLine.size()==0){
			listLine.add(newEchartType);
		}
		JsonConfig config = new JsonConfig();//PingJson
		config.setExcludes(new String[]{"typeO"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String listLineString = JSONArray.fromObject(listLine,config).toString(); //Json总数统计

		return listLineString;
	}
	
	
	
	
	
	//
	@RequiresPermissions("event:ccmEventKacc:view")
	@RequestMapping(value = "form")
	public String form(CcmEventKacc ccmEventKacc, Model model) {
		model.addAttribute("ccmEventKacc", ccmEventKacc);
		return "ccm/event/ccmEventKaccForm";
	}

	@RequiresPermissions("event:ccmEventKacc:edit")
	@RequestMapping(value = "save")
	public String save(CcmEventKacc ccmEventKacc, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmEventKacc)){
			return form(ccmEventKacc, model);
		}
		ccmEventKaccService.save(ccmEventKacc);
		addMessage(redirectAttributes, "保存重点地区排查整治成功");
		return "redirect:"+Global.getAdminPath()+"/event/ccmEventKacc/?repage";
	}
	
	@RequiresPermissions("event:ccmEventKacc:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmEventKacc ccmEventKacc, RedirectAttributes redirectAttributes) {
		ccmEventKaccService.delete(ccmEventKacc);
		addMessage(redirectAttributes, "删除重点地区排查整治成功");
		return "redirect:"+Global.getAdminPath()+"/event/ccmEventKacc/?repage";
	}

}