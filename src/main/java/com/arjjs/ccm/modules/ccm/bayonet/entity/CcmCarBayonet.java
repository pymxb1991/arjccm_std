/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.bayonet.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 车辆卡口实体类Entity
 * @author lgh
 * @version 2019-05-31
 */
public class CcmCarBayonet extends DataEntity<CcmCarBayonet> {
	
	private static final long serialVersionUID = 1L;
	private String bayonetName;		// 卡口名称
	private String useType;		// 使用类别
	private String organizationNumber;		// 组织编号
	private String policeNumber;		// 卡警编号
	private String gbCode;		// 国标码
	private String address;		// 标记地址
	private String frontType;		// 前端类型
	private String bayonetType;		// 卡口类型
	private String bayonetPattern;		// 卡口模式
	private String isOneToMore;		// 是否一图多车
	
	public CcmCarBayonet() {
		super();
	}

	public CcmCarBayonet(String id){
		super(id);
	}

	@Length(min=1, max=64, message="卡口名称长度必须介于 1 和 64 之间")
	public String getBayonetName() {
		return bayonetName;
	}

	public void setBayonetName(String bayonetName) {
		this.bayonetName = bayonetName;
	}
	
	@Length(min=1, max=1, message="使用类别长度必须介于 1 和 1 之间")
	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}
	
	@Length(min=1, max=64, message="组织编号长度必须介于 1 和 64 之间")
	public String getOrganizationNumber() {
		return organizationNumber;
	}

	public void setOrganizationNumber(String organizationNumber) {
		this.organizationNumber = organizationNumber;
	}
	
	@Length(min=1, max=64, message="卡警编号长度必须介于 1 和 64 之间")
	public String getPoliceNumber() {
		return policeNumber;
	}

	public void setPoliceNumber(String policeNumber) {
		this.policeNumber = policeNumber;
	}
	
	@Length(min=1, max=64, message="国标码长度必须介于 1 和 64 之间")
	public String getGbCode() {
		return gbCode;
	}

	public void setGbCode(String gbCode) {
		this.gbCode = gbCode;
	}
	
	@Length(min=0, max=255, message="标记地址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=1, message="前端类型长度必须介于 0 和 1 之间")
	public String getFrontType() {
		return frontType;
	}

	public void setFrontType(String frontType) {
		this.frontType = frontType;
	}
	
	@Length(min=0, max=1, message="卡口类型长度必须介于 0 和 1 之间")
	public String getBayonetType() {
		return bayonetType;
	}

	public void setBayonetType(String bayonetType) {
		this.bayonetType = bayonetType;
	}
	
	@Length(min=0, max=1, message="卡口模式长度必须介于 0 和 1 之间")
	public String getBayonetPattern() {
		return bayonetPattern;
	}

	public void setBayonetPattern(String bayonetPattern) {
		this.bayonetPattern = bayonetPattern;
	}
	
	@Length(min=0, max=1, message="是否一图多车长度必须介于 0 和 1 之间")
	public String getIsOneToMore() {
		return isOneToMore;
	}

	public void setIsOneToMore(String isOneToMore) {
		this.isOneToMore = isOneToMore;
	}
	
}