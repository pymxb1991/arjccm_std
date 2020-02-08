/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.work.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 意见建议Entity
 * @author liang
 * @version 2018-06-12
 */
public class CcmWorkAdvise extends DataEntity<CcmWorkAdvise> {
	
	private static final long serialVersionUID = 1L;
	private Date datas;		// 时间
	private String details;		// 内容
	private String reply;		// 回复
	private Date beginDatas;		// 开始 时间
	private Date endDatas;		// 结束 时间

	private String pname;

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public CcmWorkAdvise() {
		super();
	}

	public CcmWorkAdvise(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDatas() {
		return datas;
	}

	public void setDatas(Date datas) {
		this.datas = datas;
	}
	
	@Length(min=0, max=1000, message="内容长度必须介于 0 和 1000 之间")
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	@Length(min=0, max=1000, message="回复长度必须介于 0 和 1000 之间")
	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
	
	public Date getBeginDatas() {
		return beginDatas;
	}

	public void setBeginDatas(Date beginDatas) {
		this.beginDatas = beginDatas;
	}
	
	public Date getEndDatas() {
		return endDatas;
	}

	public void setEndDatas(Date endDatas) {
		this.endDatas = endDatas;
	}
		
}