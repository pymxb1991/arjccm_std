/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.opinion.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.ActEntity;
import com.arjjs.ccm.modules.plm.act.entity.PlmAct;

/**
 * 建议意见箱Entity
 * @author liucong
 * @version 2018-07-30
 */
public class PlmOpinion extends ActEntity<PlmOpinion> {
	
	private static final long serialVersionUID = 1L;
	private String procInsId;		// 流程实例ID
	private String file;		// 附件
	private String name;		// 标题名称
	private String themeName;		// 主题名称
	private String type;		// 意见类型，字典表&lsquo;plm_opinion_type&rsquo;
	private String body;		// 意见内容
	private String imgUrl;		// 图片
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	private PlmAct plmAct; // 业务流程总表
	private String cancelFlag;   //是否可撤销
	
	public PlmOpinion() {
		super();
	}

	public PlmOpinion(String id){
		super(id);
	}
	@Length(min=0, max=256, message="长度必须介于 0 和 64之间")
	public String getProcInsId() {
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}

	@Length(min=0, max=256, message="附件长度必须介于 0 和 256 之间")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	@Length(min=0, max=128, message="标题名称长度必须介于 0 和 128 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=128, message="主题名称长度必须介于 0 和 128 之间")
	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	
	@Length(min=0, max=2, message="意见类型，字典表&lsquo;plm_opinion_type&rsquo;长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	@Length(min=0, max=255, message="图片长度必须介于 0 和 255 之间")
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	@Length(min=0, max=256, message="扩展1长度必须介于 0 和 256 之间")
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	@Length(min=0, max=256, message="扩展2长度必须介于 0 和 256 之间")
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	public PlmAct getPlmAct() {
		return plmAct;
	}

	public void setPlmAct(PlmAct plmAct) {
		this.plmAct = plmAct;
	}

	public String getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(String cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	
}