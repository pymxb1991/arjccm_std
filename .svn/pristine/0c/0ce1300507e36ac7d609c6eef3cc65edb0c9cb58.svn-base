/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.publicity.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 宣传信息实体类Entity
 * @author 刘永建
 * @version 2019-06-18
 */
public class CcmLogPublicity extends DataEntity<CcmLogPublicity> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String content;		// 内容
	private String pubtype;		// 类型
	private String files;		// 附件
	private String pubstatus;		// 状态
	
	public CcmLogPublicity() {
		super();
	}

	public CcmLogPublicity(String id){
		super(id);
	}

	@Length(min=0, max=200, message="标题长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=2000, message="内容长度必须介于 0 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=2, message="类型长度必须介于 0 和 1 之间")
	public String getType() {
		return pubtype;
	}

	public void setType(String type) {
		this.pubtype = type;
	}
	
	@Length(min=0, max=2000, message="附件长度必须介于 0 和 2000 之间")
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return pubstatus;
	}

	public void setStatus(String status) {
		this.pubstatus = status;
	}
	
}