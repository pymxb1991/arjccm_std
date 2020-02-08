/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.financial.entity;

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
 * 金融机构Entity
 * 
 * @author lgh
 * @version 2019-04-29
 */
public class CcmPlaceFinancial extends DataEntity<CcmPlaceFinancial> {

	private static final long serialVersionUID = 1L;
	private CcmBasePlace ccmBasePlace;
	private String type; // 场所类型
	private String bankType; // 银行类型
	private String basePlaceId; // 场所id

	// 场所基础信息（部门）
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
		if (ccmBasePlace!=null&&ccmBasePlace.getKeyPoint() != null) {
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

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getRelevanceOrg() {
		return relevanceOrg;
	}

	public void setRelevanceOrg(String relevanceOrg) {
		this.relevanceOrg = relevanceOrg;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLeaderContact() {
		return leaderContact;
	}

	public void setLeaderContact(String leaderContact) {
		this.leaderContact = leaderContact;
	}

	public CcmBasePlace getCcmBasePlace() {
		return ccmBasePlace;
	}

	public void setCcmBasePlace(CcmBasePlace ccmBasePlace) {
		this.ccmBasePlace = ccmBasePlace;
	}

	public CcmPlaceFinancial() {
		super();
	}

	public CcmPlaceFinancial(String id) {
		super(id);
	}

	@Length(min = 0, max = 2, message = "场所类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min = 0, max = 255, message = "民银行 商业银行 信用社 邮政储蓄 非银行金融机构）长度必须介于 0 和 255 之间")
	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	@Length(min = 0, max = 64, message = "场所id长度必须介于 0 和 64 之间")
	public String getBasePlaceId() {
		return basePlaceId;
	}

	public void setBasePlaceId(String basePlaceId) {
		this.basePlaceId = basePlaceId;
	}

	public String getLeaderIdCard() {
		return leaderIdCard;
	}

	public void setLeaderIdCard(String leaderIdCard) {
		this.leaderIdCard = leaderIdCard;
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