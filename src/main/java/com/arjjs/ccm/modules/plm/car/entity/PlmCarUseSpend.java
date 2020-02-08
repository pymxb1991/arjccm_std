/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 用车费用记录Entity
 * @author fu
 * @version 2018-07-04
 */
public class PlmCarUseSpend extends DataEntity<PlmCarUseSpend> {
	
	private static final long serialVersionUID = 1L;
	private String carUseId;		// 领用单ID
	private PlmCar car;		// 车辆ID
	private PlmCarDriver driver;		// 司机ID
	private User use;		// 领用人ID
	private PlmCarRepair repairCom;		//维保单位Id
	private String type;		// 费用类型
	private String isDamaged;		// 是否损坏
	private Double mildage;		// 行驶里程(km)
	private Double oilFee;		// 加油费(元)
	private Double tollFee;		// 过路费(元)
	private Double parkingFee;		// 停车费(元)
	private Double repairFee;		// 保/修费用(元)
	private Double otherFee;		// 其他费用(元)
	private Double totaFee;		// 总费用(元)
	
	public PlmCarUseSpend() {
		super();
	}

	public PlmCarUseSpend(String id){
		super(id);
	}

	@Length(min=0, max=64, message="领用单ID长度必须介于 0 和 64 之间")
	public String getCarUseId() {
		return carUseId;
	}

	public void setCarUseId(String carUseId) {
		this.carUseId = carUseId;
	}
	
	public PlmCar getCar() {
		return car;
	}

	public void setCar(PlmCar car) {
		this.car = car;
	}
	
	
	public PlmCarDriver getDriver() {
		return driver;
	}

	public void setDriver(PlmCarDriver driver) {
		this.driver = driver;
	}
	
	public User getUse() {
		return use;
	}

	public void setUse(User use) {
		this.use = use;
	}
	
	@Length(min=0, max=2, message="费用类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=2, message="是否损坏长度必须介于 0 和 2 之间")
	public String getIsDamaged() {
		return isDamaged;
	}

	public void setIsDamaged(String isDamaged) {
		this.isDamaged = isDamaged;
	}
	
	public Double getMildage() {
		return mildage;
	}

	public void setMildage(Double mildage) {
		this.mildage = mildage;
	}
	
	public Double getOilFee() {
		return oilFee;
	}

	public void setOilFee(Double oilFee) {
		this.oilFee = oilFee;
	}
	
	public Double getTollFee() {
		return tollFee;
	}

	public void setTollFee(Double tollFee) {
		this.tollFee = tollFee;
	}
	
	public Double getParkingFee() {
		return parkingFee;
	}

	public void setParkingFee(Double parkingFee) {
		this.parkingFee = parkingFee;
	}
	
	public Double getRepairFee() {
		return repairFee;
	}

	public void setRepairFee(Double repairFee) {
		this.repairFee = repairFee;
	}
	
	public Double getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(Double otherFee) {
		this.otherFee = otherFee;
	}
	
	public Double getTotaFee() {
		return totaFee;
	}

	public void setTotaFee(Double totaFee) {
		this.totaFee = totaFee;
	}

	public PlmCarRepair getRepairCom() {
		return repairCom;
	}

	public void setRepairCom(PlmCarRepair repairCom) {
		this.repairCom = repairCom;
	}
	
}