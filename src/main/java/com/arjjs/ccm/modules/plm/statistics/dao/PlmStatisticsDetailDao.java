/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.statistics.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsDetail;

/**
 * 统计首页明细DAO接口
 * @author liuxue
 * @version 2018-07-24
 */
@MyBatisDao
public interface PlmStatisticsDetailDao extends CrudDao<PlmStatisticsDetail> {
	
}