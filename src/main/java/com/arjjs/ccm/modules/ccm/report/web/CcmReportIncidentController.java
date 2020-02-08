/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.report.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.citycomponents.entity.CcmCityComponents;
import com.arjjs.ccm.modules.ccm.citycomponents.service.CcmCityComponentsService;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventIncident;
import com.arjjs.ccm.modules.ccm.event.service.CcmEventIncidentService;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildmanage;
import com.arjjs.ccm.modules.ccm.house.service.CcmHouseBuildmanageService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgArea;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgAreaService;
import com.arjjs.ccm.modules.ccm.org.service.CcmOrgNpseService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPopTenant;
import com.arjjs.ccm.modules.ccm.pop.service.CcmPopTenantService;
import com.arjjs.ccm.modules.ccm.report.entity.CcmPeopleAmount;
import com.arjjs.ccm.modules.ccm.report.service.CcmPeopleAmountService;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.service.AreaService;
import com.arjjs.ccm.tool.CommUtil;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.SearchTab;
import com.arjjs.ccm.tool.SearchTabMore;
import com.google.common.collect.Maps;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 案事件统计Controller
 * 
 * @author arj
 * @version 2018-01-20
 */
@Controller
@RequestMapping(value = "${adminPath}/report/ccmIncidentBegin")
public class CcmReportIncidentController extends BaseController {

	@Autowired
	private CcmEventIncidentService ccmEventIncidentService;
	@Autowired
	private CcmPeopleAmountService ccmPeopleAmountService;
	@Autowired
	private CcmPopTenantService ccmPopTenantService;
	@Autowired
	private CcmHouseBuildmanageService ccmHouseBuildmanageService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private CcmOrgAreaService ccmOrgAreaService;
	@Autowired
	private CcmOrgNpseService ccmOrgNpseService;
	@Autowired
	private CcmCityComponentsService ccmCityComponentsService;
	
	
	/**
	 * 入口
	 * 
	 * @param id
	 * @param eventPath
	 * @return
	 */
	@ModelAttribute
	public CcmEventIncident get(@RequestParam(required = false) String id,
			@RequestParam(required = false) String eventPath) {
		CcmEventIncident entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmEventIncidentService.get(id);
		}
		if (entity == null) {
			entity = new CcmEventIncident();
		}
		return entity;
	}

	/**
	 * 根据案事件性质统计情况
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getItemByProperty")
	public List<EchartType> getItemByProperty(Model model) {
		// 返回对象结果
		List<EchartType> list = ccmEventIncidentService.getItemByProperty();
		return list;
	}

	/**
	 * @see 根据案事件分级统计情况
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getItemByScale")
	public Map<String, Object> getItemByScale(Model model) {
		// 返回对象结果
		Map<String, Object> map = ccmEventIncidentService.getItemByScale();
		return map;
	}

	/**
	 * 根据案事件模块分类event_kind
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getItemByScaleTable")
	public Map<String, SearchTab> getItemByScaleTable(@RequestParam(required = false) String type, Model model) {
		// 返回对象结果
		CcmEventIncident ccmEventIncident = new CcmEventIncident();
		if (!StringUtils.isEmpty(type)) {
			ccmEventIncident.setEventKind(type);
		}
		// 查询 根据地区、分级类型的数据
		List<EchartType> list = ccmEventIncidentService.getItemByScaleTable(ccmEventIncident);

		// 获取并组装所有需要的值
		return CommUtil.GetSearchTabForIncident(list);
	}

	/**
	 * 案事件统计
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getItemBySum")
	public Map<String, String> getItemBySum(Model model) {
		// 返回对象结果
		Map<String, String> map = Maps.newHashMap();
		List<EchartType> list = ccmEventIncidentService.getItemBySum();
		int n = 0;
		int y = 0;
		int totle = 0;
		for (EchartType et : list) {
			// 如果为空
			//if(StringUtils.isEmpty(et.getType())){
			//	continue;
			//}
			if("02".equals(et.getType())){
				y = Integer.parseInt(et.getValue());
			}else{
				n += Integer.parseInt(et.getValue());
			}
			//map.put(et.getType(), et.getValue());
			//totle += Integer.parseInt(et.getValue());
		}
		//map.put("2", totle + "");
		totle = n+y;
		map.put("0",n + "");
		map.put("1",y + "");
		map.put("2",totle + "");
		return map;
	}

	/**
	 * 今日案事件统计
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getItemBySumToday")
	public Map<String, String> getItemBySumToday(Model model) {
		// 返回对象结果
		Map<String, String> map = Maps.newHashMap();
		List<EchartType> list = ccmEventIncidentService.getItemBySumToday();
		int a = 0;
		int b = 0;
		int c = 0;
		for (EchartType et : list) {
			// 如果为空
			//if(StringUtils.isEmpty(et.getType())){
			//	continue;
			//}
			if("03".equals(et.getType())){	// 已处理
				c = Integer.parseInt(et.getValue());
			}else if("02".equals(et.getType())){	// 处理中
				b += Integer.parseInt(et.getValue());
			}else if("01".equals(et.getType())){	// 未处理
				a += Integer.parseInt(et.getValue());
			}
			//map.put(et.getType(), et.getValue());
			//totle += Integer.parseInt(et.getValue());
		}
		//map.put("2", totle + "");
		map.put("0",a + "");
		map.put("1",b + "");
		map.put("2",c + "");
		return map;
	}
	/**
	 * 首页概况各种总数查询
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getEachAll")
	public SearchTabMore getEachAll(Model model) {
		SearchTabMore searchTabMore = new SearchTabMore();
		searchTabMore.setValue1("0");//实有人口总数
		searchTabMore.setValue2("0");//房屋总数
		searchTabMore.setValue3("0");//楼栋总数
		searchTabMore.setValue4("0");//网格总数
		searchTabMore.setValue5("0");//网格人员
		searchTabMore.setValue6("0");//重点企业
		//实有人口总数
		SearchTabMore searchTabMore1 = ccmPeopleAmountService.getnumPopFollowPop();
		if(searchTabMore1!=null){
			int p =
			Integer.parseInt(searchTabMore1.getValue()!=null?searchTabMore1.getValue():"0")
			+Integer.parseInt(searchTabMore1.getValue1()!=null?searchTabMore1.getValue1():"0")
			+Integer.parseInt(searchTabMore1.getValue2()!=null?searchTabMore1.getValue2():"0")
			+Integer.parseInt(searchTabMore1.getValue3()!=null?searchTabMore1.getValue3():"0");
			searchTabMore.setValue1(p+"");//实有人口总数
		}
		//房屋总数
		int house = ccmPopTenantService.findListNum(new CcmPopTenant());
		searchTabMore.setValue2(house+"");//房屋总数
		//楼栋总数
		int build = ccmHouseBuildmanageService.findListNum(new CcmHouseBuildmanage());
		searchTabMore.setValue3(build+"");//楼栋总数
		//网格总数
		Area area = new Area();
		area.setType("7");//网格
		List<Area> list4 = areaService.findList(area);
		if(list4.size()>0){
			searchTabMore.setValue4(list4.size()+"");//网格总数
		}
		//网格人员
		List<CcmOrgArea> list5 = ccmOrgAreaService.findList(new CcmOrgArea());
		if(list5.size()>0){
			int a=0;
			for(CcmOrgArea areas:list5){
				if(areas.getNetPeoNum()!=null){
					a +=areas.getNetPeoNum();
				}
			}
			searchTabMore.setValue5(a+"");//网格人员
		}
		//重点企业数量
		int org = ccmOrgNpseService.findCompImpoTypes();
		searchTabMore.setValue6(org+"");//重点企业数量
		
		return searchTabMore;
	}
	/**
	 * 首页概况各种总数查询-城市管理
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getEachAll2")
	public SearchTabMore getEachAll2(Model model) {
		SearchTabMore searchTabMore = new SearchTabMore();
		searchTabMore.setValue1("0");//实有人口总数
		searchTabMore.setValue2("0");//房屋总数
		searchTabMore.setValue3("0");//楼栋总数
		searchTabMore.setValue4("0");//网格总数
		searchTabMore.setValue5("0");//网格人员
		searchTabMore.setValue6("0");//重点企业
		//实有人口总数
		SearchTabMore searchTabMore1 = ccmPeopleAmountService.getnumPopFollowPop();
		if(searchTabMore1!=null){
			int p =
			Integer.parseInt(searchTabMore1.getValue()!=null?searchTabMore1.getValue():"0")
			+Integer.parseInt(searchTabMore1.getValue1()!=null?searchTabMore1.getValue1():"0")
			+Integer.parseInt(searchTabMore1.getValue2()!=null?searchTabMore1.getValue2():"0")
			+Integer.parseInt(searchTabMore1.getValue3()!=null?searchTabMore1.getValue3():"0");
			searchTabMore.setValue1(p+"");//实有人口总数
		}
		//楼栋总数
		int build = ccmHouseBuildmanageService.findListNum(new CcmHouseBuildmanage());
		searchTabMore.setValue2(build+"");//楼栋总数
		//房屋总数
		int house = ccmPopTenantService.findListNum(new CcmPopTenant());
		searchTabMore.setValue3(house+"");//房屋总数
		//网格总数
		Area area = new Area();
		area.setType("7");//网格
		List<Area> list4 = areaService.findList(area);
		if(list4.size()>0){
			searchTabMore.setValue4(list4.size()+"");//网格总数
		}
		//网格人员
		List<CcmOrgArea> list5 = ccmOrgAreaService.findList(new CcmOrgArea());
		if(list5.size()>0){
			int a=0;
			for(CcmOrgArea areas:list5){
				if(areas.getNetPeoNum()!=null){
					a +=areas.getNetPeoNum();
				}
			}
			searchTabMore.setValue5(a+"");//网格人员
		}
		//城市部件数量
		int city = ccmCityComponentsService.findListNum();
		searchTabMore.setValue6(city+"");//城市部件数量
		
		return searchTabMore;
	}
	
	
	
	
	
	
	
	/**
	 * 前10的地区破案率
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findSolveByArea")
	public Map<String, Object> findSolveByArea(Model model) {
		// 返回对象结果
		List<EchartType> list = ccmEventIncidentService.findSolveByArea();
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			EchartType et = list.get(i);
			list1.add(et.getType());
			list2.add(et.getValue());
		}
		// 返回对象结果
		Map<String, Object> map = Maps.newHashMap();
		map.put("1", list1);
		map.put("2", list2);

		return map;
	}

	/**
	 * 按月统计的处理率
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findSolveByMon")
	public Map<String, Object> findSolveByMon(Model model) {
		// 返回对象结果
		Map<String, Object> map = Maps.newHashMap();
		// 解决率
		List<EchartType> list1 = ccmEventIncidentService.findSolveByMon();
		// 总数
		List<EchartType> list2 = ccmEventIncidentService.findSumByMon(new CcmEventIncident());

		// X轴坐标 赋值
		List<String> XList = new ArrayList<>();
		List<String> ListY1 = new ArrayList<>();

		// 装配 y轴 两个部分内容 的值
		for (int i = 0; i < list1.size(); i++) {
			EchartType et = list1.get(i);
			XList.add(et.getType());
			ListY1.add(et.getValue());
		}
		List<String> ListY2 = new ArrayList<>();
		for (int i = 0; i < list2.size(); i++) {
			EchartType et = list2.get(i);
			ListY2.add(et.getValue());
		}
		// 填充数据
		map.put("X轴", XList);
		map.put("处理率", ListY1);
		map.put("案事件数", ListY2);
		return map;
	}
	/**
	 * 案事件处理比例-大屏-滨海新区社会网格化管理信息平台
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findSumByMon")
	public Map<String, String> findSumByMon(Model model) {
		// 返回对象结果
		Map<String, String> map = Maps.newHashMap();
		
		CcmEventIncident ccmEventIncident = new CcmEventIncident();
		List<EchartType> list = ccmEventIncidentService.findSumByMon(ccmEventIncident);// 总数
		ccmEventIncident.setStatus("01");
		List<EchartType> list1 = ccmEventIncidentService.findSumByMon(ccmEventIncident);// 未处理
		ccmEventIncident.setStatus("02");
		List<EchartType> list2 = ccmEventIncidentService.findSumByMon(ccmEventIncident);// 处理中
		ccmEventIncident.setStatus("03");
		List<EchartType> list3 = ccmEventIncidentService.findSumByMon(ccmEventIncident);// 已完成
		
		JsonConfig config = new JsonConfig();//PingJson
		config.setExcludes(new String[]{"typeO"});
		config.setIgnoreDefaultExcludes(false);  //设置默认忽略
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		String listString = JSONArray.fromObject(list,config).toString(); //Json
		String list1String = JSONArray.fromObject(list1,config).toString(); //Json
		String list2String = JSONArray.fromObject(list2,config).toString(); //Json
		String list3String = JSONArray.fromObject(list3,config).toString(); //Json
		
		// 填充数据
		map.put("quan", listString);
		map.put("01", list1String);
		map.put("02", list2String);
		map.put("03", list3String);
		return map;
	}

	/**
	 * 最新案事件
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findSumByCondition")
	public List<SearchTab> findSumByCondition(Model model) {
		// 返回对象结果
		List<SearchTab> list = ccmEventIncidentService.findSumByCondition();
		return list;
	}

	/**
	 * 案事件类型统计
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findSumByEventType")
	public List<EchartType> findSumByEventType(Model model) {
		// 返回对象结果
		List<EchartType> list = ccmEventIncidentService.findSumByEventType();
		return list;
	}
	/**
	 * 类型统计情况-首页城市管理
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findSumByEventType2")
	public List<String> findSumByEventType2(Model model) {
		// 返回对象结果
		List<String> list = new ArrayList<>();
		List<EchartType> listEchartType = ccmEventIncidentService.findSumByEventType();
		String e0 = "0";
		String e1 = "0";
		String e2 = "0";
		String e3 = "0";
		String e4 = "0";
		String e5 = "0";
		for(EchartType l:listEchartType){
			if("01".equals(l.getTypeO())){
				e1 = l.getValue();
			}
			if("02".equals(l.getTypeO())){
				e2 = l.getValue();
			}
			if("03".equals(l.getTypeO())){
				e3 = l.getValue();
			}
			if("04".equals(l.getTypeO())){
				e4 = l.getValue();
			}
			if("05".equals(l.getTypeO())){
				e5 = l.getValue();
			}
		}
		e0 = (Integer.parseInt(e1)+Integer.parseInt(e2)+Integer.parseInt(e3)+Integer.parseInt(e4)+Integer.parseInt(e5))+"";
		list.add(e0);//累计受理事件
		list.add(e1);//安全事故
		list.add(e2);//群体性事件
		list.add(e3);//食品安全事故
		list.add(e4);//有关刑事案件
		list.add(e5);//其他
		
		return list;
	}
	
	/**
	 * 事件分类
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findEventFenLeiData")
	public List<EchartType> findEventFenLeiData(Model model) {
		// 返回对象结果
		List<EchartType> list = ccmEventIncidentService.findEventFenLeiData();
		return list;
	}
	/**
	 * 案事件类型统计
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findSumByEventWeek")
	public List<EchartType> findSumByEventWeek(Model model) {
		// 返回对象结果
		List<EchartType> list = ccmEventIncidentService.findSumByEventWeek();
		// 使用map
		Map<String,String> map  = new HashMap<>(); 
		// 返回结果
		for (EchartType echartType : list) {
			map.put(echartType.getType(), echartType.getValue());
		}
		// 封装数据
		List<EchartType> weekList = CommUtil.dateToWeek();
		for(EchartType echartType : weekList){
			if(map.containsKey(echartType.getType())){
				echartType.setValue(map.get(echartType.getType()));
			}else{
				echartType.setValue(0+"");
			}
		}
		return weekList;
	}

	/**
	 * 案事件统计
	 * 
	 * @return
	 */
	@RequiresPermissions("report:ccmIncidentBegin:view")
	@RequestMapping(value = "police")
	public String police() {
		return "ccm/report/reportPolice";
	}
	
	
	
	/**
	 * 本月事件发生前十
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findEventMonthMap")
	public Map<String, Object> findEventMonthMap(Model model) {
		// 返回对象结果
		List<EchartType> list = ccmEventIncidentService.findEventMonthMap();
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			EchartType et = list.get(i);
			list1.add(et.getType());
			list2.add(et.getValue());
		}
		// 返回对象结果
		Map<String, Object> map = Maps.newHashMap();
		map.put("1", list1);
		map.put("2", list2);

		return map;
	}
	/**
	 * 近一年事件发生/重点人员帮扶趋势图
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findEventYearMap")
	public List<SearchTab> findEventYearMap(CcmPeopleAmount ccmPeopleAmount ,Model model) {
		// 返回对象结果
		List<SearchTab> list = ccmEventIncidentService.findEventYearMap();
		if(list.size()==0){
			SearchTab searchTab = new SearchTab();
			searchTab.setType("暂无数据");
			searchTab.setValue1("0");
			searchTab.setValue2("0");
			list.add(searchTab);
		}else{
			for(int i=0;i<list.size();i++){
				if(list.get(i).getType()==null){
					list.get(i).setType("暂无数据");
				}
				if(list.get(i).getValue1()==null){
					list.get(i).setValue1("0");
				}
				if(list.get(i).getValue2()==null){
					list.get(i).setValue2("0");
				}
			}
		}
		return list;
	}
	
	
	/**
	 * 事件规模统计
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "findEventGuiMoData")
	public List<EchartType> findEventGuiMoData(Model model) {
		// 返回对象结果
		List<EchartType> list = ccmEventIncidentService.findEventGuiMoData();
		return list;
	}
	

}