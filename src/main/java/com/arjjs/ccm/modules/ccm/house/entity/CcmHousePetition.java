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
 * 重点上访人员Entity
 * @author liang
 * @version 2018-03-07
 */
public class CcmHousePetition extends DataEntity<CcmHousePetition> {
	
	private static final long serialVersionUID = 1L;
	private String peopleId;		// 实有人口（id）
	private String workUnit;		// 工作单位
	private String workPhone;		// 工作单位电话
	private String atteType;		// 关注程度
	private String petitionType;		// 上访性质
	private String peopleNumber;		// 个或群访
	private String petitionFor;		// 上访事由
	private Date firstTime;		// 初次上访时间
	private Integer petitionNum;		// 上访次数
	private String petitionPlace;		// 上访地点
	private String reflectProblem;		// 反映问题
	private String troubleBehavior;		// 滋事行为
	private String petitionResult;		// 上访结果
	private String atteTypeLable;	//app接口使用
	
	// 实有人口
	private String type; // 人口类型
	private String name; // 姓名
	private String censu; // 籍贯
	
	private String sex; // 性别
	private String ident; // 公民身份号码
	private String telephone; // 联系方式

	private String domiciledetail; // 户籍门（楼）详址
	private String residencedetail; // 现住门（楼）详址
	private String images; // 图片
	private Date birthday;	//出生日期
	private String comName; 	//app接口使用，所属社区

	

	private User checkUser;		// 拦截器中使用该用户进行权限拦截，App的rest接口使用
	public User getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}

	


	@ExcelField(title="人口类型", align=2, sort=2,dictType="sys_ccm_people")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@ExcelField(title="姓名", align=1, sort=1)
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

	@ExcelField(title="联系方式", align=2, sort=19)
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


	public CcmHousePetition() {
		super();
	}

	public CcmHousePetition(String id){
		super(id);
	}

//	@ExcelField(title="实有人口（id）", align=2, sort=20)
//	@Length(min=0, max=64, message="实有人口（id）长度必须介于 0 和 64 之间")
	public String getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}
	
	@ExcelField(title="工作单位", align=2, sort=6)
	@Length(min=0, max=100, message="工作单位长度必须介于 0 和 100 之间")
	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}
	
	@ExcelField(title="工作单位电话", align=2, sort=7)
	@Length(min=0, max=64, message="工作单位电话长度必须介于 0 和 64 之间")
	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	
	@ExcelField(title="关注程度", align=2, sort=21,dictType="ccm_conc_exte")
	@Length(min=0, max=2, message="关注程度长度必须介于 0 和 2 之间")
	public String getAtteType() {
		return atteType;
	}

	public void setAtteType(String atteType) {
		this.atteType = atteType;
	}
	
	@ExcelField(title="上访性质", align=2, sort=8,dictType="ccm_house_petition_type")
	@Length(min=0, max=2, message="上访性质长度必须介于 0 和 2 之间")
	public String getPetitionType() {
		return petitionType;
	}

	public void setPetitionType(String petitionType) {
		this.petitionType = petitionType;
	}
	
	@ExcelField(title="个或群访", align=2, sort=9,dictType="ccm_house_petition_popnum")
	@Length(min=0, max=2, message="个或群访长度必须介于 0 和 2 之间")
	public String getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(String peopleNumber) {
		this.peopleNumber = peopleNumber;
	}
	
	@ExcelField(title="上访事由", align=2, sort=10)
	@Length(min=0, max=200, message="上访事由长度必须介于 0 和 200 之间")
	public String getPetitionFor() {
		return petitionFor;
	}

	public void setPetitionFor(String petitionFor) {
		this.petitionFor = petitionFor;
	}
	
	@ExcelField(title="初次上访时间", align=2, sort=11)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}
	
	@ExcelField(title="上访次数", align=2, sort=12)
	public Integer getPetitionNum() {
		return petitionNum;
	}

	public void setPetitionNum(Integer petitionNum) {
		this.petitionNum = petitionNum;
	}
	
	@ExcelField(title="上访地点", align=2, sort=13)
	@Length(min=0, max=200, message="上访地点长度必须介于 0 和 200 之间")
	public String getPetitionPlace() {
		return petitionPlace;
	}

	public void setPetitionPlace(String petitionPlace) {
		this.petitionPlace = petitionPlace;
	}
	
	@ExcelField(title="反映问题", align=2, sort=14)
	@Length(min=0, max=200, message="反映问题长度必须介于 0 和 200 之间")
	public String getReflectProblem() {
		return reflectProblem;
	}

	public void setReflectProblem(String reflectProblem) {
		this.reflectProblem = reflectProblem;
	}
	
	@ExcelField(title="滋事行为", align=2, sort=15)
	@Length(min=0, max=200, message="滋事行为长度必须介于 0 和 200 之间")
	public String getTroubleBehavior() {
		return troubleBehavior;
	}

	public void setTroubleBehavior(String troubleBehavior) {
		this.troubleBehavior = troubleBehavior;
	}
	
	@ExcelField(title="上访结果", align=2, sort=16)
	@Length(min=0, max=200, message="上访结果长度必须介于 0 和 200 之间")
	public String getPetitionResult() {
		return petitionResult;
	}

	public void setPetitionResult(String petitionResult) {
		this.petitionResult = petitionResult;
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