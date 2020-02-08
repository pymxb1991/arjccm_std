/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.holiday.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 节假日管理Entity
 * @author yiqingxuan
 * @version 2019-06-18
 */
public class CcmWorkerHoliday extends DataEntity<CcmWorkerHoliday> {
	
	private static final long serialVersionUID = 1L;
	private String holidayName;		// 假日名称
	private Date holidayBegin;		// 开始时间
	private Date holidayEnd;		// 结束时间
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CcmWorkerHoliday() {
		super();
	}

	public CcmWorkerHoliday(String id){
		super(id);
	}

	@Length(min=0, max=64, message="假日名称长度必须介于 0 和 64 之间")
	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getHolidayBegin() {
		return holidayBegin;
	}

	public void setHolidayBegin(Date holidayBegin) {
		this.holidayBegin = holidayBegin;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getHolidayEnd() {
		return holidayEnd;
	}

	public void setHolidayEnd(Date holidayEnd) {
		this.holidayEnd = holidayEnd;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
}