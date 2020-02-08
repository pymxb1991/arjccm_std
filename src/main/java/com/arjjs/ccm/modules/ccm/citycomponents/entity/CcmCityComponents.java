/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.citycomponents.entity;

import org.hibernate.validator.constraints.Length;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 城市部件Entity
 * @author pengjianqiang
 * @version 2018-03-06
 */
public class CcmCityComponents extends DataEntity<CcmCityComponents> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 部件类型
	private String name;		// 名称
	private String code;		// 编号
	private String competentDepartmentCode;		// 主管部门代码
	private String competentDepartmentName;		// 主管部门名称
	private String ownershipDepartmentCode;		// 权属部门代码
	private String ownershipDepartmentName;		// 权属部门名称
	private String maintainDepartmentCode;		// 养护部门代码
	private String maintainDepartmentName;		// 养护部门名称
	private String maintainDepartmentTel;		// 养护部门电话
	private Area area;		// 设备所在区域
	private String address;		// 详细地点
	private String imagePath;		// 图片
	private String spatialForm;		// 空间形态
	private String status;		// 状态
	private String areaMap;		// 坐标（面）
	private String areaPoint;		// 坐标（点）
	private String more1;  // Sql 查询语句
	private String[] types; // 部件类型数组

	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}

	private User checkUser;		// 拦截器中使用该用户进行权限拦截，App的rest接口使用
	public User getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}

	public CcmCityComponents() {
		super();
	}

	public CcmCityComponents(String id){
		super(id);
	}

	@Length(min=0, max=2, message="部件类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="编号长度必须介于 0 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=64, message="主管部门代码长度必须介于 0 和 64 之间")
	public String getCompetentDepartmentCode() {
		return competentDepartmentCode;
	}

	public void setCompetentDepartmentCode(String competentDepartmentCode) {
		this.competentDepartmentCode = competentDepartmentCode;
	}
	
	@Length(min=0, max=100, message="主管部门名称长度必须介于 0 和 100 之间")
	public String getCompetentDepartmentName() {
		return competentDepartmentName;
	}

	public void setCompetentDepartmentName(String competentDepartmentName) {
		this.competentDepartmentName = competentDepartmentName;
	}
	
	@Length(min=0, max=64, message="权属部门代码长度必须介于 0 和 64 之间")
	public String getOwnershipDepartmentCode() {
		return ownershipDepartmentCode;
	}

	public void setOwnershipDepartmentCode(String ownershipDepartmentCode) {
		this.ownershipDepartmentCode = ownershipDepartmentCode;
	}
	
	@Length(min=0, max=100, message="权属部门名称长度必须介于 0 和 100 之间")
	public String getOwnershipDepartmentName() {
		return ownershipDepartmentName;
	}

	public void setOwnershipDepartmentName(String ownershipDepartmentName) {
		this.ownershipDepartmentName = ownershipDepartmentName;
	}
	
	@Length(min=0, max=64, message="养护部门代码长度必须介于 0 和 64 之间")
	public String getMaintainDepartmentCode() {
		return maintainDepartmentCode;
	}

	public void setMaintainDepartmentCode(String maintainDepartmentCode) {
		this.maintainDepartmentCode = maintainDepartmentCode;
	}
	
	@Length(min=0, max=100, message="养护部门名称长度必须介于 0 和 100 之间")
	public String getMaintainDepartmentName() {
		return maintainDepartmentName;
	}

	public void setMaintainDepartmentName(String maintainDepartmentName) {
		this.maintainDepartmentName = maintainDepartmentName;
	}
	
	@Length(min=0, max=64, message="养护部门电话长度必须介于 0 和 64 之间")
	public String getMaintainDepartmentTel() {
		return maintainDepartmentTel;
	}

	public void setMaintainDepartmentTel(String maintainDepartmentTel) {
		this.maintainDepartmentTel = maintainDepartmentTel;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Length(min=0, max=100, message="详细地点长度必须介于 0 和 100 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=255, message="图片长度必须介于 0 和 255之间")
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	@Length(min=0, max=2, message="空间形态长度必须介于 0 和 2 之间")
	public String getSpatialForm() {
		return spatialForm;
	}

	public void setSpatialForm(String spatialForm) {
		this.spatialForm = spatialForm;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=2000, message="坐标（面）长度必须介于 0 和 2000 之间")
	public String getAreaMap() {
		return areaMap;
	}

	public void setAreaMap(String areaMap) {
		this.areaMap = areaMap;
	}
	
	@Length(min=0, max=40, message="坐标（点）长度必须介于 0 和 40 之间")
	public String getAreaPoint() {
		return areaPoint;
	}

	public void setAreaPoint(String areaPoint) {
		this.areaPoint = areaPoint;
	}

	public String getMore1() {
		return more1;
	}

	public void setMore1(String more1) {
		this.more1 = more1;
	}
	
}