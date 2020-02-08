/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.equapply.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 维修报废详细表Entity
 * @author liu
 * @version 2018-08-31
 */
public class PlmEquEquipment extends DataEntity<PlmEquEquipment> {
	
	private static final long serialVersionUID = 1L;
	private String applyId;		// 申请id
	private String code;		// 物资编号
	private String name;		// 物资名称
	private String spec;		// 规格型号
	private String typeId;		// 物资类别
	private String typeChild;		// 物资类别2
	private String shape;		// 物资型态
	private Date productionDate;		// 生产日期
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	
	public PlmEquEquipment() {
		super();
	}

	public PlmEquEquipment(String id){
		super(id);
	}

	@Length(min=0, max=64, message="申请id长度必须介于 0 和 64 之间")
	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	
	@Length(min=1, max=64, message="物资编号长度必须介于 1 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=64, message="物资名称长度必须介于 1 和 64 之间")
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
	
	@Length(min=1, max=64, message="物资类别长度必须介于 1 和 64 之间")
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	@Length(min=0, max=64, message="物资类别2长度必须介于 0 和 64 之间")
	public String getTypeChild() {
		return typeChild;
	}

	public void setTypeChild(String typeChild) {
		this.typeChild = typeChild;
	}
	
	@Length(min=0, max=64, message="物资型态长度必须介于 0 和 64 之间")
	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
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
	
}