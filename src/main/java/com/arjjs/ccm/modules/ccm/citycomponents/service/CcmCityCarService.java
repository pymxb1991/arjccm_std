/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.citycomponents.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arjjs.ccm.common.persistence.Page;
import com.arjjs.ccm.common.service.CrudService;
import com.arjjs.ccm.modules.ccm.citycomponents.entity.CcmCityCar;
import com.arjjs.ccm.modules.ccm.citycomponents.dao.CcmCityCarDao;

/**
 * 特殊车辆服务管理Service
 * @author zjb
 * @version 2018-09-07
 */
@Service
@Transactional(readOnly = true)
public class CcmCityCarService extends CrudService<CcmCityCarDao, CcmCityCar> {

	public CcmCityCar get(String id) {
		return super.get(id);
	}
	
	public List<CcmCityCar> findList(CcmCityCar ccmCityCar) {
		return super.findList(ccmCityCar);
	}
	
	public Page<CcmCityCar> findPage(Page<CcmCityCar> page, CcmCityCar ccmCityCar) {
		return super.findPage(page, ccmCityCar);
	}
	
	@Transactional(readOnly = false)
	public void save(CcmCityCar ccmCityCar) {
		super.save(ccmCityCar);
	}
	
	@Transactional(readOnly = false)
	public void delete(CcmCityCar ccmCityCar) {
		super.delete(ccmCityCar);
	}
	
}