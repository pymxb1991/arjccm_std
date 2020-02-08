/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.view.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.TreeEntity;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 综治机构Entity
 * @author liang
 * @version 2018-01-12
 */
public class VCcmOrg extends TreeEntity<VCcmOrg> {
	
	private static final long serialVersionUID = 1L;
	//private VCcmOrg parent;		// 父级编号
	//private String parentIds;		// 所有父级编号
	//private String name;		// 名称
	//private String sort;		// 排序
	private Area area;		// 归属区域
	private String code;		// 区域编码
	private String type;		// 机构类型
	private String grade;		// 机构等级
	private String address;		// 联系地址
	private String zipCode;		// 邮政编码
	private String master;		// 负责人
	private String phone;		// 电话
	private String fax;		// 传真
	private String email;		// 邮箱
	private String useable;		// 是否启用
	private Office primaryPerson;		// 主负责人
	private Office deputyPerson;		// 副负责人
	private Office office;		// 机构
	private String picPath;		// 机构照片
	private String description;		// 机构说明
	private String mainFunc;		// 主要职能
	private String areaMap;		// 区域图
	private String areaPoint;		// 中心点
	private String maxDispatchTime; //最大签收时间
	private String maxArriveTime;  //最大到达时间
	private String icon;		// 图标
	private String more1;		// 冗余字段1
	private String sql;		// sql
	public VCcmOrg() {
		super();
	}

	public VCcmOrg(String id){
		super(id);
	}

	@JsonBackReference
	@NotNull(message="父级编号不能为空")
	public VCcmOrg getParent() {
		return parent;
	}

	public void setParent(VCcmOrg parent) {
		this.parent = parent;
	}
	
	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	@Length(min=1, max=2000, message="所有父级编号长度必须介于 1 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
//	public String getSort() {
//		return sort;
//	}
//
//	public void setSort(String sort) {
//		this.sort = sort;
//	}
	
	@NotNull(message="归属区域不能为空")
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
	
	@Length(min=1, max=1, message="机构类型长度必须介于 1 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=2, message="机构等级长度必须介于 1 和 2 之间")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Length(min=0, max=255, message="联系地址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=100, message="邮政编码长度必须介于 0 和 100 之间")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Length(min=0, max=100, message="负责人长度必须介于 0 和 100 之间")
	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}
	
	@Length(min=0, max=200, message="电话长度必须介于 0 和 200 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=200, message="传真长度必须介于 0 和 200 之间")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@Length(min=0, max=200, message="邮箱长度必须介于 0 和 200 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=64, message="是否启用长度必须介于 0 和 64 之间")
	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}
	
	public Office getPrimaryPerson() {
		return primaryPerson;
	}

	public void setPrimaryPerson(Office primaryPerson) {
		this.primaryPerson = primaryPerson;
	}
	
	public Office getDeputyPerson() {
		return deputyPerson;
	}

	public void setDeputyPerson(Office deputyPerson) {
		this.deputyPerson = deputyPerson;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=255, message="机构照片长度必须介于 0 和 255 之间")
	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	
	@Length(min=0, max=1000, message="机构说明长度必须介于 0 和 1000 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=1000, message="主要职能长度必须介于 0 和 1000 之间")
	public String getMainFunc() {
		return mainFunc;
	}

	public void setMainFunc(String mainFunc) {
		this.mainFunc = mainFunc;
	}
	
	@Length(min=0, max=2000, message="区域图长度必须介于 0 和 2000 之间")
	public String getAreaMap() {
		return areaMap;
	}

	public void setAreaMap(String areaMap) {
		this.areaMap = areaMap;
	}
	
	@Length(min=0, max=40, message="中心点长度必须介于 0 和 40 之间")
	public String getAreaPoint() {
		return areaPoint;
	}

	public void setAreaPoint(String areaPoint) {
		this.areaPoint = areaPoint;
	}
	
	@Length(min=0, max=255, message="图标长度必须介于 0 和 255 之间")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Length(min=0, max=100, message="冗余字段1长度必须介于 0 和 100 之间")
	public String getMore1() {
		return more1;
	}

	public void setMore1(String more1) {
		this.more1 = more1;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}

	public String getMaxDispatchTime() {
		return maxDispatchTime;
	}

	public void setMaxDispatchTime(String maxDispatchTime) {
		this.maxDispatchTime = maxDispatchTime;
	}

	public String getMaxArriveTime() {
		return maxArriveTime;
	}

	public void setMaxArriveTime(String maxArriveTime) {
		this.maxArriveTime = maxArriveTime;
	}
	
}