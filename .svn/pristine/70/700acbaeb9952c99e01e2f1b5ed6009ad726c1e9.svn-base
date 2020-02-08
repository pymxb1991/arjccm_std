/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.email.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 邮箱表Entity
 * @author liucong
 * @version 2018-07-24
 */
public class PlmEmailBox extends DataEntity<PlmEmailBox> {
	
	private static final long serialVersionUID = 1L;
	private String emailId;		// 邮件编号
	private String senderId;		// 发送人id
	private String senderName;		// 发送人名称
	private String senderMail;		// 发件邮箱
	private String receiverId;		// 收信人
	private String receiverName;		// 收信人名称
	private String receiverMail;		// 收件邮箱
	private String copyerId;		// 抄送人
	private String copyerCode;		// 抄送人编号
	private String copyerMail;		// 抄送邮箱
	private String title;		// 主题
	private String content;		// 内容
	private String files;		// 附件
	private String sendType;		// 发送类型
	private Date sendDate;		// 发送日期
	private Date readDate;		// 读取时间
	private String boxType;		// 邮箱类型：0：草稿 1：收件箱 2：发件箱 3：垃圾箱
	private String emailStatus;		// 邮件类型：0：未读1：已读2：回复3：转发4：全部转发
	private String sendReceiver;		// 邮件位置：00：本地01：外部10：内部+短信 11：外部+短信
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	
	public PlmEmailBox() {
		super();
	}

	public PlmEmailBox(String id){
		super(id);
	}

	@Length(min=0, max=64, message="邮件编号长度必须介于 0 和 64 之间")
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	@Length(min=0, max=64, message="发送人id长度必须介于 0 和 64 之间")
	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	
	@Length(min=0, max=20, message="发送人名称长度必须介于 0 和 20 之间")
	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
	@Length(min=0, max=2000, message="发件邮箱长度必须介于 0 和 2000 之间")
	public String getSenderMail() {
		return senderMail;
	}

	public void setSenderMail(String senderMail) {
		this.senderMail = senderMail;
	}
	
	@Length(min=0, max=2000, message="收信人长度必须介于 0 和 2000 之间")
	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	
	@Length(min=0, max=2000, message="收信人名称长度必须介于 0 和 2000 之间")
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	@Length(min=0, max=2000, message="收件邮箱长度必须介于 0 和 2000 之间")
	public String getReceiverMail() {
		return receiverMail;
	}

	public void setReceiverMail(String receiverMail) {
		this.receiverMail = receiverMail;
	}
	
	@Length(min=0, max=2000, message="抄送人长度必须介于 0 和 2000 之间")
	public String getCopyerId() {
		return copyerId;
	}

	public void setCopyerId(String copyerId) {
		this.copyerId = copyerId;
	}
	
	@Length(min=0, max=2000, message="抄送人编号长度必须介于 0 和 2000 之间")
	public String getCopyerCode() {
		return copyerCode;
	}

	public void setCopyerCode(String copyerCode) {
		this.copyerCode = copyerCode;
	}
	
	@Length(min=0, max=2000, message="抄送邮箱长度必须介于 0 和 2000 之间")
	public String getCopyerMail() {
		return copyerMail;
	}

	public void setCopyerMail(String copyerMail) {
		this.copyerMail = copyerMail;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=2000, message="附件长度必须介于 0 和 2000 之间")
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	
	@Length(min=0, max=11, message="发送类型长度必须介于 0 和 11 之间")
	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
	
	@Length(min=0, max=1, message="邮箱类型：0：草稿 1：收件箱 2：发件箱 3：垃圾箱长度必须介于 0 和 1 之间")
	public String getBoxType() {
		return boxType;
	}

	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}
	
	@Length(min=0, max=2, message="邮件类型：0：未读1：已读2：回复3：转发4：全部转发长度必须介于 0 和 2 之间")
	public String getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}
	
	@Length(min=0, max=2, message="邮件位置：00：本地01：外部10：内部+短信 11：外部+短信长度必须介于 0 和 2 之间")
	public String getSendReceiver() {
		return sendReceiver;
	}

	public void setSendReceiver(String sendReceiver) {
		this.sendReceiver = sendReceiver;
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