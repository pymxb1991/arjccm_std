/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 出库、入库进出明细Entity
 * @author dongqikai
 * @version 2018-06-30
 */
public class PlmMinusandAddDetail extends DataEntity<PlmMinusandAddDetail> {
	
	private static final long serialVersionUID = 1L;
	private String parent;		// 入库单id
	private PlmProvideInfo provide;		// 主供应商ID
	private PlmStorage storage;		// 仓库id
	private String equipmentCode;   //物资编号
	private String name;		// 物资名称
	private String spec;		// 规格型号
	private String typeId;		// 物资类别
	private String typeChild;		// 物资子类
	private Integer erialNumber;		// 物资数量
	private String category;  //分类
	private String price;    //物资价格
	private String imgUrl;		// 图片
	private String productionBatch;		// 生产批次
	private Date productionDate;		// 生产日期
	private Date guaranteePeriod;		// 质保期限
	private Integer expirationDate;		// 保质期
	private Date deadlineDate;   //使用有效期
	private Date backDate;  //归还日期
	private String giveBack;  //是否归还(0：待归还 1：已归还2：归还失败)
	private String flag = "0";     // 入库状态（0：未入库|1：已入库）
	private String unit;		// 计量单位
	private Date durableYears;		// 使用年限
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	
	private String equId; //物资id
	private String isConditionFlag = "0";  //查询条件是否增加入库状态  0：否    1：是
	
	public static final String CONDITION_FLAG = "1";  //增加入库状态条件
	
	public PlmMinusandAddDetail() {
		super();
	}

	public PlmMinusandAddDetail(String id){
		super(id);
	}

	@Length(min=1, max=64, message="入库单id长度必须介于 1 和 64 之间")
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	
	public PlmStorage getStorage() {
		return storage;
	}

	public void setStorage(PlmStorage storage) {
		this.storage = storage;
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
	
	@Length(min=0, max=64, message="物资子类长度必须介于 0 和 64 之间")
	public String getTypeChild() {
		return typeChild;
	}

	public void setTypeChild(String typeChild) {
		this.typeChild = typeChild;
	}
	
	@NotNull(message="物资数量不能为空")
	public Integer getErialNumber() {
		return erialNumber;
	}

	public void setErialNumber(Integer erialNumber) {
		this.erialNumber = erialNumber;
	}
	
	@Length(min=0, max=256, message="图片长度必须介于 0 和 256 之间")
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	@Length(min=0, max=20, message="生产批次长度必须介于 0 和 20 之间")
	public String getProductionBatch() {
		return productionBatch;
	}

	public void setProductionBatch(String productionBatch) {
		this.productionBatch = productionBatch;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getGuaranteePeriod() {
		return guaranteePeriod;
	}

	public void setGuaranteePeriod(Date guaranteePeriod) {
		this.guaranteePeriod = guaranteePeriod;
	}
	
	public Integer getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Integer expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	@Length(min=0, max=64, message="计量单位长度必须介于 0 和 64 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDurableYears() {
		return durableYears;
	}

	public void setDurableYears(Date durableYears) {
		this.durableYears = durableYears;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public PlmProvideInfo getProvide() {
		return provide;
	}

	public void setProvide(PlmProvideInfo provide) {
		this.provide = provide;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getEquId() {
		return equId;
	}

	public void setEquId(String equId) {
		this.equId = equId;
	}

	public String getIsConditionFlag() {
		return isConditionFlag;
	}

	public void setIsConditionFlag(String isConditionFlag) {
		this.isConditionFlag = isConditionFlag;
	}

	public Date getDeadlineDate() {
		return deadlineDate;
	}

	public void setDeadlineDate(Date deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public Date getBackDate() {
		return backDate;
	}

	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}

	public String getGiveBack() {
		if(giveBack==null||giveBack=="") {
			giveBack="0";
		}
		return giveBack;
	}

	public void setGiveBack(String giveBack) {
		this.giveBack = giveBack;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}