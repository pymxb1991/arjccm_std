/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.rest.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 微信信息回复Entity
 * @author fu
 * @version 2018-04-14
 */
public class CcmWechatEventReplyRest  {
	
	private String event_id;		// 事件ID
	private Date reply_time;		// 回复时间
	private String reply_user_id;		// 回复人员
	private String reply_user_name;		// 回复人员
	private String reply_info;		// 回复信息
	private String status;		// 消息回复状态
	private Date beginReplyTime;		// 开始 回复时间
	private Date endReplyTime;		// 结束 回复时间
	private String type;	//查询方式
	

	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReply_time() {
		return reply_time;
	}

	public void setReply_time(Date reply_time) {
		this.reply_time = reply_time;
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

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getReply_user_id() {
		return reply_user_id;
	}

	public void setReply_user_id(String reply_user_id) {
		this.reply_user_id = reply_user_id;
	}

	public String getReply_user_name() {
		return reply_user_name;
	}

	public void setReply_user_name(String reply_user_name) {
		this.reply_user_name = reply_user_name;
	}

	public String getReply_info() {
		return reply_info;
	}

	public void setReply_info(String reply_info) {
		this.reply_info = reply_info;
	}
		
}