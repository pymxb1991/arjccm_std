/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.attendanceapply.service;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.attendanceapply.dao.CcmWorkerAttendanceApplyDao;
import com.arjjs.ccm.modules.ccm.attendanceapply.entity.CcmWorkerAttendanceApply;
import com.arjjs.ccm.modules.pbs.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 加班请假申请Service
 * @author yi
 * @version 2019-11-04
 */
@Service
@Transactional(readOnly = true)
public class CcmWorkerAttendanceApplyService extends CrudService<CcmWorkerAttendanceApplyDao, CcmWorkerAttendanceApply> {

	public CcmWorkerAttendanceApply get(String id) {
		return super.get(id);
	}
	
	public List<CcmWorkerAttendanceApply> findList(CcmWorkerAttendanceApply ccmWorkerAttendanceApply) {
		return super.findList(ccmWorkerAttendanceApply);
	}
	
	public Page<CcmWorkerAttendanceApply> findPage(Page<CcmWorkerAttendanceApply> page, CcmWorkerAttendanceApply ccmWorkerAttendanceApply) {
		if(UserUtils.getUser().getId()!=null){
			ccmWorkerAttendanceApply.getSqlMap().put("dsf", dataScopeFilter( UserUtils.getUser(), "c", "b"));
		} else {
			if(ccmWorkerAttendanceApply.getCurrentUser().getId()!=null){
				ccmWorkerAttendanceApply.getSqlMap().put("dsf", dataScopeFilter( ccmWorkerAttendanceApply.getCurrentUser(), "c", "b"));
			}
		}
		return super.findPage(page, ccmWorkerAttendanceApply);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmWorkerAttendanceApply ccmWorkerAttendanceApply) {
		super.save(ccmWorkerAttendanceApply);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmWorkerAttendanceApply ccmWorkerAttendanceApply) {
		super.delete(ccmWorkerAttendanceApply);
	}
	
}