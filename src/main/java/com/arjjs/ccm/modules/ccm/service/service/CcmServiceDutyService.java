/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.service.entity.CcmServiceDuty;
import com.arjjs.ccm.modules.ccm.service.dao.CcmServiceDutyDao;

/**
 * 工作职责Service
 * @author liang
 * @version 2018-08-02
 */
@Service
@Transactional(readOnly = true)
public class CcmServiceDutyService extends CrudService<CcmServiceDutyDao, CcmServiceDuty> {

	public CcmServiceDuty get(String id) {
		return super.get(id);
	}
	
	public List<CcmServiceDuty> findList(CcmServiceDuty ccmServiceDuty) {
		return super.findList(ccmServiceDuty);
	}
	
	public Page<CcmServiceDuty> findPage(Page<CcmServiceDuty> page, CcmServiceDuty ccmServiceDuty) {
		return super.findPage(page, ccmServiceDuty);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmServiceDuty ccmServiceDuty) {
		super.save(ccmServiceDuty);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmServiceDuty ccmServiceDuty) {
		super.delete(ccmServiceDuty);
	}
	
}