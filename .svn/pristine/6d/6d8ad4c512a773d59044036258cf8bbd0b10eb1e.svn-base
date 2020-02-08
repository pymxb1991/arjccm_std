/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.contract.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.ActEntity;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.modules.plm.storage.entity.PlmProvideInfo;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.PlmTypes;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 采购合同会签Entity
 * @author liuxue
 * @version 2018-07-07
 */
public class PlmContractSign extends ActEntity<PlmContractSign> {
	
	private static final long serialVersionUID = 1L;
	private String procInsId;		// 流程实例id
	private Office depart;		// 采购部门
	private String applyId;      //项目编号
	private Date applyDate;		// 申请日期
	private String contractName;		// 合同名称
	private String contractId;		// 合同编号
	private String contractType;		// 合同类别
	private Date reviewDate;		// 送审时间
	private User user;		// 项目负责人
	private String isStandard;		// 是否标准合同
	private String provider;		// 合同提供方
	private String contractMoney;		// 合同金额(万)
	private String isBudget;		// 是否在预算内
	private PlmProvideInfo 	supplier;		// 供货方
	private String purText;		// 采购内容
	private String describes;		// 采购项目概要
	private String files;		// 附件
	private String type;		// 类型
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	private PlmAct plmAct;		//业务流程总表
	private String cancelFlag;   //是否可撤销
	
	PlmTypes plmTypes;
	@SuppressWarnings("static-access")
	public PlmContractSign() {
		super();
		this.isStandard= plmTypes.TYPE;
		this.provider= plmTypes.TYPE;
		this.isBudget= plmTypes.TYPE;
		
	}
	
	public PlmAct getPlmAct() {
		return plmAct;
	}

	public void setPlmAct(PlmAct plmAct) {
		this.plmAct = plmAct;
	}

	

	public PlmContractSign(String id){
		super(id);
	}

	@Length(min=0, max=64, message="流程实例id长度必须介于 0 和 64 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}
	
	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	@NotNull(message="采购部门不能为空")
	public Office getDepart() {
		return depart;
	}

	public void setDepart(Office depart) {
		this.depart = depart;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="申请日期不能为空")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	@Length(min=1, max=50, message="合同名称长度必须介于 1 和 50 之间")
	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	
	@Length(min=1, max=64, message="合同编号长度必须介于 1 和 64 之间")
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
	@Length(min=1, max=1, message="合同类别长度必须介于 1 和 1 之间")
	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public String getIsStandard() {
		return isStandard;
	}

	public void setIsStandard(String isStandard) {
		this.isStandard = isStandard;
	}
	
	
	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	public String getContractMoney() {
		return contractMoney;
	}

	public void setContractMoney(String contractMoney) {
		this.contractMoney = contractMoney;
	}
	
	
	public String getIsBudget() {
		return isBudget;
	}

	public void setIsBudget(String isBudget) {
		this.isBudget = isBudget;
	}
	
	
	public PlmProvideInfo getSupplier() {
		return supplier;
	}

	public void setSupplier(PlmProvideInfo supplier) {
		this.supplier = supplier;
	}
	
	public String getPurText() {
		return purText;
	}

	public void setPurText(String purText) {
		this.purText = purText;
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
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	

	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	
}