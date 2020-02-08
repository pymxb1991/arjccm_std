/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.entity;

import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmDevice;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

import java.util.List;

/**
 * 机构设置绑定Entity
 * @author maoxb
 * @version 2019-08-29
 */
public class CcmOrgDevice extends DataEntity<CcmOrgDevice> {
	
	private static final long serialVersionUID = 1L;
	private CcmDevice deviceId;		// 设备信息
	//private String deviceIdList;		// 设备
	private String orgId;		// 机构ID
	private String orgName;    //机构名称
	private String more1;		// more1
	private String more2;		// more2
	private String more3;		// more3
	private List<CcmDevice> deviceList;	//设备列表
	//设备列表
	private List<String> deviceSelect;	//设备列表
	
	public CcmOrgDevice() {
		super();
	}

	public CcmOrgDevice(String id){
		super(id);
	}

	public CcmDevice getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(CcmDevice deviceId) {
		this.deviceId = deviceId;
	}
	
	@Length(min=1, max=64, message="机构ID长度必须介于 1 和 64 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@Length(min=0, max=255, message="more1长度必须介于 0 和 255 之间")
	public String getMore1() {
		return more1;
	}

	public void setMore1(String more1) {
		this.more1 = more1;
	}
	
	@Length(min=0, max=255, message="more2长度必须介于 0 和 255 之间")
	public String getMore2() {
		return more2;
	}

	public void setMore2(String more2) {
		this.more2 = more2;
	}
	
	@Length(min=0, max=255, message="more3长度必须介于 0 和 255 之间")
	public String getMore3() {
		return more3;
	}

	public void setMore3(String more3) {
		this.more3 = more3;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<String> getDeviceSelect() {
		return deviceSelect;
	}

	public void setDeviceSelect(List<String> deviceSelect) {
		this.deviceSelect = deviceSelect;
	}

	public List<CcmDevice> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<CcmDevice> deviceList) {
		this.deviceList = deviceList;
	}
}