package com.arjjs.ccm.modules.flat.handle.entity;

public class BphAlarmHandleReceive {

	private String type;//1通知类2任务类
	private String title;//标题
	private String content;//内容
	private String destinationX;//目的地经度
	private String destinationY;//目的地纬度
	private String userId;//出警人
	private String taskArrange;//任务安排
	private String alarmId;		// 警情id
	private String handleCode;		// 处警单编号
	private String handlePoliceId;		// 处警员id
	private String task;		// 任务描述
	private String handleResult;		// 反馈信息
	private String status;		// 处置状态
	private String planId;		// 预案ID
	private String stepId;		// 步骤ID
	private String actionId;		// 动作ID
	private double destinyX;		// 目的地经度
	private double destinyY;		// 目的地纬度
	private String optionDesc;// 操作描述
	
	public String getOptionDesc() {
		return optionDesc;
	}
	public void setOptionDesc(String optionDesc) {
		this.optionDesc = optionDesc;
	}
	public String getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}
	public String getHandleCode() {
		return handleCode;
	}
	public void setHandleCode(String handleCode) {
		this.handleCode = handleCode;
	}
	public String getHandlePoliceId() {
		return handlePoliceId;
	}
	public void setHandlePoliceId(String handlePoliceId) {
		this.handlePoliceId = handlePoliceId;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getHandleResult() {
		return handleResult;
	}
	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDestinationX() {
		return destinationX;
	}
	public void setDestinationX(String destinationX) {
		this.destinationX = destinationX;
	}
	public String getDestinationY() {
		return destinationY;
	}
	public void setDestinationY(String destinationY) {
		this.destinationY = destinationY;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTaskArrange() {
		return taskArrange;
	}
	public void setTaskArrange(String taskArrange) {
		this.taskArrange = taskArrange;
	}
	public double getDestinyX() {
		return destinyX;
	}
	public void setDestinyX(double destinyX) {
		this.destinyX = destinyX;
	}
	public double getDestinyY() {
		return destinyY;
	}
	public void setDestinyY(double destinyY) {
		this.destinyY = destinyY;
	}
	
}
