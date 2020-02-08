/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.alarm.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.config.Global;
import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.utils.DictUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 实时警情Entity
 * 
 * @author wangyikai
 * @version 2018-11-14
 */
public class BphAlarmInfo extends DataEntity<BphAlarmInfo> {

	private static final long serialVersionUID = 1L;
	private String orderNum; // 接警单编号
	private String policeNum; // 接警员工号
	private String policeName; // 接警员姓名
	private String manName; // 报警人姓名
	private String manSex; // 报警人性别
	private String manTel; // 报警人联系电话
	private String place; // 案发地点
	private Double x; // 经度
	private Double y; // 纬度
	private Double z; // 高度
	private String content; // 警情内容
	private String typeCode; // 警情类型代码
	private String typeName; // 警情类型名称
	private String genreCode; // 警情类别代码
	private String classCode; // 警情细类代码
	private Area area; // 区域
	private String areaId;
	private Office office; // 受理单位
	private String officeName;//受理单位名称
	private String officeId;
	private String officeIds;
	private String alarmFrom; // 报警方式
	private Date alarmTime; // 报警时间
	private String alarmRecord; // 接警录音号
	private String isImportant; // 是否为重大警情
	private String state; // 警情状态
	private Date beginAlarmTime; // 开始 报警时间
	private Date endAlarmTime; // 结束 报警时间
	private String num; // 区域内警情数量
	private String areaMap; // 围成区域的点位
	private String areaPoint; // 区域中心点位
	private String[] typeCodes;
	private String optionDesc;// 操作描述
	private String oName;
	private String count;
	private String value0;
	private String value1;
	private String value2;
	private String value3;
	private String dateTime;// 日期
	private String criminalNum;// 刑事案件数量
	private String administrationNum;// 行政案件数量
	private String peaceNum;// 治安事件数量
	private String fireNum;// 火灾事故数量
	private String trafficNum;// 交通事故数量
	private String disputeNum;// 纠纷数量
	private String helpNum;// 公民求助数量
	private String superviseNum;// 警务监督数量
	private String otherNum;// 其他数量
	private String sum;//统计的总数量
	private String count0;// 统计state=0数量
	private String count1;// 统计state=1数量
	private String count2;// 统计state=2数量
	private String count3;// 统计state=3数量
	private String numState;// 按部门统计数量
	private String numState0;// 按部门统计数量
	private String numState1;// 按部门统计数量
	private String numState2;// 按部门统计数量
	private String numState3;// 按部门统计数量
	private String name;
	private String time;
	private String currentPage;
	private Integer pageSize;
	private int startIndex;
	private String parentId;
	private boolean incSubset;//包括下级部门
	private String handleStatus;		// 处置状态
	private String handleTask;//任务描述	
	private String handlePoliceId;//出警人员id
	private String dealTypeCode;//处理类型代码
	private String receiveOfficeId;//接警单位 

	
	public String getOfficeIds() {
		return officeIds;
	}

	public void setOfficeIds(String officeIds) {
		this.officeIds = officeIds;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getHandlePoliceId() {
		return handlePoliceId;
	}

	public void setHandlePoliceId(String handlePoliceId) {
		this.handlePoliceId = handlePoliceId;
	}

	public String getHandleTask() {
		return handleTask;
	}

	public void setHandleTask(String handleTask) {
		this.handleTask = handleTask;
	}

	public String getHandleStatus() {
		return handleStatus;
	}

	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}

	public boolean isIncSubset() {
		return incSubset;
	}

	public void setIncSubset(boolean incSubset) {
		this.incSubset = incSubset;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getStartIndex() {
		if(StringUtils.isNotBlank(currentPage) && pageSize != null) {
			startIndex = (Integer.valueOf(currentPage) - 1) * pageSize;
		}
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getTypeName() {
		typeName = DictUtils.getDictLabel(typeCode, "bph_alarm_info_typecode", "");
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getNumState0() {
		return numState0;
	}

	public void setNumState0(String numState0) {
		this.numState0 = numState0;
	}

	public String getNumState1() {
		return numState1;
	}

	public void setNumState1(String numState1) {
		this.numState1 = numState1;
	}

	public String getNumState2() {
		return numState2;
	}

	public void setNumState2(String numState2) {
		this.numState2 = numState2;
	}

	public String getNumState3() {
		return numState3;
	}

	public void setNumState3(String numState3) {
		this.numState3 = numState3;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCount0() {
		return count0;
	}

	public void setCount0(String count0) {
		this.count0 = count0;
	}

	public String getCount1() {
		return count1;
	}

	public void setCount1(String count1) {
		this.count1 = count1;
	}

	public String getCount2() {
		return count2;
	}

	public void setCount2(String count2) {
		this.count2 = count2;
	}

	public String getCount3() {
		return count3;
	}

	public void setCount3(String count3) {
		this.count3 = count3;
	}

	public String getNumState() {
		return numState;
	}

	public void setNumState(String numState) {
		this.numState = numState;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getCriminalNum() {
		return criminalNum;
	}

	public void setCriminalNum(String criminalNum) {
		this.criminalNum = criminalNum;
	}

	public String getAdministrationNum() {
		return administrationNum;
	}

	public void setAdministrationNum(String administrationNum) {
		this.administrationNum = administrationNum;
	}

	public String getPeaceNum() {
		return peaceNum;
	}

	public void setPeaceNum(String peaceNum) {
		this.peaceNum = peaceNum;
	}

	public String getFireNum() {
		return fireNum;
	}

	public void setFireNum(String fireNum) {
		this.fireNum = fireNum;
	}

	public String getTrafficNum() {
		return trafficNum;
	}

	public void setTrafficNum(String trafficNum) {
		this.trafficNum = trafficNum;
	}

	public String getDisputeNum() {
		return disputeNum;
	}

	public void setDisputeNum(String disputeNum) {
		this.disputeNum = disputeNum;
	}

	public String getHelpNum() {
		return helpNum;
	}

	public void setHelpNum(String helpNum) {
		this.helpNum = helpNum;
	}

	public String getSuperviseNum() {
		return superviseNum;
	}

	public void setSuperviseNum(String superviseNum) {
		this.superviseNum = superviseNum;
	}

	public String getOtherNum() {
		return otherNum;
	}

	public void setOtherNum(String otherNum) {
		this.otherNum = otherNum;
	}

	public String getValue0() {
		return value0;
	}

	public void setValue0(String value0) {
		this.value0 = value0;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getOptionDesc() {
		return optionDesc;
	}

	public void setOptionDesc(String optionDesc) {
		this.optionDesc = optionDesc;
	}

	public String[] getTypeCodes() {
		return typeCodes;
	}

	public void setTypeCodes(String[] typeCodes) {
		this.typeCodes = typeCodes;
	}

	public String getAreaPoint() {
		return areaPoint;
	}

	public void setAreaPoint(String areaPoint) {
		this.areaPoint = areaPoint;
	}

	public String getAreaMap() {
		return areaMap;
	}

	public void setAreaMap(String areaMap) {
		this.areaMap = areaMap;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public BphAlarmInfo() {
		super();
	}

	public BphAlarmInfo(String id) {
		super(id);
	}

	@Length(min = 0, max = 64, message = "接警单编号长度必须介于 0 和 64 之间")
	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	@Length(min = 0, max = 64, message = "接警员工号长度必须介于 0 和 64 之间")
	public String getPoliceNum() {
		return policeNum;
	}

	public void setPoliceNum(String policeNum) {
		this.policeNum = policeNum;
	}

	@Length(min = 0, max = 12, message = "接警员姓名长度必须介于 0 和 12 之间")
	public String getPoliceName() {
		return policeName;
	}

	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	@Length(min = 0, max = 12, message = "报警人姓名长度必须介于 0 和 12 之间")
	public String getManName() {
		return manName;
	}

	public void setManName(String manName) {
		this.manName = manName;
	}

	@Length(min = 0, max = 1, message = "报警人性别长度必须介于 0 和 1 之间")
	public String getManSex() {
		return manSex;
	}

	public void setManSex(String manSex) {
		this.manSex = manSex;
	}

	@Length(min = 0, max = 15, message = "报警人联系电话长度必须介于 0 和 15 之间")
	public String getManTel() {
		return manTel;
	}

	public void setManTel(String manTel) {
		this.manTel = manTel;
	}

	@Length(min = 0, max = 200, message = "案发地点长度必须介于 0 和 200 之间")
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getZ() {
		return z;
	}

	public void setZ(Double z) {
		this.z = z;
	}

	@Length(min = 0, max = 500, message = "警情内容长度必须介于 0 和 500 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Length(min = 0, max = 64, message = "警情类型代码长度必须介于 0 和 64 之间")
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	@Length(min = 0, max = 64, message = "警情类别代码长度必须介于 0 和 64 之间")
	public String getGenreCode() {
		return genreCode;
	}

	public void setGenreCode(String genreCode) {
		this.genreCode = genreCode;
	}

	@Length(min = 0, max = 64, message = "警情细类代码长度必须介于 0 和 64 之间")
	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Length(min = 0, max = 2, message = "报警方式长度必须介于 0 和 2 之间")
	public String getAlarmFrom() {
		return alarmFrom;
	}

	public void setAlarmFrom(String alarmFrom) {
		this.alarmFrom = alarmFrom;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAlarmTime() {
		return alarmTime;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	public String getAlarmRecord() {
		if(StringUtils.isNotBlank(alarmRecord)) {
			if(alarmRecord.indexOf("http://") == -1) {
				return Global.getConfig("ALARM_INFO_ALARM_RECORD_PATH") + alarmRecord;
			} else {
				return alarmRecord;
			}
		} else {
			return alarmRecord;
		}
	}

	public void setAlarmRecord(String alarmRecord) {
		this.alarmRecord = alarmRecord;
	}

	@Length(min = 0, max = 1, message = "是否为重大警情长度必须介于 0 和 1 之间")
	public String getIsImportant() {
		return isImportant;
	}

	public void setIsImportant(String isImportant) {
		this.isImportant = isImportant;
	}

	@Length(min = 0, max = 2, message = "警情状态长度必须介于 0 和 2 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getBeginAlarmTime() {
		return beginAlarmTime;
	}

	public void setBeginAlarmTime(Date beginAlarmTime) {
		this.beginAlarmTime = beginAlarmTime;
	}

	public Date getEndAlarmTime() {
		return endAlarmTime;
	}

	public void setEndAlarmTime(Date endAlarmTime) {
		this.endAlarmTime = endAlarmTime;
	}

	public String getDealTypeCode() {
		return dealTypeCode;
	}

	public void setDealTypeCode(String dealTypeCode) {
		this.dealTypeCode = dealTypeCode;
	}

	public String getReceiveOfficeId() {
		return receiveOfficeId;
	}

	public void setReceiveOfficeId(String receiveOfficeId) {
		this.receiveOfficeId = receiveOfficeId;
	}
	
}