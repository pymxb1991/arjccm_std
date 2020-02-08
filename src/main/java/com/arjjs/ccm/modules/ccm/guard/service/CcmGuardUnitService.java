/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.guard.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.guard.entity.CcmGuardUnit;
import com.arjjs.ccm.modules.ccm.guard.dao.CcmGuardUnitDao;

/**
 * 警卫单位Service
 * @author lijiupeng
 * @version 2019-07-17
 */
@Service
@Transactional(readOnly = true)
public class CcmGuardUnitService extends CrudService<CcmGuardUnitDao, CcmGuardUnit> {

	public CcmGuardUnit get(String id) {
		return super.get(id);
	}
	
	public List<CcmGuardUnit> findList(CcmGuardUnit ccmGuardUnit) {
		return super.findList(ccmGuardUnit);
	}
	
	public Page<CcmGuardUnit> findPage(Page<CcmGuardUnit> page, CcmGuardUnit ccmGuardUnit) {
		return super.findPage(page, ccmGuardUnit);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmGuardUnit ccmGuardUnit) {
		super.save(ccmGuardUnit);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmGuardUnit ccmGuardUnit) {
		super.delete(ccmGuardUnit);
	}

	
}