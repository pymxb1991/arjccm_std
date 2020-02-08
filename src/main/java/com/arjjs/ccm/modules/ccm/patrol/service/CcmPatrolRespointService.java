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
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolRespoint;
import com.arjjs.ccm.modules.ccm.patrol.dao.CcmPatrolRespointDao;

/**
 * 巡逻点位结果Service
 * 
 * @author arj
 * @version 2018-03-16
 */
@Service
@Transactional(readOnly = true)
public class CcmPatrolRespointService extends CrudService<CcmPatrolRespointDao, CcmPatrolRespoint> {

	@Autowired
	private CcmPatrolRespointDao ccmPatrolRespointDao;

	public CcmPatrolRespoint get(String id) {
		return super.get(id);
	}

	public List<CcmPatrolRespoint> findList(CcmPatrolRespoint ccmPatrolRespoint) {
		return super.findList(ccmPatrolRespoint);
	}

	public Page<CcmPatrolRespoint> findPage(Page<CcmPatrolRespoint> page, CcmPatrolRespoint ccmPatrolRespoint) {
		return super.findPage(page, ccmPatrolRespoint);
	}

	@Transactional(readOnly = false)
	public void save(CcmPatrolRespoint ccmPatrolRespoint) {
		super.save(ccmPatrolRespoint);
	}

	@Transactional(readOnly = false)
	public void delete(CcmPatrolRespoint ccmPatrolRespoint) {
		super.delete(ccmPatrolRespoint);
	}

	// 检测是否存在该点位信息
	public boolean exitCheck(CcmPatrolRespoint ccmPatrolRespoint) {
		int count = ccmPatrolRespointDao.exitCheck(ccmPatrolRespoint);
		return count > 0 ? true:false ;
	}
	// exitCheck
}