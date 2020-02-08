/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.cms.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

import java.util.Date;

/**
 * 网上论坛帖子Entity
 * @author maoxb
 * @version 2019-08-01
 */
public class CmsBbsArticle extends DataEntity<CmsBbsArticle> {
	
	private static final long serialVersionUID = 1L;
	private String fontUserName;		// 前端用户(注册的用户名称)
	private String title;		// 标题
	private String type;		// 类型
	private String contentText;		// 正文
	private String commentId;		// 评论ID
	private String viewNum;		// 浏览量
	private String commentNum;		// 评论数
	private String hotMun;		// 当日浏览量/热度
	private String likeNum;		// 点赞数
	private String endCommentName;		// 最终评论人(注册的用户名称)
	private String reviewFlag;		// 审核标记
	private Date reviewDate;		// 审核时间

	
	public CmsBbsArticle() {
		super();
	}

	public CmsBbsArticle(String id){
		super(id);
	}

	@Length(min=0, max=64, message="前端用户(注册的用户名称)长度必须介于 0 和 64 之间")
	public String getFontUserName() {
		return fontUserName;
	}

	public void setFontUserName(String fontUserName) {
		this.fontUserName = fontUserName;
	}
	
	@Length(min=0, max=255, message="标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=1, message="类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}
	
	@Length(min=0, max=64, message="评论ID长度必须介于 0 和 64 之间")
	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	
	@Length(min=0, max=11, message="浏览量长度必须介于 0 和 11 之间")
	public String getViewNum() {
		return viewNum;
	}

	public void setViewNum(String viewNum) {
		this.viewNum = viewNum;
	}
	
	@Length(min=0, max=11, message="评论数长度必须介于 0 和 11 之间")
	public String getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}
	
	@Length(min=0, max=11, message="当日浏览量/热度长度必须介于 0 和 11 之间")
	public String getHotMun() {
		return hotMun;
	}

	public void setHotMun(String hotMun) {
		this.hotMun = hotMun;
	}
	
	@Length(min=0, max=11, message="点赞数长度必须介于 0 和 11 之间")
	public String getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(String likeNum) {
		this.likeNum = likeNum;
	}
	
	@Length(min=0, max=64, message="最终评论人(注册的用户名称)长度必须介于 0 和 64 之间")
	public String getEndCommentName() {
		return endCommentName;
	}

	public void setEndCommentName(String endCommentName) {
		this.endCommentName = endCommentName;
	}

	public String getReviewFlag() {
		return reviewFlag;
	}

	public void setReviewFlag(String reviewFlag) {
		this.reviewFlag = reviewFlag;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
}