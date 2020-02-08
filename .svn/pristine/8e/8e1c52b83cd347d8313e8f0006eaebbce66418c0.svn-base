/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.warning.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.iot.warning.dao.CcmEarlyWarningDao;
import com.arjjs.ccm.modules.iot.warning.entity.CcmEarlyWarning;

/**
 * 预警记录Service
 * 
 * @author yiqingxuan
 * @version 2019-07-24
 */
@Service
@Transactional(readOnly = true)
public class CcmEarlyWarningService extends CrudService<CcmEarlyWarningDao, CcmEarlyWarning> {

	@Autowired
	private CcmEarlyWarningDao ccmEarlyWarningDao;

	public CcmEarlyWarning get(String id) {
		return super.get(id);
	}

	public List<CcmEarlyWarning> findList(CcmEarlyWarning ccmEarlyWarning) {
		return super.findList(ccmEarlyWarning);
	}

	public Page<CcmEarlyWarning> findPage(Page<CcmEarlyWarning> page, CcmEarlyWarning ccmEarlyWarning) {
		return super.findPage(page, ccmEarlyWarning);
	}

	@Transactional(readOnly = false)
	public void save(CcmEarlyWarning ccmEarlyWarning) {
		super.save(ccmEarlyWarning);
	}

	@Transactional(readOnly = false)
	public void delete(CcmEarlyWarning ccmEarlyWarning) {
		super.delete(ccmEarlyWarning);
	}

	public List<CcmEarlyWarning> getDataByToday() {
		return ccmEarlyWarningDao.getDataByToday();
	}

	public Page<CcmEarlyWarning> findPagegroupby(Page<CcmEarlyWarning> page, CcmEarlyWarning ccmEarlyWarning) {
		ccmEarlyWarning.setPage(page);
		page.setList(ccmEarlyWarningDao.findPagegroupby(ccmEarlyWarning));
		return page;
	}

	public List<CcmEarlyWarning> findListbyidcard(CcmEarlyWarning ccmEarlyWarning) {
		return ccmEarlyWarningDao.findListbyidcard(ccmEarlyWarning);
	}

	public List<Map<String, String>> getSortCountToday(CcmEarlyWarning ccmEarlyWarning) {
		return ccmEarlyWarningDao.getSortCountToday(ccmEarlyWarning);
	}

	public List<Map<String, String>> getSortCountWeek(CcmEarlyWarning ccmEarlyWarning) {
		return ccmEarlyWarningDao.getSortCountWeek(ccmEarlyWarning);
	}
}