/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.sys.entity;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.TreeEntity;
import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 机构Entity
 * 
 * @author admin001
 * @version 2013-05-15
 */
public class PbsOffice extends TreeEntity<PbsOffice> {

	private static final long serialVersionUID = 1L;
	// private Office parent; // 父级编号
	// private String parentIds; // 所有父级编号
	private PbsArea area; // 归属区域
	private String code; // 机构编码
	// private String name; // 机构名称
	// private Integer sort; // 排序
	private String type; // 机构类型（1：公司；2：部门；3：小组）
	private String grade; // 机构等级（1：一级；2：二级；3：三级；4：四级）
	private String address; // 联系地址
	private String zipCode; // 邮政编码
	private String master; // 负责人
	private String phone; // 电话
	private String fax; // 传真
	private String email; // 邮箱
	private String useable;// 是否可用
	private PbsUser primaryPerson;// 主负责人
	private PbsUser deputyPerson;// 副负责人
	private List<String> childDeptList;// 快速添加子部门
	private String parentName; // 父类姓名 - 查询用
	private String memberId; // 使用的党员id - 查询用
	private String parentidstring; // 使用的父id
	private String areaidstring; // 归属区域编码
	private String removeme; 
	// 
	private String memberName; // 党员名次
	public PbsOffice() {
		super();
		// this.sort = 30;
		this.type = "2";
	}

	public PbsOffice(String id) {
		super(id);
	}

	public List<String> getChildDeptList() {
		return childDeptList;
	}

	public void setChildDeptList(List<String> childDeptList) {
		this.childDeptList = childDeptList;
	}

	@ExcelField(title = "是否启用", align = 2, sort = 10, dictType = "yes_no")
	public String getUseable() {
		return useable;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public PbsUser getPrimaryPerson() {
		return primaryPerson;
	}

	public void setPrimaryPerson(PbsUser primaryPerson) {
		this.primaryPerson = primaryPerson;
	}

	public PbsUser getDeputyPerson() {
		return deputyPerson;
	}

	public void setDeputyPerson(PbsUser deputyPerson) {
		this.deputyPerson = deputyPerson;
	}

	@JsonBackReference
	// @NotNull
	// @ExcelField(title = "父类部门", align = 2, sort = 25)
	public PbsOffice getParent() {
		return parent;
	}

	public void setParent(PbsOffice parent) {
		this.parent = parent;
	}

	@ExcelField(title = "父类ids", align = 2, sort = 55)
	@Length(min = 1, max = 2000)
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	@NotNull
	// @ExcelField(title = "所属区域", align = 2, sort = 20)
	public PbsArea getArea() {
		return area;
	}

	public void setArea(PbsArea area) {
		this.area = area;
	}

	@Length(min = 1, max = 100)
	@ExcelField(title = "部门名称", align = 2, sort = 5)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title = "顺位", align = 2, sort = 10)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Length(min = 1, max = 1)
	@ExcelField(title = "部门类型", align = 2, sort = 10, dictType = "sys_office_type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min = 1, max = 1)
	@ExcelField(title = "机构等级", align = 2, sort = 50)
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Length(min = 0, max = 255)
	@ExcelField(title = "联系地址", align = 2, sort = 15)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Length(min = 0, max = 100)
	@ExcelField(title = "邮政编码", align = 2, sort = 25)
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Length(min = 0, max = 100)
	@ExcelField(title = "负责人", align = 2, sort = 30)
	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	@Length(min = 0, max = 200)
	@ExcelField(title = "电话", align = 2, sort = 35)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Length(min = 0, max = 200)
	@ExcelField(title = "传真", align = 2, sort = 40)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Length(min = 0, max = 200)
	@ExcelField(title = "邮件", align = 2, sort = 45)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Length(min = 0, max = 100)
	@ExcelField(title = "区域编码", align = 2, sort = 50)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	// @ExcelField(title = "父类id", align = 2, sort = 60)
	// public String getParentId() {
	// return parent != null && parent.getId() != null ? parent.getId() : "0";
	// }

	public void setParentId(String parentId) {
		this.parent.id = parentId;
	}

	@Override
	public String toString() {
		return name;
	}

	// 新增用于查询
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

//	@SupCol(isUnique = "true", isHide = "true")
	@ExcelField(title = "ID", align = 2, sort = 1)
	public String getId() {
		return id;
	}

	@ExcelField(title = "父类id", align = 2, sort = 60)
	public String getParentidstring() {
		return parentidstring;
	}

	public void setParentidstring(String parentidstring) {
		this.parentidstring = parentidstring;
	}

	@ExcelField(title = "区域id", align = 2, sort = 60)
	public String getAreaidstring() {
		return areaidstring;
	}

	public void setAreaidstring(String areaidstring) {
		this.areaidstring = areaidstring;
	}

	public String getRemoveme() {
		return removeme;
	}

	public void setRemoveme(String removeme) {
		this.removeme = removeme;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
}