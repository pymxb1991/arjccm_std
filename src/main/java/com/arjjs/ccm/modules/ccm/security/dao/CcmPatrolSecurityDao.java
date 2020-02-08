/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.security.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.security.entity.CcmPatrolSecurity;

/**
 * 警卫管理DAO接口
 * @author lijiupeng
 * @version 2019-07-11
 */
@MyBatisDao
public interface CcmPatrolSecurityDao extends CrudDao<CcmPatrolSecurity> {
    int updateStatus(String val,String id);
}