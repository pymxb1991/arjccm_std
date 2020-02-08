/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgPerson;
import com.arjjs.ccm.modules.ccm.org.dao.CcmOrgPersonDao;

/**
 * 自治组织人员管理Service
 * @author liuyongjian
 * @version 2019-08-13
 */
@Service
@Transactional(readOnly = true)
public class CcmOrgPersonService extends CrudService<CcmOrgPersonDao, CcmOrgPerson> {

	public CcmOrgPerson get(String id) {
		return super.get(id);
	}
	
	public List<CcmOrgPerson> findList(CcmOrgPerson ccmOrgPerson) {
		return super.findList(ccmOrgPerson);
	}
	
	public Page<CcmOrgPerson> findPage(Page<CcmOrgPerson> page, CcmOrgPerson ccmOrgPerson) {
		return super.findPage(page, ccmOrgPerson);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmOrgPerson ccmOrgPerson) {
		super.save(ccmOrgPerson);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmOrgPerson ccmOrgPerson) {
		super.delete(ccmOrgPerson);
	}
	
}