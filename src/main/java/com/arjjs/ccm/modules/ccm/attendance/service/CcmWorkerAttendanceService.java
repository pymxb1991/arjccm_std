/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.attendance.entity.CcmWorkerAttendance;
import com.arjjs.ccm.modules.ccm.attendance.dao.CcmWorkerAttendanceDao;

/**
 * 社工考勤登记Service
 * @author yiqingxuan
 * @version 2019-06-18
 */
@Service
@Transactional(readOnly = true)
public class CcmWorkerAttendanceService extends CrudService<CcmWorkerAttendanceDao, CcmWorkerAttendance> {
	
	@Autowired
	private CcmWorkerAttendanceDao ccmWorkerAttendanceDao;
	
	public CcmWorkerAttendance get(String id) {
		return super.get(id);
	}
	
	public List<CcmWorkerAttendance> findList(CcmWorkerAttendance ccmWorkerAttendance) {
		return super.findList(ccmWorkerAttendance);
	}
	
	public Page<CcmWorkerAttendance> findPage(Page<CcmWorkerAttendance> page, CcmWorkerAttendance ccmWorkerAttendance) {
		return super.findPage(page, ccmWorkerAttendance);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmWorkerAttendance ccmWorkerAttendance) {
		super.save(ccmWorkerAttendance);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmWorkerAttendance ccmWorkerAttendance) {
		super.delete(ccmWorkerAttendance);
	}
	
	public Page<CcmWorkerAttendance> getcountPage(Page<CcmWorkerAttendance> page, CcmWorkerAttendance ccmWorkerAttendance) {		
		ccmWorkerAttendance.setPage(page);
		page.setList(ccmWorkerAttendanceDao.getcountPage(ccmWorkerAttendance));
		return page;
	}
	
	public List<CcmWorkerAttendance> getcountexport(CcmWorkerAttendance ccmWorkerAttendance){
		List<CcmWorkerAttendance> list = ccmWorkerAttendanceDao.getcountPage(ccmWorkerAttendance);
		return list;
	}

	public CcmWorkerAttendance getByapplyId(String applyId){
		return ccmWorkerAttendanceDao.getByapplyId(applyId);
	}
			
}