/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.stepaction.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 过程动作关联表Entity
 * @author liu
 * @version 2018-11-17
 */
public class BphStepAction extends DataEntity<BphStepAction> {
	
	private static final long serialVersionUID = 1L;
	private String actionId;		// 动作ID
	private String stepId;		// 步骤ID
	private int sort;//排序
	
	
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public BphStepAction() {
		super();
	}

	public BphStepAction(String id){
		super(id);
	}

	@Length(min=0, max=64, message="动作ID长度必须介于 0 和 64 之间")
	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	
	@Length(min=0, max=64, message="步骤ID长度必须介于 0 和 64 之间")
	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	
}