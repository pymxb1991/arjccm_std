/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 业务学习Entity
 * @author liujindan
 * @version 2019-02-25
 */
public class PbsCourseinfo extends DataEntity<PbsCourseinfo> {
	
	private static final long serialVersionUID = 1L;
	private String sParentid;		// 科目id
	private String sType;		// 课程类型
	private String sName;		// 课程名称
	private String iSort;		// 展示排序
	private String sIconurl;		// 展示的缩略图
	private String sFiletype;		// 文件类型
	private String sFileurl;		// 文件路径
	private String sContent;		// 课程内容
	private String sSpare01;		// 备用字段
	private String sSpare02;		// 备用字段
	private String sSpare03;		// 备用字段
	private String sSpare04;		// 备用字段
	
	private User checkUser;		// 拦截器中使用该用户进行权限拦截，App的rest接口使用
	
	

	public User getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}

	public String getsParentid() {
		return sParentid;
	}

	public void setsParentid(String sParentid) {
		this.sParentid = sParentid;
	}

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getiSort() {
		return iSort;
	}

	public void setiSort(String iSort) {
		this.iSort = iSort;
	}

	public String getsIconurl() {
		return sIconurl;
	}

	public void setsIconurl(String sIconurl) {
		this.sIconurl = sIconurl;
	}

	public String getsFiletype() {
		return sFiletype;
	}

	public void setsFiletype(String sFiletype) {
		this.sFiletype = sFiletype;
	}

	public String getsFileurl() {
		return sFileurl;
	}

	public void setsFileurl(String sFileurl) {
		this.sFileurl = sFileurl;
	}

	public String getsContent() {
		return sContent;
	}

	public void setsContent(String sContent) {
		this.sContent = sContent;
	}

	public String getsSpare01() {
		return sSpare01;
	}

	public void setsSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}

	public String getsSpare02() {
		return sSpare02;
	}

	public void setsSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}

	public String getsSpare03() {
		return sSpare03;
	}

	public void setsSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}

	public String getsSpare04() {
		return sSpare04;
	}

	public void setsSpare04(String sSpare04) {
		this.sSpare04 = sSpare04;
	}

	public PbsCourseinfo() {
		super();
	}

	public PbsCourseinfo(String id){
		super(id);
	}

//	@Length(min=1, max=64, message="科目id长度必须介于 1 和 64 之间")
	public String getSParentid() {
		return sParentid;
	}

	public void setSParentid(String sParentid) {
		this.sParentid = sParentid;
	}
	
	@Length(min=1, max=10, message="课程类型长度必须介于 1 和 10 之间")
	public String getSType() {
		return sType;
	}

	public void setSType(String sType) {
		this.sType = sType;
	}
	
	@Length(min=1, max=50, message="课程名称长度必须介于 1 和 50 之间")
	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}
	
	@Length(min=0, max=10, message="展示排序长度必须介于 0 和 10 之间")
	public String getISort() {
		return iSort;
	}

	public void setISort(String iSort) {
		this.iSort = iSort;
	}
	
	@Length(min=0, max=1000, message="展示的缩略图长度必须介于 0 和 1000 之间")
	public String getSIconurl() {
		return sIconurl;
	}

	public void setSIconurl(String sIconurl) {
		this.sIconurl = sIconurl;
	}
	
	@Length(min=0, max=50, message="文件类型长度必须介于 0 和 50 之间")
	public String getSFiletype() {
		return sFiletype;
	}

	public void setSFiletype(String sFiletype) {
		this.sFiletype = sFiletype;
	}
	
	@Length(min=0, max=1000, message="文件路径长度必须介于 0 和 1000 之间")
	public String getSFileurl() {
		return sFileurl;
	}

	public void setSFileurl(String sFileurl) {
		this.sFileurl = sFileurl;
	}
	
	@Length(min=0, max=2000, message="课程内容长度必须介于 0 和 2000 之间")
	public String getSContent() {
		return sContent;
	}

	public void setSContent(String sContent) {
		this.sContent = sContent;
	}
	
	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}
	
	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}
	
	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}
	
	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSSpare04() {
		return sSpare04;
	}

	public void setSSpare04(String sSpare04) {
		this.sSpare04 = sSpare04;
	}
	
}