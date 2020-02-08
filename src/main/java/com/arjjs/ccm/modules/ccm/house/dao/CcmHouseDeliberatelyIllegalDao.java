/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseDeliberatelyIllegal;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseDrugs;

/**
 *  故意犯法DAO接口
 * @author liuxue
 * @version 2018-09-27
 */
@MyBatisDao
public interface CcmHouseDeliberatelyIllegalDao extends CrudDao<CcmHouseDeliberatelyIllegal> {
	/**
	 *  
	 * @param  
	 * @return
	 */
	public CcmHouseDeliberatelyIllegal getItemByPeopleId(String id);
}