/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyperson.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.partyperson.entity.CcmPartyPerson;
import com.arjjs.ccm.modules.ccm.partyperson.dao.CcmPartyPersonDao;

/**
 * 党员信息管理Service
 * @author maoxb
 * @version 2019-08-13
 */
@Service
@Transactional(readOnly = true)
public class CcmPartyPersonService extends CrudService<CcmPartyPersonDao, CcmPartyPerson> {

	public CcmPartyPerson get(String id) {
		return super.get(id);
	}
	
	public List<CcmPartyPerson> findList(CcmPartyPerson ccmPartyPerson) {
		return super.findList(ccmPartyPerson);
	}
	
	public Page<CcmPartyPerson> findPage(Page<CcmPartyPerson> page, CcmPartyPerson ccmPartyPerson) {
		return super.findPage(page, ccmPartyPerson);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPartyPerson ccmPartyPerson) {
		super.save(ccmPartyPerson);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPartyPerson ccmPartyPerson) {
		super.delete(ccmPartyPerson);
	}
	
}