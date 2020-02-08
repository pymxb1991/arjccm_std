/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.pbs.sys.entity;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 党员部门扩展信息Entity
 * 
 * @author lc
 * @version 2018-04-10
 */
public class GeneralMethod extends DataEntity<GeneralMethod> {

	private static final long serialVersionUID = 1L;
	// 表名
	private String Tabletype;
 
	// 列名
	private String Columntype;

	// 该列的内容 名称 
	private String Key;


	public GeneralMethod() {
		super();
	}

	public GeneralMethod(String id) {
		super(id);
	}

	public String getTabletype() {
		return Tabletype;
	}

	public void setTabletype(String tabletype) {
		Tabletype = tabletype;
	}

	public String getColumntype() {
		return Columntype;
	}

	public void setColumntype(String columntype) {
		Columntype = columntype;
	}

	public String getKey() {
		return Key;
	}

	public void setKey(String key) {
		Key = key;
	}

}