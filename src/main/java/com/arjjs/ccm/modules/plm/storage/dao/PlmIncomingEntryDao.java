/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.storage.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.storage.entity.PlmIncomingEntry;

/**
 * 入库单DAO接口
 * @author dongqikai
 * @version 2018-06-30
 */
@MyBatisDao
public interface PlmIncomingEntryDao extends CrudDao<PlmIncomingEntry> {
	
	public void updateIncomingStatus(PlmIncomingEntry plmIncomingEntry);
	
}