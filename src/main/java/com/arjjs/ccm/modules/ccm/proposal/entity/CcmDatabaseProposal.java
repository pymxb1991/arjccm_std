/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.proposal.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 公告建议管理Entity
 * @author cdz
 * @version 2019-09-16
 */
@ApiModel
public class CcmDatabaseProposal extends DataEntity<CcmDatabaseProposal> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="公告标题")
	private String title;		// 公告标题
	@ApiModelProperty(value="公告内容")
	private String content;		// 公告内容
	@ApiModelProperty(value="用户id")
	private String userId;		// 用户id
	@ApiModelProperty(value="开始日期")
	private Date beginCreateDate;		// 开始日期
	@ApiModelProperty(value="结束日期")
	private Date endCreateDate;		// 结束日期
	@ApiModelProperty(value="每页条数")
	private int pageSize;		// 每页条数
	@ApiModelProperty(value="当前页初始的条数")
	private int pageNo;		// 当前页初始的条数
	@ApiModelProperty(value="当前页码")
	private int pageCurrent;	// 当前页码

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public CcmDatabaseProposal() {
		super();
	}

	public CcmDatabaseProposal(String id){
		super(id);
	}

	@Length(min=0, max=100, message="公告标题长度必须介于 0 和 100 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=1000, message="公告内容长度必须介于 0 和 1000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}