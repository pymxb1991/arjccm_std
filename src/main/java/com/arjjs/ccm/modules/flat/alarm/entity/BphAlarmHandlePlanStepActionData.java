package com.arjjs.ccm.modules.flat.alarm.entity;

import java.util.List;

import com.arjjs.ccm.modules.flat.handle.entity.BphAlarmHandle;

public class BphAlarmHandlePlanStepActionData {

	private BphAlarmInfo alarmInfo;
	private List<BphAlarmHandle> alarmHandleList;
	private List<BphAlarmHandleFileResult> alarmHandleFileResultList;

	public List<BphAlarmHandleFileResult> getAlarmHandleFileResultList() {
		return alarmHandleFileResultList;
	}

	public void setAlarmHandleFileResultList(List<BphAlarmHandleFileResult> alarmHandleFileResultList) {
		this.alarmHandleFileResultList = alarmHandleFileResultList;
	}

	public BphAlarmInfo getAlarmInfo() {
		return alarmInfo;
	}

	public void setAlarmInfo(BphAlarmInfo alarmInfo) {
		this.alarmInfo = alarmInfo;
	}

	public List<BphAlarmHandle> getAlarmHandleList() {
		return alarmHandleList;
	}

	public void setAlarmHandleList(List<BphAlarmHandle> alarmHandleList) {
		this.alarmHandleList = alarmHandleList;
	}

}
