/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.patrol.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.patrol.entity.CcmPatrolUnit;
import com.arjjs.ccm.modules.flat.analyst.entity.Count;

import java.util.List;

/**
 * 巡逻单位DAO接口
 * @author lijiupeng
 * @version 2019-07-08
 */
@MyBatisDao
public interface CcmPatrolUnitDao extends CrudDao<CcmPatrolUnit> {

    /**
     * in id 返回list
     * @param ids
     * @return
     */


    List<Count> getByCount();

}