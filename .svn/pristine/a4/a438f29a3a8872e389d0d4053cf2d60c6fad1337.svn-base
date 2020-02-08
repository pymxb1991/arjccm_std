/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.cpp.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 人际关系Entity
 * @author liuxue
 * @version 2018-11-03
 */
public class CppPopPop extends DataEntity<CppPopPop> {
	
	private static final long serialVersionUID = 1L;
	private String idCard1;		// 人员证件1
	private String idCard2;		// 人员证件2
	private String name1;		// 姓名1
	private String name2;		// 姓名2
	private String otherName;		// 系统没记录人员姓名
	private String type;		// 关系类型
	private Date timeRelation;		// 关系建立时间
	private String addTime;		// 添加时间
	public CppPopPop() {
		super();
	}

	public CppPopPop(String id){
		super(id);
	}
	
	public String getOtherName() {
		return otherName;
	}
    
	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	@Length(min=0, max=18, message="人员证件1长度必须介于 0 和 18 之间")
	public String getIdCard1() {
		return idCard1;
	}

	public void setIdCard1(String idCard1) {
		this.idCard1 = idCard1;
	}
	
	@Length(min=0, max=18, message="人员证件2长度必须介于 0 和 18 之间")
	public String getIdCard2() {
		return idCard2;
	}

	public void setIdCard2(String idCard2) {
		this.idCard2 = idCard2;
	}
	
	@Length(min=0, max=2, message="关系类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTimeRelation() {
		return timeRelation;
	}

	public void setTimeRelation(Date timeRelation) {
		this.timeRelation = timeRelation;
	}
	
}