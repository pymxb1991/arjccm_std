package com.arjjs.ccm.modules.flat.userBindingDevice.entity;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.Office;

public class UserBindingDevice extends DataEntity<UserBindingDevice>{

	private static final long serialVersionUID = 1L;
	private String userId; //用户id
	private Office company;	// 归属公司
	private Office office;	// 归属部门
	private String loginName; //登录名
	private String defualtDevice; //默认使用 0:警务通 1:执法记录仪 2:对讲机
	private String name; //姓名
	private String phone; //电话
	private String mobile; //手机
	private String policePhoneCode; //警务通
	private String interPhoneCode; //对讲机
	private String actionRecoderCode; //执法记录仪
	private String param;//连接视频参数
	
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Office getCompany() {
		return company;
	}
	public void setCompany(Office company) {
		this.company = company;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public String getDefualtDevice() {
		return defualtDevice;
	}
	public void setDefualtDevice(String defualtDevice) {
		this.defualtDevice = defualtDevice;
	}
	
}
