/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.unit.entity;

import com.arjjs.ccm.modules.flat.relief.entity.CcmReliefTask;
import com.arjjs.ccm.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 备勤单位实体类Entity
 * @author lgh
 * @version 2019-07-26
 */
public class CcmReliefUnit extends DataEntity<CcmReliefUnit> {

	private static final long serialVersionUID = 1L;
	private User user;		// 备勤人员
	private String userName;	// 备勤人员名字
	private String missionsId;		// 备勤任务

	public String getMissionName() {
		return missionName;
	}

	public void setMissionName(String missionName) {
		this.missionName = missionName;
	}

	private String missionName; //备勤名称
	private CcmReliefTask ccmReliefTask;	// 任务外键
	private String patrolVehicles;		// 备勤车辆
	private String more1;		// 存放officeID 为了查询简单
	private String more2;		// 冗余字段2
	private Date departureTime;		// 接受时间
	private String status;		// 任务状态

	public CcmReliefUnit() {
		super();
	}

	public CcmReliefUnit(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public CcmReliefTask getCcmReliefTask() {
		return ccmReliefTask;
	}

	public void setCcmReliefTask(CcmReliefTask ccmReliefTask) {
		this.ccmReliefTask = ccmReliefTask;
	}

	@Length(min=0, max=128, message="备勤任务长度必须介于 0 和 128 之间")
	public String getMissionsId() {
		return missionsId;
	}

	public void setMissionsId(String missionsId) {
		this.missionsId = missionsId;
	}

	@Length(min=0, max=128, message="备勤车辆长度必须介于 0 和 128 之间")
	public String getPatrolVehicles() {
		return patrolVehicles;
	}

	public void setPatrolVehicles(String patrolVehicles) {
		this.patrolVehicles = patrolVehicles;
	}

	@Length(min=0, max=255, message="冗余字段1长度必须介于 0 和 255 之间")
	public String getMore1() {
		return more1;
	}

	public void setMore1(String more1) {
		this.more1 = more1;
	}

	@Length(min=0, max=255, message="冗余字段2长度必须介于 0 和 255 之间")
	public String getMore2() {
		return more2;
	}

	public void setMore2(String more2) {
		this.more2 = more2;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	@Length(min=0, max=128, message="任务状态长度必须介于 0 和 128 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}