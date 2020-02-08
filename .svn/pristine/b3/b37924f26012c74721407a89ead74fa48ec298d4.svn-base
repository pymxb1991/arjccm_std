/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;

/**
 * 消息提醒信息Entity
 * 
 * @author lc
 * @version 2018-08-02
 */
public class PbsRemindMsg extends DataEntity<PbsRemindMsg> {

	private static final long serialVersionUID = 1L;
	private String sFuncionid; // 模块功能
	private String sDataid; // 对应的数据主键ID
	private String sContent; // 通知内容
	private PbsPartymem sAcceptorid; // 党员
	private String sStat; // 读取状态
	private PbsPartymem sSenderid; // 发送者
	private String sSpare01; // 备用字段
	private String sSpare02; // 备用字段
	private String sSpare03; // 备用字段
	private String sSpare04; // 备用字段

	public PbsRemindMsg() {
		super();
	}

	public PbsRemindMsg(String id) {
		super(id);
	}

	@Length(min = 0, max = 64, message = "模块功能长度必须介于 0 和 64 之间")
	public String getSFuncionid() {
		return sFuncionid;
	}

	public void setSFuncionid(String sFuncionid) {
		this.sFuncionid = sFuncionid;
	}

	@Length(min = 0, max = 64, message = "对应的数据主键ID长度必须介于 0 和 64 之间")
	public String getSDataid() {
		return sDataid;
	}

	public void setSDataid(String sDataid) {
		this.sDataid = sDataid;
	}

	@Length(min = 0, max = 1500, message = "通知内容长度必须介于 0 和 1500 之间")
	public String getSContent() {
		return sContent;
	}

	public void setSContent(String sContent) {
		this.sContent = sContent;
	}

	public PbsPartymem getsAcceptorid() {
		return sAcceptorid;
	}

	public void setsAcceptorid(PbsPartymem sAcceptorid) {
		this.sAcceptorid = sAcceptorid;
	}

	@Length(min = 0, max = 5, message = "读取状态长度必须介于 0 和 5 之间")
	public String getSStat() {
		return sStat;
	}

	public void setSStat(String sStat) {
		this.sStat = sStat;
	}

	public PbsPartymem getsSenderid() {
		return sSenderid;
	}

	public void setsSenderid(PbsPartymem sSenderid) {
		this.sSenderid = sSenderid;
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