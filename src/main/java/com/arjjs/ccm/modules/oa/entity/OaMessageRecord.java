/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.oa.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.User;

import java.util.Date;

/**
 * 通知通告记录Entity
 * @author admin001
 * @version 2014-05-16
 */
public class OaMessageRecord extends DataEntity<OaMessageRecord> {
	
	private static final long serialVersionUID = 1L;
	private OaMessage oaMessage;		// 通知通告ID
	private User user;		// 接受人
	private String readFlag;		// 阅读标记（0：未读；1：已读）
	private Date readDate;		// 阅读时间
	
	
	public OaMessageRecord() {
		super();
	}

	public OaMessageRecord(String id){
		super(id);
	}
	
	public OaMessageRecord(OaMessage oaMessage){
		this.oaMessage = oaMessage;
	}

	public OaMessage getOaMessage() {
		return oaMessage;
	}

	public void setOaMessage(OaMessage oaMessage) {
		this.oaMessage = oaMessage;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=1, message="阅读标记（0：未读；1：已读）长度必须介于 0 和 1 之间")
	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}
	
	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
	
}