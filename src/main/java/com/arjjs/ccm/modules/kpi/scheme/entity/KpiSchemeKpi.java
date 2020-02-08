/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.scheme.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 绩效考评KPIEntity
 * @author liang
 * @version 2018-04-11
 */
public class KpiSchemeKpi extends DataEntity<KpiSchemeKpi> {
	
	private static final long serialVersionUID = 1L;
	private String parentId;		// 上级id
	private String name;		// KPI名称
	private Double score;		// KPI分数
	private String type;		// 考核类别
	private String schemeId;		// 所属方案
	private String schemeName;		// 所属方案名称
	private String schemeState;		// 所属方案状态
	
	
	public String getSchemeState() {
		return schemeState;
	}

	public void setSchemeState(String schemeState) {
		this.schemeState = schemeState;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public KpiSchemeKpi() {
		super();
	}

	public KpiSchemeKpi(String id){
		super(id);
	}

	@Length(min=0, max=64, message="上级id长度必须介于 0 和 64 之间")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Length(min=0, max=64, message="KPI名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
	@Length(min=0, max=2, message="考核类别长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="所属方案长度必须介于 0 和 64 之间")
	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	
}