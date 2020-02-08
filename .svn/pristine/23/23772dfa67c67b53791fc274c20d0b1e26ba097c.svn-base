/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.chat.entity;

import org.hibernate.validator.constraints.Length;
import com.arjjs.ccm.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 聊天室Entity
 * @author lc
 * @version 2018-06-22
 */
public class PbsChatroom extends DataEntity<PbsChatroom> {
	
	private static final long serialVersionUID = 1L;
	private String sTitle;		// 聊天室
	private User sMater;		// 聊天室管理员
	private Date dtCreatetime;		// 聊天室创建时间
	private Date dtClosetime;		// 聊天室关闭时间
	private Integer iCurrntmem;		// 聊天室人员数量
	private String sDescription;		// 描述信息
	private String sSpare01;		// 备用字段
	private String sSpare02;		// 备用字段
	private String sSpare03;		// 备用字段
	private String sSpare04;		// 备用字段
	
	public PbsChatroom() {
		super();
	}

	public PbsChatroom(String id){
		super(id);
	}

	@Length(min=0, max=200, message="聊天室长度必须介于 0 和 200 之间")
	public String getSTitle() {
		return sTitle;
	}

	public void setSTitle(String sTitle) {
		this.sTitle = sTitle;
	}
	
	public User getsMater() {
		return sMater;
	}

	public void setsMater(User sMater) {
		this.sMater = sMater;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDtCreatetime() {
		return dtCreatetime;
	}

	public void setDtCreatetime(Date dtCreatetime) {
		this.dtCreatetime = dtCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDtClosetime() {
		return dtClosetime;
	}

	public void setDtClosetime(Date dtClosetime) {
		this.dtClosetime = dtClosetime;
	}
	
	public Integer getICurrntmem() {
		return iCurrntmem;
	}

	public void setICurrntmem(Integer iCurrntmem) {
		this.iCurrntmem = iCurrntmem;
	}
	
	@Length(min=0, max=100, message="描述信息长度必须介于 0 和 100 之间")
	public String getSDescription() {
		return sDescription;
	}

	public void setSDescription(String sDescription) {
		this.sDescription = sDescription;
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
	
}