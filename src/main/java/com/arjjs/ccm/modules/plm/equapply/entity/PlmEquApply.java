/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.equapply.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.ActEntity;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.modules.plm.storage.entity.PlmEquipment;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.PlmTypes;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 物资申请Entity
 * 
 * @author liucong
 * @version 2018-06-30
 */
public class PlmEquApply extends ActEntity<PlmEquApply> {

	PlmTypes plmTypes;
	private static final long serialVersionUID = 1L;
	private PlmEquipment equipment; // 装备物资id
	private PlmEquApplyDetail detail; // 装备明细id
	private String procInsId; // 流程实例ID
	private User user; // 申请人
	private String equipmentId; // 物品名称
	private String carId; // 车辆id
	private Date applyDate; // 日期
	private String applyBody; // 申请原因
	private String type; // 申请类别
	private String extend1; // 扩展1
	private String extend2; // 扩展2
	private String submitType; // 提交状态
	private Integer number; // 申请数量
	private String eId; // 物品id
	private String[] details; // 物资信息json
	private PlmAct plmAct; // 业务流程总表
	private String isGive;//是否归还
	private Date expectationDate;//预归还时间
	private String cancelFlag;   //是否可撤销
	public PlmEquApplyDetail getDetail() {
		return detail;
	}

	public void setDetail(PlmEquApplyDetail detail) {
		this.detail = detail;
	}

	public String geteId() {
		return eId;
	}

	public PlmEquipment getEquipment() {
		return equipment;
	}

	public void setEquipment(PlmEquipment equipment) {
		this.equipment = equipment;
	}

	public void seteId(String eId) {
		this.eId = eId;
	}

	@SuppressWarnings("static-access")
	public PlmEquApply() {
		super();
		this.isGive=plmTypes.TYPE;
	}

	public PlmEquApply(String id) {
		super(id);
	}

	@Length(min = 0, max = 64, message = "流程实例ID长度必须介于 0 和 64 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Length(min = 0, max = 64, message = "物品长度必须介于 0 和 64 之间")
	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	@Length(min = 0, max = 64, message = "车辆id长度必须介于 0 和 64 之间")
	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "日期不能为空")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getApplyBody() {
		return applyBody;
	}

	public void setApplyBody(String applyBody) {
		this.applyBody = applyBody;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min = 0, max = 256, message = "扩展1长度必须介于 0 和 256 之间")
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	@Length(min = 0, max = 256, message = "扩展2长度必须介于 0 和 256 之间")
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	@Length(min = 1, max = 1, message = "提交状态长度必须介于 1 和 1 之间")
	public String getSubmitType() {
		return submitType;
	}

	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String[] getDetails() {
		return details;
	}

	public void setDetails(String[] details) {
		this.details = details;
	}

	public PlmAct getPlmAct() {
		return plmAct;
	}

	public void setPlmAct(PlmAct plmAct) {
		this.plmAct = plmAct;
	}

	public String getIsGive() {
		return isGive;
	}

	public void setIsGive(String isGive) {
		this.isGive = isGive;
	}

	public Date getExpectationDate() {
		return expectationDate;
	}

	public void setExpectationDate(Date expectationDate) {
		this.expectationDate = expectationDate;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	
}