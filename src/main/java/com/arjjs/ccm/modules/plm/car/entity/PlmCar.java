/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;

/**
 * 车辆Entity
 * @author fu
 * @version 2018-06-30
 */
public class PlmCar extends DataEntity<PlmCar> {
	
	private static final long serialVersionUID = 1L;
	private String vehicle;		// 车牌号
	private String photo;		//照片
	private String vehicleStatus;		// 状态
	private String brand;		// 品牌
	private String vmodel;		// 型号
	private String vtype;		// 车型
	private String engineNum;		// 发动机号
	private Date productDate;		// 出厂日期
	private Integer loadNum;		// 核载人数
	private Integer loadCapacity;		// 载重量(kg)
	private Date buyDate;		// 购车日期
	private Date annualDate;		// 年检时间
	private Date inspectionDate;		// 应检日期
	private String insuranceType;		// 保险种类
	private Date trafDate;		// 交强险到期日期
	private Date commDate;		// 商业险到期时间
	private Integer mileage;		// 行驶里程
	private Integer maintainMil;		//上次保养里程
	private String stateDescription;		// 状况描述
	private String carFlag;		// 车辆标志
	private String carFeature;		// 车辆特征
	private Office office;	//所属部门
	
	public PlmCar() {
		super();
	}

	public PlmCar(String id){
		super(id);
	}

	@Length(min=0, max=64, message="车牌号长度必须介于 0 和 64 之间")
	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	
	@Length(min=0, max=2, message="状态长度必须介于 0 和 2 之间")
	public String getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}
	
	@Length(min=0, max=64, message="品牌长度必须介于 0 和 64 之间")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Length(min=0, max=64, message="型号长度必须介于 0 和 64 之间")
	public String getVmodel() {
		return vmodel;
	}

	public void setVmodel(String vmodel) {
		this.vmodel = vmodel;
	}
	
	@Length(min=0, max=2, message="车型长度必须介于 0 和 2 之间")
	public String getVtype() {
		return vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}
	
	@Length(min=0, max=64, message="发动机号长度必须介于 0 和 64 之间")
	public String getEngineNum() {
		return engineNum;
	}

	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	
	public Integer getLoadNum() {
		return loadNum;
	}

	public void setLoadNum(Integer loadNum) {
		this.loadNum = loadNum;
	}
	
	public Integer getLoadCapacity() {
		return loadCapacity;
	}

	public void setLoadCapacity(Integer loadCapacity) {
		this.loadCapacity = loadCapacity;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAnnualDate() {
		return annualDate;
	}

	public void setAnnualDate(Date annualDate) {
		this.annualDate = annualDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	
	@Length(min=0, max=2, message="保险种类长度必须介于 0 和 2 之间")
	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTrafDate() {
		return trafDate;
	}

	public void setTrafDate(Date trafDate) {
		this.trafDate = trafDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCommDate() {
		return commDate;
	}

	public void setCommDate(Date commDate) {
		this.commDate = commDate;
	}
	
	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}
	
	@Length(min=0, max=1000, message="状况描述长度必须介于 0 和 1000 之间")
	public String getStateDescription() {
		return stateDescription;
	}

	public void setStateDescription(String stateDescription) {
		this.stateDescription = stateDescription;
	}
	
	@Length(min=0, max=50, message="车辆标志长度必须介于 0 和 50 之间")
	public String getCarFlag() {
		return carFlag;
	}

	public void setCarFlag(String carFlag) {
		this.carFlag = carFlag;
	}

	public List<String> getCarFlagList() {
		List<String> list = Lists.newArrayList();
		if (carFlag != null){
			for (String s : StringUtils.split(carFlag, ",")) {
				list.add(s);
			}
		}
		return list;
	}
	public void setCarFlagList(List<String> list) {
		carFlag = ","+StringUtils.join(list, ",")+",";
	}
	
	@Length(min=0, max=50, message="车辆特征长度必须介于 0 和 50 之间")
	public String getCarFeature() {
		return carFeature;
	}

	public void setCarFeature(String carFeature) {
		this.carFeature = carFeature;
	}
	public List<String> getCarFeatureList() {
		List<String> list = Lists.newArrayList();
		if (carFeature != null){
			for (String s : StringUtils.split(carFeature, ",")) {
				list.add(s);
			}
		}
		return list;
	}
	public void setCarFeatureList(List<String> list) {
		carFeature = ","+StringUtils.join(list, ",")+",";
	}
	public Integer getMaintainMil() {
		return maintainMil;
	}

	public void setMaintainMil(Integer maintainMil) {
		this.maintainMil = maintainMil;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
}