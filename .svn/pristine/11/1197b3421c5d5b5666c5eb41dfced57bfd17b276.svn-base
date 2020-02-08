/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.fence.entity;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmMobileDevice;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 电子围栏实体类Entity
 * @author lgh
 * @version 2019-05-31
 */
public class CcmElectronicFence extends DataEntity<CcmElectronicFence> {

	private static final long serialVersionUID = 1L;
	private String fenceName;		// 围栏名称
	private String alarmRule;		// 报警规则
	private String gravity;		// 绘制轨迹
	private Date startTime;		// 开始时间
	private Date endTime;		// 结束时间
	private String areaPoint;		//中心点
	private String areaMap;		//电子围栏范围





	public String getAreaPoint() {
		return areaPoint;
	}

	public void setAreaPoint(String areaPoint) {
		this.areaPoint = areaPoint;
	}

	public String getAreaMap() {
		return areaMap;
	}

	public void setAreaMap(String areaMap) {
		this.areaMap = areaMap;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}



	private CcmMobileDevice mobileInfo;		// 设备相关信息
	public CcmMobileDevice getMobileInfo() {
		return mobileInfo;
	}

	public void setMobileInfo(CcmMobileDevice mobileInfo) {
		this.mobileInfo = mobileInfo;
	}

	public CcmElectronicFence() {
		super();
	}

	public CcmElectronicFence(String id){
		super(id);
	}

	@Length(min=1, max=64, message="围栏名称长度必须介于 1 和 64 之间")
	public String getFenceName() {
		return fenceName;
	}

	public void setFenceName(String fenceName) {
		this.fenceName = fenceName;
	}

	@Length(min=1, max=3, message="报警规则长度必须介于 1 和 3 之间")
	public String getAlarmRule() {
		return alarmRule;
	}

	public void setAlarmRule(String alarmRule) {
		this.alarmRule = alarmRule;
	}

	@Length(min=0, max=512, message="绘制轨迹长度必须介于 0 和 512 之间")
	public String getGravity() {
		return gravity;
	}

	public void setGravity(String gravity) {
		this.gravity = gravity;
	}
}