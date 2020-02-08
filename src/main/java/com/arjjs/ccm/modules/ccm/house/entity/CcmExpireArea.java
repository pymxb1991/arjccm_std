package com.arjjs.ccm.modules.ccm.house.entity;

import java.util.List;

import com.google.common.collect.Lists;

public class CcmExpireArea {
	private String areaId;
	private String areaType;
	private String areaName;
	private List<CcmExpireUser> userList = Lists.newArrayList();
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public List<CcmExpireUser> getUserList() {
		return userList;
	}
	public void setUserList(List<CcmExpireUser> userList) {
		this.userList = userList;
	}
}