/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.audit.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.modules.risk.report.entity.RiskReport;
import com.arjjs.ccm.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 重大事项审核Entity
 * @author liang
 * @version 2018-04-02
 */
public class RiskAudit extends DataEntity<RiskAudit> {
	
	private static final long serialVersionUID = 1L;
	private RiskReport riskReport;		// 所属报告
	private User user;		// 审核人
	private String readFlag;		// 阅读状态
	private Date readTime;		// 审核时间
	private String opinion;		// 审核意见
	private String result;		// 审核结果
	private String file;		// 附件
	

	
	@Length(min=0, max=256, message="附件长度必须介于 0 和 256 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public RiskAudit() {
		super();
	}

	public RiskAudit(String id){
		super(id);
	}

	public RiskReport getRiskReport() {
		return riskReport;
	}

	public void setRiskReport(RiskReport riskReport) {
		this.riskReport = riskReport;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=1, message="阅读标记（0：未读；1：已读）长度必须介于 0 和 1 之间")
	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	
	@Length(min=0, max=1000, message="审核意见长度必须介于 0 和 1000 之间")
	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	@Length(min=0, max=2, message="审核结果（01：待审核；02：未通过；03：已通过）长度必须介于 0 和 2 之间")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}