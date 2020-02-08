/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.vote.entity;

import org.hibernate.validator.constraints.Length;
import com.arjjs.ccm.modules.sys.entity.User;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 投票主题用户信息Entity
 * @author lc
 * @version 2018-03-27
 */
public class PbsVoteUser extends DataEntity<PbsVoteUser> {
	
	private static final long serialVersionUID = 1L;
	private String topid;		// 主题id
	private User user;		// 用户id
	private String sIp;		// 用户ip
	private String score;		// 结果(积分)
	private String sSpare01;		// 其他内容1
	private String sSpare02;		// 其他内容2
	private String sSpare03;		// 其他内容3
	private String sSpare04;		// 其他内容4
	
	public PbsVoteUser() {
		super();
	}

	public PbsVoteUser(String id){
		super(id);
	}

	@Length(min=0, max=64, message="主题id长度必须介于 0 和 64 之间")
	public String getTopid() {
		return topid;
	}

	public void setTopid(String topid) {
		this.topid = topid;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=50, message="用户ip长度必须介于 0 和 50 之间")
	public String getSIp() {
		return sIp;
	}

	public void setSIp(String sIp) {
		this.sIp = sIp;
	}
	
	@Length(min=0, max=50, message="结果(积分)长度必须介于 0 和 50 之间")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
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
	
}