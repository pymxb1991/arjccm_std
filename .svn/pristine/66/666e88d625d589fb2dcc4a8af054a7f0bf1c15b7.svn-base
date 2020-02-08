/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 运行工作流Entity
 * 
 * @author lc
 * @version 2018-04-23
 */
public class PbsFlowwork extends DataEntity<PbsFlowwork> {

	private static final long serialVersionUID = 1L;
	private String sName; // 工作流名称
	private PbsFlowdefinition sFlowid; // 所属工作流模板
	private String sFlowurl; // 工作节点的页面URL
	private Date dtStartdate; // 开始日期
	private Date dtEnddate; // 结束日期
	private String sBinddata; // 绑定的数据类型
	private String sBindkey; // 绑定的主键
	private String sDescription; // 流程节点的描述信息
	private String sSpare01; // 备用内容1
	private String sSpare02; // 备用内容2
	private String sSpare03; // 备用内容3
	private String sSpare04; // 备用内容4
	private User sOperator; // 操作人员
	private PbsPartymem sBindmember; // 党员信息
	private String sBindstat; // 当前流程是否完成
	private String flowType; // 流程的类型

	public PbsFlowwork() {
		super();
	}

	public PbsFlowwork(String id) {
		super(id);
	}

	@Length(min = 0, max = 200, message = "工作流名称长度必须介于 0 和 200 之间")
	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}

	@Length(min = 0, max = 1000, message = "工作节点的页面URL长度必须介于 0 和 1000 之间")
	public String getSFlowurl() {
		return sFlowurl;
	}

	public void setSFlowurl(String sFlowurl) {
		this.sFlowurl = sFlowurl;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDtStartdate() {
		return dtStartdate;
	}

	public void setDtStartdate(Date dtStartdate) {
		this.dtStartdate = dtStartdate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDtEnddate() {
		return dtEnddate;
	}

	public void setDtEnddate(Date dtEnddate) {
		this.dtEnddate = dtEnddate;
	}

	@Length(min = 0, max = 200, message = "绑定的数据类型长度必须介于 0 和 200 之间")
	public String getSBinddata() {
		return sBinddata;
	}

	public void setSBinddata(String sBinddata) {
		this.sBinddata = sBinddata;
	}

	@Length(min = 0, max = 200, message = "绑定的主键长度必须介于 0 和 200 之间")
	public String getSBindkey() {
		return sBindkey;
	}

	public void setSBindkey(String sBindkey) {
		this.sBindkey = sBindkey;
	}

	@Length(min = 0, max = 1000, message = "流程节点的描述信息长度必须介于 0 和 1000 之间")
	public String getSDescription() {
		return sDescription;
	}

	public void setSDescription(String sDescription) {
		this.sDescription = sDescription;
	}

	@Length(min = 0, max = 50, message = "备用内容1长度必须介于 0 和 50 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}

	@Length(min = 0, max = 50, message = "备用内容2长度必须介于 0 和 50 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}

	@Length(min = 0, max = 50, message = "备用内容3长度必须介于 0 和 50 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}

	@Length(min = 0, max = 50, message = "备用内容4长度必须介于 0 和 50 之间")
	public String getSSpare04() {
		return sSpare04;
	}

	public void setSSpare04(String sSpare04) {
		this.sSpare04 = sSpare04;
	}

	public PbsFlowdefinition getsFlowid() {
		return sFlowid;
	}

	public void setsFlowid(PbsFlowdefinition sFlowid) {
		this.sFlowid = sFlowid;
	}

	public User getsOperator() {
		return sOperator;
	}

	public void setsOperator(User sOperator) {
		this.sOperator = sOperator;
	}

	public PbsPartymem getsBindmember() {
		return sBindmember;
	}

	public void setsBindmember(PbsPartymem sBindmember) {
		this.sBindmember = sBindmember;
	}

	public String getsBindstat() {
		return sBindstat;
	}

	public void setsBindstat(String sBindstat) {
		this.sBindstat = sBindstat;
	}

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	
}