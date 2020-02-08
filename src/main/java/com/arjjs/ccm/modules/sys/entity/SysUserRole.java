/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 查询用户对应角色Entity
 * @author cby
 * @version 2019-12-03
 */
public class SysUserRole extends DataEntity<SysUserRole> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户编号
	private String roleId;		// 角色编号
	
	public SysUserRole() {
		super();
	}

	public SysUserRole(String id){
		super(id);
	}

	@Length(min=1, max=64, message="用户编号长度必须介于 1 和 64 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=1, max=64, message="角色编号长度必须介于 1 和 64 之间")
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}