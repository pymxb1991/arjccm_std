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
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.SearchTab;
import com.arjjs.ccm.modules.risk.report.dao.RiskEventGreatDao;

/**
 * 重大事项报备Service
 * @author liang
 * @version 2018-03-30
 */
@Service
@Transactional(readOnly = true)
public class RiskEventGreatService extends CrudService<RiskEventGreatDao, RiskEventGreat> {

	@Autowired
	private RiskEventGreatDao riskEventGreatDao;
	
	public RiskEventGreat get(String id) {
		return super.get(id);
	}
	
	public List<RiskEventGreat> findList(RiskEventGreat riskEventGreat) {
		return super.findList(riskEventGreat);
	}
	
	public Page<RiskEventGreat> findPage(Page<RiskEventGreat> page, RiskEventGreat riskEventGreat) {
		return super.findPage(page, riskEventGreat);
	}
	
	@Transactional(readOnly = false)
	public void save(RiskEventGreat riskEventGreat) {
		super.save(riskEventGreat);
	}
	
	@Transactional(readOnly = false)
	public void delete(RiskEventGreat riskEventGreat) {
		super.delete(riskEventGreat);
	}

	//入库查询
	public Page<RiskEventGreat> findListDatabasePage(Page<RiskEventGreat> page, RiskEventGreat riskEventGreat) {
		riskEventGreat.setPage(page);
		page.setList(riskEventGreatDao.findListDatabasePage(riskEventGreat));
		return page;
	}

	//查询list重大事项数据分析
	public List<SearchTab> findListNum() {
		return riskEventGreatDao.findListNum();
	}

	//查询list重大事项近几个月事项报备趋势图
	public List<EchartType> findListTrend() {
		return riskEventGreatDao.findListTrend();
	}

	//未调查
	public int findSum1() {
		return riskEventGreatDao.findSum1();
	}

	//已调查
	public int findSum2() {
		return riskEventGreatDao.findSum2();
	}

	//未提交
	public int findSum3() {
		return riskEventGreatDao.findSum3();
	}

	//已提交
	public int findSum4() {
		return riskEventGreatDao.findSum4();
	}
	
}