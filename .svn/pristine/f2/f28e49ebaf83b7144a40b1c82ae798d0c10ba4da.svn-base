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
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.plm.car.dao.PlmCarUseDao;
import com.arjjs.ccm.modules.plm.car.entity.PlmCar;
import com.arjjs.ccm.modules.plm.car.entity.PlmCarDriver;
import com.arjjs.ccm.modules.plm.car.entity.PlmCarUse;
import com.arjjs.ccm.modules.plm.car.entity.PlmCarUseSpend;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.tool.EchartType;

/**
 * 领用记录Service
 * @author fu
 * @version 2018-07-02
 */
@Service
@Transactional(readOnly = true)
public class PlmCarUseService extends CrudService<PlmCarUseDao, PlmCarUse> {
	
	
	@Autowired
	private PlmCarService plmCarService;
	@Autowired
	private PlmCarDriverService plmCarDriverService;
	@Autowired
	private PlmCarUseSpendService plmCarUseSpendService;

	public PlmCarUse get(String id) {
		PlmCarUse entity = super.get(id);
		//获取用车费用
		PlmCarUseSpend spend= plmCarUseSpendService.getByCarUseId(id);
		entity.setSpend(spend);
		return entity;
	}
	
	public List<PlmCarUse> findList(PlmCarUse plmCarUse) {
		return super.findList(plmCarUse);
	}
	
	public Page<PlmCarUse> findPage(Page<PlmCarUse> page, PlmCarUse plmCarUse) {
		return super.findPage(page, plmCarUse);
	}
	
	@Transactional(readOnly = false)
//	@Transactional(rollbackFor = Exception.class)
	public void save(PlmCarUse plmCarUse) {
		super.save(plmCarUse);
		//若领出(归还人为空)，则修改车辆状态为领用，司机状态为外出；若归还修改车辆状态为空闲，司机状态为在岗
		if(plmCarUse.getGbuse()==null){
			
			PlmCar plmCar = plmCarService.get(plmCarUse.getCar().getId());
			//不同领用事由，修改车辆为不同状态
			switch (plmCarUse.getType()) {
			case "01":
				plmCar.setVehicleStatus("02");
				break;
			case "02":
				plmCar.setVehicleStatus("03");
				break;
			case "03":
				plmCar.setVehicleStatus("04");
				break;
			case "04":
				plmCar.setVehicleStatus("05");
				break;
			default:
				break;
			}
			plmCarService.save(plmCar);
			if(StringUtils.isNotBlank(plmCarUse.getDriver().getId())){
				PlmCarDriver plmCarDriver = plmCarDriverService.get(plmCarUse.getDriver().getId());
				plmCarDriver.setStatus("02");
				plmCarDriverService.save(plmCarDriver);
			}
			
		}else{
			//保存费用信息
			plmCarUse.getSpend().setCarUseId(plmCarUse.getId());
			plmCarUse.getSpend().setCar(plmCarUse.getCar());
			plmCarUse.getSpend().setDriver(plmCarUse.getDriver());
			plmCarUse.getSpend().setUse(plmCarUse.getUse());
			plmCarUse.getSpend().setType(plmCarUse.getType());
			plmCarUseSpendService.save(plmCarUse.getSpend());
			//修改车辆、司机状态为可用
			PlmCar plmCar = plmCarService.get(plmCarUse.getCar().getId());
			plmCar.setVehicleStatus("01");
			plmCarService.save(plmCar);
			if(StringUtils.isNotBlank(plmCarUse.getDriver().getId())){
				PlmCarDriver plmCarDriver = plmCarDriverService.get(plmCarUse.getDriver().getId());
				plmCarDriver.setStatus("01");
				plmCarDriverService.save(plmCarDriver);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(PlmCarUse plmCarUse) {
		super.delete(plmCarUse);
	}

	public List<EchartType> selectUseNumByCar(Office office) {
		return dao.selectUseNumByCar(office) ;
	}

	public List<EchartType> selectUseNumByOffice() {
		return dao.selectUseNumByOffice() ;
	}

	public List<EchartType> selectNumByDriver() {
		return dao.selectNumByDriver() ;
	}

	public List<EchartType> selectUseNumAllByOffice() {
		return dao.selectUseNumAllByOffice() ;
	}

	public List<EchartType> selectUseAndVioNumByMonth() {
		return dao.selectUseAndVioNumByMonth() ;
	}

	
}