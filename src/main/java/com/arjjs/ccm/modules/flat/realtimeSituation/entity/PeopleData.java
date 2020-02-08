package com.arjjs.ccm.modules.flat.realtimeSituation.entity;

public class PeopleData {

	private String devId;
	private String devName;
	private String devType;
	private String timeOccurs;
	private String x;
	private String y;
	private String code;
	private String userId; //用户id
	private String name; //姓名
	private String phone; //电话
	private String mobile; //手机
	private String policePhoneCode; //警务通
	private String interPhoneCode; //对讲机
	private String actionRecoderCode; //执法记录仪
	private String officeName;//部门名称
	private String defualtDevice; //优先级
	private String param;//连接视频参数
	private String userState;//用户状态（备勤、忙碌）
	
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getDefualtDevice() {
		return defualtDevice;
	}

	public void setDefualtDevice(String defualtDevice) {
		this.defualtDevice = defualtDevice;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPolicePhoneCode() {
		return policePhoneCode;
	}

	public void setPolicePhoneCode(String policePhoneCode) {
		this.policePhoneCode = policePhoneCode;
	}

	public String getInterPhoneCode() {
		return interPhoneCode;
	}

	public void setInterPhoneCode(String interPhoneCode) {
		this.interPhoneCode = interPhoneCode;
	}

	public String getActionRecoderCode() {
		return actionRecoderCode;
	}

	public void setActionRecoderCode(String actionRecoderCode) {
		this.actionRecoderCode = actionRecoderCode;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getTimeOccurs() {
		return timeOccurs;
	}

	public void setTimeOccurs(String timeOccurs) {
		this.timeOccurs = timeOccurs;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

}
