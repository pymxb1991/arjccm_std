/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.entity.wechat;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 微信信息回复Entity
 * @author fu
 * @version 2018-04-14
 */
public class CcmWechatEventReply extends DataEntity<CcmWechatEventReply> {
	
	private static final long serialVersionUID = 1L;
	private CcmWechatEvent event;		// 事件ID
	private Date replyTime;		// 回复时间
	private String replyUserId;		// 回复人员
	private String replyInfo;		// 回复信息
	private String status;		// 消息回复状态
	private Date beginReplyTime;		// 开始 回复时间
	private Date endReplyTime;		// 结束 回复时间
	private String type;	//查询方式
	
	public CcmWechatEventReply() {
		super();
	}

	public CcmWechatEventReply(String id){
		super(id);
	}

	public CcmWechatEvent getEvent() {
		return event;
	}

	public void setEvent(CcmWechatEvent event) {
		this.event = event;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	
	@Length(min=0, max=64, message="回复人员长度必须介于 0 和 64 之间")
	public String getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(String replyUserId) {
		this.replyUserId = replyUserId;
	}
	
	@Length(min=0, max=256, message="回复信息长度必须介于 0 和 256 之间")
	public String getReplyInfo() {
		return replyInfo;
	}

	public void setReplyInfo(String replyInfo) {
		this.replyInfo = replyInfo;
	}
	
	@Length(min=0, max=2, message="消息回复状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getBeginReplyTime() {
		return beginReplyTime;
	}

	public void setBeginReplyTime(Date beginReplyTime) {
		this.beginReplyTime = beginReplyTime;
	}
	
	public Date getEndReplyTime() {
		return endReplyTime;
	}

	public void setEndReplyTime(Date endReplyTime) {
		this.endReplyTime = endReplyTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
		
}