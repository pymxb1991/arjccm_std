/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.alarmnotify.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 警情通知记录Entity
 * @author maoxb
 * @version 2019-05-05
 */
public class BphAlarmNotify extends DataEntity<BphAlarmNotify> {
	
	private static final long serialVersionUID = 1L;
	private String alarmId;		// 警情ID
	private String receiveUserId;		// 接警人员
	private String receiveUserName;		// 接警人员
	private String type;		// 类型
	private String title;		// 标题
	private String content;		// 内容
	private String status;		// 状态
	private String planId;		// 预案ID
	private String stepId;		// 步骤ID
	private String actionId;		// 动作ID
	
	public BphAlarmNotify() {
		super();
	}

	public BphAlarmNotify(String id){
		super(id);
	}

	@Length(min=0, max=64, message="警情ID长度必须介于 0 和 64 之间")
	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}
	
	@Length(min=0, max=64, message="接警人员长度必须介于 0 和 64 之间")
	public String getReceiveUserId() {
		return receiveUserId;
	}

	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}
	
	@Length(min=0, max=10, message="类型长度必须介于 0 和 10 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=200, message="标题长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=2000, message="内容长度必须介于 0 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReceiveUserName() {
		return receiveUserName;
	}

	public void setReceiveUserName(String receiveUserName) {
		this.receiveUserName = receiveUserName;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
}