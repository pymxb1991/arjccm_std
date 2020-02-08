/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.overallControl.entity;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.ccm.car.entity.CcmCarControl;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.iot.device.entity.CcmDeviceControl;
import com.arjjs.ccm.modules.iot.face.entity.CcmFaceControl;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * 整体布控实体类Entity
 * @author lgh
 * @version 2019-06-05
 */
public class CcmOverallControl extends DataEntity<CcmOverallControl> {

	private static final long serialVersionUID = 1L;

	//布控内容判断
	private boolean face;	//是否布控人脸
	private boolean device;	//是否布控手机
	private boolean car;	//是否布控汽车

	//人员基本信息
	private String name;	//姓名
	private String sex;		//性别
	private String ident;	//公民身份号码
	private String age;		//年龄

	//布控基本信息
	private String controlName;	//布控名称
	private String controlGrade;	//布控等级
	private Date startDate;		// 布控开始时间
	private Date endDate;		// 布控结束时间
	private String reason;		// 布控原因

	//人脸布控信息
	private List<String> librarySelect;	//名单库
	private List<String> captureMachine;	//抓拍机
	private String images;	//照片

	//手机布控信息
	private List<String> telephone;	//手机号
	private String phoneRange;	//手机布控范围
	private String phoneType;	//手机布控类型

	//汽车布控信息
	private List<String> vehicleNumber;	//车牌号码
	private String vehicleType;		//车辆布控类型

	public CcmOverallControl() {
		super();
	}

	public CcmOverallControl(String id){
		super(id);
	}


	public boolean isFace() {
		return face;
	}

	public void setFace(boolean face) {
		this.face = face;
	}

	public boolean isDevice() {
		return device;
	}

	public void setDevice(boolean device) {
		this.device = device;
	}

	public boolean isCar() {
		return car;
	}

	public void setCar(boolean car) {
		this.car = car;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getControlName() {
		return controlName;
	}

	public void setControlName(String controlName) {
		this.controlName = controlName;
	}

	public String getControlGrade() {
		return controlGrade;
	}

	public void setControlGrade(String controlGrade) {
		this.controlGrade = controlGrade;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<String> getLibrarySelect() {
		return librarySelect;
	}

	public void setLibrarySelect(List<String> librarySelect) {
		this.librarySelect = librarySelect;
	}

	public List<String> getCaptureMachine() {
		return captureMachine;
	}

	public void setCaptureMachine(List<String> captureMachine) {
		this.captureMachine = captureMachine;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public List<String> getTelephone() {
		return telephone;
	}

	public void setTelephone(List<String> telephone) {
		this.telephone = telephone;
	}

	public String getPhoneRange() {
		return phoneRange;
	}

	public void setPhoneRange(String phoneRange) {
		this.phoneRange = phoneRange;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public List<String> getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(List<String> vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
}