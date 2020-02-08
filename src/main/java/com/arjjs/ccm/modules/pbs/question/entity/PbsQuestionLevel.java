/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 考试难度级别Entity
 * @author lc
 * @version 2018-06-06
 */
public class PbsQuestionLevel extends DataEntity<PbsQuestionLevel> {
	
	private static final long serialVersionUID = 1L;
	private String sName;		// 考试难度级别名称
	private String iVal;		// 难度级别赋值
	private String sUrl;		// 展示的图片
	private String sSpare01;		// 备用字段
	private String sSpare02;		// 备用字段
	private String sSpare03;		// 备用字段
	private String sSpare04;		// 备用字段
	
	public PbsQuestionLevel() {
		super();
	}

	public PbsQuestionLevel(String id){
		super(id);
	}

	@Length(min=1, max=500, message="考试难度级别名称长度必须介于 1 和 500 之间")
	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}
	
	@Length(min=0, max=10, message="难度级别赋值长度必须介于 0 和 10 之间")
	public String getIVal() {
		return iVal;
	}

	public void setIVal(String iVal) {
		this.iVal = iVal;
	}
	
	@Length(min=0, max=500, message="展示的图片长度必须介于 0 和 500 之间")
	public String getSUrl() {
		return sUrl;
	}

	public void setSUrl(String sUrl) {
		this.sUrl = sUrl;
	}
	
	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}
	
	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}
	
	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}
	
	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSSpare04() {
		return sSpare04;
	}

	public void setSSpare04(String sSpare04) {
		this.sSpare04 = sSpare04;
	}
	
}