/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.statistics.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 统计首页明细Entity
 * @author liuxue
 * @version 2018-07-24
 */
public class PlmStatisticsDetail extends DataEntity<PlmStatisticsDetail> {
	
	private static final long serialVersionUID = 1L;
	private PlmStatisticsPlan parent;		// 方案编号
	private String title;		// 布局标题
	private String content;		// 内容链接
	private String longItude;		// 横向位置
	private String latItude;		// 纵向位置
	private String type;		// 类型
	private String sort;		// 排序
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	
	public PlmStatisticsDetail() {
		super();
	}

	public PlmStatisticsDetail(String id){
		super(id);
	}

	@JsonBackReference
	@NotNull(message="方案编号不能为空")
	public PlmStatisticsPlan getParent() {
		return parent;
	}

	public void setParent(PlmStatisticsPlan parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=256, message="布局标题长度必须介于 0 和 256 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=256, message="内容链接长度必须介于 0 和 256 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=24, message="横向位置长度必须介于 0 和 24 之间")
	public String getLongItude() {
		return longItude;
	}

	public void setLongItude(String longItude) {
		this.longItude = longItude;
	}
	
	@Length(min=0, max=24, message="纵向位置长度必须介于 0 和 24 之间")
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