/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 维保单位Entity
 * @author fu
 * @version 2018-07-02
 */
public class PlmCarRepair extends DataEntity<PlmCarRepair> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 单位名称
	private String num;		// 单位编号
	private String addr;		// 单位地址
	private String tel;		// 单位电话
	private String leader;		// 负责人
	private String phone;		// 负责人手机
	private String files;		// 合同附件
	private String photo;		//照片
	
	public PlmCarRepair() {
		super();
	}

	public PlmCarRepair(String id){
		super(id);
	}

	@Length(min=0, max=64, message="单位名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="单位编号长度必须介于 0 和 20 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Length(min=0, max=100, message="单位地址长度必须介于 0 和 100 之间")
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@Length(min=0, max=20, message="单位电话长度必须介于 0 和 20 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=64, message="负责人长度必须介于 0 和 64 之间")
	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}
	
	@Length(min=0, max=11, message="负责人手机长度必须介于 0 和 11 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=1000, message="合同附件长度必须介于 0 和 1000 之间")
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}