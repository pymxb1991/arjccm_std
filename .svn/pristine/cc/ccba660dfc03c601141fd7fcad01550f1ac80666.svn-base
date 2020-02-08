/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 巡逻点位顺序Entity
 * @author arj
 * @version 2018-03-15
 */
public class CcmPatrolPointsort extends DataEntity<CcmPatrolPointsort> {
	
	private static final long serialVersionUID = 1L;
	private String planId;		// 巡逻计划ID
	private String pointId;		// 点位ID
	private String sort;		// 顺序
	private String areaPoint;   //点位坐标
	
	public CcmPatrolPointsort() {
		super();
	}

	public CcmPatrolPointsort(String id){
		super(id);
	}

	@Length(min=0, max=64, message="巡逻计划ID长度必须介于 0 和 64 之间")
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}
	
	@Length(min=0, max=64, message="点位ID长度必须介于 0 和 64 之间")
	public String getPointId() {
		return pointId;
	}

	public void setPointId(String pointId) {
		this.pointId = pointId;
	}
	
	@Length(min=0, max=64, message="顺序长度必须介于 0 和 64 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getAreaPoint() {
		return areaPoint;
	}

	public void setAreaPoint(String areaPoint) {
		this.areaPoint = areaPoint;
	}
	
}