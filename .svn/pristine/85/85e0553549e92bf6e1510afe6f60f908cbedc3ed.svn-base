/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolUser;

/**
 * 巡逻人员DAO接口
 * @author arj
 * @version 2018-03-14
 */
@MyBatisDao
public interface CcmPatrolUserDao extends CrudDao<CcmPatrolUser> {
	
	/**
	 * 插入用户信息
	 * @param  ccmPatrolUserList 
	 * @return
	 */
	public int insertAll(List<CcmPatrolUser> ccmPatrolUserList);
	
	/**
	 * 根据点位ID删除计划用户信息
	 * @param ccmPatrolUserId  
	 * @return
	 */
	public int deleteByPatrolUserId(String ccmPatrolUserId);
}