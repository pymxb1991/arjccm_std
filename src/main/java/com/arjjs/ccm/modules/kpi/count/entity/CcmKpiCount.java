/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.count.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 绩效统计实体类Entity
 * @author lgh
 * @version 2019-07-17
 */
public class CcmKpiCount extends DataEntity<CcmKpiCount> {
	
	private static final long serialVersionUID = 1L;
	private String userName;		// 用户名
	private String tailTimes;		// 重点人员帮教次数
	private String peopleNumber;		// 新增重点人员数量
	private String tenantNumber;		// 新增的房东和租住人数量
	private String orgNumber;		// 新增重点机构数量
	private String componentsNumber;		// 新增消防设施数量
	private String reportsTimes;		// 工作日志次数
	private String reliefTimes;		// 备勤次数
	private String reliefTime;		// 备勤时长
	private String patrolTimes;		// 巡逻次数
	private String patrolTime;		// 巡逻时长
	private String policeTaskTimes;		// 警卫任务次数
	
	public CcmKpiCount() {
		super();
	}

	public CcmKpiCount(String id){
		super(id);
	}

	@Length(min=0, max=64, message="用户名长度必须介于 0 和 64 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=16, message="重点人员帮教次数长度必须介于 0 和 16 之间")
	public String getTailTimes() {
		return tailTimes;
	}

	public void setTailTimes(String tailTimes) {
		this.tailTimes = tailTimes;
	}
	
	@Length(min=0, max=16, message="新增重点人员数量长度必须介于 0 和 16 之间")
	public String getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(String peopleNumber) {
		this.peopleNumber = peopleNumber;
	}
	
	@Length(min=0, max=16, message="新增的房东和租住人数量长度必须介于 0 和 16 之间")
	public String getTenantNumber() {
		return tenantNumber;
	}

	public void setTenantNumber(String tenantNumber) {
		this.tenantNumber = tenantNumber;
	}
	
	@Length(min=0, max=16, message="新增重点机构数量长度必须介于 0 和 16 之间")
	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
	
	@Length(min=0, max=16, message="新增消防设施数量长度必须介于 0 和 16 之间")
	public String getComponentsNumber() {
		return componentsNumber;
	}

	public void setComponentsNumber(String componentsNumber) {
		this.componentsNumber = componentsNumber;
	}
	
	@Length(min=0, max=16, message="工作日志次数长度必须介于 0 和 16 之间")
	public String getReportsTimes() {
		return reportsTimes;
	}

	public void setReportsTimes(String reportsTimes) {
		this.reportsTimes = reportsTimes;
	}
	
	@Length(min=0, max=16, message="备勤次数长度必须介于 0 和 16 之间")
	public String getReliefTimes() {
		return reliefTimes;
	}

	public void setReliefTimes(String reliefTimes) {
		this.reliefTimes = reliefTimes;
	}
	
	@Length(min=0, max=16, message="备勤时长长度必须介于 0 和 16 之间")
	public String getReliefTime() {
		return reliefTime;
	}

	public void setReliefTime(String reliefTime) {
		this.reliefTime = reliefTime;
	}
	
	@Length(min=0, max=16, message="巡逻次数长度必须介于 0 和 16 之间")
	public String getPatrolTimes() {
		return patrolTimes;
	}

	public void setPatrolTimes(String patrolTimes) {
		this.patrolTimes = patrolTimes;
	}
	
	@Length(min=0, max=16, message="巡逻时长长度必须介于 0 和 16 之间")
	public String getPatrolTime() {
		return patrolTime;
	}

	public void setPatrolTime(String patrolTime) {
		this.patrolTime = patrolTime;
	}
	
	@Length(min=0, max=16, message="警卫任务次数长度必须介于 0 和 16 之间")
	public String getPoliceTaskTimes() {
		return policeTaskTimes;
	}

	public void setPoliceTaskTimes(String policeTaskTimes) {
		this.policeTaskTimes = policeTaskTimes;
	}
	
}