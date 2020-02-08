/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.alarm.web;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.utils.SpringContextHolder;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.web.BaseController;
import com.arjjs.ccm.modules.flat.alarm.entity.BphAlarmHandlePlanStepActionData;
import com.arjjs.ccm.modules.flat.alarm.entity.BphAlarmInfo;
import com.arjjs.ccm.modules.flat.alarm.service.BphAlarmInfoService;
import com.arjjs.ccm.modules.flat.handle.service.BphAlarmHandleService;
import com.arjjs.ccm.tool.LayUIBean;
import com.arjjs.ccm.tool.Result;
import com.arjjs.ccm.tool.geoJson.GeoJSON;
import com.google.common.collect.Maps;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import org.apache.ibatis.annotations.Param;
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
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实时警情Controller
 * 
 * @author wangyikai
 * @version 2018-11-14
 */
@Controller
@RequestMapping(value = "${adminPath}/alarm/bphAlarmInfo")
public class BphAlarmInfoController extends BaseController {

	@Autowired
	private BphAlarmInfoService bphAlarmInfoService;

	@ModelAttribute
	public BphAlarmInfo get(@RequestParam(required = false) String id) {
		BphAlarmInfo entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = bphAlarmInfoService.get(id);
		}
		if (entity == null) {
			entity = new BphAlarmInfo();
		}
		return entity;
	}

	@RequestMapping(value = "index")
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "flat/alarm/bphAlarmInfoList";
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public LayUIBean list(BphAlarmInfo bphAlarmInfo, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<BphAlarmInfo> bphAlarmInfoList = bphAlarmInfoService.findList(bphAlarmInfo);
		Integer num = bphAlarmInfoService.countAlarmList(bphAlarmInfo);
		LayUIBean result = new LayUIBean();
		result.setCount(num);
		result.setData(bphAlarmInfoList);
		result.setCode(Result.ERROR_OK);
		result.setMsg("");
		return result;
	}

	@RequestMapping(value = "form")
	public String form(BphAlarmInfo bphAlarmInfo, Model model) {
		model.addAttribute("bphAlarmInfo", bphAlarmInfo);
		BphAlarmHandleService bean = SpringContextHolder.getBean("bphAlarmHandleService");
		model.addAttribute("bphAlarmHandle", bean.findAlarmHandlePlanStepAction(bphAlarmInfo.getId()));
		return "flat/alarm/bphAlarmInfoForm";
	}

	@RequestMapping(value = "save")
	public String save(BphAlarmInfo bphAlarmInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bphAlarmInfo)) {
			return form(bphAlarmInfo, model);
		}
		if (bphAlarmInfo.getState() == null) {
			bphAlarmInfo.setState("0");
		}
		bphAlarmInfoService.save(bphAlarmInfo);
		addMessage(redirectAttributes, "保存警情成功");
		return "redirect:" + Global.getAdminPath() + "/alarm/bphAlarmInfo/index/?repage";
	}

	@ResponseBody
	@RequestMapping(value = "eventToAlarmInfo")
	public Object eventToAlarmInfo(String eventId, RedirectAttributes redirectAttributes){
		if(StringUtils.isBlank(eventId)){
			addMessage(redirectAttributes, "保存警情是失败");
		}
		boolean b = bphAlarmInfoService.eventToAlarmInfo(eventId);
		if(b){
			addMessage(redirectAttributes, "保存警情成功");
		}else {
			addMessage(redirectAttributes, "保存警情是失败");
		}
		HashMap<String,Object> result = Maps.newHashMapWithExpectedSize(2);
		result.put("code", "200");
		return result;
	}

	@RequestMapping(value = "deleteAlarm")
	public String deleteAlarm(BphAlarmInfo bphAlarmInfo, RedirectAttributes redirectAttributes) {
		bphAlarmInfoService.deleteBphAlarmInfo(bphAlarmInfo);
		addMessage(redirectAttributes, "删除警情成功");
		return "redirect:" + Global.getAdminPath() + "/alarm/bphAlarmInfo/index";
	}

	@ResponseBody
	@RequestMapping(value = "delete")
	public int deleteBphAlarmInfo(BphAlarmInfo bphAlarmInfo, RedirectAttributes redirectAttributes) {
		return bphAlarmInfoService.deleteBphAlarmInfo(bphAlarmInfo);
	}

	/**
	 * @author wangyikai
	 * @方法描述 通过时间和案件类型查询警情信息四色图
	 */
	@ResponseBody
	@RequestMapping(value = "queryAlarmInfoByDateAndAlarmTypeToFourColor")
	public GeoJSON queryAlarmInfoByDateAndAlarmTypeToFourColor(BphAlarmInfo bphAlarmInfo) {
		return bphAlarmInfoService.queryAlarmInfoByDateAndAlarmTypeToFourColor(bphAlarmInfo);
	}

	/**
	 * @author wangyikai
	 * @方法描述 通过时间和案件类型查询警情信息热力图和聚合图
	 */
	@ResponseBody
	@RequestMapping(value = "queryAlarmInfoByDateAndAlarmType")
	public GeoJSON queryAlarmInfoByDateAndAlarmType(BphAlarmInfo bphAlarmInfo) {
		return bphAlarmInfoService.queryAlarmInfoByDateAndAlarmType(bphAlarmInfo);
	}

	/**
	 * @desc 查询当天的实时警情
	 * @param req
	 * @param resp
	 * @param bphAlarmInfo
	 * @param
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "queryAllAlarmSituation")
	public void queryAllAlarmSituation(HttpServletRequest req, HttpServletResponse resp, BphAlarmInfo bphAlarmInfo)
			throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("area") || name.equals("createBy") || name.equals("updateBy")
						|| name.equals("currentUser") || name.equals("page") || name.equals("sqlMap")
						|| name.equals("global") || name.equals("updateDate") || name.equals("createDate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		List<BphAlarmInfo> bphAlarmInfoList = bphAlarmInfoService.queryAlarmSituation(bphAlarmInfo);
		resp.getWriter().print(JSONArray.fromObject(bphAlarmInfoList, jsonConfig));
	}

	/**
	 * 查询历史警情
	 * 
	 * @param bphAlarmInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "queryHisAlarmSituation")
	public void queryHisAlarmSituation(HttpServletRequest req, HttpServletResponse resp, BphAlarmInfo bphAlarmInfo)
			throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("area") || name.equals("createBy") || name.equals("updateBy")
						|| name.equals("currentUser") || name.equals("page") || name.equals("sqlMap")
						|| name.equals("global") || name.equals("updateDate") || name.equals("createDate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		List<BphAlarmInfo> bphAlarmInfoList = bphAlarmInfoService.queryHisAlarmSituation(bphAlarmInfo);
		resp.getWriter().print(JSONArray.fromObject(bphAlarmInfoList, jsonConfig));
	}

	/**
	 * @方法描述 修改警情信息
	 * @param req
	 * @param resp
	 * @param bphAlarmInfo
	 * @param state
	 * @param beginAlarmtime
	 * @param endAlarmTime
	 * @throws IOException
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "updateAlarmInfo")
	public void updateAlarmInfo(HttpServletRequest req, HttpServletResponse resp, BphAlarmInfo bphAlarmInfo)
			throws IOException, ParseException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("createBy") || name.equals("updateBy") || name.equals("currentUser")
						|| name.equals("page") || name.equals("sqlMap") || name.equals("global")
						|| name.equals("updateDate") || name.equals("createDate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		resp.getWriter().print(JSONObject.fromObject(bphAlarmInfoService.updateAlarmInfo(bphAlarmInfo), jsonConfig));
	}

	/**
	 * @desc 警情详情
	 * @param req
	 * @param resp
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "alarmDetail")
	public BphAlarmHandlePlanStepActionData alarmDetail(HttpServletRequest req, HttpServletResponse resp, String id) {
		BphAlarmHandlePlanStepActionData bphAlarmHandlePlanStepActionData = new BphAlarmHandlePlanStepActionData();
		bphAlarmHandlePlanStepActionData.setAlarmInfo(get(id));
		BphAlarmHandleService bean = SpringContextHolder.getBean("bphAlarmHandleService");
		bphAlarmHandlePlanStepActionData.setAlarmHandleList(bean.findAlarmHandlePlanStepAction(id));
		bphAlarmHandlePlanStepActionData.setAlarmHandleFileResultList(bphAlarmInfoService.getAlarmHandleFile(id));
		return bphAlarmHandlePlanStepActionData;
	}

	@RequestMapping(value = "listNewest")
	public String listNewest(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<BphAlarmInfo> list = bphAlarmInfoService.findNewestAlarmInfo();
		model.addAttribute("list", list);
		return "flat/home/bphNewestAlarmInfoList";
	}

	/**
	 * @desc 门户今日统计
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "countDtae")
	public String countDtae(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<BphAlarmInfo> list = bphAlarmInfoService.countDtae();
		model.addAttribute("list", list);
		return "flat/home/bphTodayCountList";
	}

	/**
	 * @desc 今日警情摘要
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "findPieData")
	public String findPieData(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> map = bphAlarmInfoService.findPieData();
		model.addAttribute("jsonData", map);
		return "flat/home/bphTodayAlarmRemarkList";
	}

	/**
	 * @方法说明 获取周边资源图层数据
	 * @param type
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getPeripheralResources")
	public GeoJSON getPeripheralResources(String type) {
		return this.bphAlarmInfoService.getPeripheralResources(type);
	}

	/**
	 * @desc 初始化统计近15天各类警情数量页面
	 */
	@RequestMapping(value = "statCountByAlarmTypeIndex")
	public String statCountByAlarmTypeIndex(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/flat/home/bphAlarmInfoTypeStat";
	}

	/**
	 * @desc 统计近10天各类警情数量
	 */
	@ResponseBody
	@RequestMapping(value = "statCountByAlarmType")
	public Object statCountByAlarmType(HttpServletRequest request, HttpServletResponse response) {
		return this.bphAlarmInfoService.statCountByAlarmType();
	}

	/**
	 * @desc 初始化近10天警情数量和上月同期警情数量对比页面
	 */
	@RequestMapping(value = "sameDayContrastStatIndex")
	public String sameDayContrastStatIndex(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/flat/home/bphSameAlarmDayContrastStat";
	}

	/**
	 * @desc 近10天警情数量和上月同期警情数量对比
	 */
	@ResponseBody
	@RequestMapping(value = "sameDayContrastStat")
	public Object sameDayContrastStat(HttpServletRequest request, HttpServletResponse response) {
		return this.bphAlarmInfoService.sameDayContrastStat();
	}

	/**
	 * @desc 今日按警情类型统计各部门百分比
	 * @param typeCode
	 */
	@RequestMapping(value = "statPercentByAlarmType")
	public String statPercentByAlarmType(BphAlarmInfo bphAlarmInfo, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<BphAlarmInfo> list = bphAlarmInfoService.statPercentByAlarmType(bphAlarmInfo);
		Map<String, Object> map = bphAlarmInfoService.findPieData();
		model.addAttribute("data", list);
		model.addAttribute("typeCode", bphAlarmInfo.getTypeCode());
		model.addAttribute("jsonData", map);
		return "/flat/home/bphStatPercentByAlarmType";
	}

	/**
	 * @desc 按部门和警情类型统计近30天趋势
	 * @param typeCode
	 * @param officeId
	 */
	@RequestMapping(value = "statCountByOfficeAndAlarmType")
	public String statCountByOfficeAndAlarmType(BphAlarmInfo bphAlarmInfo, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<BphAlarmInfo> percentList = bphAlarmInfoService.statPercentByAlarmType(bphAlarmInfo);
		List<BphAlarmInfo> countList = bphAlarmInfoService.statCountByOfficeAndAlarmType(bphAlarmInfo);
		model.addAttribute("percentData", percentList);
		model.addAttribute("typeCode", bphAlarmInfo.getTypeCode());
		model.addAttribute("type", bphAlarmInfo.getTypeName());
		model.addAttribute("countData", countList);
		return "/flat/home/bphStatCountByOfficeAndAlarmType";
	}

	/**
	 * @desc 查询警情总条数
	 * @param request
	 * @param response
	 * @param bphAlarmInfo
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "findCount")
	public Integer findCount(HttpServletRequest request, HttpServletResponse response, BphAlarmInfo bphAlarmInfo)
			throws IOException {
		return bphAlarmInfoService.findCount(bphAlarmInfo);
	}

	/**
	 * @description 请求手机APP视频
	 * @author wangyikai
	 */
	@ResponseBody
	@RequestMapping(value = "requestAppVideo")
	public Result requestAppVideo(String userId) {
		return bphAlarmInfoService.requestAppVideo(userId);
	}

	/**
	 * @desc 按派出所分组统计警情总数
	 * @author wangyikai
	 */
	@RequestMapping(value = "statCountGroupOffice")
	public String statCountGroupOffice(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> map = bphAlarmInfoService.findPieData();
		model.addAttribute("typeStat", map);
		Map<String, Object> officeMap = bphAlarmInfoService.statCountGroupOffice();
		model.addAttribute("officeStat", officeMap);
		return "/flat/home/bphAlarmStatGroupOffice";
	}

	/**
	 * @desc 按指定派出所分类型统计警情总数
	 * @param officeId
	 * @author wangyikai
	 */
	@RequestMapping(value = "statPercentByOffice")
	public String statPercentByOffice(HttpServletRequest request, HttpServletResponse response, Model model,
			BphAlarmInfo bphAlarmInfo) {
		Map<String, Object> officeMap = bphAlarmInfoService.statCountGroupOffice();
		model.addAttribute("officeStat", officeMap);
		Map<String, Object> typeMap = bphAlarmInfoService.statPercentByOffice(bphAlarmInfo);
		model.addAttribute("typeMap", typeMap);
		return "/flat/home/bphAlarmPercentStatByOffice";
	}

	/**
	 * @desc 按指定派出所统计近12个月各警情类型趋势
	 * @param officeId
	 */
	@RequestMapping(value = "statCountByOffice")
	public String statCountByOffice(BphAlarmInfo bphAlarmInfo, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Map<String, Object> typeMap = bphAlarmInfoService.statPercentByOffice(bphAlarmInfo);
		model.addAttribute("typeMap", typeMap);
		Map<String, Object> officeMap = bphAlarmInfoService.statCountByOffice(bphAlarmInfo);
		model.addAttribute("data", officeMap);
		return "/flat/home/bphStatCountByOffice";
	}

	/**
	 * @desc
	 * @param request
	 * @param response
	 * @param handlePoliceId
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "findByHandlePoliceId")
	public void findByHandlePoliceId(HttpServletRequest request, HttpServletResponse response, String handlePoliceId)
			throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("createBy") || name.equals("updateBy") || name.equals("currentUser")
						|| name.equals("page") || name.equals("sqlMap") || name.equals("global")
						|| name.equals("updateDate") || name.equals("createDate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		response.getWriter()
				.print(JSONArray.fromObject(bphAlarmInfoService.findByHandlePoliceId(handlePoliceId), jsonConfig));
	}

	/**
	 * @desc 地图警情派警查询警员列表
	 * @param alarmId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "queryPoliceByAlarmId")
	public LayUIBean queryPoliceByAlarmId(String alarmId) {
		return bphAlarmInfoService.queryPoliceByAlarmId(alarmId);
	}

	/**
	 * @desc 查询派发、签收、到达超时的警情
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "queryTimeoutAlarm")
	public Map<String, Object> queryTimeoutAlarm() {
		return bphAlarmInfoService.queryTimeoutAlarm();
	}
	
	@ResponseBody
	@RequestMapping(value = "insertAlarmByIdForOffice")
	public boolean insertAlarmByIdForOffice(@Param("id")String id,@Param("officeId")String officeId) {
		return bphAlarmInfoService.insertAlarmByIdForOffice(id, officeId);
	}
}