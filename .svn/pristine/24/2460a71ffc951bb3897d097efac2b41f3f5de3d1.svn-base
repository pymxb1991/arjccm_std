/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.proposal.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 建议信息Entity
 * 
 * @author lc
 * @version 2018-05-31
 */
public class PbsProposal extends DataEntity<PbsProposal> {

	private static final long serialVersionUID = 1L;
	private String sTitle; // 建议汇报的标题
	private String sContent; // 建议汇报内容
	private PbsProposalarea sAreaid; // 建议类别
	private String sAreaversion; // 建议类别版本
	private String sShowtype; // 展示方式
	private String iCnt; // 回复人数
	private User sReporteruser; // 发布者
	private PbsPartymem sReportermem; // 发布者党员信息
	private PbsPartymem sAcceptermem; // 接收者党员信息
	private String sPublish; // 公布标记
	private String sStat; // 提交状态
	private String sLevel; // 被评价标记
	private String sDescription; // 描述信息
	private String sSpare01; // 备用字段
	private String sSpare02; // 备用字段
	private String sSpare03; // 备用字段
	private String sSpare04; // 备用字段
	

	public PbsProposal() {
		super();
	}

	public PbsProposal(String id) {
		super(id);
	}

	@Length(min = 0, max = 200, message = "建议汇报的标题长度必须介于 0 和 200 之间")
	public String getSTitle() {
		return sTitle;
	}

	public void setSTitle(String sTitle) {
		this.sTitle = sTitle;
	}

	public String getSContent() {
		return sContent;
	}

	public void setSContent(String sContent) {
		this.sContent = sContent;
	}

	@Length(min = 0, max = 10, message = "建议类别版本长度必须介于 0 和 10 之间")
	public String getSAreaversion() {
		return sAreaversion;
	}

	public void setSAreaversion(String sAreaversion) {
		this.sAreaversion = sAreaversion;
	}

	@Length(min = 0, max = 10, message = "展示方式长度必须介于 0 和 10 之间")
	public String getSShowtype() {
		return sShowtype;
	}

	public void setSShowtype(String sShowtype) {
		this.sShowtype = sShowtype;
	}

	@Length(min = 0, max = 11, message = "回复人数长度必须介于 0 和 11 之间")
	public String getICnt() {
		return iCnt;
	}

	public void setICnt(String iCnt) {
		this.iCnt = iCnt;
	}

	@Length(min = 0, max = 20, message = "公布标记长度必须介于 0 和 20 之间")
	public String getSPublish() {
		return sPublish;
	}

	public void setSPublish(String sPublish) {
		this.sPublish = sPublish;
	}

	@Length(min = 0, max = 10, message = "提交状态长度必须介于 0 和 10 之间")
	public String getSStat() {
		return sStat;
	}

	public void setSStat(String sStat) {
		this.sStat = sStat;
	}

	@Length(min = 0, max = 10, message = "被评价标记长度必须介于 0 和 10 之间")
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

	public PbsProposalarea getsAreaid() {
		return sAreaid;
	}

	public void setsAreaid(PbsProposalarea sAreaid) {
		this.sAreaid = sAreaid;
	}

	public User getsReporteruser() {
		return sReporteruser;
	}

	public void setsReporteruser(User sReporteruser) {
		this.sReporteruser = sReporteruser;
	}

	public PbsPartymem getsReportermem() {
		return sReportermem;
	}

	public void setsReportermem(PbsPartymem sReportermem) {
		this.sReportermem = sReportermem;
	}

	public PbsPartymem getsAcceptermem() {
		return sAcceptermem;
	}

	public void setsAcceptermem(PbsPartymem sAcceptermem) {
		this.sAcceptermem = sAcceptermem;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PbsProposal PbsProposal = (PbsProposal) o;
 		if (!id.equals(PbsProposal.id))
			return false;
		return id.equals(PbsProposal.id);
	}

	@Override
	public int hashCode() {
		String in = id;
		return in.hashCode();
	}
}
