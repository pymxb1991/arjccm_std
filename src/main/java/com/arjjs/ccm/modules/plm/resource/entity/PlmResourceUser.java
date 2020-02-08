/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.resource.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.TreeEntity;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 资源权限Entity
 * @author liucong
 * @version 2018-07-20
 */
public class PlmResourceUser extends TreeEntity<PlmResourceUser> {
	
	private static final long serialVersionUID = 1L;
	private User user;		
	private String uId;	// 被分享人id
	private PlmResource resource;		
	private String rId;	// 资源表id
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	
	public PlmResourceUser() {
		super();
	}

	public PlmResourceUser(String id){
		super(id);
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
	}

	public PlmResource getResource() {
		return resource;
	}

	public void setResource(PlmResource resource) {
		this.resource = resource;
	}

	@Length(min=0, max=256, message="扩展1长度必须介于 0 和 256 之间")
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	@Length(min=0, max=256, message="扩展2长度必须介于 0 和 256 之间")
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	@Override
	public PlmResourceUser getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParent(PlmResourceUser parent) {
		// TODO Auto-generated method stub
		
	}
	
}