/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.course.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 课程信息Entity
 * @author lc
 * @version 2018-04-11
 */
public class PbsCourseinfoEx extends DataEntity<PbsCourseinfoEx> {
	
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
	private String sParentname;   //课程名称
	
	public PbsCourseinfoEx() {
		super();
	}

	public PbsCourseinfoEx(String id){
		super(id);
	}

	@Length(min=1, max=64, message="科目id长度必须介于 1 和 64 之间")
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

	public String getsParentname() {
		return sParentname;
	}

	public void setsParentname(String sParentname) {
		this.sParentname = sParentname;
	}
	
}