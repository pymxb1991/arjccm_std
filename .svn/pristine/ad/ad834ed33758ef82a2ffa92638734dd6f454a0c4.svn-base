/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.Collections3;
import com.arjjs.ccm.common.utils.IdGen;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 巡逻计划Entity
 * 
 * @author arj
 * @version 2018-03-14
 */
public class CcmPatrolPlan extends DataEntity<CcmPatrolPlan> {

	private static final long serialVersionUID = 1L;
	private String name; // 名称
	private String responsibility; // 职责
	private Date beginDate; // 开始日期
	private Date endDate; // 结束日期
	private Date beginTime; // 开始时间
	private Date endTime; // 结束时间
	private String timeType; // 时间类型
	private String timeRule; // 时间规则
	private String content; // 巡检内容
	private String status; // 启动状态
	private String pointType; // 点位类型
	// 查询使用
	private Date curDate; // 当前日期
	private String curUser; // app 使用者当前的经历的巡逻计划
	private Date curBegin;  // 当前周、月的开始日期
	private Date curEnd;  // 当前周、月的结束日期
	private String statusLable;	//用于app接口列表显示
	private String timeTypeLable; // 时间类型
	
	// private String pointSort; // 点位顺序
	// private String ccmPatrolUserIds; // 巡逻人员IDs
	// private String ccmPatrolUserNames; // 巡逻人员Names
	// private String ccmPatrolPointIds; // 巡逻点位IDs
	// private String ccmPatrolPointNames; // 巡逻点位Names

	// 巡逻计划-人员
	private List<CcmPatrolUser> ccmPatrolUserList = Lists.newArrayList();

	// 巡逻计划-点位
	private List<CcmPatrolPoint> ccmPatrolPointList = Lists.newArrayList();

	// 巡逻计划-点位顺序
	private List<CcmPatrolPointsort> ccmPatrolPointSortList = Lists.newArrayList();

	public CcmPatrolPlan() {
		super();
	}

	public CcmPatrolPlan(String id) {
		super(id);
	}

	@Length(min = 0, max = 64, message = "名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min = 0, max = 64, message = "职责长度必须介于 0 和 64 之间")
	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	 @JsonFormat(pattern = "HH:mm:ss")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@JsonFormat(pattern = "HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Length(min = 0, max = 64, message = "时间类型长度必须介于 0 和 64 之间")
	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	@Length(min = 0, max = 64, message = "时间规则长度必须介于 0 和 64 之间")
	public String getTimeRule() {
		return timeRule;
	}

	public void setTimeRule(String timeRule) {
		this.timeRule = timeRule;
	}

	@Length(min = 0, max = 64, message = "巡检内容长度必须介于 0 和 64 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Length(min = 0, max = 64, message = "启动状态长度必须介于 0 和 64 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Length(min = 0, max = 64, message = "点位类型长度必须介于 0 和 64 之间")
	public String getPointType() {
		return pointType;
	}

	public void setPointType(String pointType) {
		this.pointType = pointType;
	}

	public List<CcmPatrolUser> getCcmPatrolUserList() {
		return ccmPatrolUserList;
	}

	public void setCcmPatrolUserList(List<CcmPatrolUser> ccmPatrolUserList) {
		this.ccmPatrolUserList = ccmPatrolUserList;
	}

	public List<CcmPatrolPoint> getCcmPatrolPointList() {
		return ccmPatrolPointList;
	}

	public void setCcmPatrolPointList(List<CcmPatrolPoint> ccmPatrolPointList) {
		this.ccmPatrolPointList = ccmPatrolPointList;
	}

	public String getCcmPatrolUserIds() {
		return Collections3.extractToString(ccmPatrolUserList, "user.id", ",");
	}

	// 在
	public void setCcmPatrolUserIds(String ccmPatrolUserIds) {
		this.ccmPatrolUserList = Lists.newArrayList();
		for (String id : StringUtils.split(ccmPatrolUserIds, ",")) {
			CcmPatrolUser entity = new CcmPatrolUser();
			entity.setId(IdGen.uuid());
			entity.setCcmPatrolPlan(this);
			entity.setUser(new User(id));
			entity.setPlanId(id);
			this.ccmPatrolUserList.add(entity);
		}
	}

	public String getCcmPatrolUserNames() {
		return Collections3.extractToString(ccmPatrolUserList, "user.name", ",");
	}

	public void setCcmPatrolUserNames(String ccmPatrolUserNames) {
		// 什么也不做
	}

	public String getCcmPatrolPointIds() {
		return Collections3.extractToString(ccmPatrolPointList, "id", ",");
	}

	public void setCcmPatrolPointIds(String ccmPatrolPointIds) {
		// 什么也不做 点位信息 单独添加
	}

	public String getCcmPatrolPointNames() {
		return Collections3.extractToString(ccmPatrolPointList, "name", ",");
	}

	public void setCcmPatrolPointNames(String ccmPatrolPointNames) {
		// 什么也不做 点位信息 单独添加
	}

	public String getTimeRuleName() {
		String timeRuleName = StringUtils.isNotBlank(timeRule) ? timeRule : "";
		// 周
		timeRuleName = timeRuleName.replace("W1", "周一");
		timeRuleName = timeRuleName.replace("W2", "周二");
		timeRuleName = timeRuleName.replace("W3", "周三");
		timeRuleName = timeRuleName.replace("W4", "周四");
		timeRuleName = timeRuleName.replace("W5", "周五");
		timeRuleName = timeRuleName.replace("W6", "周六");
		timeRuleName = timeRuleName.replace("W7", "周日");
		// 月
		timeRuleName = timeRuleName.replace("M", "日");
		return timeRuleName;
	}

	public String getPointSort() {
		return Collections3.extractToString(ccmPatrolPointSortList, "pointId", ";");
	}

	public void setPointSort(String pointSort) {
		this.ccmPatrolPointSortList = Lists.newArrayList();
		int num = 0;
		for (String id : StringUtils.split(pointSort, ";")) {
			CcmPatrolPointsort entity = new CcmPatrolPointsort();
			entity.setId(IdGen.uuid());
			entity.setPlanId(this.id);
			entity.setPointId(id);
			// 顺序增加 位序
			entity.setSort("" + (num++));
			this.ccmPatrolPointSortList.add(entity);
		}
	}

	public List<CcmPatrolPointsort> getCcmPatrolPointSortList() {
		return ccmPatrolPointSortList;
	}

	public void setCcmPatrolPointSortList(List<CcmPatrolPointsort> ccmPatrolPointSortList) {
		this.ccmPatrolPointSortList = ccmPatrolPointSortList;
	}

	public Date getCurDate() {
		return curDate;
	}

	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}

	public String getCurUser() {
		return curUser;
	}

	public void setCurUser(String curUser) {
		this.curUser = curUser;
	}

	public Date getCurBegin() {
		return curBegin;
	}

	public void setCurBegin(Date curBegin) {
		this.curBegin = curBegin;
	}

	public Date getCurEnd() {
		return curEnd;
	}

	public void setCurEnd(Date curEnd) {
		this.curEnd = curEnd;
	}

	public String getStatusLable() {
		return statusLable;
	}

	public void setStatusLable(String statusLable) {
		this.statusLable = statusLable;
	}

	public String getTimeTypeLable() {
		return timeTypeLable;
	}

	public void setTimeTypeLable(String timeTypeLable) {
		this.timeTypeLable = timeTypeLable;
	}

}