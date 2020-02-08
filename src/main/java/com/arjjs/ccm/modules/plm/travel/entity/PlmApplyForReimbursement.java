/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.travel.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.ActEntity;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 报销申请Entity
 * @author dongqikai
 * @version 2018-07-16
 */
public class PlmApplyForReimbursement extends ActEntity<PlmApplyForReimbursement> {
	
	private static final long serialVersionUID = 1L;
	private String procInsId;		// 流程id
	private String title;		// 标题
	private String code;		// 编号
	private User applyer;		// 申请人
	private Office department;		// 所属部门
	private Double sum;		// 报销总额
	private String type;		// 报销类型
	private Integer piece;		// 报销单数量
	private String detail;		// 报销详情
	private String cause;		// 报销原因
	private String file;		// 附件
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	private PlmAct plmAct;		//业务流程总表
	private String cancelFlag;   //是否可撤销
	
	public PlmApplyForReimbursement() {
		super();
	}

	public PlmApplyForReimbursement(String id){
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
	
	@NotNull(message="报销总额不能为空")
	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}
	
	@Length(min=1, max=16, message="报销类型长度必须介于 1 和 16 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@NotNull(message="报销单数量不能为空")
	public Integer getPiece() {
		return piece;
	}

	public void setPiece(Integer piece) {
		this.piece = piece;
	}
	
	@Length(min=1, max=4000, message="报销详情长度必须介于 1 和 4000 之间")
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Length(min=1, max=512, message="报销原因长度必须介于 1 和 512 之间")
	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
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

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	
}