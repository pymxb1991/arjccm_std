/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.proposal.entity;

import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 建议分区Entity
 * @author lc
 * @version 2018-05-31
 */
public class PbsProposalarea extends DataEntity<PbsProposalarea> {
	
	private static final long serialVersionUID = 1L;
	private Office sDepartment;		// 所属支部编号
	private String sName;		// 标题名称
	private PbsPartymem sMastermem;		// 建议负责人
	private String iVersion;		// 数据版本
	private String sShowtype;		// 展示方式
	private String sPublish;		// 公布标记
	private String sDescription;		// 描述信息
	private String sSpare01;		// 备用字段
	private String sSpare02;		// 备用字段
	private String sSpare03;		// 备用字段
	private String sSpare04;		// 备用字段
	
	public PbsProposalarea() {
		super();
	}

	public PbsProposalarea(String id){
		super(id);
	}

	@Length(min=0, max=200, message="标题名称长度必须介于 0 和 200 之间")
	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}
	
	@Length(min=0, max=10, message="数据版本长度必须介于 0 和 10 之间")
	public String getIVersion() {
		return iVersion;
	}

	public void setIVersion(String iVersion) {
		this.iVersion = iVersion;
	}
	
	@Length(min=0, max=10, message="展示方式长度必须介于 0 和 10 之间")
	public String getSShowtype() {
		return sShowtype;
	}

	public void setSShowtype(String sShowtype) {
		this.sShowtype = sShowtype;
	}
	
	@Length(min=0, max=20, message="公布标记长度必须介于 0 和 20 之间")
	public String getSPublish() {
		return sPublish;
	}

	public void setSPublish(String sPublish) {
		this.sPublish = sPublish;
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

	public PbsPartymem getsMastermem() {
		return sMastermem;
	}

	public void setsMastermem(PbsPartymem sMastermem) {
		this.sMastermem = sMastermem;
	}

	public Office getsDepartment() {
		return sDepartment;
	}

	public void setsDepartment(Office sDepartment) {
		this.sDepartment = sDepartment;
	}
	
	
}