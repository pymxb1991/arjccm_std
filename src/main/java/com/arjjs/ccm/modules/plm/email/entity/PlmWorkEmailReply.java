/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.email.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 内部邮件回复Entity
 * @author fu
 * @version 2018-08-02
 */
public class PlmWorkEmailReply extends DataEntity<PlmWorkEmailReply> {
	
	private static final long serialVersionUID = 1L;
	private String workEmailId;		// 内部邮件Id
	
	public PlmWorkEmailReply() {
		super();
	}

	public PlmWorkEmailReply(String id){
		super(id);
	}

	@Length(min=0, max=64, message="内部邮件Id长度必须介于 0 和 64 之间")
	public String getWorkEmailId() {
		return workEmailId;
	}

	public void setWorkEmailId(String workEmailId) {
		this.workEmailId = workEmailId;
	}
	
}