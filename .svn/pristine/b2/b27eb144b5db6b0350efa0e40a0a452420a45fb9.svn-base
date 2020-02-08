/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 用户指南表Entity
 * @author fuxinshuang
 * @version 2018-03-13
 */
public class CcmServiceGuidance extends DataEntity<CcmServiceGuidance> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 事项分类
	private String title;		// 标题
	private String pcsPeople;		// 办理对象
	private String pcsConditions;		// 办理条件
	private String pcsFiles;		// 提前准备材料
	private String attention;		// 注意事项
	private String expense;		// 相关费用
	private String process;		// 具体流程
	private String pcsAdd;		// 办事地点
	private String officeHours;		// 办公时间
	private String file;		// 附件
	private String typeLable;	//用于app列表显示
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	
	public CcmServiceGuidance() {
		super();
	}

	public CcmServiceGuidance(String id){
		super(id);
	}

	@Length(min=0, max=2, message="事项分类长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="标题长度必须介于 0 和 64 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=256, message="办理对象长度必须介于 0 和 256 之间")
	public String getPcsPeople() {
		return pcsPeople;
	}

	public void setPcsPeople(String pcsPeople) {
		this.pcsPeople = pcsPeople;
	}
	
	@Length(min=0, max=1000, message="办理条件长度必须介于 0 和 1000 之间")
	public String getPcsConditions() {
		return pcsConditions;
	}

	public void setPcsConditions(String pcsConditions) {
		this.pcsConditions = pcsConditions;
	}
	
	@Length(min=0, max=1000, message="提前准备材料长度必须介于 0 和 1000 之间")
	public String getPcsFiles() {
		return pcsFiles;
	}

	public void setPcsFiles(String pcsFiles) {
		this.pcsFiles = pcsFiles;
	}
	
	@Length(min=0, max=1000, message="注意事项长度必须介于 0 和 1000 之间")
	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}
	
	@Length(min=0, max=1000, message="相关费用长度必须介于 0 和 1000 之间")
	public String getExpense() {
		return expense;
	}

	public void setExpense(String expense) {
		this.expense = expense;
	}
	
	@Length(min=0, max=1000, message="具体流程长度必须介于 0 和 1000 之间")
	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}
	
	@Length(min=0, max=256, message="办事地点长度必须介于 0 和 256 之间")
	public String getPcsAdd() {
		return pcsAdd;
	}

	public void setPcsAdd(String pcsAdd) {
		this.pcsAdd = pcsAdd;
	}
	
	@Length(min=0, max=256, message="办公时间长度必须介于 0 和 256 之间")
	public String getOfficeHours() {
		return officeHours;
	}

	public void setOfficeHours(String officeHours) {
		this.officeHours = officeHours;
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

	public String getTypeLable() {
		return typeLable;
	}

	public void setTypeLable(String typeLable) {
		this.typeLable = typeLable;
	}
	
}