/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.service.TreeService;
import com.arjjs.ccm.modules.ccm.rest.dao.CcmRestOfficeDao;
import com.arjjs.ccm.modules.sys.dao.OfficeDao;
import com.arjjs.ccm.modules.sys.entity.Office;

/**
 * 区域Service
 * @author admin001
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class CcmRestOfficeService extends TreeService<CcmRestOfficeDao, Office> {

	@Autowired
	private CcmRestOfficeDao ccmRestOfficeDao;
	
	public List<Office> findAll(){
		List<Office> officeList = ccmRestOfficeDao.findList(new Office());
		
		return officeList;
	}

	public List<Office> findOfficeUser() {
		List<Office> officeList = ccmRestOfficeDao.findOfficeUser(new Office());
		return officeList;
	}

	public List<Office> findByParentIdsLike(Office office) {
		List<Office> officeList = ccmRestOfficeDao.findByParentIdsLike(office);
		return officeList;
	}



}
