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
 * 借款申请Entity
 * @author dongqikai
 * @version 2018-07-16
 */
public class PlmBorrowMoney extends ActEntity<PlmBorrowMoney> {
	
	private static final long serialVersionUID = 1L;
	private String procInsId;		// 流程id
	private String title;		// 标题
	private String code;		// 编号
	private User applyer;		// 申请人
	private Office department;		// 所属部门
	private Date borrowDate;		// 借款日期
	private Date deadlineDate;		// 截止日期
	private String sum;		// 借款总额
	private String cause;		// 借款原因
	private String purpose;		// 借款用途
	private String accountCode;		// 账号
	private String accountName;		// 账户名
	private String file;		// 附件
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	private Date beginBorrowDate;		// 开始 借款日期
	private Date endBorrowDate;		// 结束 借款日期
	private Double beginSum;		// 开始 借款总额
	private Double endSum;		// 结束 借款总额
	private PlmAct plmAct;		//业务流程总表
	private String cancelFlag;   //是否可撤销
	
	public PlmBorrowMoney() {
		super();
	}

	public PlmBorrowMoney(String id){
		super(id);
	}
   
	public PlmAct getPlmAct() {
		return plmAct;
	}

	public void setPlmAct(PlmAct plmAct) {
		this.plmAct = plmAct;
	}

	@Length(min=0, max=64, message="流程id长度必须介于 0 和 64 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}
	
	@Length(min=1, max=255, message="标题长度必须介于 1 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@NotNull(message="申请人不能为空")
	public User getApplyer() {
		return applyer;
	}

	public void setApplyer(User applyer) {
		this.applyer = applyer;
	}
	
	@NotNull(message="所属部门不能为空")
	public Office getDepartment() {
		return department;
	}

	public void setDepartment(Office department) {
		this.department = department;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="借款日期不能为空")
	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="截止日期不能为空")
	public Date getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(Date deadlineDate) {
		this.deadlineDate = deadlineDate;
	}
	
	@NotNull(message="借款总额不能为空")
	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}
	
	@Length(min=1, max=512, message="借款原因长度必须介于 1 和 512 之间")
	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
	
	@Length(min=1, max=512, message="借款用途长度必须介于 1 和 512 之间")
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	@Length(min=1, max=256, message="账号长度必须介于 1 和 256 之间")
	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	
	@Length(min=1, max=256, message="账户名长度必须介于 1 和 256 之间")
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	@Length(min=0, max=4000, message="附件长度必须介于 0 和 4000 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
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
	
	public Date getBeginBorrowDate() {
		return beginBorrowDate;
	}

	public void setBeginBorrowDate(Date beginBorrowDate) {
		this.beginBorrowDate = beginBorrowDate;
	}
	
	public Date getEndBorrowDate() {
		return endBorrowDate;
	}

	public void setEndBorrowDate(Date endBorrowDate) {
		this.endBorrowDate = endBorrowDate;
	}
		
	public Double getBeginSum() {
		return beginSum;
	}

	public void setBeginSum(Double beginSum) {
		this.beginSum = beginSum;
	}
	
	public Double getEndSum() {
		return endSum;
	}

	public void setEndSum(Double endSum) {
		this.endSum = endSum;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
		
}