package com.arjjs.ccm.modules.ccm.ccmsys.entity;

import java.util.List;

import com.google.common.collect.Lists;

public class CcmDeviceArea {
	private String id;		// 区域id
	private String areaMap;		// 区域坐标
	private List<CcmDeviceArea> childrenList = Lists.newArrayList();// 子节点
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAreaMap() {
		return areaMap;
	}
	public void setAreaMap(String areaMap) {
		this.areaMap = areaMap;
	}
	public List<CcmDeviceArea> getChildrenList() {
		return childrenList;
	}
	public void setChildrenList(List<CcmDeviceArea> childrenList) {
		this.childrenList = childrenList;
	}
}
