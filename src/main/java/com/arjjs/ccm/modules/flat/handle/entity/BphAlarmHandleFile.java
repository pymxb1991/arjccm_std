package com.arjjs.ccm.modules.flat.handle.entity;

import com.arjjs.ccm.common.persistence.DataEntity;

public class BphAlarmHandleFile extends DataEntity<BphAlarmHandleFile> {

	private static final long serialVersionUID = 1L;
	private String alarmHandleId;
	private String type;
	private String path;
	private String fileName;
	private String alarmId;
	private String userId;
	private String userName;
	private String handleResult;

	public String getHandleResult() {
		return handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	public String getAlarmHandleId() {
		return alarmHandleId;
	}

	public void setAlarmHandleId(String alarmHandleId) {
		this.alarmHandleId = alarmHandleId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
