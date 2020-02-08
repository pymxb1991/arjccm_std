/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.cms.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 网上论坛一级评论Entity
 * @author maoxb
 * @version 2019-08-01
 */
public class CmsBbsComment extends DataEntity<CmsBbsComment> {
	
	private static final long serialVersionUID = 1L;
	private String comContent;		// 评论内容
	private String articleId;		// 文章id
	private String fontUserId;		// 评论用户的id
	private Date comTime;		// 评论时间
	
	public CmsBbsComment() {
		super();
	}

	public CmsBbsComment(String id){
		super(id);
	}

	@Length(min=0, max=255, message="评论内容长度必须介于 0 和 255 之间")
	public String getComContent() {
		return comContent;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
	}
	
	@Length(min=0, max=64, message="文章id长度必须介于 0 和 64 之间")
	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	
	@Length(min=0, max=64, message="评论用户的id长度必须介于 0 和 64 之间")
	public String getFontUserId() {
		return fontUserId;
	}

	public void setFontUserId(String fontUserId) {
		this.fontUserId = fontUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getComTime() {
		return comTime;
	}

	public void setComTime(Date comTime) {
		this.comTime = comTime;
	}
	
}