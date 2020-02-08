package com.arjjs.ccm.modules.ccm.rest.entity;

public class CcmEntityFileUpload {
	String data;	//图片信息
	String name;	//图片名
	String src;	//图片返回路径
	String type; //文件类型
	String fileName; //文件名
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	
//	@Override
//	public String toString() {
//		return "CLS_VO_Progress [uploadedBytes=" + uploadedBytes + ", totalBytes=" + totalBytes + ", uuid="
//				+ uuid + "]";
//	};
	
}
