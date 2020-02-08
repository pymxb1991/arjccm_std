/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 选择题选项Entity
 * 
 * @author lc
 * @version 2018-06-09
 */
public class PbsChoiceItem extends DataEntity<PbsChoiceItem> {

	private static final long serialVersionUID = 1L;
	private String sType; // 试题类型
	private PbsQuestionObjective sParentid; // 选择题编号
	private String sKey; // 选项键值
	private String sBody; // 选项内容
	private String iSort; // 选项展示排序
	private String paperid; //试卷编号
	private String sSpare01; // 备用字段
	private String sSpare02; // 备用字段
	private String sSpare03; // 备用字段
	private String sSpare04; // 备用字段

	public PbsChoiceItem() {
		super();
	}

	public PbsChoiceItem(String id) {
		super(id);
	}

	@Length(min = 1, max = 10, message = "试题类型长度必须介于 1 和 10 之间")
	public String getSType() {
		return sType;
	}

	public void setSType(String sType) {
		this.sType = sType;
	}

	public PbsQuestionObjective getsParentid() {
		return sParentid;
	}

	public void setsParentid(PbsQuestionObjective sParentid) {
		this.sParentid = sParentid;
	}
	
	@NotEmpty
	@Length(min = 1, max = 20, message = "选项键值长度必须介于 1 和 20 之间")
	public String getSKey() {
		return sKey;
	}

	public void setSKey(String sKey) {
		this.sKey = sKey;
	}
	
	
	@NotEmpty
	@Length(min = 1, max = 2000, message = "选项内容长度必须介于 1 和 2000 之间")
	public String getSBody() {
		return sBody;
	}

	public void setSBody(String sBody) {
		this.sBody = sBody;
	}

	@Length(min = 0, max = 11, message = "选项展示排序长度必须介于 0 和 11 之间")
	public String getISort() {
		return iSort;
	}

	public void setISort(String iSort) {
		this.iSort = iSort;
	}

	@Length(min = 0, max = 64, message = "备用字段长度必须介于 0 和 64 之间")
	public String getSSpare01() {
		return sSpare01;
	}

	public void setSSpare01(String sSpare01) {
		this.sSpare01 = sSpare01;
	}

	@Length(min = 0, max = 64, message = "备用字段长度必须介于 0 和 64 之间")
	public String getSSpare02() {
		return sSpare02;
	}

	public void setSSpare02(String sSpare02) {
		this.sSpare02 = sSpare02;
	}

	@Length(min = 0, max = 64, message = "备用字段长度必须介于 0 和 64 之间")
	public String getSSpare03() {
		return sSpare03;
	}

	public void setSSpare03(String sSpare03) {
		this.sSpare03 = sSpare03;
	}

	@Length(min = 0, max = 64, message = "备用字段长度必须介于 0 和 64 之间")
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