package com.arjjs.ccm.modules.ccm.collection.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CcmRestCollection implements Serializable {
	private static final long serialVersionUID = 1L;
	private String bookId;		    // 书籍信息UUID
	private String bookCode;	    // 书籍信息编号
	private String bookName;        // 书籍信息名称
	private String databaseId;		// 书籍所属资料UUID
	private String databaseCode;	// 书籍所属资料编号
	private String databaseName;    // 书籍所属资料名称
	private Date createDate;        // 书籍收藏创建时间
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getDatabaseId() {
		return databaseId;
	}
	public void setDatabaseId(String databaseId) {
		this.databaseId = databaseId;
	}
	public String getDatabaseCode() {
		return databaseCode;
	}
	public void setDatabaseCode(String databaseCode) {
		this.databaseCode = databaseCode;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
