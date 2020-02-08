/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.TreeEntity;
import com.arjjs.ccm.modules.sys.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 仓库管理Entity
 * @author dongqikai
 * @version 2018-06-27
 */
public class PlmStorage extends TreeEntity<PlmStorage> {
	
	private static final long serialVersionUID = 1L;
	private PlmStorage parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	private User user;		// 联系人ID
	private String code;		// 编号
	private String name;		// 名称
	private String simpleName;		// 简称
	private String type;		// 仓库类型
	private Integer sort;		// 排序
	private String stoAddress;		// 地址
	private String longItude = "0.0";		// 经度
	private String latItude = "0.0";		// 纬度
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	
	public PlmStorage() {
		super();
	}

	public PlmStorage(String id){
		super(id);
	}

	@JsonBackReference
	public PlmStorage getParent() {
		return parent;
	}

	public void setParent(PlmStorage parent) {
		this.parent = parent;
	}
	
	@Length(min=1, max=2000, message="所有父级编号长度必须介于 1 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=128, message="名称长度必须介于 1 和 128 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="简称长度必须介于 0 和 64 之间")
	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}
	
	@Length(min=1, max=1, message="仓库类型长度必须介于 1 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@NotNull(message="排序不能为空")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=256, message="地址长度必须介于 0 和 256 之间")
	public String getStoAddress() {
		return stoAddress;
	}

	public void setStoAddress(String stoAddress) {
		this.stoAddress = stoAddress;
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