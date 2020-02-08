/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.unit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.flat.unit.entity.CcmReliefUnit;
import com.arjjs.ccm.modules.flat.unit.dao.CcmReliefUnitDao;

/**
 * 备勤单位实体类Service
 * @author lgh
 * @version 2019-07-26
 */
@Service
@Transactional(readOnly = true)
public class CcmReliefUnitService extends CrudService<CcmReliefUnitDao, CcmReliefUnit> {

	public CcmReliefUnit get(String id) {
		return super.get(id);
	}
	
	public List<CcmReliefUnit> findList(CcmReliefUnit ccmReliefUnit) {
		return super.findList(ccmReliefUnit);
	}
	
	public Page<CcmReliefUnit> findPage(Page<CcmReliefUnit> page, CcmReliefUnit ccmReliefUnit) {
		return super.findPage(page, ccmReliefUnit);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmReliefUnit ccmReliefUnit) {
		super.save(ccmReliefUnit);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmReliefUnit ccmReliefUnit) {
		super.delete(ccmReliefUnit);
	}
	
}