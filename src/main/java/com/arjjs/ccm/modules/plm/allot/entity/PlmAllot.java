/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.allot.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.ActEntity;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 内部调拨Entity
 * @author dongqikai
 * @version 2018-08-16
 */
public class PlmAllot extends ActEntity<PlmAllot> {
	
	private static final long serialVersionUID = 1L;
	private String procInsId;		// 流程实例ID
	private String code;		// 编号
	private String title;		// 标题
	private User applyer;		// 申请人
	private Date addDate;		// 填写日期
	private String sumCap;		// 资产总额大写
	private Double sumLow;		// 资产总额小写
	private Office outDep;		// 调出部门
	private Office inDep;		// 调入部门
	private User outManager;		// 调出负责人
	private User inManager;		// 调入负责人
	private User outOperator;		// 调出经办人
	private User inOperator;		// 调入经办人
	private Date outDate;		// 调出日期
	private Date inDate;		// 调入日期
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	private Date beginAddDate;		// 开始 填写日期
	private Date endAddDate;		// 结束 填写日期
	private PlmAct plmAct;		//业务流程总表
	private String selectRemarks;    //所选物资备注
	private String cancelFlag;   //是否可撤销
	
	public PlmAllot() {
		super();
	}

	public PlmAllot(String id){
		super(id);
	}

	@Length(min=0, max=64, message="流程实例ID长度必须介于 0 和 64 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}
	
	@Length(min=0, max=64, message="编号长度必须介于 1 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=256, message="标题长度必须介于 1 和 256 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotNull(message="申请人不能为空")
	public User getApplyer() {
		return applyer;
	}

	public void setApplyer(User applyer) {
		this.applyer = applyer;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="填写日期不能为空")
	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	
	@Length(min=1, max=128, message="资产总额大写长度必须介于 1 和 128 之间")
	public String getSumCap() {
		return sumCap;
	}

	public void setSumCap(String sumCap) {
		this.sumCap = sumCap;
	}
	
	@NotNull(message="资产总额小写不能为空")
	public Double getSumLow() {
		return sumLow;
	}

	public void setSumLow(Double sumLow) {
		this.sumLow = sumLow;
	}
	
	@NotNull(message="调出部门不能为空")
	public Office getOutDep() {
		return outDep;
	}

	public void setOutDep(Office outDep) {
		this.outDep = outDep;
	}
	
	@NotNull(message="调入部门不能为空")
	public Office getInDep() {
		return inDep;
	}

	public void setInDep(Office inDep) {
		this.inDep = inDep;
	}
	
	public User getOutManager() {
		return outManager;
	}

	public void setOutManager(User outManager) {
		this.outManager = outManager;
	}
	
	public User getInManager() {
		return inManager;
	}

	public void setInManager(User inManager) {
		this.inManager = inManager;
	}
	
	public User getOutOperator() {
		return outOperator;
	}

	public void setOutOperator(User outOperator) {
		this.outOperator = outOperator;
	}
	
	public User getInOperator() {
		return inOperator;
	}

	public void setInOperator(User inOperator) {
		this.inOperator = inOperator;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
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
	
	public Date getBeginAddDate() {
		return beginAddDate;
	}

	public void setBeginAddDate(Date beginAddDate) {
		this.beginAddDate = beginAddDate;
	}
	
	public Date getEndAddDate() {
		return endAddDate;
	}

	public void setEndAddDate(Date endAddDate) {
		this.endAddDate = endAddDate;
	}

	public PlmAct getPlmAct() {
		return plmAct;
	}

	public void setPlmAct(PlmAct plmAct) {
		this.plmAct = plmAct;
	}

	public String getSelectRemarks() {
		return selectRemarks;
	}

	public void setSelectRemarks(String selectRemarks) {
		this.selectRemarks = selectRemarks;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
		
}