package com.arjjs.ccm.modules.ccm.rest.entity;

import java.io.Serializable;
import java.util.Date;

public class AlarmHandleFile  implements Serializable {// NOTE:警情处理-附件表（处警附件表） :bph_alarm_handle_file

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String alarmHandleId;
	private String type;
	private String path;
	private String urlPath;
	private String extension;
	private String contentType;
	private String fileName;
	private String createBy;
	private Date   createDate;
	private String remarks;
	private String delFlag;
	private String duration;
	private String thumbnail;
	private String md5Value;
	public String getId() {
		return id == null ? "" : id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAlarmHandleId() {
		return alarmHandleId == null ? "" : alarmHandleId;
	}
	public void setAlarmHandleId(String alarmHandleId) {
		this.alarmHandleId = alarmHandleId;
	}
	public String getType() {
		return type == null ? "" : type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path == null ? "" : path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileName() {
		return fileName == null ? "" : fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCreateBy() {
		return createBy == null ? "" : createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate == null ? new Date() : createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRemarks() {
		return remarks == null ? "" : remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDelFlag() {
		return delFlag == null ? "" : delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getMd5Value() {
		return md5Value;
	}

	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value == null ? null : md5Value.trim();
	}
}
