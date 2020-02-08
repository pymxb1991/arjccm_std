/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.iot.configure.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 报警配置Entity
 * @author cby
 * @version 2019-07-25
 */
public class CcmReportConfigure extends DataEntity<CcmReportConfigure> {
	
	private static final long serialVersionUID = 1L;
	private String reportType;		// 报警类型
	private String reportMusic;		// 报警音乐
	private String reportAimages;		// 报警地图图片
	private String topRtype;		// 头部展示类型
	private String topRstype;		// 头部展示未处理类型
	private String topColor;		// 头部展示颜色
	private String topRimages;		// 头部展示图片
	private String icon;		// 图标
	private String remarks;
	
	public CcmReportConfigure() {
		super();
	}

	public CcmReportConfigure(String id){
		super(id);
	}

	
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Length(min=0, max=2, message="报警类型长度必须介于 0 和 2 之间")
	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
	@Length(min=0, max=255, message="报警音乐长度必须介于 0 和 255 之间")
	public String getReportMusic() {
		return reportMusic;
	}

	public void setReportMusic(String reportMusic) {
		this.reportMusic = reportMusic;
	}
	
	@Length(min=0, max=255, message="报警地图图片长度必须介于 0 和 255 之间")
	public String getReportAimages() {
		return reportAimages;
	}

	public void setReportAimages(String reportAimages) {
		this.reportAimages = reportAimages;
	}
	
	@Length(min=0, max=2, message="头部展示类型长度必须介于 0 和 2 之间")
	public String getTopRtype() {
		return topRtype;
	}

	public void setTopRtype(String topRtype) {
		this.topRtype = topRtype;
	}
	
	@Length(min=0, max=2, message="头部展示未处理类型长度必须介于 0 和 2 之间")
	public String getTopRstype() {
		return topRstype;
	}

	public void setTopRstype(String topRstype) {
		this.topRstype = topRstype;
	}
	
	@Length(min=0, max=255, message="头部展示颜色长度必须介于 0 和 255 之间")
	public String getTopColor() {
		return topColor;
	}

	public void setTopColor(String topColor) {
		this.topColor = topColor;
	}
	
	@Length(min=0, max=255, message="头部展示图片长度必须介于 0 和 255 之间")
	public String getTopRimages() {
		return topRimages;
	}

	public void setTopRimages(String topRimages) {
		this.topRimages = topRimages;
	}
	
	@Length(min=0, max=255, message="图标长度必须介于 0 和 255 之间")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}