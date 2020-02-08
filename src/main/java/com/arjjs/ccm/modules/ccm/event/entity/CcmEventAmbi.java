/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.entity;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;
import com.arjjs.ccm.modules.sys.entity.Area;
import com.arjjs.ccm.modules.sys.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 矛盾纠纷排查化解Entity
 * @author wwh
 * @version 2018-01-30
 */
public class CcmEventAmbi extends DataEntity<CcmEventAmbi> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 矛盾纠纷名称
	private Date sendDate;		// 发送日期
	private String sendAdd;		// 发生地点详址
	private String eventScale;		// 矛盾纠纷规模
	private String eventType;		// 矛盾纠纷类别
	private String invoNum;		// 涉及人数
	private String eventSket;		// 矛盾纠纷简述
	private String involveCom;		// 涉及单位
	private String partCode;		// 主要当事人证件代码
	private String partNum;		// 主要当事人证件号码
	private String partName;		// 主要当事人姓名
	private String partSex;		// 主要当事人性别
	private String partNat;		// 主要当事人民族
	private String partEduBg;		// 主要当事人学历
	private String partType;		// 主要当事人人员类别
	private String partAdd;		// 主要当事人居住详址
	private Date solve;		// 化解时限
	private String solveType;		// 化解方式
	private String solveComp;		// 化解组织
	private String solveName;		// 化解负责人姓名
	private String solveTl;		// 化解负责人联系方式
	private String solveSucc;		// 化解是否成功
	private String solveCase;		// 化解情况
	private Date evaluateDate;		// 考评日期
	private String evaluateAdv;		// 考评意见
	private String areaMap;		// 区域图
	private String areaPoint;		// 中心点
	private String icon;		// 图标
	private String status;		// 处理状态
	private Area area;		// 所属区域
	private Date beginSendDate;		// 开始时间
	private Date endSendDate;		// 结束时间
	private String statusLable;	//用于app接口列表显示
	private String file;	//附件
	private String type;
	private String count;
	private String caseId;	// 关联的事件id
	private User checkUser;		// 拦截器中使用该用户进行权限拦截，App的rest接口使用\
	
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public User getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(User checkUser) {
		this.checkUser = checkUser;
	}

	
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public Date getBeginSendDate() {
		return beginSendDate;
	}

	public void setBeginSendDate(Date beginSendDate) {
		this.beginSendDate = beginSendDate;
	}

	public Date getEndSendDate() {
		return endSendDate;
	}

	public void setEndSendDate(Date endSendDate) {
		this.endSendDate = endSendDate;
	}

	public CcmEventAmbi() {
		super();
	}

	public CcmEventAmbi(String id){
		super(id);
	}

	@Length(min=1, max=100, message="矛盾纠纷名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	@Length(min=0, max=100, message="发生地点详址长度必须介于 0 和 100 之间")
	public String getSendAdd() {
		return sendAdd;
	}

	public void setSendAdd(String sendAdd) {
		this.sendAdd = sendAdd;
	}
	
	@Length(min=1, max=2, message="矛盾纠纷规模长度必须介于 1 和 2 之间")
	public String getEventScale() {
		return eventScale;
	}

	public void setEventScale(String eventScale) {
		this.eventScale = eventScale;
	}
	
	@Length(min=0, max=4, message="矛盾纠纷类别长度必须介于 1 和 4 之间")
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	@Length(min=1, max=6, message="涉及人数长度必须介于 1 和 6 之间")
	public String getInvoNum() {
		return invoNum;
	}

	public void setInvoNum(String invoNum) {
		this.invoNum = invoNum;
	}
	
	public String getEventSket() {
		return eventSket;
	}

	public void setEventSket(String eventSket) {
		this.eventSket = eventSket;
	}
	
	@Length(min=0, max=100, message="涉及单位长度必须介于 0 和 100 之间")
	public String getInvolveCom() {
		return involveCom;
	}

	public void setInvolveCom(String involveCom) {
		this.involveCom = involveCom;
	}
	
	@Length(min=0, max=3, message="主要当事人证件代码长度必须介于 0 和 3 之间")
	public String getPartCode() {
		return partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}
	
	@Length(min=0, max=50, message="主要当事人证件号码长度必须介于 0 和 50 之间")
	public String getPartNum() {
		return partNum;
	}

	public void setPartNum(String partNum) {
		this.partNum = partNum;
	}
	
	@Length(min=0, max=80, message="主要当事人姓名长度必须介于 0 和 80 之间")
	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}
	
	@Length(min=0, max=1, message="主要当事人性别长度必须介于 0 和 1 之间")
	public String getPartSex() {
		return partSex;
	}

	public void setPartSex(String partSex) {
		this.partSex = partSex;
	}
	
	@Length(min=0, max=2, message="主要当事人民族长度必须介于 0 和 2 之间")
	public String getPartNat() {
		return partNat;
	}

	public void setPartNat(String partNat) {
		this.partNat = partNat;
	}
	
	@Length(min=0, max=2, message="主要当事人学历长度必须介于 0 和 2 之间")
	public String getPartEduBg() {
		return partEduBg;
	}

	public void setPartEduBg(String partEduBg) {
		this.partEduBg = partEduBg;
	}
	
	@Length(min=0, max=4, message="主要当事人人员类别长度必须介于 0 和 4 之间")
	public String getPartType() {
		return partType;
	}

	public void setPartType(String partType) {
		this.partType = partType;
	}
	
	@Length(min=0, max=200, message="主要当事人居住详址长度必须介于 0 和 200 之间")
	public String getPartAdd() {
		return partAdd;
	}

	public void setPartAdd(String partAdd) {
		this.partAdd = partAdd;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSolve() {
		return solve;
	}

	public void setSolve(Date solve) {
		this.solve = solve;
	}
	
	@Length(min=0, max=4, message="化解方式长度必须介于 0 和 4 之间")
	public String getSolveType() {
		return solveType;
	}

	public void setSolveType(String solveType) {
		this.solveType = solveType;
	}
	
	@Length(min=0, max=100, message="化解组织长度必须介于 0 和 100 之间")
	public String getSolveComp() {
		return solveComp;
	}

	public void setSolveComp(String solveComp) {
		this.solveComp = solveComp;
	}
	
	@Length(min=0, max=50, message="化解负责人姓名长度必须介于 0 和 50 之间")
	public String getSolveName() {
		return solveName;
	}

	public void setSolveName(String solveName) {
		this.solveName = solveName;
	}
	
	@Length(min=0, max=50, message="化解负责人联系方式长度必须介于 0 和 50 之间")
	public String getSolveTl() {
		return solveTl;
	}

	public void setSolveTl(String solveTl) {
		this.solveTl = solveTl;
	}
	
	@Length(min=1, max=1, message="化解是否成功长度必须介于 1 和 1 之间")
	public String getSolveSucc() {
		return solveSucc;
	}

	public void setSolveSucc(String solveSucc) {
		this.solveSucc = solveSucc;
	}
	
	public String getSolveCase() {
		return solveCase;
	}

	public void setSolveCase(String solveCase) {
		this.solveCase = solveCase;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEvaluateDate() {
		return evaluateDate;
	}

	public void setEvaluateDate(Date evaluateDate) {
		this.evaluateDate = evaluateDate;
	}
	
	public String getEvaluateAdv() {
		return evaluateAdv;
	}

	public void setEvaluateAdv(String evaluateAdv) {
		this.evaluateAdv = evaluateAdv;
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

	@Length(min=0, max=2, message="处理状态必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ExcelField(title="所属社区", align=1,sort=15)
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	public String getStatusLable() {
		return statusLable;
	}
	public void setStatusLable(String statusLable) {
		this.statusLable = statusLable;
	}
	
	@Length(min=0, max=255, message="当前长度必须介于 0 和 255 之间")
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
}