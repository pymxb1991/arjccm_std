/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.scheme.entity;

import com.arjjs.ccm.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 绩效主观评分Entity
 * @author liang
 * @version 2018-04-11
 */
public class KpiSchemeSubjectivity extends DataEntity<KpiSchemeSubjectivity> {
	
	private static final long serialVersionUID = 1L;
	private User userId;		// 被考核人
	private User scorerId;		// 评分人
	private String kpiId;		// KPI编号
	private Double weight;		// 权重
	private Double scoreRemarks;		// 主观分数得分（从分数表中获取，是原始分数，kpi_scheme_score中的remarks）
	
	public Double getScoreRemarks() {
		return scoreRemarks;
	}

	public void setScoreRemarks(Double scoreRemarks) {
		this.scoreRemarks = scoreRemarks;
	}

	public KpiSchemeSubjectivity() {
		super();
	}

	public KpiSchemeSubjectivity(String id){
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
	
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
}