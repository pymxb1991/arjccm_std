/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyteam.entity;

import com.arjjs.ccm.modules.sys.entity.Area;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import com.arjjs.ccm.modules.sys.entity.User;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 队伍管理Entity
 * @author maoxb
 * @version 2019-08-13
 */
public class CcmPartyVolunteerTeam extends DataEntity<CcmPartyVolunteerTeam> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private Area community;		// 选择社区
	private String superTeam;		// 上级队伍
	private Date foundTime;		// 成立日期
	private User user;		// 负责人
	private String telphone;		// 联系电话
	private String address;		// 通讯地址
	private String serverMatters;		// 服务事项
	private String serverDeeds;		// 服务事迹
	private String officeId;
	
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public CcmPartyVolunteerTeam() {
		super();
	}

	public CcmPartyVolunteerTeam(String id){
		super(id);
	}

	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=64, message="选择社区长度必须介于 1 和 64 之间")
	public Area getCommunity() {
		return community;
	}

	public void setCommunity(Area community) {
		this.community = community;
	}
	
	@Length(min=0, max=64, message="上级队伍长度必须介于 0 和 64 之间")
	public String getSuperTeam() {
		return superTeam;
	}

	public void setSuperTeam(String superTeam) {
		this.superTeam = superTeam;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="成立日期不能为空")
	public Date getFoundTime() {
		return foundTime;
	}

	public void setFoundTime(Date foundTime) {
		this.foundTime = foundTime;
	}
	
	@NotNull(message="负责人不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=20, message="联系电话长度必须介于 1 和 20 之间")
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
	@Length(min=1, max=200, message="通讯地址长度必须介于 1 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getServerMatters() {
		return serverMatters;
	}

	public void setServerMatters(String serverMatters) {
		this.serverMatters = serverMatters;
	}
	
	@Length(min=0, max=255, message="服务事迹长度必须介于 0 和 255 之间")
	public String getServerDeeds() {
		return serverDeeds;
	}

	public void setServerDeeds(String serverDeeds) {
		this.serverDeeds = serverDeeds;
	}
	
}