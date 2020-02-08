/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.service.entity.CcmServiceCivil;
import com.arjjs.ccm.modules.ccm.service.dao.CcmServiceCivilDao;

/**
 * 民政工作管理Service
 * @author liang
 * @version 2018-08-02
 */
@Service
@Transactional(readOnly = true)
public class CcmServiceCivilService extends CrudService<CcmServiceCivilDao, CcmServiceCivil> {

	public CcmServiceCivil get(String id) {
		return super.get(id);
	}
	
	public List<CcmServiceCivil> findList(CcmServiceCivil ccmServiceCivil) {
		return super.findList(ccmServiceCivil);
	}
	
	public Page<CcmServiceCivil> findPage(Page<CcmServiceCivil> page, CcmServiceCivil ccmServiceCivil) {
		return super.findPage(page, ccmServiceCivil);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmServiceCivil ccmServiceCivil) {
		super.save(ccmServiceCivil);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmServiceCivil ccmServiceCivil) {
		super.delete(ccmServiceCivil);
	}
	
}