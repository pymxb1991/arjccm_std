/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.logistics.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 会议室管理Entity
 * @author fu
 * @version 2018-06-26
 */
public class PlmRoom extends DataEntity<PlmRoom> {
	
	private static final long serialVersionUID = 1L;
	private String category;		//流程类别
	private String subject;		// 会议室名称
	private String address;		// 会议室地址/位置
	private String state;		// 使用状态
	private Integer seat;		// 座位数
	private String memo;		// 会议室介绍
	private String picture;		// 图片
	private Double longItude;		// 经度
	private Double latItude;		// 纬度
	
	public PlmRoom() {
		super();
	}

	public PlmRoom(String id){
		super(id);
	}

	@Length(min=1, max=64, message="会议室名称长度必须介于 1 和 64 之间")
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Length(min=0, max=255, message="会议室地址/位置长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=2, message="使用状态长度必须介于 0 和 2 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public Integer getSeat() {
		return seat;
	}

	public void setSeat(Integer seat) {
		this.seat = seat;
	}
	
	@Length(min=0, max=1000, message="会议室介绍长度必须介于 0 和 1000 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Length(min=0, max=255, message="图片长度必须介于 0 和 255 之间")
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public Double getLongItude() {
		return longItude;
	}

	public void setLongItude(Double longItude) {
		this.longItude = longItude;
	}
	
	public Double getLatItude() {
		return latItude;
	}

	public void setLatItude(Double latItude) {
		this.latItude = latItude;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}