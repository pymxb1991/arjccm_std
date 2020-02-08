/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.home.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.home.entity.PlmPortalDict;


/**
 * 门户字典DAO接口
 * @author liuxue
 * @version 2018-07-04
 */
@MyBatisDao
public interface PlmPortalDictDao extends CrudDao<PlmPortalDict> {
	
}