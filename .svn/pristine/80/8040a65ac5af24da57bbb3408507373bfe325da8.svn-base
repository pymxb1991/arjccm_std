/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.device.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 探针布控Entity
 * @author lhf
 * @version 2019-07-23
 */
public class CcmDeviceControl extends DataEntity<CcmDeviceControl> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String sex;		// 性别
	private String age;		// 年龄
	private String idCard;		// 身份证
	private String peopleType;		// 人员类型
	private String reason;		// 布控原因
	private Date startDate;		// 布控开始时间
	private Date endDate;		// 布控结束时间
	private String grade;		// 布控等级
	private String controlRange;		// 布控范围
	private String controlType;		// 布控类型
	private String controlBy;		// 布控设备（1为WIFI，2为RFID,3为人员定位预警）
	private String peopleEmphasis;		// 是否为重点人员
	private String reserved1;		// 预留字段1
	private String reserved2;		// 预留字段2
	private String deviceCode;    //设备编号
	private String phones;		//手机号码
	
	public CcmDeviceControl() {
		super();
	}

	public CcmDeviceControl(String id){
		super(id);
	}

	@Length(min=1, max=64, message="姓名长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=2, message="性别长度必须介于 1 和 2 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=1, max=3, message="年龄长度必须介于 1 和 3 之间")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@Length(min=1, max=18, message="身份证长度必须介于 1 和 18 之间")
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	@Length(min=0, max=64, message="人员类型长度必须介于 0 和 64 之间")
	public String getPeopleType() {
		return peopleType;
	}

	public void setPeopleType(String peopleType) {
		this.peopleType = peopleType;
	}
	
	@Length(min=0, max=255, message="布控原因长度必须介于 0 和 255 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Length(min=0, max=2, message="布控等级长度必须介于 0 和 2 之间")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Length(min=1, max=64, message="布控范围长度必须介于 1 和 64 之间")
	public String getControlRange() {
		return controlRange;
	}

	public void setControlRange(String controlRange) {
		this.controlRange = controlRange;
	}
	
	@Length(min=0, max=20, message="布控类型长度必须介于 0 和 20 之间")
	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}
	
	@Length(min=0, max=1, message="布控设备（1为WIFI，2为RFID）长度必须介于 0 和 1 之间")
	public String getControlBy() {
		return controlBy;
	}

	public void setControlBy(String controlBy) {
		this.controlBy = controlBy;
	}
	
	@Length(min=0, max=2, message="是否为重点人员长度必须介于 0 和 2 之间")
	public String getPeopleEmphasis() {
		return peopleEmphasis;
	}

	public void setPeopleEmphasis(String peopleEmphasis) {
		this.peopleEmphasis = peopleEmphasis;
	}
	
	@Length(min=0, max=255, message="预留字段1长度必须介于 0 和 255 之间")
	public String getReserved1() {
		return reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}
	
	@Length(min=0, max=255, message="预留字段2长度必须介于 0 和 255 之间")
	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	@Length(min=0, max=255, message="手机号长度必须介于 0 和 255 之间")
	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}
}