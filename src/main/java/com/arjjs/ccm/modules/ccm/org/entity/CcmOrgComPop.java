/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 公共机构人员Entity
 * @author liang
 * @version 2018-05-11
 */
public class CcmOrgComPop extends DataEntity<CcmOrgComPop> {
	
	private static final long serialVersionUID = 1L;
	private CcmOrgCommonality commonalityId;		// 所属机构
	private String code;		// 编号
	private String name;		// 姓名
	private String sex;		// 性别
	private String nation;		// 民族
	private String politics;		// 政治面貌
	private String idenCode;		// 证件代码
	private String idenNum;		// 证件号码
	private Date birthday;		// 出生日期
	private String service;		// 职务
	private String profExpertise;		// 专业特长
	private String education;		// 学历
	private String telephone;		// 手机号码
	private String fixTel;		// 固定电话
	private String images;		// 照片
	private Date beginBirthday;		// 开始 出生日期
	private Date endBirthday;		// 结束 出生日期
	
	public CcmOrgComPop() {
		super();
	}

	public CcmOrgComPop(String id){
		super(id);
	}

	public CcmOrgCommonality getCommonalityId() {
		return commonalityId;
	}

	public void setCommonalityId(CcmOrgCommonality commonalityId) {
		this.commonalityId = commonalityId;
	}
	
	@Length(min=0, max=64, message="编号长度必须介于 0 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=64, message="姓名长度必须介于 0 和 64 之间")
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
	
	@Length(min=0, max=80, message="专业特长长度必须介于 0 和 80 之间")
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
	
	@Length(min=0, max=64, message="手机号码长度必须介于 0 和 64 之间")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Length(min=0, max=64, message="固定电话长度必须介于 0 和 64 之间")
	public String getFixTel() {
		return fixTel;
	}

	public void setFixTel(String fixTel) {
		this.fixTel = fixTel;
	}
	
	@Length(min=0, max=255, message="照片长度必须介于 0 和 255 之间")
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
		
}