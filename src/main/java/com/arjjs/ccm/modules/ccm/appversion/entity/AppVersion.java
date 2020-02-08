/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.appversion.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * app版本Entity
 * @author lijiupeng
 * @version 2019-08-13
 */
public class AppVersion extends DataEntity<AppVersion> {
	
	private static final long serialVersionUID = 1L;
	private String versionCode;		// 版本code
	private String version;		// 版本号
	private String download;		// 下载地址
//	private String remarks;

//	@Override
//	public String getRemarks() {
//		return remarks;
//	}
//
//	@Override
//	public void setRemarks(String remarks) {
//		this.remarks = remarks;
//	}

	public AppVersion() {
		super();
	}

	public AppVersion(String id){
		super(id);
	}

	@Length(min=0, max=255, message="版本code长度必须介于 0 和 255 之间")
	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	
	@Length(min=0, max=255, message="版本号长度必须介于 0 和 255 之间")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@Length(min=0, max=255, message="下载地址长度必须介于 0 和 255 之间")
	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}
	
}