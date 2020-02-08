/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.entity;

import com.arjjs.ccm.modules.sys.entity.User;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * 巡逻单位Entity
 * @author lijiupeng
 * @version 2019-07-08
 */
public class CcmPatrolUnit extends DataEntity<CcmPatrolUnit> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 巡逻民警
	private String userIds;		// 巡逻民警
	private String patrolVehicles;		// 巡逻车辆
	private String vehicleEquipment;		// 车载设备
	private String individualEquipment;		// 单兵装备
	private Date departureTime;		// 时间
	private String status;		// 状态


	private CcmPatrolMissions missions; //任务外键
	private String userName;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}



	public String[] getMissionsIds() {
		return missionsIds;
	}

	public void setMissionsIds(String[] missionsIds) {
		this.missionsIds = missionsIds;
	}

	private String [] missionsIds;


	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public CcmPatrolMissions getMissions() {
		return missions;
	}

	public void setMissions(CcmPatrolMissions missions) {
		this.missions = missions;
	}

	public CcmPatrolUnit() {
		super();
	}

	public CcmPatrolUnit(String id){
		super(id);
	}

	@NotNull(message="巡逻民警不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=128, message="巡逻车辆长度必须介于 1 和 128 之间")
	public String getPatrolVehicles() {
		return patrolVehicles;
	}

	public void setPatrolVehicles(String patrolVehicles) {
		this.patrolVehicles = patrolVehicles;
	}
	
	@Length(min=1, max=128, message="车载设备长度必须介于 1 和 128 之间")
	public String getVehicleEquipment() {
		return vehicleEquipment;
	}

	public void setVehicleEquipment(String vehicleEquipment) {
		this.vehicleEquipment = vehicleEquipment;
	}
	
	@Length(min=1, max=255, message="单兵装备长度必须介于 1 和 255 之间")
	public String getIndividualEquipment() {
		return individualEquipment;
	}

	public void setIndividualEquipment(String individualEquipment) {
		this.individualEquipment = individualEquipment;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="时间不能为空")
	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	
	@Length(min=1, max=128, message="状态长度必须介于 1 和 128 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}