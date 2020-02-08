package com.arjjs.ccm.tool.geoJson;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.arjjs.ccm.common.config.Global;

public class GeoJSON {

	private static String FEATURECOLLECTION = "FeatureCollection";

	private String type;

	private String[] centpoint;

	private List<Features> features;

	//-------给地图返回数据时，支持分页显示
	private int pageNo = 1; // 当前页码
	private int pageSize = Integer.valueOf(Global.getConfig("page.pageSize")); // 页面大小,每页显示条数
	private long count;// 总记录数，设置为“-1”表示不查询总数

	public String getType() {
		return StringUtils.isEmpty(type) ? FEATURECOLLECTION : type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Features> getFeatures() {
		return features;
	}

	public void setFeatures(List<Features> features) {
		this.features = features;
	}

	public String[] getCentpoint() {
		return centpoint;
	}

	public void setCentpoint(String[] centpoint) {
		this.centpoint = centpoint;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}


}
