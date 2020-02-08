/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.home.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 门户方案Entity
 * @author liuxue
 * @version 2018-07-12
 */
public class PlmPortalPlan extends DataEntity<PlmPortalPlan> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 方案名称
	private String introduce;		// 说明
	private String type;		// 类型
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	
	
	public PlmPortalPlan() {
		super();
	}

	public PlmPortalPlan(String id){
		super(id);
	}

	@Length(min=1, max=256, message="方案名称长度必须介于 1 和 256 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=256, message="说明长度必须介于 0 和 256 之间")
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	@Length(min=1, max=1, message="类型长度必须介于 1 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=256, message="扩展1长度必须介于 1 和 256 之间")
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	@Length(min=1, max=256, message="扩展2长度必须介于 1 和 256 之间")
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	
	
}