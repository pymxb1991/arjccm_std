/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.view.entity;

import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmMobileDevice;
import com.arjjs.ccm.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.arjjs.ccm.modules.sys.entity.User;

import com.arjjs.ccm.common.persistence.TreeEntity;

/**
 * 综治队伍Entity
 * @author liang
 * @version 2018-01-12
 */
public class VCcmTeam extends TreeEntity<VCcmTeam> {
	
	private static final long serialVersionUID = 1L;
	private Office companyId;		// 归属机构
	private Office office;		// 归属部门
	private String loginName;		// 登录名
	private String password;		// 密码
	private String no;		// 工号
	private String name;		// 姓名
	private String email;		// 邮箱
	private String phone;		// 电话
	private String mobile;		// 手机
	private String userType;		// 用户类型
	private String photo;		// 用户头像
	private String loginIp;		// 最后登陆IP
	private Date loginDate;		// 最后登陆时间
	private String loginFlag;		// 是否可登录
	private User user;		// 人员ID
	private String sex;		// 性别
	private String nation;		// 民族
	private String politics;		// 政治面貌
	private String idenNum;		// 公民身份号码
	private Date birthday;		// 出生日期
	private String grade;		// 级别
	private String service;		// 职务
	private String profExpertise;		// 专业特长
	private String[] profExpertises;		// 专业特长数组
	private String education;		// 学历
	private String fixTel;		// 其他联系方式
	private String teamType;		// 人员类型
	private String more1;		// 冗余字段1
	private String more2;		// 冗余字段2
	private String status;		// 在线状态       online：在线、hide：隐身  
	private String groupId;		// 所在群组  
	private String count;
	private CcmMobileDevice ccmMobileDevice;//设备
	
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public CcmMobileDevice getCcmMobileDevice() {
		return ccmMobileDevice;
	}

	public void setCcmMobileDevice(CcmMobileDevice ccmMobileDevice) {
		this.ccmMobileDevice = ccmMobileDevice;
	}

	public VCcmTeam() {
		super();
	}

	public VCcmTeam(String id){
		super(id);
	}

	@NotNull(message="归属机构不能为空")
	public Office getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Office companyId) {
		this.companyId = companyId;
	}
	
	@NotNull(message="归属部门不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
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
	
	@Length(min=0, max=100, message="工号长度必须介于 0 和 100 之间")
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
	
	@Length(min=0, max=1, message="用户类型长度必须介于 0 和 1 之间")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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
	
	@Length(min=0, max=64, message="是否可登录长度必须介于 0 和 64 之间")
	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=2, message="民族长度必须介于 0 和 2 之间")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Length(min=0, max=2, message="政治面貌长度必须介于 0 和 2 之间")
	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}
	
	@Length(min=0, max=30, message="公民身份号码长度必须介于 0 和 30 之间")
	public String getIdenNum() {
		return idenNum;
	}

	public void setIdenNum(String idenNum) {
		this.idenNum = idenNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=3, message="级别长度必须介于 0 和 3 之间")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Length(min=0, max=2, message="职务长度必须介于 0 和 2 之间")
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
	
	@Length(min=0, max=200, message="专业特长长度必须介于 0 和 200 之间")
	public String getProfExpertise() {
		return profExpertise;
	}

	public void setProfExpertise(String profExpertise) {
		this.profExpertise = profExpertise;
	}
	
	@Length(min=0, max=2, message="学历长度必须介于 0 和 2 之间")
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	
	@Length(min=0, max=18, message="其他联系方式长度必须介于 0 和 18 之间")
	public String getFixTel() {
		return fixTel;
	}

	public void setFixTel(String fixTel) {
		this.fixTel = fixTel;
	}
	
	@Length(min=0, max=2, message="人员类型长度必须介于 0 和 2 之间")
	public String getTeamType() {
		return teamType;
	}

	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}
	
	@Length(min=0, max=100, message="冗余字段1长度必须介于 0 和 100 之间")
	public String getMore1() {
		return more1;
	}

	public void setMore1(String more1) {
		this.more1 = more1;
	}
	
	@Length(min=0, max=100, message="冗余字段2长度必须介于 0 和 100 之间")
	public String getMore2() {
		return more2;
	}

	public void setMore2(String more2) {
		this.more2 = more2;
	}

	@Override
	public VCcmTeam getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParent(VCcmTeam parent) {
		// TODO Auto-generated method stub
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String[] getProfExpertises() {
		return profExpertises;
	}

	public void setProfExpertises(String[] profExpertises) {
		this.profExpertises = profExpertises;
	}

	public List<String> getProfExpertiseList() {
		List<String> list = Lists.newArrayList();
		if (profExpertise != null){
			for (String s : StringUtils.split(profExpertise, ",")) {
				list.add(s);
			}
		}
		return list;
	}
	public void setProfExpertiseList(List<String> list) {
		profExpertise = ","+StringUtils.join(list, ",")+",";
	}
	
}