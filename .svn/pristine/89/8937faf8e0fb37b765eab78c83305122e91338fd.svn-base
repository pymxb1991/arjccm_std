/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.remote.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 远程控制Entity
 * @author cby
 * @version 2019-07-24
 */
public class CcmRemoteControl extends DataEntity<CcmRemoteControl> {
	
	private static final long serialVersionUID = 1L;
	private String equipmentName;		// 设备名称
	private String equipmentNum;		// 设备编号
	private String equipmentFrequency;		// 设备定位频率
	private String equipmentState;		// 设备状态
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	
	public CcmRemoteControl() {
		super();
	}

	public CcmRemoteControl(String id){
		super(id);
	}

	@Length(min=0, max=64, message="设备名称长度必须介于 0 和 64 之间")
	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	
	@Length(min=0, max=64, message="设备编号长度必须介于 0 和 64 之间")
	public String getEquipmentNum() {
		return equipmentNum;
	}

	public void setEquipmentNum(String equipmentNum) {
		this.equipmentNum = equipmentNum;
	}
	
	@Length(min=0, max=64, message="设备定位频率长度必须介于 0 和 64 之间")
	public String getEquipmentFrequency() {
		return equipmentFrequency;
	}

	public void setEquipmentFrequency(String equipmentFrequency) {
		this.equipmentFrequency = equipmentFrequency;
	}
	
	@Length(min=0, max=2, message="设备状态长度必须介于 0 和 2 之间")
	public String getEquipmentState() {
		return equipmentState;
	}

	public void setEquipmentState(String equipmentState) {
		this.equipmentState = equipmentState;
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