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
import com.arjjs.ccm.modules.plm.car.dao.PlmCarUseSpendDao;
import com.arjjs.ccm.modules.plm.car.entity.PlmCarUseSpend;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.tool.EchartType;

/**
 * 用车费用记录Service
 * @author fu
 * @version 2018-07-04
 */
@Service
@Transactional(readOnly = true)
public class PlmCarUseSpendService extends CrudService<PlmCarUseSpendDao, PlmCarUseSpend> {
	
	@Autowired PlmCarUseSpendDao plmCarUseSpendDao;

	public PlmCarUseSpend get(String id) {
		return super.get(id);
	}
	
	public List<PlmCarUseSpend> findList(PlmCarUseSpend plmCarUseSpend) {
		return super.findList(plmCarUseSpend);
	}
	
	public Page<PlmCarUseSpend> findPage(Page<PlmCarUseSpend> page, PlmCarUseSpend plmCarUseSpend) {
		return super.findPage(page, plmCarUseSpend);
	}
	
	@Transactional(readOnly = false)
	public void save(PlmCarUseSpend plmCarUseSpend) {
		super.save(plmCarUseSpend);
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmCarUseSpend plmCarUseSpend) {
		super.delete(plmCarUseSpend);
	}

	public PlmCarUseSpend getByCarUseId(String id) {
		return plmCarUseSpendDao.getByCarUseId(id);
	}

	public List<EchartType> selectSpendByType(Office office) {
		return plmCarUseSpendDao.selectSpendByType(office);
	}

	public List<EchartType> selectSpendNumAllByOffice() {
		return plmCarUseSpendDao.selectSpendNumAllByOffice();
	}

	public List<EchartType> selectDistanceNumAllByOffice() {
		return plmCarUseSpendDao.selectDistanceNumAllByOffice();
	}

	public List<EchartType> selectDamagedAndDisNumByMonth() {
		return plmCarUseSpendDao.selectDamagedAndDisNumByMonth();
	}
	
}