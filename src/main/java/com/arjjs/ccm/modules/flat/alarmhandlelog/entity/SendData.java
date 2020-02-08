package com.arjjs.ccm.modules.flat.alarmhandlelog.entity;

import java.util.List;

import com.arjjs.ccm.modules.flat.alarm.entity.BphAlarmInfo;

public class SendData {

	private List<UserList> userList;
	private BphAlarmInfo bphAlarmInfo;

	public List<UserList> getUserList() {
		return userList;
	}

	public void setUserList(List<UserList> userList) {
		this.userList = userList;
	}

	public BphAlarmInfo getBphAlarmInfo() {
		return bphAlarmInfo;
	}

	public void setBphAlarmInfo(BphAlarmInfo bphAlarmInfo) {
		this.bphAlarmInfo = bphAlarmInfo;
	}


}
