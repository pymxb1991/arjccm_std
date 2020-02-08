/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolPoint;
import com.arjjs.ccm.tool.EchartType;

/**
 * 巡逻点位DAO接口
 * @author arj
 * @version 2018-03-14
 */
@MyBatisDao
public interface CcmPatrolPointDao extends CrudDao<CcmPatrolPoint> {

	//巡逻路线
	List<EchartType> findPatrolPointPlanMap();
	
}