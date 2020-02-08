/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 区域扩展表（区域查询）Entity
 * @author pengjianqiang
 * @version 2018-01-29
 */
public class SysArea extends DataEntity<SysArea> {
	
	private static final long serialVersionUID = 1L;
	private SysArea parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	private String name;		// 名称
	private String sort;		// 排序
	private String code;		// 区域编码
	private String type;		// 区域类型
	private String idEx;		// 区域扩展表id

	private String more1;		// sql
	private String areaPoint;		// 区域扩展表中的坐标点
	private String areaMap;		// 区域扩展表中的坐标范围
	private String num;		// 区域中的数量
	
	
	private String remarks;		// 备注
	
	
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMore1() {
		return more1;
	}

	public void setMore1(String more1) {
		this.more1 = more1;
	}

	public String getAreaPoint() {
		return areaPoint;
	}

	public void setAreaPoint(String areaPoint) {
		this.areaPoint = areaPoint;
	}

	public String getAreaMap() {
		return areaMap;
	}

	public void setAreaMap(String areaMap) {
		this.areaMap = areaMap;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getIdEx() {
		return idEx;
	}

	public void setIdEx(String idEx) {
		this.idEx = idEx;
	}

	public SysArea() {
		super();
	}

	public SysArea(String id){
		super(id);
	}

	@JsonBackReference
	@NotNull(message="父级编号不能为空")
	public SysArea getParent() {
		return parent;
	}

	public void setParent(SysArea parent) {
		this.parent = parent;
	}
	
	@Length(min=1, max=2000, message="所有父级编号长度必须介于 1 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=100, message="区域编码长度必须介于 0 和 100 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=1, message="区域类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SysArea{" +
				"parent=" + parent +
				", parentIds='" + parentIds + '\'' +
				", name='" + name + '\'' +
				", sort='" + sort + '\'' +
				", code='" + code + '\'' +
				", type='" + type + '\'' +
				", idEx='" + idEx + '\'' +
				", more1='" + more1 + '\'' +
				", areaPoint='" + areaPoint + '\'' +
				", areaMap='" + areaMap + '\'' +
				", num='" + num + '\'' +
				'}';
	}
}