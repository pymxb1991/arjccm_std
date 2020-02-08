/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.message.entity;

import com.arjjs.ccm.common.persistence.DataEntity;

import java.util.Date;

/**
 * 消息通知Entity
 * @author lhf
 * @version 2019-10-23
 */
public class CcmMessage extends DataEntity<CcmMessage> {

	private static final long serialVersionUID = 1L;
	//消息类型
	private String type;
	//需要查询的消息本体
	private String objId;
	//消息内容
	private String content;
	//接收人ID
	private String userId;
	//是否已读
	private String readFlag;
	//超期时限
	private Date deadline;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
}