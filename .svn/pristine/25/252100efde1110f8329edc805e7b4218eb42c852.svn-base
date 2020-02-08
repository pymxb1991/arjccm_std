/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.rest.entity.CcmUserGroup;
import com.arjjs.ccm.modules.ccm.rest.dao.CcmUserGroupDao;

/**
 * 用户好友分组Service
 * @author fu
 * @version 2018-03-08
 */
@Service
@Transactional(readOnly = true)
public class CcmUserGroupService extends CrudService<CcmUserGroupDao, CcmUserGroup> {
	
	
	@Autowired
	CcmUserGroupDao ccmUserGroupDao;

	public CcmUserGroup get(String id) {
		return super.get(id);
	}
	
	public List<CcmUserGroup> findList(CcmUserGroup ccmUserGroup) {
		return super.findList(ccmUserGroup);
	}
	
	public Page<CcmUserGroup> findPage(Page<CcmUserGroup> page, CcmUserGroup ccmUserGroup) {
		return super.findPage(page, ccmUserGroup);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmUserGroup ccmUserGroup) {
		super.save(ccmUserGroup);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmUserGroup ccmUserGroup) {
		super.delete(ccmUserGroup);
	}

	public List<CcmUserGroup> findListByUserId(String userId) {
		return ccmUserGroupDao.findListByUserId(userId);
	}
	
}