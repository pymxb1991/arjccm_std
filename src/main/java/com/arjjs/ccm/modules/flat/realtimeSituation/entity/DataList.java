package com.arjjs.ccm.modules.flat.realtimeSituation.entity;

import java.util.List;

import com.arjjs.ccm.modules.ccm.ccmsys.entity.CcmDevice;

public class DataList {

	private List<CcmDevice> videoDelList;
	private List<PeopleData> PeopleDataList;
	private String carData;
	private String peopleData;
	private String x;
	private String y;
	private String radius;
	private String type;
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public List<PeopleData> getPeopleDataList() {
		return PeopleDataList;
	}

	public void setPeopleDataList(List<PeopleData> peopleDataList) {
		PeopleDataList = peopleDataList;
	}

	public String getCarData() {
		return carData;
	}

	public void setCarData(String carData) {
		this.carData = carData;
	}

	public String getPeopleData() {
		return peopleData;
	}

	public void setPeopleData(String peopleData) {
		this.peopleData = peopleData;
	}

	public List<CcmDevice> getVideoDelList() {
		return videoDelList;
	}

	public void setVideoDelList(List<CcmDevice> videoDelList) {
		this.videoDelList = videoDelList;
	}
	
}
