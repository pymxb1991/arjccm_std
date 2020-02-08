/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.equapply.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 申请明细Entity
 * @author liu
 * @version 2018-07-12
 */
public class PlmEquApplyDetail extends DataEntity<PlmEquApplyDetail> {
	
	private static final long serialVersionUID = 1L;
	private String applyId;		// 申请id
	private String name;		// 物品
	private String spec;		// 规格型号
	private String number;		// 申请数量
	private Date validityDate;    //申请有效期
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	
	public PlmEquApplyDetail() {
		super();
	}

	public PlmEquApplyDetail(String id){
		super(id);
	}

	@Length(min=0, max=64, message="申请id长度必须介于 0 和 64 之间")
	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	
	@Length(min=0, max=64, message="物品长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="规格型号长度必须介于 0 和 64 之间")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	@Length(min=0, max=6, message="申请数量长度必须介于 0 和 6 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public Date getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}
	
}