/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.vote.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.google.common.collect.Lists;

/**
 * 投票题目Entity
 * 
 * @author lc
 * @version 2018-03-27
 */
public class PbsVoteSubject extends DataEntity<PbsVoteSubject> {

	private static final long serialVersionUID = 1L;
	private PbsVoteTopic sParentid; // 父id
	private String sName; // 题目名称
	private String sMode; // 题目模式
	private String sSpare01; // 备用内容1
	private String sSpare02; // 备用内容2
	private String sSpare03; // 备用内容3
	private String sSpare04; // 备用内容4
	private String sSort; // 顺位
	private String sSum; // 总数
	
	private int itemCount; // 查询的结果
	
	private List<PbsVoteItem> pbsVoteItemList = Lists.newArrayList();

	public PbsVoteSubject() {
		super();
	}

	public PbsVoteSubject(String id) {
		super(id);
	}

	@Length(min = 0, max = 500, message = "题目名称长度必须介于 0 和 500 之间")
	public String getSName() {
		return sName;
	}

	public void setSName(String sName) {
		this.sName = sName;
	}

	@Length(min = 0, max = 640, message = "题目模式长度必须介于 0 和 640 之间")
	public String getsMode() {
		return sMode;
	}

	public void setsMode(String sMode) {
		this.sMode = sMode;
	}

	@Length(min = 0, max = 50, message = "备用内容1长度必须介于 0 和 50 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}

	@Length(min = 0, max = 50, message = "备用内容2长度必须介于 0 和 50 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}

	@Length(min = 0, max = 50, message = "备用内容3长度必须介于 0 和 50 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}

	@Length(min = 0, max = 50, message = "备用内容4长度必须介于 0 和 50 之间")
	public String getSSpare04() {
		return sSpare04;
	}

	public void setSSpare04(String sSpare04) {
		this.sSpare04 = sSpare04;
	}

	public PbsVoteTopic getsParentid() {
		return sParentid;
	}

	public void setsParentid(PbsVoteTopic sParentid) {
		this.sParentid = sParentid;
	}

	public String getsSort() {
		return sSort;
	}

	public void setsSort(String sSort) {
		this.sSort = sSort;
	}

	public String getsSum() {
		return sSum;
	}

	public void setsSum(String sSum) {
		this.sSum = sSum;
	}

	public List<PbsVoteItem> getPbsVoteItemList() {
		return pbsVoteItemList;
	}

	public void setPbsVoteItemList(List<PbsVoteItem> pbsVoteItemList) {
		this.pbsVoteItemList = pbsVoteItemList;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	
	
	
	
}