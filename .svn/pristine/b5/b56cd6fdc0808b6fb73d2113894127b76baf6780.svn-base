/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.pop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmRoadAddress;
import com.arjjs.ccm.modules.ccm.pop.dao.CcmRoadAddressDao;

/**
 * 街路巷Service
 * @author liujindan
 * @version 2019-02-25
 */
@Service
@Transactional(readOnly = true)
public class CcmRoadAddressService extends CrudService<CcmRoadAddressDao, CcmRoadAddress> {

	public CcmRoadAddress get(String id) {
		return super.get(id);
	}
	
	public List<CcmRoadAddress> findList(CcmRoadAddress ccmRoadAddress) {
		return super.findList(ccmRoadAddress);
	}
	
	public Page<CcmRoadAddress> findPage(Page<CcmRoadAddress> page, CcmRoadAddress ccmRoadAddress) {
		return super.findPage(page, ccmRoadAddress);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmRoadAddress ccmRoadAddress) {
		super.save(ccmRoadAddress);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmRoadAddress ccmRoadAddress) {
		super.delete(ccmRoadAddress);
	}
	
}