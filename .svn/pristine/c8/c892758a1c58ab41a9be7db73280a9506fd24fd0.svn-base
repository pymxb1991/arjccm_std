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
import com.arjjs.ccm.modules.ccm.know.entity.CcmEconomicsMonth;
import com.arjjs.ccm.modules.ccm.citycomponents.dao.CcmCityComponentsDao;
import com.arjjs.ccm.modules.ccm.know.dao.CcmEconomicsMonthDao;

/**
 * 经济运行数据-月Service
 * @author liang
 * @version 2018-06-03
 */
@Service
@Transactional(readOnly = true)
public class CcmEconomicsMonthService extends CrudService<CcmEconomicsMonthDao, CcmEconomicsMonth> {

	@Autowired
	private CcmEconomicsMonthDao ccmEconomicsMonthDao;
	
	
	
	public CcmEconomicsMonth get(String id) {
		return super.get(id);
	}
	
	public List<CcmEconomicsMonth> findList(CcmEconomicsMonth ccmEconomicsMonth) {
		return super.findList(ccmEconomicsMonth);
	}
	
	public Page<CcmEconomicsMonth> findPage(Page<CcmEconomicsMonth> page, CcmEconomicsMonth ccmEconomicsMonth) {
		return super.findPage(page, ccmEconomicsMonth);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmEconomicsMonth ccmEconomicsMonth) {
		super.save(ccmEconomicsMonth);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmEconomicsMonth ccmEconomicsMonth) {
		super.delete(ccmEconomicsMonth);
	}

	//经济数据-大屏经济建设
	public List<CcmEconomicsMonth> getData() {
		return ccmEconomicsMonthDao.getData();
	}

	//税收统计-大屏经济建设
	public List<CcmEconomicsMonth> getShuiShouData() {
		return ccmEconomicsMonthDao.getShuiShouData();
	}
	
}