/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 应急预案Entity
 * @author pengjianqiang
 * @version 2018-03-06
 */
public class CcmEmergencyPlan extends DataEntity<CcmEmergencyPlan> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 预案名称
	private String eventScale;		// 案（事）件分级
	private String eventType;		// 案（事）件类型
	private String leader;		// 应急领导小组
	private String principle;		// 工作原则
	private String step;		// 应急处理措施
	private String description;		// 备注说明
	private String images;		// 附件信息
	
	public CcmEmergencyPlan() {
		super();
	}

	public CcmEmergencyPlan(String id){
		super(id);
	}

	@Length(min=0, max=100, message="预案名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=2, message="案（事）件分级长度必须介于 0 和 2 之间")
	public String getEventScale() {
		return eventScale;
	}

	public void setEventScale(String eventScale) {
		this.eventScale = eventScale;
	}
	
	@Length(min=0, max=2, message="案（事）件类型长度必须介于 0 和 2 之间")
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	@Length(min=0, max=1000, message="应急领导小组长度必须介于 0 和 1000 之间")
	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}
	
	@Length(min=0, max=1000, message="工作原则长度必须介于 0 和 1000 之间")
	public String getPrinciple() {
		return principle;
	}

	public void setPrinciple(String principle) {
		this.principle = principle;
	}
	
	@Length(min=0, max=1000, message="应急处理措施长度必须介于 0 和 1000 之间")
	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}
	
	@Length(min=0, max=1000, message="备注说明长度必须介于 0 和 1000 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=255, message="附件信息长度必须介于 0 和 255 之间")
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
}