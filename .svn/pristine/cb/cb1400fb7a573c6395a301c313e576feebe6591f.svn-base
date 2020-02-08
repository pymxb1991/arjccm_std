/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.purchase.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.ActEntity;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 采购计划申请Entity
 * @author liuxue
 * @version 2018-07-07
 */
public class PlmPurchaseApply extends ActEntity<PlmPurchaseApply> {
	
	private static final long serialVersionUID = 1L;
	private String procInsId;		// 流程实例id
	private Office office;		// 采购部门
	private String applyId;      //项目编号
	private Date applyDate;		// 申请日期
	private String fundingName;		// 经费项目名称
	private String fundingCard;		// 经费卡号
	private String fundingType;		// 经费类型
	private String name;		// 采购项目名称
	private String purBudget;		// 采购项目预算(万元)
	private User userPur;		// 采购项目负责人
	private User userTech;		// 项目技术负责人或经办人
	private String describes;		// 采购项目概况
	private String files;		// 附件
	private String type;		// 类型
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	private Date beginApplyDate;		// 开始 申请日期
	private Date endApplyDate;		// 结束 申请日期
	private PlmAct plmAct;		//业务流程总表
	private String cancelFlag;   //是否可撤销
	
	
	public PlmAct getPlmAct() {
		return plmAct;
	}

	public void setPlmAct(PlmAct plmAct) {
		this.plmAct = plmAct;
	}

	public PlmPurchaseApply() {
		super();
	}

	public PlmPurchaseApply(String id){
		super(id);
	}
    
	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	@Length(min=0, max=64, message="流程实例id长度必须介于 0 和 64 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	@Length(min=0, max=50, message="经费项目名称长度必须介于 0 和 50 之间")
	public String getFundingName() {
		return fundingName;
	}

	public void setFundingName(String fundingName) {
		this.fundingName = fundingName;
	}
	
	@Length(min=0, max=19, message="经费卡号长度必须介于 0 和 19 之间")
	public String getFundingCard() {
		return fundingCard;
	}

	public void setFundingCard(String fundingCard) {
		this.fundingCard = fundingCard;
	}
	
	@Length(min=0, max=1, message="经费类型长度必须介于 0 和 1 之间")
	public String getFundingType() {
		return fundingType;
	}

	public void setFundingType(String fundingType) {
		this.fundingType = fundingType;
	}
	
	@Length(min=0, max=64, message="采购项目名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPurBudget() {
		return purBudget;
	}

	public void setPurBudget(String purBudget) {
		this.purBudget = purBudget;
	}
	
	public User getUserPur() {
		return userPur;
	}

	public void setUserPur(User userPur) {
		this.userPur = userPur;
	}
	
	public User getUserTech() {
		return userTech;
	}

	public void setUserTech(User userTech) {
		this.userTech = userTech;
	}
	
	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}
	
	@Length(min=0, max=256, message="附件长度必须介于 0 和 256 之间")
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

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
		
}