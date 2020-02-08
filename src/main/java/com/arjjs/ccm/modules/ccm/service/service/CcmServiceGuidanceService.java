/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.service.entity.CcmServiceGuidance;
import com.arjjs.ccm.modules.ccm.service.dao.CcmServiceGuidanceDao;

/**
 * 用户指南表Service
 * @author fuxinshuang
 * @version 2018-03-13
 */
@Service
@Transactional(readOnly = true)
public class CcmServiceGuidanceService extends CrudService<CcmServiceGuidanceDao, CcmServiceGuidance> {

	public CcmServiceGuidance get(String id) {
		return super.get(id);
	}
	
	public List<CcmServiceGuidance> findList(CcmServiceGuidance ccmServiceGuidance) {
		return super.findList(ccmServiceGuidance);
	}
	
	public Page<CcmServiceGuidance> findPage(Page<CcmServiceGuidance> page, CcmServiceGuidance ccmServiceGuidance) {
		return super.findPage(page, ccmServiceGuidance);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmServiceGuidance ccmServiceGuidance) {
		super.save(ccmServiceGuidance);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmServiceGuidance ccmServiceGuidance) {
		super.delete(ccmServiceGuidance);
	}
	
}