/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 职位信息Entity
 * @author lc
 * @version 2018-06-15
 */
public class PbsPositionlevel extends DataEntity<PbsPositionlevel> {
	
	private static final long serialVersionUID = 1L;
	private String sType;		// 职位/头衔
	private String sName;		// 对象名称
	private String sWorkcode;		// 对象编码
	private String sLevel;		// 对象等级
	private String sParent;		// 上级
	private String sSpare01;		// 其他内容1
	private String sSpare02;		// 其他内容2
	private String sSpare03;		// 其他内容3
	private String sSpare04;		// 其他内容4
	
	public PbsPositionlevel() {
		super();
	}

	public PbsPositionlevel(String id){
		super(id);
	}

	@Length(min=1, max=20, message="职位/头衔长度必须介于 1 和 20 之间")
	public String getSType() {
		return sType;
	}

	public void setSType(String sType) {
		this.sType = sType;
	}
	
	@Length(min=1, max=200, message="对象名称长度必须介于 1 和 200 之间")
	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}
	
	@Length(min=1, max=100, message="对象编码长度必须介于 1 和 100 之间")
	public String getSWorkcode() {
		return sWorkcode;
	}

	public void setSWorkcode(String sWorkcode) {
		this.sWorkcode = sWorkcode;
	}
	
	@Length(min=0, max=100, message="对象等级长度必须介于 0 和 100 之间")
	public String getSLevel() {
		return sLevel;
	}

	public void setSLevel(String sLevel) {
		this.sLevel = sLevel;
	}
	
	@Length(min=0, max=64, message="上级长度必须介于 0 和 64 之间")
	public String getSParent() {
		return sParent;
	}

	public void setSParent(String sParent) {
		this.sParent = sParent;
	}
	
	@Length(min=0, max=50, message="其他内容1长度必须介于 0 和 50 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}
	
	@Length(min=0, max=50, message="其他内容2长度必须介于 0 和 50 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}
	
	@Length(min=0, max=50, message="其他内容3长度必须介于 0 和 50 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}
	
	@Length(min=0, max=50, message="其他内容4长度必须介于 0 和 50 之间")
	public String getSSpare04() {
		return sSpare04;
	}

	public void setSSpare04(String sSpare04) {
		this.sSpare04 = sSpare04;
	}
	
}