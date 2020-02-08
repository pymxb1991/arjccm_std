/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.report.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 社情民意调查Entity
 * @author liang
 * @version 2018-03-31
 */
public class RiskMassesOpinion extends DataEntity<RiskMassesOpinion> {
	
	private static final long serialVersionUID = 1L;
	private RiskEventGreat riskEventGreat;		// 所属重大事项
	private String type;		// 类型
	private String file;		// 上传问卷结果
	private String fileName;		// 问卷名称
	private Date beginUpdateDate;		// 开始 提交时间
	private Date endUpdateDate;		// 结束 提交时间
	
	
	
	
	@Length(min=0, max=256, message="问卷名称长度必须介于 0 和 256 之间")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public RiskMassesOpinion() {
		super();
	}

	public RiskMassesOpinion(String id){
		super(id);
	}

	public RiskEventGreat getRiskEventGreat() {
		return riskEventGreat;
	}

	public void setRiskEventGreat(RiskEventGreat riskEventGreat) {
		this.riskEventGreat = riskEventGreat;
	}
	
	@Length(min=0, max=2, message="类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=256, message="上传问卷结果长度必须介于 0 和 256 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public Date getBeginUpdateDate() {
		return beginUpdateDate;
	}

	public void setBeginUpdateDate(Date beginUpdateDate) {
		this.beginUpdateDate = beginUpdateDate;
	}
	
	public Date getEndUpdateDate() {
		return endUpdateDate;
	}

	public void setEndUpdateDate(Date endUpdateDate) {
		this.endUpdateDate = endUpdateDate;
	}
		
}