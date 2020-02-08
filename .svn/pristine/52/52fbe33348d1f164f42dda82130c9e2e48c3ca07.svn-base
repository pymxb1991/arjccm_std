/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.person.entity;

import java.beans.Transient;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.StringUtils;
import com.google.common.collect.Lists;

/**
 * 人物标签Entity
 * 
 * @author lc
 * @version 2018-08-03
 */
public class PbsMemlabel extends DataEntity<PbsMemlabel> {

	private static final long serialVersionUID = 1L;
	private PbsPartymem sMemberid; // 党员
	private String sType; // 标签类别
	private String sLabelids; // 标签列表信息 存储使用
	//private String[] sLabelidLists; // 标签数组 显示使用
	private String sDescription; // 描述信息
	private String sSpare01; // 备用字段
	private String sSpare02; // 备用字段
	private String sSpare03; // 备用字段
	private String sSpare04; // 备用字段

	public PbsMemlabel() {
		super();
	}

	public PbsMemlabel(String id) {
		super(id);
	}

	public PbsPartymem getsMemberid() {
		return sMemberid;
	}

	public void setsMemberid(PbsPartymem sMemberid) {
		this.sMemberid = sMemberid;
	}

	@Length(min = 0, max = 64, message = "标签类别长度必须介于 0 和 64 之间")
	public String getSType() {
		return sType;
	}

	public void setSType(String sType) {
		this.sType = sType;
	}

	public String getSLabelids() {
		return sLabelids;
	}

	public void setSLabelids(String sLabelids) {
		// StringBuffer sB = new StringBuffer();
		// if(null==sLabelidLists){
		// return ;
		// }
		// for (int i = 0; i < sLabelidLists.length; i++) {
		// sB.append(sLabelidLists[i]);
		// }
		this.sLabelids = sLabelids;
		// sB.toString();
	}

	
	@Transient
	public List<String> getPosidList() {
		List<String> list = Lists.newArrayList();
		if (sLabelids != null){
			for (String s : StringUtils.split(sLabelids, ",")) {
				list.add(s);
			}
		}
		return list;
	}
 
	@Transient
	public void setPosidList(List<String> list) {
		sLabelids = ","+StringUtils.join(list, ",")+",";
	}
	
	// 从数据库获取
	// public String[] getsLabelidLists() {
	// return sLabelids.split(",");
	// }
	//
	// public void setsLabelidLists(String[] sLabelidLists) {
	// this.sLabelidLists = sLabelidLists;
	// }

	@Length(min = 0, max = 1000, message = "描述信息长度必须介于 0 和 1000 之间")
	public String getSDescription() {
		return sDescription;
	}

	public void setSDescription(String sDescription) {
		this.sDescription = sDescription;
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

}