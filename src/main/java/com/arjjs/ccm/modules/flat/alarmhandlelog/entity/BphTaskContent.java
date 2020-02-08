package com.arjjs.ccm.modules.flat.alarmhandlelog.entity;

import java.util.List;

public class BphTaskContent {
	private String retCode;
	private String retMessage;
	private List<BphAlarmHandleInfo> resultList;
	
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMessage() {
		return retMessage;
	}
	public void setRetMessage(String retMessage) {
		this.retMessage = retMessage;
	}
	public List<BphAlarmHandleInfo> getResultList() {
		return resultList;
	}
	public void setResultList(List<BphAlarmHandleInfo> resultList) {
		this.resultList = resultList;
	}
}
