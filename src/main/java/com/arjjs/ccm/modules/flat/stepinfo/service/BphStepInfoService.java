/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.stepinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.flat.actionuser.entity.BphActionUser;
import com.arjjs.ccm.modules.flat.actionuser.service.BphActionUserService;
import com.arjjs.ccm.modules.flat.planinfo.entity.BphPlanInfo;
import com.arjjs.ccm.modules.flat.stepinfo.dao.BphStepInfoDao;
import com.arjjs.ccm.modules.flat.stepinfo.entity.BphStepInfo;

/**
 * 预案过程Service
 * @author zhanghao
 * @version 2018-11-14
 */
@Service
@Transactional(readOnly = true)
public class BphStepInfoService extends CrudService<BphStepInfoDao, BphStepInfo> {

	@Autowired
	private BphActionUserService bphActionUserService;
	
	public BphStepInfo get(String id) {
		return super.get(id);
	}
	
	public List<BphStepInfo> getStepData(BphStepInfo bphStepInfo) {
		return dao.getStepData(bphStepInfo);
	}
	
	public List<BphStepInfo> getListStep() {
		return dao.getListStep();
	}
	
	public List<BphStepInfo> findList(BphStepInfo bphStepInfo) {
		return super.findList(bphStepInfo);
	}
	
	public Page<BphStepInfo> findPage(Page<BphStepInfo> page, BphStepInfo bphStepInfo) {
		return super.findPage(page, bphStepInfo);
	}
	
	public List<BphStepInfo> findListByPlanInfoId(String planId){
		BphPlanInfo bphPlanInfo = new BphPlanInfo();
		bphPlanInfo.setId(planId);
		return dao.findListByPlanInfoId(bphPlanInfo);
	}
	
	public List<BphStepInfo> findListBySteId(String stepId){
		BphStepInfo bphStepInfo = new BphStepInfo();
		bphStepInfo.setId(stepId);
		return dao.findListBySteId(bphStepInfo);
	}
	
	public List<BphActionUser> findListByActionId(String actionId){
		BphActionUser bphActionUser =new BphActionUser();
		bphActionUser.setActionId(actionId);
		return bphActionUserService.getIdList(bphActionUser);
	}
	
	@Transactional(readOnly = false)
	public void save(BphStepInfo bphStepInfo) {
		super.save(bphStepInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(BphStepInfo bphStepInfo) {
		super.delete(bphStepInfo);
	}
	
	@Transactional(readOnly = false)
	public  BphStepInfo checkStepName(BphStepInfo bphStepInfo) {
		return this.dao.checkStepName(bphStepInfo);
	}
}