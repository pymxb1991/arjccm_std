/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.map.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.pbs.activity.entity.PbsActivityrec;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.task.entity.PbsTaskrec;
import com.arjjs.ccm.modules.sys.entity.Office;

/**
 * 地图信息表Entity
 * @author lc
 * @version 2018-04-02
 */
public class PbsGeographical extends DataEntity<PbsGeographical> {
	
	private static final long serialVersionUID = 1L;
	private String sType;		// 地理位置类型
	private String sMaptype;		// 地图类型
	private String sLongitude;		// 地理经度
	private String sLatitude;		// 地理纬度
	private String sDepartname;		// 关联内容名称
	private String sSource;		// 绑定表
	private String sPrimarykey01;		// 关联主键1
	private String sPrimarykey02;		// 关联主键2
	private String sPrimarykey03;		// 关联主键3
	private String sPrimarykey04;		// 关联主键4
	private String sPrimarykey05;		// 关联主键5
	private String sSpare01;		// 备用内容1
	private String sSpare02;		// 备用内容2
	private String sSpare03;		// 备用内容3
	private String sSpare04;		// 备用内容4
	
	private String officeName;      // 部门名称 private 
	private String Peoplesum;      // 部门 包含的数量 
	private String officeRemark;  // 部门描述
	
	private Office sDepartmentid;		// 部门
	private PbsPartymem pbsPartymem;		// 党员
	
	private PbsTaskrec sSuperiorid; // 任务
	private PbsActivityrec sActivityid; // 活动
	private String memDescription;
	
	public PbsGeographical() {
		super();
	}

	public PbsGeographical(String id){
		super(id);
	}

	@Length(min=1, max=30, message="地理位置类型长度必须介于 1 和 30 之间")
	public String getSType() {
		return sType;
	}

	public void setSType(String sType) {
		this.sType = sType;
	}
	
	@Length(min=0, max=60, message="地图类型长度必须介于 0 和 60 之间")
	public String getSMaptype() {
		return sMaptype;
	}

	public void setSMaptype(String sMaptype) {
		this.sMaptype = sMaptype;
	}
	
	@Length(min=1, max=60, message="地理经度长度必须介于 1 和 60 之间")
	public String getSLongitude() {
		return sLongitude;
	}

	public void setSLongitude(String sLongitude) {
		this.sLongitude = sLongitude;
	}
	
	@Length(min=0, max=60, message="地理纬度长度必须介于 0 和 60 之间")
	public String getSLatitude() {
		return sLatitude;
	}

	public void setSLatitude(String sLatitude) {
		this.sLatitude = sLatitude;
	}
	
	@Length(min=0, max=50, message="关联内容名称长度必须介于 0 和 50 之间")
	public String getSDepartname() {
		return sDepartname;
	}

	public void setSDepartname(String sDepartname) {
		this.sDepartname = sDepartname;
	}
	
	@Length(min=0, max=50, message="绑定表长度必须介于 0 和 50 之间")
	public String getSSource() {
		return sSource;
	}

	public void setSSource(String sSource) {
		this.sSource = sSource;
	}
	
	@Length(min=0, max=64, message="关联主键1长度必须介于 0 和 64 之间")
	public String getSPrimarykey01() {
		return sPrimarykey01;
	}

	public void setSPrimarykey01(String sPrimarykey01) {
		this.sPrimarykey01 = sPrimarykey01;
	}
	
	@Length(min=0, max=64, message="关联主键2长度必须介于 0 和 64 之间")
	public String getSPrimarykey02() {
		return sPrimarykey02;
	}

	public void setSPrimarykey02(String sPrimarykey02) {
		this.sPrimarykey02 = sPrimarykey02;
	}
	
	@Length(min=0, max=64, message="关联主键3长度必须介于 0 和 64 之间")
	public String getSPrimarykey03() {
		return sPrimarykey03;
	}

	public void setSPrimarykey03(String sPrimarykey03) {
		this.sPrimarykey03 = sPrimarykey03;
	}
	
	@Length(min=0, max=64, message="关联主键4长度必须介于 0 和 64 之间")
	public String getSPrimarykey04() {
		return sPrimarykey04;
	}

	public void setSPrimarykey04(String sPrimarykey04) {
		this.sPrimarykey04 = sPrimarykey04;
	}
	
	@Length(min=0, max=64, message="关联主键5长度必须介于 0 和 64 之间")
	public String getSPrimarykey05() {
		return sPrimarykey05;
	}

	public void setSPrimarykey05(String sPrimarykey05) {
		this.sPrimarykey05 = sPrimarykey05;
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

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getPeoplesum() {
		return Peoplesum;
	}

	public void setPeoplesum(String peoplesum) {
		Peoplesum = peoplesum;
	}

	public Office getsDepartmentid() {
		return sDepartmentid;
	}

	public void setsDepartmentid(Office sDepartmentid) {
		this.sDepartmentid = sDepartmentid;
	}

	public PbsPartymem getPbsPartymem() {
		return pbsPartymem;
	}

	public void setPbsPartymem(PbsPartymem pbsPartymem) {
		this.pbsPartymem = pbsPartymem;
	}

	public PbsTaskrec getsSuperiorid() {
		return sSuperiorid;
	}

	public void setsSuperiorid(PbsTaskrec sSuperiorid) {
		this.sSuperiorid = sSuperiorid;
	}

	public PbsActivityrec getsActivityid() {
		return sActivityid;
	}

	public void setsActivityid(PbsActivityrec sActivityid) {
		this.sActivityid = sActivityid;
	}

	public String getMemDescription() {
		return memDescription;
	}

	public void setMemDescription(String memDescription) {
		this.memDescription = memDescription;
	}

	public String getOfficeRemark() {
		return officeRemark;
	}

	public void setOfficeRemark(String officeRemark) {
		this.officeRemark = officeRemark;
	}
	
}