/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.log.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 跟踪信息Entity
 * @author arj
 * @version 2018-01-23
 */
public class CcmLogTail extends DataEntity<CcmLogTail> {
	
	private static final long serialVersionUID = 1L;
	private String relevanceId;		// 关联ID
	private String relevanceTable;		// 跟踪对象
	private String type;		// 类型
	private String tailCase;		// 跟踪事项
	private String tailContent;		// 跟踪内容
	private String tailPerson;		// 处理人员
	private Date tailTime;		// 时间
	private String more1;		// 处理人联系方式
	private String more2;		// 冗余字段2
	private String more3;		// 冗余字段3
	private String file;
	
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public CcmLogTail() {
		super();
	}

	public CcmLogTail(String id){
		super(id);
	}
	
	@NotEmpty(message="跟踪信息的关联信息不存在。")
	@Length(min=0, max=64, message="关联ID长度必须介于 0 和 64 之间")
	public String getRelevanceId() {
		return relevanceId;
	}

	public void setRelevanceId(String relevanceId) {
		this.relevanceId = relevanceId;
	}
	
	@Length(min=0, max=100, message="跟踪对象长度必须介于 0 和 100 之间")
	public String getRelevanceTable() {
		return relevanceTable;
	}

	public void setRelevanceTable(String relevanceTable) {
		this.relevanceTable = relevanceTable;
	}
	
	@Length(min=0, max=2, message="类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=100, message="跟踪事项长度必须介于 0 和 100 之间")
	public String getTailCase() {
		return tailCase;
	}

	public void setTailCase(String tailCase) {
		this.tailCase = tailCase;
	}
	
	public String getTailContent() {
		return tailContent;
	}

	public void setTailContent(String tailContent) {
		this.tailContent = tailContent;
	}
	
	@Length(min=0, max=100, message="处理人员长度必须介于 0 和 100 之间")
	public String getTailPerson() {
		return tailPerson;
	}

	public void setTailPerson(String tailPerson) {
		this.tailPerson = tailPerson;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTailTime() {
		return tailTime;
	}

	public void setTailTime(Date tailTime) {
		this.tailTime = tailTime;
	}
	
	@Length(min=0, max=100, message="处理人联系方式长度必须介于 0 和 100 之间")
	public String getMore1() {
		return more1;
	}

	public void setMore1(String more1) {
		this.more1 = more1;
	}
	
	@Length(min=0, max=100, message="冗余字段2长度必须介于 0 和 100 之间")
	public String getMore2() {
		return more2;
	}

	public void setMore2(String more2) {
		this.more2 = more2;
	}
	
	@Length(min=0, max=100, message="冗余字段3长度必须介于 0 和 100 之间")
	public String getMore3() {
		return more3;
	}

	public void setMore3(String more3) {
		this.more3 = more3;
	}
	
}