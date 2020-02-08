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
import com.arjjs.ccm.modules.risk.report.entity.RiskReport;
import com.arjjs.ccm.modules.risk.audit.dao.RiskAuditDao;
import com.arjjs.ccm.modules.risk.report.dao.RiskReportDao;

/**
 * 事项评估报告Service
 * @author liang
 * @version 2018-04-02
 */
@Service
@Transactional(readOnly = true)
public class RiskReportService extends CrudService<RiskReportDao, RiskReport> {

	@Autowired
	private RiskReportDao riskReportDao;
	@Autowired
	private RiskAuditDao riskAuditDao;
	
	
	public RiskReport get(String id) {
		return super.get(id);
	}
	
	public List<RiskReport> findList(RiskReport riskReport) {
		return super.findList(riskReport);
	}
	
	public Page<RiskReport> findPage(Page<RiskReport> page, RiskReport riskReport) {
		return super.findPage(page, riskReport);
	}
	
	@Transactional(readOnly = false)
	public void save(RiskReport riskReport) {
		super.save(riskReport);
	}
	
	@Transactional(readOnly = false)
	public void delete(RiskReport riskReport) {
		super.delete(riskReport);
	}

	//入库查询
	public Page<RiskReport> findListDatabasePage(Page<RiskReport> page, RiskReport riskReport) {
		riskReport.setPage(page);
		page.setList(riskReportDao.findListDatabasePage(riskReport));
		return page;
	}

	//审核上报表单提交，保存audit
	@Transactional(readOnly = false)
	public void saveInsertAll(RiskReport riskReport) {
		if (riskReport.getRiskAuditList().size() > 0) {
			riskAuditDao.saveInsertAll(riskReport.getRiskAuditList());
		}		
	}

	
	
}