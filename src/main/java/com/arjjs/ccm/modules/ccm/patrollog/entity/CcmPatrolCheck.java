/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrollog.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 巡检考评Entity
 * @author 刘永建
 * @version 2019-07-15
 */
public class CcmPatrolCheck extends DataEntity<CcmPatrolCheck> {
	
	private static final long serialVersionUID = 1L;
	private String image;		// 照片
	private String patrolContent;		// 巡检内容
	private String reportPerson;		// 登记人
	private String reportRoad;		// 路段
	private String checkPerson;		// 考评人
	private Date checkDate;		// 考评时间
	private String score;		// 分数
	private String evaluate;		// 评价
	private Date beginCreateDate;		// 开始 登记时间
	private Date endCreateDate;		// 结束 登记时间
	
	public CcmPatrolCheck() {
		super();
	}

	public CcmPatrolCheck(String id){
		super(id);
	}

	@Length(min=0, max=255, message="照片长度必须介于 0 和 255 之间")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Length(min=1, max=2000, message="巡检内容长度必须介于 1 和 2000 之间")
	public String getPatrolContent() {
		return patrolContent;
	}

	public void setPatrolContent(String patrolContent) {
		this.patrolContent = patrolContent;
	}
	
	@Length(min=1, max=15, message="登记人长度必须介于 1 和 15 之间")
	public String getReportPerson() {
		return reportPerson;
	}

	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}
	
	@Length(min=0, max=255, message="路段长度必须介于 0 和 255 之间")
	public String getReportRoad() {
		return reportRoad;
	}

	public void setReportRoad(String reportRoad) {
		this.reportRoad = reportRoad;
	}
	
	@Length(min=0, max=15, message="考评人长度必须介于 0 和 15 之间")
	public String getCheckPerson() {
		return checkPerson;
	}

	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	@Length(min=0, max=255, message="分数长度必须介于 0 和 255 之间")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	@Length(min=0, max=2000, message="评价长度必须介于 0 和 2000 之间")
	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
}