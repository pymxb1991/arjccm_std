/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.addressbook.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.TreeEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 个人通讯录分组Entity
 * @author liucong
 * @version 2018-07-16
 */
public class PlmEmployeeGroups extends TreeEntity<PlmEmployeeGroups> {
	
	private static final long serialVersionUID = 1L;
	private PlmEmployeeGroups parent;		// 父id
	private String parentIds;		// 所有父级编号
	private String name;		// 分组名字
	private Integer sort;		// 排序
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	
	public PlmEmployeeGroups() {
		super();
	}

	public PlmEmployeeGroups(String id){
		super(id);
	}

	@JsonBackReference
	public PlmEmployeeGroups getParent() {
		return parent;
	}

	public void setParent(PlmEmployeeGroups parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=64, message="所有父级编号长度必须介于 0 和 64 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=0, max=64, message="分组名字长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
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
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}