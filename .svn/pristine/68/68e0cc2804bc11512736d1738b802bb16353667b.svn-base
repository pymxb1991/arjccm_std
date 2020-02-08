/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 民政工作管理Entity
 * @author liang
 * @version 2018-08-02
 */
public class CcmServiceCivil extends DataEntity<CcmServiceCivil> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 工作类型
	private Date times;		// 时间
	private String adds;		// 服务场所
	private String receiver;		// 受理人
	private String gods;		// 被受理人
	private String details;		// 工作内容
	private String file;		// 附件
	private Date beginTimes;		// 开始 时间
	private Date endTimes;		// 结束 时间
	
	public CcmServiceCivil() {
		super();
	}

	public CcmServiceCivil(String id){
		super(id);
	}

	@Length(min=0, max=2, message="工作类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTimes() {
		return times;
	}

	public void setTimes(Date times) {
		this.times = times;
	}
	
	@Length(min=0, max=256, message="服务场所长度必须介于 0 和 256 之间")
	public String getAdds() {
		return adds;
	}

	public void setAdds(String adds) {
		this.adds = adds;
	}
	
	@Length(min=0, max=64, message="受理人长度必须介于 0 和 64 之间")
	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	@Length(min=0, max=64, message="被受理人长度必须介于 0 和 64 之间")
	public String getGods() {
		return gods;
	}

	public void setGods(String gods) {
		this.gods = gods;
	}
	
	@Length(min=0, max=1024, message="工作内容长度必须介于 0 和 1024 之间")
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	@Length(min=0, max=256, message="附件长度必须介于 0 和 256 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public Date getBeginTimes() {
		return beginTimes;
	}

	public void setBeginTimes(Date beginTimes) {
		this.beginTimes = beginTimes;
	}
	
	public Date getEndTimes() {
		return endTimes;
	}

	public void setEndTimes(Date endTimes) {
		this.endTimes = endTimes;
	}
		
}