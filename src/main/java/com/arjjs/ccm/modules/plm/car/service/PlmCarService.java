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
import com.arjjs.ccm.modules.plm.car.dao.PlmCarDao;
import com.arjjs.ccm.modules.plm.car.entity.PlmCar;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.tool.EchartType;
import com.arjjs.ccm.tool.Select2Type;

/**
 * 车辆Service
 * @author fu
 * @version 2018-06-30
 */
@Service
@Transactional(readOnly = true)
public class PlmCarService extends CrudService<PlmCarDao, PlmCar> {

	@Autowired
	private PlmCarDao plmCarDao;
	
	public PlmCar get(String id) {
		return super.get(id);
	}
	
	public List<PlmCar> findList(PlmCar plmCar) {
		return super.findList(plmCar);
	}
	
	public Page<PlmCar> findPage(Page<PlmCar> page, PlmCar plmCar) {
		return super.findPage(page, plmCar);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmCar plmCar) {
		super.save(plmCar);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmCar plmCar) {
		super.delete(plmCar);
	}

	public List<Select2Type> findSelect2Type(PlmCar plmCar) {
		return plmCarDao.findSelect2Type(plmCar);
	}

	public List<EchartType> selectNumByVtype(Office office) {
		return plmCarDao.selectNumByVtype(office);
	}
	
	public Integer countCar( ) {
		
		return plmCarDao.count();
	}
	public List<EchartType> countByStatusAjax(){
		return plmCarDao.countByStatusAjax();
	};
}