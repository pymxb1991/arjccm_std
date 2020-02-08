/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.alarmhandlelog.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.rest.entity.AlarmHandleUserStatus;
import com.arjjs.ccm.modules.flat.alarmhandlelog.entity.BphAlarmHandleLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 处警过程记录DAO接口
 * @author wangyikai
 * @version 2018-11-22
 */
@MyBatisDao
public interface BphAlarmHandleLogDao extends CrudDao<BphAlarmHandleLog> {
	List<BphAlarmHandleLog> findHandleLog (BphAlarmHandleLog bphAlarmHandleLog);
	List<BphAlarmHandleLog> findByAlarmId(String alarmId);

    List<AlarmHandleUserStatus> queryAlarmHandleStatusUsers(@Param("alarmId") String alarmId);
}