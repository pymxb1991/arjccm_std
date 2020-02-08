/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 请求处理Entity
 * @author arj
 * @version 2018-01-18
 */
public class CcmEventRequestdeal extends DataEntity<CcmEventRequestdeal> {
	
	private static final long serialVersionUID = 1L;
	private String eventRequestId;		// 请求名称
	private String dealUnit;		// 处理单位
	private String eventPrincipal;		// 请求负责人
	private String telPerson;		// 个人电话
	private String telCom;		// 单位电话
	private String createName; // 创建者
	private String caseName; // 案事件名称
	
	
	public CcmEventRequestdeal() {
		super();
	}

	public CcmEventRequestdeal(String id){
		super(id);
	}

	@Length(min=1, max=64, message="请求处理应该在请求登记提交之后添加。")
	public String getEventRequestId() {
		return eventRequestId;
	}

	public void setEventRequestId(String eventRequestId) {
		this.eventRequestId = eventRequestId;
	}
	
	@Length(min=0, max=200, message="处理单位长度必须介于 0 和 200 之间")
	public String getDealUnit() {
		return dealUnit;
	}

	public void setDealUnit(String dealUnit) {
		this.dealUnit = dealUnit;
	}
	
	@Length(min=0, max=20, message="请求负责人长度必须介于 0 和 20 之间")
	public String getEventPrincipal() {
		return eventPrincipal;
	}

	public void setEventPrincipal(String eventPrincipal) {
		this.eventPrincipal = eventPrincipal;
	}
	
	@Length(min=0, max=30, message="个人电话长度必须介于 0 和 30 之间")
	public String getTelPerson() {
		return telPerson;
	}

	public void setTelPerson(String telPerson) {
		this.telPerson = telPerson;
	}
	
	@Length(min=0, max=30, message="单位电话长度必须介于 0 和 30 之间")
	public String getTelCom() {
		return telCom;
	}

	public void setTelCom(String telCom) {
		this.telCom = telCom;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	
	
}