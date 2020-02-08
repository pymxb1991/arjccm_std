/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 党员表关系Entity
 * @author lc
 * @version 2018-04-03
 */
public class PbsPartymembind extends DataEntity<PbsPartymembind> {
	
	private static final long serialVersionUID = 1L;
	private String sPartymemid;		// 党员的主键信息
	private String sType;		// 绑定类型：默认为 USER 用户绑定
	private String sName;		// 绑定名称
	private String sSource;		// 绑定表单
	private String sPrimarykey01;		// 关联主键01
	private String sPrimarykey02;		// 关联主键02
	private String sPrimarykey03;		// 关联主键03
	private String sPrimarykey04;		// 关联主键04
	private String sPrimarykey05;		// 关联主键05
	private String sSpare01;		// 备用字段
	private String sSpare02;		// 备用字段
	private String sSpare03;		// 备用字段
	private String sSpare04;		// 备用字段
	
	private String pageTurn;       // 页面 跳转链接
	private String username;       // 用户名称  查询
	private String partymemname;      //党员名称 查询 
	
	private PbsPartymem partymem;   // 党员对象 
	
	public PbsPartymem getPartymem() {
		return partymem;
	}

	public void setPartymem(PbsPartymem partymem) {
		this.partymem = partymem;
	}

	public PbsPartymembind() {
		super();
	}

	public PbsPartymembind(String id){
		super(id);
	}

	@Length(min=0, max=64, message="党员的主键信息长度必须介于 0 和 64 之间")
	public String getSPartymemid() {
		return sPartymemid;
	}

	public void setSPartymemid(String sPartymemid) {
		this.sPartymemid = sPartymemid;
	}
	
	@Length(min=0, max=30, message="绑定类型：默认为 USER 用户绑定长度必须介于 0 和 30 之间")
	public String getSType() {
		return sType;
	}

	public void setSType(String sType) {
		this.sType = sType;
	}
	
	@Length(min=0, max=50, message="绑定名称长度必须介于 0 和 50 之间")
	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}
	
	@Length(min=0, max=50, message="绑定表单长度必须介于 0 和 50 之间")
	public String getSSource() {
		return sSource;
	}

	public void setSSource(String sSource) {
		this.sSource = sSource;
	}
	
	@Length(min=0, max=64, message="关联主键01长度必须介于 0 和 64 之间")
	public String getSPrimarykey01() {
		return sPrimarykey01;
	}

	public void setSPrimarykey01(String sPrimarykey01) {
		this.sPrimarykey01 = sPrimarykey01;
	}
	
	@Length(min=0, max=64, message="关联主键02长度必须介于 0 和 64 之间")
	public String getSPrimarykey02() {
		return sPrimarykey02;
	}

	public void setSPrimarykey02(String sPrimarykey02) {
		this.sPrimarykey02 = sPrimarykey02;
	}
	
	@Length(min=0, max=64, message="关联主键03长度必须介于 0 和 64 之间")
	public String getSPrimarykey03() {
		return sPrimarykey03;
	}

	public void setSPrimarykey03(String sPrimarykey03) {
		this.sPrimarykey03 = sPrimarykey03;
	}
	
	@Length(min=0, max=64, message="关联主键04长度必须介于 0 和 64 之间")
	public String getSPrimarykey04() {
		return sPrimarykey04;
	}

	public void setSPrimarykey04(String sPrimarykey04) {
		this.sPrimarykey04 = sPrimarykey04;
	}
	
	@Length(min=0, max=64, message="关联主键05长度必须介于 0 和 64 之间")
	public String getSPrimarykey05() {
		return sPrimarykey05;
	}

	public void setSPrimarykey05(String sPrimarykey05) {
		this.sPrimarykey05 = sPrimarykey05;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPartymemname() {
		return partymemname;
	}

	public void setPartymemname(String partymemname) {
		this.partymemname = partymemname;
	}

	public String getPageTurn() {
		return pageTurn;
	}

	public void setPageTurn(String pageTurn) {
		this.pageTurn = pageTurn;
	}
	
	
}