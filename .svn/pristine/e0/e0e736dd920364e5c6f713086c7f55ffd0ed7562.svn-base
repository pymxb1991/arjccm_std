/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.score.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.kpi.score.entity.KpiSchemeJournal;
import com.arjjs.ccm.modules.kpi.score.dao.KpiSchemeJournalDao;

/**
 * 绩效流水Service
 * @author liang
 * @version 2018-04-11
 */
@Service
@Transactional(readOnly = true)
public class KpiSchemeJournalService extends CrudService<KpiSchemeJournalDao, KpiSchemeJournal> {

	public KpiSchemeJournal get(String id) {
		return super.get(id);
	}
	
	public List<KpiSchemeJournal> findList(KpiSchemeJournal kpiSchemeJournal) {
		return super.findList(kpiSchemeJournal);
	}
	
	public Page<KpiSchemeJournal> findPage(Page<KpiSchemeJournal> page, KpiSchemeJournal kpiSchemeJournal) {
		return super.findPage(page, kpiSchemeJournal);
	}
	
	@Transactional(readOnly = false)
	public void save(KpiSchemeJournal kpiSchemeJournal) {
		super.save(kpiSchemeJournal);
	}
	
	@Transactional(readOnly = false)
	public void delete(KpiSchemeJournal kpiSchemeJournal) {
		super.delete(kpiSchemeJournal);
	}
	
}