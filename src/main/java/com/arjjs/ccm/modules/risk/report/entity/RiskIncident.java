/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.report.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 风险事件管理Entity
 * @author liang
 * @version 2018-03-31
 */
public class RiskIncident extends DataEntity<RiskIncident> {
	
	private static final long serialVersionUID = 1L;
	private RiskEventGreat riskEventGreat;		// 所属重大事项
	private String source;		// 来源
	private String name;		// 风险名称
	private String type;		// 风险类型
	private String description;		// 风险摘要
	private String countermeasure;		// 对策研究
	private String implement;		// 实施控制
	private String importance;		// 重要程度
	private String urgency;		// 紧急程度
	private String putMan;		// 提出人
	private Date putTime;		// 提出时间
	private String disposeType;		// 处理状态
	private Date beginPutTime;		// 开始 提出时间
	private Date endPutTime;		// 结束 提出时间
	
	public RiskIncident() {
		super();
	}

	public RiskIncident(String id){
		super(id);
	}

	
	public RiskEventGreat getRiskEventGreat() {
		return riskEventGreat;
	}

	public void setRiskEventGreat(RiskEventGreat riskEventGreat) {
		this.riskEventGreat = riskEventGreat;
	}
	
	@Length(min=0, max=100, message="来源长度必须介于 0 和 100 之间")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Length(min=0, max=100, message="风险名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min=0, max=2, message="风险类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=256, message="风险摘要长度必须介于 0 和 256 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=1000, message="对策研究长度必须介于 0 和 1000 之间")
	public String getCountermeasure() {
		return countermeasure;
	}

	public void setCountermeasure(String countermeasure) {
		this.countermeasure = countermeasure;
	}
	
	@Length(min=0, max=1000, message="实施控制长度必须介于 0 和 1000 之间")
	public String getImplement() {
		return implement;
	}

	public void setImplement(String implement) {
		this.implement = implement;
	}
	
	@Length(min=0, max=2, message="重要程度长度必须介于 0 和 2 之间")
	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}
	
	@Length(min=0, max=2, message="紧急程度长度必须介于 0 和 2 之间")
	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	
	@Length(min=0, max=64, message="提出人长度必须介于 0 和 64 之间")
	public String getPutMan() {
		return putMan;
	}

	public void setPutMan(String putMan) {
		this.putMan = putMan;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPutTime() {
		return putTime;
	}

	public void setPutTime(Date putTime) {
		this.putTime = putTime;
	}
	
	@Length(min=0, max=2, message="处理状态长度必须介于 0 和 2 之间")
	public String getDisposeType() {
		return disposeType;
	}

	public void setDisposeType(String disposeType) {
		this.disposeType = disposeType;
	}
	
	public Date getBeginPutTime() {
		return beginPutTime;
	}

	public void setBeginPutTime(Date beginPutTime) {
		this.beginPutTime = beginPutTime;
	}
	
	public Date getEndPutTime() {
		return endPutTime;
	}

	public void setEndPutTime(Date endPutTime) {
		this.endPutTime = endPutTime;
	}
		
}