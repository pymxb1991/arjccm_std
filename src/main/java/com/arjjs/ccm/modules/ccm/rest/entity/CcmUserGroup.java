/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.rest.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 用户好友分组Entity
 * @author fu
 * @version 2018-06-12
 */
public class CcmUserGroup extends DataEntity<CcmUserGroup> {
	
	private static final long serialVersionUID = 1L;
	private String groupname;		// 分组名称
	private String avatar;		// 群组头像
	private List<CcmRestChatUser> list;	//群组下用户
	private String type;	//用于满足app插件格式要求
	private String userList;
	private String groupOwnerId;//群主id
	
	
	public String getGroupOwnerId() {
		return groupOwnerId;
	}

	public void setGroupOwnerId(String groupOwnerId) {
		this.groupOwnerId = groupOwnerId;
	}

	public String getUserList() {
		return userList;
	}

	public void setUserList(String userList) {
		this.userList = userList;
	}

	public CcmUserGroup() {
		super();
	}

	public CcmUserGroup(String id){
		super(id);
	}

	@Length(min=1, max=64, message="分组名称长度必须介于 1 和 64 之间")
	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	
	@Length(min=1, max=256, message="群组头像长度必须介于 1 和 256 之间")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<CcmRestChatUser> getList() {
		return list;
	}


	public void setList(List<CcmRestChatUser> list) {
		this.list = list;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
}