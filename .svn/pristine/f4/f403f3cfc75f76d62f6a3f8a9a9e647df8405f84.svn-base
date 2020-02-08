/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 在逃人员Entity
 * 
 * @author 李彩云
 * @version 2018-09-19
 */
public class CcmHouseEscape extends DataEntity<CcmHouseEscape> {

	private static final long serialVersionUID = 1L;
	private String peopleId; // 实有人口（id）
	private String nickName; // 别名/绰号/曾用名
	private String height; // 身高
	private String dialect; // 口音
	private String bodyApperanceFeature; // 体貌特征
	private String atteType; // 关注程度
	private String specialMark; // 特殊标记
	private Date caseOccurDate; // 案发日期
	private String caseType; // 案件类型
	private String caseId; // 案件编号
	private String establishDepartment; // 立案部门
	private Date establishDate; // 立案日期
	private Date escapeDate; // 逃跑日期
	private String escapePeopleId; // 在逃人员编号
	private String escapeType; // 在逃类型
	private String legalInstruemnt; // 法律文书
	private String orderForArrest; // 通缉令
	private String orderForArrestId; // 通缉编号
	private Integer crimPast;		// 有无犯罪史
	private String crimeHistory;		// 犯罪史详情
	// 实有人口
	private String type; // 人口类型
	private String name; // 姓名
	private String censu; // 籍贯
	private String sex; // 性别
	private String ident; // 公民身份号码

	private String domiciledetail; // 户籍门（楼）详址
	private String residencedetail; // 现住门（楼）详址
	private String images; // 图片
	private Date birthday; // 出生日期
	private User checkUser; // 拦截器中使用该用户进行权限拦截，App的rest接口使用
	
	@ExcelField(title="犯罪史详情", align=2, sort=21)
	@Length(min=0, max=1024, message="犯罪史详情长度必须介于 0 和 1024 之间")
	public String getCrimeHistory() {
		return crimeHistory;
	}

	public void setCrimeHistory(String crimeHistory) {
		this.crimeHistory = crimeHistory;
	}
	
	@ExcelField(title="有无犯罪史", align=2, sort=13,dictType="yes_no")
	public Integer getCrimPast() {
		return crimPast;
	}

	public void setCrimPast(Integer crimPast) {
		this.crimPast = crimPast;
	}

	public String getDomiciledetail() {
		return domiciledetail;
	}

	public void setDomiciledetail(String domiciledetail) {
		this.domiciledetail = domiciledetail;
	}

	public String getResidencedetail() {
		return residencedetail;
	}

	public void setResidencedetail(String residencedetail) {
		this.residencedetail = residencedetail;
	}
	@ExcelField(title="籍贯", align=2, sort=3)
	public String getCensu() {
		return censu;
	}

	public void setCensu(String censu) {
		this.censu = censu;
	}
	@ExcelField(title="人口类型", align=2, sort=2,dictType="sys_ccm_people")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@ExcelField(title="姓名", align=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ExcelField(title="性别", align=2, sort=17,dictType="sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@ExcelField(title="公民身份号码", align=2, sort=18)
	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	public User getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}
	public CcmHouseEscape() {
		super();
	}

	public CcmHouseEscape(String id) {
		super(id);
	}
	
	@Length(min = 1, max = 64, message = "实有人口（id）长度必须介于 1 和 64 之间")
	public String getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}
	
	@ExcelField(title="别名/绰号/曾用名", align=2, sort=5)
	@Length(min = 0, max = 255, message = "别名/绰号/曾用名长度必须介于 0 和 255 之间")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@ExcelField(title="身高", align=2, sort=4)
	@Length(min = 0, max = 64, message = "身高长度必须介于 0 和 64 之间")
	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	@ExcelField(title="口音", align=2, sort=5)
	@Length(min = 0, max = 255, message = "口音长度必须介于 0 和 255 之间")
	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	@ExcelField(title="体貌特征", align=2, sort=10)
	@Length(min = 0, max = 255, message = "体貌特征长度必须介于 0 和 255 之间")
	public String getBodyApperanceFeature() {
		return bodyApperanceFeature;
	}

	public void setBodyApperanceFeature(String bodyApperanceFeature) {
		this.bodyApperanceFeature = bodyApperanceFeature;
	}

	@ExcelField(title="特殊标记", align=2, sort=11)
	@Length(min = 0, max = 255, message = "特殊标记长度必须介于 0 和 255 之间")
	public String getSpecialMark() {
		return specialMark;
	}

	public void setSpecialMark(String specialMark) {
		this.specialMark = specialMark;
	}

	@ExcelField(title="案发日期", align=2, sort=12)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCaseOccurDate() {
		return caseOccurDate;
	}

	public void setCaseOccurDate(Date caseOccurDate) {
		this.caseOccurDate = caseOccurDate;
	}

	@ExcelField(title="案件类型", align=2, sort=13)
	@Length(min = 0, max = 255, message = "案件类型长度必须介于 0 和 255 之间")
	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	@ExcelField(title="案件编号", align=2, sort=14)
	@Length(min = 0, max = 64, message = "案件编号长度必须介于 0 和 64 之间")
	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	@ExcelField(title="立案部门", align=2, sort=15)
	@Length(min = 0, max = 255, message = "立案部门长度必须介于 0 和 255 之间")
	public String getEstablishDepartment() {
		return establishDepartment;
	}

	public void setEstablishDepartment(String establishDepartment) {
		this.establishDepartment = establishDepartment;
	}

	@ExcelField(title="立案日期", align=2, sort=16)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEstablishDate() {
		return establishDate;
	}

	public void setEstablishDate(Date establishDate) {
		this.establishDate = establishDate;
	}

	@ExcelField(title="逃跑日期", align=2, sort=17)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEscapeDate() {
		return escapeDate;
	}

	public void setEscapeDate(Date escapeDate) {
		this.escapeDate = escapeDate;
	}

	@ExcelField(title="在逃人员编号", align=2, sort=18)
	@Length(min = 0, max = 64, message = "在逃人员编号长度必须介于 0 和 64 之间")
	public String getEscapePeopleId() {
		return escapePeopleId;
	}

	public void setEscapePeopleId(String escapePeopleId) {
		this.escapePeopleId = escapePeopleId;
	}

	@ExcelField(title="在逃类型", align=2, sort=19)
	@Length(min = 0, max = 255, message = "在逃类型长度必须介于 0 和 255 之间")
	public String getEscapeType() {
		return escapeType;
	}

	public void setEscapeType(String escapeType) {
		this.escapeType = escapeType;
	}

	@Length(min = 0, max = 255, message = "法律文书长度必须介于 0 和 255 之间")
	public String getLegalInstruemnt() {
		return legalInstruemnt;
	}

	public void setLegalInstruemnt(String legalInstruemnt) {
		this.legalInstruemnt = legalInstruemnt;
	}

	@Length(min = 0, max = 255, message = "通缉令长度必须介于 0 和 255 之间")
	public String getOrderForArrest() {
		return orderForArrest;
	}

	public void setOrderForArrest(String orderForArrest) {
		this.orderForArrest = orderForArrest;
	}

	@Length(min = 0, max = 64, message = "通缉编号长度必须介于 0 和 64 之间")
	public String getOrderForArrestId() {
		return orderForArrestId;
	}

	public void setOrderForArrestId(String orderForArrestId) {
		this.orderForArrestId = orderForArrestId;
	}

	@ExcelField(title = "关注程度", align = 2, sort = 20, dictType = "ccm_conc_exte")
	@Length(min = 0, max = 2, message = "关注程度长度必须介于 0 和 2 之间")
	public String getAtteType() {
		return atteType;
	}

	public void setAtteType(String atteType) {
		this.atteType = atteType;
	}

}