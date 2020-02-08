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
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 入库单Entity
 * @author dongqikai
 * @version 2018-06-30
 */
public class PlmIncomingEntry extends DataEntity<PlmIncomingEntry> {
	
	private static final long serialVersionUID = 1L;
	private PlmProvideInfo provide;		// 供货单位
	private String procInsId;		// 流程实例ID
	private Date incomingDate;		// 入库日期
	private String incomingCode;		// 入库单号
	private String incomingType;		// 入库类别
	private PlmStorage storage;		// 仓库
	private String deliveryNumber;		// 送货单号
	private String invoiceValue;		// 发票金额
	private String invoice;		// 发票单号
	private Date billDate;		// 发票日期
	private User user;		// 申请人
	private Office userJob;		// 申请人部门
	private String cause;   //申请原因
	private Date beginIncomingDate;		// 开始 入库日期
	private Date endIncomingDate;		// 结束 入库日期
	private String file;		// 附件
	private String type;		// 入库状态
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	private String flag;    //状态标志，用于出入库页面显示
	private String[] equIds;  //物资列表所有id
	private String[] number;  //出库物资数量（用于耗材）
	private Date deadLineDate;   //物资使用有效期
	public static final String CONSUMABLE_TYPE = "0";  //耗材类型值
	public static final String COMPLETE_OUT = "5";  //完成出库
	public static final String READY_OUT = "3";  //准备出库
	public static final String COMPLETE_IN = "2";  //完成入库
	public static final String READY_IN = "0";  //准备入库
	public static final String NOT_EDIT = "1";  //不可编辑标记
	public static final String DEFAULT_NUM = "1";  //默认出库物资数量
	public static final String INCOMMING_TYPE = "1";  //正在入库状态
	
	public PlmIncomingEntry() {
		super();
	}

	public PlmIncomingEntry(String id){
		super(id);
	}

	public PlmProvideInfo getProvide() {
		return provide;
	}

	public void setProvide(PlmProvideInfo provide) {
		this.provide = provide;
	}
	
	@Length(min=0, max=64, message="流程实例ID长度必须介于 0 和 64 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="入库日期不能为空")
	public Date getIncomingDate() {
		return incomingDate;
	}

	public void setIncomingDate(Date incomingDate) {
		this.incomingDate = incomingDate;
	}
	
	public String getIncomingCode() {
		return incomingCode;
	}

	public void setIncomingCode(String incomingCode) {
		this.incomingCode = incomingCode;
	}
	
	@Length(min=1, max=64, message="入库类别长度必须介于 1 和 64 之间")
	public String getIncomingType() {
		return incomingType;
	}

	public void setIncomingType(String incomingType) {
		this.incomingType = incomingType;
	}
	
	public PlmStorage getStorage() {
		return storage;
	}

	public void setStorage(PlmStorage storage) {
		this.storage = storage;
	}
	
	@Length(min=0, max=64, message="送货单号长度必须介于 0 和 64 之间")
	public String getDeliveryNumber() {
		return deliveryNumber;
	}

	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}
	
	@Length(min=0, max=64, message="发票金额长度必须介于 0 和 64 之间")
	public String getInvoiceValue() {
		return invoiceValue;
	}

	public void setInvoiceValue(String invoiceValue) {
		this.invoiceValue = invoiceValue;
	}
	
	@Length(min=0, max=20, message="发票单号长度必须介于 0 和 20 之间")
	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	
	@Length(min=0, max=256, message="附件长度必须介于 0 和 256 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	@Length(min=1, max=64, message="入库状态长度必须介于 1 和 64 之间")
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String[] getEquIds() {
		return equIds;
	}

	public void setEquIds(String[] equIds) {
		this.equIds = equIds;
	}

	public String[] getNumber() {
		return number;
	}

	public void setNumber(String[] number) {
		this.number = number;
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

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public Date getBeginIncomingDate() {
		return beginIncomingDate;
	}

	public void setBeginIncomingDate(Date beginIncomingDate) {
		this.beginIncomingDate = beginIncomingDate;
	}

	public Date getEndIncomingDate() {
		return endIncomingDate;
	}

	public void setEndIncomingDate(Date endIncomingDate) {
		this.endIncomingDate = endIncomingDate;
	}

	public Date getDeadLineDate() {
		return deadLineDate;
	}

	public void setDeadLineDate(Date deadLineDate) {
		this.deadLineDate = deadLineDate;
	}
	
}