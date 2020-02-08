/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowGovernmentAffairs;
import com.arjjs.ccm.modules.ccm.know.dao.CcmKnowGovernmentAffairsDao;

/**
 * 民政事务Service
 * @author liang
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class CcmKnowGovernmentAffairsService extends CrudService<CcmKnowGovernmentAffairsDao, CcmKnowGovernmentAffairs> {

	public CcmKnowGovernmentAffairs get(String id) {
		return super.get(id);
	}
	
	public List<CcmKnowGovernmentAffairs> findList(CcmKnowGovernmentAffairs ccmKnowGovernmentAffairs) {
		return super.findList(ccmKnowGovernmentAffairs);
	}
	
	public Page<CcmKnowGovernmentAffairs> findPage(Page<CcmKnowGovernmentAffairs> page, CcmKnowGovernmentAffairs ccmKnowGovernmentAffairs) {
		return super.findPage(page, ccmKnowGovernmentAffairs);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmKnowGovernmentAffairs ccmKnowGovernmentAffairs) {
		super.save(ccmKnowGovernmentAffairs);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmKnowGovernmentAffairs ccmKnowGovernmentAffairs) {
		super.delete(ccmKnowGovernmentAffairs);
	}
	
}