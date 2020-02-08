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
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolUnit;
import com.arjjs.ccm.modules.ccm.patrol.dao.CcmPatrolUnitDao;

/**
 * 巡逻单位Service
 * @author lijiupeng
 * @version 2019-07-08
 */
@Service
@Transactional(readOnly = true)
public class CcmPatrolUnitService extends CrudService<CcmPatrolUnitDao, CcmPatrolUnit> {

	@Autowired
	private CcmPatrolUnitDao ccmPatrolUnitDao;

	public CcmPatrolUnit get(String id) {
		return super.get(id);
	}
	
	public List<CcmPatrolUnit> findList(CcmPatrolUnit ccmPatrolUnit) {
		return super.findList(ccmPatrolUnit);
	}
	
	public Page<CcmPatrolUnit> findPage(Page<CcmPatrolUnit> page, CcmPatrolUnit ccmPatrolUnit) {
		return super.findPage(page, ccmPatrolUnit);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPatrolUnit ccmPatrolUnit) {
		super.save(ccmPatrolUnit);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPatrolUnit ccmPatrolUnit) {
		super.delete(ccmPatrolUnit);
	}



}