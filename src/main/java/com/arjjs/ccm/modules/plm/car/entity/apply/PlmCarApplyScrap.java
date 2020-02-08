/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.entity.apply;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.ActEntity;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.modules.plm.car.entity.PlmCar;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 报废申请Entity
 * @author fu
 * @version 2018-07-07
 */
public class PlmCarApplyScrap extends ActEntity<PlmCarApplyScrap> {
	
	private static final long serialVersionUID = 1L;
	private String procInsId;		// 流程实例ID
	private String title;		// 申请标题
	private User user;		// 申请人ID
	private PlmCar car;		// 报废车辆ID
	private String type;		// 报废类型
	private String reason;		// 报废原因
	private String isEnd;		//流程结束标志
	private PlmAct plmAct;		//业务流程总表
	private String cancelFlag;   //是否可撤销
	
	public PlmCarApplyScrap() {
		super();
	}

	public PlmCarApplyScrap(String id){
		super(id);
	}

	@Length(min=0, max=64, message="流程实例ID长度必须介于 0 和 64 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}
	
	@Length(min=0, max=64, message="申请标题长度必须介于 0 和 64 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public PlmCar getCar() {
		return car;
	}

	public void setCar(PlmCar car) {
		this.car = car;
	}
	
	@Length(min=0, max=2, message="报废类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=1000, message="报废原因长度必须介于 0 和 1000 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}

	public PlmAct getPlmAct() {
		return plmAct;
	}

	public void setPlmAct(PlmAct plmAct) {
		this.plmAct = plmAct;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	
}