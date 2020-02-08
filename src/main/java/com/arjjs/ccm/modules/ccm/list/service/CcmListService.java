/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.list.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.list.entity.CcmList;
import com.arjjs.ccm.modules.ccm.list.dao.CcmListDao;

/**
 * 名单库实体类Service
 * @author jpy
 * @version 2019-06-04
 */
@Service
@Transactional(readOnly = true)
public class CcmListService extends CrudService<CcmListDao, CcmList> {

	public CcmList get(String id) {
		return super.get(id);
	}
	
	public List<CcmList> findList(CcmList ccmList) {
		return super.findList(ccmList);
	}
	
	public List<CcmList> getList(CcmList ccmList) {
		return dao.getList(ccmList);
	}
	
	public Integer getPeopleCount(CcmList ccmList) {
		return dao.getPeopleCount(ccmList);
	}
	
	public Page<CcmList> findPage(Page<CcmList> page, CcmList ccmList) {
		return super.findPage(page, ccmList);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmList ccmList) {
		super.save(ccmList);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmList ccmList) {
		super.delete(ccmList);
	}
	
}