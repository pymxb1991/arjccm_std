/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.entity;

import org.hibernate.validator.constraints.Length;
import com.arjjs.ccm.modules.sys.entity.Area;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 自治组织人员管理Entity
 * @author liuyongjian
 * @version 2019-08-13
 */
public class CcmOrgPerson extends DataEntity<CcmOrgPerson> {
	
	private static final long serialVersionUID = 1L;
	private String images;     //人员照片
	private String name;		// 姓名
	private Area area;		// 所属社区
	private String organize;		// 所属组织
	private String organizeName;		// 所属组织name
	private String sex;		// 性别
	private String edu;		// 学历
	private String age;		// 年龄
	private String nation;		// 民族
	private String health;		// 健康情况
	private String duty;		// 现任职务
	private String phone;		// 联系电话
	
	public CcmOrgPerson() {
		super();
	}

	public CcmOrgPerson(String id){
		super(id);
	}

	public String getOrganizeName() {
		return organizeName;
	}

	public void setOrganizeName(String organizeName) {
		this.organizeName = organizeName;
	}

	@Length(min=0, max=255, message="图片长度必须介于 0 和 255 之间")
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	@Length(min=1, max=255, message="姓名长度必须介于 1 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	

	public String getOrganize() {
		return organize;
	}

	public void setOrganize(String organize) {
		this.organize = organize;
	}
	
	@Length(min=1, max=255, message="性别长度必须介于 1 和 255 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=1, max=255, message="学历长度必须介于 1 和 255 之间")
	public String getEdu() {
		return edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}
	
	@Length(min=1, max=255, message="年龄长度必须介于 1 和 255 之间")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@Length(min=1, max=255, message="民族长度必须介于 1 和 255 之间")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Length(min=1, max=255, message="健康情况长度必须介于 1 和 255 之间")
	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}
	
	@Length(min=1, max=255, message="现任职务长度必须介于 1 和 255 之间")
	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}
	
	@Length(min=1, max=255, message="联系电话长度必须介于 1 和 255 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}