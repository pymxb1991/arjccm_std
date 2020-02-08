/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.entity;

import com.arjjs.ccm.modules.sys.entity.Area;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 辖区信息Entity
 * @author wwh
 * @version 2018-01-23
 */
public class CcmHouseAreainfor extends DataEntity<CcmHouseAreainfor> {
	
	private static final long serialVersionUID = 1L;
	private Area area;		// 辖区信息
	private String areainfor;		// 辖区说明
	private String more1;		// 冗余字段1
	private String more2;		// 冗余字段2
	
	public CcmHouseAreainfor() {
		super();
	}

	public CcmHouseAreainfor(String id){
		super(id);
	}

	@NotNull(message="辖区信息不能为空")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	public String getAreainfor() {
		return areainfor;
	}

	public void setAreainfor(String areainfor) {
		this.areainfor = areainfor;
	}
	
	@Length(min=0, max=100, message="冗余字段1长度必须介于 0 和 100 之间")
	public String getMore1() {
		return more1;
	}

	public void setMore1(String more1) {
		this.more1 = more1;
	}
	
	@Length(min=0, max=100, message="冗余字段2长度必须介于 0 和 100 之间")
	public String getMore2() {
		return more2;
	}

	public void setMore2(String more2) {
		this.more2 = more2;
	}
	
}