/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolResult;
import com.arjjs.ccm.modules.ccm.patrol.dao.CcmPatrolResultDao;

/**
 * 巡逻结果Service
 * @author arj
 * @version 2018-03-16
 */
@Service
@Transactional(readOnly = true)
public class CcmPatrolResultService extends CrudService<CcmPatrolResultDao, CcmPatrolResult> {

	public CcmPatrolResult get(String id) {
		return super.get(id);
	}
	
	public List<CcmPatrolResult> findList(CcmPatrolResult ccmPatrolResult) {
		return super.findList(ccmPatrolResult);
	}
	
	public Page<CcmPatrolResult> findPage(Page<CcmPatrolResult> page, CcmPatrolResult ccmPatrolResult) {
		return super.findPage(page, ccmPatrolResult);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPatrolResult ccmPatrolResult) {
		super.save(ccmPatrolResult);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPatrolResult ccmPatrolResult) {
		super.delete(ccmPatrolResult);
	}
	
}