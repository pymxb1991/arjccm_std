/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.oa.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.ActEntity;
import com.arjjs.ccm.modules.act.entity.Act;
import com.arjjs.ccm.modules.ccm.rest.entity.ActForApp;

/**
 * 公文Entity
 * @author pengjianqiang
 * @version 2018-03-19
 */
public class CcmOfficialDocument extends ActEntity<CcmOfficialDocument> {
	
	private static final long serialVersionUID = 1L;
	private String procInsId;		// 流程实例ID
	private String type;		// 分类
	private String subject;		// 公文主题
	private String content;		// 内容
	private String file;		// 附件
	private String drafter;		// 起草人姓名
	private String drafterTel;		// 起草人电话
	private String status;		// 审核状态
	private List<ActForApp> histoicFlowListForApp;	//流转信息
	private ActForApp actForApp;	//
	private String typeLable;	//用户app接口
	
	public CcmOfficialDocument() {
		super();
	}

	public CcmOfficialDocument(String id){
		super(id);
	}
	
	public Act getAct() {
		if (act == null){
			act = new Act();
		}
		return act;
	}

	public void setAct(Act act) {
		this.act = act;
	}
	@Length(min=1, max=64, message="流程实例ID长度必须介于 1 和 64 之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}
	
	@Length(min=1, max=2, message="分类长度必须介于 1 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=256, message="公文主题长度必须介于 1 和 256 之间")
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Length(min=0, max=1000, message="内容长度必须介于 0 和 1000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=1000, message="附件长度必须介于 0 和 1000 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	@Length(min=0, max=100, message="起草人姓名长度必须介于 0 和 100 之间")
	public String getDrafter() {
		return drafter;
	}

	public void setDrafter(String drafter) {
		this.drafter = drafter;
	}
	
	@Length(min=0, max=30, message="起草人电话长度必须介于 0 和 30 之间")
	public String getDrafterTel() {
		return drafterTel;
	}

	public void setDrafterTel(String drafterTel) {
		this.drafterTel = drafterTel;
	}
	
	@Length(min=0, max=2, message="审核状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ActForApp> getHistoicFlowListForApp() {
		return histoicFlowListForApp;
	}

	public void setHistoicFlowListForApp(List<ActForApp> histoicFlowListForApp) {
		this.histoicFlowListForApp = histoicFlowListForApp;
	}

	public ActForApp getActForApp() {
		return actForApp;
	}

	public void setActForApp(ActForApp actForApp) {
		this.actForApp = actForApp;
	}

	public String getTypeLable() {
		return typeLable;
	}

	public void setTypeLable(String typeLable) {
		this.typeLable = typeLable;
	}

	
	
}