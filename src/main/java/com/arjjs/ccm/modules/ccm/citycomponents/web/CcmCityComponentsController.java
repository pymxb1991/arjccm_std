/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.citycomponents.web;

import java.util.ArrayList;
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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmDevice;
import com.arjjs.ccm.modules.ccm.ccmsys.service.CcmDeviceService;
import com.arjjs.ccm.modules.ccm.citycomponents.entity.CcmCityComponents;
import com.arjjs.ccm.modules.ccm.citycomponents.service.CcmCityComponentsService;
import com.arjjs.ccm.modules.ccm.line.entity.CcmLineProtect;
import com.arjjs.ccm.modules.ccm.line.service.CcmLineProtectService;
import com.arjjs.ccm.modules.ccm.sys.entity.SysDicts;
import com.arjjs.ccm.modules.ccm.sys.service.SysDictsService;
import com.arjjs.ccm.tool.EchartType;
import com.google.common.collect.Maps;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 城市部件Controller
 * @author pengjianqiang
 * @version 2018-03-06
 */
@Controller
@RequestMapping(value = "${adminPath}/citycomponents/ccmCityComponents")
public class CcmCityComponentsController extends BaseController {

	@Autowired
	private CcmCityComponentsService ccmCityComponentsService;
	@Autowired
	private CcmDeviceService ccmDeviceService;
	@Autowired
	private CcmLineProtectService ccmLineProtectService;
	@Autowired
	private SysDictsService sysDictsService;
	
	
	@ModelAttribute
	public CcmCityComponents get(@RequestParam(required=false) String id) {
		CcmCityComponents entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ccmCityComponentsService.get(id);
		}
		if (entity == null){
			entity = new CcmCityComponents();
		}
		return entity;
	}

	/**
	 * 城市部件状态
	 */
	@ResponseBody
	@RequestMapping(value = "getCityType")
	public List<List<EchartType>> getCityType(Model model) {
		List<List<EchartType>> list = new ArrayList<>();
		
		String[] cityCY = {"01","04","05","08","15","18"};//01井盖//04停车场//05健身设施//08存车支架//15邮筒//18应急避难场所
		//String cityCY = "(a.type = '01' OR a.type = '04' OR a.type = '05' OR a.type = '08' OR a.type = '15' OR a.type = '18')";
		CcmCityComponents ccmCityComponentsCY = new CcmCityComponents();
		ccmCityComponentsCY.setTypes(cityCY);
		List<EchartType> listCY = ccmCityComponentsService.getCityTypeGY(ccmCityComponentsCY);//公用设施
		list.add(listCY);
		String[] cityDL = {"02","03","06","14"};//02交通信号灯//03交通标志牌//06公交站亭//14路灯
		//String cityDL = "(a.type = '02' OR a.type = '03' OR a.type = '06' OR a.type = '14')";
		CcmCityComponents ccmCityComponentsDL = new CcmCityComponents();
		ccmCityComponentsDL.setTypes(cityDL);
		List<EchartType> listDL = ccmCityComponentsService.getCityTypeGY(ccmCityComponentsDL);//道路交通
		list.add(listDL);
		String[] citySY = {"07","10","13"};//07垃圾箱//10景观灯//13行道树
		//String citySY = "(a.type = '07' OR a.type = '10' OR a.type = '13')";
		CcmCityComponents ccmCityComponentsSY = new CcmCityComponents();
		ccmCityComponentsSY.setTypes(citySY);
		List<EchartType> listSY = ccmCityComponentsService.getCityTypeGY(ccmCityComponentsSY);//市容环境
		list.add(listSY);
		
		return list;
	}
	/**
	 * 城市部件状态-大屏-滨海新区社会网格化管理信息平台
	 */
	@ResponseBody
	@RequestMapping(value = "getCityTypeAll")
	public String getCityTypeAll(Model model) {
		List<EchartType> list = ccmCityComponentsService.getCityTypeGY(new CcmCityComponents());
		
		JsonConfig config = new JsonConfig();//PingJson
		config.setExcludes(new String[]{"typeO"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String listCityTypeAll = JSONArray.fromObject(list,config).toString(); //Json
		return listCityTypeAll;
	}
	
	/**
	 * 城市部件数据一览-大屏-滨海新区社会网格化管理信息平台
	 */
	@ResponseBody
	@RequestMapping(value = "getCityData")
	public List<String> getCityData(Model model) {
		List<String> list = new ArrayList<>();
		
		List<CcmCityComponents> listCity = ccmCityComponentsService.findList(new CcmCityComponents());
		list.add(listCity.size()+"");//各类公共设施
		
		//视频监控
		CcmDevice ccmDevice = new CcmDevice();
		ccmDevice.setTypeId("003");
		List<CcmDevice> listCcmDevice = ccmDeviceService.findList(ccmDevice); 
		list.add(listCcmDevice.size()+"");
		
		//护路护线
		List<CcmLineProtect> listLine = ccmLineProtectService.findList(new CcmLineProtect()); 
		list.add(listLine.size()+"");
		
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "getCitycomponents")
	public Map<String,Object> getCitycomponents(Model model) {
		Map<String,Object> map = Maps.newHashMap();
		
		//公共设施
		Map<String,Object> map1 = Maps.newHashMap();
		List<CcmCityComponents> listCity = ccmCityComponentsService.findList(new CcmCityComponents());
		int sumCity = listCity.size();
		String[] cityCY = {"01"};//01井盖//04停车场//05健身设施//08存车支架//15邮筒//18应急避难场所
		CcmCityComponents ccmCityComponentsCY = new CcmCityComponents();
		ccmCityComponentsCY.setTypes(cityCY);
		List<EchartType> listCY = ccmCityComponentsService.getCityTypeGY(ccmCityComponentsCY);//公用设施
		// map1.put("sum", sumCity);
		map.put("公共设施", map1);
		
		//道路环境
		Map<String,Object> map2 = Maps.newHashMap();
		CcmDevice ccmDevice = new CcmDevice();
		ccmDevice.setTypeId("003");
		List<CcmDevice> listCcmDevice = ccmDeviceService.findList(ccmDevice); 
		int sumDevice = listCcmDevice.size();
		String[] cityDL = {"14"};//02交通信号灯//03交通标志牌//06公交站亭//14路灯
		CcmCityComponents ccmCityComponentsDL = new CcmCityComponents();
		ccmCityComponentsDL.setTypes(cityDL);
		List<EchartType> listDL = ccmCityComponentsService.getCityTypeGY(ccmCityComponentsDL);//道路交通
		// map2.put("sum", sumDevice);
		map.put("道路环境", map2);
		
		//市容环境
		Map<String,Object> map3 = Maps.newHashMap();
		List<CcmLineProtect> listLine = ccmLineProtectService.findList(new CcmLineProtect()); 
		int sumLine = listLine.size();
		String[] citySY = {"07"};//07垃圾箱//10景观灯//13行道树
		CcmCityComponents ccmCityComponentsSY = new CcmCityComponents();
		ccmCityComponentsSY.setTypes(citySY);
		List<EchartType> listSY = ccmCityComponentsService.getCityTypeGY(ccmCityComponentsSY);//市容环境
		// map3.put("sum", sumLine);
		map.put("市容环境", map3);
		
		List<SysDicts> sysDictList = sysDictsService.findAllListByType("ccm_city_components_status");
		for (SysDicts sysDicts : sysDictList) {	
			Map<String,Object> mapCY = Maps.newHashMap();
			int valueCY = 0;
			for (EchartType echartType : listCY) {
				if(echartType.getTypeO().equals(sysDicts.getValue())) {
					valueCY = Integer.valueOf(echartType.getValue());
				}
			}
			mapCY.put("value",valueCY);
			float percentCY = ((float)valueCY/sumCity)*100;
			mapCY.put("percent",Math.round(percentCY));
			mapCY.put("label",sysDicts.getLabel());
			
			Map<String,Object> mapDL = Maps.newHashMap();
			int valueDL = 0;
			for (EchartType echartType : listDL) {
				if(echartType.getTypeO().equals(sysDicts.getValue())) {
					valueDL = Integer.valueOf(echartType.getValue());
				}
			}
			mapDL.put("value",valueDL);
			float percentDL = ((float)valueDL/sumDevice)*100;
			mapDL.put("percent",Math.round(percentDL));
			mapDL.put("label",sysDicts.getLabel());
			
			Map<String,Object> mapSY = Maps.newHashMap();
			int valueSY = 0;
			for (EchartType echartType : listSY) {
				if(echartType.getTypeO().equals(sysDicts.getValue())) {
					valueSY = Integer.valueOf(echartType.getValue());
				}
			}
			mapSY.put("value",valueSY);
			float percent = ((float)valueSY/sumLine)*100;
			mapSY.put("percent",Math.round(percent));
			mapSY.put("label",sysDicts.getLabel());
			
			map1.put(sysDicts.getValue(), mapCY);
			map2.put(sysDicts.getValue(), mapDL);
			map3.put(sysDicts.getValue(), mapSY);
			
			map.put(sysDicts.getValue(), sysDicts.getLabel());
		}
		return map;
	}
	
	
	
	
	
	
	@RequiresPermissions("citycomponents:ccmCityComponents:view")
	@RequestMapping(value = {"list", ""})
	public String list(CcmCityComponents ccmCityComponents, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CcmCityComponents> page = ccmCityComponentsService.findPage(new Page<CcmCityComponents>(request, response), ccmCityComponents); 
		model.addAttribute("page", page);
		return "ccm/citycomponents/ccmCityComponentsList";
	}

	@RequiresPermissions("citycomponents:ccmCityComponents:view")
	@RequestMapping(value = "form")
	public String form(CcmCityComponents ccmCityComponents, Model model) {
		model.addAttribute("ccmCityComponents", ccmCityComponents);
		return "ccm/citycomponents/ccmCityComponentsForm";
	}

	@RequiresPermissions("citycomponents:ccmCityComponents:edit")
	@RequestMapping(value = "save")
	public String save(CcmCityComponents ccmCityComponents, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmCityComponents)){
			return form(ccmCityComponents, model);
		}
		ccmCityComponentsService.save(ccmCityComponents);
		addMessage(redirectAttributes, "保存城市部件成功");
		return "redirect:"+Global.getAdminPath()+"/citycomponents/ccmCityComponents/?repage";
	}
	
	@RequiresPermissions("citycomponents:ccmCityComponents:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmCityComponents ccmCityComponents, RedirectAttributes redirectAttributes) {
		ccmCityComponentsService.delete(ccmCityComponents);
		addMessage(redirectAttributes, "删除城市部件成功");
		return "redirect:"+Global.getAdminPath()+"/citycomponents/ccmCityComponents/?repage";
	}

	@RequestMapping(value = "mapvForm")
	public String mapvForm(CcmCityComponents ccmCityComponents, Model model) {
		model.addAttribute("ccmCityComponents", ccmCityComponents);
		return "ccm/citycomponents/ccmCityComponentsMapvForm";
	}
}