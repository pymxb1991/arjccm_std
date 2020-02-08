/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgComprehensive;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseSchoolrim;
import com.arjjs.ccm.modules.ccm.org.dao.CcmOrgComprehensiveDao;

/**
 * 综治机构Service
 * @author liang
 * @version 2018-01-12
 */
@Service
@Transactional(readOnly = true)
public class CcmOrgComprehensiveService extends CrudService<CcmOrgComprehensiveDao, CcmOrgComprehensive> {

	//新填officeId查询
	@Autowired
	private CcmOrgComprehensiveDao ccmOrgComprehensiveDao;
	
	public CcmOrgComprehensive get(String id) {
		return super.get(id);
	}
	

	//新填officeId查询
	public CcmOrgComprehensive findOfficeId(String officeId) {
		return ccmOrgComprehensiveDao.findOfficeId(officeId);
	}
	
	public List<CcmOrgComprehensive> findList(CcmOrgComprehensive ccmOrgComprehensive) {
		return super.findList(ccmOrgComprehensive);
	}
	
	public Page<CcmOrgComprehensive> findPage(Page<CcmOrgComprehensive> page, CcmOrgComprehensive ccmOrgComprehensive) {
		return super.findPage(page, ccmOrgComprehensive);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmOrgComprehensive ccmOrgComprehensive) {
		super.save(ccmOrgComprehensive);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmOrgComprehensive ccmOrgComprehensive) {
		super.delete(ccmOrgComprehensive);
	}
	
	// 更新点位坐标信息
	@Transactional(readOnly = false)
	public boolean updateCoordinates (CcmOrgComprehensive ccmOrgComprehensive) {
		return ccmOrgComprehensiveDao.updateCoordinates(ccmOrgComprehensive)>0;
	}
	
	// 更新点位坐标信息
	@Transactional(readOnly = false)
	public boolean insertCoordinates (CcmOrgComprehensive ccmOrgComprehensive) {
		return ccmOrgComprehensiveDao.insertCoordinates(ccmOrgComprehensive)>0;
	}
}