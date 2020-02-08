/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.org.entity.CcmOrgNpseEconomic;
import com.arjjs.ccm.modules.ccm.org.dao.CcmOrgNpseEconomicDao;

/**
 * 企业经济运行数据Service
 * @author liang
 * @version 2018-07-19
 */
@Service
@Transactional(readOnly = true)
public class CcmOrgNpseEconomicService extends CrudService<CcmOrgNpseEconomicDao, CcmOrgNpseEconomic> {

	public CcmOrgNpseEconomic get(String id) {
		return super.get(id);
	}
	
	public List<CcmOrgNpseEconomic> findList(CcmOrgNpseEconomic ccmOrgNpseEconomic) {
		return super.findList(ccmOrgNpseEconomic);
	}
	
	public Page<CcmOrgNpseEconomic> findPage(Page<CcmOrgNpseEconomic> page, CcmOrgNpseEconomic ccmOrgNpseEconomic) {
		return super.findPage(page, ccmOrgNpseEconomic);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmOrgNpseEconomic ccmOrgNpseEconomic) {
		super.save(ccmOrgNpseEconomic);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmOrgNpseEconomic ccmOrgNpseEconomic) {
		super.delete(ccmOrgNpseEconomic);
	}
	
}