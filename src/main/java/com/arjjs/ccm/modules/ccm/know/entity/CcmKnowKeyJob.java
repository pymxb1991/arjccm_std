/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.arjjs.ccm.modules.sys.entity.Office;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 专项工作Entity
 * @author liang
 * @version 2018-03-23
 */
public class CcmKnowKeyJob extends DataEntity<CcmKnowKeyJob> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 专项名称
	private Date timeStart;		// 开始时间
	private Date timeEnd;		// 结束时间
	private String address;		// 地点
	private Office office;		// 发起部门
	private String abstracts;		// 专项工作简介
	private String plan;		// 专项工作计划
	private String achievement;		// 专项工作成果
	private String summarize;		// 总结
	private String file;		// 附件
	private Date beginTimeStart;		// 开始 开始时间
	private Date endTimeStart;		// 结束 开始时间
	
	public CcmKnowKeyJob() {
		super();
	}

	public CcmKnowKeyJob(String id){
		super(id);
	}

	@Length(min=0, max=64, message="专项名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}
	
	@Length(min=0, max=256, message="地点长度必须介于 0 和 256 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=256, message="专项工作简介长度必须介于 0 和 256 之间")
	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	
	@Length(min=0, max=512, message="专项工作计划长度必须介于 0 和 512 之间")
	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}
	
	@Length(min=0, max=1024, message="专项工作成果长度必须介于 0 和 1024 之间")
	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	
	@Length(min=0, max=512, message="总结长度必须介于 0 和 512 之间")
	public String getSummarize() {
		return summarize;
	}

	public void setSummarize(String summarize) {
		this.summarize = summarize;
	}
	
	@Length(min=0, max=256, message="附件长度必须介于 0 和 256 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public Date getBeginTimeStart() {
		return beginTimeStart;
	}

	public void setBeginTimeStart(Date beginTimeStart) {
		this.beginTimeStart = beginTimeStart;
	}
	
	public Date getEndTimeStart() {
		return endTimeStart;
	}

	public void setEndTimeStart(Date endTimeStart) {
		this.endTimeStart = endTimeStart;
	}
		
}