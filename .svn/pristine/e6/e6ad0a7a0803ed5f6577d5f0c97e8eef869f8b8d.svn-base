/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.entity;

import org.hibernate.validator.constraints.Length;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 公共机构管理Entity
 * @author liang
 * @version 2018-02-24
 */
public class CcmOrgCommonality extends DataEntity<CcmOrgCommonality> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 机构名称
	private String orgCode;		// 机构编号
	private String type;		// 机构类型
	private String adds;		// 地址
	private String principalName;		// 负责人姓名
	private String principalTel;		// 负责人电话
	private Integer institutionPeopleNum;		// 机构人数
	private Integer keyResourceNum;		// 重点资源数
	private String orgTel;		// 机构联系电话
	private String images;		// 图片
	private String files;		// 附件
	private Area area;		// 所属区域
	private String areaMap;		// 区域图
	private String areaPoint;		// 中心点
	private String image;		// 图标
	private String more1;		// sql
	private String more2;		// 冗余字段2
	private String more3;		// 冗余字段3
	private String typeLable;	//app接口使用

	private User checkUser;		// 拦截器中使用该用户进行权限拦截，App的rest接口使用
	public User getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}

	public CcmOrgCommonality() {
		super();
	}

	public CcmOrgCommonality(String id){
		super(id);
	}

	@Length(min=0, max=100, message="机构名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="机构编号长度必须介于 0 和 100 之间")
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	@Length(min=0, max=2, message="机构类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=200, message="地址长度必须介于 0 和 200 之间")
	public String getAdds() {
		return adds;
	}

	public void setAdds(String adds) {
		this.adds = adds;
	}
	
	@Length(min=0, max=64, message="负责人姓名长度必须介于 0 和 64 之间")
	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}
	
	@Length(min=0, max=18, message="负责人电话长度必须介于 0 和 18 之间")
	public String getPrincipalTel() {
		return principalTel;
	}

	public void setPrincipalTel(String principalTel) {
		this.principalTel = principalTel;
	}
	
	@Length(min=0, max=18, message="机构联系电话长度必须介于 0 和 18 之间")
	public String getOrgTel() {
		return orgTel;
	}

	public void setOrgTel(String orgTel) {
		this.orgTel = orgTel;
	}
	
	@Length(min=0, max=255, message="图片长度必须介于 0 和 255 之间")
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Length(min=0, max=2000, message="区域图长度必须介于 0 和 2000 之间")
	public String getAreaMap() {
		return areaMap;
	}

	public void setAreaMap(String areaMap) {
		this.areaMap = areaMap;
	}
	
	@Length(min=0, max=40, message="中心点长度必须介于 0 和 40 之间")
	public String getAreaPoint() {
		return areaPoint;
	}

	public void setAreaPoint(String areaPoint) {
		this.areaPoint = areaPoint;
	}
	
	@Length(min=0, max=255, message="图标长度必须介于 0 和 255 之间")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Length(min=0, max=100, message="冗余字段1长度必须介于 0 和 100 之间")
	public String getMore1() {
		return more1;
	}

	public void setMore1(String more1) {
		this.more1 = more1;
	}
	
	@Length(min=0, max=100, message="冗余字段2长度必须介于 0 和 100 之间")
	public String getMore2() {
		return more2;
	}

	public void setMore2(String more2) {
		this.more2 = more2;
	}
	
	@Length(min=0, max=100, message="冗余字段3长度必须介于 0 和 100 之间")
	public String getMore3() {
		return more3;
	}

	public void setMore3(String more3) {
		this.more3 = more3;
	}
	public String getTypeLable() {
		return typeLable;
	}
	public void setTypeLable(String typeLable) {
		this.typeLable = typeLable;
	}
	public Integer getInstitutionPeopleNum() {
		return institutionPeopleNum;
	}
	public void setInstitutionPeopleNum(Integer institutionPeopleNum) {
		this.institutionPeopleNum = institutionPeopleNum;
	}
	public Integer getKeyResourceNum() {
		return keyResourceNum;
	}
	public void setKeyResourceNum(Integer keyResourceNum) {
		this.keyResourceNum = keyResourceNum;
	}
	
}