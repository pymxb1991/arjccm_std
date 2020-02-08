/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 巡逻点位Entity
 * @author arj
 * @version 2018-03-15
 */
public class CcmPatrolPoint extends DataEntity<CcmPatrolPoint> {
	
	private static final long serialVersionUID = 1L;
	private String areaPoint;		// 坐标
	private String name;		// 名称
	private String property;		// 顺序
	private CcmPatrolPlan ccmPatrolPlan; // 计划
	
	public CcmPatrolPoint() {
		super();
	}

	public CcmPatrolPoint(String id){
		super(id);
	}

	@Length(min=0, max=64, message="坐标长度必须介于 0 和 64 之间")
	public String getAreaPoint() {
		return areaPoint;
	}

	public void setAreaPoint(String areaPoint) {
		this.areaPoint = areaPoint;
	}
	
	@Length(min=0, max=64, message="名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="顺序长度必须介于 0 和 64 之间")
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public CcmPatrolPlan getCcmPatrolPlan() {
		return ccmPatrolPlan;
	}

	public void setCcmPatrolPlan(CcmPatrolPlan ccmPatrolPlan) {
		this.ccmPatrolPlan = ccmPatrolPlan;
	}
	
	
}