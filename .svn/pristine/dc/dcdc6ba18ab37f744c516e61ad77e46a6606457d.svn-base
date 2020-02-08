/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventStakeholder;
import com.arjjs.ccm.modules.ccm.event.dao.CcmEventStakeholderDao;

/**
 * 案事件干系人Service
 * @author pengjianqiang
 * @version 2018-01-30
 */
@Service
@Transactional(readOnly = true)
public class CcmEventStakeholderService extends CrudService<CcmEventStakeholderDao, CcmEventStakeholder> {

	public CcmEventStakeholder get(String id) {
		return super.get(id);
	}
	
	public List<CcmEventStakeholder> findList(CcmEventStakeholder ccmEventStakeholder) {
		return super.findList(ccmEventStakeholder);
	}
	
	public Page<CcmEventStakeholder> findPage(Page<CcmEventStakeholder> page, CcmEventStakeholder ccmEventStakeholder) {
		return super.findPage(page, ccmEventStakeholder);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmEventStakeholder ccmEventStakeholder) {
		super.save(ccmEventStakeholder);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmEventStakeholder ccmEventStakeholder) {
		super.delete(ccmEventStakeholder);
	}
	
}