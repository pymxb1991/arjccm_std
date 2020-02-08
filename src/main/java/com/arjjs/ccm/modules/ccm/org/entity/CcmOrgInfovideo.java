/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 综治视联网信息中心Entity
 * @author fu
 * @version 2018-01-26
 */
public class CcmOrgInfovideo extends DataEntity<CcmOrgInfovideo> {
	
	private static final long serialVersionUID = 1L;
	private String centreName;		// 中心名称
	private String centreTel;		// 中心联系方式
	private String centreScale;		// 中心层级
	private String centreRespName;		// 中心负责人姓名
	private Integer specialtyNum;		// 专职工作人员数量
	private Double workArea;		// 办公用房面积（平方米）
	private Integer expenditure;		// 年度运行经费(元）
	private Integer videoSecuNum;		// 联网公共安全视频监控摄像机数量
	private Integer officeAllday;		// 是否24小时有人值守
	private String address;		// 所在地行政区划代码
	private String addDetail;		// 所在地详址
	private String areaMap;		// 区域图
	private String areaPoint;		// 中心点
	private String icon;		// 图标
	private String images;		// 综治视联网信息中心图片
	
	public CcmOrgInfovideo() {
		super();
	}

	public CcmOrgInfovideo(String id){
		super(id);
	}

	@Length(min=0, max=100, message="中心名称长度必须介于 0 和 100 之间")
	public String getCentreName() {
		return centreName;
	}

	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}
	
	@Length(min=0, max=50, message="中心联系方式长度必须介于 0 和 50 之间")
	public String getCentreTel() {
		return centreTel;
	}

	public void setCentreTel(String centreTel) {
		this.centreTel = centreTel;
	}
	
	@Length(min=0, max=2, message="中心层级长度必须介于 0 和 2 之间")
	public String getCentreScale() {
		return centreScale;
	}

	public void setCentreScale(String centreScale) {
		this.centreScale = centreScale;
	}
	
	@Length(min=0, max=50, message="中心负责人姓名长度必须介于 0 和 50 之间")
	public String getCentreRespName() {
		return centreRespName;
	}

	public void setCentreRespName(String centreRespName) {
		this.centreRespName = centreRespName;
	}
	
	public Integer getSpecialtyNum() {
		return specialtyNum;
	}

	public void setSpecialtyNum(Integer specialtyNum) {
		this.specialtyNum = specialtyNum;
	}
	
	public Double getWorkArea() {
		return workArea;
	}

	public void setWorkArea(Double workArea) {
		this.workArea = workArea;
	}
	
	public Integer getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(Integer expenditure) {
		this.expenditure = expenditure;
	}
	
	public Integer getVideoSecuNum() {
		return videoSecuNum;
	}

	public void setVideoSecuNum(Integer videoSecuNum) {
		this.videoSecuNum = videoSecuNum;
	}
	
	public Integer getOfficeAllday() {
		return officeAllday;
	}

	public void setOfficeAllday(Integer officeAllday) {
		this.officeAllday = officeAllday;
	}
	
	@Length(min=6, max=6, message="所在地行政区划代码长度必须6 位")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=80, message="所在地详址长度必须介于 0 和 80 之间")
	public String getAddDetail() {
		return addDetail;
	}

	public void setAddDetail(String addDetail) {
		this.addDetail = addDetail;
	}
	
	@Length(min=0, max=2000, message="区域图长度必须介于 0 和 2000 之间")
	public String getAreaMap() {
		return areaMap;
	}

	public void setAreaMap(String areaMap) {
		this.areaMap = areaMap;
	}
	
	@Length(min=0, max=40, message="中心点长度必须介于 0 和 40 之间")
	public String getAreaPoint() {
		return areaPoint;
	}

	public void setAreaPoint(String areaPoint) {
		this.areaPoint = areaPoint;
	}
	
	@Length(min=0, max=255, message="图标长度必须介于 0 和 255 之间")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Length(min=0, max=255, message="综治视联网信息中心图片长度必须介于 0 和 255 之间")
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
}