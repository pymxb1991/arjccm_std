/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.score.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.kpi.score.entity.KpiFinalScore;
import com.arjjs.ccm.modules.kpi.score.entity.KpiSchemeScore;
import com.arjjs.ccm.modules.kpi.scheme.entity.KpiScheme;
import com.arjjs.ccm.modules.kpi.score.dao.KpiFinalScoreDao;
import com.arjjs.ccm.modules.kpi.score.dao.KpiSchemeScoreDao;

/**
 * 绩效KPI得分Service
 * @author liang
 * @version 2018-04-11
 */
@Service
@Transactional(readOnly = true)
public class KpiSchemeScoreService extends CrudService<KpiSchemeScoreDao, KpiSchemeScore> {

	@Autowired
	private KpiFinalScoreDao kpiFinalScoreDao;
	
	@Autowired
	private KpiSchemeScoreDao kpiSchemeScoreDao;
	
	public KpiSchemeScore get(String id) {
		return super.get(id);
	}
	
	public List<KpiSchemeScore> findList(KpiSchemeScore kpiSchemeScore) {
		return super.findList(kpiSchemeScore);
	}
	
	public Page<KpiSchemeScore> findPage(Page<KpiSchemeScore> page, KpiSchemeScore kpiSchemeScore) {
		return super.findPage(page, kpiSchemeScore);
	}
	
	@Transactional(readOnly = false)
	public void save(KpiSchemeScore kpiSchemeScore) {
		super.save(kpiSchemeScore);
	}
	
	@Transactional(readOnly = false)
	public void delete(KpiSchemeScore kpiSchemeScore) {
		super.delete(kpiSchemeScore);
	}

	public List<KpiFinalScore> getSchemeUserBySchemeID(KpiScheme kpiScheme) {
		List<KpiFinalScore> schemeUserLst = kpiFinalScoreDao.getSchemeUserBySchemeID(kpiScheme);
		
		return schemeUserLst;
	}

	public List<KpiSchemeScore> getSchemeScore(KpiSchemeScore kpiSchemeScore) {
		List<KpiSchemeScore> schemeScoreLst = kpiSchemeScoreDao.getSchemeScore(kpiSchemeScore);
		
		return schemeScoreLst;
	}
	
}