/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 在线办事Entity
 * @author fuxinshuang
 * @version 2018-03-14
 */
public class CcmServiceOnline extends DataEntity<CcmServiceOnline> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 事项分类
	private String applicant;		// 申请人
	private String ident;		// 公民身份号码
	private String telephone;		// 联系方式
	private String relevantPerson;		// 相关人
	private String description;		// 问题描述
	private String file;		// 提交资料
	private String status;		// 审核状态
	private String reviewer;		// 审核人
	private String content;		// 文字回复
	private String replyFile;		// 相关附件
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	private boolean isSelf;		// 是否只查询自己的办事申请
	private String typeLable;		// 用户app列表显示
	private String statusLable;	//用于app接口列表显示
	private String areaId;  //审核辖区
	private Date beginTime;		// 开始时间(查询时间条件)
	private Date endTime;		// 结束时间(查询时间条件)
	
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}
    
	
	
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public CcmServiceOnline() {
		super();
	}

	public CcmServiceOnline(String id){
		super(id);
	}

	@Length(min=0, max=2, message="事项分类长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=256, message="申请人长度必须介于 0 和 256 之间")
	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	
	@Length(min=0, max=18, message="公民身份号码长度必须介于 0 和 18 之间")
	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}
	
	@Length(min=0, max=50, message="联系方式长度必须介于 0 和 50 之间")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Length(min=0, max=256, message="相关人长度必须介于 0 和 256 之间")
	public String getRelevantPerson() {
		return relevantPerson;
	}

	public void setRelevantPerson(String relevantPerson) {
		this.relevantPerson = relevantPerson;
	}
	
	@Length(min=0, max=1000, message="问题描述长度必须介于 0 和 1000 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=1000, message="提交资料长度必须介于 0 和 1000 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	@Length(min=0, max=2, message="审核状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=256, message="审核人长度必须介于 0 和 256 之间")
	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	
	@Length(min=0, max=1000, message="文字回复长度必须介于 0 和 1000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=1000, message="相关附件长度必须介于 0 和 1000 之间")
	public String getReplyFile() {
		return replyFile;
	}

	public void setReplyFile(String replyFile) {
		this.replyFile = replyFile;
	}
	
	@Length(min=0, max=256, message="扩展1长度必须介于 0 和 256 之间")
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	@Length(min=0, max=256, message="扩展2长度必须介于 0 和 256 之间")
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	public boolean isSelf() {
		return isSelf;
	}

	public void setSelf(boolean isSelf) {
		this.isSelf = isSelf;
	}

	public String getTypeLable() {
		return typeLable;
	}

	public void setTypeLable(String typeLable) {
		this.typeLable = typeLable;
	}

	public String getStatusLable() {
		return statusLable;
	}

	public void setStatusLable(String statusLable) {
		this.statusLable = statusLable;
	}
	
}