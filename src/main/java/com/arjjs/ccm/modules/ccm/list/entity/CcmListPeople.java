/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.list.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 静态库和黑名单人员实体类Entity
 * @author jpy
 * @version 2019-06-05
 */
public class CcmListPeople extends DataEntity<CcmListPeople> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String listId;		// 所属库id
	private String papersType;		// 证件类型
	private String papersNumber;		// 证件号码
	private String sex;		// 性别
	private Date birthday;		// 出生日期
	private String img;		// 上传图片
	
	private String type;		// 名单库类型
	private String listName;		// 所属库名称
	
	public CcmListPeople() {
		super();
	}

	public CcmListPeople(String id){
		super(id);
	}

	@Length(min=1, max=64, message="姓名长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message="所属库id不能为空")
	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}
	
	@Length(min=1, max=2, message="证件类型长度必须介于 1 和 2 之间")
	public String getPapersType() {
		return papersType;
	}

	public void setPapersType(String papersType) {
		this.papersType = papersType;
	}
	
	@Length(min=0, max=64, message="证件号码长度必须介于 0 和 64 之间")
	public String getPapersNumber() {
		return papersNumber;
	}

	public void setPapersNumber(String papersNumber) {
		this.papersNumber = papersNumber;
	}
	
	@Length(min=1, max=2, message="性别长度必须介于 1 和 2 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="出生日期不能为空")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=255, message="上传图片长度必须介于 0 和 255 之间")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	@Length(min=1, max=2, message="类型长度必须介于 1 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=64, message="姓名长度必须介于 1 和 64 之间")
	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}
	
}