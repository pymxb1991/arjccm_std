package com.arjjs.ccm.tool.geoJson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.google.common.collect.Lists;

public class Geometry {
	// 图形类型
	private String type;
	// 像素点值 确保类型与值相同
	
	private List<?> coordinates;
	// 该图形的中心点-信息
	private String[] coordinateCentre;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@JsonGetter
	public List<?> getCoordinates() {
		return (null == coordinates || coordinates.size() ==0 )?(Lists.newArrayList()):coordinates;
	}
	public void setCoordinates(List<?> coordinates) {
		this.coordinates = coordinates;
	}
	
	public String[] getCoordinateCentre() {
		return coordinateCentre;
	}
	public void setCoordinateCentre(String[] coordinateCentre) {
		this.coordinateCentre = coordinateCentre;
	}
	
	
	
}
