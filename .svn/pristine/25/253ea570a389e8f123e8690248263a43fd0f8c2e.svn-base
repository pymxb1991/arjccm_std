/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.sys.entity;

import org.hibernate.validator.constraints.Length;
import com.arjjs.ccm.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 工作日志记录Entity
 * @author arj
 * @version 2018-03-08
 */
public class CcmWorkReportRead extends DataEntity<CcmWorkReportRead> {
	
	private static final long serialVersionUID = 1L;
	private CcmWorkReport ccmWorkReport;
	@JsonIgnore
	public CcmWorkReport getCcmWorkReport() {
		return ccmWorkReport;
	}

	public void setCcmWorkReport(CcmWorkReport ccmWorkReport) {
		this.ccmWorkReport = ccmWorkReport;
	}

	private String reportId;		// 工作日志id
	private User user;		// 收件人
	private String readFlag;		// 阅读标记
	private Date readTime;		// 阅读时间
	private String userId;
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public CcmWorkReportRead() {
		super();
	}

	public CcmWorkReportRead(String id){
		super(id);
	}

	public CcmWorkReportRead(CcmWorkReport ccmWorkReport){
		this.ccmWorkReport =ccmWorkReport;
	}
	@Length(min=0, max=64, message="工作日志id长度必须介于 0 和 64 之间")
	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=2, message="阅读标记长度必须介于 0 和 2 之间")
	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	
}