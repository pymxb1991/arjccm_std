/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.warning.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 预警记录Entity
 * @author yiqingxuan
 * @version 2019-07-24
 */
public class CcmEarlyWarning extends DataEntity<CcmEarlyWarning> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String sex;		// 性别
	private String age;		// 年龄
	private String idCard;		// 身份证
	private String peopleType;		// 人员类型
	private String specialType;		// 特殊类型
	private String alarmFacility;		// 预警设备
	private String type; //状态
	private String address;		// 地址
	private Date alarmDate;		// 预警时间
	private String isCheck;		// 是否核实
	private String img;		// 图片
	private String peopleEmphasis;		// 是否为重点人员
	private String macAddress;		// MAC地址
	private String rfid;		// RFID
	private String imei;		// IMEI
	private String phone;		// 手机
	private String carid;    //车牌号
	private String isPeople; //1 人  2车
	private String place;  //环境场所
	private Date beginDate; // 开始 时间
	private Date endDate; // 结束 时间
	private String x; // 经度
	private String y; // 纬度
	private String alarmType;	//预警类型

	public CcmEarlyWarning() {
		super();
	}

	public CcmEarlyWarning(String id){
		super(id);
	}

	@Length(min=0, max=64, message="姓名长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=2, message="性别长度必须介于 0 和 2 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=3, message="年龄长度必须介于 0 和 3 之间")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@Length(min=0, max=18, message="身份证长度必须介于 0 和 18 之间")
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

	@Length(min=0, max=64, message="特殊类型长度必须介于 0 和 64 之间")
	public String getSpecialType() {
		return specialType;
	}

	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}
	
	@Length(min=0, max=20, message="预警设备长度必须介于 0 和 20 之间")
	public String getAlarmFacility() {
		return alarmFacility;
	}

	public void setAlarmFacility(String alarmFacility) {
		this.alarmFacility = alarmFacility;
	}
	
	@Length(min=0, max=255, message="地址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAlarmDate() {
		return alarmDate;
	}

	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
	}
	
	@Length(min=0, max=2, message="是否核实长度必须介于 0 和 2 之间")
	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	
	@Length(min=0, max=255, message="图片长度必须介于 0 和 255 之间")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	@Length(min=0, max=2, message="是否为重点人员长度必须介于 0 和 2 之间")
	public String getPeopleEmphasis() {
		return peopleEmphasis;
	}

	public void setPeopleEmphasis(String peopleEmphasis) {
		this.peopleEmphasis = peopleEmphasis;
	}
	
	@Length(min=0, max=64, message="MAC地址长度必须介于 0 和 64 之间")
	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	
	@Length(min=0, max=64, message="RFID长度必须介于 0 和 64 之间")
	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	
	@Length(min=0, max=64, message="IMEI长度必须介于 0 和 64 之间")
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
	
	@Length(min=0, max=64, message="手机长度必须介于 0 和 64 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCarid() {
		return carid;
	}

	public void setCarid(String carid) {
		this.carid = carid;
	}

	public String getIsPeople() {
		return isPeople;
	}

	public void setIsPeople(String isPeople) {
		this.isPeople = isPeople;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
}