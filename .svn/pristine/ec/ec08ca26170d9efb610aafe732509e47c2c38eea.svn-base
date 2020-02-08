/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.collection.entity;

import org.hibernate.validator.constraints.Length;
import com.arjjs.ccm.modules.sys.entity.User;
import javax.validation.constraints.NotNull;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 收藏夹管理Entity
 * @author cdz
 * @version 2019-09-16
 */
public class CcmDatabaseCollection extends DataEntity<CcmDatabaseCollection> {
	
	private static final long serialVersionUID = 1L;
	private String bookId;		// 书籍信息UUID
	private User user;		// 用户信息UUID
	private String bookName;
	private String userName;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public CcmDatabaseCollection() {
		super();
	}

	public CcmDatabaseCollection(String id){
		super(id);
	}

	@Length(min=1, max=64, message="书籍信息UUID长度必须介于 1 和 64 之间")
	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
	@NotNull(message="用户信息UUID不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}