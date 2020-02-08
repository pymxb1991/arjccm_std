/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.work.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.arjjs.ccm.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 定时提醒Entity
 * @author liang
 * @version 2018-08-02
 */
public class CcmWorkTiming extends DataEntity<CcmWorkTiming> {
	
	private static final long serialVersionUID = 1L;
	private Date timing;		// 提醒时间
	private User user;		// 提醒人员
	private String details;		// 提醒内容
	private Date beginTiming;		// 开始 提醒时间
	private Date endTiming;		// 结束 提醒时间
	
	public CcmWorkTiming() {
		super();
	}

	public CcmWorkTiming(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTiming() {
		return timing;
	}

	public void setTiming(Date timing) {
		this.timing = timing;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=1024, message="提醒内容长度必须介于 0 和 1024 之间")
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	public Date getBeginTiming() {
		return beginTiming;
	}

	public void setBeginTiming(Date beginTiming) {
		this.beginTiming = beginTiming;
	}
	
	public Date getEndTiming() {
		return endTiming;
	}

	public void setEndTiming(Date endTiming) {
		this.endTiming = endTiming;
	}
		
}