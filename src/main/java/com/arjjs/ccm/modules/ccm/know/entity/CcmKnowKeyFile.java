/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 重要文件Entity
 * @author liang
 * @version 2018-03-23
 */
public class CcmKnowKeyFile extends DataEntity<CcmKnowKeyFile> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 文件类型
	private String name;		// 文件名称
	private String abstracts;		// 摘要
	private String content;		// 内容
	private String file;		// 附件
	
	public CcmKnowKeyFile() {
		super();
	}

	public CcmKnowKeyFile(String id){
		super(id);
	}

	@Length(min=0, max=2, message="文件类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="文件名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=256, message="摘要长度必须介于 0 和 256 之间")
	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	
	@Length(min=0, max=2000, message="内容长度必须介于 0 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=256, message="附件长度必须介于 0 和 256 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
}