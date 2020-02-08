/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.traffic.entity;

import java.util.Date;
import java.util.List;

import com.arjjs.ccm.modules.sys.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.ccm.place.entity.CcmBasePlace;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.google.common.collect.Lists;

/**
 * 交通出行场所Entity
 * 
 * @author ljd
 * @version 2019-04-29
 */
public class CcmPlaceTraffic extends DataEntity<CcmPlaceTraffic> {

	private static final long serialVersionUID = 1L;
	private CcmBasePlace ccmBasePlace;
	private String type; // 场所类型（01车站 02停车场 03加油站或加气站）
	private String stationType; // 车站类型(汽车站 火车站 地铁站)
	private String stationNature; // 车站性质(客运站 货运站 客货运站)
	private String stationRank; // 车站等级
	private String parkSpaceNumber; // 停车位数量
	private String parkSpacePay; // 停车位收费（元/小时）
	private String parkSpaceMaxpay; // 收费封顶（元/小时）
	private String gasStationType; // 加油站类型（加油站 加气站）
	private String gasStationLevel; // 加油站等级（一级 二级 三级）
	private String gasStationTanker; // 加油/气 机数量（个）
	private String basePlaceId; // 场所id
	private Date beginCreateDate; // 开始 创建时间
	private Date endCreateDate; // 结束 创建时间

	// 基础场所属性
	private String placeType;
	private String placeName; // 场所名称
	private String relevanceOrg; // 关联组织机构
	private String leaderName; // 负责人姓名
	private String address; // 地址
	private String leaderContact; // 负责人联系方式
	private String leaderIdCard;
	private String placeUse;
	private Date createTime;
	private String keyPoint;
	private String placeArea;
	private String workerNumber; // 工作人员数量
	private String placePicture;
	private String governingBodyName;
	private String administrativeDivision; // 行政区划
	
	private Area area;
	private User checkUser;
	public List<String> getKeyPointList() {
		List<String> list = Lists.newArrayList();
		if (ccmBasePlace!=null && ccmBasePlace.getKeyPoint() != null) {
			for (String s : StringUtils.split(ccmBasePlace.getKeyPoint(), ",")) {
				list.add(s);
			}
		}
		return list;
	}

	public void setKeyPointList(List<String> keyPointList) {
		ccmBasePlace.setKeyPoint("," + StringUtils.join(keyPointList, ",") + ",");
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public CcmBasePlace getCcmBasePlace() {
		return ccmBasePlace;
	}

	public void setCcmBasePlace(CcmBasePlace ccmBasePlace) {
		this.ccmBasePlace = ccmBasePlace;
	}

	@Length(min = 1, max = 255, message = "场所名称长度必须介于 1 和 255 之间")
	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	@Length(min = 0, max = 255, message = "关联组织机构长度必须介于 0 和 255 之间")
	public String getRelevanceOrg() {
		return relevanceOrg;
	}

	public void setRelevanceOrg(String relevanceOrg) {
		this.relevanceOrg = relevanceOrg;
	}

	@Length(min = 0, max = 255, message = "负责人姓名长度必须介于 0 和 255 之间")
	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	@Length(min = 0, max = 255, message = "负责人身份证号码长度必须介于 0 和 255 之间")
	public String getLeaderIdCard() {
		return leaderIdCard;
	}

	public void setLeaderIdCard(String leaderIdCard) {
		this.leaderIdCard = leaderIdCard;
	}

	@Length(min = 0, max = 255, message = "负责人联系方式长度必须介于 0 和 255 之间")
	public String getLeaderContact() {
		return leaderContact;
	}

	public void setLeaderContact(String leaderContact) {
		this.leaderContact = leaderContact;
	}

	public CcmPlaceTraffic() {
		super();
	}

	public CcmPlaceTraffic(String id) {
		super(id);
	}

	@Length(min = 0, max = 2, message = "场所类型（01车站 02停车场 03加油站或加气站）长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min = 0, max = 255, message = "车站类型(汽车站  火车站  地铁站)长度必须介于 0 和 255 之间")
	public String getStationType() {
		return stationType;
	}

	public void setStationType(String stationType) {
		this.stationType = stationType;
	}

	@Length(min = 0, max = 255, message = "车站性质(客运站 货运站 客货运站)长度必须介于 0 和 255 之间")
	public String getStationNature() {
		return stationNature;
	}

	public void setStationNature(String stationNature) {
		this.stationNature = stationNature;
	}

	@Length(min = 0, max = 255, message = "车站等级长度必须介于 0 和 255 之间")
	public String getStationRank() {
		return stationRank;
	}

	public void setStationRank(String stationRank) {
		this.stationRank = stationRank;
	}

	@Length(min = 0, max = 64, message = "停车位数量长度必须介于 0 和 64 之间")
	public String getParkSpaceNumber() {
		return parkSpaceNumber;
	}

	public void setParkSpaceNumber(String parkSpaceNumber) {
		this.parkSpaceNumber = parkSpaceNumber;
	}

	@Length(min = 0, max = 255, message = "停车位收费（元/小时）长度必须介于 0 和 255 之间")
	public String getParkSpacePay() {
		return parkSpacePay;
	}

	public void setParkSpacePay(String parkSpacePay) {
		this.parkSpacePay = parkSpacePay;
	}

	@Length(min = 0, max = 255, message = "收费封顶（元/小时）长度必须介于 0 和 255 之间")
	public String getParkSpaceMaxpay() {
		return parkSpaceMaxpay;
	}

	public void setParkSpaceMaxpay(String parkSpaceMaxpay) {
		this.parkSpaceMaxpay = parkSpaceMaxpay;
	}

	@Length(min = 0, max = 2, message = "加油站类型（加油站  加气站）长度必须介于 0 和 2 之间")
	public String getGasStationType() {
		return gasStationType;
	}

	public void setGasStationType(String gasStationType) {
		this.gasStationType = gasStationType;
	}

	@Length(min = 0, max = 255, message = "加油站等级（一级 二级  三级）长度必须介于 0 和 255 之间")
	public String getGasStationLevel() {
		return gasStationLevel;
	}

	public void setGasStationLevel(String gasStationLevel) {
		this.gasStationLevel = gasStationLevel;
	}

	@Length(min = 0, max = 255, message = "加油/气 机数量（个）长度必须介于 0 和 255 之间")
	public String getGasStationTanker() {
		return gasStationTanker;
	}

	public void setGasStationTanker(String gasStationTanker) {
		this.gasStationTanker = gasStationTanker;
	}

	@Length(min = 0, max = 64, message = "场所id长度必须介于 0 和 64 之间")
	public String getBasePlaceId() {
		return basePlaceId;
	}

	public void setBasePlaceId(String basePlaceId) {
		this.basePlaceId = basePlaceId;
	}

	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPlaceUse() {
		return placeUse;
	}

	public void setPlaceUse(String placeUse) {
		this.placeUse = placeUse;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getKeyPoint() {
		return keyPoint;
	}

	public void setKeyPoint(String keyPoint) {
		this.keyPoint = keyPoint;
	}

	public String getPlaceArea() {
		return placeArea;
	}

	public void setPlaceArea(String placeArea) {
		this.placeArea = placeArea;
	}

	public String getWorkerNumber() {
		return workerNumber;
	}

	public void setWorkerNumber(String workerNumber) {
		this.workerNumber = workerNumber;
	}

	public String getPlacePicture() {
		return placePicture;
	}

	public void setPlacePicture(String placePicture) {
		this.placePicture = placePicture;
	}

	public String getGoverningBodyName() {
		return governingBodyName;
	}

	public void setGoverningBodyName(String governingBodyName) {
		this.governingBodyName = governingBodyName;
	}

	public String getAdministrativeDivision() {
		return administrativeDivision;
	}

	public void setAdministrativeDivision(String administrativeDivision) {
		this.administrativeDivision = administrativeDivision;
	}

	public String getPlaceType() {
		return placeType;
	}

	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}

	public User getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}
}