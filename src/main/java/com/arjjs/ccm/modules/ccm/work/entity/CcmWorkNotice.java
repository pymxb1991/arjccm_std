/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.work.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 公告Entity
 * @author liang
 * @version 2018-06-12
 */
public class CcmWorkNotice extends DataEntity<CcmWorkNotice> {
	
	private static final long serialVersionUID = 1L;
	private Date datas;		// 时间
	private String title;		// 标题
	private String content;		// 内容
	private Date beginDatas;		// 开始 时间
	private Date endDatas;		// 结束 时间

	private String publisher;   // 发布人

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public CcmWorkNotice() {
		super();
	}

	public CcmWorkNotice(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDatas() {
		return datas;
	}

	public void setDatas(Date datas) {
		this.datas = datas;
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
	
	public Date getBeginDatas() {
		return beginDatas;
	}

	public void setBeginDatas(Date beginDatas) {
		this.beginDatas = beginDatas;
	}
	
	public Date getEndDatas() {
		return endDatas;
	}

	public void setEndDatas(Date endDatas) {
		this.endDatas = endDatas;
	}
		
}