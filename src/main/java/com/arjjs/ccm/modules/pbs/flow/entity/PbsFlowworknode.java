/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 工作节点记录Entity
 * 
 * @author lc
 * @version 2018-04-23
 */
public class PbsFlowworknode extends DataEntity<PbsFlowworknode> {

	private static final long serialVersionUID = 1L;
	private String sName; // 流程定义名称
	private PbsFlowdefinition sFlowid; // 所属工作模板
	private PbsFlownode sNodeid; // 所属节点
	private PbsFlowwork sFlowworkid; // 所属任务
	private String sBinddata; // 业务表
	private String sBindkey; // 业务主键
	private String sSetstatval; // 执行的状态设置
	private User sOperator; // 操作人员
	private PbsPartymem sBindmember; // 党员信息
	private String sActionremark; // 节点备注
	private String sDescription; // 记录描述
	private String sSpare01; // 备用内容1
	private String sSpare02; // 备用内容2
	private String sSpare03; // 备用内容3
	private String sSpare04; // 备用内容4
	private String sSort; // 顺位
	private Office sOpearatedepart; // 审核部门
	private boolean ignoreNode = false; // 忽略起始、结束环节

	public PbsFlowworknode() {
		super();
	}

	public PbsFlowworknode(String id) {
		super(id);
	}

	@Length(min = 0, max = 200, message = "流程定义名称长度必须介于 0 和 200 之间")
	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}

	@Length(min = 0, max = 200, message = "业务表长度必须介于 0 和 200 之间")
	public String getSBinddata() {
		return sBinddata;
	}

	public void setSBinddata(String sBinddata) {
		this.sBinddata = sBinddata;
	}

	@Length(min = 0, max = 2000, message = "业务主键长度必须介于 0 和 2000 之间")
	public String getSBindkey() {
		return sBindkey;
	}

	public void setSBindkey(String sBindkey) {
		this.sBindkey = sBindkey;
	}

	@Length(min = 0, max = 64, message = "执行的状态设置长度必须介于 0 和 64 之间")
	public String getSSetstatval() {
		return sSetstatval;
	}

	public void setSSetstatval(String sSetstatval) {
		this.sSetstatval = sSetstatval;
	}

	@Length(min = 0, max = 1000, message = "节点备注长度必须介于 0 和 1000 之间")
	public String getSActionremark() {
		return sActionremark;
	}

	public void setSActionremark(String sActionremark) {
		this.sActionremark = sActionremark;
	}

	@Length(min = 0, max = 1000, message = "记录描述长度必须介于 0 和 1000 之间")
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

	public PbsFlownode getsNodeid() {
		return sNodeid;
	}

	public void setsNodeid(PbsFlownode sNodeid) {
		this.sNodeid = sNodeid;
	}

	public PbsFlowwork getsFlowworkid() {
		return sFlowworkid;
	}

	public void setsFlowworkid(PbsFlowwork sFlowworkid) {
		this.sFlowworkid = sFlowworkid;
	}

	public User getsOperator() {
		return sOperator;
	}

	public void setsOperator(User sOperator) {
		this.sOperator = sOperator;
	}

	public PbsPartymem getsBindmember() {
		return sBindmember;
	}

	public void setsBindmember(PbsPartymem sBindmember) {
		this.sBindmember = sBindmember;
	}

	public String getsSort() {
		return sSort;
	}

	public void setsSort(String sSort) {
		this.sSort = sSort;
	}

	public Office getsOpearatedepart() {
		return sOpearatedepart;
	}

	public void setsOpearatedepart(Office sOpearatedepart) {
		this.sOpearatedepart = sOpearatedepart;
	}

	public boolean isIgnoreNode() {
		return ignoreNode;
	}

	public void setIgnoreNode(boolean ignoreNode) {
		this.ignoreNode = ignoreNode;
	}

}