/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 流程定义信息Entity
 * 
 * @author lc
 * @version 2018-04-19
 */
public class PbsFlowdefinition extends DataEntity<PbsFlowdefinition> {

	private static final long serialVersionUID = 1L;
	private String sName; // 流程定义名称
	private PbsFlowtype sTypeid; // 流程类别编号
	private String sStartstat; // 启用状态
	private String sLockstat; // 锁定状态
	private String sRevoke; // 撤销状态
	private String sDescription; // 描述信息
	private String sSpare01; // 备用内容1
	private String sSpare02; // 备用内容2
	private String sSpare03; // 备用内容3
	private String sSpare04; // 备用内容4
	private boolean flagNew; // 判断是否新增
	private String sTypecode; // 类型代码

	public PbsFlowdefinition() {
		super();
	}

	public PbsFlowdefinition(String id) {
		super(id);
	}

	@Length(min = 0, max = 200, message = "流程定义名称长度必须介于 0 和 200 之间")
	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}

	@Length(min = 0, max = 30, message = "启用状态长度必须介于 0 和 30 之间")
	public String getSStartstat() {
		return sStartstat;
	}

	public PbsFlowtype getsTypeid() {
		return sTypeid;
	}

	public void setsTypeid(PbsFlowtype sTypeid) {
		this.sTypeid = sTypeid;
	}

	public void setSStartstat(String sStartstat) {
		this.sStartstat = sStartstat;
	}

	@Length(min = 0, max = 30, message = "锁定状态长度必须介于 0 和 30 之间")
	public String getSLockstat() {
		return sLockstat;
	}

	public void setSLockstat(String sLockstat) {
		this.sLockstat = sLockstat;
	}

	@Length(min = 0, max = 30, message = "撤销状态长度必须介于 0 和 30 之间")
	public String getSRevoke() {
		return sRevoke;
	}

	public void setSRevoke(String sRevoke) {
		this.sRevoke = sRevoke;
	}

	@Length(min = 0, max = 1000, message = "描述信息长度必须介于 0 和 1000 之间")
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

	public boolean getFlagNew() {
		return flagNew;
	}

	public void setFlagNew(boolean flagNew) {
		this.flagNew = flagNew;
	}

	public String getsTypecode() {
		return sTypecode;
	}

	public void setsTypecode(String sTypecode) {
		this.sTypecode = sTypecode;
	}
}