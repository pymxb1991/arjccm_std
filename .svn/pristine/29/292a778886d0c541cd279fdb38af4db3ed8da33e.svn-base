/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.pop.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 街路巷Entity
 * @author liujindan
 * @version 2019-02-25
 */
public class CcmRoadAddress extends DataEntity<CcmRoadAddress> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 街路巷名称
	private String address;		// 地址
	private String areaMap;		// 区域图
	private String areaPoint;		// 中心点
	private String image;		// 图片
	
	private User checkUser;		// 拦截器中使用该用户进行权限拦截，App的rest接口使用
	
	public User getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}

	public CcmRoadAddress() {
		super();
	}

	public CcmRoadAddress(String id){
		super(id);
	}

	@Length(min=0, max=64, message="街路巷名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=512, message="地址长度必须介于 0 和 512 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	
	@Length(min=0, max=255, message="图片长度必须介于 0 和 255 之间")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}