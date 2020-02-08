/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 违章记录Entity
 * @author fu
 * @version 2018-07-02
 */
public class PlmCarViolation extends DataEntity<PlmCarViolation> {
	
	private static final long serialVersionUID = 1L;
	private String carUseId;		// 领用单ID
	private PlmCar car;		// 违章车辆
	private PlmCarDriver driver;		// 违章司机
	private User use;		//领用责任人
	private String statue;		// 处理状态
	private Date violDate;		// 违章时间
	private String type;		// 违章类型
	private String addr;		// 违章地点
	private Integer forfeit;		// 罚款金额
	private Integer deduction;		// 扣分
	private Date beginViolDate;		// 开始 违章时间
	private Date endViolDate;		// 结束 违章时间
	
	public PlmCarViolation() {
		super();
	}

	public PlmCarViolation(String id){
		super(id);
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
	
	@Length(min=0, max=2, message="处理状态长度必须介于 0 和 2 之间")
	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getViolDate() {
		return violDate;
	}

	public void setViolDate(Date violDate) {
		this.violDate = violDate;
	}
	
	@Length(min=0, max=2, message="违章类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=100, message="违章地点长度必须介于 0 和 100 之间")
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	public Integer getForfeit() {
		return forfeit;
	}

	public void setForfeit(Integer forfeit) {
		this.forfeit = forfeit;
	}
	
	public Integer getDeduction() {
		return deduction;
	}

	public void setDeduction(Integer deduction) {
		this.deduction = deduction;
	}
	
	public Date getBeginViolDate() {
		return beginViolDate;
	}

	public void setBeginViolDate(Date beginViolDate) {
		this.beginViolDate = beginViolDate;
	}
	
	public Date getEndViolDate() {
		return endViolDate;
	}

	public void setEndViolDate(Date endViolDate) {
		this.endViolDate = endViolDate;
	}

	public String getCarUseId() {
		return carUseId;
	}

	public void setCarUseId(String carUseId) {
		this.carUseId = carUseId;
	}

	public User getUse() {
		return use;
	}

	public void setUse(User use) {
		this.use = use;
	}
		
}