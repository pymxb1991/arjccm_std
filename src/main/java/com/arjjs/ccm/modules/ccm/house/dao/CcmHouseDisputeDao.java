/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHarmNationalSecurity;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseDispute;

/**
 * 矛盾纠纷DAO接口
 * @author liu
 * @version 2018-09-27
 */
@MyBatisDao
public interface CcmHouseDisputeDao extends CrudDao<CcmHouseDispute> {
	
	
	public CcmHouseDispute getItemByPeopleId(String id);
}