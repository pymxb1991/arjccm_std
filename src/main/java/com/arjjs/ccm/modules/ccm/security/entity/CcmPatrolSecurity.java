/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.security.entity;

import com.arjjs.ccm.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.arjjs.ccm.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 警卫管理Entity
 * @author lijiupeng
 * @version 2019-07-11
 */
public class CcmPatrolSecurity extends DataEntity<CcmPatrolSecurity> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 巡逻民警
	private Date securityTime;		// 警卫时间
	private String office;		// 参与单位
	private String numberUnits;		// 单位人数
	private String guardLine;		// 警卫线路
	private Date collectionTime;		// 集合时间
	private String collectionPlace;		// 集合地点
	private String status;		// 状态
	private String officeName;
	private String title;
	private Date endTime;
	private String auditingStatus;

	public String getAuditingStatus() {
		return auditingStatus;
	}

	public void setAuditingStatus(String auditingStatus) {
		this.auditingStatus = auditingStatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public CcmPatrolSecurity() {
		super();
	}

	public CcmPatrolSecurity(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSecurityTime() {
		return securityTime;
	}

	public void setSecurityTime(Date securityTime) {
		this.securityTime = securityTime;
	}
	
	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}
	
	@Length(min=0, max=255, message="单位人数长度必须介于 0 和 255 之间")
	public String getNumberUnits() {
		return numberUnits;
	}

	public void setNumberUnits(String numberUnits) {
		this.numberUnits = numberUnits;
	}
	
	@Length(min=0, max=255, message="警卫线路长度必须介于 0 和 255 之间")
	public String getGuardLine() {
		return guardLine;
	}

	public void setGuardLine(String guardLine) {
		this.guardLine = guardLine;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(Date collectionTime) {
		this.collectionTime = collectionTime;
	}
	
	@Length(min=0, max=255, message="集合地点长度必须介于 0 和 255 之间")
	public String getCollectionPlace() {
		return collectionPlace;
	}

	public void setCollectionPlace(String collectionPlace) {
		this.collectionPlace = collectionPlace;
	}
	
	@Length(min=0, max=64, message="状态长度必须介于 0 和 64 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}