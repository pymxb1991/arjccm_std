/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.scheme.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.kpi.scheme.entity.KpiScheme;
import com.arjjs.ccm.modules.kpi.scheme.dao.KpiSchemeDao;

/**
 * 绩效考评方案Service
 * @author liang
 * @version 2018-04-11
 */
@Service
@Transactional(readOnly = true)
public class KpiSchemeService extends CrudService<KpiSchemeDao, KpiScheme> {

	public KpiScheme get(String id) {
		return super.get(id);
	}
	
	public List<KpiScheme> findList(KpiScheme kpiScheme) {
		return super.findList(kpiScheme);
	}
	
	public Page<KpiScheme> findPage(Page<KpiScheme> page, KpiScheme kpiScheme) {
		return super.findPage(page, kpiScheme);
	}
	
	@Transactional(readOnly = false)
	public void save(KpiScheme kpiScheme) {
		super.save(kpiScheme);
	}
	
	@Transactional(readOnly = false)
	public void delete(KpiScheme kpiScheme) {
		super.delete(kpiScheme);
	}
	
}