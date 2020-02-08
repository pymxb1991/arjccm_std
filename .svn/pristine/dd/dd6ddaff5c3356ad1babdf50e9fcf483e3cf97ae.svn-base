/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.planinfo.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.flat.stepinfo.entity.BphStepInfo;

/**
 * 数字化预案Entity
 * @author zhanghao
 * @version 2018-11-14
 */
public class BphPlanInfo extends DataEntity<BphPlanInfo> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 预案名称
	private String isImportant;		// 警情级别
	private String typeCode;		// 警情类型
	private String content;		// 预案描述
	private BphStepInfo bphStepInfo; //过程
	private int num;//已启动数量
	private String count;
	
	
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public BphPlanInfo() {
		super();
	}

	public BphPlanInfo(String id){
		super(id);
	}

	@Length(min=0, max=80, message="预案名称长度必须介于 0 和 80 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="警情级别长度必须介于 0 和 1 之间")
	public String getIsImportant() {
		return isImportant;
	}

	public void setIsImportant(String isImportant) {
		this.isImportant = isImportant;
	}
	
	@Length(min=0, max=64, message="警情类型长度必须介于 0 和 64 之间")
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	@Length(min=0, max=400, message="预案描述长度必须介于 0 和 400 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BphStepInfo getBphStepInfo() {
		return bphStepInfo;
	}

	public void setBphStepInfo(BphStepInfo bphStepInfo) {
		this.bphStepInfo = bphStepInfo;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}