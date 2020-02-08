/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.policy.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 政策法规Entity
 * @author liu
 * @version 2018-06-20
 */
public class PlmKnowPolicy extends DataEntity<PlmKnowPolicy> {
    public static final String DEFAULT_TEMPLATE = "frontViewArticle";
	private static final long serialVersionUID = 1L;
	private String file1;		// 附件1
	private String level;		// 效力级别
	private String type;		// 类别
	private String name;		// 标题
	private String lssNo;		// 发文字号
	private String relDept;		// 发布部门
	private Date relDate;		// 发布日期
	private Date impDate;		// 实施日期
	private String ratifyDept;		// 批准部门
	private Date ratifyDate;		// 批准日期
	private String prescription;		// 时效性
	private String content;		// 内容
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	private String types;		//区分法规和规章
	
	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public PlmKnowPolicy() {
		super();
	}

	public PlmKnowPolicy(String id){
		super(id);
	}

	@Length(min=0, max=256, message="附件1长度必须介于 0 和 256 之间")
	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}
	
	@Length(min=0, max=2, message="效力级别长度必须介于 0 和 2 之间")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	@Length(min=0, max=2, message="类别长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=512, message="标题长度必须介于 0 和 512 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="发文字号长度必须介于 0 和 64 之间")
	public String getLssNo() {
		return lssNo;
	}

	public void setLssNo(String lssNo) {
		this.lssNo = lssNo;
	}
	
	@Length(min=0, max=64, message="发布部门长度必须介于 0 和 64 之间")
	public String getRelDept() {
		return relDept;
	}

	public void setRelDept(String relDept) {
		this.relDept = relDept;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRelDate() {
		return relDate;
	}

	public void setRelDate(Date relDate) {
		this.relDate = relDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getImpDate() {
		return impDate;
	}

	public void setImpDate(Date impDate) {
		this.impDate = impDate;
	}
	
	@Length(min=0, max=64, message="批准部门长度必须介于 0 和 64 之间")
	public String getRatifyDept() {
		return ratifyDept;
	}

	public void setRatifyDept(String ratifyDept) {
		this.ratifyDept = ratifyDept;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRatifyDate() {
		return ratifyDate;
	}

	public void setRatifyDate(Date ratifyDate) {
		this.ratifyDate = ratifyDate;
	}
	
	@Length(min=0, max=64, message="时效性长度必须介于 0 和 64 之间")
	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=256, message="扩展1长度必须介于 0 和 256 之间")
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	@Length(min=0, max=256, message="扩展2长度必须介于 0 和 256 之间")
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	
}