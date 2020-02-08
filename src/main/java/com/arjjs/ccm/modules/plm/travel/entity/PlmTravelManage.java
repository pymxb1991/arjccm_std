/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.travel.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.ActEntity;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 出差管理Entity
 * @author dongqikai
 * @version 2018-07-13
 */
public class PlmTravelManage extends ActEntity<PlmTravelManage> {
	
	private static final long serialVersionUID = 1L;
	private String fldSubject;		// 主题
	private String fldDest;		// 目的地
	private String procInsId;		// 流程id
	private String code;    //编号
	private User fldApplicant;		// 申请人
	private Date fldDt;		// 申请日期
	private Office fldDept;		// 部门
	private Date fldBdt;		// 开始时间
	private User departmentHead;		// 部门负责人
	private Date fldEdt;		// 返回时间
	private String fldTransport;		// 交通工具
	private Long fldLoan;		// 借款总额
	private String fldRoute;		// 出差路线
	private String fldReason;		// 出差事由
	private String fldAccessory;		// 附件
	private String fldAuthorsc;		// 作者安全码
	private Long fldDays;		// 出差天数
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	private Date beginFldDt;		// 开始 申请日期
	private Date endFldDt;		// 结束 申请日期
	private Date beginFldBdt;		// 开始 开始时间
	private Date endFldBdt;		// 结束 开始时间
	private Date beginFldEdt;		// 开始 返回时间
	private Date endFldEdt;		// 结束 返回时间
	private PlmAct plmAct;		//业务流程总表
	private String cancelFlag;   //是否可撤销
	public PlmTravelManage() {
		super();
	}

	public PlmTravelManage(String id){
		super(id);
	}
     
	public PlmAct getPlmAct() {
		return plmAct;
	}

	public void setPlmAct(PlmAct plmAct) {
		this.plmAct = plmAct;
	}

	@NotNull(message="主题不能为空")
	@Length(min=0, max=256, message="主题长度必须介于 0 和 256 之间")
	public String getFldSubject() {
		return fldSubject;
	}

	public void setFldSubject(String fldSubject) {
		this.fldSubject = fldSubject;
	}
	
	@Length(min=1, max=256, message="目的地长度必须介于 1 和 256 之间")
	public String getFldDest() {
		return fldDest;
	}

	public void setFldDest(String fldDest) {
		this.fldDest = fldDest;
	}
	
	@Length(min=0, max=64, message="流程id长度必须介于 0 和 64 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}
	
	@NotNull(message="申请人不能为空")
	public User getFldApplicant() {
		return fldApplicant;
	}

	public void setFldApplicant(User fldApplicant) {
		this.fldApplicant = fldApplicant;
	}
	
	@Length(min=1, max=255, message="编号长度必须介于 1 和 255 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="申请日期不能为空")
	public Date getFldDt() {
		return fldDt;
	}

	public void setFldDt(Date fldDt) {
		this.fldDt = fldDt;
	}
	
	public Office getFldDept() {
		return fldDept;
	}

	public void setFldDept(Office fldDept) {
		this.fldDept = fldDept;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="开始时间不能为空")
	public Date getFldBdt() {
		return fldBdt;
	}

	public void setFldBdt(Date fldBdt) {
		this.fldBdt = fldBdt;
	}
	
	
	public User getDepartmentHead() {
		return departmentHead;
	}

	public void setDepartmentHead(User departmentHead) {
		this.departmentHead = departmentHead;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="返回时间不能为空")
	public Date getFldEdt() {
		return fldEdt;
	}

	public void setFldEdt(Date fldEdt) {
		this.fldEdt = fldEdt;
	}
	
	@Length(min=0, max=64, message="交通工具长度必须介于 0 和 64 之间")
	public String getFldTransport() {
		return fldTransport;
	}

	public void setFldTransport(String fldTransport) {
		this.fldTransport = fldTransport;
	}
	
	public Long getFldLoan() {
		return fldLoan;
	}

	public void setFldLoan(Long fldLoan) {
		this.fldLoan = fldLoan;
	}
	
	@Length(min=0, max=256, message="出差路线长度必须介于 0 和 256 之间")
	public String getFldRoute() {
		return fldRoute;
	}

	public void setFldRoute(String fldRoute) {
		this.fldRoute = fldRoute;
	}
	
	@Length(min=0, max=512, message="出差事由长度必须介于 0 和 512 之间")
	public String getFldReason() {
		return fldReason;
	}

	public void setFldReason(String fldReason) {
		this.fldReason = fldReason;
	}
	
	@Length(min=0, max=256, message="附件长度必须介于 0 和 256 之间")
	public String getFldAccessory() {
		return fldAccessory;
	}

	public void setFldAccessory(String fldAccessory) {
		this.fldAccessory = fldAccessory;
	}
	
	@Length(min=0, max=64, message="作者安全码长度必须介于 0 和 64 之间")
	public String getFldAuthorsc() {
		return fldAuthorsc;
	}

	public void setFldAuthorsc(String fldAuthorsc) {
		this.fldAuthorsc = fldAuthorsc;
	}
	
	public Long getFldDays() {
		return fldDays;
	}

	public void setFldDays(Long fldDays) {
		this.fldDays = fldDays;
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
	
	public Date getBeginFldDt() {
		return beginFldDt;
	}

	public void setBeginFldDt(Date beginFldDt) {
		this.beginFldDt = beginFldDt;
	}
	
	public Date getEndFldDt() {
		return endFldDt;
	}

	public void setEndFldDt(Date endFldDt) {
		this.endFldDt = endFldDt;
	}
		
	public Date getBeginFldBdt() {
		return beginFldBdt;
	}

	public void setBeginFldBdt(Date beginFldBdt) {
		this.beginFldBdt = beginFldBdt;
	}
	
	public Date getEndFldBdt() {
		return endFldBdt;
	}

	public void setEndFldBdt(Date endFldBdt) {
		this.endFldBdt = endFldBdt;
	}
		
	public Date getBeginFldEdt() {
		return beginFldEdt;
	}

	public void setBeginFldEdt(Date beginFldEdt) {
		this.beginFldEdt = beginFldEdt;
	}
	
	public Date getEndFldEdt() {
		return endFldEdt;
	}

	public void setEndFldEdt(Date endFldEdt) {
		this.endFldEdt = endFldEdt;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
		
}