/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.deviceonline.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 设备在线实体类Entity
 * @author lgh
 * @version 2019-07-13
 */
public class CcmDeviceOnline extends DataEntity<CcmDeviceOnline> {
	
	private static final long serialVersionUID = 1L;
	private String deviceId;		// 设备id
	private Date loginTime;		// 登陆时间
	private Date updateTime;		// 状态更新时间
	private String status;		// 登录状态
	
	public CcmDeviceOnline() {
		super();
	}

	public CcmDeviceOnline(String id){
		super(id);
	}

	@Length(min=1, max=64, message="设备id长度必须介于 1 和 64 之间")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Length(min=1, max=2, message="登录状态长度必须介于 1 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}