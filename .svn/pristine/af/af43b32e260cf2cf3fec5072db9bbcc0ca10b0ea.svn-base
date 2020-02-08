/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.web;

import java.util.ArrayList;
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
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventAmbi;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventCasedeal;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventAmbiService;
import com.arjjs.ccm.modules.ccm.log.entity.CcmLogTail;
import com.arjjs.ccm.modules.ccm.log.service.CcmLogTailService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.ccm.sys.entity.SysConfig;
import com.arjjs.ccm.modules.ccm.sys.service.SysConfigService;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.SearchTabMore;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 矛盾纠纷排查化解Controller
 * @author wwh
 * @version 2018-01-30
 */
@Controller
@RequestMapping(value = "${adminPath}/event/ccmEventAmbi")
public class CcmEventAmbiController extends BaseController {

	@Autowired
	private CcmEventAmbiService ccmEventAmbiService;
	@Autowired
	private CcmLogTailService ccmLogTailService;
	@Autowired
	private SysConfigService sysConfigService;
	
	
	
	
	@ModelAttribute
	public CcmEventAmbi get(@RequestParam(required=false) String id) {
		CcmEventAmbi entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmEventAmbiService.get(id);
		}
		if (entity == null){
			entity = new CcmEventAmbi();
		}
		return entity;
	}
	
	@RequiresPermissions("event:ccmEventAmbi:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmEventAmbi ccmEventAmbi, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmEventAmbi> page = ccmEventAmbiService.findPage(new Page<CcmEventAmbi>(request, response), ccmEventAmbi); 
		model.addAttribute("page", page);
		return "ccm/event/ccmEventAmbiList";
	}
	
	
	//矛盾纠纷排查报表
	@RequiresPermissions("event:ccmEventAmbi:view")
	@RequestMapping(value ="map")
	public String map(CcmEventAmbi ccmEventAmbi, HttpServletRequest request, HttpServletResponse response, Model model) {
		//系统级别
		SysConfig sysConfig = new SysConfig();
		sysConfig = sysConfigService.get("system_level");
		String level = sysConfig.getParamStr();
		model.addAttribute("level", level);
		model.addAttribute("ccmEventAmbi", ccmEventAmbi);
		return "ccm/event/eventMap/ccmEventAmbiMap";
	}
	//报表:矛盾纠纷排查报表-化解是否成功统计
	@ResponseBody
	@RequestMapping(value = "findSuccessMap")
	public String findSuccessMap(CcmEventAmbi ccmEventAmbi, Model model) {
		List<EchartType> listSuccess = ccmEventAmbiService.findSuccessMap(ccmEventAmbi);//化解是否成功统计
		EchartType newEchartType = new EchartType();//非空判断
		newEchartType.setType("暂无数据");
		newEchartType.setValue("0");
		if(listSuccess.size()==0){
			listSuccess.add(newEchartType);
		}
		
		JsonConfig config = new JsonConfig();//PingJson
		config.setExcludes(new String[]{"typeO"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String listSuccessString = JSONArray.fromObject(listSuccess,config).toString(); //Json化解是否成功统计

		return listSuccessString;
	}
	//报表:矛盾纠纷排查报表-矛盾纠纷规模统计
	@ResponseBody
	@RequestMapping(value = "findScaleMap")
	public String findScaleMap(CcmEventAmbi ccmEventAmbi, Model model) {
		List<EchartType> listScale = ccmEventAmbiService.findScaleMap(ccmEventAmbi);//矛盾纠纷规模统计
		EchartType newEchartType = new EchartType();//非空判断
		newEchartType.setType("暂无数据");
		newEchartType.setValue("0");
		if(listScale.size()==0){
			listScale.add(newEchartType);
		}
		
		JsonConfig config = new JsonConfig();//PingJson
		config.setExcludes(new String[]{"typeO"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String listScaleString = JSONArray.fromObject(listScale,config).toString(); //Json矛盾纠纷规模统计

		return listScaleString;
	}
	//报表:矛盾纠纷排查报表-处理状态统计
	@ResponseBody
	@RequestMapping(value = "findStatusMap")
	public String findStatusMap(CcmEventAmbi ccmEventAmbi, Model model) {
		List<EchartType> listStatus = ccmEventAmbiService.findStatusMap(ccmEventAmbi);//处理状态统计
		EchartType newEchartType = new EchartType();//非空判断
		newEchartType.setType("暂无数据");
		newEchartType.setValue("0");
		if(listStatus.size()==0){
			listStatus.add(newEchartType);
		}
		
		JsonConfig config = new JsonConfig();//PingJson
		config.setExcludes(new String[]{"typeO"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String listStatusString = JSONArray.fromObject(listStatus,config).toString(); //Json处理状态统计

		return listStatusString;
	}
	//报表:矛盾纠纷排查报表-总数统计
	@ResponseBody
	@RequestMapping(value = "findLineMap")
	public String findLineMap(CcmEventAmbi ccmEventAmbi, Model model) {
		List<EchartType> listLine = ccmEventAmbiService.findLineMap(ccmEventAmbi);//总数统计
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
	
	@ResponseBody
	@RequestMapping(value = "findAreaMap")
	public String findAreaMap(CcmEventAmbi ccmEventAmbi, Model model) {
		List<EchartType> listLine = ccmEventAmbiService.findAreaMap(ccmEventAmbi);//总数统计
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
	
	//矛盾纠纷化解getAmbiData
	@ResponseBody
	@RequestMapping(value = "getAmbiData")
	public List<String> getAmbiData(Model model) {
		// 返回对象结果
		List<String> list = new ArrayList<>();
		int ambi = ccmEventAmbiService.findListNum();
		list.add(ambi+"");//累计受理纠纷总数
		
		String s1 = "0";
		String s2 = "0";
		String s3 = "0";
		List<EchartType> listScale = ccmEventAmbiService.findScaleMap(new CcmEventAmbi());//矛盾纠纷规模统计
		for(EchartType l:listScale){
			if("01".equals(l.getTypeO())){
				s1 = l.getValue();
			}
			if("02".equals(l.getTypeO())){
				s2 = l.getValue();
			}
			if("03".equals(l.getTypeO())){
				s3 = l.getValue();
			}
		}
		
		list.add(s1);//个体性事件
		list.add(s2);//一般群体性事件
		list.add(s3);//重大群体性事件
		
		return list;
	}
	
	
	
	
	
	
	
	
	/**
	 * 矛盾纠纷详情，用于弹出层dialog的显示，方法内容和form里面的一样  
	 * @param ccmEventAmbi
	 * @param model
	 * @return
	 */
	@RequiresPermissions("event:ccmEventAmbi:view")
	@RequestMapping(value = "detail")
	public String detail(CcmEventAmbi ccmEventAmbi, Model model) {
		CcmLogTail ccmLogTailDto = new CcmLogTail();
		ccmLogTailDto.setRelevanceId(ccmEventAmbi.getId());
		ccmLogTailDto.setRelevanceTable("ccm_event_ambi");
		List<CcmLogTail > ccmLogTailList = ccmLogTailService.findListByObject(ccmLogTailDto);
		// 返回查询结果
		model.addAttribute("ccmLogTailList", ccmLogTailList);	
		model.addAttribute("ccmEventAmbi", ccmEventAmbi);
		return "ccm/event/ccmEventAmbiDetail";
	}

	//
	@RequiresPermissions("event:ccmEventAmbi:view")
	@RequestMapping(value = "form")
	public String form(CcmEventAmbi ccmEventAmbi, Model model) {
		// 创建 查询对象 建立查询条件
		CcmLogTail ccmLogTailDto = new CcmLogTail();
		ccmLogTailDto.setRelevanceId(ccmEventAmbi.getId());
		ccmLogTailDto.setRelevanceTable("ccm_event_ambi");
		List<CcmLogTail > ccmLogTailList = ccmLogTailService.findListByObject(ccmLogTailDto);
		List<CcmEventCasedeal> CcmEventCasedealList = ccmEventAmbiService.findCasedealList(ccmEventAmbi.getId());
		// 返回查询结果
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"createBy","updateBy","currentUser","dbName","global","page","updateDate","sqlMap"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String jsonDocumentList = JSONArray.fromObject(CcmEventCasedealList,config).toString(); 
		model.addAttribute("documentList", jsonDocumentList);
		model.addAttribute("documentNumber", CcmEventCasedealList.size());
		// 返回查询结果
		model.addAttribute("CcmEventCasedealList", CcmEventCasedealList);
		model.addAttribute("ccmLogTailList", ccmLogTailList);		
		model.addAttribute("ccmEventAmbi", ccmEventAmbi);
		return "ccm/event/ccmEventAmbiForm";
	}

	@RequiresPermissions("event:ccmEventAmbi:edit")
	@RequestMapping(value = "save")
	public String save(CcmEventAmbi ccmEventAmbi, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmEventAmbi)){
			return form(ccmEventAmbi, model);
		}
		if("".equals(ccmEventAmbi.getId())||ccmEventAmbi.getId()==null){
			ccmEventAmbi.setStatus("01");
		}
		ccmEventAmbiService.save(ccmEventAmbi);
		addMessage(redirectAttributes, "保存矛盾纠纷排查化解成功");
		return "redirect:"+Global.getAdminPath()+"/event/ccmEventAmbi/?repage";
	}
	
	@RequiresPermissions("event:ccmEventAmbi:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmEventAmbi ccmEventAmbi, RedirectAttributes redirectAttributes) {
		ccmEventAmbiService.delete(ccmEventAmbi);
		addMessage(redirectAttributes, "删除矛盾纠纷排查化解成功");
		return "redirect:"+Global.getAdminPath()+"/event/ccmEventAmbi/?repage";
	}

}