/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.syslog.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 用户登录查询Entity
 * 
 * @author liujindan
 * @version 2019-07-09
 */
public class SysLog extends DataEntity<SysLog> {

	private static final long serialVersionUID = 1L;
	private String type; // 日志类型
	private String title; // 日志标题
	private String remoteAddr; // 操作IP地址
	private String userAgent; // 用户代理
	private String requestUri; // 请求URI
	private String method; // 操作方式
	private String params; // 操作提交的数据
	private String exception; // 异常信息
	private Date beginCreateDate; // 开始 创建时间
	private Date endCreateDate; // 结束 创建时间

	public SysLog() {
		super();
	}

	public SysLog(String id) {
		super(id);
	}

	@Length(min = 0, max = 1, message = "日志类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min = 0, max = 255, message = "日志标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Length(min = 0, max = 255, message = "操作IP地址长度必须介于 0 和 255 之间")
	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	@Length(min = 0, max = 255, message = "用户代理长度必须介于 0 和 255 之间")
	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	@Length(min = 0, max = 255, message = "请求URI长度必须介于 0 和 255 之间")
	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	@Length(min = 0, max = 5, message = "操作方式长度必须介于 0 和 5 之间")
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

}