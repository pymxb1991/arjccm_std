/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 经济运行数据-月Entity
 * @author liang
 * @version 2018-06-03
 */
public class CcmEconomicsMonth extends DataEntity<CcmEconomicsMonth> {
	
	private static final long serialVersionUID = 1L;
	private Date months;		// 年月
	private Double gdp;		// 本年累计生产总值（亿元）
	private String unemploymentRate;		// 失业率
	private String interestRate;		// 利率
	private String ppi;		// 生产物价指数（PPI）
	private String cpi;		// 消费物价指数（CPI）
	private Integer personalIncome;		// 人均可支配收入（元）
	private Integer revenue;		// 税收（万元）
	private Date beginMonths;		// 开始 年月
	private Date endMonths;		// 结束 年月
	
	public CcmEconomicsMonth() {
		super();
	}

	public CcmEconomicsMonth(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMonths() {
		return months;
	}

	public void setMonths(Date months) {
		this.months = months;
	}
	
	public Double getGdp() {
		return gdp;
	}

	public void setGdp(Double gdp) {
		this.gdp = gdp;
	}
	
	@Length(min=0, max=10, message="失业率长度必须介于 0 和 10 之间")
	public String getUnemploymentRate() {
		return unemploymentRate;
	}

	public void setUnemploymentRate(String unemploymentRate) {
		this.unemploymentRate = unemploymentRate;
	}
	
	@Length(min=0, max=10, message="利率长度必须介于 0 和 10 之间")
	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
	
	@Length(min=0, max=10, message="生产物价指数（PPI）长度必须介于 0 和 10 之间")
	public String getPpi() {
		return ppi;
	}

	public void setPpi(String ppi) {
		this.ppi = ppi;
	}
	
	@Length(min=0, max=10, message="消费物价指数（CPI）长度必须介于 0 和 10 之间")
	public String getCpi() {
		return cpi;
	}

	public void setCpi(String cpi) {
		this.cpi = cpi;
	}
	
	public Integer getPersonalIncome() {
		return personalIncome;
	}

	public void setPersonalIncome(Integer personalIncome) {
		this.personalIncome = personalIncome;
	}
	
	public Integer getRevenue() {
		return revenue;
	}

	public void setRevenue(Integer revenue) {
		this.revenue = revenue;
	}
	
	public Date getBeginMonths() {
		return beginMonths;
	}

	public void setBeginMonths(Date beginMonths) {
		this.beginMonths = beginMonths;
	}
	
	public Date getEndMonths() {
		return endMonths;
	}

	public void setEndMonths(Date endMonths) {
		this.endMonths = endMonths;
	}
		
}