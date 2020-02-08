/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.citycomponents.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 特殊车辆服务管理Entity
 * @author zjb
 * @version 2018-09-07
 */
public class CcmCityCar extends DataEntity<CcmCityCar> {
	
	private static final long serialVersionUID = 1L;
	private String number;		// 车牌号码
	private String colorCar;		// 车身颜色
	private String type;		// 车辆类型
	private String colorCarPlate;		// 车牌颜色
	private String transportType;		// 特种运输类型
	private String carCompany;		// 车辆所属企业
	private String principle;		// 企业负责人
	private String principleTel;		// 企业负责人联系方式
	private String name;		// 车主姓名
	private String sex;		// 车主性别
	private String drivingLicence;		// 车主驾驶证号码
	private String ident;		// 车主身份证号码
	private String tel;		// 联系电话
	private String images;		// 车辆照片
	
	public CcmCityCar() {
		super();
	}

	public CcmCityCar(String id){
		super(id);
	}

	@Length(min=0, max=64, message="车牌号码长度必须介于 0 和 64 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=1, message="车身颜色长度必须介于 0 和 1 之间")
	public String getColorCar() {
		return colorCar;
	}

	public void setColorCar(String colorCar) {
		this.colorCar = colorCar;
	}
	
	@Length(min=0, max=2, message="车辆类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=1, message="车牌颜色长度必须介于 0 和 1 之间")
	public String getColorCarPlate() {
		return colorCarPlate;
	}

	public void setColorCarPlate(String colorCarPlate) {
		this.colorCarPlate = colorCarPlate;
	}
	
	@Length(min=0, max=2, message="特种运输类型长度必须介于 0 和 2 之间")
	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}
	
	@Length(min=0, max=64, message="车辆所属企业长度必须介于 0 和 64 之间")
	public String getCarCompany() {
		return carCompany;
	}

	public void setCarCompany(String carCompany) {
		this.carCompany = carCompany;
	}
	
	@Length(min=0, max=64, message="企业负责人长度必须介于 0 和 64 之间")
	public String getPrinciple() {
		return principle;
	}

	public void setPrinciple(String principle) {
		this.principle = principle;
	}
	
	@Length(min=0, max=18, message="企业负责人联系方式长度必须介于 0 和 18 之间")
	public String getPrincipleTel() {
		return principleTel;
	}

	public void setPrincipleTel(String principleTel) {
		this.principleTel = principleTel;
	}
	
	@Length(min=0, max=64, message="车主姓名长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="车主性别长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=18, message="车主驾驶证号码长度必须介于 0 和 18 之间")
	public String getDrivingLicence() {
		return drivingLicence;
	}

	public void setDrivingLicence(String drivingLicence) {
		this.drivingLicence = drivingLicence;
	}
	
	@Length(min=0, max=18, message="车主身份证号码长度必须介于 0 和 18 之间")
	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}
	
	@Length(min=0, max=18, message="联系电话长度必须介于 0 和 18 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=255, message="车辆照片长度必须介于 0 和 255 之间")
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
}