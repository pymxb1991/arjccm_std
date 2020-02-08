/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.attendanceapply.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.attendanceapply.entity.CcmWorkerAttendanceApply;

/**
 * 加班请假申请DAO接口
 * @author yi
 * @version 2019-11-04
 */
@MyBatisDao
public interface CcmWorkerAttendanceApplyDao extends CrudDao<CcmWorkerAttendanceApply> {
	
}