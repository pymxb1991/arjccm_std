/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.purchase.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.purchase.entity.PlmPurchaseDeclareDetail;

/**
 * 采购申报DAO接口
 * @author liuxue
 * @version 2018-08-25
 */
@MyBatisDao
public interface PlmPurchaseDeclareDetailDao extends CrudDao<PlmPurchaseDeclareDetail> {
	
}