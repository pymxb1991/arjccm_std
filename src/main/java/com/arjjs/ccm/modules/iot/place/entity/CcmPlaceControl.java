/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.place.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 场所布控Entity
 * @author yiqingxuan
 * @version 2019-07-25
 */
public class CcmPlaceControl extends DataEntity<CcmPlaceControl> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 场所名称
	private String controlType;		// 布控类型
	private Date controltime;		// 布控时间
	private String grade;		// 布控等级
	private String reason;		// 布控原因
	private String controlRange;		// 布控范围
	private String controlBy;		// 布控设备
	
	public CcmPlaceControl() {
		super();
	}

	public CcmPlaceControl(String id){
		super(id);
	}

	@Length(min=0, max=64, message="场所名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="布控类型长度必须介于 0 和 20 之间")
	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getControltime() {
		return controltime;
	}

	public void setControltime(Date controltime) {
		this.controltime = controltime;
	}
	
	@Length(min=0, max=20, message="布控等级长度必须介于 0 和 20 之间")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Length(min=0, max=255, message="布控原因长度必须介于 0 和 255 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Length(min=0, max=64, message="布控范围长度必须介于 0 和 64 之间")
	public String getControlRange() {
		return controlRange;
	}

	public void setControlRange(String controlRange) {
		this.controlRange = controlRange;
	}
	
	@Length(min=0, max=255, message="布控设备长度必须介于 0 和 255 之间")
	public String getControlBy() {
		return controlBy;
	}

	public void setControlBy(String controlBy) {
		this.controlBy = controlBy;
	}
	
}