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
import com.arjjs.ccm.modules.plm.car.dao.PlmCarDriverDao;
import com.arjjs.ccm.modules.plm.car.entity.PlmCarDriver;
import com.arjjs.ccm.tool.Select2Type;

/**
 * 驾驶员Service
 * @author fu
 * @version 2018-06-30
 */
@Service
@Transactional(readOnly = true)
public class PlmCarDriverService extends CrudService<PlmCarDriverDao, PlmCarDriver> {

	@Autowired
	private PlmCarDriverDao plmCarDriverDao;
	
	public PlmCarDriver get(String id) {
		return super.get(id);
	}
	
	public List<PlmCarDriver> findList(PlmCarDriver plmCarDriver) {
		return super.findList(plmCarDriver);
	}
	
	public Page<PlmCarDriver> findPage(Page<PlmCarDriver> page, PlmCarDriver plmCarDriver) {
		return super.findPage(page, plmCarDriver);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmCarDriver plmCarDriver) {
		super.save(plmCarDriver);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmCarDriver plmCarDriver) {
		super.delete(plmCarDriver);
	}

	public List<Select2Type> findSelect2Type(PlmCarDriver plmCarDriver) {
		return plmCarDriverDao.findSelect2Type(plmCarDriver);
	}
	
}