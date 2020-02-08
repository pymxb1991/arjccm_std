/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.sys.entity.Office;

/**
 * 流程审核人信息Entity
 * @author lc
 * @version 2018-04-20
 */
public class PbsFnodeapprover extends DataEntity<PbsFnodeapprover> {
	
	private static final long serialVersionUID = 1L;
	private PbsFlowdefinition sFlowid;		// 流程定义的id
	private PbsFlownode sFlownodeid;		// 流程节点id
	private Office sDepartmentid;		// 部门
	private PbsPartymem sApprover;		// 党员编号
	private String sDescription;		// 流程节点的描述信息
	private String sSpare01;		// 备用内容1
	private String sSpare02;		// 备用内容2
	private String sSpare03;		// 备用内容3
	private String sSpare04;		// 备用内容4
	
	public PbsFnodeapprover() {
		super();
	}

	public PbsFnodeapprover(String id){
		super(id);
	}

	@Length(min=0, max=1000, message="流程节点的描述信息长度必须介于 0 和 1000 之间")
	public String getSDescription() {
		return sDescription;
	}

	public void setSDescription(String sDescription) {
		this.sDescription = sDescription;
	}
	
	@Length(min=0, max=50, message="备用内容1长度必须介于 0 和 50 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}
	
	@Length(min=0, max=50, message="备用内容2长度必须介于 0 和 50 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}
	
	@Length(min=0, max=50, message="备用内容3长度必须介于 0 和 50 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}
	
	@Length(min=0, max=50, message="备用内容4长度必须介于 0 和 50 之间")
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

	public PbsFlownode getsFlownodeid() {
		return sFlownodeid;
	}

	public void setsFlownodeid(PbsFlownode sFlownodeid) {
		this.sFlownodeid = sFlownodeid;
	}

	public Office getsDepartmentid() {
		return sDepartmentid;
	}

	public void setsDepartmentid(Office sDepartmentid) {
		this.sDepartmentid = sDepartmentid;
	}

	public PbsPartymem getsApprover() {
		return sApprover;
	}

	public void setsApprover(PbsPartymem sApprover) {
		this.sApprover = sApprover;
	}
	
	
}