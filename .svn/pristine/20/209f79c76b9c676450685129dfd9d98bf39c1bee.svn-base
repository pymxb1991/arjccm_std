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
 * 矛盾纠纷Entity
 * @author liu
 * @version 2018-09-27
 */
public class CcmHouseDispute extends DataEntity<CcmHouseDispute> {
	
	private static final long serialVersionUID = 1L;
	private String peopleId;		// 实有人口（id）
	private String disputer;		// 纠纷人
	private String disputerAddr;		// 纠纷人住址
	private String disputerPhone;		// 纠纷人联系方式
	private String disputeCase;		// 纠纷原因
	private Date firstDate;		// 初次时间
	private String conomicState;		// 经济状况
	private String discoveryWay;		// 发现途径 数据字典 house_supervise_status
	private String discoverer;		// 发现人
	private String discoverId;		// 发现人身份证号
	private String discoverPhone;		// 发现人联系方式
	private String possibleBehave;		// 可能行为
	private String activityScope;		// 活动范围
	private String dangerLevel;		// 关注程度
	private String helpName;		// 疏导人
	private String helpTl;		// 疏导人联系方式
	private String helpCase;		// 疏导情况
	private String isCrimeHistory;		// 有无犯罪史
	private String crimeHistory;		// 犯罪史
	private String superviseStatus;		// 监管状态 数据字典 house_discovery_way
	private String more1;		// 冗余1
	private String more2;		// 冗余2
	
	
	private String dangerLevelLable;	//app接口使用
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
	
	public CcmHouseDispute() {
		super();
	}

	public CcmHouseDispute(String id){
		super(id);
	}

	//@ExcelField(title="实有人口（id）", align=2, sort=21)
	@Length(min=1, max=64, message="实有人口（id）长度必须介于 1 和 64 之间")
	public String getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}
	
	@ExcelField(title="纠纷人", align=2, sort=7)
	@Length(min=0, max=32, message="纠纷人长度必须介于 0 和 32 之间")
	public String getDisputer() {
		return disputer;
	}

	public void setDisputer(String disputer) {
		this.disputer = disputer;
	}
	
	@ExcelField(title="纠纷人住址", align=2, sort=8)
	@Length(min=0, max=128, message="纠纷人住址长度必须介于 0 和 128 之间")
	public String getDisputerAddr() {
		return disputerAddr;
	}

	public void setDisputerAddr(String disputerAddr) {
		this.disputerAddr = disputerAddr;
	}
	
	@ExcelField(title="纠纷人联系方式", align=2, sort=9)
	@Length(min=0, max=16, message="纠纷人联系方式长度必须介于 0 和 16 之间")
	public String getDisputerPhone() {
		return disputerPhone;
	}

	public void setDisputerPhone(String disputerPhone) {
		this.disputerPhone = disputerPhone;
	}
	
	@ExcelField(title="纠纷原因", align=2, sort=10)
	@Length(min=0, max=1024, message="纠纷原因长度必须介于 0 和 1024 之间")
	public String getDisputeCase() {
		return disputeCase;
	}

	public void setDisputeCase(String disputeCase) {
		this.disputeCase = disputeCase;
	}
	
	@ExcelField(title="初次日期", align=2, sort=6)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}
	
	@ExcelField(title="经济状况", align=2, sort=11 ,dictType="conomic_state_dict")
	@Length(min=0, max=2, message="经济状况长度必须介于 0 和 2 之间")
	public String getConomicState() {
		return conomicState;
	}

	public void setConomicState(String conomicState) {
		this.conomicState = conomicState;
	}
	
	@ExcelField(title="发现途径", align=2, sort=12 ,dictType="house_discovery_way")
	@Length(min=0, max=2, message="发现途径 数据字典 house_supervise_status长度必须介于 0 和 2 之间")
	public String getDiscoveryWay() {
		return discoveryWay;
	}

	public void setDiscoveryWay(String discoveryWay) {
		this.discoveryWay = discoveryWay;
	}
	
	@ExcelField(title="发现人", align=2, sort=21 )
	@Length(min=0, max=32, message="发现人长度必须介于 0 和 32 之间")
	public String getDiscoverer() {
		return discoverer;
	}

	public void setDiscoverer(String discoverer) {
		this.discoverer = discoverer;
	}
	
	@ExcelField(title="发现人身份证号", align=2, sort=22 )
	@Length(min=0, max=20, message="发现人身份证号长度必须介于 0 和 20 之间")
	public String getDiscoverId() {
		return discoverId;
	}

	public void setDiscoverId(String discoverId) {
		this.discoverId = discoverId;
	}
	
	@ExcelField(title="发现人联系方式", align=2, sort=23 )
	@Length(min=0, max=16, message="发现人联系方式长度必须介于 0 和 16 之间")
	public String getDiscoverPhone() {
		return discoverPhone;
	}

	public void setDiscoverPhone(String discoverPhone) {
		this.discoverPhone = discoverPhone;
	}
	
	@Length(min=0, max=512, message="可能行为长度必须介于 0 和 512 之间")
	public String getPossibleBehave() {
		return possibleBehave;
	}

	public void setPossibleBehave(String possibleBehave) {
		this.possibleBehave = possibleBehave;
	}
	
	@Length(min=0, max=512, message="活动范围长度必须介于 0 和 512 之间")
	public String getActivityScope() {
		return activityScope;
	}

	public void setActivityScope(String activityScope) {
		this.activityScope = activityScope;
	}
	
	@ExcelField(title="关注程度", align=2, sort=20,dictType="ccm_conc_exte")
	@Length(min=0, max=2, message="关注程度长度必须介于 0 和 2 之间")
	public String getDangerLevel() {
		return dangerLevel;
	}

	public void setDangerLevel(String dangerLevel) {
		this.dangerLevel = dangerLevel;
	}
	
	@ExcelField(title="疏导人", align=2, sort=15)
	@Length(min=0, max=32, message="疏导人长度必须介于 0 和 32 之间")
	public String getHelpName() {
		return helpName;
	}

	public void setHelpName(String helpName) {
		this.helpName = helpName;
	}
	
	@ExcelField(title="疏导人联系方式", align=2, sort=16)
	@Length(min=0, max=18, message="疏导人联系方式长度必须介于 0 和 18 之间")
	public String getHelpTl() {
		return helpTl;
	}

	public void setHelpTl(String helpTl) {
		this.helpTl = helpTl;
	}
	
	@ExcelField(title="疏导情况", align=2, sort=24)
	@Length(min=0, max=1024, message="疏导情况长度必须介于 0 和 1024 之间")
	public String getHelpCase() {
		return helpCase;
	}

	public void setHelpCase(String helpCase) {
		this.helpCase = helpCase;
	}
	
	@ExcelField(title="有无犯罪史", align=2, sort=13,dictType="yes_no")
	@Length(min=0, max=1, message="有无犯罪史长度必须介于 0 和 1 之间")
	public String getIsCrimeHistory() {
		return isCrimeHistory;
	}

	public void setIsCrimeHistory(String isCrimeHistory) {
		this.isCrimeHistory = isCrimeHistory;
	}
	
	@ExcelField(title="犯罪情况", align=2, sort=14)
	@Length(min=0, max=1024, message="犯罪史长度必须介于 0 和 1024 之间")
	public String getCrimeHistory() {
		return crimeHistory;
	}

	public void setCrimeHistory(String crimeHistory) {
		this.crimeHistory = crimeHistory;
	}
	
	@ExcelField(title="有无犯罪史", align=2, sort=25,dictType="	house_supervise_status")
	@Length(min=0, max=2, message="监管状态 数据字典 	house_supervise_status长度必须介于 0 和 2 之间")
	public String getSuperviseStatus() {
		return superviseStatus;
	}

	public void setSuperviseStatus(String superviseStatus) {
		this.superviseStatus = superviseStatus;
	}
	
	@Length(min=0, max=256, message="冗余1长度必须介于 0 和 256 之间")
	public String getMore1() {
		return more1;
	}

	public void setMore1(String more1) {
		this.more1 = more1;
	}
	
	@Length(min=0, max=256, message="冗余2长度必须介于 0 和 256 之间")
	public String getMore2() {
		return more2;
	}

	public void setMore2(String more2) {
		this.more2 = more2;
	}

	public String getDangerLevelLable() {
		return dangerLevelLable;
	}

	public void setDangerLevelLable(String dangerLevelLable) {
		this.dangerLevelLable = dangerLevelLable;
	}

	@ExcelField(title="人口类型", align=2, sort=2,dictType="sys_ccm_people")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@ExcelField(title="姓名", align=1 ,sort=1)
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

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
	
}