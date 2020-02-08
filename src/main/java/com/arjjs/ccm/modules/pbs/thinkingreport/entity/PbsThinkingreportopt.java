/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.thinkingreport.entity;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.sys.entity.User;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 思想汇报操作Entity
 * 
 * @author lc
 * @version 2018-05-28
 */
public class PbsThinkingreportopt extends DataEntity<PbsThinkingreportopt> {

	private static final long serialVersionUID = 1L;
	private PbsThinkingreport sReportid; // 思想汇报编号
	private String sType; // 操作类型
	private PbsPartymem sTransmem = new PbsPartymem(); // 转发发送的党员
	private String sTranscontent; // 转发附加信息
	private PbsPartymem sOperatemem = new PbsPartymem(); // 评价/转发的党员
	private User sOperateuser; // 评价/转发的登录用户
	private String sContent; // 评价/评语信息
	private String sLevel; // 评价等级
	private String sDescription; // 描述信息
	private String sSpare01; // 备用字段
	private String sSpare02; // 备用字段
	private String sSpare03; // 备用字段
	private String sSpare04; // 备用字段

	public PbsThinkingreportopt() {
		super();
	}

	public PbsThinkingreportopt(String id) {
		super(id);
	}

	@JsonBackReference
	public PbsThinkingreport getsReportid() {
		return sReportid;
	}

	public void setsReportid(PbsThinkingreport sReportid) {
		this.sReportid = sReportid;
	}

	@Length(min = 0, max = 20, message = "操作类型长度必须介于 0 和 20 之间")
	public String getSType() {
		return sType;
	}

	public void setSType(String sType) {
		this.sType = sType;
	}

	@JsonBackReference
	public PbsPartymem getsTransmem() {
		return sTransmem;
	}

	public void setsTransmem(PbsPartymem sTransmem) {
		this.sTransmem = sTransmem;
	}

	@Length(min = 0, max = 1000, message = "转发附加信息长度必须介于 0 和 1000 之间")
	public String getSTranscontent() {
		return sTranscontent;
	}

	public void setSTranscontent(String sTranscontent) {
		this.sTranscontent = sTranscontent;
	}

	@JsonBackReference
	public PbsPartymem getsOperatemem() {
		return sOperatemem;
	}

	public void setsOperatemem(PbsPartymem sOperatemem) {
		this.sOperatemem = sOperatemem;
	}

	public User getsOperateuser() {
		return sOperateuser;
	}

	public void setsOperateuser(User sOperateuser) {
		this.sOperateuser = sOperateuser;
	}

	@Length(min = 0, max = 1000, message = "评价/评语信息长度必须介于 0 和 1000 之间")
	public String getSContent() {
		return sContent;
	}

	public void setSContent(String sContent) {
		this.sContent = sContent;
	}

	@Length(min = 0, max = 10, message = "评价等级长度必须介于 0 和 10 之间")
	public String getSLevel() {
		return sLevel;
	}

	public void setSLevel(String sLevel) {
		this.sLevel = sLevel;
	}

	@Length(min = 0, max = 1000, message = "描述信息长度必须介于 0 和 1000 之间")
	public String getSDescription() {
		return sDescription;
	}

	public void setSDescription(String sDescription) {
		this.sDescription = sDescription;
	}

	@Length(min = 0, max = 64, message = "备用字段长度必须介于 0 和 64 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}

	@Length(min = 0, max = 64, message = "备用字段长度必须介于 0 和 64 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}

	@Length(min = 0, max = 64, message = "备用字段长度必须介于 0 和 64 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}

	@Length(min = 0, max = 64, message = "备用字段长度必须介于 0 和 64 之间")
	public String getSSpare04() {
		return sSpare04;
	}

	public void setSSpare04(String sSpare04) {
		this.sSpare04 = sSpare04;
	}

}