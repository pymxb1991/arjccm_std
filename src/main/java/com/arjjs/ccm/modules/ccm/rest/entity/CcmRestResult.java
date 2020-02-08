package com.arjjs.ccm.modules.ccm.rest.entity;

import com.arjjs.ccm.common.config.Global;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * rest接口统一返回对象
 * 
 * @author pengjianqiang
 * @version 2018-02-02
 */
@ApiModel
public class CcmRestResult {
	@ApiModelProperty(value = "返回代码")
	private int code; // 返回代码，代码值见CcmRestType.java
	@ApiModelProperty(value = "返回信息对象D")
	private Object result; // 返回信息对象
	@ApiModelProperty(value = "数量")
	private Integer count; // 数量
	@ApiModelProperty(value = "图片服务器路径")
	private String fileServerUrl = Global.getConfig("FILE_UPLOAD_URL");// 图片服务器路径
	@ApiModelProperty(value = "返回消息")
	private String msg;// 返回消息
	@ApiModelProperty(value = "是否返回信息")
	private boolean returnFlag = false; // 是否返回信息

	public CcmRestResult() {
		code = -1;
		result = null;
	}

	public CcmRestResult(int code, Object result, Integer count) {
		super();
		this.code = code;
		this.result = result;
		this.count = count;
	}

	public CcmRestResult(int code, Object result) {
		super();
		this.code = code;
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getFileServerUrl() {
		return fileServerUrl;
	}

	public void setFileServerUrl(String fileServerUrl) {
		this.fileServerUrl = fileServerUrl;
	}

	public boolean isReturnFlag() {
		return returnFlag;
	}

	public void setReturnFlag(boolean returnFlag) {
		this.returnFlag = returnFlag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
