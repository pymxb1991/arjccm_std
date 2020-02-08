/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.sys.entity.User;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 活动评论信息Entity
 * @author lc
 * @version 2018-07-10
 */
public class PbsActivityecomment extends DataEntity<PbsActivityecomment> {
	
	private static final long serialVersionUID = 1L;
	private PbsActivityrec sActivityid;		// 活动编号
	private String sContent;		// 评论内容信息
	private String sFeedbackid;		// 回复的评论ID
	private String sValue;		// 评价分值
	private User sOperator;		// 操作人员
	private PbsPartymem sBindmember;		// 操作绑定人员
	private String sStatus;		// 记录状态
	private String sSpare01;		// 备用字段
	private String sSpare02;		// 备用字段
	private String sSpare03;		// 备用字段
	private String sSpare04;		// 备用字段
	
	public PbsActivityecomment() {
		super();
	}

	public PbsActivityecomment(String id){
		super(id);
	}

	@Length(min=0, max=2000, message="评论内容信息长度必须介于 0 和 2000 之间")
	public String getSContent() {
		return sContent;
	}

	public void setSContent(String sContent) {
		this.sContent = sContent;
	}
	
	@Length(min=0, max=64, message="回复的评论ID长度必须介于 0 和 64 之间")
	public String getSFeedbackid() {
		return sFeedbackid;
	}

	public void setSFeedbackid(String sFeedbackid) {
		this.sFeedbackid = sFeedbackid;
	}
	
	@Length(min=0, max=100, message="评价分值长度必须介于 0 和 100 之间")
	public String getSValue() {
		return sValue;
	}

	public void setSValue(String sValue) {
		this.sValue = sValue;
	}
	
	@Length(min=0, max=100, message="记录状态长度必须介于 0 和 100 之间")
	public String getSStatus() {
		return sStatus;
	}

	public void setSStatus(String sStatus) {
		this.sStatus = sStatus;
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

	public PbsActivityrec getsActivityid() {
		return sActivityid;
	}

	public void setsActivityid(PbsActivityrec sActivityid) {
		this.sActivityid = sActivityid;
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