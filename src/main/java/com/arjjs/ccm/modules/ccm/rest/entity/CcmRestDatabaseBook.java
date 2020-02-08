package com.arjjs.ccm.modules.ccm.rest.entity;

public class CcmRestDatabaseBook {
	private String bookId;		// 书籍信息id
	private String code;		// 书籍信息编号
	private String name;		// 书籍信息名称
	private String content;		// 文章内容
	private String imageurl;	// 书籍图片
	private String isCollection;// 是否已经收藏
	public String getIsCollection() {
		return isCollection;
	}
	public void setIsCollection(String isCollection) {
		this.isCollection = isCollection;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
}
