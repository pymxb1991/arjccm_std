/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 实时轨迹点Entity
 * @author arj
 * @version 2018-03-13
 */
public class CcmTracingpoint extends DataEntity<CcmTracingpoint> {
	
	private static final long serialVersionUID = 1L;
	private String areaPoint;		// 坐标
	private User user;		// 用户
	private String deviceId;		//移动设备ID
	private Date curDate;		// 当前时间
	private Date beginCurDate;		// 开始 当前时间
	private Date endCurDate;		// 结束 当前时间
	private String uploadStatus;		// 上传状态
	private CcmPeople ccmPeople;//实有人口
	
	public CcmPeople getCcmPeople() {
		return ccmPeople;
	}

	public void setCcmPeople(CcmPeople ccmPeople) {
		this.ccmPeople = ccmPeople;
	}

	public CcmTracingpoint() {
		super();
	}

	public CcmTracingpoint(String id){
		super(id);
	}

	@Length(min=0, max=64, message="坐标长度必须介于 0 和 64 之间")
	public String getAreaPoint() {
		return areaPoint;
	}

	public void setAreaPoint(String areaPoint) {
		this.areaPoint = areaPoint;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCurDate() {
		return curDate;
	}

	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}
	
	public Date getBeginCurDate() {
		return beginCurDate;
	}

	public void setBeginCurDate(Date beginCurDate) {
		this.beginCurDate = beginCurDate;
	}
	
	public Date getEndCurDate() {
		return endCurDate;
	}

	public void setEndCurDate(Date endCurDate) {
		this.endCurDate = endCurDate;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Length(min=0, max=1, message="上传状态长度必须介于 0 和 1 之间")
	public String getUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
		
}