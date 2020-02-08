/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowKeyJob;
import com.arjjs.ccm.modules.ccm.know.dao.CcmKnowKeyJobDao;

/**
 * 专项工作Service
 * @author liang
 * @version 2018-03-23
 */
@Service
@Transactional(readOnly = true)
public class CcmKnowKeyJobService extends CrudService<CcmKnowKeyJobDao, CcmKnowKeyJob> {

	public CcmKnowKeyJob get(String id) {
		return super.get(id);
	}
	
	public List<CcmKnowKeyJob> findList(CcmKnowKeyJob ccmKnowKeyJob) {
		return super.findList(ccmKnowKeyJob);
	}
	
	public Page<CcmKnowKeyJob> findPage(Page<CcmKnowKeyJob> page, CcmKnowKeyJob ccmKnowKeyJob) {
		return super.findPage(page, ccmKnowKeyJob);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmKnowKeyJob ccmKnowKeyJob) {
		super.save(ccmKnowKeyJob);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmKnowKeyJob ccmKnowKeyJob) {
		super.delete(ccmKnowKeyJob);
	}
	
}