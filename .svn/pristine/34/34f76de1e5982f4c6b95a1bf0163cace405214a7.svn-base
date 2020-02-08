/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.web;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolPlan;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolPoint;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolPointsort;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolUser;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolPlanService;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolPointService;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolPointsortService;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolUserService;
import com.arjjs.ccm.tool.geoJson.Features;
import com.arjjs.ccm.tool.geoJson.GeoJSON;
import com.arjjs.ccm.tool.geoJson.Geometry;
import com.arjjs.ccm.tool.geoJson.Properties;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 巡逻计划Controller
 * 
 * @author arj
 * @version 2018-03-14
 */
@Controller
@RequestMapping(value = "${adminPath}/patrol/ccmPatrolPlan")
public class CcmPatrolPlanController extends BaseController {

	@Autowired
	private CcmPatrolPlanService ccmPatrolPlanService;
	@Autowired
	private CcmPatrolUserService CcmPatrolUser;
	@Autowired
	private CcmPatrolPointService ccmPatrolPointService;

	@Autowired
	private CcmPatrolPointsortService ccmPatrolPointsortService;

	@ModelAttribute
	public CcmPatrolPlan get(@RequestParam(required = false) String id) {
		CcmPatrolPlan entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmPatrolPlanService.get(id);
		}
		if (entity == null) {
			entity = new CcmPatrolPlan();
		}
		return entity;
	}

	@RequiresPermissions("patrol:ccmPatrolPlan:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmPatrolPlan ccmPatrolPlan, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CcmPatrolPlan> page = ccmPatrolPlanService.findPage(new Page<CcmPatrolPlan>(request, response),
				ccmPatrolPlan);
		model.addAttribute("page", page);

		return "ccm/patrol/ccmPatrolPlanList";
	}

	@RequiresPermissions("patrol:ccmPatrolPlan:view")
	@RequestMapping(value = "form")
	public String form(CcmPatrolPlan ccmPatrolPlan, Model model) {
		if (StringUtils.isNotBlank(ccmPatrolPlan.getId())) {
			// 用户查询
			CcmPatrolUser ccmPatrolUser = new CcmPatrolUser();
			ccmPatrolUser.setPlanId(ccmPatrolPlan.getId());
			List<CcmPatrolUser> list = CcmPatrolUser.findList(ccmPatrolUser);
			// 放置 userlist
			ccmPatrolPlan.setCcmPatrolUserList(list);
			// 点位查询
			CcmPatrolPointsort sort = new CcmPatrolPointsort();
			sort.setPlanId(ccmPatrolPlan.getId());
			List<CcmPatrolPointsort> sortlist = ccmPatrolPointsortService.findList(sort);
			ccmPatrolPlan.setCcmPatrolPointSortList(sortlist);
		}
		// 点位顺序 查询
		// ccmPatrolPointsortService

		model.addAttribute("ccmPatrolPlan", ccmPatrolPlan);
		return "ccm/patrol/ccmPatrolPlanForm";
	}

	// 保存相关 巡逻计划 信息
	@RequiresPermissions("patrol:ccmPatrolPlan:edit")
	@RequestMapping(value = "save")
	public String save(CcmPatrolPlan ccmPatrolPlan, Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		if (!beanValidator(model, ccmPatrolPlan)) {
			return form(ccmPatrolPlan, model);
		}
		ccmPatrolPlanService.save(ccmPatrolPlan);
		//
		addMessage(redirectAttributes, "保存巡逻计划成功");
		return "redirect:" + Global.getAdminPath() + "/patrol/ccmPatrolPlan/?repage";
	}

	@RequiresPermissions("patrol:ccmPatrolPlan:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPatrolPlan ccmPatrolPlan, RedirectAttributes redirectAttributes) {
		ccmPatrolPlanService.delete(ccmPatrolPlan);
		addMessage(redirectAttributes, "删除巡逻计划成功");
		return "redirect:" + Global.getAdminPath() + "/patrol/ccmPatrolPlan/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String selectIds, @RequestParam(required = false) String module,
			@RequestParam(required = false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		// 每周
		if ("02".equals(module)) {
			// 获取 周
			for (int i = 1; i <= 7; i++) {
				// 一周的时间
				String[] weeks = { "一", "二", "三", "四", "五", "六", "日" };
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", "W" + i);
				map.put("pId", "ccm_week_data");
				map.put("name", "周" + weeks[i - 1]);
				mapList.add(map);
			}
			// 获取全周
			Map<String, Object> mapWeek = Maps.newHashMap();
			mapWeek.put("id", "ccm_week_data");
			mapWeek.put("pId", "0");
			mapWeek.put("name", "全周");
			mapList.add(mapWeek);
		}
		// 每月
		if ("03".equals(module)) {
			for (int i = 1; i <= 31; i++) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", i + "M");
				map.put("pId", "ccm_month_data");
				map.put("name", i + "日");
				mapList.add(map);
			}
			// 获取全月
			Map<String, Object> mapMonth = Maps.newHashMap();
			mapMonth.put("id", "ccm_month_data");
			mapMonth.put("pId", "0");
			mapMonth.put("name", "全月");
			mapList.add(mapMonth);
		}
		return mapList;
	}

	// 全部点位 显示GEoJson  
	@ResponseBody
	@RequestMapping(value = "patrolPlanMap")
	public GeoJSON patrolPlanMap(@RequestParam(required = false) String pointSort,
			@RequestParam(required = false) String pointid, CcmPatrolPlan ccmPatrolPlan, Model model) {
		// 对象信息进行分割
		String[] Points = (StringUtils.isNotBlank(pointSort) ? pointSort : "").split(";");
		// 顺序值
		Map<String, String> pointSortMap = Maps.newHashMap();
		if (Points.length > 0) {
			for (int i = 0; i < Points.length; i++) {
				pointSortMap.put(Points[i], "" + (i + 1));
			}
		}
		// 查询地图点位信息
		List<CcmPatrolPoint> ccmPatrolPointist = ccmPatrolPointService.findList(new CcmPatrolPoint());
		// 返回对象
		GeoJSON geoJSON = new GeoJSON();
		List<Features> featureList = new ArrayList<Features>();
		// 数组
		for (CcmPatrolPoint PatrolPoint : ccmPatrolPointist) {
			// 特征,属性
			Features featureDto = new Features();
			Properties properties = new Properties();
			// 2 id 添加
			featureDto.setId(PatrolPoint.getId());
			// 3 properties 展示属性信息
			properties.setName(PatrolPoint.getName());
			// 点击后展示详细属性值
			Map<String, Object> map_P = new HashMap<String, Object>();
			// 创建附属信息
			map_P.put("name", PatrolPoint.getName());
			// 如果当前的点 为 包含改点
			if (pointSort.contains(PatrolPoint.getId())) {
				map_P.put("flag", true);
				map_P.put("sort", pointSortMap.get(PatrolPoint.getId()));
			} else {
				map_P.put("flag", false);
			}
			properties.addInfo(map_P);
			featureList.add(featureDto);
			featureDto.setProperties(properties);
			// 4 geometry 配置参数
			Geometry geometry = new Geometry();
			featureDto.setGeometry(geometry);
			// 点位信息 测试为点
			geometry.setType("Point");
			// 为中心点赋值
			if (!StringUtils.isEmpty(PatrolPoint.getAreaPoint())) {
				// 获取中心点的值
				String[] centpoint = PatrolPoint.getAreaPoint().split(",");
				// 图层中心的
				geoJSON.setCentpoint(centpoint);
				// 图形中心点
				properties.setCoordinateCentre(centpoint);
			}
			// 添加具体数据
			// 封装的list
			List<String> Coordinateslist = new ArrayList<>();
			// 当前是否为空如果为空则进行添加空数组 ，否则进行拆分添加数据
			String[] a = (StringUtils.isEmpty(PatrolPoint.getAreaPoint()) ? (",") : PatrolPoint.getAreaPoint())
					.split(",");
			// 填充数据
			if (a.length < 2) {
				Coordinateslist.add("");
				Coordinateslist.add("");
			} else {
				Coordinateslist.add(a[0]);
				Coordinateslist.add(a[1]);
			}
			// 装配点位
			geometry.setCoordinates(Coordinateslist);
		}
		// 添加数据
		geoJSON.setFeatures(featureList);
		// 如果无数据
		if (featureList.size() == 0) {
			return null;
		}
		return geoJSON;
	}

	// 点位显示图跳转
	@RequestMapping(value = "mapcheck")
	public String mapcheck(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/modules/mapping/patrol/pointCheck";
	}

	// 计划的名称
	@RequestMapping(value = "planNames")
	public String planNames(@RequestParam(required = false) String type,  
			@RequestParam(required = false) String purposeType, Model model) {
		List<CcmPatrolPlan> ccmPatrolPlans = ccmPatrolPlanService.findList(new CcmPatrolPlan());
		model.addAttribute("plan", ccmPatrolPlans);
		model.addAttribute("purposeType", purposeType);
		return "/modules/mapping/patrol/plantype";
	}

}