/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.entity;

import org.hibernate.validator.constraints.Length;

import com.arjjs.ccm.common.persistence.DataEntity;

/**
 * 巡逻点位结果Entity
 * @author arj
 * @version 2018-03-16
 */
public class CcmPatrolRespoint extends DataEntity<CcmPatrolRespoint> {
	
	private static final long serialVersionUID = 1L;
	private CcmPatrolResult resultId;		// 结果ID
	private CcmPatrolPoint pointId;		// 巡检点id
	private String result;		// 巡检结果
	
	public CcmPatrolRespoint() {
		super();
	}

	public CcmPatrolRespoint(String id){
		super(id);
	}

	public CcmPatrolResult getResultId() {
		return resultId;
	}

	public void setResultId(CcmPatrolResult resultId) {
		this.resultId = resultId;
	}
	
	public CcmPatrolPoint getPointId() {
		return pointId;
	}

	public void setPointId(CcmPatrolPoint pointId) {
		this.pointId = pointId;
	}
	
	@Length(min=0, max=255, message="巡检结果长度必须介于 0 和 255 之间")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}