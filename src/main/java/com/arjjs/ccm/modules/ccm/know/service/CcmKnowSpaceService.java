/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowSpace;
import com.arjjs.ccm.modules.ccm.know.dao.CcmKnowSpaceDao;

/**
 * 地方政策Service
 * @author wwh
 * @version 2018-01-05
 */
@Service
@Transactional(readOnly = true)
public class CcmKnowSpaceService extends CrudService<CcmKnowSpaceDao, CcmKnowSpace> {

	public CcmKnowSpace get(String id) {
		return super.get(id);
	}
	
	public List<CcmKnowSpace> findList(CcmKnowSpace ccmKnowSpace) {
		return super.findList(ccmKnowSpace);
	}
	
	public Page<CcmKnowSpace> findPage(Page<CcmKnowSpace> page, CcmKnowSpace ccmKnowSpace) {
		return super.findPage(page, ccmKnowSpace);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmKnowSpace ccmKnowSpace) {
		super.save(ccmKnowSpace);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmKnowSpace ccmKnowSpace) {
		super.delete(ccmKnowSpace);
	}
	
}