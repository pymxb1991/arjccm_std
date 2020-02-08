/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 案事件干系人Entity
 * @author pengjianqiang
 * @version 2018-01-30
 */
public class CcmEventStakeholder extends DataEntity<CcmEventStakeholder> {
	
	private static final long serialVersionUID = 1L;
	private String incidentId;		// 所属事件
	private String preventType;		// 干系人类型
	private String name;		// 姓名
	private String usedname;		// 曾用名
	private String idenCode;		// 证件代码
	private String idenNum;		// 证件号码
	private String sex;		// 性别
	private Date birthday;		// 出生日期
	private String nationality;		// 国籍（地区）
	private String nation;		// 民族
	private String censu;		// 籍贯
	private String marriage;		// 婚姻状况
	private String politics;		// 政治面貌
	private String education;		// 学历
	private String belief;		// 宗教信仰
	private String profType;		// 职业类别
	private String profession;		// 职业
	private String servPlace;		// 服务处所
	private String domicile;		// 户籍地
	private String domiciledetail;		// 户籍门（楼）详址
	private String residence;		// 现住地
	private String residencedetail;		// 现住门（楼）详址
	private String isPsychogeny;		// 是否为严重精神障碍患者
	private String isNonage;		// 是否为未成年人
	private String isKym;		// 是否为青少年
	private String more1;		// 冗余字段1
	private String more2;		// 冗余字段2
	private String more3;		// 冗余字段3
	private String incidentName;		// 所属事件名称
	
	public String getIncidentName() {
		return incidentName;
	}

	public void setIncidentName(String incidentName) {
		this.incidentName = incidentName;
	}

	
	public CcmEventStakeholder() {
		super();
	}

	public CcmEventStakeholder(String id){
		super(id);
	}

	@Length(min=0, max=64, message="所属事件长度必须介于 0 和 64 之间")
	public String getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}
	
	@Length(min=0, max=2, message="干系人类型长度必须介于 0 和 2 之间")
	public String getPreventType() {
		return preventType;
	}

	public void setPreventType(String preventType) {
		this.preventType = preventType;
	}
	
	@Length(min=0, max=80, message="姓名长度必须介于 0 和 80 之间")
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
	
	@Length(min=0, max=3, message="国籍（地区）长度必须介于 0 和 3 之间")
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	@Length(min=0, max=2, message="民族长度必须介于 0 和 2 之间")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Length(min=0, max=6, message="籍贯长度必须介于 0 和 6 之间")
	public String getCensu() {
		return censu;
	}

	public void setCensu(String censu) {
		this.censu = censu;
	}
	
	@Length(min=0, max=2, message="婚姻状况长度必须介于 0 和 2 之间")
	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	
	@Length(min=0, max=2, message="政治面貌长度必须介于 0 和 2 之间")
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
	
	@Length(min=0, max=5, message="职业类别长度必须介于 0 和 5 之间")
	public String getProfType() {
		return profType;
	}

	public void setProfType(String profType) {
		this.profType = profType;
	}
	
	@Length(min=0, max=30, message="职业长度必须介于 0 和 30 之间")
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	@Length(min=0, max=100, message="服务处所长度必须介于 0 和 100 之间")
	public String getServPlace() {
		return servPlace;
	}

	public void setServPlace(String servPlace) {
		this.servPlace = servPlace;
	}
	
	@Length(min=0, max=6, message="户籍地长度必须介于 0 和 6 之间")
	public String getDomicile() {
		return domicile;
	}

	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}
	
	@Length(min=0, max=80, message="户籍门（楼）详址长度必须介于 0 和 80 之间")
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
	
	@Length(min=0, max=80, message="现住门（楼）详址长度必须介于 0 和 80 之间")
	public String getResidencedetail() {
		return residencedetail;
	}

	public void setResidencedetail(String residencedetail) {
		this.residencedetail = residencedetail;
	}
	
	@Length(min=0, max=1, message="是否为严重精神障碍患者长度必须介于 0 和 1 之间")
	public String getIsPsychogeny() {
		return isPsychogeny;
	}

	public void setIsPsychogeny(String isPsychogeny) {
		this.isPsychogeny = isPsychogeny;
	}
	
	@Length(min=0, max=1, message="是否为未成年人长度必须介于 0 和 1 之间")
	public String getIsNonage() {
		return isNonage;
	}

	public void setIsNonage(String isNonage) {
		this.isNonage = isNonage;
	}
	
	@Length(min=0, max=1, message="是否为青少年长度必须介于 0 和 1 之间")
	public String getIsKym() {
		return isKym;
	}

	public void setIsKym(String isKym) {
		this.isKym = isKym;
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
	
	@Length(min=0, max=100, message="冗余字段3长度必须介于 0 和 100 之间")
	public String getMore3() {
		return more3;
	}

	public void setMore3(String more3) {
		this.more3 = more3;
	}
	
}