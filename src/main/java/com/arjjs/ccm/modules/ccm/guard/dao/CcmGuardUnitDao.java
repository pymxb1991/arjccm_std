/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.guard.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.guard.entity.CcmGuardUnit;
import com.arjjs.ccm.modules.flat.analyst.entity.Count;

import java.util.List;

/**
 * 警卫单位DAO接口
 *
 * @author lijiupeng
 * @version 2019-07-17
 */
@MyBatisDao
public interface CcmGuardUnitDao extends CrudDao<CcmGuardUnit> {

    List<Count> getByCount();

}