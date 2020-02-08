/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.addressbook.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.tool.PlmTypes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 通讯录Entity
 * @author liucong
 * @version 2018-07-14
 */
public class PlmEmployee extends DataEntity<PlmEmployee> implements Cloneable{
	
	PlmTypes plmTypes;
	private static final long serialVersionUID = 1L;
	private String code;		// 人员编号
	private String name;		// 人员姓名
	private String ename;		// 英文姓名
	private Office dePid;		// 部门编号id
	private String sex;		// 性别（默认男）
	private String card;		// 身份证号
	private String amac;		// 从业资格证
	private String carType;		// 准驾车型
	private String drivingLicence;		// 驾驶执照
	private String carIllegally;		// 违章
	private String state;		// 就职状态id
	private String duty;		// 职务（改字段名）
	private String phoneone;		// 联系电话一
	private String phonetwo;		// 联系电话二
	private String accounts;		// 工作编号（警号）
	private Date birthday;		// 出生日期(修改数据类型)
	private String job;		// 岗位
	private String email;		// 电子邮件（一个邮箱）
	private String web;		// 网址
	private String faxes;		// 传真号码
	private String openBank;		// 开户银行
	private String top;		// 最高学历
	private String degree;		// 学位
	private Date goTime;		// 到期日期
	private String graduateTime;		// 毕业学校
	private Date pactstartTime;		// 合同起始日期
	private Date pactendTime;		// 合同终止日期
	private String nowMailid;		// 现邮编
	private String familyPhone;		// 家庭电话
	private String familyPlace;		// 家庭住址
	private String nowPlace;		// 现住址
	private String exigenceMan;		// 紧急联系人
	private String exigencePhone;		// 紧急联系人电话
	private String familyMailid;		// 家庭邮编
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	private String type;		// 是否在个人通讯录
	private PlmEmployeeGroups groups; //通讯录分组、
	private String imgul;//图片
	private String types;
	@SuppressWarnings("static-access")
	public PlmEmployee() {
		super();
		this.sex= plmTypes.SEX_MAN;
	}

	public PlmEmployee(String id){
		super(id);
	}

	@Length(min=1, max=64, message="人员编号长度必须介于 1 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=64, message="人员姓名长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="英文姓名长度必须介于 0 和 64 之间")
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
	
	public Office getDePid() {
		return dePid;
	}

	public void setDePid(Office dePid) {
		this.dePid = dePid;
	}
	@JsonIgnore
	@Length(min=1, max=1, message="性别（默认男）长度必须介于 1 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=1, max=32, message="身份证号长度必须介于 1 和 32 之间")
	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}
	
	@Length(min=1, max=64, message="从业资格证长度必须介于 1 和 64 之间")
	public String getAmac() {
		return amac;
	}

	public void setAmac(String amac) {
		this.amac = amac;
	}
	
	@Length(min=1, max=64, message="准驾车型长度必须介于 1 和 64 之间")
	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}
	
	@Length(min=1, max=64, message="驾驶执照长度必须介于 1 和 64 之间")
	public String getDrivingLicence() {
		return drivingLicence;
	}

	public void setDrivingLicence(String drivingLicence) {
		this.drivingLicence = drivingLicence;
	}
	
	@Length(min=1, max=3, message="违章长度必须介于 1 和 3 之间")
	public String getCarIllegally() {
		return carIllegally;
	}

	public void setCarIllegally(String carIllegally) {
		this.carIllegally = carIllegally;
	}
	
	@Length(min=1, max=64, message="就职状态id长度必须介于 1 和 64 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=64, message="职务（改字段名）长度必须介于 0 和 64 之间")
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}
	
	@NotNull(message="联系电话不能为空")
	@Length(min=0, max=32, message="联系电话一长度必须介于 0 和 32 之间")
	public String getPhoneone() {
		return phoneone;
	}

	public void setPhoneone(String phoneone) {
		this.phoneone = phoneone;
	}
	
	@Length(min=0, max=32, message="联系电话二长度必须介于 0 和 32 之间")
	public String getPhonetwo() {
		return phonetwo;
	}

	public void setPhonetwo(String phonetwo) {
		this.phonetwo = phonetwo;
	}
	
	@NotNull(message="警员编号不能为空")
	@Length(min=0, max=64, message="工作编号（警号）长度必须介于 0 和 64 之间")
	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=64, message="岗位长度必须介于 0 和 64 之间")
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	@Length(min=0, max=64, message="电子邮件（一个邮箱）长度必须介于 0 和 64 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=64, message="网址长度必须介于 0 和 64 之间")
	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}
	
	@Length(min=0, max=32, message="传真号码长度必须介于 0 和 32 之间")
	public String getFaxes() {
		return faxes;
	}

	public void setFaxes(String faxes) {
		this.faxes = faxes;
	}
	
	@Length(min=0, max=64, message="开户银行长度必须介于 0 和 64 之间")
	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	
	@Length(min=0, max=32, message="最高学历长度必须介于 0 和 32 之间")
	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}
	
	@Length(min=0, max=32, message="学位长度必须介于 0 和 32 之间")
	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getGoTime() {
		return goTime;
	}

	public void setGoTime(Date goTime) {
		this.goTime = goTime;
	}
	
	@Length(min=0, max=32, message="毕业学校长度必须介于 0 和 32 之间")
	public String getGraduateTime() {
		return graduateTime;
	}

	public void setGraduateTime(String graduateTime) {
		this.graduateTime = graduateTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPactstartTime() {
		return pactstartTime;
	}

	public void setPactstartTime(Date pactstartTime) {
		this.pactstartTime = pactstartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPactendTime() {
		return pactendTime;
	}

	public void setPactendTime(Date pactendTime) {
		this.pactendTime = pactendTime;
	}
	
	@Length(min=0, max=32, message="现邮编长度必须介于 0 和 32 之间")
	public String getNowMailid() {
		return nowMailid;
	}

	public void setNowMailid(String nowMailid) {
		this.nowMailid = nowMailid;
	}
	
	@Length(min=0, max=32, message="家庭电话长度必须介于 0 和 32 之间")
	public String getFamilyPhone() {
		return familyPhone;
	}

	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}
	
	@Length(min=0, max=64, message="家庭住址长度必须介于 0 和 64 之间")
	public String getFamilyPlace() {
		return familyPlace;
	}

	public void setFamilyPlace(String familyPlace) {
		this.familyPlace = familyPlace;
	}
	
	@NotNull(message="现住址不能为空")
	@Length(min=0, max=128, message="现住址长度必须介于 0 和 128 之间")
	public String getNowPlace() {
		return nowPlace;
	}

	public void setNowPlace(String nowPlace) {
		this.nowPlace = nowPlace;
	}
	
	@Length(min=0, max=64, message="紧急联系人长度必须介于 0 和 64 之间")
	public String getExigenceMan() {
		return exigenceMan;
	}

	public void setExigenceMan(String exigenceMan) {
		this.exigenceMan = exigenceMan;
	}
	
	@Length(min=0, max=32, message="紧急联系人电话长度必须介于 0 和 32 之间")
	public String getExigencePhone() {
		return exigencePhone;
	}

	public void setExigencePhone(String exigencePhone) {
		this.exigencePhone = exigencePhone;
	}
	
	@Length(min=0, max=64, message="家庭邮编长度必须介于 0 和 64 之间")
	public String getFamilyMailid() {
		return familyMailid;
	}

	public void setFamilyMailid(String familyMailid) {
		this.familyMailid = familyMailid;
	}
	
	@Length(min=0, max=256, message="扩展1长度必须介于 0 和 256 之间")
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	@Length(min=0, max=256, message="扩展2长度必须介于 0 和 256 之间")
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	
	@Length(min=0, max=1, message="是否在个人通讯录长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PlmEmployeeGroups getGroups() {
		return groups;
	}

	public void setGroups(PlmEmployeeGroups groups) {
		this.groups = groups;
	}

	public String getImgul() {
		return imgul;
	}

	public void setImgul(String imgul) {
		this.imgul = imgul;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}
	
	
}