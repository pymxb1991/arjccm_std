/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.guard.entity;

import com.arjjs.ccm.modules.ccm.security.entity.CcmPatrolSecurity;
import org.hibernate.validator.constraints.Length;
import com.arjjs.ccm.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 警卫单位Entity
 * @author lijiupeng
 * @version 2019-07-17
 */
public class CcmGuardUnit extends DataEntity<CcmGuardUnit> {
	
	private static final long serialVersionUID = 1L;
	private CcmPatrolSecurity security;		// 警卫任务id
	private User user;		// 警卫
	private String patrolVehicles;		// 车辆
	private String vehicleEquipment;		// 车载设备
	private String individualEquipment;		// 单兵装备
	private Date departureTime;		// 时间
	private String status;		// 状态
	private String [] securityIds;
	private String userIds;
	private String guardLine;

	public String getGuardLine() {
		return guardLine;
	}

	public void setGuardLine(String guardLine) {
		this.guardLine = guardLine;
	}

	public String[] getSecurityIds() {
		return securityIds;
	}

	public void setSecurityIds(String[] securityIds) {
		this.securityIds = securityIds;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public CcmGuardUnit() {
		super();
	}

	public CcmGuardUnit(String id){
		super(id);
	}

	public CcmPatrolSecurity getSecurity() {
		return security;
	}

	public void setSecurity(CcmPatrolSecurity security) {
		this.security = security;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=255, message="车辆长度必须介于 0 和 255 之间")
	public String getPatrolVehicles() {
		return patrolVehicles;
	}

	public void setPatrolVehicles(String patrolVehicles) {
		this.patrolVehicles = patrolVehicles;
	}
	
	@Length(min=0, max=128, message="车载设备长度必须介于 0 和 128 之间")
	public String getVehicleEquipment() {
		return vehicleEquipment;
	}

	public void setVehicleEquipment(String vehicleEquipment) {
		this.vehicleEquipment = vehicleEquipment;
	}
	
	@Length(min=0, max=255, message="单兵装备长度必须介于 0 和 255 之间")
	public String getIndividualEquipment() {
		return individualEquipment;
	}

	public void setIndividualEquipment(String individualEquipment) {
		this.individualEquipment = individualEquipment;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	
	@Length(min=0, max=128, message="状态长度必须介于 0 和 128 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}