/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.scheme.entity;

import com.arjjs.ccm.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 部门年度目标Entity
 * @author liang
 * @version 2018-04-11
 */
public class KpiGoalYears extends DataEntity<KpiGoalYears> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 部门
	private String years;		// 年度
	private String goal;		// 目标
	private String goalPlan;		// 目标计划
	private String progress;		// 完成进度
	private String finished;		// 是否完成
	private String conclusion;		// 考核结论
	
	public KpiGoalYears() {
		super();
	}

	public KpiGoalYears(String id){
		super(id);
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=32, message="年度长度必须介于 0 和 32 之间")
	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}
	
	@Length(min=0, max=256, message="目标长度必须介于 0 和 256 之间")
	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}
	
	@Length(min=0, max=512, message="目标计划长度必须介于 0 和 512 之间")
	public String getGoalPlan() {
		return goalPlan;
	}

	public void setGoalPlan(String goalPlan) {
		this.goalPlan = goalPlan;
	}
	
	@Length(min=0, max=256, message="完成进度长度必须介于 0 和 256 之间")
	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}
	
	@Length(min=0, max=1, message="是否完成长度必须介于 0 和 1 之间")
	public String getFinished() {
		return finished;
	}

	public void setFinished(String finished) {
		this.finished = finished;
	}
	
	@Length(min=0, max=256, message="考核结论长度必须介于 0 和 256 之间")
	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	
}