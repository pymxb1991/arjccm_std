package com.arjjs.ccm.modules.ccm.house.entity;

public class CcmHouseSetting {
	private String id;
	private String remarks;
	private String type;
	private String sendInfo;
	private String timeInterval;
	private String paramStr;
	public String getParamStr() {
		return paramStr;
	}
	public void setParamStr(String paramStr) {
		this.paramStr = paramStr;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSendInfo() {
		return sendInfo;
	}
	public void setSendInfo(String sendInfo) {
		this.sendInfo = sendInfo;
	}
	public String getTimeInterval() {
		return timeInterval;
	}
	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}
}
