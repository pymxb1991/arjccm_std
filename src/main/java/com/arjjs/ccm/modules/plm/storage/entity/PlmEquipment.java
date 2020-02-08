/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 装备物资Entity
 * @author dongqikai
 * @version 2018-06-27
 */
public class PlmEquipment extends DataEntity<PlmEquipment> implements Cloneable {
	
	private static final long serialVersionUID = 1L;
	private PlmStorage storage;		// 仓库id
	private String minusandId;		// 明细id
	private String name;		// 物资名称
	private String code;		// 物资编号
	private String spec;		// 规格型号
	private String typeId;		// 物资类别
	private String typeChild;		// 物资类别2
	private String category;  //分类
	private String shape;		// 物资型态
	private Integer stockahead = 0;		// 告警数量
	private Integer erialNumber = 1;		// 物资数量（主要是耗材）
	private String imgUrl;		// 图片
	private String productionBatch;		// 生产批次
	private Date productionDate;		// 生产日期
	private Date guaranteePeriod;		// 质保期限
	private Integer expirationDate;		// 保质期
	private String unit;		// 计量单位
	private Date durableYears;		// 使用年限
	private String price;   //物资价格
	private User user;		// 使用人
	private Office userJob;		// 使用人部门
	private String type = "1";		// 状态(默认为1：空闲)
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	private Integer counts;     // 同种物资统计总数
	private String incomingId;     //入库单ID(用于物资关联时接收入库单ID并保存到系统内存，用于保存入库明细信息时使用)
	private String qrCode;     //用于存放二维码（base64编码）
	private Integer useNumber = 0;    //已消耗的数量（主要用于耗材）
	private String outId;   //出库单id
	private String allotId;   //调拨单id
	private String onlyFlag; //唯一标识，用于判断统计记录是否为一类（由typeId+typeChild)而成
	private String flag = "0"; //0:统计全部物资数量  1:统计各个状态数量
	public static final String TYPE_CONSUMABLE = "0"; //耗材类型
	public static final String OCCUPY_STATUS = "0"; //占用状态
	public static final String UNOCCUPIED_STATUS = "1";  //空闲状态
	public static final String REPAIR_STATUS = "2";  //维修保养
	public static final String USING_STATUS = "3";  //使用中
	public static final String SCRAP_STATUS = "4";  //报废
	public static final String DETAIL_COUNT = "1";  //详细统计
	private Integer occupyCounts = 0; //占用
	private Integer unoccupiedCounts = 0; //空闲
	private Integer repairCounts = 0; //维修保养
	private Integer usingCounts = 0; //使用中
	private Integer scrapCounts = 0; //报废
	private String applyId;
	private String officeFlag;   //是否为办公用品管理标识
	
	public PlmEquipment() {
		super();
	}

	public PlmEquipment(String id){
		super(id);
	}
	
	@JsonBackReference
	public PlmStorage getStorage() {
		return storage;
	}

	public void setStorage(PlmStorage storage) {
		this.storage = storage;
	}
	
	@Length(min=0, max=64, message="明细id长度必须介于 0 和 64 之间")
	public String getMinusandId() {
		return minusandId;
	}

	public void setMinusandId(String minusandId) {
		this.minusandId = minusandId;
	}
	
	@Length(min=1, max=64, message="物资名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=64, message="物资编号长度必须介于 1 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	
	@Length(min=1, max=64, message="物资型态长度必须介于 1 和 64 之间")
	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}
	
	@NotNull(message="告警数量不能为空")
	public Integer getStockahead() {
		return stockahead;
	}

	public void setStockahead(Integer stockahead) {
		this.stockahead = stockahead;
	}
	
	@NotNull(message="物资数量（主要是耗材）不能为空")
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
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Office getUserJob() {
		return userJob;
	}

	public void setUserJob(Office userJob) {
		this.userJob = userJob;
	}
	
	@Length(min=0, max=64, message="状态长度必须介于 0 和 64 之间")
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
	
	@Override
	public PlmEquipment clone() {
		PlmEquipment plmEquipment = null;
		try {
			plmEquipment = (PlmEquipment)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return plmEquipment;
	}

	public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	public String getIncomingId() {
		return incomingId;
	}

	public void setIncomingId(String incomingId) {
		this.incomingId = incomingId;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public Integer getUseNumber() {
		return useNumber;
	}

	public void setUseNumber(Integer useNumber) {
		this.useNumber = useNumber;
	}

	public String getOutId() {
		return outId;
	}

	public void setOutId(String outId) {
		this.outId = outId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOnlyFlag() {
		return onlyFlag;
	}

	public void setOnlyFlag(String onlyFlag) {
		this.onlyFlag = onlyFlag;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getOccupyCounts() {
		return occupyCounts;
	}

	public void setOccupyCounts(Integer occupyCounts) {
		this.occupyCounts = occupyCounts;
	}

	public Integer getUnoccupiedCounts() {
		return unoccupiedCounts;
	}

	public void setUnoccupiedCounts(Integer unoccupiedCounts) {
		this.unoccupiedCounts = unoccupiedCounts;
	}

	public Integer getRepairCounts() {
		return repairCounts;
	}

	public void setRepairCounts(Integer repairCounts) {
		this.repairCounts = repairCounts;
	}

	public Integer getUsingCounts() {
		return usingCounts;
	}

	public void setUsingCounts(Integer usingCounts) {
		this.usingCounts = usingCounts;
	}

	public Integer getScrapCounts() {
		return scrapCounts;
	}

	public void setScrapCounts(Integer scrapCounts) {
		this.scrapCounts = scrapCounts;
	}

	public String getAllotId() {
		return allotId;
	}

	public void setAllotId(String allotId) {
		this.allotId = allotId;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOfficeFlag() {
		return officeFlag;
	}

	public void setOfficeFlag(String officeFlag) {
		this.officeFlag = officeFlag;
	}
	
}