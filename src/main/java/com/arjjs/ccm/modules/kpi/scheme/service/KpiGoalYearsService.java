/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.scheme.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.kpi.scheme.entity.KpiGoalYears;
import com.arjjs.ccm.modules.kpi.scheme.dao.KpiGoalYearsDao;

/**
 * 部门年度目标Service
 * @author liang
 * @version 2018-04-11
 */
@Service
@Transactional(readOnly = true)
public class KpiGoalYearsService extends CrudService<KpiGoalYearsDao, KpiGoalYears> {

	public KpiGoalYears get(String id) {
		return super.get(id);
	}
	
	public List<KpiGoalYears> findList(KpiGoalYears kpiGoalYears) {
		return super.findList(kpiGoalYears);
	}
	
	public Page<KpiGoalYears> findPage(Page<KpiGoalYears> page, KpiGoalYears kpiGoalYears) {
		return super.findPage(page, kpiGoalYears);
	}
	
	@Transactional(readOnly = false)
	public void save(KpiGoalYears kpiGoalYears) {
		super.save(kpiGoalYears);
	}
	
	@Transactional(readOnly = false)
	public void delete(KpiGoalYears kpiGoalYears) {
		super.delete(kpiGoalYears);
	}
	
}