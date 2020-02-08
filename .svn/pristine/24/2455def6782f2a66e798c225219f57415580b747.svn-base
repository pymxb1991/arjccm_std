/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 经济运行数据-年Entity
 * @author liang
 * @version 2018-06-03
 */
public class CcmEconomicsYear extends DataEntity<CcmEconomicsYear> {
	
	private static final long serialVersionUID = 1L;
	private Date years;		// 年
	private Double goal;		// 任务目标（亿元）
	private Double industrial;		// 工业总产值（亿元）
	private Double invest;		// 固定资产投资（亿元）
	private Double imports;		// 招商引资（亿元）
	private Double retail;		// 商业零售（亿元）
	private Double production;		// 生产总值（亿元）
	private Date beginYears;		// 开始 年
	private Date endYears;		// 结束 年
	
	public CcmEconomicsYear() {
		super();
	}

	public CcmEconomicsYear(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getYears() {
		return years;
	}

	public void setYears(Date years) {
		this.years = years;
	}
	
	public Double getGoal() {
		return goal;
	}

	public void setGoal(Double goal) {
		this.goal = goal;
	}
	
	public Double getIndustrial() {
		return industrial;
	}

	public void setIndustrial(Double industrial) {
		this.industrial = industrial;
	}
	
	public Double getInvest() {
		return invest;
	}

	public void setInvest(Double invest) {
		this.invest = invest;
	}
	
	public Double getImports() {
		return imports;
	}

	public void setImports(Double imports) {
		this.imports = imports;
	}
	
	public Double getRetail() {
		return retail;
	}

	public void setRetail(Double retail) {
		this.retail = retail;
	}
	
	public Double getProduction() {
		return production;
	}

	public void setProduction(Double production) {
		this.production = production;
	}
	
	public Date getBeginYears() {
		return beginYears;
	}

	public void setBeginYears(Date beginYears) {
		this.beginYears = beginYears;
	}
	
	public Date getEndYears() {
		return endYears;
	}

	public void setEndYears(Date endYears) {
		this.endYears = endYears;
	}
		
}