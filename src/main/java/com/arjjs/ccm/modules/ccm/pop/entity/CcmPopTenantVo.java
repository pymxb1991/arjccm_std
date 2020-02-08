/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.pop.entity;

import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseBuildmanage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 房屋Entity
 * @author wwh
 * @version 2018-01-10
 */
@ApiModel
public class CcmPopTenantVo  {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "房屋id")
	private String id;

	@ApiModelProperty(value = "楼栋id")
	private String buildingId;

	@ApiModelProperty(value = "所属楼栋名称")
	private String buildingName;

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    @ApiModelProperty(value = "房屋编号")
	private String houseBuild;		// 房屋编号

	@ApiModelProperty(value = "房屋地址")
	private String housePlace;		// 房屋地址

	@ApiModelProperty(value = "建筑用途")
	private String housePrup;		// 建筑用途

	@ApiModelProperty(value = "楼门号")
    private String buildDoorNum;	// 楼门号
	@ApiModelProperty(value = "层数")
	private String floorNum;		// 层数
	@ApiModelProperty(value = "门牌号")
	private String doorNum;		    // 门牌号
	@ApiModelProperty(value = "建筑面积(平方米）")
	private Double houseArea;		// 建筑面积(平方米）
	@ApiModelProperty(value = "房屋产权类型")
	private String propertyType;   //房屋产权类型
	@ApiModelProperty(value = "年限")
	private String buildingYears;	//年限
	@ApiModelProperty(value = "建筑类型")
	private String buildingType;	//建筑类型
	@ApiModelProperty(value = "状态")
	private String houseType;		// 状态
	@ApiModelProperty(value = "证件代码")
	private String idenCode;		// 证件代码
	@ApiModelProperty(value = "证件号码")
	private String idenNum;		// 证件号码
	@ApiModelProperty(value = "房主姓名")
	private String houseName;		// 房主姓名
	@ApiModelProperty(value = "房主联系方式")
	private String houseTl;		// 房主联系方式
	@ApiModelProperty(value = "房主现在居住详细地址")
	private String houseCur;		// 房主现在居住详细地址
	@ApiModelProperty(value = "出租用途")
	private String rentPur;		// 出租用途
	@ApiModelProperty(value = "隐患类型")
	private String hazard;		// 隐患类型
	@ApiModelProperty(value = " 承租人公民身份号码")
	private String tenantId;		// 承租人公民身份号码
	@ApiModelProperty(value = "承租人姓名")
	private String tenantName;		// 承租人姓名
	@ApiModelProperty(value = "承租人联系方式")
	private String tenantTl;		// 承租人联系方式
	@ApiModelProperty(value = "区域图")
	private String areaMap; // 区域图
	@ApiModelProperty(value = "中心点")
	private String areaPoint;		// 中心点
	@ApiModelProperty(value = "图标")
	private String image;		// 图标
	@ApiModelProperty(value = "额外参数")
	private String more1;      // 额外参数
	@ApiModelProperty(value = " ")
	private String count;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getBuildDoorNum() {
		return buildDoorNum;
	}

	public void setBuildDoorNum(String buildDoorNum) {
		this.buildDoorNum = buildDoorNum;
	}

	public String getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
	}

	public String getDoorNum() {
		return doorNum;
	}

	public void setDoorNum(String doorNum) {
		this.doorNum = doorNum;
	}

	public Double getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(Double houseArea) {
		this.houseArea = houseArea;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getBuildingYears() {
		return buildingYears;
	}

	public void setBuildingYears(String buildingYears) {
		this.buildingYears = buildingYears;
	}

	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
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

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
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

	public String getMore1() {
		return more1;
	}

	public void setMore1(String more1) {
		this.more1 = more1;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
}