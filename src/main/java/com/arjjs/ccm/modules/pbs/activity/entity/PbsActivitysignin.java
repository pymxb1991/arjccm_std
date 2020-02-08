/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 活动签到Entity
 * @author lc
 * @version 2018-05-15
 */
public class PbsActivitysignin extends DataEntity<PbsActivitysignin> {
	
	private static final long serialVersionUID = 1L;
	private PbsActivityrec sActivityid;		// 活动
	private PbsActivitytype sType;		// 活动类型
	private String sRegflag;		// 是否报名人员
	private PbsPartymem sSignbindmember = new PbsPartymem();		// 签到绑定人员
	private String sFamilyflow;		// 携带家属人数
	private User sOperator;		// 操作人员
	private PbsPartymem sBindmember = new PbsPartymem();		// 操作绑定人员
	private String sSpare01;		// 备用字段
	private String sSpare02;		// 备用字段
	private String sSpare03;		// 备用字段
	private String sSpare04;		// 备用字段
	
	public PbsActivitysignin() {
		super();
	}

	public PbsActivitysignin(String id){
		super(id);
	}

	@Length(min=0, max=200, message="是否报名人员长度必须介于 0 和 200 之间")
	public String getSRegflag() {
		return sRegflag;
	}

	public void setSRegflag(String sRegflag) {
		this.sRegflag = sRegflag;
	}
	
	@Length(min=0, max=20, message="携带家属人数长度必须介于 0 和 20 之间")
	public String getSFamilyflow() {
		return sFamilyflow;
	}

	public void setSFamilyflow(String sFamilyflow) {
		this.sFamilyflow = sFamilyflow;
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

	public PbsPartymem getsSignbindmember() {
		return sSignbindmember;
	}

	public void setsSignbindmember(PbsPartymem sSignbindmember) {
		this.sSignbindmember = sSignbindmember;
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