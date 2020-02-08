/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.entity;

import org.hibernate.validator.constraints.Length;
import com.arjjs.ccm.modules.sys.entity.User;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 巡逻人员Entity
 * @author arj
 * @version 2018-03-14
 */
public class CcmPatrolUser extends DataEntity<CcmPatrolUser> {
	
	private static final long serialVersionUID = 1L;
	private String planId;		// 计划ID
	private User user;		// 用户ID
	private CcmPatrolPlan ccmPatrolPlan; // 计划
	
	public CcmPatrolUser() {
		super();
	}

	public CcmPatrolUser(String id){
		super(id);
	}

	@Length(min=0, max=64, message="计划ID长度必须介于 0 和 64 之间")
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public CcmPatrolPlan getCcmPatrolPlan() {
		return ccmPatrolPlan;
	}

	public void setCcmPatrolPlan(CcmPatrolPlan ccmPatrolPlan) {
		this.ccmPatrolPlan = ccmPatrolPlan;
	}


}