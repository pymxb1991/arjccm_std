/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.ncount.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 统计计数Entity
 * @author lc
 * @version 2018-07-12
 */
public class PbsNcount extends DataEntity<PbsNcount> {
	
	private static final long serialVersionUID = 1L;
	private String sType;		// 统计类别
	private String sName;		// 统计名称
	private Integer iNumber;		// 统计总数
	private String sSubitem;		// 是否有子统计项目
	private Date dtDate;		// 统计数据日期
	private String sDesc;		// 描述信息
	private String sSpare01;		// 其他内容1
	private String sSpare02;		// 其他内容2
	private String sSpare03;		// 其他内容3
	private String sSpare04;		// 其他内容4
	
	public PbsNcount() {
		super();
	}

	public PbsNcount(String id){
		super(id);
	}

	@Length(min=0, max=50, message="统计类别长度必须介于 0 和 50 之间")
	public String getSType() {
		return sType;
	}

	public void setSType(String sType) {
		this.sType = sType;
	}
	
	@Length(min=0, max=50, message="统计名称长度必须介于 0 和 50 之间")
	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}
	
	public Integer getINumber() {
		return iNumber;
	}

	public void setINumber(Integer iNumber) {
		this.iNumber = iNumber;
	}
	
	@Length(min=0, max=255, message="是否有子统计项目长度必须介于 0 和 255 之间")
	public String getSSubitem() {
		return sSubitem;
	}

	public void setSSubitem(String sSubitem) {
		this.sSubitem = sSubitem;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDtDate() {
		return dtDate;
	}

	public void setDtDate(Date dtDate) {
		this.dtDate = dtDate;
	}
	
	@Length(min=0, max=500, message="描述信息长度必须介于 0 和 500 之间")
	public String getSDesc() {
		return sDesc;
	}

	public void setSDesc(String sDesc) {
		this.sDesc = sDesc;
	}
	
	@Length(min=0, max=50, message="其他内容1长度必须介于 0 和 50 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}
	
	@Length(min=0, max=50, message="其他内容2长度必须介于 0 和 50 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}
	
	@Length(min=0, max=50, message="其他内容3长度必须介于 0 和 50 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}
	
	@Length(min=0, max=50, message="其他内容4长度必须介于 0 和 50 之间")
	public String getSSpare04() {
		return sSpare04;
	}

	public void setSSpare04(String sSpare04) {
		this.sSpare04 = sSpare04;
	}
	
}