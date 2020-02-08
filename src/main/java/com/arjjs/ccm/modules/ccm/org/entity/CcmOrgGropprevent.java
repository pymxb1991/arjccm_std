/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 群防群治队伍Entity
 * @author liang
 * @version 2018-01-12
 */
public class CcmOrgGropprevent extends DataEntity<CcmOrgGropprevent> {
	
	private static final long serialVersionUID = 1L;
	private CcmOrgOrgprevent orgpreventId;		// 组织名称
	private String name;		// 姓名
	private String sex;		// 性别
	private String nation;		// 民族
	private String politics;		// 政治面貌
	private String idenCode;		// 证件代码
	private String idenNum;		// 证件号码
	private Date birthday;		// 出生日期
	private String service;		// 职务
	private String profExpertise;		// 专业特长
	private String[] profExpertises;		// 专业特长数组
	private String education;		// 学历
	private String telephone;		// 手机号码
	private String fixTel;		// 固定电话
	private String images;		// 图片
	private Date beginBirthday;		// 开始 出生日期
	private Date endBirthday;		// 结束 出生日期
	private String age;		// 年龄
	private String health;		// 健康情况
	private String duty;		// 现任职务
	
	public CcmOrgGropprevent() {
		super();
	}

	public CcmOrgGropprevent(String id){
		super(id);
	}

	@JsonBackReference
	public CcmOrgOrgprevent getOrgpreventId() {
		return orgpreventId;
	}

	public void setOrgpreventId(CcmOrgOrgprevent orgpreventId) {
		this.orgpreventId = orgpreventId;
	}
	
	@Length(min=0, max=50, message="姓名长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	@Length(min=0, max=3, message="证件代码长度必须介于 0 和 3 之间")
	public String getIdenCode() {
		return idenCode;
	}

	public void setIdenCode(String idenCode) {
		this.idenCode = idenCode;
	}
	
	@Length(min=0, max=30, message="证件号码长度必须介于 0 和 30 之间")
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
	
	@Length(min=0, max=30, message="职务长度必须介于 0 和 30 之间")
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
	
	@Length(min=0, max=18, message="手机号码长度必须介于 0 和 18 之间")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Length(min=0, max=18, message="固定电话长度必须介于 0 和 18 之间")
	public String getFixTel() {
		return fixTel;
	}

	public void setFixTel(String fixTel) {
		this.fixTel = fixTel;
	}
	
	@Length(min=0, max=255, message="图片长度必须介于 0 和 255 之间")
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
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
	
	@Length(min=1, max=255, message="年龄长度必须介于 1 和 255 之间")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@Length(min=1, max=255, message="健康情况长度必须介于 1 和 255 之间")
	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}
	
	@Length(min=1, max=255, message="现任职务长度必须介于 1 和 255 之间")
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}
		
}