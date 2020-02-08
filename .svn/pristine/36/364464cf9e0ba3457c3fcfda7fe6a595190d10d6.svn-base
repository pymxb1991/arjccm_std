/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.exam.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionLevel;
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionMajor;
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionProject;

/**
 * 试卷信息Entity
 * 
 * @author lc
 * @version 2018-06-11
 */
public class PbsExampaper extends DataEntity<PbsExampaper> {

	private static final long serialVersionUID = 1L;
	private String sName; // 试卷名称
	private String sTitle; // 展示标题
	private PbsQuestionMajor sMajor; // 专业编号
	private PbsQuestionProject sProject; // 科目编号
	private PbsQuestionLevel sLevel; // 难度等级编号
	private Date dtStart; // 考试开始时间
	private Date dtEnd; // 考试结束时间
	private Integer iExamtime; // 考试时间(分)
	private String sWay; // 出题方式
	private Integer iRatiocs; // 单选题分数比例
	private Integer iRatiocm; // 多选题分数比例
	private Integer iRatioj; // 判断题分数比例
	private Integer iRatioq; // 问答题分数比例
	private String sStat; // 发布标签
	private String sSpare01; // 其他内容1
	private String sSpare02; // 其他内容2
	private String sSpare03; // 其他内容3
	private String sSpare04; // 其他内容4
	private String writeFlag; // 是否写过
	private Integer iReport; // 结果数
	private String examActionId; // 考试结果ID

	public PbsExampaper() {
		super();
	}

	public PbsExampaper(String id) {
		super(id);
	}

	@Length(min = 1, max = 200, message = "试卷名称长度必须介于 1 和 200 之间")
	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}

	@Length(min = 1, max = 200, message = "展示标题长度必须介于 1 和 200 之间")
	public String getSTitle() {
		return sTitle;
	}

	public void setSTitle(String sTitle) {
		this.sTitle = sTitle;
	}

	public PbsQuestionMajor getsMajor() {
		return sMajor;
	}

	public void setsMajor(PbsQuestionMajor sMajor) {
		this.sMajor = sMajor;
	}

	public PbsQuestionProject getsProject() {
		return sProject;
	}

	public void setsProject(PbsQuestionProject sProject) {
		this.sProject = sProject;
	}

	public PbsQuestionLevel getsLevel() {
		return sLevel;
	}

	public void setsLevel(PbsQuestionLevel sLevel) {
		this.sLevel = sLevel;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDtStart() {
		return dtStart;
	}

	public void setDtStart(Date dtStart) {
		this.dtStart = dtStart;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDtEnd() {
		return dtEnd;
	}

	public void setDtEnd(Date dtEnd) {
		this.dtEnd = dtEnd;
	}

	public Integer getIExamtime() {
		return iExamtime;
	}

	public void setIExamtime(Integer iExamtime) {
		this.iExamtime = iExamtime;
	}

	@Length(min = 0, max = 20, message = "出题方式长度必须介于 0 和 20 之间")
	public String getSWay() {
		return sWay;
	}

	public void setSWay(String sWay) {
		this.sWay = sWay;
	}

	public Integer getIRatiocs() {
		return iRatiocs;
	}

	public void setIRatiocs(Integer iRatiocs) {
		this.iRatiocs = iRatiocs;
	}

	public Integer getIRatiocm() {
		return iRatiocm;
	}

	public void setIRatiocm(Integer iRatiocm) {
		this.iRatiocm = iRatiocm;
	}

	public Integer getIRatioj() {
		return iRatioj;
	}

	public void setIRatioj(Integer iRatioj) {
		this.iRatioj = iRatioj;
	}

	public Integer getIRatioq() {
		return iRatioq;
	}

	public void setIRatioq(Integer iRatioq) {
		this.iRatioq = iRatioq;
	}

	@Length(min = 0, max = 50, message = "其他内容1长度必须介于 0 和 50 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}

	@Length(min = 0, max = 50, message = "其他内容2长度必须介于 0 和 50 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}

	@Length(min = 0, max = 50, message = "其他内容3长度必须介于 0 和 50 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}

	@Length(min = 0, max = 50, message = "其他内容4长度必须介于 0 和 50 之间")
	public String getSSpare04() {
		return sSpare04;
	}

	public void setSSpare04(String sSpare04) {
		this.sSpare04 = sSpare04;
	}

	public String getsStat() {
		return sStat;
	}

	public void setsStat(String sStat) {
		this.sStat = sStat;
	}

	public String getWriteFlag() {
		return writeFlag;
	}

	public void setWriteFlag(String writeFlag) {
		this.writeFlag = writeFlag;
	}

	public Integer getiReport() {
		return iReport;
	}

	public void setiReport(Integer iReport) {
		this.iReport = iReport;
	}

	public String getExamActionId() {
		return examActionId;
	}

	public void setExamActionId(String examActionId) {
		this.examActionId = examActionId;
	}

	
}