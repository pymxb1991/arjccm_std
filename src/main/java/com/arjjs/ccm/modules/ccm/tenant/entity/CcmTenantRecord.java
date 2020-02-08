/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.tenant.entity;



import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;

/**
 * 历史租客记录表Entity
 * @author lgh
 * @version 2019-04-20
 */
public class CcmTenantRecord extends DataEntity<CcmTenantRecord> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String phoneNumber;		// 电话号码
	private String idCard;		// 身份证号码
	private String houseId;		// 房屋id
	private Date leaveDate;   //离开时间
	private Date liveDate;   //入住时间
	private  CcmPeople  ccmPeople;//人口表信息
	
	
	
	public CcmPeople getCcmPeople() {
		return ccmPeople;
	}

	public void setCcmPeople(CcmPeople ccmPeople) {
		this.ccmPeople = ccmPeople;
	}

	public Date getLiveDate() {
		return liveDate;
	}

	public void setLiveDate(Date liveDate) {
		this.liveDate = liveDate;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public CcmTenantRecord() {
		super();
	}

	public CcmTenantRecord(String id){
		super(id);
	}

	@Length(min=0, max=64, message="姓名长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="电话号码长度必须介于 0 和 64 之间")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Length(min=0, max=64, message="身份证号码长度必须介于 0 和 64 之间")
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	@Length(min=0, max=64, message="房屋id长度必须介于 0 和 64 之间")
	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	
}