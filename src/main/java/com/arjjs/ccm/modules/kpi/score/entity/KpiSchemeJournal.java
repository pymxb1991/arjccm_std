/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.kpi.score.entity;

import com.arjjs.ccm.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 绩效流水Entity
 * @author liang
 * @version 2018-04-11
 */
public class KpiSchemeJournal extends DataEntity<KpiSchemeJournal> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 被考核人
	private Double score;		// 分数
	private String reson;		// 事由
	private Date notifyDate;		// 通报日期
	private String notifyType;		// 通报形式
	private String notifyStaffName;		// 通报人姓名
	private Date beginNotifyDate;		// 开始 通报日期
	private Date endNotifyDate;		// 结束 通报日期
	
	public KpiSchemeJournal() {
		super();
	}

	public KpiSchemeJournal(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
	@Length(min=0, max=255, message="事由长度必须介于 0 和 255 之间")
	public String getReson() {
		return reson;
	}

	public void setReson(String reson) {
		this.reson = reson;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getNotifyDate() {
		return notifyDate;
	}

	public void setNotifyDate(Date notifyDate) {
		this.notifyDate = notifyDate;
	}
	
	@Length(min=0, max=64, message="通报形式长度必须介于 0 和 64 之间")
	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}
	
	@Length(min=0, max=64, message="通报人姓名长度必须介于 0 和 64 之间")
	public String getNotifyStaffName() {
		return notifyStaffName;
	}

	public void setNotifyStaffName(String notifyStaffName) {
		this.notifyStaffName = notifyStaffName;
	}
	
	public Date getBeginNotifyDate() {
		return beginNotifyDate;
	}

	public void setBeginNotifyDate(Date beginNotifyDate) {
		this.beginNotifyDate = beginNotifyDate;
	}
	
	public Date getEndNotifyDate() {
		return endNotifyDate;
	}

	public void setEndNotifyDate(Date endNotifyDate) {
		this.endNotifyDate = endNotifyDate;
	}
		
}