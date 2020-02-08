/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.moral.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.moral.entity.CcmMoral;
import com.arjjs.ccm.modules.ccm.moral.dao.CcmMoralDao;

/**
 * 道德模范实体类Service
 * @author lijiupeng
 * @version 2019-06-21
 */
@Service
@Transactional(readOnly = true)
public class CcmMoralService extends CrudService<CcmMoralDao, CcmMoral> {

	public CcmMoral get(String id) {
		return super.get(id);
	}
	
	public List<CcmMoral> findList(CcmMoral ccmMoral) {
		return super.findList(ccmMoral);
	}
	
	public Page<CcmMoral> findPage(Page<CcmMoral> page, CcmMoral ccmMoral) {
		return super.findPage(page, ccmMoral);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmMoral ccmMoral) {
		super.save(ccmMoral);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmMoral ccmMoral) {
		super.delete(ccmMoral);
	}
	
}