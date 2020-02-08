/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.plm.email.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 工作日志记录Entity
 * @author arj
 * @version 2018-03-08
 */
public class PlmWorkEmailRead extends DataEntity<PlmWorkEmailRead> {
	
	private static final long serialVersionUID = 1L;
	private PlmWorkEmail plmWorkEmail;
	
	@JsonIgnore
	public PlmWorkEmail getPlmWorkEmail() {
		return plmWorkEmail;
	}

	public void setPlmWorkEmail(PlmWorkEmail plmWorkEmail) {
		this.plmWorkEmail = plmWorkEmail;
	}

	private String reportId;		// 邮件id
	private String type;		//接受人类型
	private String status;		//邮件状态
	private String isStar;		//是否星标邮件   1是0否
	private User user;		// 收件人
	private String readFlag;		// 阅读标记
	private Date readTime;		// 阅读时间
	
	public PlmWorkEmailRead() {
		super();
	}

	public PlmWorkEmailRead(String id){
		super(id);
	}

	public PlmWorkEmailRead(PlmWorkEmail plmWorkEmail){
		this.plmWorkEmail =plmWorkEmail;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsStar() {
		return isStar;
	}

	public void setIsStar(String isStar) {
		this.isStar = isStar;
	}

	
}