/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.cpp.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 人车/联系/网络关系表Entity
 * @author liuxue
 * @version 2018-10-30
 */
public class CppPopVehile extends DataEntity<CppPopVehile> {
	
	private static final long serialVersionUID = 1L;
	private String idCard;		// 人员证件
	private String textName;		// 车牌号/手机号/QQ微信号等
	private Date timeRelation;		// 关系建立时间
	private String subType;		// (车辆/联系方式/网络身份)(车辆/联系方式/网络身份)各类型的子类型
	private String otherTypeName;		// 其他子类型名称
	private String type;		// 车辆/联系方式/网络身份(01/02/03)
	private String more1;		// 冗余字段1
	private String more2;		// 冗余字段2
	
	public CppPopVehile() {
		super();
	}

	public CppPopVehile(String id){
		super(id);
	}

	@Length(min=0, max=18, message="人员证件长度必须介于 0 和 18 之间")
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	@Length(min=0, max=64, message="车牌号/手机号/QQ微信号等长度必须介于 0 和 64 之间")
	public String getTextName() {
		return textName;
	}

	public void setTextName(String textName) {
		this.textName = textName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTimeRelation() {
		return timeRelation;
	}

	public void setTimeRelation(Date timeRelation) {
		this.timeRelation = timeRelation;
	}
	
	@Length(min=0, max=2, message="(车辆/联系方式/网络身份)(车辆/联系方式/网络身份)各类型的子类型长度必须介于 0 和 2 之间")
	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	@Length(min=0, max=20, message="其他子类型名称长度必须介于 0 和 20 之间")
	public String getOtherTypeName() {
		return otherTypeName;
	}

	public void setOtherTypeName(String otherTypeName) {
		this.otherTypeName = otherTypeName;
	}
	
	@Length(min=0, max=2, message="车辆/联系方式/网络身份(01/02/03)长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=255, message="冗余字段1长度必须介于 1 和 255 之间")
	public String getMore1() {
		return more1;
	}

	public void setMore1(String more1) {
		this.more1 = more1;
	}
	
	@Length(min=1, max=255, message="冗余字段2长度必须介于 1 和 255 之间")
	public String getMore2() {
		return more2;
	}

	public void setMore2(String more2) {
		this.more2 = more2;
	}
	
}