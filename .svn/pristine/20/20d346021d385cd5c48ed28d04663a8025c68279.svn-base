/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.allot.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 调拨详细Entity
 * @author dongqikai
 * @version 2018-08-21
 */
public class PlmAllotDetail extends DataEntity<PlmAllotDetail> {
	
	private static final long serialVersionUID = 1L;
	private String allotId;		// 调拨单id
	private String equCode;		// 物资编号
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	private String code; //物资编号
	private String name;		// 物资名称
	private String spec;		// 规格型号
	private String unit;		// 计量单位
	private Integer erialNumber = 1;		// 物资数量（主要是耗材）
	private String price;   //物资价格
	private String sum;  //总价
	private String qrCode; //二维码base64
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getErialNumber() {
		return erialNumber;
	}

	public void setErialNumber(Integer erialNumber) {
		this.erialNumber = erialNumber;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PlmAllotDetail() {
		super();
	}

	public PlmAllotDetail(String id){
		super(id);
	}

	@Length(min=1, max=64, message="调拨单id长度必须介于 1 和 64 之间")
	public String getAllotId() {
		return allotId;
	}

	public void setAllotId(String allotId) {
		this.allotId = allotId;
	}
	
	@Length(min=1, max=64, message="物资编号长度必须介于 1 和 64 之间")
	public String getEquCode() {
		return equCode;
	}

	public void setEquCode(String equCode) {
		this.equCode = equCode;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}