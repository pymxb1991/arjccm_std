/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.common.utils.Collections3;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolPlan;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolPoint;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolPointsort;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolRespoint;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolResult;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolUser;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmTracingpoint;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolPlanService;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolPointService;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolPointsortService;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolRespointService;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolResultService;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolUserService;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmTracingpointService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.CommUtil;
import com.arjjs.ccm.tool.geoJson.Features;
import com.arjjs.ccm.tool.geoJson.GeoJSON;
import com.arjjs.ccm.tool.geoJson.Geometry;
import com.arjjs.ccm.tool.geoJson.Properties;
import com.google.common.collect.Lists;

/**
 * 巡逻结果Controller
 * 
 * @author arj
 * @version 2018-03-16
 */
@Controller
@RequestMapping(value = "${adminPath}/patrol/ccmPatrolResult")
public class CcmPatrolResultController extends BaseController {

	// 巡逻结果
	@Autowired
	private CcmPatrolResultService ccmPatrolResultService;
	// 巡逻结果点位情况
	@Autowired
	private CcmPatrolRespointService ccmPatrolRespointService;
	// 巡逻计划
	@Autowired
	private CcmPatrolPlanService ccmPatrolPlanService;
	// 巡逻计划用户
	@Autowired
	private CcmPatrolUserService CcmPatrolUserService;
	// 巡逻用户实时点位信息
	@Autowired
	private CcmTracingpointService ccmTracingpointService;
	// 巡逻点位信息
	@Autowired
	private CcmPatrolPointService ccmPatrolPointService;
	// 巡逻计划 所涉及的点位信息
	@Autowired
	private CcmPatrolPointsortService ccmPatrolPointSortService;

	@ModelAttribute
	public CcmPatrolResult get(@RequestParam(required = false) String id) {
		CcmPatrolResult entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = ccmPatrolResultService.get(id);
		}
		if (entity == null) {
			entity = new CcmPatrolResult();
		}
		return entity;
	}

	@RequiresPermissions("patrol:ccmPatrolResult:view")
	@RequestMapping(value = { "list", "" })
	public String list(CcmPatrolResult ccmPatrolResult, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<CcmPatrolResult> page = ccmPatrolResultService.findPage(new Page<CcmPatrolResult>(request, response),
				ccmPatrolResult);
		model.addAttribute("page", page);
		return "ccm/patrol/ccmPatrolResultList";
	}

	@RequiresPermissions("patrol:ccmPatrolResult:view")
	@RequestMapping(value = "form")
	public String form(CcmPatrolResult ccmPatrolResult, Model model) {
		// 计算相关 点位信息
		if (StringUtils.isNotBlank(ccmPatrolResult.getId())) {
			// 巡逻结果点位对象
			CcmPatrolRespoint ccmPatrolRespointDto = new CcmPatrolRespoint();
			// 巡逻结果查询对象
			CcmPatrolResult ccmPatrolResultdto = new CcmPatrolResult();
			ccmPatrolResultdto.setId(ccmPatrolResult.getId());
			ccmPatrolRespointDto.setResultId(ccmPatrolResultdto);
			List<CcmPatrolRespoint> pointlist = ccmPatrolRespointService.findList(ccmPatrolRespointDto);
			model.addAttribute("pointlist", pointlist);
			// 计划
			CcmPatrolPlan ccmPatrolPlanDto = new CcmPatrolPlan();
			ccmPatrolPlanDto.setId(ccmPatrolResult.getPlan().getId());
			CcmPatrolPlan ccmPatrolPlan = ccmPatrolPlanService.get(ccmPatrolPlanDto);
			model.addAttribute("ccmPatrolPlan", ccmPatrolPlan);
		}

		// 加载基础信息
		model.addAttribute("ccmPatrolResult", ccmPatrolResult);
		return "ccm/patrol/ccmPatrolResultForm";
	}

	@RequiresPermissions("patrol:ccmPatrolResult:edit")
	@RequestMapping(value = "save")
	public String save(CcmPatrolResult ccmPatrolResult, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, ccmPatrolResult)) {
			return form(ccmPatrolResult, model);
		}
		ccmPatrolResultService.save(ccmPatrolResult);
		addMessage(redirectAttributes, "保存巡逻结果成功");
		return "redirect:" + Global.getAdminPath() + "/patrol/ccmPatrolResult/?repage";
	}

	@RequiresPermissions("patrol:ccmPatrolResult:edit")
	@RequestMapping(value = "delete")
	public String delete(CcmPatrolResult ccmPatrolResult, RedirectAttributes redirectAttributes) {
		ccmPatrolResultService.delete(ccmPatrolResult);
		addMessage(redirectAttributes, "删除巡逻结果成功");
		return "redirect:" + Global.getAdminPath() + "/patrol/ccmPatrolResult/?repage";
	}

	// 计划结果的名称
	@RequestMapping(value = "resultNames")
	public String resultNames(@RequestParam(required = false) String type,
			@RequestParam(required = false) String purposeType, Model model) {
		List<CcmPatrolResult> ccmPatrolResults = ccmPatrolResultService.findList(new CcmPatrolResult());
		model.addAttribute("result", ccmPatrolResults);
		model.addAttribute("purposeType", purposeType);
		return "/modules/mapping/patrol/resultype";
	}

	/**
	 * 启动 巡逻计划 生成 巡逻结果 内容
	 * 
	 * @param planId
	 * @param req
	 * @param resp
	 */
	@RequiresPermissions("patrol:ccmPatrolResult:edit")
	@RequestMapping(value = "/startplan", method = RequestMethod.GET)
	public String startplan(@RequestParam(required = false) String planId, HttpServletRequest req,
			HttpServletResponse resp, RedirectAttributes redirectAttributes) {
		// 巡逻计划 查询对象
		CcmPatrolPlan ccmPatrolPlanDto = new CcmPatrolPlan();
		ccmPatrolPlanDto.setId(planId);
		// 巡逻计划
		CcmPatrolPlan ccmPatrolPlan = ccmPatrolPlanService.get(ccmPatrolPlanDto);
		// 优先查询是否有已经 生成的 结果
		CcmPatrolResult ccmPatrolResultDto = new CcmPatrolResult();
		ccmPatrolResultDto.setBeginDate(DateUtils.parseDate(DateUtils.getDate()));
		ccmPatrolResultDto.setEndDate(DateUtils.parseDate(CommUtil.getSpecifiedDayAfter(DateUtils.getDate())));
		ccmPatrolResultDto.setPlan(ccmPatrolPlan);
		List<CcmPatrolResult> CcmPatrolResultExit = ccmPatrolResultService.findList(ccmPatrolResultDto);
		if(CcmPatrolResultExit.size()>0)
		{
			addMessage(redirectAttributes, "巡逻结果已经存在");
			return "redirect:" + Global.getAdminPath() + "/patrol/ccmPatrolPlan/?repage";
		}
		// 生成 巡逻结果对象
		CcmPatrolResult ccmPatrolResult = new CcmPatrolResult();
		// 巡逻结果对象 填充
		ccmPatrolResult.setName(ccmPatrolPlan.getName() + "-" + DateUtils.getDate());
		ccmPatrolResult.setBeginDate(new Date());
		ccmPatrolResult.setPlan(ccmPatrolPlan);
		// 默认审核未通过
		ccmPatrolResult.setStatus("2");
		ccmPatrolResultService.save(ccmPatrolResult);
		return "redirect:" + Global.getAdminPath() + "/patrol/ccmPatrolResult/?repage";
	}

	// 点位显示图跳转
	@RequestMapping(value = "mapcheck")
	public String mapcheck(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/modules/mapping/patrol/resultCheck";
	}

	// 全部点位 显示GEoJson
	@ResponseBody
	@RequestMapping(value = "patrolPlanMap")
	public GeoJSON patrolPlanMap(@RequestParam(required = false) Date enddate,
			@RequestParam(required = false) String planId, Model model,
			@RequestParam(required = false) Date begindate) {
		// 获取所需的计划Dto
		CcmPatrolUser ccmPatrolUserDto = new CcmPatrolUser();
		ccmPatrolUserDto.setPlanId(planId);
		// 获取所有计划内用户
		List<CcmPatrolUser> CcmPatrolUserlist = CcmPatrolUserService.findList(ccmPatrolUserDto);

		// 返回对象 - featureList 点位list 信息
		GeoJSON geoJSON = new GeoJSON();
		List<Features> featureList = new ArrayList<Features>();
		// 用户轨迹点位 list
		for (CcmPatrolUser PatrolUser : CcmPatrolUserlist) {
			// 查询点位信息
			CcmTracingpoint CcmTracingpointDto = new CcmTracingpoint();
			CcmTracingpointDto.setBeginCurDate(begindate);
			CcmTracingpointDto.setEndCurDate(enddate);
			// 必须的用户信息
			User userDto = new User();
			userDto.setId(PatrolUser.getUser().getId());
			CcmTracingpointDto.setUser(userDto);
			List<CcmTracingpoint> TracingpointList = ccmTracingpointService.findList(CcmTracingpointDto);
			String Tracingpoins = Collections3.extractToString(TracingpointList, "areaPoint", ";");
			// 特征,属性
			Features featureDto = new Features();
			Properties properties = new Properties();
			// 2 id 添加 用户ID 且不唯一
			featureDto.setId(PatrolUser.getId());
			// 3 properties 展示用户信息
			properties.setName(PatrolUser.getUser().getName());
			// 点击后展示详细属性值
			Map<String, Object> map_P = new HashMap<String, Object>();
			// 创建附属信息
			map_P.put("color", "#" + getRandColorCode());
			// 设置当前为 线
			map_P.put("type", "LineString");
			// 当前的 点位信息是否存在
			if (StringUtils.isBlank(Tracingpoins)) {
				map_P.put("flag", false);
			} else {
				map_P.put("flag", true);
			}
			// 如果当前的点 为 包含改点
			properties.addInfo(map_P);
			featureList.add(featureDto);
			featureDto.setProperties(properties);
			// 判断当前是否有 用户的轨迹点位
			if (StringUtils.isNoneBlank(Tracingpoins)) {
				// 4 geometry 配置参数
				Geometry geometry = new Geometry();
				featureDto.setGeometry(geometry);
				// 点位信息 测试为面
				geometry.setType("LineString");
				// 查询所有的轨迹点信息-以下是线
				String[] coordinates = (StringUtils.isEmpty(Tracingpoins) ? " ;" : Tracingpoins).split(";");
				// 返回无孔结果 2层数据一个数据源
				List<String[]> Coordinateslist = Lists.newArrayList();
				for (int i = 0; i < coordinates.length; i++) {
					// 线 至少 有两个点
					if (coordinates.length >= 1) {
						String corrdinate = coordinates[i];
						// 以“,”为分割数据
						String[] a = corrdinate.split(",");
						Coordinateslist.add(a);
					}
				}
				geometry.setCoordinates(Coordinateslist);
			}
		}
		// 查询所有地图点位信息
		List<CcmPatrolPoint> ccmPatrolPointList = ccmPatrolPointService.findList(new CcmPatrolPoint());
		// 巡逻计划点位信息
		CcmPatrolPointsort ccmPatrolPointsortDto = new CcmPatrolPointsort();
		ccmPatrolPointsortDto.setPlanId(planId);
		List<CcmPatrolPointsort> ccmPatrolPointsortList = ccmPatrolPointSortService.findList(ccmPatrolPointsortDto);
		// 计划所包含的所有点位ID
		String sortIds = Collections3.extractToString(ccmPatrolPointsortList, "pointId", ",");
		// 巡逻计划 点位
		for (CcmPatrolPoint PatrolPoint : ccmPatrolPointList) {
			// 如果 不包含则 跳过这个点
			if (!sortIds.contains(PatrolPoint.getId())) {
				continue;
			}
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
			map_P.put("type", "Point");
			map_P.put("flag", false);
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

	// 获取随机颜色值
	public static String getRandColorCode() {
		String r, g, b;
		Random random = new Random();
		r = Integer.toHexString(random.nextInt(256)).toUpperCase();
		g = Integer.toHexString(random.nextInt(256)).toUpperCase();
		b = Integer.toHexString(random.nextInt(256)).toUpperCase();
		r = r.length() == 1 ? "0" + r : r;
		g = g.length() == 1 ? "0" + g : g;
		b = b.length() == 1 ? "0" + b : b;
		return r + g + b;
	}


}