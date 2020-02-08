package com.arjjs.ccm.modules.flat.alarm.entity;

import java.util.List;

import com.arjjs.ccm.modules.flat.handle.entity.BphAlarmHandleFile;

public class BphAlarmHandleFileResult {
	private String userId;
	private String userName;
	private String handleResult;
	private List<BphAlarmHandleFile> imgFileList;
	private List<BphAlarmHandleFile> videoFileList;
	private List<BphAlarmHandleFile> audioFileList;

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

	public List<BphAlarmHandleFile> getImgFileList() {
		return imgFileList;
	}

	public void setImgFileList(List<BphAlarmHandleFile> imgFileList) {
		this.imgFileList = imgFileList;
	}

	public List<BphAlarmHandleFile> getVideoFileList() {
		return videoFileList;
	}

	public void setVideoFileList(List<BphAlarmHandleFile> videoFileList) {
		this.videoFileList = videoFileList;
	}

	public List<BphAlarmHandleFile> getAudioFileList() {
		return audioFileList;
	}

	public void setAudioFileList(List<BphAlarmHandleFile> audioFileList) {
		this.audioFileList = audioFileList;
	}

}
