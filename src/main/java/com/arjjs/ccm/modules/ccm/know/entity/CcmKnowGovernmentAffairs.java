/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 民政事务Entity
 * @author liang
 * @version 2018-06-12
 */
public class CcmKnowGovernmentAffairs extends DataEntity<CcmKnowGovernmentAffairs> {
	
	private static final long serialVersionUID = 1L;
	private Date datas;		// 日期
	private String type;		// 类型
	private String name;		// 简介
	private String details;		// 详情
	private String file;		// 附件
	private Date beginDatas;		// 开始 日期
	private Date endDatas;		// 结束 日期
	
	public CcmKnowGovernmentAffairs() {
		super();
	}

	public CcmKnowGovernmentAffairs(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDatas() {
		return datas;
	}

	public void setDatas(Date datas) {
		this.datas = datas;
	}
	
	@Length(min=0, max=2, message="类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=100, message="简介长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1000, message="详情长度必须介于 0 和 1000 之间")
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	@Length(min=0, max=256, message="附件长度必须介于 0 和 256 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
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