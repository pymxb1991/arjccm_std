/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.appupload.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * App 上传记录表Entity
 * @author maoxb
 * @version 2019-05-16
 */
public class SysAppUpload extends DataEntity<SysAppUpload> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// APP名称
	private String type;		// 类别(0:android 或 1:ios)
	private String url;		// 下载地址
	private String version;		// 版本号
	private String size;		// 字节数(13位能支持9T,10位支持4G)
	private String md5Value;		// MD5值
	private String status;		// 最新标志(0:不是最新 或 1:是最新)
	private String files;		// 附件
	private String download;

	public SysAppUpload() {
		super();
	}

	public SysAppUpload(String id){
		super(id);
	}

	@Length(min=1, max=40, message="APP名称长度必须介于 1 和 40 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=1, message="类别长度必须介于 1 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=300, message="下载地址长度必须介于 0 和 300 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Length(min=1, max=10, message="版本号长度必须介于 1 和 10 之间")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@Length(min=0, max=10, message="字节数(13位能支持9T,10位支持4G)长度必须介于 0 和 10 之间")
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	@Length(min=0, max=32, message="MD5值长度必须介于 0 和 32 之间")
	public String getMd5Value() {
		return md5Value;
	}

	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
	}
	
	@Length(min=0, max=1, message="最新标志(0:不是最新 或 1:是最新)长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=2000, message="附件长度必须介于 0 和 2000 之间")
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}
}