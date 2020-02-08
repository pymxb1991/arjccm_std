/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.exam.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.sys.entity.Office;

/**
 * 考试信息Entity
 * 
 * @author lx
 * @version 2018-06-11
 */
public class PbsExamaction extends DataEntity<PbsExamaction> {

	private static final long serialVersionUID = 1L;
	private PbsExampaper sExampaper; // 试卷编号
	private PbsPartymem sExaminee; // 答题者编号
	private Date dtStart; // 开始答题时间
	private Date dtSubmit; // 提交时间
	private Integer iReport; // 考试成绩
	private String sStat; // 提交状态
	private String sSpare01; // 其他内容1
	private String sSpare02; // 其他内容2
	private String sSpare03; // 其他内容3
	private String sSpare04; // 其他内容4
	// 查询用对象
	private String Results; // 获取id以及结果
	private Office sDepartment; //获取部门
	private int attends; //获取部门参加人数
	private int times; // 获取考试参加的次数
	private String ofcphoto; //部门的照片
	
	private Date beginHappenDate;		// 开始 发生日期
	private Date endHappenDate;		// 结束 发生日期
	private String sPrimarykey01;

	
	public String getsPrimarykey01() {
		return sPrimarykey01;
	}

	public void setsPrimarykey01(String sPrimarykey01) {
		this.sPrimarykey01 = sPrimarykey01;
	}

	public PbsExamaction() {
		super();
	}

	public PbsExamaction(String id) {
		super(id);
	}

	public PbsExampaper getsExampaper() {
		return sExampaper;
	}

	public void setsExampaper(PbsExampaper sExampaper) {
		this.sExampaper = sExampaper;
	}

	public PbsPartymem getsExaminee() {
		return sExaminee;
	}

	public void setsExaminee(PbsPartymem sExaminee) {
		this.sExaminee = sExaminee;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDtStart() {
		return dtStart;
	}

	public void setDtStart(Date dtStart) {
		this.dtStart = dtStart;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDtSubmit() {
		return dtSubmit;
	}

	public void setDtSubmit(Date dtSubmit) {
		this.dtSubmit = dtSubmit;
	}

	public Integer getIReport() {
		return iReport;
	}

	public void setIReport(Integer iReport) {
		this.iReport = iReport;
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

	public String getResults() {
		return Results;
	}

	public void setResults(String result) {
		Results = result;
	}

	public Office getsDepartment() {
		return sDepartment;
	}

	public void setsDepartment(Office sDepartment) {
		this.sDepartment = sDepartment;
	}

	public String getOfcphoto() {
		return ofcphoto;
	}

	public void setOfcphoto(String ofcphoto) {
		this.ofcphoto = ofcphoto;
	}

	public int getAttends() {
		return attends;
	}

	public void setAttends(int attends) {
		this.attends = attends;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public Date getBeginHappenDate() {
		return beginHappenDate;
	}

	public void setBeginHappenDate(Date beginHappenDate) {
		this.beginHappenDate = beginHappenDate;
	}

	public Date getEndHappenDate() {
		return endHappenDate;
	}

	public void setEndHappenDate(Date endHappenDate) {
		this.endHappenDate = endHappenDate;
	}
	
}