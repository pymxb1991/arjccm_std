/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;

/**
 * 活动通知Entity
 * @author lc
 * @version 2018-05-14
 */
public class PbsActinotifications extends DataEntity<PbsActinotifications> {
	
	private static final long serialVersionUID = 1L;
	private PbsActivityrec sActivityid;		// 活动
	private String sType;		// 通知类型
	private String sContent;		// 通知内容
	private PbsPartymem sAcceptorid =new PbsPartymem();		// 名称
	private String sStat;		// 读取状态
	private String sDescription;		// 描述信息
	private String sUrl;		// 参考信息
	private String sSpare01;		// 备用字段
	private String sSpare02;		// 备用字段
	private String sSpare03;		// 备用字段
	private String sSpare04;		// 备用字段
	
	public PbsActinotifications() {
		super();
	}

	public PbsActinotifications(String id){
		super(id);
	}

	
	public String getSContent() {
		return sContent;
	}

	public void setSContent(String sContent) {
		this.sContent = sContent;
	}
	
	
	@Length(min=0, max=5, message="读取状态长度必须介于 0 和 5 之间")
	public String getSStat() {
		return sStat;
	}

	public void setSStat(String sStat) {
		this.sStat = sStat;
	}
	
	@Length(min=0, max=1000, message="描述信息长度必须介于 0 和 1000 之间")
	public String getSDescription() {
		return sDescription;
	}

	public void setSDescription(String sDescription) {
		this.sDescription = sDescription;
	}
	
	@Length(min=0, max=500, message="参考信息长度必须介于 0 和 500 之间")
	public String getSUrl() {
		return sUrl;
	}

	public void setSUrl(String sUrl) {
		this.sUrl = sUrl;
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

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}

	public PbsPartymem getsAcceptorid() {
		return sAcceptorid;
	}

	public void setsAcceptorid(PbsPartymem sAcceptorid) {
		this.sAcceptorid = sAcceptorid;
	}
	
	
	
}