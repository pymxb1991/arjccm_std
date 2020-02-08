/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.special.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.kpi.special.entity.KpiSpecialScore;
import com.arjjs.ccm.modules.kpi.special.dao.KpiSpecialScoreDao;

/**
 * 考核细则Service
 * @author yiqingxuan
 * @version 2019-07-12
 */
@Service
@Transactional(readOnly = true)
public class KpiSpecialScoreService extends CrudService<KpiSpecialScoreDao, KpiSpecialScore> {
	
	@Autowired
	private KpiSpecialScoreDao kpiSpecialScoreDao;

	public KpiSpecialScore get(String id) {
		return super.get(id);
	}
	
	public List<KpiSpecialScore> findList(KpiSpecialScore kpiSpecialScore) {
		return super.findList(kpiSpecialScore);
	}
	
	public Page<KpiSpecialScore> findPage(Page<KpiSpecialScore> page, KpiSpecialScore kpiSpecialScore) {
		return super.findPage(page, kpiSpecialScore);
	}
	
	@Transactional(readOnly = false)
	public void save(KpiSpecialScore kpiSpecialScore) {
		super.save(kpiSpecialScore);
	}
	
	@Transactional(readOnly = false)
	public void delete(KpiSpecialScore kpiSpecialScore) {
		super.delete(kpiSpecialScore);
	}
	
	//获取网格得分情况排行
	public List<KpiSpecialScore> getForWG(KpiSpecialScore kpiSpecialScore) {
		return kpiSpecialScoreDao.getForWG(kpiSpecialScore);
	}
	
	//获取社区得分情况排行
	public List<KpiSpecialScore> getForSQ(KpiSpecialScore kpiSpecialScore) {
		logger.info("--------->>>>>>>>>>分割线");
		return kpiSpecialScoreDao.getForSQ(kpiSpecialScore);
	}
	
	//获取区县得分情况排行
	public List<KpiSpecialScore> getForQX(KpiSpecialScore kpiSpecialScore) {
		return kpiSpecialScoreDao.getForQX(kpiSpecialScore);
	}
	
}