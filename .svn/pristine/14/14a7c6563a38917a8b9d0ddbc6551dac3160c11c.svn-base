/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.duty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.duty.entity.CcmWorkerDuty;
import com.arjjs.ccm.modules.ccm.duty.dao.CcmWorkerDutyDao;

/**
 * 社工职责Service
 * @author yiqingxuan
 * @version 2019-06-20
 */
@Service
@Transactional(readOnly = true)
public class CcmWorkerDutyService extends CrudService<CcmWorkerDutyDao, CcmWorkerDuty> {

	public CcmWorkerDuty get(String id) {
		return super.get(id);
	}
	
	public List<CcmWorkerDuty> findList(CcmWorkerDuty ccmWorkerDuty) {
		return super.findList(ccmWorkerDuty);
	}
	
	public Page<CcmWorkerDuty> findPage(Page<CcmWorkerDuty> page, CcmWorkerDuty ccmWorkerDuty) {
		return super.findPage(page, ccmWorkerDuty);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmWorkerDuty ccmWorkerDuty) {
		super.save(ccmWorkerDuty);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmWorkerDuty ccmWorkerDuty) {
		super.delete(ccmWorkerDuty);
	}
	
}