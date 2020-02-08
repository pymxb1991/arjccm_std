/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 告警日志查询Entity
 * @author pengjianqiang
 * @version 2018-05-03
 */
public class CcmAlarmLog extends DataEntity<CcmAlarmLog> {
	
	private static final long serialVersionUID = 1L;
	private String objTable;		// 对象类型（表名）
	private String objId;		// 对象id
	private String alarmType;		// 告警类型
	private String param;		// 告警参数
	private Date beginCreateDate;		// 开始 发生时间
	private Date endCreateDate;		// 结束 发生时间
	
	public CcmAlarmLog() {
		super();
	}

	public CcmAlarmLog(String id){
		super(id);
	}

	@Length(min=0, max=64, message="对象类型（表名）长度必须介于 0 和 64 之间")
	public String getObjTable() {
		return objTable;
	}

	public void setObjTable(String objTable) {
		this.objTable = objTable;
	}
	
	@Length(min=0, max=64, message="对象id长度必须介于 0 和 64 之间")
	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	@Length(min=0, max=2, message="告警类型长度必须介于 0 和 2 之间")
	public String getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	
	@Length(min=0, max=128, message="告警参数长度必须介于 0 和 128 之间")
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
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
		
}