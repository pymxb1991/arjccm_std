/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.pop.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;
import com.arjjs.ccm.modules.sys.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 留守人员Entity
 * 
 * @author arj
 * @version 2018-01-06
 */
public class CcmPopBehind extends DataEntity<CcmPopBehind> {

	private static final long serialVersionUID = 1L;
	private String peopleId; // 实有人口（id）
	private String health; // 健康状况
	private String atteType; // 关注程度
	private String annualincome; // 个人年收入
	private String staytype; // 留守人员类型
	private String crucialcondition; // 家庭主要成员身份号码
	private String crucialname; // 家庭主要成员姓名
	private String crucialhealth; // 家庭主要成员健康状况
	private String crucialrelation; // 与留守人员关系
	private String crucialtelephone; // 家庭主要成员联系方式
	private String crucialwork; // 家庭主要成员工作详细地址
	private String crucialmoney; // 家庭年收入
	private String difficult; // 困难及诉求
	private String helpcase; // 帮扶情况
	private String atteTypeLable;	//app接口使用

	// 实有人口
	private String type; // 人口类型
	private String name; // 姓名
	private String censu; // 籍贯
	private String sex; // 性别
	private String ident; // 公民身份号码
	private String telephone; // 联系方式
	private Date birthday;	//出生日期

	private String domiciledetail; // 户籍门（楼）详址
	private String residencedetail; // 现住门（楼）详址
	private String images; // 图片
	private String comName; 	//app接口使用，所属社区

	private User checkUser;		// 拦截器中使用该用户进行权限拦截，App的rest接口使用
	public User getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}
	
	public CcmPopBehind() {
		super();
	}

	public CcmPopBehind(String id) {
		super(id);
	}
	
	//@ExcelField(title="实有人口（id）", align=2, sort=22)
	@Length(min = 1, max = 64, message = "实有人口（id）长度必须介于 1 和 64 之间")
	public String getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}

	@ExcelField(title="健康状况", align=2, sort=2,dictType="ccm_phy_cdt")
	@Length(min = 0, max = 2, message = "健康状况长度必须介于 0 和 2 之间")
	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	@ExcelField(title="个人年收入", align=2, sort=3)
	@Length(min = 0, max = 8, message = "个人年收入长度必须介于 0 和 8 之间")
	public String getAnnualincome() {
		return annualincome;
	}

	public void setAnnualincome(String annualincome) {
		this.annualincome = annualincome;
	}

	@ExcelField(title="留守人员类型", align=2, sort=4,dictType="ccm_rear_type")
	@Length(min = 0, max = 2, message = "留守人员类型长度必须介于 0 和 2 之间")
	public String getStaytype() {
		return staytype;
	}

	public void setStaytype(String staytype) {
		this.staytype = staytype;
	}

	@ExcelField(title="家庭主要成员身份号码", align=2, sort=5)
	@Length(min = 0, max = 18, message = "家庭主要成员身份号码长度必须介于 0 和 18 之间")
	public String getCrucialcondition() {
		return crucialcondition;
	}

	public void setCrucialcondition(String crucialcondition) {
		this.crucialcondition = crucialcondition;
	}

	@ExcelField(title="家庭主要成员姓名", align=2, sort=6)
	@Length(min = 0, max = 50, message = "家庭主要成员姓名长度必须介于 0 和 50 之间")
	public String getCrucialname() {
		return crucialname;
	}

	public void setCrucialname(String crucialname) {
		this.crucialname = crucialname;
	}

	@ExcelField(title="家庭主要成员健康状况", align=2, sort=7,dictType="ccm_phy_cdt")
	@Length(min = 0, max = 2, message = "家庭主要成员健康状况长度必须介于 0 和 2 之间")
	public String getCrucialhealth() {
		return crucialhealth;
	}

	public void setCrucialhealth(String crucialhealth) {
		this.crucialhealth = crucialhealth;
	}

	@ExcelField(title="与留守人员关系", align=2, sort=8)
	@Length(min = 0, max = 2, message = "与留守人员关系长度必须介于 0 和 2 之间")
	public String getCrucialrelation() {
		return crucialrelation;
	}

	public void setCrucialrelation(String crucialrelation) {
		this.crucialrelation = crucialrelation;
	}

	@ExcelField(title="家庭主要成员联系方式", align=2, sort=9)
	@Length(min = 0, max = 50, message = "家庭主要成员联系方式长度必须介于 0 和 50 之间")
	public String getCrucialtelephone() {
		return crucialtelephone;
	}

	public void setCrucialtelephone(String crucialtelephone) {
		this.crucialtelephone = crucialtelephone;
	}

	@ExcelField(title="家庭主要成员工作详细地址", align=2, sort=10)
	@Length(min = 0, max = 200, message = "家庭主要成员工作详细地址长度必须介于 0 和 200 之间")
	public String getCrucialwork() {
		return crucialwork;
	}

	public void setCrucialwork(String crucialwork) {
		this.crucialwork = crucialwork;
	}

	@ExcelField(title="家庭年收入", align=2, sort=11)
	@Length(min = 0, max = 8, message = "家庭年收入长度必须介于 0 和 8 之间")
	public String getCrucialmoney() {
		return crucialmoney;
	}

	public void setCrucialmoney(String crucialmoney) {
		this.crucialmoney = crucialmoney;
	}

	@ExcelField(title="困难及诉求", align=2, sort=12)
	@Length(min = 0, max = 1024, message = "困难及诉求长度必须介于 0 和 1024 之间")
	public String getDifficult() {
		return difficult;
	}

	public void setDifficult(String difficult) {
		this.difficult = difficult;
	}

	@ExcelField(title="帮扶情况", align=2, sort=13)
	@Length(min = 0, max = 1024, message = "帮扶情况长度必须介于 0 和 1024 之间")
	public String getHelpcase() {
		return helpcase;
	}

	public void setHelpcase(String helpcase) {
		this.helpcase = helpcase;
	}

	@ExcelField(title="人口类型", align=2, sort=14,dictType="sys_ccm_people")
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
	
	@ExcelField(title="籍贯", align=2, sort=15)
	public String getCensu() {
		return censu;
	}

	public void setCensu(String censu) {
		this.censu = censu;
	}

	@ExcelField(title="性别", align=2, sort=16,dictType="sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@ExcelField(title="公民身份号码", align=2, sort=17)
	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	@ExcelField(title="联系方式", align=2, sort=18)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@ExcelField(title="户籍门（楼）详址", align=2, sort=19)
	public String getDomiciledetail() {
		return domiciledetail;
	}

	public void setDomiciledetail(String domiciledetail) {
		this.domiciledetail = domiciledetail;
	}

	@ExcelField(title="现住门（楼）详址", align=2, sort=20)
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
	@ExcelField(title="关注程度", align=2, sort=21,dictType="ccm_conc_exte")
	@Length(min = 0, max = 2, message = "关注程度长度必须介于 0 和 2 之间")
	public String getAtteType() {
		return atteType;
	}

	public void setAtteType(String atteType) {
		this.atteType = atteType;
	}
	public String getAtteTypeLable() {
		return atteTypeLable;
	}
	public void setAtteTypeLable(String atteTypeLable) {
		this.atteTypeLable = atteTypeLable;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	

}