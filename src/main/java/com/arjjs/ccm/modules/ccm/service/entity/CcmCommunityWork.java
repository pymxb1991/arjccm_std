/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 社区服务Entity
 * @author arj
 * @version 2018-03-09
 */
public class CcmCommunityWork extends DataEntity<CcmCommunityWork> {
	
	private static final long serialVersionUID = 1L;
	private String type1;		// 大类型
	private String type2;		// 小类型
	private String title;		// 标题
	private String content;		// 内容
	private String files;		// 附件
	private String status;		// 审核状态
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private String statusLable;	//用于app接口列表显示
	private String label2;      //二级分类label
	
	public String getLabel2() {
		return label2;
	}

	public void setLabel2(String label2) {
		this.label2 = label2;
	}

	public CcmCommunityWork() {
		super();
	}

	public CcmCommunityWork(String id){
		super(id);
	}

	@Length(min=0, max=64, message="大类型长度必须介于 0 和 64 之间")
	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}
	
	@Length(min=0, max=64, message="小类型长度必须介于 0 和 64 之间")
	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}
	
	@Length(min=0, max=100, message="标题长度必须介于 0 和 100 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=1000, message="内容长度必须介于 0 和 1000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=1000, message="附件长度必须介于 0 和 1000 之间")
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	
	@Length(min=0, max=2, message="审核状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getStatusLable() {
		return statusLable;
	}

	public void setStatusLable(String statusLable) {
		this.statusLable = statusLable;
	}
		
}