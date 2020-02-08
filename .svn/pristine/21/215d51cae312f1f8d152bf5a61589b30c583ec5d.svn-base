/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.apply.entity;

 
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.Collections3;
import com.arjjs.ccm.modules.pbs.flow.entity.PbsFlowdefinition;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.arjjs.ccm.modules.sys.entity.User;
import com.google.common.collect.Lists;

/**
 * 申请记录Entity
 * @author lc
 * @version 2018-04-25
 */
public class PbsApplyrec extends DataEntity<PbsApplyrec> {
	
	private static final long serialVersionUID = 1L;
	private PbsFlowdefinition sType;		// 申请类型
	private String sResume;		// 申请简述信息
	private String sContent;		// 申请内容
	private Office sPartment;		// 申请的部门编号
	private String sApprover;		// 审核人
	private User sOperator;		// 申请操作人员
	private PbsPartymem sBindmember;		// 申请绑定人员
	private String sStatus;		// 记录状态
	private String sSpare01;		// 备用字段
	private String sSpare02;		// 备用字段
	private String sSpare03;		// 备用字段
	private String sSpare04;		// 备用字段
	private String sApproverName;		// 审核人的名称
	private String flowtype; // 申请定义
	private String sFileurl; //文件路径
	private List<String> sFileurls; //文件路径的集合
	
	public PbsApplyrec() {
		super();
	}

	public PbsApplyrec(String id){
		super(id);
	}

	public PbsFlowdefinition getsType() {
		return sType;
	}

	public void setsType(PbsFlowdefinition sType) {
		this.sType = sType;
	}

	@NotEmpty(message = "简述信息不能为空")
	@Length(min=0, max=500, message="申请简述信息长度必须介于 0 和 500 之间")
	public String getSResume() {
		return sResume;
	}

	public void setSResume(String sResume) {
		this.sResume = sResume;
	}
	
	@Length(min=0, max=2000, message="申请内容长度必须介于 0 和 2000 之间")
	public String getSContent() {
		return sContent;
	}

	public void setSContent(String sContent) {
		this.sContent = sContent;
	}
	
	
	@Length(min=0, max=100, message="记录状态长度必须介于 0 和 100 之间")
	public String getSStatus() {
		return sStatus;
	}

	public void setSStatus(String sStatus) {
		this.sStatus = sStatus;
	}
	
	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}
	
	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}
	
	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}
	
	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSSpare04() {
		return sSpare04;
	}

	public void setSSpare04(String sSpare04) {
		this.sSpare04 = sSpare04;
	}


	public User getsOperator() {
		return sOperator;
	}

	public void setsOperator(User sOperator) {
		this.sOperator = sOperator;
	}


	public Office getsPartment() {
		return sPartment;
	}

	public void setsPartment(Office sPartment) {
		this.sPartment = sPartment;
	}

	public String getsApprover() {
		return sApprover;
	}

	public void setsApprover(String sApprover) {
		this.sApprover = sApprover;
	}

	public PbsPartymem getsBindmember() {
		return sBindmember;
	}

	public void setsBindmember(PbsPartymem sBindmember) {
		this.sBindmember = sBindmember;
	}

	public String getsApproverName() {
		return sApproverName;
	}

	public void setsApproverName(String sApproverName) {
		this.sApproverName = sApproverName;
	}


	private List<PbsPartymem> partymemList = Lists.newArrayList();
	public List<PbsPartymem> getPartymemList() {
		return partymemList;
	}
	public void setPartymemList(List<PbsPartymem> partymemList) {
		this.partymemList = partymemList;
	}
	// 获取 Id
	public String getPartymemListIds() {
		return Collections3.extractToString(partymemList, "id", ",") ;
	}

	public String getFlowtype() {
		return flowtype;
	}

	public void setFlowtype(String flowtype) {
		this.flowtype = flowtype;
	}

	public String getsFileurl() {
		return sFileurl;
	}

	public void setsFileurl(String sFileurl) {
		this.sFileurl = sFileurl;
	}

	public List<String> getsFileurls() {
		return sFileurls;
	}

	public void setsFileurls(List<String> sFileurls) {
		this.sFileurls = sFileurls;
	}
	
}