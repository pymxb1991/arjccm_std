package com.arjjs.ccm.tool.geoJson;

import com.arjjs.ccm.modules.ccm.org.entity.CcmDeviceVo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Properties {

	// 默认社区名称
	private String name;
	// 图标
	private String icon;
	// 其他信息
	private Map<String, Object> info;
	// 视频信息号源
	private Map<String, String> video;
	// 视频信息号源列表
	private List<CcmDeviceVo> videoList;
	// 该图形的中心点-信息
	private String[] coordinateCentre;
	private String color;
	private String userState;//用户状态
	private String type;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String[] getCoordinateCentre() {
		return coordinateCentre;
	}

	public void setCoordinateCentre(String[] coordinateCentre) {
		this.coordinateCentre = coordinateCentre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Map<String, Object> getInfo() {
		return info;
	}

	public void setInfo(Map<String, Object> info) {
		this.info = info;
	}

	public void addInfo(Map<String, Object> info) {
		if (this.info == null || this.info.size() < 1) {
			this.info = new LinkedHashMap<String, Object>();
		}
		this.info.putAll(info);
	}

	public Map<String, String> getVideo() {
		return video;
	}

	public void setVideo(Map<String, String> video) {
		this.video = video;
	}

	public List<CcmDeviceVo> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<CcmDeviceVo> videoList) {
		this.videoList = videoList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
