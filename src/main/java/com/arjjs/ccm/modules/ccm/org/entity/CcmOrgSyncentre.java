/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.org.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 综治中心Entity
 * @author fu
 * @version 2018-01-18
 */
public class CcmOrgSyncentre extends DataEntity<CcmOrgSyncentre> {
	
	private static final long serialVersionUID = 1L;
	private String centreName;		// 综治中心名称
	private String centreTel;		// 综治中心联系方式
	private String centreScale;		// 综治中心层级
	private String respName;		// 负责人姓名
	private String respTel;		// 负责人联系方式
	private String constituteCom;		// 组成单位
	private String address;		// 所在地所在地行政区划代码
	private String addDetail;		// 所在地详址
	private String areaMap;		// 区域图
	private String areaPoint;		// 中心点
	private String icon;		// 图标
	private String images;		// 综治中心图片
	
	public CcmOrgSyncentre() {
		super();
	}

	public CcmOrgSyncentre(String id){
		super(id);
	}

	@Length(min=0, max=100, message="综治中心名称长度必须介于 0 和 100 之间")
	public String getCentreName() {
		return centreName;
	}

	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}
	
	@Length(min=0, max=50, message="综治中心联系方式长度必须介于 0 和 50 之间")
	public String getCentreTel() {
		return centreTel;
	}

	public void setCentreTel(String centreTel) {
		this.centreTel = centreTel;
	}
	
	@Length(min=0, max=2, message="综治中心层级长度必须介于 0 和 2 之间")
	public String getCentreScale() {
		return centreScale;
	}

	public void setCentreScale(String centreScale) {
		this.centreScale = centreScale;
	}
	
	@Length(min=0, max=50, message="负责人姓名长度必须介于 0 和 50 之间")
	public String getRespName() {
		return respName;
	}

	public void setRespName(String respName) {
		this.respName = respName;
	}
	
	@Length(min=0, max=50, message="负责人联系方式长度必须介于 0 和 50 之间")
	public String getRespTel() {
		return respTel;
	}

	public void setRespTel(String respTel) {
		this.respTel = respTel;
	}
	
	@Length(min=0, max=400, message="组成单位长度必须介于 0 和 400 之间")
	public String getConstituteCom() {
		return constituteCom;
	}

	public void setConstituteCom(String constituteCom) {
		this.constituteCom = constituteCom;
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
	
	@Length(min=0, max=255, message="综治中心图片长度必须介于 0 和 255 之间")
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
}