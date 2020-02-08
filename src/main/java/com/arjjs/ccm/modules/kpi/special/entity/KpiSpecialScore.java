/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.special.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 考核细则Entity
 * @author yiqingxuan
 * @version 2019-07-12
 */
public class KpiSpecialScore extends DataEntity<KpiSpecialScore> {
	
	private static final long serialVersionUID = 1L;
	private String keyname;		// key名称
	private String name;		// 名称
	private String weights;		// 权重
	
	//查询排行榜得分
	private String score;
	private Date time;
	
	//private Date year;
	private Date month;
	private String quarter;
	private String type;
	private Date beginDate;
	private Date endDate;
	
	
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

//	public Date getYear() {
//		return year;
//	}
//
//	public void setYear(Date year) {
//		this.year = year;
//	}

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public KpiSpecialScore() {
		super();
	}

	public KpiSpecialScore(String id){
		super(id);
	}

	@Length(min=0, max=64, message="key名称长度必须介于 0 和 64 之间")
	public String getKeyname() {
		return keyname;
	}

	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}
	
	@Length(min=0, max=64, message="名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="权重长度必须介于 0 和 64 之间")
	public String getWeights() {
		return weights;
	}

	public void setWeights(String weights) {
		this.weights = weights;
	}
	
}