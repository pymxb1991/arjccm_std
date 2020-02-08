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
 * 重要会议Entity
 * @author liang
 * @version 2018-03-23
 */
public class CcmKnowKeyMeeting extends DataEntity<CcmKnowKeyMeeting> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private Date timeStart;		// 开始时间
	private Integer timeLong;		// 会议时长(分钟)
	private String address;		// 会议地点
	private Office office;		// 发起部门
	private String compere;		// 主持人
	private String attendee;		// 参加人员
	private String absentee;		// 缺席人员
	private String type;		// 会议类型
	private String content;		// 会议内容
	private String resolution;		// 会议决议
	private String handle;		// 会议督办
	private String file;		// 附件
	private Date beginTimeStart;		// 开始 开始时间
	private Date endTimeStart;		// 结束 开始时间
	
	public CcmKnowKeyMeeting() {
		super();
	}

	public CcmKnowKeyMeeting(String id){
		super(id);
	}

	@Length(min=0, max=64, message="标题长度必须介于 0 和 64 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	
	@Length(min=0, max=64, message="会议地点长度必须介于 0 和 64 之间")
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
	
	@Length(min=0, max=256, message="缺席人员长度必须介于 0 和 256 之间")
	public String getAbsentee() {
		return absentee;
	}

	public void setAbsentee(String absentee) {
		this.absentee = absentee;
	}
	
	@Length(min=0, max=2, message="会议类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=2000, message="会议内容长度必须介于 0 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=256, message="会议决议长度必须介于 0 和 256 之间")
	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	
	@Length(min=0, max=256, message="会议督办长度必须介于 0 和 256 之间")
	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
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