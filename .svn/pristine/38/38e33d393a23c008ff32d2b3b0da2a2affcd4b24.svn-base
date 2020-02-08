/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.statistics.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 统计首页Entity
 * @author liuxue
 * @version 2018-07-24
 */
public class PlmStatistics extends DataEntity<PlmStatistics> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 布局标题
	private String content;		// 链接
	private String pageTitle;		// 页标题
	private String pages;		// 页数
	private String longItude;		// 行位置
	private String latItude;		// 列位置
	private String type;		// 类型
	private String sort;		// 排序
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	
	public PlmStatistics() {
		super();
	}

	public PlmStatistics(String id){
		super(id);
	}

	@Length(min=1, max=256, message="布局标题长度必须介于 1 和 256 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=1, max=256, message="链接长度必须介于 1 和 256 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=256, message="页标题长度必须介于 0 和 256 之间")
	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	
	@Length(min=0, max=24, message="页数长度必须介于 0 和 24 之间")
	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}
	
	public String getLongItude() {
		return longItude;
	}

	public void setLongItude(String longItude) {
		this.longItude = longItude;
	}
	
	public String getLatItude() {
		return latItude;
	}

	public void setLatItude(String latItude) {
		this.latItude = latItude;
	}
	
	@Length(min=0, max=1, message="类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=100, message="排序长度必须介于 0 和 100 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
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