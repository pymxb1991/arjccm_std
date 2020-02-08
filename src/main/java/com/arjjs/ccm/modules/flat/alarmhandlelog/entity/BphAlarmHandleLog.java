/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.alarmhandlelog.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 处警过程记录Entity
 * @author wangyikai
 * @version 2018-11-22
 */
public class BphAlarmHandleLog extends DataEntity<BphAlarmHandleLog> {
	
	private static final long serialVersionUID = 1L;
	private String alarmId;		// 警情id
	private String handleId;		// 处警ID
	private User user;		// 操作人员id
	private Date operateTime;		// 操作时间
	private String operateDesc;		// 过程描述
	
	public BphAlarmHandleLog() {
		super();
	}

	public BphAlarmHandleLog(String id){
		super(id);
	}

	@Length(min=0, max=64, message="警情id长度必须介于 0 和 64 之间")
	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	
	@Length(min=0, max=128, message="过程描述长度必须介于 0 和 128 之间")
	public String getOperateDesc() {
		return operateDesc = operateDesc == null ? "" : operateDesc;
	}

	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}

	public String getHandleId() {
		return handleId;
	}

	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}
}