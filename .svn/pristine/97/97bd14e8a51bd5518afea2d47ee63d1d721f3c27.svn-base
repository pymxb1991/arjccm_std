/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.know.entity.CcmKnowKeyRegion;
import com.arjjs.ccm.modules.ccm.know.dao.CcmKnowKeyRegionDao;

/**
 * 重点地区标准Service
 * @author liang
 * @version 2018-03-22
 */
@Service
@Transactional(readOnly = true)
public class CcmKnowKeyRegionService extends CrudService<CcmKnowKeyRegionDao, CcmKnowKeyRegion> {

	public CcmKnowKeyRegion get(String id) {
		return super.get(id);
	}
	
	public List<CcmKnowKeyRegion> findList(CcmKnowKeyRegion ccmKnowKeyRegion) {
		return super.findList(ccmKnowKeyRegion);
	}
	
	public Page<CcmKnowKeyRegion> findPage(Page<CcmKnowKeyRegion> page, CcmKnowKeyRegion ccmKnowKeyRegion) {
		return super.findPage(page, ccmKnowKeyRegion);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmKnowKeyRegion ccmKnowKeyRegion) {
		super.save(ccmKnowKeyRegion);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmKnowKeyRegion ccmKnowKeyRegion) {
		super.delete(ccmKnowKeyRegion);
	}
	
}