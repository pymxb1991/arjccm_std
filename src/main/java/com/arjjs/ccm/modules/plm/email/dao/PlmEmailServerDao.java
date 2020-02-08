/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.email.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.email.entity.PlmEmailServer;

/**
 * 邮箱配置DAO接口
 * @author liucong
 * @version 2018-07-24
 */
@MyBatisDao
public interface PlmEmailServerDao extends CrudDao<PlmEmailServer> {
	
}