/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.message.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 消息管理Entity
 * @author cby
 * @version 2019-07-24
 */
public class CcmMessageManage extends DataEntity<CcmMessageManage> {
	
	private static final long serialVersionUID = 1L;
	private String equipmentId;		// 关联设备id
	private String equipmentNum;		// 关联设备编号
	private String message;		// 消息
	private String sendState;		// 发送状态
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	
	public CcmMessageManage() {
		super();
	}

	public CcmMessageManage(String id){
		super(id);
	}

	@Length(min=0, max=64, message="关联设备id长度必须介于 0 和 64 之间")
	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	
	@Length(min=0, max=64, message="关联设备编号长度必须介于 0 和 64 之间")
	public String getEquipmentNum() {
		return equipmentNum;
	}

	public void setEquipmentNum(String equipmentNum) {
		this.equipmentNum = equipmentNum;
	}
	
	@Length(min=0, max=64, message="消息长度必须介于 0 和 64 之间")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Length(min=0, max=2, message="发送状态长度必须介于 0 和 2 之间")
	public String getSendState() {
		return sendState;
	}

	public void setSendState(String sendState) {
		this.sendState = sendState;
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

	@Override
	public String toString() {
		return "CcmMessageManage [equipmentId=" + equipmentId + ", equipmentNum=" + equipmentNum + ", message="
				+ message + ", sendState=" + sendState + ", beginCreateDate=" + beginCreateDate + ", endCreateDate="
				+ endCreateDate + "]";
	}
		
	
}