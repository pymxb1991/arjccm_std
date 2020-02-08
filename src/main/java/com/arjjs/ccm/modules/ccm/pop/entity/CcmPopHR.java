/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.pop.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 户籍人口表单Entity
 * @author arj
 * @version 2017-12-27
 */
public class CcmPopHR extends DataEntity<CcmPopHR> {
	
	private static final long serialVersionUID = 1L;
	private String identity;		// 公民身份号码
	private String name;		// 姓名
	private String usedname;		// 曾用名
	private String sex;		// 性别
	private Date birthday;		// 出生日期
	private String nation;		// 民族
	private String censuregister;		// 籍贯
	private String marriage;		// 婚姻状况
	private String politics;		// 政抬面貌
	private String education;		// 学历
	private String belief;		// 宗教信仰
	private String professiontype;		// 职业类別
	private String profession;		// 职业
	private String serviceplace;		// 服务处所
	private String telephone;		// 联系方式
	private String domicile;		// 户籍地
	private String domiciledetail;		// 户籍&lt;楼&gt;详址
	private String residence;		// 现住地
	private String residencedetail;		// 现住地 &lt;楼&gt; 详址
	private String uniformlogo;		// 人户一致标识
	private String account;		// 户号
	private String accountidentity;		// 户主公民身份号码
	private String accountname;		// 户主姓名
	private String accountrelation;		// 与户主关系
	private String accounttelephone;		// 户主联系方式
	private Date beginBirthday;		// 开始 出生日期
	private Date endBirthday;		// 结束 出生日期
	
	public CcmPopHR() {
		super();
	}

	public CcmPopHR(String id){
		super(id);
	}

	@Length(min=1, max=18, message="公民身份号码长度必须介于 1 和 18 之间")
	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	@Length(min=0, max=50, message="姓名长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="曾用名长度必须介于 0 和 50 之间")
	public String getUsedname() {
		return usedname;
	}

	public void setUsedname(String usedname) {
		this.usedname = usedname;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=2, message="民族长度必须介于 0 和 2 之间")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Length(min=0, max=6, message="籍贯长度必须介于 0 和 6 之间")
	public String getCensuregister() {
		return censuregister;
	}

	public void setCensuregister(String censuregister) {
		this.censuregister = censuregister;
	}
	
	@Length(min=0, max=2, message="婚姻状况长度必须介于 0 和 2 之间")
	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	
	@Length(min=0, max=2, message="政抬面貌长度必须介于 0 和 2 之间")
	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}
	
	@Length(min=0, max=2, message="学历长度必须介于 0 和 2 之间")
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	
	@Length(min=0, max=2, message="宗教信仰长度必须介于 0 和 2 之间")
	public String getBelief() {
		return belief;
	}

	public void setBelief(String belief) {
		this.belief = belief;
	}
	
	@Length(min=0, max=5, message="职业类別长度必须介于 0 和 5 之间")
	public String getProfessiontype() {
		return professiontype;
	}

	public void setProfessiontype(String professiontype) {
		this.professiontype = professiontype;
	}
	
	@Length(min=0, max=30, message="职业长度必须介于 0 和 30 之间")
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	@Length(min=0, max=100, message="服务处所长度必须介于 0 和 100 之间")
	public String getServiceplace() {
		return serviceplace;
	}

	public void setServiceplace(String serviceplace) {
		this.serviceplace = serviceplace;
	}
	
	@Length(min=0, max=50, message="联系方式长度必须介于 0 和 50 之间")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Length(min=0, max=6, message="户籍地长度必须介于 0 和 6 之间")
	public String getDomicile() {
		return domicile;
	}

	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}
	
	@Length(min=0, max=80, message="户籍&lt;楼&gt;详址长度必须介于 0 和 80 之间")
	public String getDomiciledetail() {
		return domiciledetail;
	}

	public void setDomiciledetail(String domiciledetail) {
		this.domiciledetail = domiciledetail;
	}
	
	@Length(min=0, max=6, message="现住地长度必须介于 0 和 6 之间")
	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}
	
	@Length(min=0, max=80, message="现住地 &lt;楼&gt; 详址长度必须介于 0 和 80 之间")
	public String getResidencedetail() {
		return residencedetail;
	}

	public void setResidencedetail(String residencedetail) {
		this.residencedetail = residencedetail;
	}
	
	@Length(min=0, max=2, message="人户一致标识长度必须介于 0 和 2 之间")
	public String getUniformlogo() {
		return uniformlogo;
	}

	public void setUniformlogo(String uniformlogo) {
		this.uniformlogo = uniformlogo;
	}
	
	@Length(min=0, max=9, message="户号长度必须介于 0 和 9 之间")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@Length(min=0, max=18, message="户主公民身份号码长度必须介于 0 和 18 之间")
	public String getAccountidentity() {
		return accountidentity;
	}

	public void setAccountidentity(String accountidentity) {
		this.accountidentity = accountidentity;
	}
	
	@Length(min=0, max=50, message="户主姓名长度必须介于 0 和 50 之间")
	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	
	@Length(min=0, max=2, message="与户主关系长度必须介于 0 和 2 之间")
	public String getAccountrelation() {
		return accountrelation;
	}

	public void setAccountrelation(String accountrelation) {
		this.accountrelation = accountrelation;
	}
	
	@Length(min=0, max=50, message="户主联系方式长度必须介于 0 和 50 之间")
	public String getAccounttelephone() {
		return accounttelephone;
	}

	public void setAccounttelephone(String accounttelephone) {
		this.accounttelephone = accounttelephone;
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