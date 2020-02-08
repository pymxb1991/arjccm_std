/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 节点操作后修改Entity
 * 
 * @author lc
 * @version 2018-04-20
 */
public class PbsFlowsetstat extends DataEntity<PbsFlowsetstat> {

	private static final long serialVersionUID = 1L;
	private PbsFlowdefinition sFlowid; // 定义流程
	private PbsFlownode sFlownodeid; // 节点
	private String sOpeatertype; // 操作类型
	private String sName; // 操作名称
	private String sCollection; // 操作对象
	private String sProperty; // 操作对象属性名称
	private String sSetval; // 操作设置值
	private String sDescription; // 流程节点的描述信息
	private String sSpare01; // 备用内容1
	private String sSpare02; // 备用内容2
	private String sSpare03; // 备用内容3
	private String sSpare04; // 备用内容4
	private String key; //传入的 操作id值

	public PbsFlowsetstat() {
		super();
	}

	public PbsFlowsetstat(String id) {
		super(id);
	}

	@Length(min = 0, max = 64, message = "操作类型长度必须介于 0 和 64 之间")
	public String getSOpeatertype() {
		return sOpeatertype;
	}

	public void setSOpeatertype(String sOpeatertype) {
		this.sOpeatertype = sOpeatertype;
	}

	@Length(min = 0, max = 100, message = "操作名称长度必须介于 0 和 100 之间")
	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}

	@Length(min = 0, max = 100, message = "操作对象长度必须介于 0 和 100 之间")
	public String getSCollection() {
		return sCollection;
	}

	public void setSCollection(String sCollection) {
		this.sCollection = sCollection;
	}

	@Length(min = 0, max = 100, message = "操作对象属性名称长度必须介于 0 和 100 之间")
	public String getSProperty() {
		return sProperty;
	}

	public void setSProperty(String sProperty) {
		this.sProperty = sProperty;
	}

	@Length(min = 0, max = 255, message = "操作设置值长度必须介于 0 和 255 之间")
	public String getSSetval() {
		return sSetval;
	}

	public void setSSetval(String sSetval) {
		this.sSetval = sSetval;
	}

	@Length(min = 0, max = 1000, message = "流程节点的描述信息长度必须介于 0 和 1000 之间")
	public String getSDescription() {
		return sDescription;
	}

	public void setSDescription(String sDescription) {
		this.sDescription = sDescription;
	}

	@Length(min = 0, max = 50, message = "备用内容1长度必须介于 0 和 50 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}

	@Length(min = 0, max = 50, message = "备用内容2长度必须介于 0 和 50 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}

	@Length(min = 0, max = 50, message = "备用内容3长度必须介于 0 和 50 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}

	@Length(min = 0, max = 50, message = "备用内容4长度必须介于 0 和 50 之间")
	public String getSSpare04() {
		return sSpare04;
	}

	public void setSSpare04(String sSpare04) {
		this.sSpare04 = sSpare04;
	}

	public PbsFlowdefinition getsFlowid() {
		return sFlowid;
	}

	public void setsFlowid(PbsFlowdefinition sFlowid) {
		this.sFlowid = sFlowid;
	}

	public PbsFlownode getsFlownodeid() {
		return sFlownodeid;
	}

	public void setsFlownodeid(PbsFlownode sFlownodeid) {
		this.sFlownodeid = sFlownodeid;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	
}