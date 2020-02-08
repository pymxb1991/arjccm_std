/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.home.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 门户字典Entity
 * @author liuxue
 * @version 2018-07-04
 */
public class PlmPortalDict extends DataEntity<PlmPortalDict> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String connect;		// 更多链接
	private String content;		// 内容链接
	private String line;		// 数据行数
	private String type;		// 类型
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	
	public PlmPortalDict() {
		super();
	}

	public PlmPortalDict(String id){
		super(id);
	}

	@Length(min=0, max=256, message="标题长度必须介于 0 和 256 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=256, message="更多链接长度必须介于 0 和 256 之间")
	public String getConnect() {
		return connect;
	}

	public void setConnect(String connect) {
		this.connect = connect;
	}
	
	@Length(min=0, max=256, message="内容链接长度必须介于 0 和 256 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=2, message="数据行数长度必须介于 0 和 2 之间")
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
	
	@Length(min=0, max=1, message="类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
}