/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolMissions;
import com.arjjs.ccm.modules.ccm.patrol.dao.CcmPatrolMissionsDao;

/**
 * 巡逻任务Service
 * @author lijiupeng
 * @version 2019-07-05
 */
@Service
@Transactional(readOnly = false)
public class CcmPatrolMissionsService extends CrudService<CcmPatrolMissionsDao, CcmPatrolMissions> {

	@Autowired
	private CcmPatrolMissionsDao ccmPatrolMissionsDao;

	public CcmPatrolMissions get(String id) {
		return super.get(id);
	}
	
	public List<CcmPatrolMissions> findList(CcmPatrolMissions ccmPatrolMissions) {
		return super.findList(ccmPatrolMissions);
	}
	
	public Page<CcmPatrolMissions> findPage(Page<CcmPatrolMissions> page, CcmPatrolMissions ccmPatrolMissions) {
		return super.findPage(page, ccmPatrolMissions);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPatrolMissions ccmPatrolMissions) {
		super.save(ccmPatrolMissions);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPatrolMissions ccmPatrolMissions) {
		super.delete(ccmPatrolMissions);
	}

	public List<CcmPatrolMissions> findListByDate(Date startDate, Date endDate){
		return ccmPatrolMissionsDao.findListByDate(startDate,endDate);
	}
	public int updateStatus(String val,String id){
		return ccmPatrolMissionsDao.updateStatus(val,id);
	}
	
}