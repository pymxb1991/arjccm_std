/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.activity.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.Collections3;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;

/**
 * 活动信息Entity
 * 
 * @author lc
 * @version 2018-05-14
 */
public class PbsActivityrec extends DataEntity<PbsActivityrec> {

	private static final long serialVersionUID = 1L;
	private String sTitle; // 活动标题
	private PbsActivitytype sType ; // 活动类型
	private String sContent; // 活动内容
	private PbsPartymem sMastermem ; // 活动负责人
	private String sAttendorgs; // 参加活动组织名称列表
	private String sAttendants; // 参加人员姓名列表
	private Date dtStarttime; // 活动开始时间
	private Date dtEndtime; // 活动结束时间
	private String sPlace; // 活动地点
	private String sStat; // 审核状态
	private String sDescription; // 描述信息
	private String sUrl; // 参考信息
	private String sFlag; // 评分标记
	private String sFile01; // 附件1
	private String sFile02; // 附件2
	private String sFile03; // 附件3
	private String sFile04; // 附件4
	private String sEnrolment; // 报名人数
	private String sRegistnum; // 登记人数
	private String sActualnum; // 实到人数
	private String sSpare01; // 备用字段
	private String sSpare02; // 备用字段
	private String sSpare03; // 备用字段
	private String sSpare04; // 备用字段
	private boolean newflag;  // 是否最近一周
	private String sGroupType; //自定义类别
	
	private PbsPartymem sAcceptorid;		// 名称

	private List<PbsActinotifications> pbsActinotificationList = Lists.newArrayList();

	public PbsActivityrec() {
		super();
	}

	public PbsActivityrec(String id) {
		super(id);
	}

	@Length(min = 0, max = 200, message = "活动标题长度必须介于 0 和 200 之间")
	public String getSTitle() {
		return sTitle;
	}

	public void setSTitle(String sTitle) {
		this.sTitle = sTitle;
	}

	public String getSContent() {
		return sContent;
	}

	public void setSContent(String sContent) {
		this.sContent = sContent;
	}

	public PbsPartymem getSMastermem() {
		return sMastermem;
	}

	public void setSMastermem(PbsPartymem sMastermem) {
		this.sMastermem = sMastermem;
	}

	@Length(min = 0, max = 1000, message = "参加活动组织名称列表长度必须介于 0 和 1000 之间")
	public String getSAttendorgs() {
		return sAttendorgs;
	}

	public void setSAttendorgs(String sAttendorgs) {
		this.sAttendorgs = sAttendorgs;
	}

	@Length(min = 0, max = 1000, message = "参加人员姓名列表长度必须介于 0 和 1000 之间")
	public String getSAttendants() {
		return sAttendants;
	}

	public void setSAttendants(String sAttendants) {
		this.sAttendants = sAttendants;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDtStarttime() {
		return dtStarttime;
	}

	public void setDtStarttime(Date dtStarttime) {
		this.dtStarttime = dtStarttime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDtEndtime() {
		return dtEndtime;
	}

	public void setDtEndtime(Date dtEndtime) {
		this.dtEndtime = dtEndtime;
	}

	@Length(min = 0, max = 500, message = "活动地点长度必须介于 0 和 500 之间")
	public String getSPlace() {
		return sPlace;
	}

	public void setSPlace(String sPlace) {
		this.sPlace = sPlace;
	}

	@Length(min = 0, max = 10, message = "审核状态长度必须介于 0 和 10 之间")
	public String getSStat() {
		return sStat;
	}

	public void setSStat(String sStat) {
		this.sStat = sStat;
	}

	@Length(min = 0, max = 1000, message = "描述信息长度必须介于 0 和 1000 之间")
	public String getSDescription() {
		return sDescription;
	}

	public void setSDescription(String sDescription) {
		this.sDescription = sDescription;
	}

	@Length(min = 0, max = 500, message = "参考信息长度必须介于 0 和 500 之间")
	public String getSUrl() {
		return sUrl;
	}

	public void setSUrl(String sUrl) {
		this.sUrl = sUrl;
	}

	@Length(min = 0, max = 10, message = "评分标记长度必须介于 0 和 10 之间")
	public String getSFlag() {
		return sFlag;
	}

	public void setSFlag(String sFlag) {
		this.sFlag = sFlag;
	}

	@Length(min = 0, max = 500, message = "附件1长度必须介于 0 和 500 之间")
	public String getSFile01() {
		return sFile01;
	}

	public void setSFile01(String sFile01) {
		this.sFile01 = sFile01;
	}

	@Length(min = 0, max = 500, message = "附件2长度必须介于 0 和 500 之间")
	public String getSFile02() {
		return sFile02;
	}

	public void setSFile02(String sFile02) {
		this.sFile02 = sFile02;
	}

	@Length(min = 0, max = 500, message = "附件3长度必须介于 0 和 500 之间")
	public String getSFile03() {
		return sFile03;
	}

	public void setSFile03(String sFile03) {
		this.sFile03 = sFile03;
	}

	@Length(min = 0, max = 500, message = "附件4长度必须介于 0 和 500 之间")
	public String getSFile04() {
		return sFile04;
	}

	public void setSFile04(String sFile04) {
		this.sFile04 = sFile04;
	}

	@Length(min = 0, max = 10, message = "报名人数长度必须介于 0 和 10 之间")
	public String getSEnrolment() {
		return sEnrolment;
	}

	public void setSEnrolment(String sEnrolment) {
		this.sEnrolment = sEnrolment;
	}

	@Length(min = 0, max = 10, message = "登记人数长度必须介于 0 和 10 之间")
	public String getSRegistnum() {
		return sRegistnum;
	}

	public void setSRegistnum(String sRegistnum) {
		this.sRegistnum = sRegistnum;
	}

	@Length(min = 0, max = 10, message = "实到人数长度必须介于 0 和 10 之间")
	public String getSActualnum() {
		return sActualnum;
	}

	public void setSActualnum(String sActualnum) {
		this.sActualnum = sActualnum;
	}

	@Length(min = 0, max = 64, message = "备用字段长度必须介于 0 和 64 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}

	@Length(min = 0, max = 64, message = "备用字段长度必须介于 0 和 64 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}

	@Length(min = 0, max = 64, message = "备用字段长度必须介于 0 和 64 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}

	@Length(min = 0, max = 64, message = "备用字段长度必须介于 0 和 64 之间")
	public String getSSpare04() {
		return sSpare04;
	}

	public void setSSpare04(String sSpare04) {
		this.sSpare04 = sSpare04;
	}

	public PbsActivitytype getsType() {
		return sType;
	}

	public void setsType(PbsActivitytype sType) {
		this.sType = sType;
	}
	
	public String getsGroupType() {
		return sGroupType;
	}

	public void setsGroupType(String sGroupType) {
		this.sGroupType = sGroupType;
	}

	public List<PbsActinotifications> getPbsActinotificationList() {
		return pbsActinotificationList;
	}

	public void setPbsActinotificationList(List<PbsActinotifications> pbsActinotificationList) {
		this.pbsActinotificationList = pbsActinotificationList;
	}

	public String getPbsActinotificationIds() {
		return Collections3.extractToString(pbsActinotificationList, "sAcceptorid.id", ",");
	}

	public void setPbsActinotificationIds(String ActinotificationIds) {
		this.pbsActinotificationList = Lists.newArrayList();
		String[] s= StringUtils.split(ActinotificationIds, ",");
		List<String> list = Arrays.asList(s);
		Set<String> set = new HashSet<String>(list);
		String [] rid=(String [])set.toArray(new String[0]);
		for (String id : rid) {
			PbsActinotifications entity = new PbsActinotifications();
			entity.setsActivityid(this);
			entity.setsType("1");
			entity.setSContent(this.getSContent());
			entity.setSStat("0");
			entity.setSUrl(this.sUrl);
			entity.setSDescription(this.sDescription);
			entity.setsAcceptorid(new PbsPartymem(id));
			// 插入前补完
			entity.preInsert();
			this.pbsActinotificationList.add(entity);
		}
	}

	public PbsPartymem getsAcceptorid() {
		return sAcceptorid;
	}

	public void setsAcceptorid(PbsPartymem sAcceptorid) {
		this.sAcceptorid = sAcceptorid;
	}

	public boolean isNewflag() {
		return newflag;
	}

	public void setNewflag(boolean newflag) {
		this.newflag = newflag;
	}
	
}