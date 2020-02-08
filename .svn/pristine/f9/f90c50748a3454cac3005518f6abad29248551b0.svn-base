/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrollog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.patrollog.entity.CcmPatrolLog;

/**
 * 巡检日志DAO接口
 * @author 刘永建
 * @version 2019-07-15
 */
@MyBatisDao
public interface CcmPatrolLogDao extends CrudDao<CcmPatrolLog> {
	List<CcmPatrolLog> findListByUser(@Param("user_id")String id);
}