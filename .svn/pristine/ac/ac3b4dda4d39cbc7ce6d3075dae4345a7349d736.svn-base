/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEmergencyPlan;
import com.arjjs.ccm.modules.ccm.event.dao.CcmEmergencyPlanDao;

/**
 * 应急预案Service
 * @author pengjianqiang
 * @version 2018-02-23
 */
@Service
@Transactional(readOnly = true)
public class CcmEmergencyPlanService extends CrudService<CcmEmergencyPlanDao, CcmEmergencyPlan> {

	
	public CcmEmergencyPlan get(String id) {
		return super.get(id);
	}
	
	public List<CcmEmergencyPlan> findList(CcmEmergencyPlan ccmEmergencyPlan) {
		return super.findList(ccmEmergencyPlan);
	}
	
	public Page<CcmEmergencyPlan> findPage(Page<CcmEmergencyPlan> page, CcmEmergencyPlan ccmEmergencyPlan) {
		return super.findPage(page, ccmEmergencyPlan);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmEmergencyPlan ccmEmergencyPlan) {
		super.save(ccmEmergencyPlan);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmEmergencyPlan ccmEmergencyPlan) {
		super.delete(ccmEmergencyPlan);
	}
	
}