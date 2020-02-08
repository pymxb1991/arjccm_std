/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyactivity.entity;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.ccm.partybuild.entity.CcmPartyOrganiz;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 党员活动管理Entity
 * @author maoxb
 * @version 2019-08-13
 */
public class CcmPartyActivity extends DataEntity<CcmPartyActivity> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 1:社区党员管理2：两新党员管理
	private Area community;		// 社区（单位）
	private Date activitTime;		// 活动时间
	private CcmPartyOrganiz organization;		// 选择组织
	private String shouldJoinParty;		// 应参加党员
	private String realityJoinParyt;		// 实际参加党员
	private String leavePersonId;		// 请假人员名称
	private String activitTitle;		// 活动主题
	private String activitContent;		// 活动内容
	private String officeId;
	
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public CcmPartyActivity() {
		super();
	}

	public CcmPartyActivity(String id){
		super(id);
	}

	@Length(min=0, max=1, message="1:社区党员管理2：两新党员管理长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Area getCommunity() {
		return community;
	}

	public void setCommunity(Area community) {
		this.community = community;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActivitTime() {
		return activitTime;
	}

	public void setActivitTime(Date activitTime) {
		this.activitTime = activitTime;
	}
	
	public CcmPartyOrganiz getOrganization() {
		return organization;
	}

	public void setOrganization(CcmPartyOrganiz organization) {
		this.organization = organization;
	}
	
	@Length(min=0, max=10, message="应参加党员长度必须介于 0 和 10 之间")
	public String getShouldJoinParty() {
		return shouldJoinParty;
	}

	public void setShouldJoinParty(String shouldJoinParty) {
		this.shouldJoinParty = shouldJoinParty;
	}
	
	@Length(min=0, max=10, message="实际参加党员长度必须介于 0 和 10 之间")
	public String getRealityJoinParyt() {
		return realityJoinParyt;
	}

	public void setRealityJoinParyt(String realityJoinParyt) {
		this.realityJoinParyt = realityJoinParyt;
	}
	
	@Length(min=0, max=500, message="请假人员名称长度必须介于 0 和 500 之间")
	public String getLeavePersonId() {
		return leavePersonId;
	}

	public void setLeavePersonId(String leavePersonId) {
		this.leavePersonId = leavePersonId;
	}
	
	@Length(min=0, max=200, message="活动主题长度必须介于 0 和 200 之间")
	public String getActivitTitle() {
		return activitTitle;
	}

	public void setActivitTitle(String activitTitle) {
		this.activitTitle = activitTitle;
	}
	
	public String getActivitContent() {
		return activitContent;
	}

	public void setActivitContent(String activitContent) {
		this.activitContent = activitContent;
	}
	
}