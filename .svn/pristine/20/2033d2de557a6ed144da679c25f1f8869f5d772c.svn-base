package com.arjjs.ccm.modules.flat.planManage.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.modules.flat.alarm.entity.BphAlarmInfo;
import com.arjjs.ccm.modules.flat.alarm.service.BphAlarmInfoService;
import com.arjjs.ccm.modules.flat.alarmhandlelog.entity.BphAlarmHandleLog;
import com.arjjs.ccm.modules.flat.alarmhandlelog.service.BphAlarmHandleLogService;
import com.arjjs.ccm.modules.flat.planinfo.service.BphPlanInfoService;
import com.arjjs.ccm.modules.flat.realtimeSituation.entity.DataList;
import com.arjjs.ccm.modules.flat.realtimeSituation.service.RealtimeSituationService;
import com.arjjs.ccm.modules.flat.stepinfo.service.BphStepInfoService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

@Controller
@RequestMapping(value = "${adminPath}/flat/planManage")
public class PlanManageController {

	@Autowired
	private RealtimeSituationService realtimeSituationService;
	@Autowired
	private BphAlarmInfoService bphAlarmInfoService;
	@Autowired
	private BphAlarmHandleLogService bphAlarmHandleLogService;
	@Autowired
	private BphPlanInfoService bphPlanInfoService;
	@Autowired
	private BphStepInfoService bphStepInfoservice;
	
	@RequestMapping(value = "planManage")
	public String planManage(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/flat/planManage/planManageList";
	}
	
	/**
	 * 方法说明：周边查询，主要功能说明：通过传入x,y,半径等计算出范围并查询所在范围内的所有数据
	 * @param request
	 * @param response
	 * @param param
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "findCircumData")
	public void findCircumData(HttpServletRequest request, HttpServletResponse response,DataList dataList) throws IOException{
		response.getWriter().print(realtimeSituationService.findList(dataList));
	}
	
	/**
	 * 方法说明：修改警情级别
	 * @param req
	 * @param resp
	 * @param bphAlarmInfo
	 * @throws IOException
	 */
	
	@ResponseBody
	@RequestMapping(value = "updateAlarmInfo")
	public void updateAlarmInfo(HttpServletRequest req, HttpServletResponse resp, BphAlarmInfo bphAlarmInfo) throws IOException {
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
	 * 方法说明：处置分析
	 * @param bphAlarmHandleLog
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "findHandleLog")
	public List<BphAlarmHandleLog> findHandleLog(BphAlarmHandleLog bphAlarmHandleLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<BphAlarmHandleLog> HandleLogList = bphAlarmHandleLogService.findHandleLog(bphAlarmHandleLog);
		return HandleLogList;
	}
	
	/**
	 * 方法说明：警情详情查询
	 * @param req
	 * @param resp
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "alarmDetail")
	@ResponseBody
	public BphAlarmInfo alarmDetail(HttpServletRequest req, HttpServletResponse resp, String id) throws IOException {
		return bphAlarmInfoService.get(id);
	}
	
	/**
	 * 方法说明：预案关联查询
	 * @param request
	 * @param response
	 * @param isImportant
	 * @param typeCode
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "planAssociated")
	public void planAssociated(HttpServletRequest request, HttpServletResponse response,String isImportant,String typeCode) throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("area") || name.equals("createBy") || name.equals("updateBy") || name.equals("currentUser") || name.equals("page") || name.equals("sqlMap") || name.equals("global") || name.equals("updateDate") || name.equals("createDate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		response.getWriter().print(JSONArray.fromObject(bphPlanInfoService.findByIsImportantAndTypeCode(isImportant,typeCode),jsonConfig));;
	}
	
	/**
	 * 方法说明：查询案情关联的预案、过程、动作信息
	 * @param request
	 * @param response
	 * @param isImportant
	 * @param typeCode
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "planProcessData")
	public void planProcessData(HttpServletRequest request, HttpServletResponse response,String planId) throws IOException{
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("area") || name.equals("createBy") || name.equals("updateBy") || name.equals("currentUser") || name.equals("page") || name.equals("sqlMap") || name.equals("global") || name.equals("updateDate") || name.equals("createDate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		response.getWriter().print(JSONArray.fromObject(bphStepInfoservice.findListByPlanInfoId(planId),jsonConfig));
	}
	
	@ResponseBody
	@RequestMapping(value = "planActionData")
	public void planActionData(HttpServletRequest request, HttpServletResponse response,String stepId) throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("area") || name.equals("createBy") || name.equals("updateBy") || name.equals("currentUser") || name.equals("page") || name.equals("sqlMap") || name.equals("global") || name.equals("updateDate") || name.equals("createDate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		response.getWriter().print(JSONArray.fromObject(bphStepInfoservice.findListBySteId(stepId),jsonConfig));
	}
	
	/**
	 * 方法说明：根据动作查询用户
	 * @param request
	 * @param response
	 * @param actionId
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "planActionUsers")
	public void planActionUsers(HttpServletRequest request, HttpServletResponse response,String actionId) throws IOException {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("area") || name.equals("createBy") || name.equals("updateBy") || name.equals("currentUser") || name.equals("page") || name.equals("sqlMap") || name.equals("global") || name.equals("updateDate") || name.equals("createDate")) {
					return true;
				} else {
					return false;
				}
			}
		});
		response.getWriter().print(JSONArray.fromObject(bphStepInfoservice.findListByActionId(actionId),jsonConfig));
	}
	
	/**
	 * 方法说明：添加过程记录信息
	 * @param request
	 * @param response
	 * @param alarmId
	 * @param operateDesc
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "insertHandleData")
	public String insertHandleData(HttpServletRequest request, HttpServletResponse response,String alarmId,String operateDesc) throws IOException {
		int i = bphAlarmHandleLogService.insert(alarmId,operateDesc);
		if(i > 0) {
			return "0";
		}else {
			return "-1";
		}
	}
	
}
