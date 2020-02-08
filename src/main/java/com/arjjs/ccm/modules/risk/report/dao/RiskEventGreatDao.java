/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.report.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.risk.report.entity.RiskEventGreat;
import com.arjjs.ccm.modules.risk.report.entity.RiskIncident;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.SearchTab;

/**
 * 重大事项报备DAO接口
 * @author liang
 * @version 2018-03-30
 */
@MyBatisDao
public interface RiskEventGreatDao extends CrudDao<RiskEventGreat> {

	//入库查询
	List<RiskEventGreat> findListDatabasePage(RiskEventGreat riskEventGreat);

	//查询list重大事项数据分析
	List<SearchTab> findListNum();

	//查询list重大事项近几个月事项报备趋势图
	List<EchartType> findListTrend();

	//未调查
	int findSum1();

	//已调查
	int findSum2();

	//未提交
	int findSum3();

	//已提交
	int findSum4();

	
}