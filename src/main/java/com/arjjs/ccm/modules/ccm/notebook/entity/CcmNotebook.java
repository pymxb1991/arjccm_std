/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.notebook.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 记事本Entity
 * @author liuyongjian
 * @version 2019-06-18
 */
public class CcmNotebook extends DataEntity<CcmNotebook> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 笔记标题
	private String content;		// 内容
	private String files;		// 附件
	
	public CcmNotebook() {
		super();
	}

	public CcmNotebook(String id){
		super(id);
	}

	@Length(min=1, max=255, message="笔记标题长度必须介于 1 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=1, max=2000, message="内容长度必须介于 1 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=2000, message="附件长度必须介于 0 和 2000 之间")
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	
}