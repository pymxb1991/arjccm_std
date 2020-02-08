/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyreport.entity;

import com.arjjs.ccm.modules.ccm.partybuild.entity.CcmPartyOrganiz;
import com.arjjs.ccm.modules.ccm.partyperson.entity.CcmPartyPerson;
import com.arjjs.ccm.modules.sys.entity.Area;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 双报道情况管理Entity
 * @author maoxb
 * @version 2019-08-14
 */
public class CcmPartyReport extends DataEntity<CcmPartyReport> {
	
	private static final long serialVersionUID = 1L;
	private Area community;		// 社区（单位）
	private String orgParty;		// 党组织ID-党员ID
	private CcmPartyOrganiz orgPartyEntity ; //党组织实体
	private CcmPartyPerson perPartyEntity ; //党员实体
	private Date reportingTime;		// 报道时间
	private String type;		// 1：党组织  2、党员
	private String officeId;
	
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public CcmPartyReport() {
		super();
	}

	public CcmPartyReport(String id){
		super(id);
	}

	public Area getCommunity() {
		return community;
	}

	public void setCommunity(Area community) {
		this.community = community;
	}
	
	@Length(min=0, max=64, message="党组织ID-党员ID长度必须介于 0 和 64 之间")
	public String getOrgParty() {
		return orgParty;
	}

	public void setOrgParty(String orgParty) {
		this.orgParty = orgParty;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReportingTime() {
		return reportingTime;
	}

	public void setReportingTime(Date reportingTime) {
		this.reportingTime = reportingTime;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CcmPartyOrganiz getOrgPartyEntity() {
		return orgPartyEntity;
	}

	public void setOrgPartyEntity(CcmPartyOrganiz orgPartyEntity) {
		this.orgPartyEntity = orgPartyEntity;
	}

	public CcmPartyPerson getPerPartyEntity() {
		return perPartyEntity;
	}

	public void setPerPartyEntity(CcmPartyPerson perPartyEntity) {
		this.perPartyEntity = perPartyEntity;
	}
}