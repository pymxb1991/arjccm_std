package com.arjjs.ccm.modules.flat.analyst.entity;

public class Count {

	private String days;
	private String counts;
	private String times;
	private String userId;

	public Count() {
		super();

	}

	public Count(String days, String counts, String times, String userId) {
		super();
		this.days = days;
		this.counts = counts;
		this.times = times;
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getCounts() {
		return counts;
	}

	public void setCounts(String counts) {
		this.counts = counts;
	}

}
