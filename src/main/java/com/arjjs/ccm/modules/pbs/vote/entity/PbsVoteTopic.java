/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.vote.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.Collections3;

/**
 * 投票主题信息Entity
 * @author lc
 * @version 2018-03-27
 */
public class PbsVoteTopic extends DataEntity<PbsVoteTopic> {
	
	private static final long serialVersionUID = 1L;
	private String sName;		// 标题名称
	private String sBody;		// 内容
	private String sClose;		// 是否关闭
	private Date dtStart;		// 投票开始时间
	private Date dtEnd;		// 投票结束时间
	private String sIsautoclose;		// 是否开启自动关闭投票
	private String sSpare01;		// 其他内容1
	private String sSpare02;		// 其他内容2
	private String sSpare03;		// 其他内容3
	private String sSpare04;		// 其他内容4
	private String sSum;   // 投票参与总数
	private List<PbsVoteUser> PbsVoteuserList  = Lists.newArrayList();   // 投票用户
	private boolean voteflag;           // 该用户是否已经 投票
	private String sBelongfunc;      // 当前服务类型
	private String sStat; // 是否发布
	
	public PbsVoteTopic() {
		super();
	}

	public PbsVoteTopic(String id){
		super(id);
	}

	@Length(min=0, max=200, message="标题名称长度必须介于 0 和 200 之间")
	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}
	
	public String getSBody() {
		return sBody;
	}

	public void setSBody(String sBody) {
		this.sBody = sBody;
	}
	
	@Length(min=0, max=20, message="是否关闭长度必须介于 0 和 20 之间")
	public String getSClose() {
		return sClose;
	}

	public void setSClose(String sClose) {
		this.sClose = sClose;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDtStart() {
		return dtStart;
	}

	public void setDtStart(Date dtStart) {
		this.dtStart = dtStart;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDtEnd() {
		return dtEnd;
	}

	public void setDtEnd(Date dtEnd) {
		this.dtEnd = dtEnd;
	}
	
	@Length(min=0, max=20, message="是否开启自动关闭投票长度必须介于 0 和 20 之间")
	public String getSIsautoclose() {
		return sIsautoclose;
	}

	public void setSIsautoclose(String sIsautoclose) {
		this.sIsautoclose = sIsautoclose;
	}
	
	@Length(min=0, max=50, message="其他内容1长度必须介于 0 和 50 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}
	
	@Length(min=0, max=50, message="其他内容2长度必须介于 0 和 50 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}
	
	@Length(min=0, max=50, message="其他内容3长度必须介于 0 和 50 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}
	
	@Length(min=0, max=50, message="其他内容4长度必须介于 0 和 50 之间")
	public String getSSpare04() {
		return sSpare04;
	}

	public void setSSpare04(String sSpare04) {
		this.sSpare04 = sSpare04;
	}

	public String getsSum() {
		return sSum;
	}

	public void setsSum(String sSum) {
		this.sSum = sSum;
	}

	public List<PbsVoteUser> getPbsVoteuserList() {
		return PbsVoteuserList;
	}

	public void setPbsVoteuserList(List<PbsVoteUser> pbsVoteuserList) {
		PbsVoteuserList = pbsVoteuserList;
	}

	
	public String getPbsVoteuserIds() {
		return Collections3.extractToString(PbsVoteuserList, "user.id", ",") ;
	}

	public boolean isVoteflag() {
		return voteflag;
	}

	public void setVoteflag(boolean voteflag) {
		this.voteflag = voteflag;
	}

	public String getSBelongfunc() {
		return sBelongfunc;
	}

	public void setSBelongfunc(String sBelongfunc) {
		this.sBelongfunc = sBelongfunc;
	}

	public String getsStat() {
		return sStat;
	}

	public void setsStat(String sStat) {
		this.sStat = sStat;
	}
}