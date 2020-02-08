/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.score.entity;

import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;
import com.arjjs.ccm.modules.kpi.scheme.entity.KpiSchemeKpi;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 绩效总成绩Entity
 * @author pjq
 * @version 2018-04-11
 */
public class KpiFinalScore extends DataEntity<KpiFinalScore> {
	
	private static final long serialVersionUID = 1L;

	private User user;		// 被考核人
	private String schemeId;		// 方案id
	private String kpiId;		// KPIid
	private String kpiName;		// KPI名称

	@ExcelField(title = "绩效总成绩", align = 2, sort = 5)
	private Double finalScore;		// 最终总得分
	private Double score;		// 绩效分数
	private Double sourceScore;		// 原始数值
	@ExcelField(title = "特殊考核项得分", align = 2, sort = 6)
	private Double journalScore;		// 流水分数合计
	private Double modifyScore;		// 调整分数合计


	private Office office;		// 被考核人所属部门
	private List<String> scoreList;//kpi得分，总成绩显示时使用
	private List<KpiSchemeKpi> kpiList;// 方案id 下对应的kpi数组
	private List<KpiSchemeScore> kpiScoreList;// 被考核人 下对应的kpi得分数组
	
	
	public List<KpiSchemeScore> getKpiScoreList() {
		return kpiScoreList;
	}
	public void setKpiScoreList(List<KpiSchemeScore> kpiScoreList) {
		this.kpiScoreList = kpiScoreList;
	}
	public List<KpiSchemeKpi> getKpiList() {
		return kpiList;
	}
	public void setKpiList(List<KpiSchemeKpi> kpiList) {
		this.kpiList = kpiList;
	}
	public List<String> getScoreList() {
		return scoreList;
	}
	public void setScoreList(List<String> scoreList) {
		this.scoreList = scoreList;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public KpiFinalScore() {
		super();
	}

	public KpiFinalScore(String id){
		super(id);
	}


	public User getUser() {
		return user;
	}


	@ExcelField(title="被考核人", align=2, sort=2)
	public String getUserName() {
		return user.getName();
	}

	@ExcelField(title="工号", align=2, sort=1)
	public String getUserNo() {
		return user.getNo();
	}

	@ExcelField(title="用户类型", align=2, sort=3, dictType = "sys_user_type")
	public String getUserType() {
		return user.getUserType();
	}


	@ExcelField(title="被考核人所属部门", align=2, sort=4)
	public String getOfficeName() {
		return office.getName();
	}

	@ExcelField(title="群众考核（40.0）", align=2, sort=7)
	public String getMassesAssessment() {
		return this.scoreList.get(0);
	}

	@ExcelField(title="日常工作评分（40.0）", align=2, sort=8)
	public String getWorkAssessment() {
		return this.scoreList.get(1);
	}

	@ExcelField(title="领导评分（20.0）", align=2, sort=9)
	public String getLeaderAssessment() {
		return this.scoreList.get(2);
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=64, message="方案id长度必须介于 0 和 64 之间")
	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	
	@Length(min=0, max=64, message="KPIid长度必须介于 0 和 64 之间")
	public String getKpiId() {
		return kpiId;
	}

	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}
	
	@Length(min=0, max=64, message="KPI名称长度必须介于 0 和 64 之间")
	public String getKpiName() {
		return kpiName;
	}

	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}
	
	public Double getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(Double finalScore) {
		this.finalScore = finalScore;
	}
	
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
	public Double getSourceScore() {
		return sourceScore;
	}

	public void setSourceScore(Double sourceScore) {
		this.sourceScore = sourceScore;
	}
	
	public Double getJournalScore() {
		return journalScore;
	}

	public void setJournalScore(Double journalScore) {
		this.journalScore = journalScore;
	}
	
	public Double getModifyScore() {
		return modifyScore;
	}

	public void setModifyScore(Double modifyScore) {
		this.modifyScore = modifyScore;
	}
	
}