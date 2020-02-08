/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.act.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.User;
import com.arjjs.ccm.tool.PlmTypes;

/**
 * 业务申请单主表Entity
 * 
 * @author fu
 * @version 2018-07-20
 */
public class PlmAct extends DataEntity<PlmAct> {
    
	private static final long serialVersionUID = 1L;
	private String procInsId; // 流程实例ID
	private String title; // 流程名称
	private String tableId; // 表单ID
	private String tableName; // 表单名称
	private String formUrl; // 详情url
	private String status; // 状态
	private String type; // 类型
	private String procDefId; // 流程定义ID
	private String isSup; // 是否督办
	private User supExe; // 督办人id
	private User supIni; // 发起人id
	private String supDetail; // 督办明细
	PlmTypes plmTypes;
	@SuppressWarnings("static-access")
	public PlmAct() {
		super();
		this.isSup= plmTypes.IS_SUP_TYPE;
		
	}

	public PlmAct(String id) {
		super(id);
	}

	@Length(min = 0, max = 64, message = "流程实例ID长度必须介于 0 和 64 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}

	@Length(min = 0, max = 64, message = "流程名称长度必须介于 0 和 64 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Length(min = 0, max = 64, message = "表单ID长度必须介于 0 和 64 之间")
	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	@Length(min = 0, max = 64, message = "表单名称长度必须介于 0 和 64 之间")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Length(min = 0, max = 256, message = "详情url长度必须介于 0 和 256 之间")
	public String getFormUrl() {
		return formUrl;
	}

	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}

	@Length(min = 0, max = 2, message = "状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Length(min = 0, max = 2, message = "类型长度必须介于 0 和 40之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min = 0, max = 1, message = "是否督办长度必须介于 0 和 1 之间")
	public String getIsSup() {
		return isSup;
	}

	public void setIsSup(String isSup) {
		this.isSup = isSup;
	}

	public User getSupExe() {
		return supExe;
	}

	public void setSupExe(User supExe) {
		this.supExe = supExe;
	}

	public User getSupIni() {
		return supIni;
	}

	public void setSupIni(User supIni) {
		this.supIni = supIni;
	}

	@Length(min = 0, max = 256, message = "督办明细长度必须介于 0 和 256 之间")
	public String getSupDetail() {
		return supDetail;
	}

	public void setSupDetail(String supDetail) {
		this.supDetail = supDetail;
	}

	public String getProcDefId() {
		return procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}

}