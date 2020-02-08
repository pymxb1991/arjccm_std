/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.organization.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.organization.entity.CcmEventOrganization;
import com.arjjs.ccm.modules.ccm.organization.dao.CcmEventOrganizationDao;

/**
 * 调解组织管理Service
 * @author chengdezheng
 * @version 2019-08-13
 */
@Service
@Transactional(readOnly = true)
public class CcmEventOrganizationService extends CrudService<CcmEventOrganizationDao, CcmEventOrganization> {

	public CcmEventOrganization get(String id) {
		return super.get(id);
	}
	
	public List<CcmEventOrganization> findList(CcmEventOrganization ccmEventOrganization) {
		return super.findList(ccmEventOrganization);
	}
	
	public Page<CcmEventOrganization> findPage(Page<CcmEventOrganization> page, CcmEventOrganization ccmEventOrganization) {
		return super.findPage(page, ccmEventOrganization);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmEventOrganization ccmEventOrganization) {
		super.save(ccmEventOrganization);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmEventOrganization ccmEventOrganization) {
		super.delete(ccmEventOrganization);
	}
	
}