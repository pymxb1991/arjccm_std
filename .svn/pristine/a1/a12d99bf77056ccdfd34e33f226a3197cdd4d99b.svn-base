/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.entity.preview;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 事件预处理Entity
 * 
 * @author lgh
 * @version 2019-05-06
 */
public class CcmEventIncidentPreview extends DataEntity<CcmEventIncidentPreview> {

	private static final long serialVersionUID = 1L;
	private String caseName; // 案（事)件名称
	private Date happenDate; // 发生日期
	private String casePlace; // 发案地 （所属网格）
	private Area area; //发案地
	private String address;	//发案地代码
	private String happenPlace; // 发生地详址
	private String areaMap; // 区域图
	private String areaPoint; // 中心点
	private String image; // 图标
	private String eventKind; // 事件类别
	private String otherId; // 种类详细信息
	private String eventScale; // 案（事）件分级
	private String eventType; // 案（事）件类型
	private String number; // 案（事)件编号
	private String property; // 案（事)件性质
	private String caseCondition; // 案（事)件情况
	private String culPapercode; // 主犯（嫌疑人)证件代码
	private String culPaperid; // 主犯（嫌疑人)证件号码
	private String culName; // 主犯（嫌疑人）姓名
	private Integer typeSolve; // 是否破案
	private Integer numCrime; // 作案人数
	private Integer numFlee; // 在逃人数
	private Integer numArrest; // 抓捕人数
	private Date caseOverDay; // 侦查终结日期
	private String caseSolve; // 案件侦破情况
	private String file1; // 附件1
	private String file2; // 附件2
	private String file3; // 附件3
	private String status; // 状态
	private String reportPerson; // 上报人
	private String reportPersonPhone; // 上报人联系电话
	private String isKeyPlace; // 是否重点场所
	private String caseScope; // 事件规模
	private String reportType; // 上报类型
	private String eventKindParentType; // 事件类别_父类型类别
	private Date beginHappenDate; // 开始 发生日期
	private Date endHappenDate; // 结束 发生日期
	private String similarity; // 相似度阈值
	private String eventSort;	//事件模块分类

	private Area userArea;  //登录用户所在区域
	

	public Area getUserArea() {
		return userArea;
	}

	public void setUserArea(Area userArea) {
		this.userArea = userArea;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Length(min = 0, max = 255, message = "发案地代码长度必须介于 1 和 255 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSimilarity() {
		return similarity;
	}

	public void setSimilarity(String similarity) {
		this.similarity = similarity;
	}

	public CcmEventIncidentPreview() {
		super();
	}

	public CcmEventIncidentPreview(String id) {
		super(id);
	}

	@Length(min = 1, max = 100, message = "案（事)件名称长度必须介于 1 和 100 之间")
	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message = "发生日期不能为空")
	public Date getHappenDate() {
		return happenDate;
	}

	public void setHappenDate(Date happenDate) {
		this.happenDate = happenDate;
	}

	@Length(min = 1, max = 64, message = "发案地 （所属网格）长度必须介于 1 和 64 之间")
	public String getCasePlace() {
		return casePlace;
	}

	public void setCasePlace(String casePlace) {
		this.casePlace = casePlace;
	}

	@Length(min = 0, max = 200, message = "发生地详址长度必须介于 0 和 200 之间")
	public String getHappenPlace() {
		return happenPlace;
	}

	public void setHappenPlace(String happenPlace) {
		this.happenPlace = happenPlace;
	}

	@Length(min = 0, max = 2000, message = "区域图长度必须介于 0 和 2000 之间")
	public String getAreaMap() {
		return areaMap;
	}

	public void setAreaMap(String areaMap) {
		this.areaMap = areaMap;
	}

	@Length(min = 0, max = 40, message = "中心点长度必须介于 0 和 40 之间")
	public String getAreaPoint() {
		return areaPoint;
	}

	public void setAreaPoint(String areaPoint) {
		this.areaPoint = areaPoint;
	}

	@Length(min = 0, max = 255, message = "图标长度必须介于 0 和 255 之间")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Length(min = 0, max = 2, message = "事件模块分类长度必须介于 0 和 2 之间")
	public String getEventKind() {
		return eventKind;
	}

	public void setEventKind(String eventKind) {
		this.eventKind = eventKind;
	}

	@Length(min = 0, max = 64, message = "种类详细信息长度必须介于 0 和 64 之间")
	public String getOtherId() {
		return otherId;
	}

	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}

	@Length(min = 0, max = 2, message = "案（事）件分级长度必须介于 0 和 2 之间")
	public String getEventScale() {
		return eventScale;
	}

	public void setEventScale(String eventScale) {
		this.eventScale = eventScale;
	}

	@Length(min = 0, max = 2, message = "案（事）件类型长度必须介于 0 和 2 之间")
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	@Length(min = 0, max = 100, message = "案（事)件编号长度必须介于 0 和 100 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Length(min = 0, max = 2, message = "案（事)件性质长度必须介于 0 和 2 之间")
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getCaseCondition() {
		return caseCondition;
	}

	public void setCaseCondition(String caseCondition) {
		this.caseCondition = caseCondition;
	}

	@Length(min = 0, max = 3, message = "主犯（嫌疑人)证件代码长度必须介于 0 和 3 之间")
	public String getCulPapercode() {
		return culPapercode;
	}

	public void setCulPapercode(String culPapercode) {
		this.culPapercode = culPapercode;
	}

	@Length(min = 0, max = 50, message = "主犯（嫌疑人)证件号码长度必须介于 0 和 50 之间")
	public String getCulPaperid() {
		return culPaperid;
	}

	public void setCulPaperid(String culPaperid) {
		this.culPaperid = culPaperid;
	}

	@Length(min = 0, max = 80, message = "主犯（嫌疑人）姓名长度必须介于 0 和 80 之间")
	public String getCulName() {
		return culName;
	}

	public void setCulName(String culName) {
		this.culName = culName;
	}

	public Integer getTypeSolve() {
		return typeSolve;
	}

	public void setTypeSolve(Integer typeSolve) {
		this.typeSolve = typeSolve;
	}

	public Integer getNumCrime() {
		return numCrime;
	}

	public void setNumCrime(Integer numCrime) {
		this.numCrime = numCrime;
	}

	public Integer getNumFlee() {
		return numFlee;
	}

	public void setNumFlee(Integer numFlee) {
		this.numFlee = numFlee;
	}

	public Integer getNumArrest() {
		return numArrest;
	}

	public void setNumArrest(Integer numArrest) {
		this.numArrest = numArrest;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCaseOverDay() {
		return caseOverDay;
	}

	public void setCaseOverDay(Date caseOverDay) {
		this.caseOverDay = caseOverDay;
	}

	public String getCaseSolve() {
		return caseSolve;
	}

	public void setCaseSolve(String caseSolve) {
		this.caseSolve = caseSolve;
	}

	@Length(min = 0, max = 256, message = "附件1长度必须介于 0 和 256 之间")
	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}

	@Length(min = 0, max = 256, message = "附件2长度必须介于 0 和 256 之间")
	public String getFile2() {
		return file2;
	}

	public void setFile2(String file2) {
		this.file2 = file2;
	}

	@Length(min = 0, max = 256, message = "附件3长度必须介于 0 和 256 之间")
	public String getFile3() {
		return file3;
	}

	public void setFile3(String file3) {
		this.file3 = file3;
	}

	@Length(min = 0, max = 2, message = "状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Length(min = 1, max = 15, message = "上报人长度必须介于 1 和 15 之间")
	public String getReportPerson() {
		return reportPerson;
	}

	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}

	@Length(min = 0, max = 11, message = "上报人联系电话长度必须介于 0 和 11 之间")
	public String getReportPersonPhone() {
		return reportPersonPhone;
	}

	public void setReportPersonPhone(String reportPersonPhone) {
		this.reportPersonPhone = reportPersonPhone;
	}

	@Length(min = 1, max = 2, message = "是否重点场所长度必须介于 1 和 2 之间")
	public String getIsKeyPlace() {
		return isKeyPlace;
	}

	public void setIsKeyPlace(String isKeyPlace) {
		this.isKeyPlace = isKeyPlace;
	}

	@Length(min = 1, max = 255, message = "事件规模长度必须介于 1 和 255 之间")
	public String getCaseScope() {
		return caseScope;
	}

	public void setCaseScope(String caseScope) {
		this.caseScope = caseScope;
	}

	@Length(min = 1, max = 10, message = "上报类型长度必须介于 1 和 10 之间")
	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	@Length(min = 0, max = 2, message = "事件类别_父类型类别长度必须介于 0 和 2 之间")
	public String getEventKindParentType() {
		return eventKindParentType;
	}

	public void setEventKindParentType(String eventKindParentType) {
		this.eventKindParentType = eventKindParentType;
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

	@Length(min = 0, max = 2, message = "事件类别长度必须介于 0 和 2 之间")
	public String getEventSort() {
		return eventSort;
	}

	public void setEventSort(String eventSort) {
		this.eventSort = eventSort;
	}
}