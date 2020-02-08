/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.stepinfo.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.flat.action.entity.BphActionInfo;
import com.arjjs.ccm.modules.flat.planinfo.entity.BphPlanInfo;

/**
 * 预案过程Entity
 * @author zhanghao
 * @version 2018-11-14
 */
public class BphStepInfo extends DataEntity<BphStepInfo> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 步骤名称
	private String content;		// 步骤描述
	private BphPlanInfo bphPlanInfo;		// 预案
	private BphActionInfo bphActionInfo;		// 动作
	private String bpsId;
	private String bpsPlanId;
	private String bpsStepId;
	private String bsiName;
	private String bsiContent;
	private String planId;
	
	
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getBpsId() {
		return bpsId;
	}

	public void setBpsId(String bpsId) {
		this.bpsId = bpsId;
	}

	public String getBpsPlanId() {
		return bpsPlanId;
	}

	public void setBpsPlanId(String bpsPlanId) {
		this.bpsPlanId = bpsPlanId;
	}

	public String getBpsStepId() {
		return bpsStepId;
	}

	public void setBpsStepId(String bpsStepId) {
		this.bpsStepId = bpsStepId;
	}

	public String getBsiName() {
		return bsiName;
	}

	public void setBsiName(String bsiName) {
		this.bsiName = bsiName;
	}

	public String getBsiContent() {
		return bsiContent;
	}

	public void setBsiContent(String bsiContent) {
		this.bsiContent = bsiContent;
	}

	public BphStepInfo() {
		super();
	}

	public BphStepInfo(String id){
		super(id);
	}

	@Length(min=0, max=80, message="步骤名称长度必须介于 0 和 80 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="步骤描述长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BphPlanInfo getBphPlanInfo() {
		return bphPlanInfo;
	}

	public void setBphPlanInfo(BphPlanInfo bphPlanInfo) {
		this.bphPlanInfo = bphPlanInfo;
	}

	public BphActionInfo getBphActionInfo() {
		return bphActionInfo;
	}

	public void setBphActionInfo(BphActionInfo bphActionInfo) {
		this.bphActionInfo = bphActionInfo;
	}
	
	
}