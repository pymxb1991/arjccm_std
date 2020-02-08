/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.travel.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.ActEntity;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 公文管理Entity
 * @author dongqikai
 * @version 2018-07-17
 */
public class PlmOfficialDocument extends ActEntity<PlmOfficialDocument> {
	
	private static final long serialVersionUID = 1L;
	private String procInsId;		// 流程id
	private String title;		// 标题
	private String documentType;		// 文案类型
	private String code;		// 编号
	private String urgent;		// 紧急程度
	private String confidentiality;		// 密级
	private Date date;		// 日期
	private String deadline;		// 保密期限
	private Office fromDept;		// 发送单位
	private Office toDept;		// 接收单位
	private Office associatedDept;		// 联合行文单位
	private String type;		// 记录类型
	private String file;		// 附件
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	private Date beginDate;		// 开始 日期
	private Date endDate;		// 结束 日期
	private PlmAct plmAct;		//业务流程总表
	public static final String TYPE_RECEIVE = "0"; //记录类型为接收
	public static final String TYPE_SEND = "1";  //记录类型为发送
	private String cancelFlag;   //是否可撤销
	
	public PlmAct getPlmAct() {
		return plmAct;
	}

	public void setPlmAct(PlmAct plmAct) {
		this.plmAct = plmAct;
	}

	public PlmOfficialDocument() {
		super();
	}

	public PlmOfficialDocument(String id){
		super(id);
	}

	@Length(min=0, max=64, message="流程id长度必须介于 0 和 64 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}
	
	@Length(min=1, max=255, message="标题长度必须介于 1 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=1, max=16, message="文案类型长度必须介于 1 和 16 之间")
	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
	@Length(min=1, max=64, message="编号长度必须介于 1 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=16, message="紧急程度长度必须介于 1 和 16 之间")
	public String getUrgent() {
		return urgent;
	}

	public void setUrgent(String urgent) {
		this.urgent = urgent;
	}
	
	@Length(min=1, max=16, message="密级长度必须介于 1 和 16 之间")
	public String getConfidentiality() {
		return confidentiality;
	}

	public void setConfidentiality(String confidentiality) {
		this.confidentiality = confidentiality;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="日期不能为空")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Length(min=1, max=16, message="保密期限长度必须介于 1 和 16 之间")
	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	public Office getFromDept() {
		return fromDept;
	}

	public void setFromDept(Office fromDept) {
		this.fromDept = fromDept;
	}
	
	public Office getToDept() {
		return toDept;
	}

	public void setToDept(Office toDept) {
		this.toDept = toDept;
	}
	
	public Office getAssociatedDept() {
		return associatedDept;
	}

	public void setAssociatedDept(Office associatedDept) {
		this.associatedDept = associatedDept;
	}
	
	@Length(min=1, max=16, message="记录类型长度必须介于 1 和 16 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=4000, message="附件长度必须介于 0 和 4000 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
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
	
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
		
}