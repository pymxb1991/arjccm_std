/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 党员登记信息Entity
 * @author lc
 * @version 2018-04-08
 */
public class PbsPartymemreg extends DataEntity<PbsPartymemreg> {
	
	private static final long serialVersionUID = 1L;
	private String sIdtype;		// 证件类别
	private String sIdnum;		// 身份号码
	private String sName;		// 姓名
	private String iSex;		// 性别
	private String iNation;		// 民族信息
	private Date dtBirth;		// 出生日期
	private String sEducation;		// 学历信息
	private String iType;		// 党员类别
	private String sPartybranch;		// 所在党支部全称
	private Date dtAdmission;		// 入党日期
	private Date dtCorrection;		// 转正
	private String sPost;		// 工作岗位
	private String sMobilephone;		// 联系电话-移动电话
	private String sFixedphone;		// 联系电话-固定电话
	private String sHomeaddr;		// 家庭住址（具体到门牌号）
	private String iStat;		// 党籍状态
	private String iOutcontact;		// 是否为失联党员
	private Date dtOutcontact;		// 失联日期
	private String iFloat;		// 是否为流动党员
	private String sFloattopro;		// 外出流向_省
	private String sFloattocity;		// 外出流向_市
	private String sFloattocounty;		// 外出流向_县
	private String sSpare01;		// 备用字段1
	private String sSpare02;		// 备用字段2
	private String sSpare03;		// 备用字段3
	private String sSpare04;		// 备用字段4
	private String sPhoto;		// 党员头像
	private String sRegip;		// 登记IP
	private String Userid;		// 登记的用户ID
	private String sRegstat;		// 审核状态
	private Date beginDtBirth;		// 开始 出生日期
	private Date endDtBirth;		// 结束 出生日期
	private String username;      // 用户名
	private String pbspartymemid; // 党员用户id
	private String userPhoto; //用户头像
	
	
	public PbsPartymemreg() {
		super();
	}

	public PbsPartymemreg(String id){
		super(id);
	}

	@Length(min=1, max=30, message="证件类别长度必须介于 1 和 30 之间")
	public String getSIdtype() {
		return sIdtype;
	}

	public void setSIdtype(String sIdtype) {
		this.sIdtype = sIdtype;
	}
	
	@Length(min=1, max=30, message="身份号码长度必须介于 1 和 30 之间")
	public String getSIdnum() {
		return sIdnum;
	}

	public void setSIdnum(String sIdnum) {
		this.sIdnum = sIdnum;
	}
	
	@Length(min=0, max=200, message="姓名长度必须介于 0 和 200 之间")
	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}
	
	@Length(min=0, max=64, message="性别长度必须介于 0 和 64 之间")
	public String getISex() {
		return iSex;
	}

	public void setISex(String iSex) {
		this.iSex = iSex;
	}
	
	@Length(min=0, max=64, message="民族信息长度必须介于 0 和 64 之间")
	public String getINation() {
		return iNation;
	}

	public void setINation(String iNation) {
		this.iNation = iNation;
	}
	
	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDtBirth() {
		return dtBirth;
	}

	public void setDtBirth(Date dtBirth) {
		this.dtBirth = dtBirth;
	}
	
	@Length(min=0, max=64, message="学历信息长度必须介于 0 和 64 之间")
	public String getSEducation() {
		return sEducation;
	}

	public void setSEducation(String sEducation) {
		this.sEducation = sEducation;
	}
	
	@Length(min=0, max=64, message="党员类别长度必须介于 0 和 64 之间")
	public String getIType() {
		return iType;
	}

	public void setIType(String iType) {
		this.iType = iType;
	}
	
	@Length(min=0, max=64, message="所在党支部全称长度必须介于 0 和 64 之间")
	public String getSPartybranch() {
		return sPartybranch;
	}

	public void setSPartybranch(String sPartybranch) {
		this.sPartybranch = sPartybranch;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDtAdmission() {
		return dtAdmission;
	}

	public void setDtAdmission(Date dtAdmission) {
		this.dtAdmission = dtAdmission;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDtCorrection() {
		return dtCorrection;
	}

	public void setDtCorrection(Date dtCorrection) {
		this.dtCorrection = dtCorrection;
	}
	
	@Length(min=0, max=64, message="工作岗位长度必须介于 0 和 64 之间")
	public String getSPost() {
		return sPost;
	}

	public void setSPost(String sPost) {
		this.sPost = sPost;
	}
	
	@Length(min=0, max=64, message="联系电话-移动电话长度必须介于 0 和 64 之间")
	public String getSMobilephone() {
		return sMobilephone;
	}

	public void setSMobilephone(String sMobilephone) {
		this.sMobilephone = sMobilephone;
	}
	
	@Length(min=0, max=64, message="联系电话-固定电话长度必须介于 0 和 64 之间")
	public String getSFixedphone() {
		return sFixedphone;
	}

	public void setSFixedphone(String sFixedphone) {
		this.sFixedphone = sFixedphone;
	}
	
	@Length(min=0, max=200, message="家庭住址（具体到门牌号）长度必须介于 0 和 200 之间")
	public String getSHomeaddr() {
		return sHomeaddr;
	}

	public void setSHomeaddr(String sHomeaddr) {
		this.sHomeaddr = sHomeaddr;
	}
	
	@Length(min=0, max=64, message="党籍状态长度必须介于 0 和 64 之间")
	public String getIStat() {
		return iStat;
	}

	public void setIStat(String iStat) {
		this.iStat = iStat;
	}
	
	@Length(min=0, max=64, message="是否为失联党员长度必须介于 0 和 64 之间")
	public String getIOutcontact() {
		return iOutcontact;
	}

	public void setIOutcontact(String iOutcontact) {
		this.iOutcontact = iOutcontact;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDtOutcontact() {
		return dtOutcontact;
	}

	public void setDtOutcontact(Date dtOutcontact) {
		this.dtOutcontact = dtOutcontact;
	}
	
	@Length(min=0, max=64, message="是否为流动党员长度必须介于 0 和 64 之间")
	public String getIFloat() {
		return iFloat;
	}

	public void setIFloat(String iFloat) {
		this.iFloat = iFloat;
	}
	
	@Length(min=0, max=64, message="外出流向_省长度必须介于 0 和 64 之间")
	public String getSFloattopro() {
		return sFloattopro;
	}

	public void setSFloattopro(String sFloattopro) {
		this.sFloattopro = sFloattopro;
	}
	
	@Length(min=0, max=64, message="外出流向_市长度必须介于 0 和 64 之间")
	public String getSFloattocity() {
		return sFloattocity;
	}

	public void setSFloattocity(String sFloattocity) {
		this.sFloattocity = sFloattocity;
	}
	
	@Length(min=0, max=64, message="外出流向_县长度必须介于 0 和 64 之间")
	public String getSFloattocounty() {
		return sFloattocounty;
	}

	public void setSFloattocounty(String sFloattocounty) {
		this.sFloattocounty = sFloattocounty;
	}
	
	@Length(min=0, max=64, message="备用字段1长度必须介于 0 和 64 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}
	
	@Length(min=0, max=64, message="备用字段2长度必须介于 0 和 64 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}
	
	@Length(min=0, max=64, message="备用字段3长度必须介于 0 和 64 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}
	
	@Length(min=0, max=64, message="备用字段4长度必须介于 0 和 64 之间")
	public String getSSpare04() {
		return sSpare04;
	}

	public void setSSpare04(String sSpare04) {
		this.sSpare04 = sSpare04;
	}
	
	@Length(min=0, max=1000, message="党员头像长度必须介于 0 和 1000 之间")
	public String getSPhoto() {
		return sPhoto;
	}

	public void setSPhoto(String sPhoto) {
		this.sPhoto = sPhoto;
	}
	
	@Length(min=0, max=50, message="登记IP长度必须介于 0 和 50 之间")
	public String getSRegip() {
		return sRegip;
	}

	public void setSRegip(String sRegip) {
		this.sRegip = sRegip;
	}
	
	@Length(min=0, max=64, message="登记的用户ID长度必须介于 0 和 64 之间")
	public String getUserid() {
		return Userid;
	}

	public void setUserid(String Userid) {
		this.Userid = Userid;
	}
	
	@Length(min=0, max=10, message="审核状态长度必须介于 0 和 10 之间")
	public String getSRegstat() {
		return sRegstat;
	}

	public void setSRegstat(String sRegstat) {
		this.sRegstat = sRegstat;
	}
	
	public Date getBeginDtBirth() {
		return beginDtBirth;
	}

	public void setBeginDtBirth(Date beginDtBirth) {
		this.beginDtBirth = beginDtBirth;
	}
	
	public Date getEndDtBirth() {
		return endDtBirth;
	}

	public void setEndDtBirth(Date endDtBirth) {
		this.endDtBirth = endDtBirth;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPbspartymemid() {
		return pbspartymemid;
	}

	public void setPbspartymemid(String pbspartymemid) {
		this.pbspartymemid = pbspartymemid;
	}
	
	
}