/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.entity;

import org.hibernate.validator.constraints.Length;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.entity.User;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 重点地区排查整治Entity
 * @author arj
 * @version 2018-01-04
 */
public class CcmEventKacc extends DataEntity<CcmEventKacc> {
	
	private static final long serialVersionUID = 1L;
	private String secuPlace;		// 治安重点地区
	private Area area;		// 所属社区
	private String secuProb;		// 治安突出问题
	private String distType;		// 涉及区域类型
	private String distScope;		// 涉及区域范围
	private String compLead;		// 整治牵头单位
	private String compPart;		// 整治参与单位
	private String compPrinName;		// 整治牵头单位负责人姓名
	private String compPrinPhone;		// 整治牵头单位负责人联系方式
	private Date abarDate;		// 整改时限
	private Integer abarSolvNum;		// 整治期间破获刑事案事件数
	private Integer abarInveNum;		// 整治期间查处治安案事件数
	private String abarCase;		// 整治情况
	private String resuAsse;		// 效果评估
	private Date beginCreateDate;		// 开始时间
	private Date endCreateDate;		// 结束时间
	private String secuProbLable;	//app接口使用

	private User checkUser;		// 拦截器中使用该用户进行权限拦截，App的rest接口使用
	public User getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}

	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public CcmEventKacc() {
		super();
	}

	public CcmEventKacc(String id){
		super(id);
	}

	@Length(min=0, max=100, message="治安重点地区长度必须介于 0 和 100 之间")
	public String getSecuPlace() {
		return secuPlace;
	}

	public void setSecuPlace(String secuPlace) {
		this.secuPlace = secuPlace;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Length(min=0, max=2, message="治安突出问题长度必须介于 0 和 2 之间")
	public String getSecuProb() {
		return secuProb;
	}

	public void setSecuProb(String secuProb) {
		this.secuProb = secuProb;
	}
	
	@Length(min=0, max=2, message="涉及区域类型长度必须介于 0 和 2 之间")
	public String getDistType() {
		return distType;
	}

	public void setDistType(String distType) {
		this.distType = distType;
	}
	
	@Length(min=0, max=400, message="涉及区域范围长度必须介于 0 和 400 之间")
	public String getDistScope() {
		return distScope;
	}

	public void setDistScope(String distScope) {
		this.distScope = distScope;
	}
	
	@Length(min=0, max=100, message="整治牵头单位长度必须介于 0 和 100 之间")
	public String getCompLead() {
		return compLead;
	}

	public void setCompLead(String compLead) {
		this.compLead = compLead;
	}
	
	@Length(min=0, max=400, message="整治参与单位长度必须介于 0 和 400 之间")
	public String getCompPart() {
		return compPart;
	}

	public void setCompPart(String compPart) {
		this.compPart = compPart;
	}
	
	@Length(min=0, max=50, message="整治牵头单位负责人姓名长度必须介于 0 和 50 之间")
	public String getCompPrinName() {
		return compPrinName;
	}

	public void setCompPrinName(String compPrinName) {
		this.compPrinName = compPrinName;
	}
	
	@Length(min=0, max=50, message="整治牵头单位负责人联系方式长度必须介于 0 和 50 之间")
	public String getCompPrinPhone() {
		return compPrinPhone;
	}

	public void setCompPrinPhone(String compPrinPhone) {
		this.compPrinPhone = compPrinPhone;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAbarDate() {
		return abarDate;
	}

	public void setAbarDate(Date abarDate) {
		this.abarDate = abarDate;
	}
	
	public Integer getAbarSolvNum() {
		return abarSolvNum;
	}

	public void setAbarSolvNum(Integer abarSolvNum) {
		this.abarSolvNum = abarSolvNum;
	}
	
	public Integer getAbarInveNum() {
		return abarInveNum;
	}

	public void setAbarInveNum(Integer abarInveNum) {
		this.abarInveNum = abarInveNum;
	}
	
	public String getAbarCase() {
		return abarCase;
	}

	public void setAbarCase(String abarCase) {
		this.abarCase = abarCase;
	}
	
	@Length(min=0, max=2, message="效果评估长度必须介于 0 和 2 之间")
	public String getResuAsse() {
		return resuAsse;
	}

	public void setResuAsse(String resuAsse) {
		this.resuAsse = resuAsse;
	}
	public String getSecuProbLable() {
		return secuProbLable;
	}
	public void setSecuProbLable(String secuProbLable) {
		this.secuProbLable = secuProbLable;
	}
	
}