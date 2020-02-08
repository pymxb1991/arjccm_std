package com.arjjs.ccm.modules.ccm.sys.entity;

import java.util.Date;

import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 日常走访、事件处理导出实体类
 * @author cby
 *
 */
public class CcmWorkReportVO {
	
	private String name; 			//行政社区名称
	private String createName;  	// 创建人姓名
	private String telephone;  		//电话
	private String type;			// 类型
	private String eventtype; 	//事件类型
	private Date beginDate;			// 开始日期
	private Date endDate;			// 结束日期
	private String place;  			//走访地址
	private String title;			// 标题
	private String content;			// 内容
	
	@JsonIgnore
	@ExcelField(title="行政区划", align=2, sort=1)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JsonIgnore
	@ExcelField(title="社工姓名", align=2, sort=2)
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	@JsonIgnore
	@ExcelField(title="电话", align=2, sort=3)
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@JsonIgnore
	@ExcelField(title="日志类型", align=2, sort=4)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@JsonIgnore
	@ExcelField(title="事件类型", align=2, sort=5)
	public String getEventtype() {
		return eventtype;
	}
	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}
	@JsonIgnore
	@ExcelField(title="开始时间", align=2, sort=6)
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	@JsonIgnore
	@ExcelField(title="结束时间", align=2, sort=7)
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@JsonIgnore
	@ExcelField(title="地点", align=2, sort=8)
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	@JsonIgnore
	@ExcelField(title="标题", align=2, sort=9)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@JsonIgnore
	@ExcelField(title="内容", align=2, sort=10)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
