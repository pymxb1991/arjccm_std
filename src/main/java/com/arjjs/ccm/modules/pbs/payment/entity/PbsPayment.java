/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.payment.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;

/**
 * 党员缴费信息管理Entity
 * @author qixuesong
 * @version 2018-09-05
 */
public class PbsPayment extends DataEntity<PbsPayment> {
	
	private static final long serialVersionUID = 1L;
	private String sPartymemid;		// 党员id
	private String money;		// 缴费金额
	private Date paymentTime;		// 缴费时间
	private String sType;		// 缴费方式
	private PbsPartymem pbspartymem; //党员
	private Date startPayTime; //缴费开始时间
	private Date endPayTime; //缴费结束时间
	private String pbsmemName; //党员名称
	
	public PbsPayment() {
		super();
	}

	public PbsPayment(String id){
		super(id);
	}
	
	@ExcelField(title="编号",sort=1,align=2)
	public String getId() {
		return id;
	}
	
	@Length(min=1, max=64, message="党员id长度必须介于 1 和 64 之间")
	public String getSPartymemid() {
		return sPartymemid;
	}

	public void setSPartymemid(String sPartymemid) {
		this.sPartymemid = sPartymemid;
	}
	
	@ExcelField(title="缴费金额",sort=10,align=2)
	@Length(min=1, max=11, message="缴费金额长度必须介于 1 和 11 之间")
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="缴费日期",sort=20,align=2)
	public Date getPaymentTime() {
		return paymentTime;
	}
	
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	
	@ExcelField(title="缴费类型",sort=15,align=2,dictType="pay_type")
	@Length(min=0, max=64, message="缴费方式长度必须介于 0 和 64 之间")
	public String getSType() {
		return sType;
	}

	public void setSType(String sType) {
		this.sType = sType;
	}

	@ExcelField(title="备注",sort=30,align=2)
	@Length(min=0, max=255)
	public String getRemarks() {
		return remarks;
	}
	
	public PbsPartymem getPbspartymem() {
		return pbspartymem;
	}

	public void setPbspartymem(PbsPartymem pbspartymem) {
		this.pbspartymem = pbspartymem;
	}

	public Date getStartPayTime() {
		return startPayTime;
	}

	public void setStartPayTime(Date startPayTime) {
		this.startPayTime = startPayTime;
	}

	public Date getEndPayTime() {
		return endPayTime;
	}

	public void setEndPayTime(Date endPayTime) {
		this.endPayTime = endPayTime;
	}
	
	public String getsPartymemid() {
		return sPartymemid;
	}

	public void setsPartymemid(String sPartymemid) {
		this.sPartymemid = sPartymemid;
	}
	
	@ExcelField(title="党员",align=2,sort=5)
	public String getPbsmemName() {
		return pbsmemName;
	}

	public void setPbsmemName(String pbsmemName) {
		this.pbsmemName = pbsmemName;
	}
	
}