/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.citycomponents.entity;

import org.hibernate.validator.constraints.Length;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.entity.User;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 土地管理Entity
 * @author pengjianqiang
 * @version 2018-03-06
 */
public class CcmLand extends DataEntity<CcmLand> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 地块名称
	private String code;		// 地块编码
	private Area area;		// 所属区域
	private String address;		// 坐落位置
	private String landArea;		// 地块面积（平方米）
	private String user;		// 土地使用者
	private String landUsage;		// 用地性质
	private String type;		// 土地用途
	private Date lifeTime;		// 使用期限
	private String areaMap;		// 坐标面
	private String areaPoint;		// 坐标点
	private String more1;  // Sql 查询语句

	private User checkUser;		// 拦截器中使用该用户进行权限拦截，App的rest接口使用
	public User getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}

	
	public CcmLand() {
		super();
	}

	public CcmLand(String id){
		super(id);
	}
    
	public String getMore1() {
		return more1;
	}
	public void setMore1(String more1) {
		this.more1 = more1;
	}
	@Length(min=0, max=100, message="地块名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="地块编码长度必须介于 0 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Length(min=0, max=200, message="坐落位置长度必须介于 0 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=10, message="地块面积（平方米）长度必须介于 0 和 10 之间")
	public String getLandArea() {
		return landArea;
	}

	public void setLandArea(String landArea) {
		this.landArea = landArea;
	}
	
	@Length(min=0, max=100, message="土地使用者长度必须介于 0 和 100 之间")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	@Length(min=0, max=10, message="用地性质长度必须介于 0 和 10 之间")
	public String getLandUsage() {
		return landUsage;
	}

	public void setLandUsage(String landUsage) {
		this.landUsage = landUsage;
	}
	
	@Length(min=0, max=10, message="土地用途长度必须介于 0 和 10 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLifeTime() {
		return lifeTime;
	}

	public void setLifeTime(Date lifeTime) {
		this.lifeTime = lifeTime;
	}
	
	@Length(min=0, max=2000, message="坐标面长度必须介于 0 和 2000 之间")
	public String getAreaMap() {
		return areaMap;
	}

	public void setAreaMap(String areaMap) {
		this.areaMap = areaMap;
	}
	
	@Length(min=0, max=40, message="坐标点长度必须介于 0 和 40 之间")
	public String getAreaPoint() {
		return areaPoint;
	}

	public void setAreaPoint(String areaPoint) {
		this.areaPoint = areaPoint;
	}
	
}