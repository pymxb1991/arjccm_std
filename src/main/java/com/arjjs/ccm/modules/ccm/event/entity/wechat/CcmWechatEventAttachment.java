/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.event.entity.wechat;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 微信信息上报附件Entity
 * @author fu
 * @version 2018-04-14
 */
public class CcmWechatEventAttachment extends DataEntity<CcmWechatEventAttachment> {
	
	private static final long serialVersionUID = 1L;
	private String eventId;		// 事件ID
	private String fileName;		// 文件名称
	private String fileType;		// 文件类型（图片、语音、视频、文档、压缩包、其他）
	private String path;		// 附件路径
	
	public CcmWechatEventAttachment() {
		super();
	}

	public CcmWechatEventAttachment(String id){
		super(id);
	}

	@Length(min=0, max=64, message="事件ID长度必须介于 0 和 64 之间")
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	@Length(min=0, max=64, message="文件名称长度必须介于 0 和 64 之间")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Length(min=0, max=2, message="文件类型（图片、语音、视频、文档、压缩包、其他）长度必须介于 0 和 2 之间")
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Length(min=0, max=128, message="附件路径长度必须介于 0 和 128 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}