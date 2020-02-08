/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.Area;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 巡逻任务Entity
 * @author lijiupeng
 * @version 2019-07-05
 */
public class CcmPatrolMissions extends DataEntity<CcmPatrolMissions> {
	
	private static final long serialVersionUID = 1L;
	private String patrolContent;		// 巡逻任务
	private Date patrolTime;		// 巡逻时间
	private String office;		// 参与单位
	private String number;		// 每个单位人数
	private String patrolRoutes;		// 巡逻路线
	private Area area;		// 巡逻辖区
	private String status;		// 状态
	private String officeName;
	private Date endTime;
	private String auditingStatus;

	public String getAuditingStatus() {
		return auditingStatus;
	}

	public void setAuditingStatus(String auditingStatus) {
		this.auditingStatus = auditingStatus;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public CcmPatrolMissions() {
		super();
	}

	public CcmPatrolMissions(String id){
		super(id);
	}

	@Length(min=0, max=255, message="巡逻任务长度必须介于 0 和 255 之间")
	public String getPatrolContent() {
		return patrolContent;
	}

	public void setPatrolContent(String patrolContent) {
		this.patrolContent = patrolContent;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPatrolTime() {
		return patrolTime;
	}

	public void setPatrolTime(Date patrolTime) {
		this.patrolTime = patrolTime;
	}
	
	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}
	
	@Length(min=0, max=1024, message="每个单位人数长度必须介于 0 和 1024 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=255, message="巡逻路线长度必须介于 0 和 255 之间")
	public String getPatrolRoutes() {
		return patrolRoutes;
	}

	public void setPatrolRoutes(String patrolRoutes) {
		this.patrolRoutes = patrolRoutes;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Length(min=0, max=255, message="状态长度必须介于 0 和 255 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	@Override
	public String toString() {
		return "CcmPatrolMissions{" +
				"patrolContent='" + patrolContent + '\'' +
				", patrolTime=" + patrolTime +
				", office='" + office + '\'' +
				", number='" + number + '\'' +
				", patrolRoutes='" + patrolRoutes + '\'' +
				", area=" + area +
				", status='" + status + '\'' +
				", officeName='" + officeName + '\'' +
				'}';
	}
}