/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.count.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.flat.analyst.entity.Count;
import com.arjjs.ccm.modules.kpi.count.entity.CcmKpiCount;

/**
 * 绩效统计实体类DAO接口
 * @author lgh
 * @version 2019-07-17
 */
@MyBatisDao
public interface CcmKpiCountDao extends CrudDao<CcmKpiCount> {
	
	List<Count> getRank();
	
}