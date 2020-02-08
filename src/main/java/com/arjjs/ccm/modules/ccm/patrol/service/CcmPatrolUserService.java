/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolUser;
import com.arjjs.ccm.modules.ccm.patrol.dao.CcmPatrolUserDao;

/**
 * 巡逻人员Service
 * @author arj
 * @version 2018-03-14
 */
@Service
@Transactional(readOnly = true)
public class CcmPatrolUserService extends CrudService<CcmPatrolUserDao, CcmPatrolUser> {

	public CcmPatrolUser get(String id) {
		return super.get(id);
	}
	
	public List<CcmPatrolUser> findList(CcmPatrolUser ccmPatrolUser) {
		return super.findList(ccmPatrolUser);
	}
	
	public Page<CcmPatrolUser> findPage(Page<CcmPatrolUser> page, CcmPatrolUser ccmPatrolUser) {
		return super.findPage(page, ccmPatrolUser);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPatrolUser ccmPatrolUser) {
		super.save(ccmPatrolUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPatrolUser ccmPatrolUser) {
		super.delete(ccmPatrolUser);
	}
	
}