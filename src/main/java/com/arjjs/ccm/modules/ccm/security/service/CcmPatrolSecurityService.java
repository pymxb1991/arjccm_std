/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.security.entity.CcmPatrolSecurity;
import com.arjjs.ccm.modules.ccm.security.dao.CcmPatrolSecurityDao;

/**
 * 警卫管理Service
 * @author lijiupeng
 * @version 2019-07-11
 */
@Service
@Transactional(readOnly = true)
public class CcmPatrolSecurityService extends CrudService<CcmPatrolSecurityDao, CcmPatrolSecurity> {

	@Autowired
	private CcmPatrolSecurityDao ccmPatrolSecurityDao;

	public CcmPatrolSecurity get(String id) {
		return super.get(id);
	}
	
	public List<CcmPatrolSecurity> findList(CcmPatrolSecurity ccmPatrolSecurity) {
		return super.findList(ccmPatrolSecurity);
	}
	
	public Page<CcmPatrolSecurity> findPage(Page<CcmPatrolSecurity> page, CcmPatrolSecurity ccmPatrolSecurity) {
		return super.findPage(page, ccmPatrolSecurity);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPatrolSecurity ccmPatrolSecurity) {
		super.save(ccmPatrolSecurity);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPatrolSecurity ccmPatrolSecurity) {
		super.delete(ccmPatrolSecurity);
	}
	@Transactional(readOnly = false)
	public int updateStatus(String val,String id){
		return ccmPatrolSecurityDao.updateStatus(val,id);
	}

}