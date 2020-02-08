package com.arjjs.ccm.modules.flat.flow.entity;

import com.arjjs.ccm.common.persistence.DataEntity;

public class PlanFlowManage extends DataEntity<PlanFlowManage>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;//树形name
	private String typeCode;//警情类型
	private String pId;//父id
	private String typeClass;//树形标志量
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getTypeClass() {
		return typeClass;
	}
	public void setTypeClass(String typeClass) {
		this.typeClass = typeClass;
	}
	
}
