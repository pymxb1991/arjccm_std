/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 编码方案管理Entity
 * @author dongqikai
 * @version 2018-07-03
 */
public class SysCodes extends DataEntity<SysCodes> {
	
	private static final long serialVersionUID = 1L;
	private String codeA;		// 首部编码一般固定
	private String codeB;		// 次级编码YYYY年份
	private String codeC;		// 三级编码MM月份
	private String codeD;		// 四级编码DD日期
	private String codeE;		// 存储下一个编号
	private String version;		// 乐观锁版本
	private String codeType;		// 编码类型
	
	public SysCodes() {
		super();
	}

	public SysCodes(String id){
		super(id);
	}

	@Length(min=1, max=6, message="首部编码一般固定长度必须介于 1 和 6 之间")
	public String getCodeA() {
		return codeA;
	}

	public void setCodeA(String codeA) {
		this.codeA = codeA;
	}
	
	@Length(min=1, max=6, message="次级编码YYYY年份长度必须介于 1 和 6 之间")
	public String getCodeB() {
		return codeB;
	}

	public void setCodeB(String codeB) {
		this.codeB = codeB;
	}
	
	@Length(min=1, max=4, message="三级编码MM月份长度必须介于 1 和 4 之间")
	public String getCodeC() {
		return codeC;
	}

	public void setCodeC(String codeC) {
		this.codeC = codeC;
	}
	
	@Length(min=1, max=4, message="四级编码DD日期长度必须介于 1 和 4 之间")
	public String getCodeD() {
		return codeD;
	}

	public void setCodeD(String codeD) {
		this.codeD = codeD;
	}
	
	@Length(min=1, max=8, message="存储下一个编号长度必须介于 1 和 8 之间")
	public String getCodeE() {
		return codeE;
	}

	public void setCodeE(String codeE) {
		this.codeE = codeE;
	}
	
	@Length(min=1, max=128, message="乐观锁版本长度必须介于 1 和 128 之间")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@Length(min=1, max=128, message="编码类型长度必须介于 1 和 128 之间")
	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	
}