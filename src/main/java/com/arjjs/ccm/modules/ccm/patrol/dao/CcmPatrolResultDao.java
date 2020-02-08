/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolResult;

/**
 * 巡逻结果DAO接口
 * @author arj
 * @version 2018-03-16
 */
@MyBatisDao
public interface CcmPatrolResultDao extends CrudDao<CcmPatrolResult> {
	
}