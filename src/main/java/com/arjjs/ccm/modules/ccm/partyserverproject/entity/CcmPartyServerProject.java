/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyserverproject.entity;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 服务项目管理Entity
 * @author cby
 * @version 2019-08-15
 */
public class CcmPartyServerProject extends DataEntity<CcmPartyServerProject> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 项目名称
	private Date projectCreateDate;		// 项目创建时间
	private Area community;		// 所属社区
	private String userId;		// 负责人
	private String telphone;		// 负责人联系电话
	private String relaName;		// 联系人
	private String relaTelphone;		// 联系人电话
	private String content;		// 内容
	private String clailNum;		// 认领数
	private String relclailNum;		// 实际认领数
	private String officeId;
	
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public CcmPartyServerProject() {
		super();
	}

	public CcmPartyServerProject(String id){
		super(id);
	}

	@Length(min=1, max=100, message="项目名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="项目创建时间不能为空")
	public Date getProjectCreateDate() {
		return projectCreateDate;
	}

	public void setProjectCreateDate(Date projectCreateDate) {
		this.projectCreateDate = projectCreateDate;
	}
	
	public Area getCommunity() {
		return community;
	}

	public void setCommunity(Area community) {
		this.community = community;
	}
	
	@Length(min=1, max=150, message="负责人长度必须介于 1 和 150 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=1, max=20, message="负责人联系电话长度必须介于 1 和 20 之间")
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
	@Length(min=1, max=64, message="联系人长度必须介于 1 和 64 之间")
	public String getRelaName() {
		return relaName;
	}

	public void setRelaName(String relaName) {
		this.relaName = relaName;
	}
	
	@Length(min=1, max=20, message="联系人电话长度必须介于 1 和 20 之间")
	public String getRelaTelphone() {
		return relaTelphone;
	}

	public void setRelaTelphone(String relaTelphone) {
		this.relaTelphone = relaTelphone;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=1, max=10, message="认领数长度必须介于 1 和 10 之间")
	public String getClailNum() {
		return clailNum;
	}

	public void setClailNum(String clailNum) {
		this.clailNum = clailNum;
	}

	public String getRelclailNum() {
		return relclailNum;
	}

	public void setRelclailNum(String relclailNum) {
		this.relclailNum = relclailNum;
	}
}