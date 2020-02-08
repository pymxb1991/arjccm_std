/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.report.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.risk.report.entity.RiskIncident;
import com.arjjs.ccm.tool.EchartType;

/**
 * 风险事件管理DAO接口
 * @author liang
 * @version 2018-03-31
 */
@MyBatisDao
public interface RiskIncidentDao extends CrudDao<RiskIncident> {

	//查询风险事件重要程度统计
	List<EchartType> findImportance(RiskIncident riskIncident);

	//查询风险事件紧急程度统计
	List<EchartType> findUrgency(RiskIncident riskIncident);

	//查询风险事件处理状态统计
	List<EchartType> findDisposeType(RiskIncident riskIncident);

	//查询list重大事项近几个月风险事件趋势图
	List<EchartType> findTrend();
	
}