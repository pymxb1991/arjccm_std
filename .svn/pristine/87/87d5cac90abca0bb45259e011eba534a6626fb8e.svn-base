/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.cms.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;
import com.arjjs.ccm.modules.sys.entity.Area;

/**
 * 居民用户管理Entity
 * @author liuxue
 * @version 2018-10-15
 */
public class CcmFontUser extends DataEntity<CcmFontUser> {
	
	private static final long serialVersionUID = 1L;
	private String loginName;		// 登录名
	private String password;		// 密码
	private String no;		// 身份证号
	private String name;		// 姓名
	private String email;		// 邮箱
	private String phone;		// 电话
	private String mobile;		// 手机
	private String photo;		// 用户头像
	private String loginIp;		// 最后登陆IP
	private Date loginDate;		// 最后登陆时间
	private String loginFlag;		// 审核状态
	private String newPassword;  //新密码
	private String confirmNewPassword; //再次新密码
	private Area areaComId;		// 所属社区ID
	private String isNameVisable;      //姓名是否可见
	private String isNoVisable;      //身份证号是否可见
	private String isEmailVisable;      //邮箱是否可见
	private String isMobileVisable;    //手机号码是否可见
	private String loginType;    //在线状态
	private String updateStatus;    //修改状态   0：个人信息  1：修改密码
	private String officeId;  //所属部门id
	
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String newPassword) {
		this.loginType = loginType;
	}
	
	public CcmFontUser() {
		super();
	}

	public CcmFontUser(String id){
		super(id);
	}
 
	
	
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Length(min=1, max=100, message="登录名长度必须介于 1 和 100 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=1, max=100, message="密码长度必须介于 1 和 100 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=100, message="身份证号长度必须介于 0 和 100 之间")
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	@Length(min=1, max=100, message="姓名长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=200, message="邮箱长度必须介于 0 和 200 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=200, message="电话长度必须介于 0 和 200 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=200, message="手机长度必须介于 0 和 200 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=1000, message="用户头像长度必须介于 0 和 1000 之间")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Length(min=0, max=100, message="最后登陆IP长度必须介于 0 和 100 之间")
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
	@Length(min=0, max=64, message="审核状态长度必须介于 0 和 64 之间")
	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}
	
	
	public Area getAreaComId() {
		return areaComId;
	}

	public void setAreaComId(Area areaComId) {
		this.areaComId = areaComId;
	}

	public String getIsNameVisable() {
		return isNameVisable;
	}

	public void setIsNameVisable(String isNameVisable) {
		this.isNameVisable = isNameVisable;
	}

	public String getIsNoVisable() {
		return isNoVisable;
	}

	public void setIsNoVisable(String isNoVisable) {
		this.isNoVisable = isNoVisable;
	}

	public String getIsEmailVisable() {
		return isEmailVisable;
	}

	public void setIsEmailVisable(String isEmailVisable) {
		this.isEmailVisable = isEmailVisable;
	}

	public String getIsMobileVisable() {
		return isMobileVisable;
	}

	public void setIsMobileVisable(String isMobileVisable) {
		this.isMobileVisable = isMobileVisable;
	}
	
	
}