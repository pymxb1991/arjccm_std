/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 活动请假Entity
 * @author lc
 * @version 2018-05-15
 */
public class PbsActivityleave extends DataEntity<PbsActivityleave> {
	
	private static final long serialVersionUID = 1L;
	private PbsActivityrec sActivityid;		// 活动
	private PbsActivitytype sType;		// 请假类型
	private User sOperator;		// 报名操作人员
	private PbsPartymem sBindmember;		// 报名绑定人员
	private String sAllpyreason;		// 请假原因
	private String sStat;		// 审批状态
	private String sSpare01;		// 备用字段
	private String sSpare02;		// 备用字段
	private String sSpare03;		// 备用字段
	private String sSpare04;		// 备用字段
	
	public PbsActivityleave() {
		super();
	}

	public PbsActivityleave(String id){
		super(id);
	}

	
	@Length(min=0, max=1000, message="请假原因长度必须介于 0 和 1000 之间")
	public String getSAllpyreason() {
		return sAllpyreason;
	}

	public void setSAllpyreason(String sAllpyreason) {
		this.sAllpyreason = sAllpyreason;
	}
	
	@Length(min=0, max=10, message="审批状态长度必须介于 0 和 10 之间")
	public String getSStat() {
		return sStat;
	}

	public void setSStat(String sStat) {
		this.sStat = sStat;
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

	public PbsActivitytype getsType() {
		return sType;
	}

	public void setsType(PbsActivitytype sType) {
		this.sType = sType;
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