/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 工作职责Entity
 * @author liang
 * @version 2018-08-02
 */
public class CcmServiceDuty extends DataEntity<CcmServiceDuty> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 岗位名称
	private String code;		// 岗位编号
	private String department;		// 所在部门
	private String chief;		// 主管领导
	private String summary;		// 岗位概要
	private String duty;		// 岗位职责
	private String skill;		// 知识技能要求
	private String quality;		// 个人素质要求
	
	public CcmServiceDuty() {
		super();
	}

	public CcmServiceDuty(String id){
		super(id);
	}

	@Length(min=0, max=64, message="岗位名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="岗位编号长度必须介于 0 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=64, message="所在部门长度必须介于 0 和 64 之间")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Length(min=0, max=64, message="主管领导长度必须介于 0 和 64 之间")
	public String getChief() {
		return chief;
	}

	public void setChief(String chief) {
		this.chief = chief;
	}
	
	@Length(min=0, max=256, message="岗位概要长度必须介于 0 和 256 之间")
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@Length(min=0, max=512, message="岗位职责长度必须介于 0 和 512 之间")
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}
	
	@Length(min=0, max=256, message="知识技能要求长度必须介于 0 和 256 之间")
	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	@Length(min=0, max=256, message="个人素质要求长度必须介于 0 和 256 之间")
	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}
	
}