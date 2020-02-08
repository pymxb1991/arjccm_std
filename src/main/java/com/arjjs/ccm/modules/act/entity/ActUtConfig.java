/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.act.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.arjjs.ccm.common.utils.StringUtils;

/**
 * 流程配置Entity
 * @author dongqikai
 * @version 2018-07-16
 */
public class ActUtConfig extends DataEntity<ActUtConfig> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 流程标题
	private String processType;		// 流程类别
	private String processId;		// 流程定义id
	private String processName;		// 流程名称
	private String table;		// 数据库表名
	private String formKeyName;		// 表单名
	private String titleConfig;    //流程标题显示配置
	private String classPath;     //类路径
	private String[] titleConfigs;    //流程标题显示配置参数接收
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	
	public ActUtConfig() {
		super();
	}

	public ActUtConfig(String id){
		super(id);
	}

	@Length(min=1, max=512, message="流程标题长度必须介于 1 和 512 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=1, max=16, message="流程类别长度必须介于 1 和 16 之间")
	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}
	
	@Length(min=1, max=64, message="流程定义id长度必须介于 1 和 64 之间")
	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}
	
	@Length(min=1, max=256, message="流程名称长度必须介于 1 和 256 之间")
	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}
	
	@Length(min=1, max=256, message="表单名长度必须介于 1 和 256 之间")
	public String getFormKeyName() {
		return formKeyName;
	}

	public void setFormKeyName(String formKeyName) {
		this.formKeyName = formKeyName;
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

	public String getTitleConfig() {
		return titleConfig;
	}

	public void setTitleConfig(String titleConfig) {
		this.titleConfig = titleConfig;
	}

	public String[] getTitleConfigs() {
		return titleConfigs;
	}

	public void setTitleConfigs(String[] titleConfigs) {
		this.titleConfigs = titleConfigs;
	}
	
	/**
	 * 将接收的配置信息转化为数据库存储的信息形式
	 */
	public void configs2config() {
		if (this.titleConfigs == null) return;
		this.titleConfig = "";
		for (int i = 0; i < this.titleConfigs.length; i++) {
			if (i != 0) {
				this.titleConfig += "@@";
			}
			if (StringUtils.isBlank(this.titleConfigs[i]) || "undefined".equals(this.titleConfigs[i])) {
				this.titleConfig += "";
			} else {
				this.titleConfig += this.titleConfigs[i];
			}
		}
	}
	
	/**
	 * 将数据库存储的信息转化为前台展示形式的信息
	 */
	public void config2configs() {
		this.titleConfigs = null;
		if (StringUtils.isBlank(this.titleConfig)) {
			return;
		}
		this.titleConfigs = this.titleConfig.split("@@");
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

}