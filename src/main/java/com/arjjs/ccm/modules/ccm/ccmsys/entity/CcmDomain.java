/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.ccmsys.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 下级域服务器管理Entity
 * @author pengjianqiang
 * @version 2018-05-09
 */
public class CcmDomain extends DataEntity<CcmDomain> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 服务器名称
	private String code;		// 编码
	private String ip;		// IP
	private String url;		// 服务器接口地址
	private String username;		// 用户名
	private String password;		// 密码
	private String upperAreaCode;		// 上级区域编码（本系统）
	private String lowerAreaCode;		// 下级区域编码
	
	public CcmDomain() {
		super();
	}

	public CcmDomain(String id){
		super(id);
	}

	@Length(min=0, max=64, message="服务器名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="编码长度必须介于 0 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=40, message="IP长度必须介于 0 和 40 之间")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Length(min=0, max=64, message="服务器接口地址长度必须介于 0 和 64 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=0, max=64, message="用户名长度必须介于 0 和 64 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=0, max=64, message="密码长度必须介于 0 和 64 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=64, message="上级区域编码（本系统）长度必须介于 0 和 64 之间")
	public String getUpperAreaCode() {
		return upperAreaCode;
	}

	public void setUpperAreaCode(String upperAreaCode) {
		this.upperAreaCode = upperAreaCode;
	}
	
	@Length(min=0, max=64, message="下级区域编码长度必须介于 0 和 64 之间")
	public String getLowerAreaCode() {
		return lowerAreaCode;
	}

	public void setLowerAreaCode(String lowerAreaCode) {
		this.lowerAreaCode = lowerAreaCode;
	}
	
}