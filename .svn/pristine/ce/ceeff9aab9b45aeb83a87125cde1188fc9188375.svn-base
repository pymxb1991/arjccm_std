/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.actionuser.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 动作执行人员关联表Entity
 * 
 * @author liu
 * @version 2018-11-15
 */
public class BphActionUser extends DataEntity<BphActionUser> {

	private static final long serialVersionUID = 1L;
	private String userId; // 默认要推送的用户
	private String actionId; // 动作ID
	private String uName;// 名字

	public BphActionUser() {
		super();
	}

	public BphActionUser(String id) {
		super(id);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Length(min = 0, max = 64, message = "动作ID长度必须介于 0 和 64 之间")
	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

}