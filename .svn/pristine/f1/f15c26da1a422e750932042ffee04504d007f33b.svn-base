/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.risk.report.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.Collections3;
import com.arjjs.ccm.common.utils.IdGen;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.risk.audit.entity.RiskAudit;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.modules.sys.utils.UserUtils;
import com.google.common.collect.Lists;

/**
 * 事项评估报告Entity
 * @author liang
 * @version 2018-04-02
 */
public class RiskReport extends DataEntity<RiskReport> {
	
	private static final long serialVersionUID = 1L;
	private RiskEventGreat riskEventGreat;		// 所属重大事项
	private String isReserve;		// 是否入库
	private String file;		// 附件
	private Date beginUpdateDate;		// 开始 生成时间
	private Date endUpdateDate;		// 结束 生成时间
	private String fileName;//文件名
	private String readNum;		// 已读
	private String unReadNum;	// 未读

	private String unauditNum;	// 01：待审核
	private String noauditNum;	// 02：未通过
	private String auditNum;	// 03：已通过
	
	private List<RiskAudit> riskAuditList = Lists.newArrayList();//发送人员信息
	

	
	public List<RiskAudit> getRiskAuditList() {
		return riskAuditList;
	}

	public void setRiskAuditList(List<RiskAudit> riskAuditList) {
		this.riskAuditList = riskAuditList;
	}
	/**
	 * 获取发送记录用户ID
	 * @return
	 */
	public String getRiskAuditIds() {
		return Collections3.extractToString(riskAuditList, "user.id", ",") ;
	}
	/**
	 * 设置发送记录用户ID
	 * @return
	 */
	public void setRiskAuditIds(String riskAudit) {
		this.riskAuditList = Lists.newArrayList();
		for (String id : StringUtils.split(riskAudit, ",")){
			RiskAudit entity = new RiskAudit();
			entity.setId(IdGen.uuid());
			entity.setRiskReport(this);
			entity.setUser(new User(id));
			entity.setCreateBy(UserUtils.getUser());
			entity.setUpdateBy(UserUtils.getUser());
			Date date1 = new Date();
			entity.setUpdateDate(date1);
			entity.setCreateDate(date1);
			entity.setDelFlag("0");
			entity.setReadFlag("0");
			entity.setResult("01");
			this.riskAuditList.add(entity);
		}
	}
	/**
	 * 获取发送记录用户Name
	 * @return
	 */
	public String getRiskAuditNames() {
		return Collections3.extractToString(riskAuditList, "user.name", ",") ;
	}
	/**
	 * 设置发送记录用户Name
	 * @return
	 */
	public void setRiskAuditNames(String riskAudit) {
		// 什么也不做
	}
	
	
	
	public String getUnauditNum() {
		return unauditNum;
	}

	public void setUnauditNum(String unauditNum) {
		this.unauditNum = unauditNum;
	}

	public String getNoauditNum() {
		return noauditNum;
	}

	public void setNoauditNum(String noauditNum) {
		this.noauditNum = noauditNum;
	}

	public String getAuditNum() {
		return auditNum;
	}

	public void setAuditNum(String auditNum) {
		this.auditNum = auditNum;
	}

	public String getReadNum() {
		return readNum;
	}

	public void setReadNum(String readNum) {
		this.readNum = readNum;
	}

	public String getUnReadNum() {
		return unReadNum;
	}

	public void setUnReadNum(String unReadNum) {
		this.unReadNum = unReadNum;
	}
	
	@Length(min=0, max=256, message="文件名长度必须介于 0 和 256 之间")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	public RiskReport() {
		super();
	}

	public RiskReport(String id){
		super(id);
	}

	public RiskEventGreat getRiskEventGreat() {
		return riskEventGreat;
	}

	public void setRiskEventGreat(RiskEventGreat riskEventGreat) {
		this.riskEventGreat = riskEventGreat;
	}
	
	public String getIsReserve() {
		return isReserve;
	}

	public void setIsReserve(String isReserve) {
		this.isReserve = isReserve;
	}
	
	@Length(min=0, max=256, message="附件长度必须介于 0 和 256 之间")
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