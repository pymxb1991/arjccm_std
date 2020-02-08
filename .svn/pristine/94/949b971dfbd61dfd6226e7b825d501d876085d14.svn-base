/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.ccmsys.entity;


import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.arjjs.ccm.common.persistence.TreeEntity;

/**
 * 设备管理Entity
 * @author arj
 * @version 2018-01-25
 */
public class CcmLiveVideo extends TreeEntity<CcmLiveVideo> {
	
	private static final long serialVersionUID = 1L;
	private CcmLiveVideo parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	private String name;		// 名称
	
	public CcmLiveVideo() {
		super();
	}

	public CcmLiveVideo(String id){
		super(id);
	}

	@JsonBackReference
	public CcmLiveVideo getParent() {
		return parent;
	}

	public void setParent(CcmLiveVideo parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=200, message="所有父级编号长度必须介于 0 和 200 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
	
}