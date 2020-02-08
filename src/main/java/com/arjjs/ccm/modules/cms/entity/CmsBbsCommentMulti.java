/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.cms.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 网上论坛多级评论Entity
 * @author maoxb
 * @version 2019-08-01
 */
public class CmsBbsCommentMulti extends DataEntity<CmsBbsCommentMulti> {
	
	private static final long serialVersionUID = 1L;
	private String commentId;		// 一级评论id
	private String commentMultiContent;		// comment_multi_content
	private String commentMultiUserId;		// 多级评论用户id
	private Date commentMultiTime;		// comment_multi_time
	
	public CmsBbsCommentMulti() {
		super();
	}

	public CmsBbsCommentMulti(String id){
		super(id);
	}

	@Length(min=1, max=64, message="一级评论id长度必须介于 1 和 64 之间")
	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	
	@Length(min=0, max=255, message="comment_multi_content长度必须介于 0 和 255 之间")
	public String getCommentMultiContent() {
		return commentMultiContent;
	}

	public void setCommentMultiContent(String commentMultiContent) {
		this.commentMultiContent = commentMultiContent;
	}
	
	@Length(min=1, max=64, message="多级评论用户id长度必须介于 1 和 64 之间")
	public String getCommentMultiUserId() {
		return commentMultiUserId;
	}

	public void setCommentMultiUserId(String commentMultiUserId) {
		this.commentMultiUserId = commentMultiUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCommentMultiTime() {
		return commentMultiTime;
	}

	public void setCommentMultiTime(Date commentMultiTime) {
		this.commentMultiTime = commentMultiTime;
	}
	
}