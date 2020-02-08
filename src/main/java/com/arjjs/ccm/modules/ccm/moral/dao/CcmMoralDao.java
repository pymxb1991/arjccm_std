/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.moral.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.moral.entity.CcmMoral;

/**
 * 道德模范实体类DAO接口
 * @author lijiupeng
 * @version 2019-06-21
 */
@MyBatisDao
public interface CcmMoralDao extends CrudDao<CcmMoral> {
	
}