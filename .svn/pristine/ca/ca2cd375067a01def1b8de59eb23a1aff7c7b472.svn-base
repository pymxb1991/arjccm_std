/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.task.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.sys.entity.User;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 工作安排反馈信息Entity
 * @author lc
 * @version 2018-05-03
 */
public class PbsTaskevaluate extends DataEntity<PbsTaskevaluate> {
	
	private static final long serialVersionUID = 1L;
	private PbsTaskrec sTaskid;		// 任务
	private String sContent;		// 评价描述信息
	private PbsPartymem sExecmember;		// 任务的负责人
	private String sValue;		// 评价分值
	private User sOperator;		// 操作人员
	private PbsPartymem sBindmember;		// 操作绑定人员
	private String sStatus;		// 记录状态
	private String sSpare01;		// 备用内容1
	private String sSpare02;		// 备用内容2
	private String sSpare03;		// 备用内容3
	private String sSpare04;		// 备用内容4
	
	public PbsTaskevaluate() {
		super();
	}

	public PbsTaskevaluate(String id){
		super(id);
	}

	
	@Length(min=0, max=255, message="评价描述信息长度必须介于 0 和 255 之间")
	public String getSContent() {
		return sContent;
	}

	public void setSContent(String sContent) {
		this.sContent = sContent;
	}
	
	
	@Length(min=0, max=255, message="评价分值长度必须介于 0 和 255 之间")
	public String getSValue() {
		return sValue;
	}

	public void setSValue(String sValue) {
		this.sValue = sValue;
	}
	
	public User getSOperator() {
		return sOperator;
	}

	public void setSOperator(User sOperator) {
		this.sOperator = sOperator;
	}
	
	
	@Length(min=0, max=255, message="记录状态长度必须介于 0 和 255 之间")
	public String getSStatus() {
		return sStatus;
	}

	public void setSStatus(String sStatus) {
		this.sStatus = sStatus;
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

	public PbsTaskrec getsTaskid() {
		return sTaskid;
	}

	public void setsTaskid(PbsTaskrec sTaskid) {
		this.sTaskid = sTaskid;
	}

	public PbsPartymem getsExecmember() {
		return sExecmember;
	}

	public void setsExecmember(PbsPartymem sExecmember) {
		this.sExecmember = sExecmember;
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
	
	
	
}