/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyactivity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.partyactivity.entity.CcmPartyActivity;
import com.arjjs.ccm.modules.ccm.partyactivity.dao.CcmPartyActivityDao;

/**
 * 党员活动管理Service
 * @author maoxb
 * @version 2019-08-13
 */
@Service
@Transactional(readOnly = true)
public class CcmPartyActivityService extends CrudService<CcmPartyActivityDao, CcmPartyActivity> {

	public CcmPartyActivity get(String id) {
		return super.get(id);
	}
	
	public List<CcmPartyActivity> findList(CcmPartyActivity ccmPartyActivity) {
		return super.findList(ccmPartyActivity);
	}
	
	public Page<CcmPartyActivity> findPage(Page<CcmPartyActivity> page, CcmPartyActivity ccmPartyActivity) {
		return super.findPage(page, ccmPartyActivity);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPartyActivity ccmPartyActivity) {
		super.save(ccmPartyActivity);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPartyActivity ccmPartyActivity) {
		super.delete(ccmPartyActivity);
	}
	
}