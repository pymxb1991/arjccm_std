/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 活动定义Entity
 * @author lc
 * @version 2018-05-14
 */
public class PbsActivitytype extends DataEntity<PbsActivitytype> {
	
	private static final long serialVersionUID = 1L;
	private String sName;		// 活动名称
	private String sFlowdefinitionid;		// 工作流定义表
	private String sGroup;		// 自定义分组标识
	private String sDescription;		// 任务类型定义描述信息
	private String sSpare01;		// 备用字段
	private String sSpare02;		// 备用字段
	private String sSpare03;		// 备用字段
	private String sSpare04;		// 备用字段
	
	public PbsActivitytype() {
		super();
	}

	public PbsActivitytype(String id){
		super(id);
	}

	@Length(min=0, max=200, message="活动名称长度必须介于 0 和 200 之间")
	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}
	
	@Length(min=0, max=64, message="工作流定义表长度必须介于 0 和 64 之间")
	public String getSFlowdefinitionid() {
		return sFlowdefinitionid;
	}

	public void setSFlowdefinitionid(String sFlowdefinitionid) {
		this.sFlowdefinitionid = sFlowdefinitionid;
	}
	
	@Length(min=0, max=200, message="自定义分组标识长度必须介于 0 和 200 之间")
	public String getSGroup() {
		return sGroup;
	}

	public void setSGroup(String sGroup) {
		this.sGroup = sGroup;
	}
	
	@Length(min=0, max=1000, message="任务类型定义描述信息长度必须介于 0 和 1000 之间")
	public String getSDescription() {
		return sDescription;
	}

	public void setSDescription(String sDescription) {
		this.sDescription = sDescription;
	}
	
	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}
	
	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}
	
	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}
	
	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSSpare04() {
		return sSpare04;
	}

	public void setSSpare04(String sSpare04) {
		this.sSpare04 = sSpare04;
	}
	
	
}