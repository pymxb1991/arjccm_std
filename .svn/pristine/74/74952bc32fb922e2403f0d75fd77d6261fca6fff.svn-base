/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.task.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 工作安排操作记录Entity
 * @author lc
 * @version 2018-05-03
 */
public class PbsTaskoprec extends DataEntity<PbsTaskoprec> {
	
	private static final long serialVersionUID = 1L;
	private PbsTaskrec sTaskid;		// 任务
	private String sSort;		// 操作排序编号
	private String sType;		// 任务操作类型
	private String sContent;		// 操作内容描述
	private Office sExecdepartment;		// 执行任务人员的部门编号
	private PbsPartymem sExecutor;		// 执行负责人员
	private User sOperator;		// 操作人员
	private PbsPartymem sBindmember;		// 绑定人员
	private String sOptstatus;		// 任务最终操作状态
	private String sSpare01;		// 备用内容1
	private String sSpare02;		// 备用内容2
	private String sSpare03;		// 备用内容3
	private String sSpare04;		// 备用内容4
	
	public PbsTaskoprec() {
		super();
	}

	public PbsTaskoprec(String id){
		super(id);
	}
	
	

	public PbsTaskrec getsTaskid() {
		return sTaskid;
	}

	public void setsTaskid(PbsTaskrec sTaskid) {
		this.sTaskid = sTaskid;
	}

	@Length(min=0, max=20, message="操作排序编号长度必须介于 0 和 20 之间")
	public String getSSort() {
		return sSort;
	}

	public void setSSort(String sSort) {
		this.sSort = sSort;
	}
	
	@Length(min=0, max=64, message="任务操作类型长度必须介于 0 和 64 之间")
	public String getSType() {
		return sType;
	}

	public void setSType(String sType) {
		this.sType = sType;
	}
	
	@Length(min=0, max=2000, message="操作内容描述长度必须介于 0 和 2000 之间")
	public String getSContent() {
		return sContent;
	}

	public void setSContent(String sContent) {
		this.sContent = sContent;
	}
	
	public Office getsExecdepartment() {
		return sExecdepartment;
	}

	public void setsExecdepartment(Office sExecdepartment) {
		this.sExecdepartment = sExecdepartment;
	}

	public PbsPartymem getsExecutor() {
		return sExecutor;
	}

	public void setsExecutor(PbsPartymem sExecutor) {
		this.sExecutor = sExecutor;
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

	@Length(min=0, max=100, message="任务最终操作状态长度必须介于 0 和 100 之间")
	public String getSOptstatus() {
		return sOptstatus;
	}

	public void setSOptstatus(String sOptstatus) {
		this.sOptstatus = sOptstatus;
	}
	
	@Length(min=0, max=50, message="备用内容1长度必须介于 0 和 50 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}
	
	@Length(min=0, max=50, message="备用内容2长度必须介于 0 和 50 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}
	
	@Length(min=0, max=50, message="备用内容3长度必须介于 0 和 50 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}
	
	@Length(min=0, max=50, message="备用内容4长度必须介于 0 和 50 之间")
	public String getSSpare04() {
		return sSpare04;
	}

	public void setSSpare04(String sSpare04) {
		this.sSpare04 = sSpare04;
	}
	
}