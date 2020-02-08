/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.score.entity;

import com.arjjs.ccm.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 绩效KPI得分Entity
 * @author liang
 * @version 2018-04-11
 */
public class KpiSchemeScore extends DataEntity<KpiSchemeScore> {
	
	private static final long serialVersionUID = 1L;
	private User userId;		// 被考核人
	private User scorerId;		// 评分人
	private String kpiId;		// KPI编号
	private Double score;		// 分数
	private String schemeId;		// 所属方案
	
	public KpiSchemeScore() {
		super();
	}

	public KpiSchemeScore(String id){
		super(id);
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}
	
	public User getScorerId() {
		return scorerId;
	}

	public void setScorerId(User scorerId) {
		this.scorerId = scorerId;
	}
	
	@Length(min=0, max=64, message="KPI编号长度必须介于 0 和 64 之间")
	public String getKpiId() {
		return kpiId;
	}

	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}
	
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
	@Length(min=0, max=64, message="所属方案长度必须介于 0 和 64 之间")
	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	
}