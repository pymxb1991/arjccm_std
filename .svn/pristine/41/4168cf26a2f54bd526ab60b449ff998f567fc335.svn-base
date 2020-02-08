/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.planstep.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.flat.planstep.dao.BphPlanStepDao;
import com.arjjs.ccm.modules.flat.planstep.entity.BphPlanStep;
import com.arjjs.ccm.modules.flat.stepinfo.entity.BphStepInfo;

/**
 * 预案过程关联表Service
 * @author liu
 * @version 2018-11-17
 */
@Service
@Transactional(readOnly = true)
public class BphPlanStepService extends CrudService<BphPlanStepDao, BphPlanStep> {

	public BphPlanStep get(String id) {
		return super.get(id);
	}
	
	public List<BphStepInfo> getStepByPlanId(BphPlanStep bphPlanStep) {
		return dao.getStepByPlanId(bphPlanStep);
	}
	
	
	public List<BphPlanStep> findList(BphPlanStep bphPlanStep) {
		return super.findList(bphPlanStep);
	}
	
	public Page<BphPlanStep> findPage(Page<BphPlanStep> page, BphPlanStep bphPlanStep) {
		return super.findPage(page, bphPlanStep);
	}
	
	@Transactional(readOnly = false)
	public void save(BphPlanStep bphPlanStep) {
		if (bphPlanStep.getIsNewRecord()){
			BphPlanStep bphPlanStepSort = new BphPlanStep();
			bphPlanStepSort = findLastSort(bphPlanStep);
			if(bphPlanStepSort != null) {
				bphPlanStep.setSort(bphPlanStepSort.getSort()+1);
			}else {
				bphPlanStep.setSort(1);
			}
		}
		super.save(bphPlanStep);
	}
	
	@Transactional(readOnly = false)
	public void delete(BphPlanStep bphPlanStep) {
		super.delete(bphPlanStep);
	}
	
	@Transactional(readOnly = false)
	public void deleteByStepIdAndPlanId(BphPlanStep bphPlanStep) {
		dao.deleteByStepIdAndPlanId(bphPlanStep);
	}
	
	public BphPlanStep findLastSort(BphPlanStep bphPlanStep) {
		return dao.findLastSort(bphPlanStep);
	}
	
}