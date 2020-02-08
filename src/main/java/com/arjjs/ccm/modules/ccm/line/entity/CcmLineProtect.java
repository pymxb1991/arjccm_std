/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.line.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 护路护线Entity
 * @author arj
 * @version 2018-01-23
 */
public class CcmLineProtect extends DataEntity<CcmLineProtect> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String lineType;		// 线路类型
	private String compName;		// 隶属单位名称
	private String compAdd;		// 隶属单位详址
	private String compTel;		// 隶属单位联系方式
	private String compPrinName;		// 隶属单位负责人姓名
	private String compPrinTel;		// 隶属单位负责人联系方式
	private String goveName;		// 管辖单位名称
	private String goveAdd;		// 管辖单位详址
	private String goveTel;		// 管辖单位联系方式
	private String secuName;		// 分管治保负责人姓名
	private String secuTel;		// 分管治保负责人联系方式
	private String dangCase;		// 治安隐患情况
	private String dangGrade;		// 治安隐患等级
	private String line;		// 线路
	private String count;
	
	
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public CcmLineProtect() {
		super();
	}

	public CcmLineProtect(String id){
		super(id);
	}

	@NotEmpty
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=2, message="线路类型长度必须介于 0 和 2 之间")
	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}
	
	@Length(min=0, max=100, message="隶属单位名称长度必须介于 0 和 100 之间")
	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}
	
	@Length(min=0, max=200, message="隶属单位详址长度必须介于 0 和 200 之间")
	public String getCompAdd() {
		return compAdd;
	}

	public void setCompAdd(String compAdd) {
		this.compAdd = compAdd;
	}
	
	@Length(min=0, max=50, message="隶属单位联系方式长度必须介于 0 和 50 之间")
	public String getCompTel() {
		return compTel;
	}

	public void setCompTel(String compTel) {
		this.compTel = compTel;
	}
	
	@Length(min=0, max=50, message="隶属单位负责人姓名长度必须介于 0 和 50 之间")
	public String getCompPrinName() {
		return compPrinName;
	}

	public void setCompPrinName(String compPrinName) {
		this.compPrinName = compPrinName;
	}
	
	@Length(min=0, max=50, message="隶属单位负责人联系方式长度必须介于 0 和 50 之间")
	public String getCompPrinTel() {
		return compPrinTel;
	}

	public void setCompPrinTel(String compPrinTel) {
		this.compPrinTel = compPrinTel;
	}
	
	@Length(min=0, max=100, message="管辖单位名称长度必须介于 0 和 100 之间")
	public String getGoveName() {
		return goveName;
	}

	public void setGoveName(String goveName) {
		this.goveName = goveName;
	}
	
	@Length(min=0, max=200, message="管辖单位详址长度必须介于 0 和 200 之间")
	public String getGoveAdd() {
		return goveAdd;
	}

	public void setGoveAdd(String goveAdd) {
		this.goveAdd = goveAdd;
	}
	
	@Length(min=0, max=50, message="管辖单位联系方式长度必须介于 0 和 50 之间")
	public String getGoveTel() {
		return goveTel;
	}

	public void setGoveTel(String goveTel) {
		this.goveTel = goveTel;
	}
	
	@Length(min=0, max=50, message="分管治保负责人姓名长度必须介于 0 和 50 之间")
	public String getSecuName() {
		return secuName;
	}

	public void setSecuName(String secuName) {
		this.secuName = secuName;
	}
	
	@Length(min=0, max=50, message="分管治保负责人联系方式长度必须介于 0 和 50 之间")
	public String getSecuTel() {
		return secuTel;
	}

	public void setSecuTel(String secuTel) {
		this.secuTel = secuTel;
	}
	
	public String getDangCase() {
		return dangCase;
	}

	public void setDangCase(String dangCase) {
		this.dangCase = dangCase;
	}
	
	@Length(min=0, max=2, message="治安隐患等级长度必须介于 0 和 2 之间")
	public String getDangGrade() {
		return dangGrade;
	}

	public void setDangGrade(String dangGrade) {
		this.dangGrade = dangGrade;
	}
	
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
	
}