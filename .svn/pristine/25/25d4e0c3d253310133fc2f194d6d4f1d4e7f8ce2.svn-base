/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.event.entity.CcmEventRequestdeal;
import com.arjjs.ccm.modules.ccm.event.dao.CcmEventRequestdealDao;

/**
 * 请求处理Service
 * @author arj
 * @version 2018-01-18
 */
@Service
@Transactional(readOnly = true)
public class CcmEventRequestdealService extends CrudService<CcmEventRequestdealDao, CcmEventRequestdeal> {

	
	public CcmEventRequestdeal get(String id) {
		return super.get(id);
	}
	
	public List<CcmEventRequestdeal> findList(CcmEventRequestdeal ccmEventRequestdeal) {
		return super.findList(ccmEventRequestdeal);
	}
	
	public Page<CcmEventRequestdeal> findPage(Page<CcmEventRequestdeal> page, CcmEventRequestdeal ccmEventRequestdeal) {
		return super.findPage(page, ccmEventRequestdeal);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmEventRequestdeal ccmEventRequestdeal) {
		super.save(ccmEventRequestdeal);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmEventRequestdeal ccmEventRequestdeal) {
		super.delete(ccmEventRequestdeal);
	}
	
	
}