package com.arjjs.ccm.modules.ccm.rest.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/***
 * 警情通知类返回实体
 */
public class AlarmNotify  implements Serializable {// NOTE:警情表 :bph_alarm_info

	private static final long serialVersionUID = 1L;

	private String id;
	private String alarmId;		// 警情Id
	private String type;		// 类型
	private String title;		// 标题
	private String content;		// 通知内容
	private String status;		// 状态
	private String createBy;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;
	private String updateBy;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date   updateDate;
	private String remarks;
	private String delFlag;
	private String alarmContent;   //警情内容
	private String alarmCreateDate; //警情创建日期
	private String isImportant;     //是否重大 1 重大 ；0 普通

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getAlarmContent() {
		return alarmContent;
	}

	public void setAlarmContent(String alarmContent) {
		this.alarmContent = alarmContent;
	}

	public String getAlarmCreateDate() {
		return alarmCreateDate;
	}

	public void setAlarmCreateDate(String alarmCreateDate) {
		this.alarmCreateDate = alarmCreateDate;
	}

	public String getIsImportant() {
		return isImportant;
	}

	public void setIsImportant(String isImportant) {
		this.isImportant = isImportant;
	}
}
