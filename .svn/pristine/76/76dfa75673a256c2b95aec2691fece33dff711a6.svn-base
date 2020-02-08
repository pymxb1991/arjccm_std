/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 请求登记Entity
 * @author arj
 * @version 2018-01-18
 */
public class CcmEventRequest extends DataEntity<CcmEventRequest> {
	
	private static final long serialVersionUID = 1L;
	private String caseName;		// 请求名称
	private Date happenDate;		// 发生日期
	private Area area;		// 请求地点
	private String happenPlace;		// 详址
	private String areaMap;		// 区域图
	private String areaPoint;		// 中心点
	private String icon;		// 图标
	private String eventKind;		// 请求种类
	private String caseCondition;		// 请求内容
	private String type;		// 处理状态
	private Date beginHappenDate;		// 开始 发生日期
	private Date endHappenDate;		// 结束 发生日期
	private String createName; // 创建者
	private String typeLable;	//用于app接口列表显示
	private String file;	//附件
	
	private User checkUser;		// 拦截器中使用该用户进行权限拦截，App的rest接口使用
	public User getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}

	public CcmEventRequest() {
		super();
	}

	public CcmEventRequest(String id){
		super(id);
	}

	@Length(min=0, max=100, message="请求名称长度必须介于 0 和 100 之间")
	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getHappenDate() {
		return happenDate;
	}

	public void setHappenDate(Date happenDate) {
		this.happenDate = happenDate;
	}
	
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@Length(min=0, max=200, message="详址长度必须介于 0 和 200 之间")
	public String getHappenPlace() {
		return happenPlace;
	}

	public void setHappenPlace(String happenPlace) {
		this.happenPlace = happenPlace;
	}
	
	@Length(min=0, max=2000, message="区域图长度必须介于 0 和 2000 之间")
	public String getAreaMap() {
		return areaMap;
	}

	public void setAreaMap(String areaMap) {
		this.areaMap = areaMap;
	}
	
	@Length(min=0, max=40, message="中心点长度必须介于 0 和 40 之间")
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
	
	@Length(min=0, max=2, message="请求种类长度必须介于 0 和 2 之间")
	public String getEventKind() {
		return eventKind;
	}

	public void setEventKind(String eventKind) {
		this.eventKind = eventKind;
	}
	
	public String getCaseCondition() {
		return caseCondition;
	}

	public void setCaseCondition(String caseCondition) {
		this.caseCondition = caseCondition;
	}
	
	@Length(min=0, max=2, message="当前状态长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Date getBeginHappenDate() {
		return beginHappenDate;
	}

	public void setBeginHappenDate(Date beginHappenDate) {
		this.beginHappenDate = beginHappenDate;
	}
	
	public Date getEndHappenDate() {
		return endHappenDate;
	}

	public void setEndHappenDate(Date endHappenDate) {
		this.endHappenDate = endHappenDate;
	}
		
	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getTypeLable() {
		return typeLable;
	}
	public void setTypeLable(String typeLable) {
		this.typeLable = typeLable;
	}
	
	@Length(min=0, max=255, message="当前状态长度必须介于 0 和 255 之间")
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}

}