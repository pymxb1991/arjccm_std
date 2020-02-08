/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.sys.entity;

import com.arjjs.ccm.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 党员部门扩展信息Entity
 * @author lc
 * @version 2018-04-10
 */
public class PbsDepartmentetc extends DataEntity<PbsDepartmentetc> {
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 部门
	private String sImgurl;		// 部门图片展示URL
	private String sMasterid;		// 负责人
	private String sSpare01;		// 兼职党干部ID（多个）
	private String sSpare02;		// 备用内容2
	private String sSpare03;		// 备用内容3
	private String sSpare04;		// 备用内容4
	private String mastername;      // 部门负责人 
	private String masternames; 	//兼职党干部人
	 
	
	public String getMasternames() {
		return masternames;
	}

	public void setMasternames(String masternames) {
		this.masternames = masternames;
	}

	public PbsDepartmentetc() {
		super();
	}

	public PbsDepartmentetc(String id){
		super(id);
	}

	@NotNull(message="部门不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=0, max=1000, message="部门图片展示URL长度必须介于 0 和 1000 之间")
	public String getSImgurl() {
		return sImgurl;
	}

	public void setSImgurl(String sImgurl) {
		this.sImgurl = sImgurl;
	}
	
	@Length(min=0, max=64, message="负责人长度必须介于 0 和 64 之间")
	public String getSMasterid() {
		return sMasterid;
	}

	public void setSMasterid(String sMasterid) {
		this.sMasterid = sMasterid;
	}
	
	
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

	public String getMastername() {
		return mastername;
	}

	public void setMastername(String mastername) {
		this.mastername = mastername;
	}
	
	
	
}