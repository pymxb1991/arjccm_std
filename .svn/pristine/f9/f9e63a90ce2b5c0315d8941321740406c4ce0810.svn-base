/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.tenant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.tenant.entity.CcmTenantRecord;
import com.arjjs.ccm.modules.ccm.tenant.dao.CcmTenantRecordDao;

/**
 * 历史租客记录表Service
 * @author lgh
 * @version 2019-04-20
 */
@Service
@Transactional(readOnly = true)
public class CcmTenantRecordService extends CrudService<CcmTenantRecordDao, CcmTenantRecord> {
	
	@Autowired
	private CcmTenantRecordDao ccmTenantRecordDao;

	public CcmTenantRecord get(String id) {
		return super.get(id);
	}
	
	public List<CcmTenantRecord> findList(CcmTenantRecord ccmTenantRecord) {
		return super.findList(ccmTenantRecord);
	}
	
	public Page<CcmTenantRecord> findPage(Page<CcmTenantRecord> page, CcmTenantRecord ccmTenantRecord) {
		return super.findPage(page, ccmTenantRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmTenantRecord ccmTenantRecord) {
		super.save(ccmTenantRecord);
	}
	@Transactional(readOnly = false)
	public void update(CcmTenantRecord ccmTenantRecord) {
		ccmTenantRecordDao.update(ccmTenantRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmTenantRecord ccmTenantRecord) {
		super.delete(ccmTenantRecord);
	}
	
	
}