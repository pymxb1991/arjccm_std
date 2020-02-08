/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 驾驶员Entity
 * @author fu
 * @version 2018-06-30
 */
public class PlmCarDriver extends DataEntity<PlmCarDriver> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String photo;		//照片
	private String status;		// 状态
	private String sex;		// 性别
	private String didType;		// 准驾车型
	private Date birthday;		// 出生日期
	private Date dexpDate;		// 领证日期
	private String didNum;		// 驾驶证号
	private Date didAvldate;		// 驾驶证有效期
	private Date didValdate;		// 年审时间
	private String tel;		// 电话
	private String phone;		// 手机
	private String licensePic;		//驾照照片
	
	public PlmCarDriver() {
		super();
	}

	public PlmCarDriver(String id){
		super(id);
	}

	@Length(min=0, max=64, message="姓名长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=2, message="准驾车型长度必须介于 0 和 2 之间")
	public String getDidType() {
		return didType;
	}

	public void setDidType(String didType) {
		this.didType = didType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDexpDate() {
		return dexpDate;
	}

	public void setDexpDate(Date dexpDate) {
		this.dexpDate = dexpDate;
	}
	
	@Length(min=0, max=20, message="驾驶证号长度必须介于 0 和 20 之间")
	public String getDidNum() {
		return didNum;
	}

	public void setDidNum(String didNum) {
		this.didNum = didNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDidAvldate() {
		return didAvldate;
	}

	public void setDidAvldate(Date didAvldate) {
		this.didAvldate = didAvldate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDidValdate() {
		return didValdate;
	}

	public void setDidValdate(Date didValdate) {
		this.didValdate = didValdate;
	}
	
	@Length(min=0, max=20, message="电话长度必须介于 0 和 20 之间")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Length(min=0, max=11, message="手机长度必须介于 0 和 11 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getLicensePic() {
		return licensePic;
	}

	public void setLicensePic(String licensePic) {
		this.licensePic = licensePic;
	}
	
}