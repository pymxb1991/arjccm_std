/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.scheme.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.kpi.scheme.entity.KpiSchemeKpi;

/**
 * 绩效考评KPIDAO接口
 * @author liang
 * @version 2018-04-11
 */
@MyBatisDao
public interface KpiSchemeKpiDao extends CrudDao<KpiSchemeKpi> {

	public List<KpiSchemeKpi> findPageSubjective(KpiSchemeKpi kpiSchemeKpi);

	//查找状态
	public KpiSchemeKpi getAll(KpiSchemeKpi kpiSchemeKpi);
	
}