/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.manage.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 评估流程管理Entity
 * @author liang
 * @version 2018-04-04
 */
public class RiskAssessFlow extends DataEntity<RiskAssessFlow> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 流程名称
	private String userName;		// 发起人
	private String specialists;		// 专家组人员
	private String tenet;		// 评估原则
	private String content;		// 评估内容
	private Date limitTime;		// 有效时限
	private Date beginLimitTime;		// 开始 有效时限
	private Date endLimitTime;		// 结束 有效时限
	
	public RiskAssessFlow() {
		super();
	}

	public RiskAssessFlow(String id){
		super(id);
	}

	@Length(min=0, max=64, message="流程名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="发起人长度必须介于 0 和 64 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=256, message="专家组人员长度必须介于 0 和 256 之间")
	public String getSpecialists() {
		return specialists;
	}

	public void setSpecialists(String specialists) {
		this.specialists = specialists;
	}
	
	@Length(min=0, max=512, message="评估原则长度必须介于 0 和 512 之间")
	public String getTenet() {
		return tenet;
	}

	public void setTenet(String tenet) {
		this.tenet = tenet;
	}
	
	@Length(min=0, max=1024, message="评估内容长度必须介于 0 和 1024 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(Date limitTime) {
		this.limitTime = limitTime;
	}
	
	public Date getBeginLimitTime() {
		return beginLimitTime;
	}

	public void setBeginLimitTime(Date beginLimitTime) {
		this.beginLimitTime = beginLimitTime;
	}
	
	public Date getEndLimitTime() {
		return endLimitTime;
	}

	public void setEndLimitTime(Date endLimitTime) {
		this.endLimitTime = endLimitTime;
	}
		
}