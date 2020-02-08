/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.thinkingreport.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.sys.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 思想汇报信息Entity
 * 
 * @author lc
 * @version 2018-05-28
 */
public class PbsThinkingreport extends DataEntity<PbsThinkingreport> {

	private static final long serialVersionUID = 1L;
	private String sTitle; // 思想汇报的标题
	private String sContent; // 思想汇报内容
	private User sReporteruser; // 发布者
	private PbsPartymem sReportermem = new PbsPartymem(); // 发布者党员
	private PbsPartymem sAcceptermem = new PbsPartymem(); // 接收者党员
	private String sPublish; // 公布标记
	private String sStat; // 状态
	private String sLevel; // 被评价等级
	private String sDescription; // 描述信息
	private String sSpare01; // 备用字段
	private String sSpare02; // 备用字段
	private String sSpare03; // 备用字段
	private String sSpare04; // 备用字段
	private String sType; // 当前的信息的类型 转发 或 直接获取
	private PbsPartymem sTransmem; // 当前的 被转发的人

	public PbsThinkingreport() {
		super();
	}

	public PbsThinkingreport(String id) {
		super(id);
	}

	@NotEmpty(message = "标题不能为空")
	@Length(min = 0, max = 200, message = "思想汇报的标题长度必须介于 0 和 200 之间")
	public String getSTitle() {
		return sTitle;
	}

	public void setSTitle(String sTitle) {
		this.sTitle = sTitle;
	}

	@NotEmpty(message = "内容不能为空")
	public String getSContent() {
		return sContent;
	}

	public void setSContent(String sContent) {
		this.sContent = sContent;
	}

	public User getsReporteruser() {
		return sReporteruser;
	}

	public void setsReporteruser(User sReporteruser) {
		this.sReporteruser = sReporteruser;
	}

	@JsonBackReference
	public PbsPartymem getsReportermem() {
		return sReportermem;
	}

	public void setsReportermem(PbsPartymem sReportermem) {
		this.sReportermem = sReportermem;
	}

	@JsonBackReference
	public PbsPartymem getsAcceptermem() {
		return sAcceptermem;
	}

	public void setsAcceptermem(PbsPartymem sAcceptermem) {
		this.sAcceptermem = sAcceptermem;
	}

	@Length(min = 0, max = 20, message = "公布标记长度必须介于 0 和 20 之间")
	public String getSPublish() {
		return sPublish;
	}

	public void setSPublish(String sPublish) {
		this.sPublish = sPublish;
	}

	@Length(min = 0, max = 10, message = "状态长度必须介于 0 和 10 之间")
	public String getSStat() {
		return sStat;
	}

	public void setSStat(String sStat) {
		this.sStat = sStat;
	}

	@Length(min = 0, max = 10, message = "被评价等级长度必须介于 0 和 10 之间")
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

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}

	public PbsPartymem getsTransmem() {
		return sTransmem;
	}

	public void setsTransmem(PbsPartymem sTransmem) {
		this.sTransmem = sTransmem;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PbsThinkingreport Thinkingreport = (PbsThinkingreport) o;
		if (!id.equals(Thinkingreport.id))
			return false;
		return id.equals(Thinkingreport.id);
	}

	@Override
	public int hashCode() {
		String in = id;
		return in.hashCode();
	}
}