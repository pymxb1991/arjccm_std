/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.action.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.modules.flat.stepinfo.entity.BphStepInfo;

/**
 * 执行动作配置Entity
 * @author liu
 * @version 2018-11-14
 */
public class BphActionInfo extends DataEntity<BphActionInfo> {
	
	private static final long serialVersionUID = 1L;	// 动作标题
	private String name;		// 动作名称
	private String type;		// 动作类型  1 通知类  2 任务类  当为2时会关联任务反馈信息表
	private String title;		// 动作标题
	private String content;		// 动作内容
	private BphStepInfo bphStepInfo;	// 过程
	private int num;					//已启动数量
	private String bsaActionId;
	private String bsaStepId;
	private String stepId;
	

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	public String getBsaActionId() {
		return bsaActionId;
	}

	public void setBsaActionId(String bsaActionId) {
		this.bsaActionId = bsaActionId;
	}

	public String getBsaStepId() {
		return bsaStepId;
	}

	public void setBsaStepId(String bsaStepId) {
		this.bsaStepId = bsaStepId;
	}

	public BphActionInfo() {
		super();
	}

	public BphActionInfo(String id){
		super(id);
	}

	@Length(min=0, max=80, message="动作名称长度必须介于 0 和 80 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="动作类型  1 通知类  2 任务类  当为2时会关联任务反馈信息表长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=80, message="动作标题长度必须介于 0 和 80 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=500, message="动作内容长度必须介于 0 和 500 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BphStepInfo getBphStepInfo() {
		return bphStepInfo;
	}

	public void setBphStepInfo(BphStepInfo bphStepInfo) {
		this.bphStepInfo = bphStepInfo;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
 
}