/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyvolutterpost.entity;

import com.arjjs.ccm.modules.sys.entity.Area;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 志愿者岗位管理Entity
 * @author cby
 * @version 2019-08-15
 */
public class CcmPartyVolutterPost extends DataEntity<CcmPartyVolutterPost> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 岗位名称
	private String type;		// 岗位类型
	private String professionalRequirements;		// 专业要求
	private Area community;		// 所属社区
	private String claimNum;		// 拟认领数
	private String relclailNum;		// 实认领数
	private Date publishTime;		// 发布时间
	private String rela;		// 联系人
	private String telphone;		// 联系电话
	private String jobDescription;		// 岗位描述
	private String officeId;
	
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getRelclailNum() {
		return relclailNum;
	}

	public void setRelclailNum(String relclailNum) {
		this.relclailNum = relclailNum;
	}
	
	public CcmPartyVolutterPost() {
		super();
	}

	public CcmPartyVolutterPost(String id){
		super(id);
	}

	@Length(min=0, max=100, message="岗位名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="岗位类型长度必须介于 0 和 64 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=100, message="专业要求长度必须介于 0 和 100 之间")
	public String getProfessionalRequirements() {
		return professionalRequirements;
	}

	public void setProfessionalRequirements(String professionalRequirements) {
		this.professionalRequirements = professionalRequirements;
	}
	
	public Area getCommunity() {
		return community;
	}

	public void setCommunity(Area community) {
		this.community = community;
	}
	
	@Length(min=0, max=10, message="拟认领数长度必须介于 0 和 10 之间")
	public String getClaimNum() {
		return claimNum;
	}

	public void setClaimNum(String claimNum) {
		this.claimNum = claimNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	
	@Length(min=0, max=64, message="联系人长度必须介于 0 和 64 之间")
	public String getRela() {
		return rela;
	}

	public void setRela(String rela) {
		this.rela = rela;
	}
	
	@Length(min=0, max=20, message="联系电话长度必须介于 0 和 20 之间")
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	
}