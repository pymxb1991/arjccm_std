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
 * 领用记录Entity
 * @author fu
 * @version 2018-07-02
 */
public class PlmCarUse extends DataEntity<PlmCarUse> {
	
	private static final long serialVersionUID = 1L;
	private PlmCar car;		// 车辆
	private PlmCarDriver driver;		// 司机
	private User use;		// 领出人
	private Date outDate;		// 领出日期
	private Double outMileage;		//领出总里程(km)
	private String type;			// 流程类型
	private String cause ; // 领用事由
	private String process;		// 关联流程
	private User gbuse;		// 归还人
	private Date inDate;		// 归还日期
	private Double inMileage;		//归还总里程(km)
	private Date beginOutDate;		// 开始 领出日期
	private Date endOutDate;		// 结束 领出日期
	private String gbFlag;		//是否归还
	
	private PlmCarUseSpend spend;		//本次领用费用
	
	
	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public PlmCarUse() {
		super();
	}

	public PlmCarUse(String id){
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
	
	public User getUse() {
		return use;
	}

	public void setUse(User use) {
		this.use = use;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	
	@Length(min=0, max=2, message="领用事由长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}
	
	public User getGbuse() {
		return gbuse;
	}

	public void setGbuse(User gbuse) {
		this.gbuse = gbuse;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public String getGbFlag() {
		return gbFlag;
	}

	public void setGbFlag(String gbFlag) {
		this.gbFlag = gbFlag;
	}
	public Date getBeginOutDate() {
		return beginOutDate;
	}

	public void setBeginOutDate(Date beginOutDate) {
		this.beginOutDate = beginOutDate;
	}
	
	public Date getEndOutDate() {
		return endOutDate;
	}

	public void setEndOutDate(Date endOutDate) {
		this.endOutDate = endOutDate;
	}

	public Double getOutMileage() {
		return outMileage;
	}

	public void setOutMileage(Double outMileage) {
		this.outMileage = outMileage;
	}

	public Double getInMileage() {
		return inMileage;
	}

	public void setInMileage(Double inMileage) {
		this.inMileage = inMileage;
	}

	public PlmCarUseSpend getSpend() {
		return spend;
	}

	public void setSpend(PlmCarUseSpend spend) {
		this.spend = spend;
	}
	
}