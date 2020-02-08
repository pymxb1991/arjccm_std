/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.car.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 车辆布控实体类Entity
 * @author lgh
 * @version 2019-06-03
 */
public class CcmCarControl extends DataEntity<CcmCarControl> {
	
	private static final long serialVersionUID = 1L;
	private String plateNumber;		// 车牌号码
	private String controlType;		// 布控类型
	private Date startTime;		// 布控开始时间
	private Date endTime;		// 布控结束时间
	private String plateColor;		// 车牌颜色
	private String controlReason;		// 布控原因
	private String contactNumber;		// 联系人电话
	private String canSee;		// 个人可见
	private String canDialogs;		// 报警弹框
	private String canVoice;		// 报警声音
	private String description;		// 布控描述
	private String ident;	//车主身份证号
	
	public CcmCarControl() {
		super();
	}

	public CcmCarControl(String id){
		super(id);
	}

	@Length(min=1, max=64, message="车牌号码长度必须介于 1 和 64 之间")
	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	@Length(min=0, max=2, message="布控类型长度必须介于 0 和 2 之间")
	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=2, message="车牌颜色长度必须介于 0 和 2 之间")
	public String getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}
	
	@Length(min=0, max=255, message="布控原因长度必须介于 0 和 255 之间")
	public String getControlReason() {
		return controlReason;
	}

	public void setControlReason(String controlReason) {
		this.controlReason = controlReason;
	}
	
	@Length(min=0, max=64, message="联系人电话长度必须介于 0 和 64 之间")
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	@Length(min=0, max=2, message="个人可见长度必须介于 0 和 2 之间")
	public String getCanSee() {
		return canSee;
	}

	public void setCanSee(String canSee) {
		this.canSee = canSee;
	}
	
	@Length(min=0, max=2, message="报警弹框长度必须介于 0 和 2 之间")
	public String getCanDialogs() {
		return canDialogs;
	}

	public void setCanDialogs(String canDialogs) {
		this.canDialogs = canDialogs;
	}
	
	@Length(min=0, max=2, message="报警声音长度必须介于 0 和 2 之间")
	public String getCanVoice() {
		return canVoice;
	}

	public void setCanVoice(String canVoice) {
		this.canVoice = canVoice;
	}
	
	@Length(min=0, max=255, message="布控描述长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Length(min=0, max=18, message="身份证长度必须介于 0 和 18 之间")
	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}
}