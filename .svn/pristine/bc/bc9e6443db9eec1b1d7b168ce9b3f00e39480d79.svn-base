/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.calendar.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.PlmTypes;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 工作日历Entity
 * @author liuxue
 * @version 2018-07-19
 */
public class PlmCalendar extends DataEntity<PlmCalendar> {
	
	private static final long serialVersionUID = 1L;
	private String subject;		// 日程标题
	private Date beginDate;		// 开始日期
	private Date beginTime;		// 开始时间
	private Date endDate;		// 结束日期
	private Date endTime;		// 结束时间
	private String isRepeat;		// 是否重复
	private String round;		// 重复周期
	private String importance;		// 重要性
	private String priority;		// 紧急程度
	private String tag;		// 日程类型
	private String state;		// 状态
	private String notify;		// 提示方式
	private String spot;		// 地点
	private String type;		// 类型
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	private Date beginBeginDate;		// 开始 开始日期
	private Date endBeginDate;		// 结束 开始日期
	private Date beginEndDate;		// 开始 结束时间
	private Date endEndDate;		// 结束 结束时间
	private List<String>   notifylist;
	private User user;		// user_id
	
	
	
	PlmTypes plmTypes;
	@SuppressWarnings("static-access")
	public PlmCalendar() {
		super();
		this.isRepeat= plmTypes.TYPE;
		this.importance= plmTypes.TYPE;
		this.priority= plmTypes.TYPE;
		this.notify= plmTypes.TYPE;
		
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getNotifylist() {
		return notifylist;
	}

	public void setNotifylist(List<String> notifylist) {
		this.notifylist = notifylist;
	}

	

	public PlmCalendar(String id){
		super(id);
	}

	@Length(min=0, max=256, message="日程标题长度必须介于 0 和 256 之间")
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=1, message="是否重复长度必须介于 0 和 1 之间")
	public String getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}
	
	@Length(min=0, max=1, message="重复周期长度必须介于 0 和 1 之间")
	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}
	
	@Length(min=0, max=1, message="重要性长度必须介于 0 和 1 之间")
	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}
	
	@Length(min=0, max=1, message="紧急程度长度必须介于 0 和 1 之间")
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	@Length(min=0, max=2, message="日程类型长度必须介于 0 和 2 之间")
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=6, message="提示方式长度必须介于 0 和 6之间")
	public String getNotify() {
		return notify;
	}

	public void setNotify(String notify) {
		this.notify = notify;
	}
	
	@Length(min=0, max=256, message="地点长度必须介于 0 和 256 之间")
	public String getSpot() {
		return spot;
	}

	public void setSpot(String spot) {
		this.spot = spot;
	}
	
	@Length(min=0, max=1, message="类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=256, message="扩展1长度必须介于 0 和 256 之间")
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	@Length(min=0, max=256, message="扩展2长度必须介于 0 和 256 之间")
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	
	public Date getBeginBeginDate() {
		return beginBeginDate;
	}

	public void setBeginBeginDate(Date beginBeginDate) {
		this.beginBeginDate = beginBeginDate;
	}
	
	public Date getEndBeginDate() {
		return endBeginDate;
	}

	public void setEndBeginDate(Date endBeginDate) {
		this.endBeginDate = endBeginDate;
	}
		
	public Date getBeginEndDate() {
		return beginEndDate;
	}

	public void setBeginEndDate(Date beginEndDate) {
		this.beginEndDate = beginEndDate;
	}
	
	public Date getEndEndDate() {
		return endEndDate;
	}

	public void setEndEndDate(Date endEndDate) {
		this.endEndDate = endEndDate;
	}
		
}