/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.scheme.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.arjjs.ccm.modules.sys.entity.Office;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 绩效考评方案Entity
 * @author liang
 * @version 2018-04-11
 */
public class KpiScheme extends DataEntity<KpiScheme> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 方案名称
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private Office office;		// 所属部门
	private String userType;		// 考核人员类别
	private String state;		// 方案状态
	
	public KpiScheme() {
		super();
	}

	public KpiScheme(String id){
		super(id);
	}

	@Length(min=0, max=64, message="方案名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=2, message="考核人员类别长度必须介于 0 和 2 之间")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Length(min=0, max=1, message="方案状态长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}