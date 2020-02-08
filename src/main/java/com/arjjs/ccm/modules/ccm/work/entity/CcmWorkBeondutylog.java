/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.work.entity;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.sys.entity.Office;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 值班日志表Entity
 * @author liang
 * @version 2018-06-12
 */
public class CcmWorkBeondutylog extends DataEntity<CcmWorkBeondutylog> {
	
	private static final long serialVersionUID = 1L;
	private Date datas;		// 时间
	private String type;		// 记录类型
	private String adds;		// 值班地点
	private String details;		// 内容
	private Date beginDatas;		// 开始 时间
	private Date endDatas;		// 结束 时间
	private Office office;	//部门
	
	public CcmWorkBeondutylog() {
		super();
	}

	public CcmWorkBeondutylog(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDatas() {
		return datas;
	}

	public void setDatas(Date datas) {
		this.datas = datas;
	}
	
	@Length(min=0, max=2, message="记录类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=100, message="值班地点长度必须介于 0 和 100 之间")
	public String getAdds() {
		return adds;
	}

	public void setAdds(String adds) {
		this.adds = adds;
	}
	
	@Length(min=0, max=1000, message="内容长度必须介于 0 和 1000 之间")
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
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

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
}