/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.log.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.ccm.pop.entity.CcmPeople;
import com.arjjs.ccm.modules.sys.entity.User;

/**
 * 重点人员签到记录Entity
 * @author pengjianqiang
 * @version 2019-03-05
 */
public class CcmLogImpPopSign extends DataEntity<CcmLogImpPopSign> {
	
	private static final long serialVersionUID = 1L;
	private CcmPeople peopleId;		// 人员ID
	private String relevanceTable;		// 关联表
	private String type;		// 类型
	private String content;		// 内容
	private Date tailTime;		// 时间
	private Date beginTailTime;		// 开始 时间
	private Date endTailTime;		// 结束 时间
	private String pic;			// 图片
	private String effectiveStatus;  //有效状态
	private String errorStatus;  //异常状态
	
	public CcmLogImpPopSign() {
		super();
	}

	public CcmLogImpPopSign(String id){
		super(id);
	}


	
	public CcmPeople getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(CcmPeople peopleId) {
		this.peopleId = peopleId;
	}

	@Length(min=0, max=100, message="关联表长度必须介于 0 和 100 之间")
	public String getRelevanceTable() {
		return relevanceTable;
	}

	public void setRelevanceTable(String relevanceTable) {
		this.relevanceTable = relevanceTable;
	}
	
	@Length(min=0, max=2, message="类型长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTailTime() {
		return tailTime;
	}

	public void setTailTime(Date tailTime) {
		this.tailTime = tailTime;
	}
	
	public Date getBeginTailTime() {
		return beginTailTime;
	}

	public void setBeginTailTime(Date beginTailTime) {
		this.beginTailTime = beginTailTime;
	}
	
	public Date getEndTailTime() {
		return endTailTime;
	}

	public void setEndTailTime(Date endTailTime) {
		this.endTailTime = endTailTime;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getEffectiveStatus() {
		return effectiveStatus;
	}

	public void setEffectiveStatus(String effectiveStatus) {
		this.effectiveStatus = effectiveStatus;
	}

	public String getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}
		
}