/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.scheme.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.kpi.scheme.entity.KpiSchemeSubjectivity;

/**
 * 绩效主观评分DAO接口
 * @author liang
 * @version 2018-04-11
 */
@MyBatisDao
public interface KpiSchemeSubjectivityDao extends CrudDao<KpiSchemeSubjectivity> {

	public List<KpiSchemeSubjectivity> findListWithScore(KpiSchemeSubjectivity kpiSchemeSubjectivity);
	//真删除
	void deleteTrue(KpiSchemeSubjectivity l);
}