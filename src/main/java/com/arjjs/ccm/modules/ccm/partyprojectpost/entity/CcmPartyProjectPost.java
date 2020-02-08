/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.partyprojectpost.entity;

import com.arjjs.ccm.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 认领功能Entity
 * @author cby
 * @version 2019-08-15
 */
public class CcmPartyProjectPost extends DataEntity<CcmPartyProjectPost> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 数据类型
	private String proPost;		// 服务项目或岗位id
	private String orgParty;		// 认领组织或党员id
	private String more1;		// 冗余字段1
	private String more2;		// 冗余字段1
	private String more3;		// 冗余字段1
	private List<String> orgPartyList; //批量认领list
	
	public CcmPartyProjectPost() {
		super();
	}

	public CcmPartyProjectPost(String id){
		super(id);
	}

	@Length(min=0, max=1, message="数据类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="服务项目或岗位id长度必须介于 0 和 64 之间")
	public String getProPost() {
		return proPost;
	}

	public void setProPost(String proPost) {
		this.proPost = proPost;
	}
	
	@Length(min=0, max=64, message="认领组织或党员id长度必须介于 0 和 64 之间")
	public String getOrgParty() {
		return orgParty;
	}

	public void setOrgParty(String orgParty) {
		this.orgParty = orgParty;
	}
	
	@Length(min=1, max=200, message="冗余字段1长度必须介于 1 和 200 之间")
	public String getMore1() {
		return more1;
	}

	public void setMore1(String more1) {
		this.more1 = more1;
	}
	
	@Length(min=1, max=2000, message="冗余字段1长度必须介于 1 和 2000 之间")
	public String getMore2() {
		return more2;
	}

	public void setMore2(String more2) {
		this.more2 = more2;
	}
	
	@Length(min=1, max=2000, message="冗余字段1长度必须介于 1 和 2000 之间")
	public String getMore3() {
		return more3;
	}

	public void setMore3(String more3) {
		this.more3 = more3;
	}

	public List<String> getOrgPartyList() {
		return orgPartyList;
	}

	public void setOrgPartyList(List<String> orgPartyList) {
		this.orgPartyList = orgPartyList;
	}
}