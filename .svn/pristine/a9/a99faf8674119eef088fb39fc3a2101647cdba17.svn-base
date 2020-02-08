/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.entity;

import com.arjjs.ccm.modules.sys.entity.Area;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 自治组织管理Entity
 * @author liuyongjian
 * @version 2019-08-13
 */
public class CcmOrgControl extends DataEntity<CcmOrgControl> {
	
	private static final long serialVersionUID = 1L;
	private Area area;		// 所属社区
	private String name;		// 名称
	private String principal;		// 负责人
	private String phone;		// 联系电话
	private String teladdress;		// 通讯地址
	private String orgnature;		// 组织性质
	
	public CcmOrgControl() {
		super();
	}

	public CcmOrgControl(String id){
		super(id);
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="负责人长度必须介于 0 和 255 之间")
	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
	@Length(min=0, max=255, message="联系电话长度必须介于 0 和 255 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=255, message="通讯地址长度必须介于 0 和 255 之间")
	public String getTeladdress() {
		return teladdress;
	}

	public void setTeladdress(String teladdress) {
		this.teladdress = teladdress;
	}
	
	@Length(min=0, max=255, message="组织性质长度必须介于 0 和 255 之间")
	public String getOrgnature() {
		return orgnature;
	}

	public void setOrgnature(String orgnature) {
		this.orgnature = orgnature;
	}
	
}