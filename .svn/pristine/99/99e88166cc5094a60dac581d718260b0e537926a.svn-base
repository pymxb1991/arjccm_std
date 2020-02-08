/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.ccmsys.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 待上传上级平台记录管理Entity
 * @author pengjianqiang
 * @version 2018-05-12
 */
public class CcmUploadLog extends DataEntity<CcmUploadLog> {
	
	private static final long serialVersionUID = 1L;
	private String tableName;		// 所在表
	private String dataId;		// 记录id
	private String operateType;		// 操作类型
	private String param;		// 备用参数
	private String uploadStatus;		// 上传状态
	private Date beginUpdateDate;		// 开始 更新时间
	private Date endUpdateDate;		// 结束 更新时间
	
	public CcmUploadLog() {
		super();
	}

	public CcmUploadLog(String id){
		super(id);
	}

	@Length(min=0, max=64, message="所在表长度必须介于 0 和 64 之间")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	@Length(min=0, max=64, message="记录id长度必须介于 0 和 64 之间")
	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	
	@Length(min=0, max=1, message="操作类型长度必须介于 0 和 1 之间")
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
	@Length(min=0, max=128, message="备用参数长度必须介于 0 和 128 之间")
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	@Length(min=0, max=1, message="上传状态长度必须介于 0 和 1 之间")
	public String getUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	
	public Date getBeginUpdateDate() {
		return beginUpdateDate;
	}

	public void setBeginUpdateDate(Date beginUpdateDate) {
		this.beginUpdateDate = beginUpdateDate;
	}
	
	public Date getEndUpdateDate() {
		return endUpdateDate;
	}

	public void setEndUpdateDate(Date endUpdateDate) {
		this.endUpdateDate = endUpdateDate;
	}
		
}