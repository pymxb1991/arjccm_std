/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 物资盘点Entity
 * @author dongqikai
 * @version 2018-07-10
 */
public class PlmCheck extends DataEntity<PlmCheck> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 编号
	private Date checkDateStart;		// 盘点开始时间
	private Date checkDateEnd;		// 盘点结束时间
	private String title;		// 盘点主题
	private PlmStorage storage;		// 盘点仓库
	private String content;		// 盘点计划
	private String type;		// 盘点物资类型
	private String status;		// 盘点状态
	private String extend1;		// 扩展1
	private String typeChild;		// 盘点物资子类
	private String extend2;		// 扩展2
	public static final String READY_CHECK = "1";  //待盘点状态
	public static final String CHECKING = "2";  //盘点进行中
	public static final String COMPLETE = "3";  //盘点完成
	
	public PlmCheck() {
		super();
	}

	public PlmCheck(String id){
		super(id);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="盘点开始时间不能为空")
	public Date getCheckDateStart() {
		return checkDateStart;
	}

	public void setCheckDateStart(Date checkDateStart) {
		this.checkDateStart = checkDateStart;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="盘点结束时间不能为空")
	public Date getCheckDateEnd() {
		return checkDateEnd;
	}

	public void setCheckDateEnd(Date checkDateEnd) {
		this.checkDateEnd = checkDateEnd;
	}
	
	@Length(min=1, max=256, message="盘点主题长度必须介于 1 和 256 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public PlmStorage getStorage() {
		return storage;
	}

	public void setStorage(PlmStorage storage) {
		this.storage = storage;
	}
	
	@Length(min=0, max=512, message="盘点计划长度必须介于 0 和 512 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=64, message="盘点物资类型长度必须介于 0 和 64 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="盘点状态长度必须介于 0 和 64 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=256, message="扩展1长度必须介于 0 和 256 之间")
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	
	@Length(min=0, max=64, message="盘点物资子类长度必须介于 0 和 64 之间")
	public String getTypeChild() {
		return typeChild;
	}

	public void setTypeChild(String typeChild) {
		this.typeChild = typeChild;
	}
	
	@Length(min=0, max=256, message="扩展2长度必须介于 0 和 256 之间")
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	
}