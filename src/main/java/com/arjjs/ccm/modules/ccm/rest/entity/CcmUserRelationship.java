/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.rest.entity;

import com.arjjs.ccm.modules.sys.entity.User;

import java.util.List;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 用户关系Entity
 * @author fu
 * @version 2018-03-08
 */
public class CcmUserRelationship extends DataEntity<CcmUserRelationship> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 用户A
	private String groupId;		// 分组id
	private String relationType;		// 用户AB的关系
	
	private CcmUserGroup userGroup;		// 分组
	
	private List<String> userIdList;
	
	public CcmUserRelationship() {
		super();
	}
	public CcmUserRelationship(String id){
		super(id);
	}

	
	
	
	public CcmUserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(CcmUserGroup userGroup) {
		this.userGroup = userGroup;
	}
	@Length(min=1, max=64, message="分组id长度必须介于 1 和 64 之间")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@Length(min=0, max=2, message="用户AB的关系长度必须介于 0 和 2 之间")
	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<String> getUserIdList() {
		return userIdList;
	}
	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}

	
}