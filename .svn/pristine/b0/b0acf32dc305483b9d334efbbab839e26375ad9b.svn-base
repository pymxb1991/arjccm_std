/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.entity;

import com.arjjs.ccm.modules.sys.entity.User;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 综治队伍Entity
 * @author liang
 * @version 2018-01-13
 */
public class CcmOrgTeam extends DataEntity<CcmOrgTeam> {
	
	private static final long serialVersionUID = 1L;
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
	private String status;		// 在线状态       online：在线、hide：隐身  
	private String more1;		// 冗余字段1
	private String more2;		// 冗余字段2
	private Date beginBirthday;		// 开始 出生日期
	private Date endBirthday;		// 结束 出生日期
	
	public CcmOrgTeam() {
		super();
	}

	public CcmOrgTeam(String id){
		super(id);
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
	
	@Length(min=0, max=200, message="专业特长长度必须介于 0 和200 之间")
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
	
	public Date getBeginBirthday() {
		return beginBirthday;
	}

	public void setBeginBirthday(Date beginBirthday) {
		this.beginBirthday = beginBirthday;
	}
	
	public Date getEndBirthday() {
		return endBirthday;
	}

	public void setEndBirthday(Date endBirthday) {
		this.endBirthday = endBirthday;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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