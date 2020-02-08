/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.scenic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.scenic.entity.CcmPlaceScenic;
import com.arjjs.ccm.modules.ccm.scenic.dao.CcmPlaceScenicDao;

/**
 * 旅游景点Service
 * @author ljd
 * @version 2019-04-29
 */
@Service
@Transactional(readOnly = true)
public class CcmPlaceScenicService extends CrudService<CcmPlaceScenicDao, CcmPlaceScenic> {

	public CcmPlaceScenic get(String id) {
		return super.get(id);
	}
	
	public List<CcmPlaceScenic> findList(CcmPlaceScenic ccmPlaceScenic) {
		return super.findList(ccmPlaceScenic);
	}
	
	public Page<CcmPlaceScenic> findPage(Page<CcmPlaceScenic> page, CcmPlaceScenic ccmPlaceScenic) {
		return super.findPage(page, ccmPlaceScenic);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPlaceScenic ccmPlaceScenic) {
		super.save(ccmPlaceScenic);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPlaceScenic ccmPlaceScenic) {
		super.delete(ccmPlaceScenic);
	}
	
}