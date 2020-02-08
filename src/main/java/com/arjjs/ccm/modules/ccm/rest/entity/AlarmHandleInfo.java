package com.arjjs.ccm.modules.ccm.rest.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 警情详情实体类
 */

public class AlarmHandleInfo  implements Serializable {

	private static final long serialVersionUID = 1L;

	private String handleId;
	private String alarmId;
	private double handleDestinyX;
	private double handleDestinyY;
	private double handleReceiveX;
	private double handleReceiveY;
	private double handleArriveX;
	private double handleArriveY;
	private double handleFinishX;
	private double handleFinishY;
	private String handleResult;
	private String handleStatus;

	private String alarmOrderNum;
	private String alarmPoliceNum;
	private String alarmPoliceName;
	private String alarmManName;
	private String alarmManTel;
	private String alarmPlace;
	private double alarmX;
	private double alarmY;
	private double alarmZ;
	private String alarmContent;
	private String alarmGenerName;
	private String alarmTypeName;
	private String alarmClassName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date   alarmTime;
	private String alarmRecord;
	private String officeName;

	private String maxDispatchTime;
	private String maxArriveTime;
	Map<String,List<AlarmHandleUserStatus>> alarmHandleUserStatusMap = new HashMap<String,List<AlarmHandleUserStatus>>();
	//List<AlarmHandleUserStatus> alarmHandleUserStatuses  = new ArrayList<>();

	public String getHandleId() {
		return handleId;
	}

	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}

	public String getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}

	public double getHandleDestinyX() {
		return handleDestinyX;
	}

	public void setHandleDestinyX(double handleDestinyX) {
		this.handleDestinyX = handleDestinyX;
	}

	public double getHandleDestinyY() {
		return handleDestinyY;
	}

	public void setHandleDestinyY(double handleDestinyY) {
		this.handleDestinyY = handleDestinyY;
	}

	public double getHandleReceiveX() {
		return handleReceiveX;
	}

	public void setHandleReceiveX(double handleReceiveX) {
		this.handleReceiveX = handleReceiveX;
	}

	public double getHandleReceiveY() {
		return handleReceiveY;
	}

	public void setHandleReceiveY(double handleReceiveY) {
		this.handleReceiveY = handleReceiveY;
	}

	public double getHandleArriveX() {
		return handleArriveX;
	}

	public void setHandleArriveX(double handleArriveX) {
		this.handleArriveX = handleArriveX;
	}

	public double getHandleArriveY() {
		return handleArriveY;
	}

	public void setHandleArriveY(double handleArriveY) {
		this.handleArriveY = handleArriveY;
	}

	public double getHandleFinishX() {
		return handleFinishX;
	}

	public void setHandleFinishX(double handleFinishX) {
		this.handleFinishX = handleFinishX;
	}

	public double getHandleFinishY() {
		return handleFinishY;
	}

	public void setHandleFinishY(double handleFinishY) {
		this.handleFinishY = handleFinishY;
	}

	public String getHandleResult() {
		return handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}

	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}

	public String getAlarmOrderNum() {
		return alarmOrderNum;
	}

	public void setAlarmOrderNum(String alarmOrderNum) {
		this.alarmOrderNum = alarmOrderNum;
	}

	public String getAlarmPoliceNum() {
		return alarmPoliceNum;
	}

	public void setAlarmPoliceNum(String alarmPoliceNum) {
		this.alarmPoliceNum = alarmPoliceNum;
	}

	public String getAlarmPoliceName() {
		return alarmPoliceName;
	}

	public void setAlarmPoliceName(String alarmPoliceName) {
		this.alarmPoliceName = alarmPoliceName;
	}

	public String getAlarmManName() {
		return alarmManName;
	}

	public void setAlarmManName(String alarmManName) {
		this.alarmManName = alarmManName;
	}

	public String getAlarmManTel() {
		return alarmManTel;
	}

	public void setAlarmManTel(String alarmManTel) {
		this.alarmManTel = alarmManTel;
	}

	public String getAlarmPlace() {
		return alarmPlace;
	}

	public void setAlarmPlace(String alarmPlace) {
		this.alarmPlace = alarmPlace;
	}

	public double getAlarmX() {
		return alarmX;
	}

	public void setAlarmX(double alarmX) {
		this.alarmX = alarmX;
	}

	public double getAlarmY() {
		return alarmY;
	}

	public void setAlarmY(double alarmY) {
		this.alarmY = alarmY;
	}

	public double getAlarmZ() {
		return alarmZ;
	}

	public void setAlarmZ(double alarmZ) {
		this.alarmZ = alarmZ;
	}

	public String getAlarmContent() {
		return alarmContent;
	}

	public void setAlarmContent(String alarmContent) {
		this.alarmContent = alarmContent;
	}

	public String getAlarmGenerName() {
		return alarmGenerName;
	}

	public void setAlarmGenerName(String alarmGenerName) {
		this.alarmGenerName = alarmGenerName;
	}

	public String getAlarmTypeName() {
		return alarmTypeName;
	}

	public void setAlarmTypeName(String alarmTypeName) {
		this.alarmTypeName = alarmTypeName;
	}

	public String getAlarmClassName() {
		return alarmClassName;
	}

	public void setAlarmClassName(String alarmClassName) {
		this.alarmClassName = alarmClassName;
	}

	public Date getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	public String getAlarmRecord() {
		return alarmRecord;
	}

	public void setAlarmRecord(String alarmRecord) {
		this.alarmRecord = alarmRecord;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getMaxDispatchTime() {
		return maxDispatchTime;
	}

	public void setMaxDispatchTime(String maxDispatchTime) {
		this.maxDispatchTime = maxDispatchTime;
	}

	public String getMaxArriveTime() {
		return maxArriveTime;
	}

	public void setMaxArriveTime(String maxArriveTime) {
		this.maxArriveTime = maxArriveTime;
	}

/*	public List<AlarmHandleUserStatus> getAlarmHandleUserStatuses() {
		return alarmHandleUserStatuses;
	}

	public void setAlarmHandleUserStatuses(List<AlarmHandleUserStatus> alarmHandleUserStatuses) {
		this.alarmHandleUserStatuses = alarmHandleUserStatuses;
	}*/

	public Map<String, List<AlarmHandleUserStatus>> getAlarmHandleUserStatusMap() {
		return alarmHandleUserStatusMap;
	}

	public void setAlarmHandleUserStatusMap(Map<String, List<AlarmHandleUserStatus>> alarmHandleUserStatusMap) {
		this.alarmHandleUserStatusMap = alarmHandleUserStatusMap;
	}
}
