/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.task.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.sys.entity.Office;

/**
 * 工作安排记录Entity
 * 
 * @author lc
 * @version 2018-05-03
 */
public class PbsTaskrec extends DataEntity<PbsTaskrec> {

	private static final long serialVersionUID = 1L;
	private PbsTaskrec sSuperiorid; // 上级任务
	private PbsTasktype sType; // 任务类型
	private String sResume; // 任务简述信息
	private String sContent; // 任务内容
	private Office sExecdepartment; // 执行任务人员的部门编号
	private PbsPartymem sExecutor; // 执行分配人员
	private PbsPartymem sBindmember; // 任务接收人员
	private String sStatus; // 记录状态
	private String sSpare01; // 备用内容1
	private String sSpare02; // 备用内容2
	private String sSpare03; // 备用内容3
	private String sSpare04; // 备用内容4
	private boolean isrelevance = false; //是否关联接收人和发布人
	private String getType; //  查询 类别
	private Date sDeadline;  // 截止日期
	private String sOptstatus; // 最终状态
	
	public PbsTaskrec() {
		super();
	}

	public PbsTaskrec(String id) {
		super(id);
	}

	public PbsTaskrec getsSuperiorid() {
		return sSuperiorid;
	}

	public void setsSuperiorid(PbsTaskrec sSuperiorid) {
		this.sSuperiorid = sSuperiorid;
	}

	public PbsTasktype getsType() {
		return sType;
	}

	public void setsType(PbsTasktype sType) {
		this.sType = sType;
	}

	@Length(min = 0, max = 500, message = "任务简述信息长度必须介于 0 和 500 之间")
	public String getSResume() {
		return sResume;
	}

	public void setSResume(String sResume) {
		this.sResume = sResume;
	}

	@Length(min = 0, max = 2000, message = "任务内容长度必须介于 0 和 2000 之间")
	public String getSContent() {
		return sContent;
	}

	public void setSContent(String sContent) {
		this.sContent = sContent;
	}

	@Length(min = 0, max = 100, message = "记录状态长度必须介于 0 和 100 之间")
	public String getSStatus() {
		return sStatus;
	}

	public void setSStatus(String sStatus) {
		this.sStatus = sStatus;
	}

	@Length(min = 0, max = 50, message = "备用内容1长度必须介于 0 和 50 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}

	@Length(min = 0, max = 50, message = "备用内容2长度必须介于 0 和 50 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}

	@Length(min = 0, max = 50, message = "备用内容3长度必须介于 0 和 50 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}

	@Length(min = 0, max = 50, message = "备用内容4长度必须介于 0 和 50 之间")
	public String getSSpare04() {
		return sSpare04;
	}

	public void setSSpare04(String sSpare04) {
		this.sSpare04 = sSpare04;
	}

	public Office getsExecdepartment() {
		return sExecdepartment;
	}

	public void setsExecdepartment(Office sExecdepartment) {
		this.sExecdepartment = sExecdepartment;
	}

	public PbsPartymem getsExecutor() {
		return sExecutor;
	}

	public void setsExecutor(PbsPartymem sExecutor) {
		this.sExecutor = sExecutor;
	}

	public PbsPartymem getsBindmember() {
		return sBindmember;
	}

	public void setsBindmember(PbsPartymem sBindmember) {
		this.sBindmember = sBindmember;
	}

	public boolean isIsrelevance() {
		return isrelevance;
	}

	public void setIsrelevance(boolean isrelevance) {
		this.isrelevance = isrelevance;
	}

	public String getGetType() {
		return getType;
	}

	public void setGetType(String getType) {
		this.getType = getType;
	}

	public Date getsDeadline() {
		return sDeadline;
	}

	public void setsDeadline(Date sDeadline) {
		this.sDeadline = sDeadline;
	}

	public String getsOptstatus() {
		return sOptstatus;
	}

	public void setsOptstatus(String sOptstatus) {
		this.sOptstatus = sOptstatus;
	}
	
}