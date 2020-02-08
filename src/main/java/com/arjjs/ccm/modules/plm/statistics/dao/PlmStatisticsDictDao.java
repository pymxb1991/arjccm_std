/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.statistics.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.statistics.entity.PlmStatisticsDict;

/**
 * 统计首页字典DAO接口
 * @author liuxue
 * @version 2018-07-24
 */
@MyBatisDao
public interface PlmStatisticsDictDao extends CrudDao<PlmStatisticsDict> {
	
}