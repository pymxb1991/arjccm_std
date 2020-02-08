/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.vote.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.common.utils.excel.annotation.ExcelField;

/**
 * 投票题目选项信息Entity
 * @author lc
 * @version 2018-03-27
 */
public class PbsVoteItem extends DataEntity<PbsVoteItem> {
	
	private static final long serialVersionUID = 1L;
	private PbsVoteSubject sParentid;		// 题目id
	private String sName;		// 选项名称
	private String iCnt;		// 选择数量
	private String iCntCent;		// 选择百分比
	private String sSpare01;		// 备用内容1
	private String sSpare02;		// 备用内容2
	private String sSpare03;		// 备用内容3
	private String sSpare04;		// 备用内容4
	private String sSort;    // 顺位
	private PbsVoteTopic topid;  // 主题id
	private String sBelongfunc;
	private String topicName; // 主题名称
	private String subjectName; //题目名称
	
	
	private boolean voteFlag = false;  // 是否已经选中
	
	public PbsVoteItem() {
		super();
	}

	public PbsVoteItem(String id){
		super(id);
	}

	@ExcelField(title="选项名称", align=2, sort=15)
	@Length(min=0, max=64, message="选项名称长度必须介于 0 和 64 之间")
	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}
	
	@ExcelField(title="选择数量", align=2, sort=20)
	@Length(min=0, max=64, message="选择数量长度必须介于 0 和 64 之间")
	public String getICnt() {
		return StringUtils.isNoneBlank(iCnt)?iCnt:"0";
	}

	public void setICnt(String iCnt) {
		this.iCnt = iCnt;
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

	public PbsVoteSubject getsParentid() {
		return sParentid;
	}

	public void setsParentid(PbsVoteSubject sParentid) {
		this.sParentid = sParentid;
	}

	public String getiCntCent() {
		return iCntCent;
	}

	public void setiCntCent(String iCntCent) {
		this.iCntCent = iCntCent;
	}

	public PbsVoteTopic getTopid() {
		return topid;
	}

	public void setTopid(PbsVoteTopic topid) {
		this.topid = topid;
	}

	public boolean isVoteFlag() {
		return voteFlag;
	}

	public void setVoteFlag(boolean voteFlag) {
		this.voteFlag = voteFlag;
	}

	@ExcelField(title="选项", align=2, sort=14,dictType="vote_item")
	public String getsSort() {
		return sSort;
	}

	public void setsSort(String sSort) {
		this.sSort = sSort;
	}
	
	@ExcelField(title="主题名称", align=2, sort=5)
	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	
	
	@ExcelField(title="题目名称", align=2, sort=10)
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getsBelongfunc() {
		return sBelongfunc;
	}

	public void setsBelongfunc(String sBelongfunc) {
		this.sBelongfunc = sBelongfunc;
	}
	
}