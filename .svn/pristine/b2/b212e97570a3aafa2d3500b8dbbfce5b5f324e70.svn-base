/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.report.entity;

import com.arjjs.ccm.modules.sys.entity.Area;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 人口新增统计Entity
 * @author arj
 * @version 2018-01-20
 */
public class CcmPeopleStat extends DataEntity<CcmPeopleStat> {
	
	private static final long serialVersionUID = 1L;
	private Area area;		// 区域id
	private Date statDate;		// 日期（年月日）
	private Integer personNew;		// 户籍新增数量
	private Integer overseaNew;		// 外籍新增数量
	private Integer floatNew;		// 流动新增数量
	private Integer unsettleNew;		// 未落户新增数量
	private Integer aidsNew;		// 艾滋病患者新增数量
	private Integer disputeNew;		//矛盾纠纷新增数量
	private Integer psychogenyNew;		// 肇事肇祸等严重精神障碍患者新增数量
	private Integer rectificationNew;		// 社区矫正新增数量
	private Integer releaseNew;		// 刑释新增数量
	private Integer drugsNew;		// 吸毒新增数量
	private Integer escapeNew;      //在逃新增数量
	private Integer behindNew;		// 留守新增数量
	private Integer kymNew;		// 重点青少年新增数量
	private Integer visitNew;		// 重点上访新增数量
	private Integer heresyNew;		// 涉教人员新增数量
	private Integer dangerousNew;		// 危险品从业人员新增数量
	private Integer harmNationalNew;  //危害国家安全新增数量
	private Integer more1;		// 冗余字段1
	private String more2;		// 冗余字段2
	private Integer more3;		// 冗余字段3
	private Date beginStatDate;		// 开始 日期（年月日）
	private Date endStatDate;		// 结束 日期（年月日）
	
	public CcmPeopleStat() {
		super();
	}

	public CcmPeopleStat(String id){
		super(id);
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}
	
	public Integer getPersonNew() {
		return personNew;
	}

	public Integer getHarmNationalNew() {
		return harmNationalNew;
	}

	public void setHarmNationalNew(Integer harmNationalNew) {
		this.harmNationalNew = harmNationalNew;
	}

	public void setPersonNew(Integer personNew) {
		this.personNew = personNew;
	}
	
	public Integer getOverseaNew() {
		return overseaNew;
	}

	public void setOverseaNew(Integer overseaNew) {
		this.overseaNew = overseaNew;
	}
	
	

	public Integer getFloatNew() {
		return floatNew;
	}

	public void setFloatNew(Integer floatNew) {
		this.floatNew = floatNew;
	}
	
	public Integer getUnsettleNew() {
		return unsettleNew;
	}

	public void setUnsettleNew(Integer unsettleNew) {
		this.unsettleNew = unsettleNew;
	}
	
	public Integer getAidsNew() {
		return aidsNew;
	}

	public void setAidsNew(Integer aidsNew) {
		this.aidsNew = aidsNew;
	}
	
	public Integer getDisputeNew() {
		return disputeNew;
	}

	public void setDisputeNew(Integer disputeNew) {
		this.disputeNew = disputeNew;
	}

	public Integer getPsychogenyNew() {
		return psychogenyNew;
	}

	public void setPsychogenyNew(Integer psychogenyNew) {
		this.psychogenyNew = psychogenyNew;
	}
	
	public Integer getRectificationNew() {
		return rectificationNew;
	}

	public void setRectificationNew(Integer rectificationNew) {
		this.rectificationNew = rectificationNew;
	}
	
	public Integer getReleaseNew() {
		return releaseNew;
	}

	public void setReleaseNew(Integer releaseNew) {
		this.releaseNew = releaseNew;
	}
	
	public Integer getDrugsNew() {
		return drugsNew;
	}

	public void setDrugsNew(Integer drugsNew) {
		this.drugsNew = drugsNew;
	}
	
	public Integer getBehindNew() {
		return behindNew;
	}

	public void setBehindNew(Integer behindNew) {
		this.behindNew = behindNew;
	}
	
	public Integer getKymNew() {
		return kymNew;
	}

	public void setKymNew(Integer kymNew) {
		this.kymNew = kymNew;
	}
	
	public Integer getVisitNew() {
		return visitNew;
	}

	public void setVisitNew(Integer visitNew) {
		this.visitNew = visitNew;
	}
	
	public Integer getHeresyNew() {
		return heresyNew;
	}

	public void setHeresyNew(Integer heresyNew) {
		this.heresyNew = heresyNew;
	}
	
	public Integer getDangerousNew() {
		return dangerousNew;
	}

	public void setDangerousNew(Integer dangerousNew) {
		this.dangerousNew = dangerousNew;
	}
	
	public Integer getMore1() {
		return more1;
	}

	public void setMore1(Integer more1) {
		this.more1 = more1;
	}
	
	@Length(min=0, max=12, message="冗余字段2长度必须介于 0 和 12 之间")
	public String getMore2() {
		return more2;
	}

	public void setMore2(String more2) {
		this.more2 = more2;
	}
	
	public Integer getMore3() {
		return more3;
	}

	public void setMore3(Integer more3) {
		this.more3 = more3;
	}
	
	public Date getBeginStatDate() {
		return beginStatDate;
	}

	public void setBeginStatDate(Date beginStatDate) {
		this.beginStatDate = beginStatDate;
	}
	
	public Date getEndStatDate() {
		return endStatDate;
	}

	public void setEndStatDate(Date endStatDate) {
		this.endStatDate = endStatDate;
	}

	public Integer getEscapeNew() {
		return escapeNew;
	}

	public void setEscapeNew(Integer escapeNew) {
		this.escapeNew = escapeNew;
	}
	
		
}