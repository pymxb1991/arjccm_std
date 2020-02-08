/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.audit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.risk.audit.entity.RiskAudit;
import com.arjjs.ccm.modules.risk.audit.dao.RiskAuditDao;

/**
 * 重大事项审核Service
 * @author liang
 * @version 2018-04-02
 */
@Service
@Transactional(readOnly = true)
public class RiskAuditService extends CrudService<RiskAuditDao, RiskAudit> {

	public RiskAudit get(String id) {
		return super.get(id);
	}
	
	public List<RiskAudit> findList(RiskAudit riskAudit) {
		return super.findList(riskAudit);
	}
	
	public Page<RiskAudit> findPage(Page<RiskAudit> page, RiskAudit riskAudit) {
		return super.findPage(page, riskAudit);
	}
	
	@Transactional(readOnly = false)
	public void save(RiskAudit riskAudit) {
		super.save(riskAudit);
	}
	
	@Transactional(readOnly = false)
	public void delete(RiskAudit riskAudit) {
		super.delete(riskAudit);
	}
	
}