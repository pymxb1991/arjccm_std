/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.vote.entity;

import org.hibernate.validator.constraints.Length;
import com.arjjs.ccm.modules.sys.entity.User;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 投票个人选项信息Entity
 * 
 * @author lc
 * @version 2018-03-27
 */
public class PbsVoteOpdetail extends DataEntity<PbsVoteOpdetail> {

	private static final long serialVersionUID = 1L;
	private String sTopic; // 所属主题
	private String sSubject; // 所属题目
	private String sItem; // 所属选项
	private String sTopicName; // 所属主题
	private String sSubjectName; // 所属题目
	private String sItemName;
	private User user; // 登陆用户
	private String sIp; // 用户访问IP
	private String sSpare01; // 备用内容1
	private String sSpare02; // 备用内容2
	private String sSpare03; // 备用内容3
	private String sSpare04; // 备用内容4

	public PbsVoteOpdetail() {
		super();
	}

	public PbsVoteOpdetail(String id) {
		super(id);
	}

	@Length(min = 0, max = 64, message = "所属主题长度必须介于 0 和 64 之间")
	public String getSTopic() {
		return sTopic;
	}

	public void setSTopic(String sTopic) {
		this.sTopic = sTopic;
	}

	@Length(min = 0, max = 64, message = "所属题目长度必须介于 0 和 64 之间")
	public String getSSubject() {
		return sSubject;
	}

	public void setSSubject(String sSubject) {
		this.sSubject = sSubject;
	}

	@Length(min = 0, max = 64, message = "所属选项长度必须介于 0 和 64 之间")
	public String getSItem() {
		return sItem;
	}

	public void setSItem(String sItem) {
		this.sItem = sItem;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Length(min = 0, max = 50, message = "用户访问IP长度必须介于 0 和 50 之间")
	public String getSIp() {
		return sIp;
	}

	public void setSIp(String sIp) {
		this.sIp = sIp;
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

	public String getsTopicName() {
		return sTopicName;
	}

	public void setsTopicName(String sTopicName) {
		this.sTopicName = sTopicName;
	}

	public String getsSubjectName() {
		return sSubjectName;
	}

	public void setsSubjectName(String sSubjectName) {
		this.sSubjectName = sSubjectName;
	}

	public String getsItemName() {
		return sItemName;
	}

	public void setsItemName(String sItemName) {
		this.sItemName = sItemName;
	}

	
}