/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.entity;

import org.hibernate.validator.constraints.Length;
import com.arjjs.ccm.modules.sys.entity.Area;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 群防群治组织Entity
 * @author liang
 * @version 2018-01-13
 */
public class CcmOrgOrgprevent extends DataEntity<CcmOrgOrgprevent> {
	
	private static final long serialVersionUID = 1L;
	private String more2;		// 父级编号
	private String more3;		// 所有父级编号
	private Integer more4;		// 排序
	private Area area;		// 归属区域
	private String code;		// 区域编码
	private String comName;		// 组织名称
	private String comType;		// 组织类型
	private String comScale;		// 组织层级
	private String guidePart;		// 业务指导部门
	private Integer manNum;		// 人员数量
	private String mainFunc;		// 主要职能
	private String more1;		// 冗余字段1
	private String name;		// 名称
	private String principal;		// 负责人
	private String phone;		// 联系电话
	private String teladdress;		// 通讯地址
	private String orgnature;		// 组织性质
	
	public CcmOrgOrgprevent() {
		super();
	}

	public CcmOrgOrgprevent(String id){
		super(id);
	}

	@Length(min=0, max=64, message="父级编号长度必须介于 0 和 64 之间")
	public String getMore2() {
		return more2;
	}

	public void setMore2(String more2) {
		this.more2 = more2;
	}
	
	@Length(min=0, max=2000, message="所有父级编号长度必须介于 0 和 2000 之间")
	public String getMore3() {
		return more3;
	}

	public void setMore3(String more3) {
		this.more3 = more3;
	}
	
	public Integer getMore4() {
		return more4;
	}

	public void setMore4(Integer more4) {
		this.more4 = more4;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Length(min=0, max=100, message="区域编码长度必须介于 0 和 100 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=100, message="组织名称长度必须介于 0 和 100 之间")
	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}
	
	@Length(min=0, max=2, message="组织类型长度必须介于 0 和 2 之间")
	public String getComType() {
		return comType;
	}

	public void setComType(String comType) {
		this.comType = comType;
	}
	
	@Length(min=0, max=2, message="组织层级长度必须介于 0 和 2 之间")
	public String getComScale() {
		return comScale;
	}

	public void setComScale(String comScale) {
		this.comScale = comScale;
	}
	
	@Length(min=0, max=200, message="业务指导部门长度必须介于 0 和 200 之间")
	public String getGuidePart() {
		return guidePart;
	}

	public void setGuidePart(String guidePart) {
		this.guidePart = guidePart;
	}
	
	public Integer getManNum() {
		return manNum;
	}

	public void setManNum(Integer manNum) {
		this.manNum = manNum;
	}
	
	@Length(min=0, max=1024, message="主要职能长度必须介于 0 和 1024 之间")
	public String getMainFunc() {
		return mainFunc;
	}

	public void setMainFunc(String mainFunc) {
		this.mainFunc = mainFunc;
	}
	
	@Length(min=0, max=100, message="冗余字段1长度必须介于 0 和 100 之间")
	public String getMore1() {
		return more1;
	}

	public void setMore1(String more1) {
		this.more1 = more1;
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