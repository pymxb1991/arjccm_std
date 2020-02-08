/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgGropprevent;
import com.arjjs.ccm.modules.ccm.org.dao.CcmOrgGroppreventDao;

/**
 * 群防群治队伍Service
 * @author liang
 * @version 2018-01-12
 */
@Service
@Transactional(readOnly = true)
public class CcmOrgGroppreventService extends CrudService<CcmOrgGroppreventDao, CcmOrgGropprevent> {

	public CcmOrgGropprevent get(String id) {
		return super.get(id);
	}
	
	public List<CcmOrgGropprevent> findList(CcmOrgGropprevent ccmOrgGropprevent) {
		return super.findList(ccmOrgGropprevent);
	}
	
	public Page<CcmOrgGropprevent> findPage(Page<CcmOrgGropprevent> page, CcmOrgGropprevent ccmOrgGropprevent) {
		return super.findPage(page, ccmOrgGropprevent);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmOrgGropprevent ccmOrgGropprevent) {
		super.save(ccmOrgGropprevent);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmOrgGropprevent ccmOrgGropprevent) {
		super.delete(ccmOrgGropprevent);
	}
	
}