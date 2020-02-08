/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.know.entity.CcmEconomicsYear;
import com.arjjs.ccm.modules.ccm.pop.dao.CcmPopTenantDao;
import com.arjjs.ccm.modules.ccm.know.dao.CcmEconomicsYearDao;

/**
 * 经济运行数据-年Service
 * @author liang
 * @version 2018-06-03
 */
@Service
@Transactional(readOnly = true)
public class CcmEconomicsYearService extends CrudService<CcmEconomicsYearDao, CcmEconomicsYear> {

	@Autowired
	private CcmEconomicsYearDao ccmEconomicsYearDao;
	
	
	
	public CcmEconomicsYear get(String id) {
		return super.get(id);
	}
	
	public List<CcmEconomicsYear> findList(CcmEconomicsYear ccmEconomicsYear) {
		return super.findList(ccmEconomicsYear);
	}
	
	public Page<CcmEconomicsYear> findPage(Page<CcmEconomicsYear> page, CcmEconomicsYear ccmEconomicsYear) {
		return super.findPage(page, ccmEconomicsYear);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmEconomicsYear ccmEconomicsYear) {
		super.save(ccmEconomicsYear);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmEconomicsYear ccmEconomicsYear) {
		super.delete(ccmEconomicsYear);
	}

	//经济运行数据-大屏经济建设
	public List<CcmEconomicsYear> getData() {
		return ccmEconomicsYearDao.getData();
	}
	
}