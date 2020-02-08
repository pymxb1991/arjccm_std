/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.flat.unit.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.flat.analyst.entity.Count;
import com.arjjs.ccm.modules.flat.unit.entity.CcmReliefUnit;

import java.util.List;

/**
 * 备勤单位实体类DAO接口
 * @author lgh
 * @version 2019-07-26
 */
@MyBatisDao
public interface CcmReliefUnitDao extends CrudDao<CcmReliefUnit> {

    List<Count> getByCount();
}