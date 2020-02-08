/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.exam.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.pbs.person.entity.PbsPartymem;
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionObjective;

/**
 * 考试题目信息Entity
 * 
 * @author lc
 * @version 2018-06-11
 */
public class PbsExamactionitem extends DataEntity<PbsExamactionitem> {

	private static final long serialVersionUID = 1L;
	private PbsExampaper sExampaper; // 试卷编号
	private PbsQuestionObjective sItem; // 考题编号
	private PbsPartymem sExaminee; // 答题者编号
	private String sDoanswer; // 用户的结果
	private String sJudge; // 答案对错
	private PbsExamaction sActionid; // 选择的结果
	private Integer iScore; // 给与分数
	private String sSpare01; // 其他内容1
	private String sSpare02; // 其他内容2
	private String sSpare03; // 其他内容3
	private String sSpare04; // 其他内容4

	public PbsExamactionitem() {
		super();
	}

	public PbsExamactionitem(String id) {
		super(id);
	}

	public PbsExampaper getsExampaper() {
		return sExampaper;
	}

	public void setsExampaper(PbsExampaper sExampaper) {
		this.sExampaper = sExampaper;
	}

	public PbsQuestionObjective getsItem() {
		return sItem;
	}

	public void setsItem(PbsQuestionObjective sItem) {
		this.sItem = sItem;
	}

	public PbsPartymem getsExaminee() {
		return sExaminee;
	}

	public void setsExaminee(PbsPartymem sExaminee) {
		this.sExaminee = sExaminee;
	}

	public String getsDoanswer() {
		return sDoanswer;
	}

	public void setsDoanswer(String sDoanswer) {
		this.sDoanswer = sDoanswer;
	}

	@Length(min = 0, max = 64, message = "答案对错长度必须介于 0 和 64 之间")
	public String getSJudge() {
		return sJudge;
	}

	public void setSJudge(String sJudge) {
		this.sJudge = sJudge;
	}

	public Integer getIScore() {
		return iScore;
	}

	public void setIScore(Integer iScore) {
		this.iScore = iScore;
	}

	@Length(min = 0, max = 50, message = "其他内容1长度必须介于 0 和 50 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}

	@Length(min = 0, max = 50, message = "其他内容2长度必须介于 0 和 50 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}

	@Length(min = 0, max = 50, message = "其他内容3长度必须介于 0 和 50 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}

	@Length(min = 0, max = 50, message = "其他内容4长度必须介于 0 和 50 之间")
	public String getSSpare04() {
		return sSpare04;
	}

	public void setSSpare04(String sSpare04) {
		this.sSpare04 = sSpare04;
	}

	public PbsExamaction getsActionid() {
		return sActionid;
	}

	public void setsActionid(PbsExamaction sActionid) {
		this.sActionid = sActionid;
	}
	
}