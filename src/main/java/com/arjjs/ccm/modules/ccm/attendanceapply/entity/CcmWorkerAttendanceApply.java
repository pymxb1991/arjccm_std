/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.attendanceapply.entity;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * 加班请假申请Entity
 * @author yi
 * @version 2019-11-04
 */
public class CcmWorkerAttendanceApply extends DataEntity<CcmWorkerAttendanceApply> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 类型
	private String gooutType;		// ccm_worker_attendance_goout_type 10 出差 20  外出
	private String leaveType;		// ccm_worker_attendance_leave_type 10 病假 20 事假 30 年休 40 调休
	private String workingtimeType;		// ccm_worker_attendance_workingtime_type 10 工作日  20 双休日  30 节假日
	private Date attendanceBegin;		// 开始时间
	private Date attendanceEnd;		// 结束时间
	private String days;		// days
	private String address;		// 地点
	private String cause;		// 事由
	private String applyType;		// 申请状态  0 申请中 1 申请通过
	private String createByname;
	private String officename;
	private String officeid;
	private List<String> listofficeid;


	public CcmWorkerAttendanceApply() {
		super();
	}

	public CcmWorkerAttendanceApply(String id){
		super(id);
	}

	@Length(min=0, max=2, message="类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=2, message="ccm_worker_attendance_goout_type 10 出差 20  外出长度必须介于 0 和 2 之间")
	public String getGooutType() {
		return gooutType;
	}

	public void setGooutType(String gooutType) {
		this.gooutType = gooutType;
	}
	
	@Length(min=0, max=2, message="ccm_worker_attendance_leave_type 10 病假 20 事假 30 年休 40 调休长度必须介于 0 和 2 之间")
	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	
	@Length(min=0, max=2, message="ccm_worker_attendance_workingtime_type 10 工作日  20 双休日  30 节假日长度必须介于 0 和 2 之间")
	public String getWorkingtimeType() {
		return workingtimeType;
	}

	public void setWorkingtimeType(String workingtimeType) {
		this.workingtimeType = workingtimeType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAttendanceBegin() {
		return attendanceBegin;
	}

	public void setAttendanceBegin(Date attendanceBegin) {
		this.attendanceBegin = attendanceBegin;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAttendanceEnd() {
		return attendanceEnd;
	}

	public void setAttendanceEnd(Date attendanceEnd) {
		this.attendanceEnd = attendanceEnd;
	}
	
	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}
	
	@Length(min=0, max=64, message="地点长度必须介于 0 和 64 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=64, message="事由长度必须介于 0 和 64 之间")
	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
	
	@Length(min=0, max=2, message="申请状态  0 申请中 1 申请通过长度必须介于 0 和 2 之间")
	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getCreateByname() {
		return createByname;
	}

	public void setCreateByname(String createByname) {
		this.createByname = createByname;
	}

	public String getOfficename() {
		return officename;
	}

	public void setOfficename(String officename) {
		this.officename = officename;
	}

	public String getOfficeid() {
		return officeid;
	}

	public void setOfficeid(String officeid) {
		this.officeid = officeid;
	}

	public List<String> getListofficeid() {
		return listofficeid;
	}

	public void setListofficeid(List<String> listofficeid) {
		this.listofficeid = listofficeid;
	}
}