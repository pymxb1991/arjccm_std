/**
 * Copyright &copy; 2012-2018 All rights reserved.
 */
package com.arjjs.ccm.modules.plm.car.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.plm.car.entity.PlmCarViolation;

/**
 * 违章记录DAO接口
 * @author fu
 * @version 2018-07-02
 */
@MyBatisDao
public interface PlmCarViolationDao extends CrudDao<PlmCarViolation> {
	
}