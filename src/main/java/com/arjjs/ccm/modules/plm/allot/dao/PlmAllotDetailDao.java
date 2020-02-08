/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.allot.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.allot.entity.PlmAllotDetail;

/**
 * 调拨详细DAO接口
 * @author dongqikai
 * @version 2018-08-21
 */
@MyBatisDao
public interface PlmAllotDetailDao extends CrudDao<PlmAllotDetail> {
	
	/**
	 * 根据调拨单id删除所有记录
	 * @param allotId
	 */
	public void deleteByAllotId(String allotId);
	
}