/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.financial.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.financial.entity.CcmPlaceFinancial;
import com.arjjs.ccm.modules.ccm.financial.dao.CcmPlaceFinancialDao;

/**
 * 金融机构Service
 * @author lgh
 * @version 2019-04-29
 */
@Service
@Transactional(readOnly = true)
public class CcmPlaceFinancialService extends CrudService<CcmPlaceFinancialDao, CcmPlaceFinancial> {

	public CcmPlaceFinancial get(String id) {
		return super.get(id);
	}
	
	public List<CcmPlaceFinancial> findList(CcmPlaceFinancial ccmPlaceFinancial) {
		return super.findList(ccmPlaceFinancial);
	}
	
	public Page<CcmPlaceFinancial> findPage(Page<CcmPlaceFinancial> page, CcmPlaceFinancial ccmPlaceFinancial) {
		return super.findPage(page, ccmPlaceFinancial);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmPlaceFinancial ccmPlaceFinancial) {
		super.save(ccmPlaceFinancial);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmPlaceFinancial ccmPlaceFinancial) {
		super.delete(ccmPlaceFinancial);
	}
	
}