/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.lane.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.ccm.bayonet.entity.CcmCarBayonet;

/**
 * 车道表实体类Entity
 * 
 * @author lgh
 * @version 2019-06-03
 */
public class CcmLane extends DataEntity<CcmLane> {

	private static final long serialVersionUID = 1L;
	private String laneName; // 车道名称
	private String laneDirection; // 车道方向
	private String bigCarMaxSpeed; // 大车最快速度
	private String bigCarMinSpeed; // 大车最慢速度
	private String smallCarMaxSpeed; // 小车最快速度
	private String smallCarMinSpeed; // 小车最快速度
	private String bayonetId; // 所属卡口id
	private String passDirection; // 出入城方向
	private String bayonetName;
	private CcmCarBayonet carBayonet;

	
	public String getBayonetName() {
		return bayonetName;
	}

	public void setBayonetName(String bayonetName) {
		this.bayonetName = bayonetName;
	}

	public CcmCarBayonet getCarBayonet() {
		return carBayonet;
	}

	public void setCarBayonet(CcmCarBayonet carBayonet) {
		this.carBayonet = carBayonet;
	}

	public CcmLane() {
		super();
	}

	public CcmLane(String id) {
		super(id);
	}

	@Length(min = 1, max = 64, message = "车道名称长度必须介于 1 和 64 之间")
	public String getLaneName() {
		return laneName;
	}

	public void setLaneName(String laneName) {
		this.laneName = laneName;
	}

	@Length(min = 0, max = 2, message = "车道方向长度必须介于 0 和 2 之间")
	public String getLaneDirection() {
		return laneDirection;
	}

	public void setLaneDirection(String laneDirection) {
		this.laneDirection = laneDirection;
	}

	public String getBigCarMaxSpeed() {
		return bigCarMaxSpeed;
	}

	public void setBigCarMaxSpeed(String bigCarMaxSpeed) {
		this.bigCarMaxSpeed = bigCarMaxSpeed;
	}

	public String getBigCarMinSpeed() {
		return bigCarMinSpeed;
	}

	public void setBigCarMinSpeed(String bigCarMinSpeed) {
		this.bigCarMinSpeed = bigCarMinSpeed;
	}

	public String getSmallCarMaxSpeed() {
		return smallCarMaxSpeed;
	}

	public void setSmallCarMaxSpeed(String smallCarMaxSpeed) {
		this.smallCarMaxSpeed = smallCarMaxSpeed;
	}

	public String getSmallCarMinSpeed() {
		return smallCarMinSpeed;
	}

	public void setSmallCarMinSpeed(String smallCarMinSpeed) {
		this.smallCarMinSpeed = smallCarMinSpeed;
	}

	@Length(min = 1, max = 64, message = "所属卡口id长度必须介于 1 和 64 之间")
	public String getBayonetId() {
		return bayonetId;
	}

	public void setBayonetId(String bayonetId) {
		this.bayonetId = bayonetId;
	}

	@Length(min = 1, max = 2, message = "出入城方向长度必须介于 1 和 2 之间")
	public String getPassDirection() {
		return passDirection;
	}

	public void setPassDirection(String passDirection) {
		this.passDirection = passDirection;
	}

}