/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.attendance.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.attendance.entity.CcmWorkerAttendance;

/**
 * 社工考勤登记DAO接口
 * @author yiqingxuan
 * @version 2019-06-18
 */
@MyBatisDao
public interface CcmWorkerAttendanceDao extends CrudDao<CcmWorkerAttendance> {
	
	public List<CcmWorkerAttendance> getcountPage(CcmWorkerAttendance ccmWorkerAttendance);

	CcmWorkerAttendance getByapplyId(String id);
}