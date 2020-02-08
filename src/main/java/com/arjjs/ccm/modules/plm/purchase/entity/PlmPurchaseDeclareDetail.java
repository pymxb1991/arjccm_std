/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.purchase.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 采购申报Entity
 * @author liuxue
 * @version 2018-08-25
 */
public class PlmPurchaseDeclareDetail extends DataEntity<PlmPurchaseDeclareDetail> {
	
	private static final long serialVersionUID = 1L;
	private PlmPurchaseDeclare preId;		// 父id 父类
	private String name;		// 名称
	private String spec;		// 型号
	private String number;		// 数量
	private Double declareMoney;		// 申报金额
	private Double verifyMoney;		// 核实金额
	private String place;		// 存放地点
	private User user;		// 使用人
	private String files;		// 附件
	private String type;		// 类型
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	
	public PlmPurchaseDeclareDetail() {
		super();
	}

	public PlmPurchaseDeclareDetail(String id){
		super(id);
	}

	public PlmPurchaseDeclareDetail(PlmPurchaseDeclare preId){
		this.preId = preId;
	}

	@Length(min=1, max=64, message="父id长度必须介于 1 和 64 之间")
	public PlmPurchaseDeclare getPreId() {
		return preId;
	}

	public void setPreId(PlmPurchaseDeclare preId) {
		this.preId = preId;
	}
	
	@Length(min=1, max=64, message="名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="型号长度必须介于 0 和 64 之间")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	@Length(min=0, max=6, message="数量长度必须介于 1 和 6 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public Double getDeclareMoney() {
		return declareMoney;
	}

	public void setDeclareMoney(Double declareMoney) {
		this.declareMoney = declareMoney;
	}
	
	public Double getVerifyMoney() {
		return verifyMoney;
	}

	public void setVerifyMoney(Double verifyMoney) {
		this.verifyMoney = verifyMoney;
	}
	
	@Length(min=0, max=255, message="存放地点长度必须介于 0 和 255 之间")
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
}