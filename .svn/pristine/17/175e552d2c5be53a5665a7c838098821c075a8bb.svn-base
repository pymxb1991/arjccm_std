/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.work.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 联系人Entity
 * @author liang
 * @version 2018-06-12
 */
public class CcmWorkRelation extends DataEntity<CcmWorkRelation> {
	
	private static final long serialVersionUID = 1L;
	private String department;		// 单位
	private String name;		// 姓名
	private String tel;		// 电话
	private String mail;		// 邮件
	private String adds;		// 家庭住址
	
	public CcmWorkRelation() {
		super();
	}

	public CcmWorkRelation(String id){
		super(id);
	}

	@Length(min=0, max=100, message="单位长度必须介于 0 和 100 之间")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Length(min=0, max=32, message="姓名长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="电话长度必须介于 0 和 64 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=64, message="邮件长度必须介于 0 和 64 之间")
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@Length(min=0, max=128, message="家庭住址长度必须介于 0 和 128 之间")
	public String getAdds() {
		return adds;
	}

	public void setAdds(String adds) {
		this.adds = adds;
	}
	
}