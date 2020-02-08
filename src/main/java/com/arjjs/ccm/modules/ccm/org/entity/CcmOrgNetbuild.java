/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.TreeEntity;

/**
 * 网格化建设Entity
 * @author liang
 * @version 2018-01-22
 */
public class CcmOrgNetbuild extends TreeEntity<CcmOrgNetbuild> {
	
	private static final long serialVersionUID = 1L;
	private CcmOrgNetbuild parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	private Integer sort;		// 排序
	private String name;		// 网格名称
	private String netManName;		// 网格长姓名
	private String netPeoName;		// 网格员姓名
	private String sex;		// 性别
	private String nation;		// 民族
	private String politics;		// 政治面貌
	private Date birthday;		// 出生日期
	private String education;		// 学历
	private String telephone;		// 手机号码
	private String fixTel;		// 固定电话
	private Integer netManNum;		// 网格员数量
	private Integer netNum;		// 网格户数
	private Double netArea;		// 网格面积（平方米）
	
	public CcmOrgNetbuild() {
		super();
	}

	public CcmOrgNetbuild(String id){
		super(id);
	}

	@JsonBackReference
	public CcmOrgNetbuild getParent() {
		return parent;
	}

	public void setParent(CcmOrgNetbuild parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=2000, message="所有父级编号长度必须介于 0 和 2000 之间")
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
	
	@Length(min=0, max=100, message="网格名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="网格长姓名长度必须介于 0 和 50 之间")
	public String getNetManName() {
		return netManName;
	}

	public void setNetManName(String netManName) {
		this.netManName = netManName;
	}
	
	@Length(min=0, max=50, message="网格员姓名长度必须介于 0 和 50 之间")
	public String getNetPeoName() {
		return netPeoName;
	}

	public void setNetPeoName(String netPeoName) {
		this.netPeoName = netPeoName;
	}
	
	@Length(min=0, max=1, message="性别长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=2, message="民族长度必须介于 0 和 2 之间")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Length(min=0, max=2, message="政治面貌长度必须介于 0 和 2 之间")
	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=2, message="学历长度必须介于 0 和 2 之间")
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	
	@Length(min=0, max=18, message="手机号码长度必须介于 0 和 18 之间")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Length(min=0, max=18, message="固定电话长度必须介于 0 和 18 之间")
	public String getFixTel() {
		return fixTel;
	}

	public void setFixTel(String fixTel) {
		this.fixTel = fixTel;
	}
	
	public Integer getNetManNum() {
		return netManNum;
	}

	public void setNetManNum(Integer netManNum) {
		this.netManNum = netManNum;
	}
	
	public Integer getNetNum() {
		return netNum;
	}

	public void setNetNum(Integer netNum) {
		this.netNum = netNum;
	}
	
	public Double getNetArea() {
		return netArea;
	}

	public void setNetArea(Double netArea) {
		this.netArea = netArea;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}