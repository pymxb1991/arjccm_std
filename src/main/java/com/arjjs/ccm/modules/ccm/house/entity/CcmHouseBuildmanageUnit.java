/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 楼栋单元户信息排列Entity
 * @author liu
 * @version 2019-04-24
 */
public class CcmHouseBuildmanageUnit extends DataEntity<CcmHouseBuildmanageUnit> {
	
	private static final long serialVersionUID = 1L;
	private String buildmanageId;		// 楼栋ID
	private String residentialUnit;		//小区单元
	private String x;		// 横向坐标x
	private String y;		// 纵向坐标y
	private String houseNum;		// 门牌号
	private String userId;
	private String doorNum;
	private String houseName;
	private String houseType;
	private String tId;
	private String houseBuild;
	private String housePlace;
	private String housePrup;
	private String buildingId;
	private String buildDoorNum;
	private int floorNum;
	private String houseArea;
	private String idenCode;
	private String idenNum;
	private String houseTl;
	private String houseCur;
	private String rentPur;
	private String hazard;
	private String tenantId;
	private String tenantName;
	private String tenantTl;
	private String areaId;
	private String areaMap;
	private String areaPoint;
	private String image;
	private String bName;
	private String bBuildName;
	private String bAreaId;
	private String bFloorArea;
	private int bPilesNum;
	private int bElemNum;
	private int bBuildNum;
	private int bBuildPeo;
	private String bBuildPname;
	private String bSex;
	private String bNation;
	private String bContent;
	private String bBorthday;
	private String bEducation;
	private String bTel;
	private String bPhone;
	private String bResidence;
	private String bResidencedetail;
	private String bAreaMap;
	private String bAreaPoint;
	private String bImage;
	private String bImages;
	

	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	public String getHouseBuild() {
		return houseBuild;
	}

	public void setHouseBuild(String houseBuild) {
		this.houseBuild = houseBuild;
	}

	public String getHousePlace() {
		return housePlace;
	}

	public void setHousePlace(String housePlace) {
		this.housePlace = housePlace;
	}

	public String getHousePrup() {
		return housePrup;
	}

	public void setHousePrup(String housePrup) {
		this.housePrup = housePrup;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getBuildDoorNum() {
		return buildDoorNum;
	}

	public void setBuildDoorNum(String buildDoorNum) {
		this.buildDoorNum = buildDoorNum;
	}

	public int getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(int floorNum) {
		this.floorNum = floorNum;
	}

	public String getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}

	public String getIdenCode() {
		return idenCode;
	}

	public void setIdenCode(String idenCode) {
		this.idenCode = idenCode;
	}

	public String getIdenNum() {
		return idenNum;
	}

	public void setIdenNum(String idenNum) {
		this.idenNum = idenNum;
	}

	public String getHouseTl() {
		return houseTl;
	}

	public void setHouseTl(String houseTl) {
		this.houseTl = houseTl;
	}

	public String getHouseCur() {
		return houseCur;
	}

	public void setHouseCur(String houseCur) {
		this.houseCur = houseCur;
	}

	public String getRentPur() {
		return rentPur;
	}

	public void setRentPur(String rentPur) {
		this.rentPur = rentPur;
	}

	public String getHazard() {
		return hazard;
	}

	public void setHazard(String hazard) {
		this.hazard = hazard;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getTenantTl() {
		return tenantTl;
	}

	public void setTenantTl(String tenantTl) {
		this.tenantTl = tenantTl;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaMap() {
		return areaMap;
	}

	public void setAreaMap(String areaMap) {
		this.areaMap = areaMap;
	}

	public String getAreaPoint() {
		return areaPoint;
	}

	public void setAreaPoint(String areaPoint) {
		this.areaPoint = areaPoint;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getbBuildName() {
		return bBuildName;
	}

	public void setbBuildName(String bBuildName) {
		this.bBuildName = bBuildName;
	}

	public String getbAreaId() {
		return bAreaId;
	}

	public void setbAreaId(String bAreaId) {
		this.bAreaId = bAreaId;
	}

	public String getbFloorArea() {
		return bFloorArea;
	}

	public void setbFloorArea(String bFloorArea) {
		this.bFloorArea = bFloorArea;
	}

	public int getbPilesNum() {
		return bPilesNum;
	}

	public void setbPilesNum(int bPilesNum) {
		this.bPilesNum = bPilesNum;
	}

	public int getbElemNum() {
		return bElemNum;
	}

	public void setbElemNum(int bElemNum) {
		this.bElemNum = bElemNum;
	}

	public int getbBuildNum() {
		return bBuildNum;
	}

	public void setbBuildNum(int bBuildNum) {
		this.bBuildNum = bBuildNum;
	}

	public int getbBuildPeo() {
		return bBuildPeo;
	}

	public void setbBuildPeo(int bBuildPeo) {
		this.bBuildPeo = bBuildPeo;
	}

	public String getbBuildPname() {
		return bBuildPname;
	}

	public void setbBuildPname(String bBuildPname) {
		this.bBuildPname = bBuildPname;
	}

	public String getbSex() {
		return bSex;
	}

	public void setbSex(String bSex) {
		this.bSex = bSex;
	}

	public String getbNation() {
		return bNation;
	}

	public void setbNation(String bNation) {
		this.bNation = bNation;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getbBorthday() {
		return bBorthday;
	}

	public void setbBorthday(String bBorthday) {
		this.bBorthday = bBorthday;
	}

	public String getbEducation() {
		return bEducation;
	}

	public void setbEducation(String bEducation) {
		this.bEducation = bEducation;
	}

	public String getbTel() {
		return bTel;
	}

	public void setbTel(String bTel) {
		this.bTel = bTel;
	}

	public String getbPhone() {
		return bPhone;
	}

	public void setbPhone(String bPhone) {
		this.bPhone = bPhone;
	}

	public String getbResidence() {
		return bResidence;
	}

	public void setbResidence(String bResidence) {
		this.bResidence = bResidence;
	}

	public String getbResidencedetail() {
		return bResidencedetail;
	}

	public void setbResidencedetail(String bResidencedetail) {
		this.bResidencedetail = bResidencedetail;
	}

	public String getbAreaMap() {
		return bAreaMap;
	}

	public void setbAreaMap(String bAreaMap) {
		this.bAreaMap = bAreaMap;
	}

	public String getbAreaPoint() {
		return bAreaPoint;
	}

	public void setbAreaPoint(String bAreaPoint) {
		this.bAreaPoint = bAreaPoint;
	}

	public String getbImage() {
		return bImage;
	}

	public void setbImage(String bImage) {
		this.bImage = bImage;
	}

	public String getbImages() {
		return bImages;
	}

	public void setbImages(String bImages) {
		this.bImages = bImages;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getDoorNum() {
		return doorNum;
	}

	public void setDoorNum(String doorNum) {
		this.doorNum = doorNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public CcmHouseBuildmanageUnit() {
		super();
	}

	public CcmHouseBuildmanageUnit(String id){
		super(id);
	}

	@Length(min=0, max=64, message="楼栋ID长度必须介于 0 和 64 之间")
	public String getBuildmanageId() {
		return buildmanageId;
	}

	public void setBuildmanageId(String buildmanageId) {
		this.buildmanageId = buildmanageId;
	}
	
	public String getResidentialUnit() {
		return residentialUnit;
	}

	public void setResidentialUnit(String residentialUnit) {
		this.residentialUnit = residentialUnit;
	}

	@Length(min=0, max=2, message="横向坐标x长度必须介于 0 和 2 之间")
	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}
	
	@Length(min=0, max=2, message="纵向坐标y长度必须介于 0 和 2 之间")
	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}
	
	@Length(min=0, max=64, message="门牌号长度必须介于 0 和 64 之间")
	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}
	
}