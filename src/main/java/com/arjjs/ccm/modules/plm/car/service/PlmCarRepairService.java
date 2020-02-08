/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.plm.car.dao.PlmCarRepairDao;
import com.arjjs.ccm.modules.plm.car.entity.PlmCarRepair;
import com.arjjs.ccm.tool.Select2Type;

/**
 * 维保单位Service
 * @author fu
 * @version 2018-07-02
 */
@Service
@Transactional(readOnly = true)
public class PlmCarRepairService extends CrudService<PlmCarRepairDao, PlmCarRepair> {

	@Autowired
	private PlmCarRepairDao plmCarRepairDao;
	
	public PlmCarRepair get(String id) {
		return super.get(id);
	}
	
	public List<PlmCarRepair> findList(PlmCarRepair plmCarRepair) {
		return super.findList(plmCarRepair);
	}
	
	public Page<PlmCarRepair> findPage(Page<PlmCarRepair> page, PlmCarRepair plmCarRepair) {
		return super.findPage(page, plmCarRepair);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmCarRepair plmCarRepair) {
		super.save(plmCarRepair);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmCarRepair plmCarRepair) {
		super.delete(plmCarRepair);
	}

	public List<Select2Type> findSelect2Type(PlmCarRepair plmCarRepair) {
		return plmCarRepairDao.findSelect2Type(plmCarRepair);
	}
	
}