/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.rest.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.utils.Collections3;
import com.arjjs.ccm.common.utils.DateUtils;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolPlan;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolRespoint;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolResult;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolUser;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmTracingpoint;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolPlanService;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolRespointService;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolResultService;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmPatrolUserService;
import com.arjjs.ccm.modules.ccm.patrol.service.CcmTracingpointService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestResult;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmRestType;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.CommUtil;
import com.arjjs.ccm.tool.CommUtilRest;
import com.arjjs.ccm.tool.geoJson.Features;
import com.arjjs.ccm.tool.geoJson.GeoJSON;
import com.arjjs.ccm.tool.geoJson.Geometry;
import com.arjjs.ccm.tool.geoJson.Properties;

/**
 * 巡逻结果信息 Controller
 * 
 * @author arj
 * @version 2018-03-17
 */
@Controller
@RequestMapping(value = "${appPath}/rest/patrol/ccmRestPatrolResult")
public class CcmRestPatrolResultController extends BaseController {

	// 巡逻结果
	@Autowired
	private CcmPatrolResultService CcmPatrolResultService;
	// 巡逻点位结果
	@Autowired
	private CcmPatrolRespointService ccmPatrolRespointService;
	// 巡逻用户信息
	@Autowired
	private CcmPatrolUserService ccmpatrolUserService;
	// 实时轨迹点位信息
	@Autowired
	private CcmTracingpointService ccmTracingpointService;
	// 巡逻计划
	@Autowired
	private CcmPatrolPlanService ccmPatrolPlanService;

	/**
	 * 
	 * @param id
	 *            巡逻结果Id
	 * @return
	 * @author arj
	 * @version 2018-03-17
	 */
	@ResponseBody
	@RequestMapping(value = "/getresult", method = RequestMethod.GET)
	public CcmRestResult getresult(String userId, HttpServletRequest req, HttpServletResponse resp, String id)
			throws IOException {
		// 获取results
		CcmRestResult result = CommUtilRest.getResult(userId, req, resp, id);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		// 当前 是否为空
		CcmPatrolResult ccmPatrolResult = CcmPatrolResultService.get(id);
		// 添加 巡逻点位情况
		CcmPatrolRespoint ccmPatrolRespoint = new CcmPatrolRespoint();
		ccmPatrolRespoint.setResultId(ccmPatrolResult);
		List<CcmPatrolRespoint> pointlist = ccmPatrolRespointService.findList(ccmPatrolRespoint);
		ccmPatrolResult.setCcmPatrolRespointList(pointlist);

		result.setCode(CcmRestType.OK);
		result.setResult(ccmPatrolResult);
		return result;
	}

	/**
	 * @see 查询所有结果信息
	 * @param pageNo
	 * @param pageSiz
	 * @return
	 * @author arj
	 * @version 2018-03-12
	 */
	@ResponseBody
	@RequestMapping(value = "/queryresult", method = RequestMethod.GET)
	public CcmRestResult queryresult(String userId, HttpServletRequest req, HttpServletResponse resp,
			CcmPatrolResult ccmPatrolResult) throws IOException {
		// 获取结果
		CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		Page<CcmPatrolResult> page = CcmPatrolResultService.findPage(new Page<CcmPatrolResult>(req, resp),
				(null == ccmPatrolResult) ? new CcmPatrolResult() : ccmPatrolResult);
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		// 输出结果
		return result;
	}

	/**
	 * 查看已添加巡逻结果信息
	 * 
	 * @param userId
	 * @param req
	 * @param resp
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/getrespoint", method = RequestMethod.GET)
	public CcmRestResult getrespoint(String userId, HttpServletRequest req, HttpServletResponse resp, String id)
			throws IOException {
		// 获取results
		CcmRestResult result = CommUtilRest.getResult(userId, req, resp, id);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		// 当前 是否为空
		CcmPatrolRespoint ccmPatrolRespoint = ccmPatrolRespointService.get(id);
		result.setCode(CcmRestType.OK);
		result.setResult(ccmPatrolRespoint);
		return result;
	}

	/**
	 * 查看所有点位巡逻情况
	 * 
	 * @param userId
	 * @param req
	 * @param resp
	 * @param ccmPatrolResult
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/queryrespoint", method = RequestMethod.GET)
	public CcmRestResult queryrespoint(String userId, HttpServletRequest req, HttpServletResponse resp,
			CcmPatrolRespoint ccmPatrolRespoint) throws IOException {
		// 获取结果
		CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		// 查询所有点位情况
		Page<CcmPatrolRespoint> page = ccmPatrolRespointService.findPage(new Page<CcmPatrolRespoint>(req, resp),
				(null == ccmPatrolRespoint) ? new CcmPatrolRespoint() : ccmPatrolRespoint);
		result.setCode(CcmRestType.OK);
		result.setResult(page.getList());
		// 输出结果
		return result;
	}

	/**
	 * 保存单个 巡检点位结果
	 * 
	 * @param userId
	 * @param ccmPatrolRespoint
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/saverespoint", method = RequestMethod.POST)
	public CcmRestResult modifyrespoint(String userId, CcmPatrolRespoint ccmPatrolRespoint, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		// 获取结果
		CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}
		// 如果创建者为空
		if (ccmPatrolRespoint.getIsNewRecord()) {
			User userDto = new User(userId);
			ccmPatrolRespoint.setCreateBy(userDto);
			ccmPatrolRespoint.setUpdateBy(userDto);
		}
		ccmPatrolRespointService.save(ccmPatrolRespoint);
		// 返回
		result.setCode(CcmRestType.OK);
		result.setResult("成功");
		return result;
	}

	/**
	 * 
	 * @param enddate 巡检结果的开始时间
	 * @param planId 巡检结果对应的巡检计划Id
	 * @param begindate 巡检结果的结束时间
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "patrolPlanMap")
	public GeoJSON patrolPlanMap(@RequestParam(required = false) Date enddate,
			@RequestParam(required = false) String planId, Model model,
			@RequestParam(required = false) Date begindate) {
		// 获取所需的计划Dto
		CcmPatrolUser ccmPatrolUserDto = new CcmPatrolUser();
		ccmPatrolUserDto.setPlanId(planId);
		// 获取所有计划内用户
		List<CcmPatrolUser> CcmPatrolUserlist = ccmpatrolUserService.findList(ccmPatrolUserDto);
		// 返回对象
		GeoJSON geoJSON = new GeoJSON();
		List<Features> featureList = new ArrayList<Features>();
		// 数组
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
			// 当前的 点位信息是否存在
			if (StringUtils.isEmpty(Tracingpoins)) {
				map_P.put("flag", false);
			} else {
				map_P.put("flag", true);
			}
			// 如果当前的点 为 包含改点
			properties.addInfo(map_P);
			featureList.add(featureDto);
			featureDto.setProperties(properties);
			// 4 geometry 配置参数
			Geometry geometry = new Geometry();
			featureDto.setGeometry(geometry);
			// 点位信息 测试为面
			geometry.setType("LineString");
			// 查询所有的轨迹点信息
			// 以下是线
			String[] coordinates = (StringUtils.isEmpty(Tracingpoins) ? " ;" : Tracingpoins).split(";");
			// 返回无孔结果 2层数据一个数据源
			List<String[]> Coordinateslist = new ArrayList<>();
			for (int i = 0; i < coordinates.length; i++) {
				// 线 至少 有两个点
				if (coordinates.length > 1) {
					String corrdinate = coordinates[i];
					// 以“,”为分割数据
					String[] a = corrdinate.split(",");
					Coordinateslist.add(a);
				}
			}
			// 获取最后的结果
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

	/**
	 * 开始计划 完成对于 计划结果 信息 的添加。
	 * 
	 * @param userId
	 * @param planId
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/startplan", method = RequestMethod.GET)
	public CcmRestResult startplan(String userId, @RequestParam(required = false) String planId, HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		// 获取结果
		CcmRestResult result = CommUtilRest.queryResult(userId, req, resp);
		// 如果当前的 flag 为返回
		if (result.isReturnFlag()) {
			return result;
		}

		CcmPatrolPlan ccmPatrolPlanDto = new CcmPatrolPlan();
		ccmPatrolPlanDto.setId(planId);
		// 巡逻计划
		CcmPatrolPlan ccmPatrolPlan = ccmPatrolPlanService.get(ccmPatrolPlanDto);
		// 先查 当前结果是否已经存在
		CcmPatrolResult ccmPatrolResultDto = new CcmPatrolResult();
		ccmPatrolResultDto.setPlan(ccmPatrolPlan);
		ccmPatrolResultDto.setBeginDate(DateUtils.parseDate(DateUtils.getDate()));
		ccmPatrolResultDto.setEndDate(DateUtils.parseDate(CommUtil.getSpecifiedDayAfter(DateUtils.getDate())));
		List<CcmPatrolResult> CcmPatrolResultExit = CcmPatrolResultService.findList(ccmPatrolResultDto);
		// 如果当前查询结果已经存在
		if(CcmPatrolResultExit.size()>0){
			result.setCode(CcmRestType.OK);
			result.setResult(CcmPatrolResultExit.get(0));
			return result;
		}
		
		
		// 生成 巡逻结果对象
		CcmPatrolResult ccmPatrolResult = new CcmPatrolResult();
		// 巡逻结果对象 填充
		ccmPatrolResult.setName(ccmPatrolPlan.getName() + "-" + DateUtils.getDate());
		ccmPatrolResult.setBeginDate(new Date());
		ccmPatrolResult.setPlan(ccmPatrolPlan);
		// 默认审核未通过
		ccmPatrolResult.setStatus("2");

		// 如果创建者为空
		if (ccmPatrolResult.getIsNewRecord()) {
			User userDto = new User(userId);
			ccmPatrolResult.setCreateBy(userDto);
			ccmPatrolResult.setUpdateBy(userDto);
		}
		CcmPatrolResultService.save(ccmPatrolResult);
		// 返回
		result.setCode(CcmRestType.OK);
		result.setResult(ccmPatrolResult);
		return result;
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