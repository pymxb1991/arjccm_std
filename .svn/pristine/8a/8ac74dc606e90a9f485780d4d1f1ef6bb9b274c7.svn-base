package com.arjjs.ccm.modules.flat.flow.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arjjs.ccm.modules.flat.action.entity.BphActionInfo;
import com.arjjs.ccm.modules.flat.action.service.BphActionInfoService;
import com.arjjs.ccm.modules.flat.flow.entity.PlanFlowManage;
import com.arjjs.ccm.modules.flat.flow.service.PlanFlowManageService;
import com.arjjs.ccm.modules.flat.planstep.entity.BphPlanStep;
import com.arjjs.ccm.modules.flat.planstep.service.BphPlanStepService;
import com.arjjs.ccm.modules.flat.stepaction.entity.BphStepAction;
import com.arjjs.ccm.modules.flat.stepaction.service.BphStepActionService;
import com.arjjs.ccm.modules.flat.stepinfo.entity.BphStepInfo;
import com.arjjs.ccm.modules.flat.stepinfo.service.BphStepInfoService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

/**
 * 流程管理配置Controller
 * @author liu
 * @version 2018-11-14
 */
@Controller
@RequestMapping(value = "${adminPath}/fiow/planFlowManage")
public class PlanFlowManageController {

	@Autowired
	private PlanFlowManageService  planFlowManageService;
	@Autowired
	private BphActionInfoService bphActionInfoService;
	@Autowired
	private BphPlanStepService bphPlanStepService;
	@Autowired
	private BphStepInfoService bphStepInfoService;
	@Autowired
	private BphStepActionService bphStepActionService;
	
	@RequestMapping(value = "list")
	public String planFlowManage(){
		return "flat/flow/planFlowManageList";
	}
	
	@ResponseBody
	@RequestMapping(value = "planStepActionList")
	public List<Map<String, Object>> planStepActionList(PlanFlowManage planFlowManage,HttpServletRequest request, HttpServletResponse response) throws IOException{
//		JsonConfig jsonConfig = new JsonConfig();
//		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
//			@Override
//			public boolean apply(Object source, String name, Object value) {
//				if (name.equals("area") || name.equals("createBy") || name.equals("updateBy") || name.equals("currentUser") || name.equals("page") || name.equals("sqlMap") || name.equals("global") || name.equals("updateDate") || name.equals("createDate")) {
//					return true;
//				} else {
//					return false;
//				}
//			}
//		});
//		JSONArray planStepActionList = JSONArray.fromObject(planFlowManageService.getAllList(planFlowManage),jsonConfig);
//		response.getWriter().print(planStepActionList);
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<PlanFlowManage> list = planFlowManageService.getAllList(planFlowManage);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", list.get(i).getId());
			map.put("pId", list.get(i).getpId());
			map.put("name", list.get(i).getName());
			map.put("typeClass", list.get(i).getTypeClass());
			map.put("typeCode", list.get(i).getTypeCode());
			mapList.add(map);
		}
		return mapList;
	}
	
	@ResponseBody
	@RequestMapping(value = "stepActionList")
	public List<Map<String, Object>> stepActionList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		/*JsonConfig jsonConfig = new JsonConfig();
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
		JSONArray stepActionList = JSONArray.fromObject(planFlowManageService.getActionStep(),jsonConfig);
		response.getWriter().print(stepActionList);*/
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<PlanFlowManage> list = planFlowManageService.getActionStep();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", list.get(i).getId());
			map.put("pId", list.get(i).getpId());
			map.put("name", list.get(i).getName());
			map.put("typeClass", list.get(i).getTypeClass());
			mapList.add(map);
		}
		return mapList;
	}
	
	@ResponseBody
	@RequestMapping(value = "actionList")
	public void actionList(BphActionInfo bphActionInfo,HttpServletRequest request, HttpServletResponse response) throws IOException{
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
		JSONArray actionList = JSONArray.fromObject(bphActionInfoService.findAllListBphActionInfo(bphActionInfo),jsonConfig);
		response.getWriter().print(actionList);
	}
	
	@ResponseBody
	@RequestMapping(value = "planTree")
	public List<Map<String, Object>> planTree(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<PlanFlowManage> list = planFlowManageService.planTree();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", list.get(i).getId());
			map.put("pId", list.get(i).getpId());
			map.put("name", list.get(i).getName());
			map.put("typeClass", list.get(i).getTypeClass());
			map.put("typeCode", list.get(i).getTypeCode());
			mapList.add(map);
		}
		return mapList;
	}
	
	@ResponseBody
	@RequestMapping(value = "planStepTree")
	public List<Map<String, Object>> planStepTree(HttpServletRequest request, HttpServletResponse response,String id) throws IOException {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<BphStepInfo> list = Lists.newArrayList();
		BphPlanStep bphPlanStep = new BphPlanStep();
		if(id != null && id != "") {
			bphPlanStep.setPlanId(id);
			List<BphStepInfo> bphStepList = bphPlanStepService.getStepByPlanId(bphPlanStep);
			for (BphStepInfo bphStepInfo : bphStepList) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", bphStepInfo.getId());
				map.put("pId", bphStepInfo.getPlanId());
				map.put("name", bphStepInfo.getName());
				mapList.add(map);
			}
		}else {
			list = bphStepInfoService.getListStep();
			if(list.size()>0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map = Maps.newHashMap();
					map.put("id", list.get(i).getId());
					map.put("pId", "");
					map.put("name", list.get(i).getName());
					mapList.add(map);
				}
			}
		}
		return mapList;
	}
	
	@ResponseBody
	@RequestMapping(value = "planActionTree")
	public List<Map<String, Object>> planActionTree(HttpServletRequest request, HttpServletResponse response,String id) throws IOException {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		BphStepAction bphStepAction = new BphStepAction();
		bphStepAction.setStepId(id);
		List<BphActionInfo> bphActionList = bphStepActionService.getActionByStepId(bphStepAction);
		for(BphActionInfo bphActionInfo : bphActionList) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", bphActionInfo.getId());
			map.put("pId", bphActionInfo.getStepId());
			map.put("name", bphActionInfo.getName());
			map.put("type", bphActionInfo.getType());
			map.put("title", bphActionInfo.getTitle());
			mapList.add(map);
		}
		return mapList;
	}
	
	@RequestMapping(value = "saveBphPlanStep")
	public void saveBphPlanStep(HttpServletRequest request, HttpServletResponse response, String paramBphPlanStep) throws IOException {
		planFlowManageService.saveBphPlanStep(paramBphPlanStep);
	}
	
	@RequestMapping(value = "saveBphStepAction")
	public void saveBphStepAction(HttpServletRequest request, HttpServletResponse response, String paramBphStepAction) throws IOException {
		planFlowManageService.saveBphStepAction(paramBphStepAction);
	}
	
	@RequestMapping(value = "deleteBphPlanStep")
	public void deleteBphPlanStep(HttpServletRequest request, HttpServletResponse response,String paramDelPlanStep) throws IOException {
		planFlowManageService.deleteBphPlanStep(paramDelPlanStep);
	}
	@RequestMapping(value = "deleteBphStepAction")
	public void deleteBphStepAction(HttpServletRequest request, HttpServletResponse response,String paramDelStepAction) throws IOException {
		planFlowManageService.deleteBphStepAction(paramDelStepAction);
	}
	
}
