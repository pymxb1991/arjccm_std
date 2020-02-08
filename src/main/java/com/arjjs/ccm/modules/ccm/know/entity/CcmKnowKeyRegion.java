/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.entity;

import org.hibernate.validator.constraints.Length;
import com.arjjs.ccm.modules.sys.entity.Office;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 重点地区标准Entity
 * @author liang
 * @version 2018-03-22
 */
public class CcmKnowKeyRegion extends DataEntity<CcmKnowKeyRegion> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String type;		// 类别
	private Office office;		// 发布部门
	private Date publishDate;		// 发布日期
	private String content;		// 内容
	private String file;		// 附件1
	private Date beginPublishDate;		// 开始 发布日期
	private Date endPublishDate;		// 结束 发布日期
	
	public CcmKnowKeyRegion() {
		super();
	}

	public CcmKnowKeyRegion(String id){
		super(id);
	}

	@Length(min=0, max=64, message="标题长度必须介于 0 和 64 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=2, message="类别长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	@Length(min=0, max=2000, message="内容长度必须介于 0 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=256, message="附件1长度必须介于 0 和 256 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public Date getBeginPublishDate() {
		return beginPublishDate;
	}

	public void setBeginPublishDate(Date beginPublishDate) {
		this.beginPublishDate = beginPublishDate;
	}
	
	public Date getEndPublishDate() {
		return endPublishDate;
	}

	public void setEndPublishDate(Date endPublishDate) {
		this.endPublishDate = endPublishDate;
	}
		
}