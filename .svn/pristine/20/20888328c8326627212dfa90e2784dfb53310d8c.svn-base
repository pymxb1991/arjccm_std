/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.oa.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.Collections3;
import com.arjjs.ccm.common.utils.IdGen;
import com.arjjs.ccm.common.utils.StringUtils;
import com.arjjs.ccm.modules.sys.entity.User;
import com.google.common.collect.Lists;

/**
 * 我的消息Entity
 * @author admin001
 * @version 2014-05-16
 */
public class OaMessage extends DataEntity<OaMessage> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 类型
	private String title;		// 标题
	private String content;		// 类型
	private String files;		// 附件
	private String status;		// 状态

	private String readNum;		// 已读
	private String unReadNum;	// 未读
	
	private boolean isSelf;		// 是否只查询自己的通知
	
	private String readFlag;	// 本人阅读状态
	private String sender;
	private Date sendTime;
	private String count;
	private String value0;
	private String value1;
	private String userId;
	
	
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getValue0() {
		return value0;
	}

	public void setValue0(String value0) {
		this.value0 = value0;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private List<OaMessageRecord> oaMessageRecordList = Lists.newArrayList();
	
	public OaMessage() {
		super();
	}

	public OaMessage(String id){
		super(id);
	}

	@Length(min=0, max=200, message="标题长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=1, message="类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=2000, message="附件长度必须介于 0 和 2000 之间")
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReadNum() {
		return readNum;
	}

	public void setReadNum(String readNum) {
		this.readNum = readNum;
	}

	public String getUnReadNum() {
		return unReadNum;
	}

	public void setUnReadNum(String unReadNum) {
		this.unReadNum = unReadNum;
	}
	
	public List<OaMessageRecord> getOaMessageRecordList() {
		return oaMessageRecordList;
	}

	public void setOaMessageRecordList(List<OaMessageRecord> oaMessageRecordList) {
		this.oaMessageRecordList = oaMessageRecordList;
	}
	
	/**
	 * 获取通知发送记录用户ID
	 * @return
	 */
	public String getOaMessageRecordIds() {
		return Collections3.extractToString(oaMessageRecordList, "user.id", ",") ;
	}
	
	/**
	 * 设置通知发送记录用户ID
	 * @return
	 */
	public void setOaMessageRecordIds(String oaMessageRecord) {
		this.oaMessageRecordList = Lists.newArrayList();
		for (String id : StringUtils.split(oaMessageRecord, ",")){
			OaMessageRecord entity = new OaMessageRecord();
			entity.setId(IdGen.uuid());
			entity.setOaMessage(this);
			entity.setUser(new User(id));
			entity.setReadFlag("0");
			this.oaMessageRecordList.add(entity);
		}
	}

	/**
	 * 获取通知发送记录用户Name
	 * @return
	 */
	public String getOaMessageRecordNames() {
		return Collections3.extractToString(oaMessageRecordList, "user.name", ",") ;
	}
	
	/**
	 * 设置通知发送记录用户Name
	 * @return
	 */
	public void setOaMessageRecordNames(String oaMessageRecord) {
		// 什么也不做
	}

	public boolean isSelf() {
		return isSelf;
	}

	public void setSelf(boolean isSelf) {
		this.isSelf = isSelf;
	}

	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}
}