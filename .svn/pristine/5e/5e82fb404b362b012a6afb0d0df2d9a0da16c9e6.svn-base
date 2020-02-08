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
 * 涉教人员Entity
 * @author liang
 * @version 2018-03-07
 */
public class CcmHouseHeresy extends DataEntity<CcmHouseHeresy> {
	
	private static final long serialVersionUID = 1L;
	private String peopleId;		// 实有人口（id）
	private String atteType;		// 关注程度
	private String heresyName;		// 教派名称
	private Date firstTime;		// 初次参与时间
	private Date endTime;		// 停止时间
	private String level;		// 痴迷程度
	private String howToKnow;		// 何处得知
	private String introducer;		// 介绍人
	private String dutyOfficer;		// 包保帮教责任人
	private String officerTel;		// 责任人电话
	private Integer isStudy;		// 是否参加转化培训班
	private Integer isChange;		// 是否已转化
	private String liveStatus;		// 目前生产生活及思想状况
	private String atteTypeLable;	//app接口使用
	private Date birthday;	//出生日期
	private String comName; 	//app接口使用，所属社区
	
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

	public CcmHouseHeresy() {
		super();
	}

	public CcmHouseHeresy(String id){
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
	
	@ExcelField(title="关注程度", align=2, sort=21,dictType="ccm_conc_exte")
	@Length(min=0, max=2, message="关注程度长度必须介于 0 和 2 之间")
	public String getAtteType() {
		return atteType;
	}

	public void setAtteType(String atteType) {
		this.atteType = atteType;
	}
	
	@ExcelField(title="教派名称", align=2, sort=6)
	@Length(min=0, max=64, message="教派名称长度必须介于 0 和 64 之间")
	public String getHeresyName() {
		return heresyName;
	}

	public void setHeresyName(String heresyName) {
		this.heresyName = heresyName;
	}
	
	@ExcelField(title="初次参与时间", align=2, sort=7)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(Date firstTime) {
		this.firstTime = firstTime;
	}
	
	@ExcelField(title="停止时间", align=2, sort=8)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@ExcelField(title="痴迷程度", align=2, sort=9,dictType="ccm_house_heresy_level")
	@Length(min=0, max=2, message="痴迷程度长度必须介于 0 和 2 之间")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	@ExcelField(title="何处得知", align=2, sort=10)
	@Length(min=0, max=64, message="何处得知长度必须介于 0 和 64 之间")
	public String getHowToKnow() {
		return howToKnow;
	}

	public void setHowToKnow(String howToKnow) {
		this.howToKnow = howToKnow;
	}
	
	@ExcelField(title="介绍人", align=2, sort=11)
	@Length(min=0, max=64, message="介绍人长度必须介于 0 和 64 之间")
	public String getIntroducer() {
		return introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}
	
	@ExcelField(title="包保帮教责任人", align=2, sort=12)
	@Length(min=0, max=64, message="包保帮教责任人长度必须介于 0 和 64 之间")
	public String getDutyOfficer() {
		return dutyOfficer;
	}

	public void setDutyOfficer(String dutyOfficer) {
		this.dutyOfficer = dutyOfficer;
	}
	
	@ExcelField(title="责任人电话", align=2, sort=13)
	@Length(min=0, max=64, message="责任人电话长度必须介于 0 和 64 之间")
	public String getOfficerTel() {
		return officerTel;
	}

	public void setOfficerTel(String officerTel) {
		this.officerTel = officerTel;
	}
	
	@ExcelField(title="是否参加转化培训班", align=2, sort=14,dictType="yes_no")
	public Integer getIsStudy() {
		return isStudy;
	}

	public void setIsStudy(Integer isStudy) {
		this.isStudy = isStudy;
	}
	
	@ExcelField(title="是否已转化", align=2, sort=15,dictType="yes_no")
	public Integer getIsChange() {
		return isChange;
	}

	public void setIsChange(Integer isChange) {
		this.isChange = isChange;
	}
	
	@ExcelField(title="目前生产生活及思想状况", align=2, sort=16)
	@Length(min=0, max=200, message="目前生产生活及思想状况长度必须介于 0 和 200 之间")
	public String getLiveStatus() {
		return liveStatus;
	}

	public void setLiveStatus(String liveStatus) {
		this.liveStatus = liveStatus;
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