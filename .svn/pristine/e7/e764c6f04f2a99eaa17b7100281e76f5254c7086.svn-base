/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 流程节点Entity
 * 
 * @author lc
 * @version 2018-04-20
 */
public class PbsFlownode extends DataEntity<PbsFlownode> {

	private static final long serialVersionUID = 1L;
	private String sName; // 流程节点定义名称
	private PbsFlowdefinition sFlowid; // 所属工作流程的定义ID
	private String sFlowurl; // 工作节点的页面URL
	private PbsFlownode sPrevnodeid; // 上一个节点的编号
	private PbsFlownode sNextnodeid; // 下一个节点的编号
	private String sManual; // 是否人工操作
	private String sOpearatecontent; // 操作内容记录
	private String sDescription; // 流程节点的描述信息
	private String sSpare01; // 备用内容1
	private String sSpare02; // 备用内容2
	private String sSpare03; // 备用内容3
	private String sSpare04; // 备用内容4
	private String sNodetype; // 节点类型
	private String sSort; // 顺序

	public PbsFlownode() {
		super();
	}

	public PbsFlownode(String id) {
		super(id);
	}

	@Length(min = 0, max = 200, message = "流程节点定义名称长度必须介于 0 和 200 之间")
	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}

	@Length(min = 0, max = 1000, message = "工作节点的页面URL长度必须介于 0 和 1000 之间")
	public String getSFlowurl() {
		return sFlowurl;
	}

	public void setSFlowurl(String sFlowurl) {
		this.sFlowurl = sFlowurl;
	}

	@Length(min = 0, max = 20, message = "是否人工操作长度必须介于 0 和 20 之间")
	public String getSManual() {
		return sManual;
	}

	public void setSManual(String sManual) {
		this.sManual = sManual;
	}

	@Length(min = 0, max = 1000, message = "操作内容记录长度必须介于 0 和 1000 之间")
	public String getSOpearatecontent() {
		return sOpearatecontent;
	}

	public void setSOpearatecontent(String sOpearatecontent) {
		this.sOpearatecontent = sOpearatecontent;
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

	public PbsFlownode getsPrevnodeid() {
		return sPrevnodeid;
	}

	public void setsPrevnodeid(PbsFlownode sPrevnodeid) {
		this.sPrevnodeid = sPrevnodeid;
	}

	public PbsFlownode getsNextnodeid() {
		return sNextnodeid;
	}

	public void setsNextnodeid(PbsFlownode sNextnodeid) {
		this.sNextnodeid = sNextnodeid;
	}

	public String getsNodetype() {
		return sNodetype;
	}

	public void setsNodetype(String sNodetype) {
		this.sNodetype = sNodetype;
	}

	public String getsSort() {
		return sSort;
	}

	public void setsSort(String sSort) {
		this.sSort = sSort;
	}

	
}