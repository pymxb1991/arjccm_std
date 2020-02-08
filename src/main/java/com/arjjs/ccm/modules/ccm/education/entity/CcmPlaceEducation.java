/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.education.entity;

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
 * 文化教育场所Entity
 * 
 * @author ljd
 * @version 2019-04-26
 */
public class CcmPlaceEducation extends DataEntity<CcmPlaceEducation> {

	private static final long serialVersionUID = 1L;
	private CcmBasePlace ccmBasePlace;
	private String type; // 场所类型（01学校02研究所03美术馆或博物馆）
	private String schoolCode; // 学校标识码
	private String schoolNet; // 学校官网
	private String schoolRank; // 学校级别
	private String schoolType; // 办学类型
	private String schoolPhone; // 办公电话
	private String faxNumber; // 传真号
	private String managerName; // 行政管理员名字
	private String managerPhoneNumber; // 行政管理员联系方式
	private String graduateSchoolPhone; // 研究院办公电话
	private String workerNumber; // 工作人员数量
	private String graduateSchoolNature; // 研究院性质
	private String museumName; // 博物馆或美术馆名字
	private String isFree; // 是否免费（01 免费 02收费 ）
	private String managerNumber; // 管理人员数量
	private String basePlaceId; // 场所id
	private Date beginCreateDate; // 开始 创建时间
	private Date endCreateDate; // 结束 创建时间
	
	//基础场所属性
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

	public CcmPlaceEducation() {
		super();
	}

	public CcmPlaceEducation(String id) {
		super(id);
	}

	@Length(min = 1, max = 2, message = "场所类型（01学校02研究所03美术馆或博物馆）长度必须介于 1 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min = 0, max = 255, message = "学校标识码长度必须介于 0 和 255 之间")
	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	@Length(min = 0, max = 255, message = "学校官网长度必须介于 0 和 255 之间")
	public String getSchoolNet() {
		return schoolNet;
	}

	public void setSchoolNet(String schoolNet) {
		this.schoolNet = schoolNet;
	}

	@Length(min = 0, max = 255, message = "学校级别长度必须介于 0 和 255 之间")
	public String getSchoolRank() {
		return schoolRank;
	}

	public void setSchoolRank(String schoolRank) {
		this.schoolRank = schoolRank;
	}

	@Length(min = 0, max = 2, message = "办学类型长度必须介于 0 和 2 之间")
	public String getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(String schoolType) {
		this.schoolType = schoolType;
	}

	@Length(min = 0, max = 255, message = "办公电话长度必须介于 0 和 255 之间")
	public String getSchoolPhone() {
		return schoolPhone;
	}

	public void setSchoolPhone(String schoolPhone) {
		this.schoolPhone = schoolPhone;
	}

	@Length(min = 0, max = 11, message = "传真号长度必须介于 0 和 11 之间")
	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	@Length(min = 0, max = 255, message = "行政管理员名字长度必须介于 0 和 255 之间")
	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	@Length(min = 0, max = 255, message = "行政管理员联系方式长度必须介于 0 和 255 之间")
	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}

	public void setManagerPhoneNumber(String managerPhoneNumber) {
		this.managerPhoneNumber = managerPhoneNumber;
	}

	@Length(min = 0, max = 255, message = "研究院办公电话长度必须介于 0 和 255 之间")
	public String getGraduateSchoolPhone() {
		return graduateSchoolPhone;
	}

	public void setGraduateSchoolPhone(String graduateSchoolPhone) {
		this.graduateSchoolPhone = graduateSchoolPhone;
	}

	@Length(min = 0, max = 11, message = "工作人员数量长度必须介于 0 和 11 之间")
	public String getWorkerNumber() {
		return workerNumber;
	}

	public void setWorkerNumber(String workerNumber) {
		this.workerNumber = workerNumber;
	}

	@Length(min = 0, max = 255, message = "研究院性质长度必须介于 0 和 255 之间")
	public String getGraduateSchoolNature() {
		return graduateSchoolNature;
	}

	public void setGraduateSchoolNature(String graduateSchoolNature) {
		this.graduateSchoolNature = graduateSchoolNature;
	}

	@Length(min = 0, max = 255, message = "博物馆或美术馆名字长度必须介于 0 和 255 之间")
	public String getMuseumName() {
		return museumName;
	}

	public void setMuseumName(String museumName) {
		this.museumName = museumName;
	}

	@Length(min = 0, max = 255, message = "是否免费（01 免费  02收费  ）长度必须介于 0 和 255 之间")
	public String getIsFree() {
		return isFree;
	}

	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}

	@Length(min = 0, max = 100, message = "管理人员数量长度必须介于 0 和 100 之间")
	public String getManagerNumber() {
		return managerNumber;
	}

	public void setManagerNumber(String managerNumber) {
		this.managerNumber = managerNumber;
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