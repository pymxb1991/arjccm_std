/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.wechat.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;

/**
 * 微信消息Entity
 * 
 * @author lc
 * @version 2018-06-29
 */
public class PbsWebchatsendmsg extends DataEntity<PbsWebchatsendmsg> {

	private static final long serialVersionUID = 1L;
	private String sAccount; // 消息发送账户
	private String sToken; // TOKEN
	private String sMsgtype; // 发送消息类型
	private String sSenddata; // 消息发送内容
	private String sResultcontent; // 返回消息内容
	private String sRetcode; // 发送是否成功
	private String sRetmsg; // 返回消息内容
	private PbsPartymem sCreatemem; // 党员ID
	private String sDescription; // 描述信息
	private String sSpare01; // 备用字段
	private String sSpare02; // 备用字段
	private String sSpare03; // 备用字段
	private String sSpare04; // 备用字段
	private Date dtSendtime; // 发送的时间
	private String sStat; // 发送状态
	private int iSendnum; //当月发送的数量  
	private int iLimit; //发送限制
	
	
	public PbsWebchatsendmsg() {
		super();
	}

	public PbsWebchatsendmsg(String id) {
		super(id);
	}

	@Length(min = 0, max = 200, message = "消息发送账户长度必须介于 0 和 200 之间")
	public String getSAccount() {
		return sAccount;
	}

	public void setSAccount(String sAccount) {
		this.sAccount = sAccount;
	}

	@Length(min = 0, max = 100, message = "TOKEN长度必须介于 0 和 100 之间")
	public String getSToken() {
		return sToken;
	}

	public void setSToken(String sToken) {
		this.sToken = sToken;
	}

	@Length(min = 0, max = 100, message = "发送消息类型长度必须介于 0 和 100 之间")
	public String getSMsgtype() {
		return sMsgtype;
	}

	public void setSMsgtype(String sMsgtype) {
		this.sMsgtype = sMsgtype;
	}

	public String getSSenddata() {
		return sSenddata;
	}

	public void setSSenddata(String sSenddata) {
		this.sSenddata = sSenddata;
	}

	@Length(min = 0, max = 1000, message = "返回消息内容长度必须介于 0 和 1000 之间")
	public String getSResultcontent() {
		return sResultcontent;
	}

	public void setSResultcontent(String sResultcontent) {
		this.sResultcontent = sResultcontent;
	}

	@Length(min = 0, max = 20, message = "发送是否成功长度必须介于 0 和 20 之间")
	public String getSRetcode() {
		return sRetcode;
	}

	public void setSRetcode(String sRetcode) {
		this.sRetcode = sRetcode;
	}

	@Length(min = 0, max = 500, message = "返回消息内容长度必须介于 0 和 500 之间")
	public String getSRetmsg() {
		return sRetmsg;
	}

	public void setSRetmsg(String sRetmsg) {
		this.sRetmsg = sRetmsg;
	}

	public PbsPartymem getsCreatemem() {
		return sCreatemem;
	}

	public void setsCreatemem(PbsPartymem sCreatemem) {
		this.sCreatemem = sCreatemem;
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

	public Date getDtSendtime() {
		return dtSendtime;
	}

	public void setDtSendtime(Date dtSendtime) {
		this.dtSendtime = dtSendtime;
	}

	public String getsStat() {
		return sStat;
	}

	public void setsStat(String sStat) {
		this.sStat = sStat;
	}

	public int getiSendnum() {
		return iSendnum;
	}

	public void setiSendnum(int iSendnum) {
		this.iSendnum = iSendnum;
	}

	public int getiLimit() {
		return iLimit;
	}

	public void setiLimit(int iLimit) {
		this.iLimit = iLimit;
	}

}