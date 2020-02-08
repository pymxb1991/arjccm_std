/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.dma.eventheme.entity;

/**
 * 统计单位事件 返回实体
 * @author arj
 * @version 2019-09-06
 */
public class CountOfficeEventEntity {

	private String id; //处警ID

	private String handlePoliceId;		// 处警员id

	private String handleStatus; //处警状态

	private String officeGrandParentName; // 父节点名称
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHandlePoliceId() {
		return handlePoliceId;
	}

	public void setHandlePoliceId(String handlePoliceId) {
		this.handlePoliceId = handlePoliceId;
	}

	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}

	public String getOfficeGrandParentName() {
		return officeGrandParentName;
	}

	public void setOfficeGrandParentName(String officeGrandParentName) {
		this.officeGrandParentName = officeGrandParentName;
	}
}