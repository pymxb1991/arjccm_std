/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.proposal.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.sys.entity.User;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 建议操作信息Entity
 * @author lc
 * @version 2018-05-31
 */
public class PbsProposalopt extends DataEntity<PbsProposalopt> {
	
	private static final long serialVersionUID = 1L;
	private PbsProposalarea sAreatype;		// 建议类别
	private String sAreavesion;		// 类别版本
	private PbsProposal sProposalid;		// 献言建策
	private String sOpttype;		// 操作类型
	private String sOpresult;		// 操作结果
	private String sContent;		// 操作批注
	private String sShowtype;		// 展示方式
	private User sReporteruser;		// 操作者用户
	private PbsPartymem sReportermem;		// 操作者党员
	private String sLevel;		// 评价等级
	private String sDescription;		// 描述信息
	private String sSpare01;		// 备用字段
	private String sSpare02;		// 备用字段
	private String sSpare03;		// 备用字段
	private String sSpare04;		// 备用字段
	
	public PbsProposalopt() {
		super();
	}

	public PbsProposalopt(String id){
		super(id);
	}

	
	@Length(min=0, max=10, message="操作类型长度必须介于 0 和 10 之间")
	public String getSOpttype() {
		return sOpttype;
	}

	public void setSOpttype(String sOpttype) {
		this.sOpttype = sOpttype;
	}
	
	@Length(min=0, max=10, message="操作结果长度必须介于 0 和 10 之间")
	public String getSOpresult() {
		return sOpresult;
	}

	public void setSOpresult(String sOpresult) {
		this.sOpresult = sOpresult;
	}
	
	public String getSContent() {
		return sContent;
	}

	public void setSContent(String sContent) {
		this.sContent = sContent;
	}
	
	@Length(min=0, max=10, message="展示方式长度必须介于 0 和 10 之间")
	public String getSShowtype() {
		return sShowtype;
	}

	public void setSShowtype(String sShowtype) {
		this.sShowtype = sShowtype;
	}
	
	public User getSReporteruser() {
		return sReporteruser;
	}

	public void setSReporteruser(User sReporteruser) {
		this.sReporteruser = sReporteruser;
	}
	
	@Length(min=0, max=10, message="评价等级长度必须介于 0 和 10 之间")
	public String getSLevel() {
		return sLevel;
	}

	public void setSLevel(String sLevel) {
		this.sLevel = sLevel;
	}
	
	@Length(min=0, max=1000, message="描述信息长度必须介于 0 和 1000 之间")
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

	public PbsProposalarea getsAreatype() {
		return sAreatype;
	}

	public void setsAreatype(PbsProposalarea sAreatype) {
		this.sAreatype = sAreatype;
	}

	public String getsAreavesion() {
		return sAreavesion;
	}

	public void setsAreavesion(String sAreavesion) {
		this.sAreavesion = sAreavesion;
	}

	public PbsProposal getsProposalid() {
		return sProposalid;
	}

	public void setsProposalid(PbsProposal sProposalid) {
		this.sProposalid = sProposalid;
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
	
	
}