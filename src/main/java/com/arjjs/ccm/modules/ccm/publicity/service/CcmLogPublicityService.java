/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.publicity.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.publicity.entity.CcmLogPublicity;
import com.arjjs.ccm.modules.ccm.publicity.dao.CcmLogPublicityDao;

/**
 * 宣传信息实体类Service
 * @author 刘永建
 * @version 2019-06-18
 */
@Service
@Transactional(readOnly = true)
public class CcmLogPublicityService extends CrudService<CcmLogPublicityDao, CcmLogPublicity> {

	public CcmLogPublicity get(String id) {
		return super.get(id);
	}
	
	public List<CcmLogPublicity> findList(CcmLogPublicity ccmLogPublicity) {
		return super.findList(ccmLogPublicity);
	}
	
	public Page<CcmLogPublicity> findPage(Page<CcmLogPublicity> page, CcmLogPublicity ccmLogPublicity) {
		return super.findPage(page, ccmLogPublicity);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmLogPublicity ccmLogPublicity) {
		super.save(ccmLogPublicity);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmLogPublicity ccmLogPublicity) {
		super.delete(ccmLogPublicity);
	}
	
}