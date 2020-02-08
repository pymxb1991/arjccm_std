/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.work.entity.CcmWorkTiming;
import com.arjjs.ccm.modules.ccm.pop.dao.CcmPeopleDao;
import com.arjjs.ccm.modules.ccm.work.dao.CcmWorkTimingDao;

/**
 * 定时提醒Service
 * @author liang
 * @version 2018-08-02
 */
@Service
@Transactional(readOnly = true)
public class CcmWorkTimingService extends CrudService<CcmWorkTimingDao, CcmWorkTiming> {

	@Autowired
	private CcmWorkTimingDao ccmWorkTimingDao;
	
	
	
	public CcmWorkTiming get(String id) {
		return super.get(id);
	}
	
	public List<CcmWorkTiming> findList(CcmWorkTiming ccmWorkTiming) {
		return super.findList(ccmWorkTiming);
	}
	
	public Page<CcmWorkTiming> findPage(Page<CcmWorkTiming> page, CcmWorkTiming ccmWorkTiming) {
		return super.findPage(page, ccmWorkTiming);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmWorkTiming ccmWorkTiming) {
		super.save(ccmWorkTiming);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmWorkTiming ccmWorkTiming) {
		super.delete(ccmWorkTiming);
	}

	//定时提醒
	public List<CcmWorkTiming> findTiming() {
		return ccmWorkTimingDao.findTiming();
	}
	
}