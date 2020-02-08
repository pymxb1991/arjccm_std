/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.car.dao.PlmCarViolationDao;
import com.arjjs.ccm.modules.plm.car.entity.PlmCarViolation;

/**
 * 违章记录Service
 * @author fu
 * @version 2018-07-02
 */
@Service
@Transactional(readOnly = true)
public class PlmCarViolationService extends CrudService<PlmCarViolationDao, PlmCarViolation> {

	public PlmCarViolation get(String id) {
		return super.get(id);
	}
	
	public List<PlmCarViolation> findList(PlmCarViolation plmCarViolation) {
		return super.findList(plmCarViolation);
	}
	
	public Page<PlmCarViolation> findPage(Page<PlmCarViolation> page, PlmCarViolation plmCarViolation) {
		return super.findPage(page, plmCarViolation);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmCarViolation plmCarViolation) {
		super.save(plmCarViolation);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmCarViolation plmCarViolation) {
		super.delete(plmCarViolation);
	}
	
}