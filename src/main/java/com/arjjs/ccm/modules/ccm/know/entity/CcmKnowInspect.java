/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.know.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 检查记录Entity
 * @author liang
 * @version 2018-05-31
 */
public class CcmKnowInspect extends DataEntity<CcmKnowInspect> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 检查名称
	private Date inspectData;		// 检查日期
	private String type;		// 类别
	private Integer num;		// 发现问题数
	private String file;		// 附件
	private Date beginInspectData;		// 开始 检查日期
	private Date endInspectData;		// 结束 检查日期
	
	public CcmKnowInspect() {
		super();
	}

	public CcmKnowInspect(String id){
		super(id);
	}

	@Length(min=0, max=100, message="检查名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInspectData() {
		return inspectData;
	}

	public void setInspectData(Date inspectData) {
		this.inspectData = inspectData;
	}
	
	@Length(min=0, max=2, message="类别长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	@Length(min=0, max=256, message="附件长度必须介于 0 和 256 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public Date getBeginInspectData() {
		return beginInspectData;
	}

	public void setBeginInspectData(Date beginInspectData) {
		this.beginInspectData = beginInspectData;
	}
	
	public Date getEndInspectData() {
		return endInspectData;
	}

	public void setEndInspectData(Date endInspectData) {
		this.endInspectData = endInspectData;
	}
		
}