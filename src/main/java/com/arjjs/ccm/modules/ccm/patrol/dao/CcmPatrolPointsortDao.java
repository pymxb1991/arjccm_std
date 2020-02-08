/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolPointsort;

/**
 * 巡逻点位顺序DAO接口
 * 
 * @author arj
 * @version 2018-03-15
 */
@MyBatisDao
public interface CcmPatrolPointsortDao extends CrudDao<CcmPatrolPointsort> {

	/**
	 * 新增相关内容
	 * 
	 * @param ccmPatrolUserList
	 * @return
	 */
	public int insertAll(List<CcmPatrolPointsort> ccmPatrolPointSortList);

	/**
	 * 根据点位ID删除计划Id 所有信息
	 * 
	 * @param ccmPatrolUserId
	 * @return
	 */
	public int deleteBypointId(String planID);
}