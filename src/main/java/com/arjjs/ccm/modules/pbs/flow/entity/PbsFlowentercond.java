/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.flow.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 进入节点条件信息Entity
 * @author lc
 * @version 2018-04-20
 */
public class PbsFlowentercond extends DataEntity<PbsFlowentercond> {
	
	private static final long serialVersionUID = 1L;
	private PbsFlowdefinition sFlowid;		// 所属流程
	private PbsFlownode sFlownodeid;		// 所属工作流程的定义ID
	private String sCondtype;		// 状态类型
	private String sOrder;		// 顺序编号
	private String sCollection;		// 操作对象名称
	private String sProperty;		// 操作对象属性名
	private String sCondsign;		// 条件符号
	private String sCondval;		// 条件判断值
	private String sConnection;		// 与其他条件的连接关系
	private String sOpearatecontent;		// 操作内容记录
	private String sDescription;		// 流程节点的描述信息
	private String sSpare01;		// 备用内容1
	private String sSpare02;		// 备用内容2
	private String sSpare03;		// 备用内容3
	private String sSpare04;		// 备用内容4
	
	public PbsFlowentercond() {
		super();
	}

	public PbsFlowentercond(String id){
		super(id);
	}

	public String getSCondtype() {
		return sCondtype;
	}
	
	public void setSCondtype(String sCondtype) {
		this.sCondtype = sCondtype;
	}
	
	@Length(min=0, max=64, message="顺序编号长度必须介于 0 和 64 之间")
	public String getSOrder() {
		return sOrder;
	}

	public void setSOrder(String sOrder) {
		this.sOrder = sOrder;
	}
	
	@Length(min=0, max=100, message="操作对象名称长度必须介于 0 和 100 之间")
	public String getSCollection() {
		return sCollection;
	}

	public void setSCollection(String sCollection) {
		this.sCollection = sCollection;
	}
	
	@Length(min=0, max=100, message="操作对象属性名长度必须介于 0 和 100 之间")
	public String getSProperty() {
		return sProperty;
	}

	public void setSProperty(String sProperty) {
		this.sProperty = sProperty;
	}
	
	@Length(min=0, max=10, message="条件符号长度必须介于 0 和 10 之间")
	public String getSCondsign() {
		return sCondsign;
	}

	public void setSCondsign(String sCondsign) {
		this.sCondsign = sCondsign;
	}
	
	@Length(min=0, max=100, message="条件判断值长度必须介于 0 和 100 之间")
	public String getSCondval() {
		return sCondval;
	}

	public void setSCondval(String sCondval) {
		this.sCondval = sCondval;
	}
	
	@Length(min=0, max=20, message="与其他条件的连接关系长度必须介于 0 和 20 之间")
	public String getSConnection() {
		return sConnection;
	}

	public void setSConnection(String sConnection) {
		this.sConnection = sConnection;
	}
	
	@Length(min=0, max=1000, message="操作内容记录长度必须介于 0 和 1000 之间")
	public String getSOpearatecontent() {
		return sOpearatecontent;
	}

	public void setSOpearatecontent(String sOpearatecontent) {
		this.sOpearatecontent = sOpearatecontent;
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



	
	
	
}