/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.manage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.risk.manage.entity.RiskAssessFlow;
import com.arjjs.ccm.modules.risk.manage.dao.RiskAssessFlowDao;

/**
 * 评估流程管理Service
 * @author liang
 * @version 2018-04-04
 */
@Service
@Transactional(readOnly = true)
public class RiskAssessFlowService extends CrudService<RiskAssessFlowDao, RiskAssessFlow> {

	public RiskAssessFlow get(String id) {
		return super.get(id);
	}
	
	public List<RiskAssessFlow> findList(RiskAssessFlow riskAssessFlow) {
		return super.findList(riskAssessFlow);
	}
	
	public Page<RiskAssessFlow> findPage(Page<RiskAssessFlow> page, RiskAssessFlow riskAssessFlow) {
		return super.findPage(page, riskAssessFlow);
	}
	
	@Transactional(readOnly = false)
	public void save(RiskAssessFlow riskAssessFlow) {
		super.save(riskAssessFlow);
	}
	
	@Transactional(readOnly = false)
	public void delete(RiskAssessFlow riskAssessFlow) {
		super.delete(riskAssessFlow);
	}
	
}