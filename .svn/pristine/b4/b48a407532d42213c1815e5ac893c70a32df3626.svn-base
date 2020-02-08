/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.files.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 附件Entity
 * @author dongqikai
 * @version 2018-06-22
 */
public class PlmPublicFiles extends DataEntity<PlmPublicFiles> {
	
	private static final long serialVersionUID = 1L;
	private String fid;		// 外键
	private String seqidx;		// 排列顺序
	private String tablename;		// 对应主表
	private String cnname;		// 中文名称
	private String filename;		// 文件名
	private String filetype;		// 文件类型
	private String size;		// 文件大小
	private String extend1;		// 扩展1
	private String extend2;		// 扩展2
	
	public PlmPublicFiles() {
		super();
	}

	public PlmPublicFiles(String id){
		super(id);
	}

	@Length(min=1, max=64, message="外键长度必须介于 1 和 64 之间")
	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}
	
	@Length(min=1, max=3, message="排列顺序长度必须介于 1 和 3 之间")
	public String getSeqidx() {
		return seqidx;
	}

	public void setSeqidx(String seqidx) {
		this.seqidx = seqidx;
	}
	
	@Length(min=1, max=128, message="对应主表长度必须介于 1 和 128 之间")
	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	
	@Length(min=1, max=128, message="中文名称长度必须介于 1 和 128 之间")
	public String getCnname() {
		return cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}
	
	@Length(min=0, max=256, message="文件名长度必须介于 0 和 256 之间")
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	@Length(min=0, max=256, message="文件类型长度必须介于 0 和 256 之间")
	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	
	@Length(min=1, max=8, message="文件大小长度必须介于 1 和 8 之间")
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
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
	
}