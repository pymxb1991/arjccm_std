/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.ccmsys.entity;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.ccm.view.entity.VCcmTeam;
import com.arjjs.ccm.modules.sys.entity.Area;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Date;

/**
 * 移动设备管理Entity
 * @author fu
 * @version 2018-04-20
 */
@ApiModel
public class CcmMobileDevice extends DataEntity<CcmMobileDevice> {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="设备唯一标识码")
	private String deviceId;		// 设备唯一标识码
	@ApiModelProperty(value=" 使用者")
	private VCcmTeam vCcmTeam;		// 使用者
	@ApiModelProperty(value="最后一次使用位置")
	private String areaPoint;		// 最后一次使用位置
	@ApiModelProperty(value="是否授权使用")
	private String isVariable;		//是否授权使用
	@ApiModelProperty(value="中心点")
	private String efencePoint;		//中心点
	@ApiModelProperty(value="电子围栏范围")
	private String efenceScope;		//电子围栏范围
	@ApiModelProperty(value="图标")
	private String icon;		// 图标
	@ApiModelProperty(value="开始 创建时间")
	private Date beginCreateDate;		// 开始 创建时间
	@ApiModelProperty(value="结束 创建时间")
	private Date endCreateDate;		// 结束 创建时间
	@ApiModelProperty(value="使用类型")
	private String useType; //使用类型
	@ApiModelProperty(value="所在部门")
	private String officeName;	//所在部门
	@ApiModelProperty(value="是否越界报警")
	private String isAlarm;		// 是否越界报警
	@ApiModelProperty(value="是否在线")
	private String isOnline;	// 是否在线（根据坐标更新时间判断）
	@ApiModelProperty(value="登录用户所在区域")
	private Area userArea;  //登录用户所在区域


	private String baseUrl;	// app服务器地址

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public Area getUserArea() {
		return userArea;
	}

	public void setUserArea(Area userArea) {
		this.userArea = userArea;
	}

	//增加字段，用于地图轨迹查询树形用 pengjianqiang20180905
	private String type; // 设备类型
	private String typeClass; // 类型种类
	private String typeCaption; // 设备类型名称
	private String typeName; //设备类型文字
	private String name; // 设备名称
	private String parentId; // 父id
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeClass() {
		return typeClass;
	}

	public void setTypeClass(String typeClass) {
		this.typeClass = typeClass;
	}

	public String getTypeCaption() {
		return typeCaption;
	}

	public void setTypeCaption(String typeCaption) {
		this.typeCaption = typeCaption;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getEfencePoint() {
		return efencePoint;
	}

	public void setEfencePoint(String efencePoint) {
		this.efencePoint = efencePoint;
	}

	public String getEfenceScope() {
		return efenceScope;
	}

	public void setEfenceScope(String efenceScope) {
		this.efenceScope = efenceScope;
	}

	public CcmMobileDevice() {
		super();
	}

	public CcmMobileDevice(String id){
		super(id);
	}

	@Length(min=1, max=64, message="设备唯一标识码长度必须介于 1 和 64 之间")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	
	@Length(min=0, max=40, message="最后一次使用位置长度必须介于 0 和 40 之间")
	public String getAreaPoint() {
		return areaPoint;
	}

	public void setAreaPoint(String areaPoint) {
		this.areaPoint = areaPoint;
	}
	
	@Length(min=0, max=255, message="图标长度必须介于 0 和 255 之间")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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


	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}


	public VCcmTeam getvCcmTeam() {
		return vCcmTeam;
	}

	public void setvCcmTeam(VCcmTeam vCcmTeam) {
		this.vCcmTeam = vCcmTeam;
	}

	public String getIsVariable() {
		return isVariable;
	}

	public void setIsVariable(String isVariable) {
		this.isVariable = isVariable;
	}

	public String getIsAlarm() {
		return isAlarm;
	}

	public void setIsAlarm(String isAlarm) {
		this.isAlarm = isAlarm;
	}

	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}

		
}