/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.scheme.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.kpi.scheme.entity.KpiScheme;

/**
 * 绩效考评方案DAO接口
 * @author liang
 * @version 2018-04-11
 */
@MyBatisDao
public interface KpiSchemeDao extends CrudDao<KpiScheme> {
	
}