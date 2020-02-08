/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.manage.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.risk.manage.entity.RiskSpecialist;
import com.arjjs.ccm.modules.risk.manage.dao.RiskSpecialistDao;

/**
 * 专家库Service
 * @author liang
 * @version 2018-03-31
 */
@Service
@Transactional(readOnly = true)
public class RiskSpecialistService extends CrudService<RiskSpecialistDao, RiskSpecialist> {

	public RiskSpecialist get(String id) {
		return super.get(id);
	}
	
	public List<RiskSpecialist> findList(RiskSpecialist riskSpecialist) {
		return super.findList(riskSpecialist);
	}
	
	public Page<RiskSpecialist> findPage(Page<RiskSpecialist> page, RiskSpecialist riskSpecialist) {
		return super.findPage(page, riskSpecialist);
	}
	
	@Transactional(readOnly = false)
	public void save(RiskSpecialist riskSpecialist) {
		super.save(riskSpecialist);
	}
	
	@Transactional(readOnly = false)
	public void delete(RiskSpecialist riskSpecialist) {
		super.delete(riskSpecialist);
	}
	
}