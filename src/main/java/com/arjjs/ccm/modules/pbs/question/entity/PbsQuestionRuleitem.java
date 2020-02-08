/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.question.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 评分规则定义Entity
 * @author lc
 * @version 2018-06-06
 */
public class PbsQuestionRuleitem extends DataEntity<PbsQuestionRuleitem> {
	
	private static final long serialVersionUID = 1L;
	private String sparentid;		// 评分规则名称
	private String stype;		// 试题类型
	private String scompare;		// 对比方式
	private String scodetype;		// 判断代码语言
	private String scodecontent;		// 脚本内容
	private String sjudgeval;		// 判断值
	private String ival;		// 赋分比例
	private String surl;		// 展示的图片
	private String sspare01;		// 备用字段
	private String sspare02;		// 备用字段
	private String sspare03;		// 备用字段
	private String sspare04;		// 备用字段
	
	@Length(min=1, max=64, message="评分规则名称长度必须介于 1 和 64 之间")
	public String getSparentid() {
		return sparentid;
	}

	public void setSparentid(String sparentid) {
		this.sparentid = sparentid;
	}
	
	@Length(min=0, max=10, message="试题类型长度必须介于 0 和 10 之间")
	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}
	
	@Length(min=0, max=20, message="对比方式长度必须介于 0 和 20 之间")
	public String getScompare() {
		return scompare;
	}

	public void setScompare(String scompare) {
		this.scompare = scompare;
	}
	
	@Length(min=0, max=50, message="判断代码语言长度必须介于 0 和 50 之间")
	public String getScodetype() {
		return scodetype;
	}

	public void setScodetype(String scodetype) {
		this.scodetype = scodetype;
	}

	@Length(min=0, max=2000, message="脚本内容长度必须介于 0 和 2000 之间")
	public String getScodecontent() {
		return scodecontent;
	}

	public void setScodecontent(String scodecontent) {
		this.scodecontent = scodecontent;
	}

	@Length(min=0, max=200, message="判断值长度必须介于 0 和 200 之间")
	public String getSjudgeval() {
		return sjudgeval;
	}

	public void setSjudgeval(String sjudgeval) {
		this.sjudgeval = sjudgeval;
	}

	@Length(min=0, max=11, message="赋分比例长度必须介于 0 和 11 之间")
	public String getIval() {
		return ival;
	}

	public void setIval(String ival) {
		this.ival = ival;
	}

	@Length(min=0, max=500, message="展示的图片长度必须介于 0 和 500 之间")
	public String getSurl() {
		return surl;
	}

	public void setSurl(String surl) {
		this.surl = surl;
	}

	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSspare01() {
		return sspare01;
	}

	public void setSspare01(String sspare01) {
		this.sspare01 = sspare01;
	}

	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSspare02() {
		return sspare02;
	}

	public void setSspare02(String sspare02) {
		this.sspare02 = sspare02;
	}

	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSspare03() {
		return sspare03;
	}

	public void setSspare03(String sspare03) {
		this.sspare03 = sspare03;
	}
	
	@Length(min=0, max=64, message="备用字段长度必须介于 0 和 64 之间")
	public String getSspare04() {
		return sspare04;
	}

	public void setSspare04(String sspare04) {
		this.sspare04 = sspare04;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PbsQuestionRuleitem() {
		super();
	}

	public PbsQuestionRuleitem(String id){
		super(id);
	}

	
}