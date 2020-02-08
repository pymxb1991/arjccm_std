/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.attendance.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;

/**
 * 社工考勤登记Entity
 * @author yiqingxuan
 * @version 2019-06-18
 */
public class CcmWorkerAttendance extends DataEntity<CcmWorkerAttendance> {
	
	private static final long serialVersionUID = 1L;
	private String type;  //类型
	private String gooutType;		// ccm_worker_attendance_goout_type 10 出差 20  外出
	private String leaveType;		// ccm_worker_attendance_leave_type 10 病假 20 事假 30 年休 40 调休
	private String workingtimeType;		// ccm_worker_attendance_workingtime_type 10 工作日  20 双休日  30 节假日
	private Date attendanceBegin;		// 开始时间
	private Date attendanceEnd;		// 结束时间
	private String days;		// 天数
	private String address;		// 地点
	private String cause;		// 事由
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private String  chuchai;   //出差
	private String  waichu;		 //外出
	private String  bingjia;	 //病假
	private String  shijia;	 //事假
	private String  nianxiu;	 //年休
	private String  tiaoxiu;	 //调休
	private String  jiaban;	 //加班
	private String createByname;
	private String officename;
	private String applyId;


	public CcmWorkerAttendance() {
		super();
	}

	public CcmWorkerAttendance(String id){
		super(id);
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@ExcelField(title="出差", align=1,sort=3)
	public String getChuchai() {
		return chuchai;
	}

	public void setChuchai(String chuchai) {
		this.chuchai = chuchai;
	}
	@ExcelField(title="外出", align=1,sort=4)
	public String getWaichu() {
		return waichu;
	}

	public void setWaichu(String waichu) {
		this.waichu = waichu;
	}
	@ExcelField(title="病假", align=1,sort=5)
	public String getBingjia() {
		return bingjia;
	}

	public void setBingjia(String bingjia) {
		this.bingjia = bingjia;
	}
	@ExcelField(title="事假", align=1,sort=6)
	public String getShijia() {
		return shijia;
	}

	public void setShijia(String shijia) {
		this.shijia = shijia;
	}
	@ExcelField(title="年休", align=1,sort=7)
	public String getNianxiu() {
		return nianxiu;
	}

	public void setNianxiu(String nianxiu) {
		this.nianxiu = nianxiu;
	}
	@ExcelField(title="调休", align=1,sort=8)
	public String getTiaoxiu() {
		return tiaoxiu;
	}

	public void setTiaoxiu(String tiaoxiu) {
		this.tiaoxiu = tiaoxiu;
	}
	@ExcelField(title="加班", align=1,sort=9)
	public String getJiaban() {
		return jiaban;
	}

	public void setJiaban(String jiaban) {
		this.jiaban = jiaban;
	}
	@ExcelField(title="登录人", align=1,sort=1)
	public String getCreateByname() {
		return createByname;
	}

	public void setCreateByname(String createByname) {
		this.createByname = createByname;
	}
	@ExcelField(title="部门", align=1,sort=2)
	public String getOfficename() {
		return officename;
	}

	public void setOfficename(String officename) {
		this.officename = officename;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
}