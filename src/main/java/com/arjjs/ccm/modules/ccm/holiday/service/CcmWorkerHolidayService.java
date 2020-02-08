/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.holiday.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.holiday.entity.CcmWorkerHoliday;
import com.arjjs.ccm.modules.ccm.holiday.dao.CcmWorkerHolidayDao;

/**
 * 节假日管理Service
 * @author yiqingxuan
 * @version 2019-06-18
 */
@Service
@Transactional(readOnly = true)
public class CcmWorkerHolidayService extends CrudService<CcmWorkerHolidayDao, CcmWorkerHoliday> {

	public CcmWorkerHoliday get(String id) {
		return super.get(id);
	}
	
	public List<CcmWorkerHoliday> findList(CcmWorkerHoliday ccmWorkerHoliday) {
		return super.findList(ccmWorkerHoliday);
	}
	
	public Page<CcmWorkerHoliday> findPage(Page<CcmWorkerHoliday> page, CcmWorkerHoliday ccmWorkerHoliday) {
		return super.findPage(page, ccmWorkerHoliday);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmWorkerHoliday ccmWorkerHoliday) {
		super.save(ccmWorkerHoliday);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmWorkerHoliday ccmWorkerHoliday) {
		super.delete(ccmWorkerHoliday);
	}
	
}