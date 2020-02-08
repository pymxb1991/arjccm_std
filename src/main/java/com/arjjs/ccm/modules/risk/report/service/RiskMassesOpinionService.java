/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.report.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.risk.report.entity.RiskMassesOpinion;
import com.arjjs.ccm.modules.risk.report.dao.RiskMassesOpinionDao;

/**
 * 社情民意调查Service
 * @author liang
 * @version 2018-03-31
 */
@Service
@Transactional(readOnly = true)
public class RiskMassesOpinionService extends CrudService<RiskMassesOpinionDao, RiskMassesOpinion> {

	public RiskMassesOpinion get(String id) {
		return super.get(id);
	}
	
	public List<RiskMassesOpinion> findList(RiskMassesOpinion riskMassesOpinion) {
		return super.findList(riskMassesOpinion);
	}
	
	public Page<RiskMassesOpinion> findPage(Page<RiskMassesOpinion> page, RiskMassesOpinion riskMassesOpinion) {
		return super.findPage(page, riskMassesOpinion);
	}
	
	@Transactional(readOnly = false)
	public void save(RiskMassesOpinion riskMassesOpinion) {
		super.save(riskMassesOpinion);
	}
	
	@Transactional(readOnly = false)
	public void delete(RiskMassesOpinion riskMassesOpinion) {
		super.delete(riskMassesOpinion);
	}
	
}