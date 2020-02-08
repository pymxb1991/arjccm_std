/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.service.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 计生管理Entity
 * @author pengjianqiang
 * @version 2019-02-25
 */
public class CcmBirthControl extends DataEntity<CcmBirthControl> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private Date relDate;		// 日期
	private String type;		// 类型
	private String content;		// 内容
	private String file;		// 附件
	private String file2;		// 附件2
	private Date beginRelDate;		// 开始 日期
	private Date endRelDate;		// 结束 日期
	
	@Length(min=0, max=128, message="附件长度必须介于 0 和 128 之间")
	public String getFile2() {
		return file2;
	}

	public void setFile2(String file2) {
		this.file2 = file2;
	}
	
	public CcmBirthControl() {
		super();
	}

	public CcmBirthControl(String id){
		super(id);
	}

	@Length(min=0, max=64, message="标题长度必须介于 0 和 64 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRelDate() {
		return relDate;
	}

	public void setRelDate(Date relDate) {
		this.relDate = relDate;
	}
	
	@Length(min=0, max=1, message="类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=128, message="附件长度必须介于 0 和 128 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public Date getBeginRelDate() {
		return beginRelDate;
	}

	public void setBeginRelDate(Date beginRelDate) {
		this.beginRelDate = beginRelDate;
	}
	
	public Date getEndRelDate() {
		return endRelDate;
	}

	public void setEndRelDate(Date endRelDate) {
		this.endRelDate = endRelDate;
	}
		
}