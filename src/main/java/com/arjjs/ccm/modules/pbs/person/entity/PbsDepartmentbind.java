/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 党员部门关系Entity
 * @author lc
 * @version 2018-04-03
 */
public class PbsDepartmentbind extends DataEntity<PbsDepartmentbind> {
	
	private static final long serialVersionUID = 1L;
	private String sDepartmentid;		// 党支部id
	private String sPartymemid;		// 党员的主键信息
	private PbsPositionlevel sPost;		// 担任职务
	private String sSpare01;		// 备用字段
	private String sSpare02;		// 备用字段
	private String sSpare03;		// 备用字段
	private String sSpare04;		// 备用字段
	private Date dtPosttime;		// 任职开始日期
	private PbsPositionlevel sPosttitle;		// 头衔
	private String sDepartmentidSelect; // 搜索框使用的 查询支部id
	private String pageTurn;       // 页面 跳转链接
	private String partymemname;      //党员名称
	private String officename;      // 部门名称
	private PbsPartymem partymem;   // 党员对象
	private String officeparentid; // 查询用部门父ID
	private String partymemtype; // 查询党员类型
	
	
	public PbsDepartmentbind() {
		super();
	}

	public PbsDepartmentbind(String id){
		super(id);
	}

	@Length(min=1, max=64, message="党支部id长度必须介于 1 和 64 之间")
	public String getSDepartmentid() {
		return sDepartmentid;
	}

	public void setSDepartmentid(String sDepartmentid) {
		this.sDepartmentid = sDepartmentid;
	}
	
	@Length(min=1, max=64, message="党员的主键信息长度必须介于 1 和 64 之间")
	public String getSPartymemid() {
		return sPartymemid;
	}

	public void setSPartymemid(String sPartymemid) {
		this.sPartymemid = sPartymemid;
	}
	
	public PbsPositionlevel getSPost() {
		return sPost;
	}

	public void setSPost(PbsPositionlevel sPost) {
		this.sPost = sPost;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDtPosttime() {
		return dtPosttime;
	}

	public void setDtPosttime(Date dtPosttime) {
		this.dtPosttime = dtPosttime;
	}
	
	public PbsPositionlevel getSPosttitle() {
		return sPosttitle;
	}

	public void setSPosttitle(PbsPositionlevel sPosttitle) {
		this.sPosttitle = sPosttitle;
	}

	public String getPageTurn() {
		return pageTurn;
	}

	public void setPageTurn(String pageTurn) {
		this.pageTurn = pageTurn;
	}

	public String getPartymemname() {
		return partymemname;
	}

	public void setPartymemname(String partymemname) {
		this.partymemname = partymemname;
	}

	public String getOfficename() {
		return officename;
	}

	public void setOfficename(String officename) {
		this.officename = officename;
	}

	public PbsPartymem getPartymem() {
		return partymem;
	}

	public void setPartymem(PbsPartymem partymem) {
		this.partymem = partymem;
	}

	public String getOfficeparentid() {
		return officeparentid;
	}

	public void setOfficeparentid(String officeparentid) {
		this.officeparentid = officeparentid;
	}

	public String getsDepartmentidSelect() {
		return sDepartmentidSelect;
	}

	public void setsDepartmentidSelect(String sDepartmentidSelect) {
		this.sDepartmentidSelect = sDepartmentidSelect;
	}

	public String getPartymemtype() {
		return partymemtype;
	}

	public void setPartymemtype(String partymemtype) {
		this.partymemtype = partymemtype;
	}
	
}
