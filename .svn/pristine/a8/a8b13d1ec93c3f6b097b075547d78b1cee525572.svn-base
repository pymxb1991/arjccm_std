/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.statistics.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 统计首页字典Entity
 * @author liuxue
 * @version 2018-07-24
 */
public class PlmStatisticsDict extends DataEntity<PlmStatisticsDict> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String content;		// 更多链接
	private String line;		// 数据行数
	private String type;		// 类型typeName
	private String typeName;	// 类型说明
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	
	public PlmStatisticsDict() {
		super();
	}

	public PlmStatisticsDict(String id){
		super(id);
	}
     
	public String getTitleAndTypeName() {
		
		String titleAndTypeName=title+"("+typeName+")";
		  if(typeName==null||typeName.equals("")) {
			  titleAndTypeName=title;
		  }
		return titleAndTypeName;
	}
	
	
	@Length(min=0, max=256, message="标题长度必须介于 0 和 256 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=256, message="链接长度必须介于 0 和 256 之间")
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
	
	@Length(min=0, max=50, message="类型长度必须介于 0 和50 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	@Length(min=0, max=256, message="类型说明长度必须介于 0 和 256 之间")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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