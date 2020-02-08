/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.pop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPopHR;
import com.arjjs.ccm.modules.ccm.pop.dao.CcmPopHRDao;

/**
 * 户籍人口表单Service
 * @author arj
 * @version 2017-12-27
 */
@Service
@Transactional(readOnly = true)
public class CcmPopHRService extends CrudService<CcmPopHRDao, CcmPopHR> {

	public CcmPopHR get(String id) {
		return super.get(id);
	}
	
	public List<CcmPopHR> findList(CcmPopHR ccmPopHR) {
		return super.findList(ccmPopHR);
	}
	
	public Page<CcmPopHR> findPage(Page<CcmPopHR> page, CcmPopHR ccmPopHR) {
		return super.findPage(page, ccmPopHR);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPopHR ccmPopHR) {
		super.save(ccmPopHR);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPopHR ccmPopHR) {
		super.delete(ccmPopHR);
	}
	
}