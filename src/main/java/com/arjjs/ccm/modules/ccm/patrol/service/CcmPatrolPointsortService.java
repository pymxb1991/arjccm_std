/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolPointsort;
import com.arjjs.ccm.modules.ccm.patrol.dao.CcmPatrolPointsortDao;

/**
 * 巡逻点位顺序Service
 * @author arj
 * @version 2018-03-15
 */
@Service
@Transactional(readOnly = true)
public class CcmPatrolPointsortService extends CrudService<CcmPatrolPointsortDao, CcmPatrolPointsort> {

	public CcmPatrolPointsort get(String id) {
		return super.get(id);
	}
	
	public List<CcmPatrolPointsort> findList(CcmPatrolPointsort ccmPatrolPointsort) {
		return super.findList(ccmPatrolPointsort);
	}
	
	public Page<CcmPatrolPointsort> findPage(Page<CcmPatrolPointsort> page, CcmPatrolPointsort ccmPatrolPointsort) {
		return super.findPage(page, ccmPatrolPointsort);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPatrolPointsort ccmPatrolPointsort) {
		super.save(ccmPatrolPointsort);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPatrolPointsort ccmPatrolPointsort) {
		super.delete(ccmPatrolPointsort);
	}
	
}