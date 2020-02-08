/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.score.dao;

import java.util.List;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.kpi.scheme.entity.KpiScheme;
import com.arjjs.ccm.modules.kpi.score.entity.KpiFinalScore;

/**
 * 绩效总成绩DAO接口
 * @author pjq
 * @version 2018-04-11
 */
@MyBatisDao
public interface KpiFinalScoreDao extends CrudDao<KpiFinalScore> {
	public List<KpiScheme> findKpiScheme(KpiFinalScore kpiFinalScore);
	public List<KpiFinalScore> getFinalScoreUser(KpiFinalScore kpiFinalScore);
	public List<KpiFinalScore> getFinalScoreBySchemeUserId(KpiFinalScore kpiFinalScore);
	
	public List<KpiFinalScore> getSchemeUserBySchemeID(KpiScheme kpiScheme);
	//添加绩效总得分，相对应的方案,各个kpi
	public List<KpiFinalScore> findSumAll(KpiFinalScore kpiFinalScore);
	//添加绩效总得分，相对应的方案,总绩效
	public List<KpiFinalScore> findSum(KpiFinalScore kpiFinalScore);
	
}