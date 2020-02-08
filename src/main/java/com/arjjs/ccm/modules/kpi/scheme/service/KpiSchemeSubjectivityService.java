/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.scheme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.kpi.scheme.entity.KpiSchemeSubjectivity;
import com.arjjs.ccm.modules.kpi.scheme.dao.KpiSchemeSubjectivityDao;

/**
 * 绩效主观评分Service
 * @author liang
 * @version 2018-04-11
 */
@Service
@Transactional(readOnly = true)
public class KpiSchemeSubjectivityService extends CrudService<KpiSchemeSubjectivityDao, KpiSchemeSubjectivity> {

	@Autowired
	private KpiSchemeSubjectivityDao kpiSchemeSubjectivityDao;
	
	public KpiSchemeSubjectivity get(String id) {
		return super.get(id);
	}
	
	public List<KpiSchemeSubjectivity> findList(KpiSchemeSubjectivity kpiSchemeSubjectivity) {
		return super.findList(kpiSchemeSubjectivity);
	}
	
	public List<KpiSchemeSubjectivity> findListWithScore(KpiSchemeSubjectivity kpiSchemeSubjectivity) {
		List<KpiSchemeSubjectivity> subjectiveUserLst = kpiSchemeSubjectivityDao.findListWithScore(kpiSchemeSubjectivity);
		return subjectiveUserLst;
	}
	
	public Page<KpiSchemeSubjectivity> findPage(Page<KpiSchemeSubjectivity> page, KpiSchemeSubjectivity kpiSchemeSubjectivity) {
		return super.findPage(page, kpiSchemeSubjectivity);
	}
	
	@Transactional(readOnly = false)
	public void save(KpiSchemeSubjectivity kpiSchemeSubjectivity) {
		super.save(kpiSchemeSubjectivity);
	}
	
	@Transactional(readOnly = false)
	public void delete(KpiSchemeSubjectivity kpiSchemeSubjectivity) {
		super.delete(kpiSchemeSubjectivity);
	}

	//真删除
	@Transactional(readOnly = false)
	public void deleteTrue(KpiSchemeSubjectivity l) {
		kpiSchemeSubjectivityDao.deleteTrue(l);
	}
	
}