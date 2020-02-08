/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 综治领导责任制Entity
 * @author wwh
 * @version 2018-01-23
 */
public class CcmOrgLeadduty extends DataEntity<CcmOrgLeadduty> {
	
	private static final long serialVersionUID = 1L;
	private String implementedAdd;		// 被实施地区
	private String implementedAddScale;		// 被实施地区层级
	private String implementName;		// 实施主体名称
	private String implementScale;		// 实施主体层级
	private String policyType;		// 政策种类
	
	public CcmOrgLeadduty() {
		super();
	}

	public CcmOrgLeadduty(String id){
		super(id);
	}

	@Length(min=0, max=100, message="被实施地区长度必须介于 0 和 100 之间")
	public String getImplementedAdd() {
		return implementedAdd;
	}

	public void setImplementedAdd(String implementedAdd) {
		this.implementedAdd = implementedAdd;
	}
	
	@Length(min=0, max=2, message="被实施地区层级长度必须介于 0 和 2 之间")
	public String getImplementedAddScale() {
		return implementedAddScale;
	}

	public void setImplementedAddScale(String implementedAddScale) {
		this.implementedAddScale = implementedAddScale;
	}
	
	@Length(min=0, max=100, message="实施主体名称长度必须介于 0 和 100 之间")
	public String getImplementName() {
		return implementName;
	}

	public void setImplementName(String implementName) {
		this.implementName = implementName;
	}
	
	@Length(min=0, max=2, message="实施主体层级长度必须介于 0 和 2 之间")
	public String getImplementScale() {
		return implementScale;
	}

	public void setImplementScale(String implementScale) {
		this.implementScale = implementScale;
	}
	
	@Length(min=0, max=2, message="政策种类长度必须介于 0 和 2 之间")
	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	
}