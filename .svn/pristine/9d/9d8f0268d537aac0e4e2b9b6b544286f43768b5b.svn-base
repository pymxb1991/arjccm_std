package com.arjjs.ccm.modules.pbs.common.utils;

import java.util.Comparator;

public class EchartType implements Comparator<EchartType> {
	private String id;
	
	private String name;

	private String sTitle;

	private Integer value;

	public String getsTitle() {
		return sTitle;
	}

	public void setsTitle(String sTitle) {
		this.sTitle = sTitle;
	}
 
	@Override
	public int compare(EchartType arg0, EchartType arg1) {
		int hits0 = arg0.getValue();
		int hits1 = arg1.getValue();
		if (hits1 > hits0) {
			return 1;
		} else if (hits1 == hits0) {
			return 0;
		} else {
			return -1;
		}
	}

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	

}
