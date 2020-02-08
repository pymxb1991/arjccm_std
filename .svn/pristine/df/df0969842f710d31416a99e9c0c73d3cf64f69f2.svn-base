/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.risk.report.entity.RiskEventGreat;
import com.arjjs.ccm.modules.risk.report.entity.RiskIncident;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.modules.ccm.tree.entity.CcmTree;
import com.arjjs.ccm.modules.risk.report.dao.RiskEventGreatDao;
import com.arjjs.ccm.modules.risk.report.dao.RiskIncidentDao;

/**
 * 风险事件管理Service
 * @author liang
 * @version 2018-03-31
 */
@Service
@Transactional(readOnly = true)
public class RiskIncidentService extends CrudService<RiskIncidentDao, RiskIncident> {

	@Autowired
	private RiskEventGreatDao riskEventGreatDao;
	@Autowired
	private RiskIncidentDao riskIncidentDao;
	
	
	public RiskIncident get(String id) {
		return super.get(id);
	}
	
	public List<RiskIncident> findList(RiskIncident riskIncident) {
		return super.findList(riskIncident);
	}
	
	public Page<RiskIncident> findPage(Page<RiskIncident> page, RiskIncident riskIncident) {
		return super.findPage(page, riskIncident);
	}
	
	@Transactional(readOnly = false)
	public void save(RiskIncident riskIncident) {
		super.save(riskIncident);
	}
	
	@Transactional(readOnly = false)
	public void delete(RiskIncident riskIncident) {
		super.delete(riskIncident);
	}

	//查询重大事项报备
	public List<RiskEventGreat> findList(RiskEventGreat riskEventGreat) {
		return riskEventGreatDao.findList(riskEventGreat);
	}

	//查询风险事件重要程度统计
	public List<EchartType> findImportance(RiskIncident riskIncident) {
		return riskIncidentDao.findImportance(riskIncident);
	}

	//查询风险事件紧急程度统计
	public List<EchartType> findUrgency(RiskIncident riskIncident) {
		return riskIncidentDao.findUrgency(riskIncident);
	}

	//查询风险事件处理状态统计
	public List<EchartType> findDisposeType(RiskIncident riskIncident) {
		return riskIncidentDao.findDisposeType(riskIncident);
	}

	//查询list重大事项近几个月风险事件趋势图
	public List<EchartType> findTrend() {
		return riskIncidentDao.findTrend();
	}

	
	
}