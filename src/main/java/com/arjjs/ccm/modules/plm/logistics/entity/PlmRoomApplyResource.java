/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.logistics.entity;

import com.arjjs.ccm.common.persistence.ActEntity;

/**
 * 
 * @ClassName:  PlmRoomApplyResource 
 * @Description :
 * @author：
 * @date： 2019年8月1日下午1:23:21
 */
public class PlmRoomApplyResource extends ActEntity<PlmRoomApplyResource> {
	
	private static final long serialVersionUID = 1L;
	private String meetingId;		
	private String resourceName;		
	private String resourceAddress;
	
	
	
	public String getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceAddress() {
		return resourceAddress;
	}
	public void setResourceAddress(String resourceAddress) {
		this.resourceAddress = resourceAddress;
	}		
	

	
	
}