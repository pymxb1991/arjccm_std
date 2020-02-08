/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.exam.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.pbs.question.entity.PbsChoiceItem;
import com.arjjs.ccm.modules.pbs.question.entity.PbsQuestionObjective;

/**
 * 试卷题目Entity
 * @author lc
 * @version 2018-06-11
 */
public class PbsExampaperitem extends DataEntity<PbsExampaperitem> {
	
	private static final long serialVersionUID = 1L;
	private PbsExampaper sExampaper;		// 试卷编号
	private PbsQuestionObjective sItem;		// 考题编号
	private String sBelongtype;		// 所属试题类型区域
	private Integer iSort;		// 出题顺序
	private String sSpare01;		// 其他内容1
	private String sSpare02;		// 其他内容2
	private String sSpare03;		// 其他内容3
	private String sSpare04;		// 其他内容4
	private List<PbsChoiceItem> choiceList; //所有的选项题目信息  
	private PbsExamactionitem pbsExamactionitem = new PbsExamactionitem(); //  用户选择的对象信息

	
	public PbsExampaperitem() {
		super();
	}

	public PbsExampaperitem(String id){
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
	
	@Length(min=0, max=10, message="所属试题类型区域长度必须介于 0 和 10 之间")
	public String getSBelongtype() {
		return sBelongtype;
	}

	public void setSBelongtype(String sBelongtype) {
		this.sBelongtype = sBelongtype;
	}
	
	public Integer getISort() {
		return iSort;
	}

	public void setISort(Integer iSort) {
		this.iSort = iSort;
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

	public List<PbsChoiceItem> getChoiceList() {
		return choiceList;
	}

	public void setChoiceList(List<PbsChoiceItem> choiceList) {
		this.choiceList = choiceList;
	}

	public PbsExamactionitem getPbsExamactionitem() {
		return pbsExamactionitem;
	}

	public void setPbsExamactionitem(PbsExamactionitem pbsExamactionitem) {
		this.pbsExamactionitem = pbsExamactionitem;
	}
	
	
	
}