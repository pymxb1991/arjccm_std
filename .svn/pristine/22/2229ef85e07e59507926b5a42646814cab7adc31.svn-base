/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolPoint;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.modules.ccm.event.dao.CcmEventCasedealDao;
import com.arjjs.ccm.modules.ccm.patrol.dao.CcmPatrolPointDao;

/**
 * 巡逻点位Service
 * @author arj
 * @version 2018-03-14
 */
@Service
@Transactional(readOnly = true)
public class CcmPatrolPointService extends CrudService<CcmPatrolPointDao, CcmPatrolPoint> {

	@Autowired
	private CcmPatrolPointDao ccmPatrolPointDao;
	
	
	
	public CcmPatrolPoint get(String id) {
		return super.get(id);
	}
	
	public List<CcmPatrolPoint> findList(CcmPatrolPoint ccmPatrolPoint) {
		return super.findList(ccmPatrolPoint);
	}
	
	public Page<CcmPatrolPoint> findPage(Page<CcmPatrolPoint> page, CcmPatrolPoint ccmPatrolPoint) {
		return super.findPage(page, ccmPatrolPoint);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPatrolPoint ccmPatrolPoint) {
		super.save(ccmPatrolPoint);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPatrolPoint ccmPatrolPoint) {
		super.delete(ccmPatrolPoint);
	}

	//巡逻路线
	public List<EchartType> findPatrolPointPlanMap() {
		return ccmPatrolPointDao.findPatrolPointPlanMap();
	}
	
}