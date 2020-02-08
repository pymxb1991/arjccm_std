/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyvoluteer.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.partyvoluteer.entity.CcmPartyVolunteer;
import com.arjjs.ccm.modules.ccm.partyvoluteer.dao.CcmPartyVolunteerDao;

/**
 * 志愿者管理Service
 * @author maoxb
 * @version 2019-08-13
 */
@Service
@Transactional(readOnly = true)
public class CcmPartyVolunteerService extends CrudService<CcmPartyVolunteerDao, CcmPartyVolunteer> {

	public CcmPartyVolunteer get(String id) {
		return super.get(id);
	}
	
	public List<CcmPartyVolunteer> findList(CcmPartyVolunteer ccmPartyVolunteer) {
		return super.findList(ccmPartyVolunteer);
	}
	
	public Page<CcmPartyVolunteer> findPage(Page<CcmPartyVolunteer> page, CcmPartyVolunteer ccmPartyVolunteer) {
		return super.findPage(page, ccmPartyVolunteer);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPartyVolunteer ccmPartyVolunteer) {
		super.save(ccmPartyVolunteer);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPartyVolunteer ccmPartyVolunteer) {
		super.delete(ccmPartyVolunteer);
	}
	
}