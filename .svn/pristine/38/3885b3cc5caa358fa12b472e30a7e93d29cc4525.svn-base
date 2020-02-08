/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 巡逻结果Entity
 * @author arj
 * @version 2018-03-16
 */
public class CcmPatrolResult extends DataEntity<CcmPatrolResult> {
	
	private static final long serialVersionUID = 1L;
	private CcmPatrolPlan plan;		// 计划ID
	private Date beginDate;		// 开始时间
	private Date endDate;		// 结束时间
	private String status;		// 本次巡逻结果
	private String name;		// 结果名称
	private List<CcmPatrolRespoint> ccmPatrolRespointList; // 巡检点结果 列表
	
	
	public CcmPatrolResult() {
		super();
	}

	public CcmPatrolResult(String id){
		super(id);
	}

	@JsonBackReference
	public CcmPatrolPlan getPlan() {
		return plan;
	}

	public void setPlan(CcmPatrolPlan plan) {
		this.plan = plan;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Length(min=0, max=64, message="本次巡逻结果长度必须介于 0 和 64 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Length(min=0, max=64, message="结果名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CcmPatrolRespoint> getCcmPatrolRespointList() {
		return ccmPatrolRespointList;
	}

	public void setCcmPatrolRespointList(List<CcmPatrolRespoint> ccmPatrolRespointList) {
		this.ccmPatrolRespointList = ccmPatrolRespointList;
	}
	
	
}