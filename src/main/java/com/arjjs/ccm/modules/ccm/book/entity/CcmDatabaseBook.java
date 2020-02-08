/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;

import com.arjjs.ccm.common.persistence.DataEntity;

import java.util.Date;
import java.util.List;

/**
 * 资料库录入管理Entity
 * @author cdz
 * @version 2019-09-16
 */
@ApiModel
public class CcmDatabaseBook extends DataEntity<CcmDatabaseBook> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="书籍信息编号")
	private String code;		// 书籍信息编号
	@ApiModelProperty(value="书籍名称")
	private String name;		// 书籍名称
	@ApiModelProperty(value="书籍父节点")
	private CcmDatabaseBook parent;		// 书籍父节点id
	@ApiModelProperty(value="书籍所有父级id")
	private String parentIds;		// 书籍所有父级id
	@ApiModelProperty(value="书籍父级id")
	private String bookId;		// 书籍父级id
	@ApiModelProperty(value="用户id")
	private String userId;		// 用户id
	@ApiModelProperty(value="书籍显示排序")
	private String sort;		// 书籍显示排序
	@ApiModelProperty(value="书籍类型")
	private String type;		// 书籍类型（书籍，目录，内容）
	@ApiModelProperty(value="审核状态")
	private String examine;		// 审核状态
	@ApiModelProperty(value="是否为重点推荐书籍")
	private String isKeyBook;		// 是否为重点推荐书籍
	@ApiModelProperty(value="文章内容")
	private String content;		// 文章内容
	@ApiModelProperty(value="书籍图片")
	private String imageurl;		// 书籍图片
	@ApiModelProperty(value="是否已经收藏")
	private String isCollection;		// 是否已经收藏
	@ApiModelProperty(value="开始日期")
	private Date beginCreateDate;		// 开始日期
	@ApiModelProperty(value="开始日期")
	private Date endCreateDate;		// 开始日期

	public String getIsCollection() {
		return isCollection;
	}

	public void setIsCollection(String isCollection) {
		this.isCollection = isCollection;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public CcmDatabaseBook() {
		super();
	}

	public CcmDatabaseBook(String id){
		super(id);
	}

	@Length(min=0, max=64, message="书籍信息编号长度必须介于 0 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=100, message="书籍名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonBackReference
	public CcmDatabaseBook getParent() {
		return parent;
	}

	public void setParent(CcmDatabaseBook parent) {
		this.parent = parent;
	}

	public String getParentId() {
		return this.parent != null && this.parent.getId() != null ? this.parent.getId() : "0";
	}

	@JsonIgnore
	public static String getRootId() {
		return "0";
	}

	@JsonIgnore
	public static void sortList(List<CcmDatabaseBook> list, List<CcmDatabaseBook> sourcelist, String parentId, boolean cascade) {
		for(int i = 0; i < sourcelist.size(); ++i) {
			CcmDatabaseBook e = (CcmDatabaseBook)sourcelist.get(i);
			if (e.getParent() != null && e.getParent().getId() != null && e.getParent().getId().equals(parentId)) {
				list.add(e);
				if (cascade) {
					for(int j = 0; j < sourcelist.size(); ++j) {
						CcmDatabaseBook child = (CcmDatabaseBook)sourcelist.get(j);
						if (child.getParent() != null && child.getParent().getId() != null && child.getParent().getId().equals(e.getId())) {
							sortList(list, sourcelist, e.getId(), true);
							break;
						}
					}
				}
			}
		}

	}

	@Length(min=0, max=2000, message="书籍所有父级id长度必须介于 0 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=6, message="书籍类型（书籍，目录，内容）长度必须介于 0 和 6 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=6, message="审核状态长度必须介于 0 和 6 之间")
	public String getExamine() {
		return examine;
	}

	public void setExamine(String examine) {
		this.examine = examine;
	}
	
	public String getIsKeyBook() {
		return isKeyBook;
	}

	public void setIsKeyBook(String isKeyBook) {
		this.isKeyBook = isKeyBook;
	}

	@Length(min=0, max=5000, message="书籍内容长度必须介于 0 和 5000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=500, message="书籍图片长度必须介于 0 和 500 之间")
	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
}