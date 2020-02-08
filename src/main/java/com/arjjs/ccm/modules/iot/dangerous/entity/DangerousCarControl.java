/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.dangerous.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 危化品车辆布控实体类Entity
 * @author lgh
 * @version 2019-06-05
 */
public class DangerousCarControl extends DataEntity<DangerousCarControl> {
	
	private static final long serialVersionUID = 1L;
	private String plateNumber;		// 车牌号码
	private String controllerType;		// 布控类型
	private Date startTime;		// 布控开始时间
	private Date endTime;		// 布控结束时间
	private String controllerLevel;		// 布控等级
	private String controllerScope;		// 布控范围
	private String controllerReason;		// 布控原因
	private String deviceCode;  //设备编号
	public DangerousCarControl() {
		super();
	}

	public DangerousCarControl(String id){
		super(id);
	}

	@Length(min=1, max=255, message="车牌号码长度必须介于 1 和 255 之间")
	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	@Length(min=1, max=2, message="布控类型长度必须介于 1 和 2 之间")
	public String getControllerType() {
		return controllerType;
	}

	public void setControllerType(String controllerType) {
		this.controllerType = controllerType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=1, max=2, message="布控等级长度必须介于 1 和 2 之间")
	public String getControllerLevel() {
		return controllerLevel;
	}

	public void setControllerLevel(String controllerLevel) {
		this.controllerLevel = controllerLevel;
	}
	
	@Length(min=1, max=255, message="布控范围长度必须介于 1 和 255 之间")
	public String getControllerScope() {
		return controllerScope;
	}

	public void setControllerScope(String controllerScope) {
		this.controllerScope = controllerScope;
	}
	
	@Length(min=0, max=255, message="布控原因长度必须介于 0 和 255 之间")
	public String getControllerReason() {
		return controllerReason;
	}

	public void setControllerReason(String controllerReason) {
		this.controllerReason = controllerReason;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	
}