/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 客观题信息Entity
 * @author lc
 * @version 2018-06-09
 */
public class PbsQuestionObjective extends DataEntity<PbsQuestionObjective> {
	
	private static final long serialVersionUID = 1L;
	private PbsQuestionProject sParentid;		// 科目ID
	private String sType;		// 试题类型
	private String sBody;		// 试题内容
	private Integer iChoicecnt;		// 选项个数
	private String sAnswer;		// 参考答案
	private Integer iValue;		// 分值
	private String sLevel;		// 难度等级
	private String sSpare01;		// 备用字段
	private String sSpare02;		// 备用字段
	private String sSpare03;		// 备用字段
	private String sSpare04;		// 备用字段
	private String paperid; // 考试id
	
	public PbsQuestionObjective() {
		super();
	}

	public PbsQuestionObjective(String id){
		super(id);
	}

	public PbsQuestionProject getsParentid() {
		return sParentid;
	}

	public void setsParentid(PbsQuestionProject sParentid) {
		this.sParentid = sParentid;
	}
	
	@Length(min=1, max=10, message="试题类型长度必须介于 1 和 10 之间")
	public String getSType() {
		return sType;
	}

	public void setSType(String sType) {
		this.sType = sType;
	}
	
	@Length(min=1, max=2000, message="试题内容长度必须介于 1 和 2000 之间")
	public String getSBody() {
		return sBody;
	}

	public void setSBody(String sBody) {
		this.sBody = sBody;
	}
	
	public Integer getIChoicecnt() {
		return iChoicecnt;
	}

	public void setIChoicecnt(Integer iChoicecnt) {
		this.iChoicecnt = iChoicecnt;
	}
	
	@Length(min=0, max=200, message="参考答案长度必须介于 0 和 200 之间")
	public String getSAnswer() {
		return sAnswer;
	}

	public void setSAnswer(String sAnswer) {
		this.sAnswer = sAnswer;
	}
	
	public Integer getIValue() {
		return iValue;
	}

	public void setIValue(Integer iValue) {
		this.iValue = iValue;
	}
	
	@Length(min=0, max=64, message="难度等级长度必须介于 0 和 64 之间")
	public String getSLevel() {
		return sLevel;
	}

	public void setSLevel(String sLevel) {
		this.sLevel = sLevel;
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

	public String getPaperid() {
		return paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}
	
	
}