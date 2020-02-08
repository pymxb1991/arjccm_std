package com.arjjs.ccm.modules.flat.alarm.entity;

import java.util.List;

import com.arjjs.ccm.modules.sys.utils.DictUtils;

public class BphAlarmTypeStat {
	private String dateTime;
	private String type;
	private String num;
	private String nums;//用来接收SQL赋值
	private String name;//警情类型名称
	private String officeName;//部门名称
	private String officeId;//部门ID
	private List<String> numList;//趋势中的数量
	public List<String> getNumList() {
		return numList;
	}
	public void setNumList(List<String> numList) {
		this.numList = numList;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getNums() {
		return nums;
	}
	public void setNums(String nums) {
		this.nums = nums;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getName() {
		name = DictUtils.getDictLabel(type, "bph_alarm_info_typecode", "");
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
}
