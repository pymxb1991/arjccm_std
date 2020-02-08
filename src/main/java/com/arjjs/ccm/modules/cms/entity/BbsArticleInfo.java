/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.cms.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 帖子详情实体类
 * @author maoxb
 * @version 2019-08-02
 */
public class BbsArticleInfo  {

	private String id;		// 帖子ID
	private String fontUserName;		// 前端用户(注册的用户名称)
	private String title;		// 标题
	private String type;		// 类型
	private String contentText;		// 正文
	private String viewNum;		// 浏览量
	private String commentNum;		// 评论数
	private String hotMun;		// 当日浏览量/热度
	private String likeNum;		// 点赞数
	private String endCommentName;		// 最终评论人(注册的用户名称)
	private String createDate; //帖子创建日期
	private String reviewFlag;		// 审核标记
	private String reviewDate;		// 审核时间


	List<BbsCommentInfo> commentList = new ArrayList<>(); //评论列表

	public List<BbsCommentInfo> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<BbsCommentInfo> commentList) {
		this.commentList = commentList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFontUserName() {
		return fontUserName;
	}

	public void setFontUserName(String fontUserName) {
		this.fontUserName = fontUserName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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

	public String getViewNum() {
		return viewNum;
	}

	public void setViewNum(String viewNum) {
		this.viewNum = viewNum;
	}

	public String getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}

	public String getHotMun() {
		return hotMun;
	}

	public void setHotMun(String hotMun) {
		this.hotMun = hotMun;
	}

	public String getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(String likeNum) {
		this.likeNum = likeNum;
	}

	public String getEndCommentName() {
		return endCommentName;
	}

	public void setEndCommentName(String endCommentName) {
		this.endCommentName = endCommentName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getReviewFlag() {
		return reviewFlag;
	}

	public void setReviewFlag(String reviewFlag) {
		this.reviewFlag = reviewFlag;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
}