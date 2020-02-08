/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.entity;

import com.arjjs.ccm.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 综治机构Entity
 * @author liang
 * @version 2018-01-12
 */
public class CcmOrgComprehensive extends DataEntity<CcmOrgComprehensive> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 机构
	private String picPath;		// 机构照片
	private String description;		// 机构说明
	private String mainFunc;		// 主要职能
	private String areaMap;		// 区域图
	private String areaPoint;		// 中心点
	private String icon;		// 图标
	private String more1;		// 冗余字段1
	private String maxDispatchTime; //最大签收时间
	private String maxArriveTime;  //最大到达时间
	
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

	public CcmOrgComprehensive() {
		super();
	}

	public CcmOrgComprehensive(String id){
		super(id);
	}

	@NotNull(message="机构不能为空")
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
	
}