/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.act.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.act.entity.ActUtConfig;

/**
 * 流程配置DAO接口
 * @author dongqikai
 * @version 2018-07-16
 */
@MyBatisDao
public interface ActUtConfigDao extends CrudDao<ActUtConfig> {
	
}