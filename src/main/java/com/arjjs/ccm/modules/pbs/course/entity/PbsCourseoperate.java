/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.course.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 课程操作信息Entity
 * @author lc
 * @version 2018-04-14
 */
public class PbsCourseoperate extends DataEntity<PbsCourseoperate> {
	
	private static final long serialVersionUID = 1L;
	private String sParentid;		// 课程信息
	private String sType;		// 课程类型
	private String sOptype;		// 访问类型
	private String sOpflag;		// 操作标记
	private String sOpresult;		// 操作结果
	private String iTime;		// 操作时长
	private User sUserid;		// 操作用户id
	private PbsPartymem sBindmember;    //操作的党员信息
	private String sContent;		// 评价文字
	private String sSpare01;		// 备用字段
	private String sSpare02;		// 备用字段
	private String sSpare03;		// 备用字段
	private String sSpare04;		// 备用字段
	private String username;        // 用户名称 
	private String coursename;      // 课程名称
	private String oName;//部门名称
	private String beginTime;//开始时间
	private String endTime;//结束时间
	private String oId;
	private String u8Name;
	

	public String getU8Name() {
		return u8Name;
	}

	public void setU8Name(String u8Name) {
		this.u8Name = u8Name;
	}

	public String getoId() {
		return oId;
	}

	public void setoId(String oId) {
		this.oId = oId;
	}


	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

	public PbsCourseoperate() {
		super();
	}

	public PbsCourseoperate(String id){
		super(id);
	}

	@Length(min=1, max=64, message="课程信息长度必须介于 1 和 64 之间")
	public String getSParentid() {
		return sParentid;
	}

	public void setSParentid(String sParentid) {
		this.sParentid = sParentid;
	}
	
	@Length(min=1, max=10, message="课程类型长度必须介于 1 和 10 之间")
	public String getSType() {
		return sType;
	}

	public void setSType(String sType) {
		this.sType = sType;
	}
	
	@Length(min=0, max=20, message="访问类型长度必须介于 0 和 20 之间")
	public String getSOptype() {
		return sOptype;
	}

	public void setSOptype(String sOptype) {
		this.sOptype = sOptype;
	}
	
	@Length(min=0, max=10, message="操作标记长度必须介于 0 和 10 之间")
	public String getSOpflag() {
		return sOpflag;
	}

	public void setSOpflag(String sOpflag) {
		this.sOpflag = sOpflag;
	}
	
	@Length(min=0, max=20, message="操作结果长度必须介于 0 和 20 之间")
	public String getSOpresult() {
		return sOpresult;
	}

	public void setSOpresult(String sOpresult) {
		this.sOpresult = sOpresult;
	}
	
	@Length(min=0, max=10, message="操作时长长度必须介于 0 和 10 之间")
	public String getITime() {
		return iTime;
	}

	public void setITime(String iTime) {
		this.iTime = iTime;
	}
	
	public User getSUserid() {
		return sUserid;
	}

	public void setSUserid(User sUserid) {
		this.sUserid = sUserid;
	}
	
	@Length(min=0, max=2000, message="评价文字长度必须介于 0 和 2000 之间")
	public String getSContent() {
		return sContent;
	}

	public void setSContent(String sContent) {
		this.sContent = sContent;
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

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public PbsPartymem getsBindmember() {
		return sBindmember;
	}

	public void setsBindmember(PbsPartymem sBindmember) {
		this.sBindmember = sBindmember;
	}
	
	
	
}