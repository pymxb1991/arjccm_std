/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowPolicy;
import com.arjjs.ccm.modules.ccm.know.dao.CcmKnowPolicyDao;

/**
 * 政策法规Service
 * @author wwh
 * @version 2018-01-04
 */
@Service
@Transactional(readOnly = true)
public class CcmKnowPolicyService extends CrudService<CcmKnowPolicyDao, CcmKnowPolicy> {

	public CcmKnowPolicy get(String id) {
		return super.get(id);
	}
	
	public List<CcmKnowPolicy> findList(CcmKnowPolicy ccmKnowPolicy) {
		return super.findList(ccmKnowPolicy);
	}
	
	public Page<CcmKnowPolicy> findPage(Page<CcmKnowPolicy> page, CcmKnowPolicy ccmKnowPolicy) {
		return super.findPage(page, ccmKnowPolicy);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmKnowPolicy ccmKnowPolicy) {
		super.save(ccmKnowPolicy);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmKnowPolicy ccmKnowPolicy) {
		super.delete(ccmKnowPolicy);
	}
	
}