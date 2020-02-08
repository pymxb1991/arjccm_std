/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.list.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.list.entity.CcmListPeople;
import com.arjjs.ccm.modules.ccm.list.dao.CcmListPeopleDao;

/**
 * 静态库和黑名单人员实体类Service
 * @author jpy
 * @version 2019-06-05
 */
@Service
@Transactional(readOnly = true)
public class CcmListPeopleService extends CrudService<CcmListPeopleDao, CcmListPeople> {

	public CcmListPeople get(String id) {
		return super.get(id);
	}
	
	public List<CcmListPeople> findList(CcmListPeople ccmListPeople) {
		return super.findList(ccmListPeople);
	}
	
	public Page<CcmListPeople> findPage(Page<CcmListPeople> page, CcmListPeople ccmListPeople) {
		return super.findPage(page, ccmListPeople);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmListPeople ccmListPeople) {
		super.save(ccmListPeople);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmListPeople ccmListPeople) {
		super.delete(ccmListPeople);
	}
	
}