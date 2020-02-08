package com.arjjs.ccm.modules.flat.flow.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.modules.flat.flow.dao.PlanFlowManageDao;
import com.arjjs.ccm.modules.flat.flow.entity.PlanFlowManage;
import com.arjjs.ccm.modules.flat.planstep.entity.BphPlanStep;
import com.arjjs.ccm.modules.flat.planstep.service.BphPlanStepService;
import com.arjjs.ccm.modules.flat.stepaction.entity.BphStepAction;
import com.arjjs.ccm.modules.flat.stepaction.service.BphStepActionService;

import net.sf.json.JSONObject;

@Service
@Transactional(readOnly = true)
public class PlanFlowManageService{

	@Autowired
	private PlanFlowManageDao planFlowManageDao;
	@Autowired
	private BphPlanStepService bphPlanStepService;
	@Autowired
	private BphStepActionService bphStepActionService;
	
	public List<PlanFlowManage> getAllList(PlanFlowManage planFlowManage){
		return planFlowManageDao.getAllList(planFlowManage);
	}
	
	public List<PlanFlowManage> getActionStep(){
		return planFlowManageDao.getActionStep();
	}
	
	public List<PlanFlowManage> planTree(){
		return planFlowManageDao.planTree();
	}
	
	@Transactional(readOnly = false)
	public void saveBphPlanStep(String paramBphPlanStep) throws IOException{
		paramBphPlanStep = java.net.URLDecoder.decode(paramBphPlanStep, "UTF-8");
		JSONObject jsonObject = JSONObject.fromObject(paramBphPlanStep);
		BphPlanStep bphPlanStep = new BphPlanStep();
		if (null != jsonObject) {
			if (jsonObject.containsKey("stepId")) {
				String stepId = jsonObject.getString("stepId");
				bphPlanStep.setStepId(stepId);
			}
			if(jsonObject.containsKey("planId")) {
				String planId = jsonObject.getString("planId");
				bphPlanStep.setPlanId(planId);
			}
		}
		bphPlanStepService.save(bphPlanStep);
	}
	
	@Transactional(readOnly = false)
	public void saveBphStepAction(String paramBphStepAction) throws IOException {
		paramBphStepAction = java.net.URLDecoder.decode(paramBphStepAction, "UTF-8");
		JSONObject jsonObject = JSONObject.fromObject(paramBphStepAction);
		BphStepAction bphStepAction = new BphStepAction();
		if (null != jsonObject) {
			if (jsonObject.containsKey("stepId")) {
				String stepId = jsonObject.getString("stepId");
				bphStepAction.setStepId(stepId);
			}
			if(jsonObject.containsKey("actionId")) {
				String actionId = jsonObject.getString("actionId");
				bphStepAction.setActionId(actionId);
			}
		}
		bphStepActionService.save(bphStepAction);
	}
	
	@Transactional(readOnly = false)
	public void deleteBphPlanStep(String  paramDelPlanStep) throws IOException{
		paramDelPlanStep = java.net.URLDecoder.decode(paramDelPlanStep, "UTF-8");
		JSONObject jsonObject = JSONObject.fromObject(paramDelPlanStep);
		BphPlanStep bphPlanStep = new BphPlanStep();
		if(null != jsonObject) {
			if(jsonObject.containsKey("stepId") && jsonObject.containsKey("planId")) {
				String stepId = jsonObject.getString("stepId");
				String planId = jsonObject.getString("planId");
				bphPlanStep.setStepId(stepId);
				bphPlanStep.setPlanId(planId);
			}
		}
		bphPlanStepService.deleteByStepIdAndPlanId(bphPlanStep);
	}
	
	@Transactional(readOnly = false)
	public void deleteBphStepAction(String paramDelStepAction) throws IOException {
		paramDelStepAction = java.net.URLDecoder.decode(paramDelStepAction, "UTF-8");
		JSONObject jsonObject = JSONObject.fromObject(paramDelStepAction);
		BphStepAction bphStepAction = new BphStepAction();
		if(jsonObject != null) {
			if(jsonObject.containsKey("actionId") && jsonObject.containsKey("stepId")) {
				String actionId = jsonObject.getString("actionId");
				String stepId = jsonObject.getString("stepId");
				bphStepAction.setActionId(actionId);
				bphStepAction.setStepId(stepId);
			}
		}
		bphStepActionService.deleteByStepIdAndActionId(bphStepAction);
	}
}
