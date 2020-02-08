package com.arjjs.ccm.modules.flat.export.entity;

import java.util.List;

import com.arjjs.ccm.modules.flat.alarm.entity.BphAlarmInfo;
import com.arjjs.ccm.modules.flat.alarmhandlelog.entity.BphAlarmHandleLog;

public class WordDataList {

	private BphAlarmInfo alarmInfo;//警情表
	private List<BphAlarmHandleLog> alarmHandleLogList;//处警过程记录表
	private List<WordAlarmHandleFilePlanStepActionData> wordAlarmHandleFilePlanStepActionDataList;
	
	public BphAlarmInfo getAlarmInfo() {
		return alarmInfo;
	}
	public void setAlarmInfo(BphAlarmInfo alarmInfo) {
		this.alarmInfo = alarmInfo;
	}
	public List<BphAlarmHandleLog> getAlarmHandleLogList() {
		return alarmHandleLogList;
	}
	public void setAlarmHandleLogList(List<BphAlarmHandleLog> alarmHandleLogList) {
		this.alarmHandleLogList = alarmHandleLogList;
	}
	public List<WordAlarmHandleFilePlanStepActionData> getWordAlarmHandleFilePlanStepActionDataList() {
		return wordAlarmHandleFilePlanStepActionDataList;
	}
	public void setWordAlarmHandleFilePlanStepActionDataList(
			List<WordAlarmHandleFilePlanStepActionData> wordAlarmHandleFilePlanStepActionDataList) {
		this.wordAlarmHandleFilePlanStepActionDataList = wordAlarmHandleFilePlanStepActionDataList;
	}

}
