/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.sys.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 日常工作数据报表Entity
 * @author pengjianqiang
 * @version 2018-04-14
 */
public class CcmWorkReportCount extends DataEntity<CcmWorkReportCount> {
	
	private static final long serialVersionUID = 1L;

	private Date beginDate;		// 开始日期
	private Date endDate;		// 结束日期
	
	private Office office;	// 所属部门
	private User user;	// 人员
	
	private Integer reportCount;		    // 日报数量
	private Integer eventIncidentCount;		// 上报案事件数
	private Integer eventAmbiCount;		    // 上报矛盾纠纷数
	private Integer eventRequestCount;		// 上报请求数
	private Integer eventDealCount;		    // 事件处理数
	private Integer eventDealScore;		    // 事件考核得分
	private Integer popUpdateCount;		    // 人口更新数
	private Integer popSpecialTailCount;	// 特殊人员跟进次数
	private Integer houseTailCount;		    // 房屋楼栋跟进次数
	private Integer orgTailCount;		    // 重点场所跟进次数
	private Integer houseCount;             // 楼栋跟进次数
	
	
	public Integer getHouseCount() {
		return houseCount;
	}
	public void setHouseCount(Integer houseCount) {
		this.houseCount = houseCount;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@ExcelField(title="姓名", align=2, sort=1)
	public String getUserName() {
		return user.getName();
	}

	@ExcelField(title="人员类型", align=2, sort=2, dictType="sys_user_type")
	public String getUserType() {
		return user.getUserType();
	}

	@ExcelField(title="所属部门", align=2, sort=3)
	public String getOfficeName() {
		return office.getName();
	}
	
	@JsonIgnore
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@ExcelField(title="日报数量", align=2, sort=4)
	public Integer getReportCount() {
		return reportCount;
	}
	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}

	@ExcelField(title="上报案事件数", align=2, sort=5)
	public Integer getEventIncidentCount() {
		return eventIncidentCount;
	}
	public void setEventIncidentCount(Integer eventIncidentCount) {
		this.eventIncidentCount = eventIncidentCount;
	}

	@ExcelField(title="上报矛盾纠纷数", align=2, sort=6)
	public Integer getEventAmbiCount() {
		return eventAmbiCount;
	}
	public void setEventAmbiCount(Integer eventAmbiCount) {
		this.eventAmbiCount = eventAmbiCount;
	}

	@ExcelField(title="上报请求数", align=2, sort=7)
	public Integer getEventRequestCount() {
		return eventRequestCount;
	}
	public void setEventRequestCount(Integer eventRequestCount) {
		this.eventRequestCount = eventRequestCount;
	}

	@ExcelField(title="事件处理数", align=2, sort=8)
	public Integer getEventDealCount() {
		return eventDealCount;
	}
	public void setEventDealCount(Integer eventDealCount) {
		this.eventDealCount = eventDealCount;
	}

	@ExcelField(title="事件考核得分", align=2, sort=9)
	public Integer getEventDealScore() {
		return eventDealScore;
	}
	public void setEventDealScore(Integer eventDealScore) {
		this.eventDealScore = eventDealScore;
	}

	@ExcelField(title="人口更新数", align=2, sort=10)
	public Integer getPopUpdateCount() {
		return popUpdateCount;
	}
	public void setPopUpdateCount(Integer popUpdateCount) {
		this.popUpdateCount = popUpdateCount;
	}

	@ExcelField(title="特殊人员跟进次数", align=2, sort=11)
	public Integer getPopSpecialTailCount() {
		return popSpecialTailCount;
	}
	public void setPopSpecialTailCount(Integer popSpecialTailCount) {
		this.popSpecialTailCount = popSpecialTailCount;
	}

	@ExcelField(title="房屋楼栋跟进次数", align=2, sort=12)
	public Integer getHouseTailCount() {
		return houseTailCount;
	}
	public void setHouseTailCount(Integer houseTailCount) {
		this.houseTailCount = houseTailCount;
	}

	@ExcelField(title="重点场所跟进次数", align=2, sort=13)
	public Integer getOrgTailCount() {
		return orgTailCount;
	}
	public void setOrgTailCount(Integer orgTailCount) {
		this.orgTailCount = orgTailCount;
	}
	
}