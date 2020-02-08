/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.fence.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.fence.entity.CcmElectronicFence;
import com.arjjs.ccm.modules.ccm.fence.dao.CcmElectronicFenceDao;

/**
 * 电子围栏实体类Service
 * @author lgh
 * @version 2019-05-31
 */
@Service
@Transactional(readOnly = true)
public class CcmElectronicFenceService extends CrudService<CcmElectronicFenceDao, CcmElectronicFence> {

	public CcmElectronicFence get(String id) {
		return super.get(id);
	}
	
	public List<CcmElectronicFence> findList(CcmElectronicFence ccmElectronicFence) {
		return super.findList(ccmElectronicFence);
	}
	
	public Page<CcmElectronicFence> findPage(Page<CcmElectronicFence> page, CcmElectronicFence ccmElectronicFence) {
		return super.findPage(page, ccmElectronicFence);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmElectronicFence ccmElectronicFence) {
		super.save(ccmElectronicFence);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmElectronicFence ccmElectronicFence) {
		super.delete(ccmElectronicFence);
	}
	
}