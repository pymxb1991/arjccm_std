/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.purchase.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.ActEntity;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;


/**
 * 采购申报Entity
 * @author liuxue
 * @version 2018-08-25
 */
public class PlmPurchaseDeclare extends ActEntity<PlmPurchaseDeclare> {
	
	private static final long serialVersionUID = 1L;
	private String procInsId;		// 流程实例id
	private Office depart;		// 采购部门
	private String applyId;		// 项目编号
	private Date applyDate;		// 申请日期
	private Double declareTotal;		// 申报合计
	private Double verifyTotal;		// 核实合计
	private String describes;		// 采购原由
	private String files;		// 附件
	private String type;		// 类型
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	private Date beginApplyDate;		// 开始 申请日期
	private Date endApplyDate;		// 结束 申请日期
	private List<PlmPurchaseDeclareDetail> plmPurchaseDeclareDetailList = Lists.newArrayList();		// 子表列表
	private PlmAct plmAct;		//业务流程总表
	private String cancelFlag;   //是否可撤销
	public PlmPurchaseDeclare() {
		super();
	}

	public PlmPurchaseDeclare(String id){
		super(id);
	}
    
	public PlmAct getPlmAct() {
		return plmAct;
	}

	public void setPlmAct(PlmAct plmAct) {
		this.plmAct = plmAct;
	}

	
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}
	
	public Office getDepart() {
		return depart;
	}

	public void setDepart(Office depart) {
		this.depart = depart;
	}
	
	@Length(min=0, max=64, message="项目编号长度必须介于 0 和 64 之间")
	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	public Double getDeclareTotal() {
		return declareTotal;
	}

	public void setDeclareTotal(Double declareTotal) {
		this.declareTotal = declareTotal;
	}
	
	public Double getVerifyTotal() {
		return verifyTotal;
	}

	public void setVerifyTotal(Double verifyTotal) {
		this.verifyTotal = verifyTotal;
	}
	
	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}
	
	
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	
	@Length(min=0, max=1, message="类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=256, message="扩展1长度必须介于 0 和 256 之间")
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	@Length(min=0, max=256, message="扩展2长度必须介于 0 和 256 之间")
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	
	public Date getBeginApplyDate() {
		return beginApplyDate;
	}

	public void setBeginApplyDate(Date beginApplyDate) {
		this.beginApplyDate = beginApplyDate;
	}
	
	public Date getEndApplyDate() {
		return endApplyDate;
	}

	public void setEndApplyDate(Date endApplyDate) {
		this.endApplyDate = endApplyDate;
	}
		
	public List<PlmPurchaseDeclareDetail> getPlmPurchaseDeclareDetailList() {
		return plmPurchaseDeclareDetailList;
	}

	public void setPlmPurchaseDeclareDetailList(List<PlmPurchaseDeclareDetail> plmPurchaseDeclareDetailList) {
		this.plmPurchaseDeclareDetailList = plmPurchaseDeclareDetailList;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
}