/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.event.entity.CcmAlarmLog;

/**
 * 告警日志查询DAO接口
 * @author pengjianqiang
 * @version 2018-05-03
 */
@MyBatisDao
public interface CcmAlarmLogDao extends CrudDao<CcmAlarmLog> {

	//查找判断是否越界告警
	List<CcmAlarmLog> findListOverstep(CcmAlarmLog ccmAlarmLog);
	
}