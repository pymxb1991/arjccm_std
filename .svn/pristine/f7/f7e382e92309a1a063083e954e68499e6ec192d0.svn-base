/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.group.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 自治群管理Entity
 * @author liuyongjian
 * @version 2019-08-06
 */
public class CcmGroupControl extends DataEntity<CcmGroupControl> {
	
	private static final long serialVersionUID = 1L;
	private String community;		// 社区
	private String name;		// 名称
	private String master;		// 群主
	private String number;		// 成员数
	private String isapprove;		// 需要审批
	private String property;		// 群性质
	private String areaName;		// 社区名称
	private String officeId;        // 部门id
	
	
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public CcmGroupControl() {
		super();
	}

	public CcmGroupControl(String id){
		super(id);
	}

	@Length(min=0, max=255, message="社区长度必须介于 0 和 255 之间")
	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}
	
	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="群主长度必须介于 0 和 255 之间")
	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}
	
	@Length(min=0, max=255, message="成员数长度必须介于 0 和 255 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@Length(min=0, max=255, message="需要审批长度必须介于 0 和 255 之间")
	public String getIsapprove() {
		return isapprove;
	}

	public void setIsapprove(String isapprove) {
		this.isapprove = isapprove;
	}
	
	@Length(min=0, max=255, message="群性质长度必须介于 0 和 255 之间")
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
}