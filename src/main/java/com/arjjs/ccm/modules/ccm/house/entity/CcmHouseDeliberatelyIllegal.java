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
 * 故意犯法Entity
 * @author liuxue
 * @version 2018-09-27
 */
public class CcmHouseDeliberatelyIllegal extends DataEntity<CcmHouseDeliberatelyIllegal> {
	
	private static final long serialVersionUID = 1L;
	private String peopleId;		// 实有人口（id）
	private String convictCrimes;		// 所判罪行
	private String convictResult;		// 审判结果
	private String superviseStatus;		// 监管状态（列管，撤管）
	private String atteType;		// 关注程度
	private String helpCase;		// 帮扶情况
	private String helpName;		// 帮扶人姓名
	private String helpTl;		// 帮扶人联系方式
	private String illegalArea;		// 违法地区
	private Date illegalDate;		// 违法时间
	private Date acquittalDate;		// 释放时间
	private String transformInfo;		// 改造情况
	private String illegalDescription;		// 违法原因
	private String isRecidivism;		// 是否累犯（有、否）
	private String reviewType;		// 回访情况
	private String crimeHistory;		// 犯罪史详情
	private String more1;		// 冗余1
	private String more2;		// 冗余2
	
	private String atteTypeLable;	//app接口使用
	//实有人口
	private String type;		// 人口类型
	private String name;        // 姓名
	private String censu;		// 籍贯
	private String sex;		// 性别
	private String ident;		// 公民身份号码
	private String telephone;	 // 联系方式
	
	private String domiciledetail;		// 户籍门（楼）详址
	private String residencedetail;		// 现住门（楼）详址
	private String images;		// 图片
	private Date birthday;	//出生日期
	private String comName; 	//app接口使用，所属社区
	private User checkUser;		// 拦截器中使用该用户进行权限拦截，App的rest接口使用
	
	
	public CcmHouseDeliberatelyIllegal() {
		super();
	}

	public CcmHouseDeliberatelyIllegal(String id){
		super(id);
	}
    
	
	
	
	public String getAtteTypeLable() {
		return atteTypeLable;
	}

	public void setAtteTypeLable(String atteTypeLable) {
		this.atteTypeLable = atteTypeLable;
	}
	@ExcelField(title="人口类型", align=2, sort=2,dictType="sys_ccm_people")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@ExcelField(title="姓名", align=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@ExcelField(title="籍贯", align=2, sort=3)
	public String getCensu() {
		return censu;
	}

	public void setCensu(String censu) {
		this.censu = censu;
	}
	@ExcelField(title="性别", align=2, sort=20,dictType="sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	@ExcelField(title="公民身份号码", align=2, sort=21)
	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}
	@ExcelField(title="联系方式", align=2, sort=22)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@ExcelField(title="户籍门（楼）详址", align=2, sort=4)
	public String getDomiciledetail() {
		return domiciledetail;
	}

	public void setDomiciledetail(String domiciledetail) {
		this.domiciledetail = domiciledetail;
	}
	@ExcelField(title="现住门（楼）详址", align=2, sort=5)
	public String getResidencedetail() {
		return residencedetail;
	}

	public void setResidencedetail(String residencedetail) {
		this.residencedetail = residencedetail;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public User getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}
	
	//@ExcelField(title="实有人口（id）", align=2, sort=24)
	@Length(min=0, max=64, message="实有人口（id）长度必须介于 0 和 64 之间")
	public String getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}
	@ExcelField(title="所判罪行", align=2, sort=6)
	@Length(min=0, max=50, message="所判罪行长度必须介于 0 和 50 之间")
	public String getConvictCrimes() {
		return convictCrimes;
	}

	public void setConvictCrimes(String convictCrimes) {
		this.convictCrimes = convictCrimes;
	}
	@ExcelField(title="审判结果", align=2, sort=7)
	@Length(min=0, max=50, message="审判结果长度必须介于 0 和 50 之间")
	public String getConvictResult() {
		return convictResult;
	}

	public void setConvictResult(String convictResult) {
		this.convictResult = convictResult;
	}
	@ExcelField(title="监管状态", align=2, sort=8,dictType="house_supervise_status")
	@Length(min=0, max=2, message="监管状态（列管，撤管）长度必须介于 0 和 2 之间")
	public String getSuperviseStatus() {
		return superviseStatus;
	}

	public void setSuperviseStatus(String superviseStatus) {
		this.superviseStatus = superviseStatus;
	}
	@ExcelField(title="关注程度", align=2, sort=23,dictType="ccm_conc_exte")
	@Length(min=0, max=2, message="关注程度长度必须介于 0 和 2 之间")
	public String getAtteType() {
		return atteType;
	}

	public void setAtteType(String atteType) {
		this.atteType = atteType;
	}
	@ExcelField(title="帮扶情况", align=2, sort=9)
	@Length(min=0, max=1024, message="帮扶情况长度必须介于 0 和 1024 之间")
	public String getHelpCase() {
		return helpCase;
	}

	public void setHelpCase(String helpCase) {
		this.helpCase = helpCase;
	}
	@ExcelField(title="帮扶人姓名", align=2, sort=10)
	@Length(min=0, max=50, message="帮扶人姓名长度必须介于 0 和 50 之间")
	public String getHelpName() {
		return helpName;
	}

	public void setHelpName(String helpName) {
		this.helpName = helpName;
	}
	@ExcelField(title="帮扶人联系方式", align=2, sort=11)
	@Length(min=0, max=18, message="帮扶人联系方式长度必须介于 0 和 18 之间")
	public String getHelpTl() {
		return helpTl;
	}

	public void setHelpTl(String helpTl) {
		this.helpTl = helpTl;
	}
	@ExcelField(title="违法地区", align=2, sort=12)
	@Length(min=0, max=255, message="违法地区长度必须介于 0 和 255 之间")
	public String getIllegalArea() {
		return illegalArea;
	}

	public void setIllegalArea(String illegalArea) {
		this.illegalArea = illegalArea;
	}
	@ExcelField(title="违法时间", align=2, sort=13)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getIllegalDate() {
		return illegalDate;
	}

	public void setIllegalDate(Date illegalDate) {
		this.illegalDate = illegalDate;
	}
	@ExcelField(title="释放时间", align=2, sort=14)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAcquittalDate() {
		return acquittalDate;
	}

	public void setAcquittalDate(Date acquittalDate) {
		this.acquittalDate = acquittalDate;
	}
	@ExcelField(title="改造情况", align=2, sort=15)
	@Length(min=0, max=255, message="改造情况长度必须介于 0 和 255 之间")
	public String getTransformInfo() {
		return transformInfo;
	}

	public void setTransformInfo(String transformInfo) {
		this.transformInfo = transformInfo;
	}
	@ExcelField(title="违法原因", align=2, sort=16)
	@Length(min=0, max=1024, message="违法原因长度必须介于 0 和 1024 之间")
	public String getIllegalDescription() {
		return illegalDescription;
	}

	public void setIllegalDescription(String illegalDescription) {
		this.illegalDescription = illegalDescription;
	}
	@ExcelField(title="是否累犯", align=2, sort=17,dictType="yes_no")
	@Length(min=0, max=1, message="是否累犯（有、否）长度必须介于 0 和 1 之间")
	public String getIsRecidivism() {
		return isRecidivism;
	}

	public void setIsRecidivism(String isRecidivism) {
		this.isRecidivism = isRecidivism;
	}
	@ExcelField(title="回访情况", align=2, sort=18)
	@Length(min=0, max=2084, message="回访情况长度必须介于 0 和 2084 之间")
	public String getReviewType() {
		return reviewType;
	}

	public void setReviewType(String reviewType) {
		this.reviewType = reviewType;
	}
	@ExcelField(title="犯罪史详情", align=2, sort=19)
	@Length(min=0, max=1024, message="犯罪史详情长度必须介于 0 和 1024 之间")
	public String getCrimeHistory() {
		return crimeHistory;
	}

	public void setCrimeHistory(String crimeHistory) {
		this.crimeHistory = crimeHistory;
	}
	
	@Length(min=0, max=255, message="冗余1长度必须介于 0 和 255 之间")
	public String getMore1() {
		return more1;
	}

	public void setMore1(String more1) {
		this.more1 = more1;
	}
	
	@Length(min=0, max=255, message="冗余2长度必须介于 0 和 255 之间")
	public String getMore2() {
		return more2;
	}

	public void setMore2(String more2) {
		this.more2 = more2;
	}
	
}