/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolRespoint;

/**
 * 巡逻点位结果DAO接口
 * 
 * @author arj
 * @version 2018-03-16
 */
@MyBatisDao
public interface CcmPatrolRespointDao extends CrudDao<CcmPatrolRespoint> {

	// 检测是否存在该信息 根据结果id 和 点位id
	public int  exitCheck(CcmPatrolRespoint ccmPatrolRespoint);
	
	// 更新 点位信息 根据
	public void updateRespoint(CcmPatrolRespoint ccmPatrolRespoint);
}