/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowKeyActivity;
import com.arjjs.ccm.modules.ccm.know.dao.CcmKnowKeyActivityDao;

/**
 * 重要活动Service
 * @author liang
 * @version 2018-03-23
 */
@Service
@Transactional(readOnly = true)
public class CcmKnowKeyActivityService extends CrudService<CcmKnowKeyActivityDao, CcmKnowKeyActivity> {

	public CcmKnowKeyActivity get(String id) {
		return super.get(id);
	}
	
	public List<CcmKnowKeyActivity> findList(CcmKnowKeyActivity ccmKnowKeyActivity) {
		return super.findList(ccmKnowKeyActivity);
	}
	
	public Page<CcmKnowKeyActivity> findPage(Page<CcmKnowKeyActivity> page, CcmKnowKeyActivity ccmKnowKeyActivity) {
		return super.findPage(page, ccmKnowKeyActivity);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmKnowKeyActivity ccmKnowKeyActivity) {
		super.save(ccmKnowKeyActivity);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmKnowKeyActivity ccmKnowKeyActivity) {
		super.delete(ccmKnowKeyActivity);
	}
	
}