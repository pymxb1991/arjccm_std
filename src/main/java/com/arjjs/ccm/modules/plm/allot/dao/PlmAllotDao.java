/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.allot.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.allot.entity.PlmAllot;

/**
 * 内部调拨DAO接口
 * @author dongqikai
 * @version 2018-08-16
 */
@MyBatisDao
public interface PlmAllotDao extends CrudDao<PlmAllot> {
	
}