/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.arjjs.ccm.modules.sys.entity.Office;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 重要活动Entity
 * @author liang
 * @version 2018-03-23
 */
public class CcmKnowKeyActivity extends DataEntity<CcmKnowKeyActivity> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 活动名称
	private Date timeStart;		// 开始时间
	private Integer timeLong;		// 活动时长(分钟)
	private String address;		// 活动地点
	private Office office;		// 发起部门
	private String compere;		// 主持人
	private String attendee;		// 参加人员
	private String type;		// 活动类别
	private String file;		// 附件
	private Date beginTimeStart;		// 开始 开始时间
	private Date endTimeStart;		// 结束 开始时间
	
	public CcmKnowKeyActivity() {
		super();
	}

	public CcmKnowKeyActivity(String id){
		super(id);
	}

	@Length(min=0, max=64, message="活动名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}
	
	public Integer getTimeLong() {
		return timeLong;
	}

	public void setTimeLong(Integer timeLong) {
		this.timeLong = timeLong;
	}
	
	@Length(min=0, max=64, message="活动地点长度必须介于 0 和 64 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=256, message="主持人长度必须介于 0 和 256 之间")
	public String getCompere() {
		return compere;
	}

	public void setCompere(String compere) {
		this.compere = compere;
	}
	
	@Length(min=0, max=256, message="参加人员长度必须介于 0 和 256 之间")
	public String getAttendee() {
		return attendee;
	}

	public void setAttendee(String attendee) {
		this.attendee = attendee;
	}
	
	@Length(min=0, max=2, message="活动类别长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=256, message="附件长度必须介于 0 和 256 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public Date getBeginTimeStart() {
		return beginTimeStart;
	}

	public void setBeginTimeStart(Date beginTimeStart) {
		this.beginTimeStart = beginTimeStart;
	}
	
	public Date getEndTimeStart() {
		return endTimeStart;
	}

	public void setEndTimeStart(Date endTimeStart) {
		this.endTimeStart = endTimeStart;
	}
		
}