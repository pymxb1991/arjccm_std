/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.report.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 重大事项报备Entity
 * @author liang
 * @version 2018-03-30
 */
public class RiskEventGreat extends DataEntity<RiskEventGreat> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 事项名称
	private String department;		// 报告单位
	private String description;		// 事项描述
	private String influence;		// 可能造成的影响
	private String process;		// 处理结果
	private String respName;		// 责任人
	private Integer isReserve;		// 是否入库
	private String file;		// 附件
	private Date beginCreateDate;		// 开始 报告时间
	private Date endCreateDate;		// 结束 报告时间
	
	public RiskEventGreat() {
		super();
	}

	public RiskEventGreat(String id){
		super(id);
	}

	@Length(min=0, max=64, message="事项名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="报告单位长度必须介于 0 和 20 之间")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Length(min=0, max=1000, message="事项描述长度必须介于 0 和 1000 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=1000, message="可能造成的影响长度必须介于 0 和 1000 之间")
	public String getInfluence() {
		return influence;
	}

	public void setInfluence(String influence) {
		this.influence = influence;
	}
	
	@Length(min=0, max=1000, message="处理结果长度必须介于 0 和 1000 之间")
	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}
	
	@Length(min=0, max=64, message="责任人长度必须介于 0 和 64 之间")
	public String getRespName() {
		return respName;
	}

	public void setRespName(String respName) {
		this.respName = respName;
	}
	
	public Integer getIsReserve() {
		return isReserve;
	}

	public void setIsReserve(Integer isReserve) {
		this.isReserve = isReserve;
	}
	
	@Length(min=0, max=256, message="附件长度必须介于 0 和 256 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
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