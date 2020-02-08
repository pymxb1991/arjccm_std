/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.purchase.dao;

import java.util.List;
import java.util.Map;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.purchase.entity.PlmPurchaseApply;


/**
 * 采购计划申请DAO接口
 * @author liuxue
 * @version 2018-07-07
 */
@MyBatisDao
public interface PlmPurchaseApplyDao extends CrudDao<PlmPurchaseApply> {
	
	public PlmPurchaseApply getByProcInsId(String procInsId);
	public int updateProcInsId(PlmPurchaseApply PlmPurchaseApply);
	
	List<Map<String, Object>> fundingTypeStatistic(PlmPurchaseApply PlmPurchaseApply);
	
	
	
}