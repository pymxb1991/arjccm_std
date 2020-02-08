package com.arjjs.ccm.modules.flat.alarmhandlelog.entity;

import java.util.List;

public class BphNoticeData {
	private String type;//消息类型 1:通知类消息 2:执行动作类
	private List<String> userId;
	private BphNoticeContent content;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getUserId() {
		return userId;
	}
	public void setUserId(List<String> userId) {
		this.userId = userId;
	}
	public BphNoticeContent getContent() {
		return content;
	}
	public void setContent(BphNoticeContent content) {
		this.content = content;
	}
}
