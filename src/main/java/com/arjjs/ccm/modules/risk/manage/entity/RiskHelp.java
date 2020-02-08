/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.manage.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 帮助信息Entity
 * @author liang
 * @version 2018-03-31
 */
public class RiskHelp extends DataEntity<RiskHelp> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 标题
	private String type;		// 信息类型
	private String description;		// 摘要
	private String antistop;		// 关键词
	private String content;		// 内容
	private String importance;		// 重要程度
	private String file;		// 附件
	
	public RiskHelp() {
		super();
	}

	public RiskHelp(String id){
		super(id);
	}

	@Length(min=0, max=64, message="标题长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=2, message="信息类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=256, message="摘要长度必须介于 0 和 256 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=100, message="关键词长度必须介于 0 和 100 之间")
	public String getAntistop() {
		return antistop;
	}

	public void setAntistop(String antistop) {
		this.antistop = antistop;
	}
	
	@Length(min=0, max=2000, message="内容长度必须介于 0 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=2, message="重要程度长度必须介于 0 和 2 之间")
	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}
	
	@Length(min=0, max=256, message="附件长度必须介于 0 和 256 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
}