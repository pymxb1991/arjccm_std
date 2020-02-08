/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.house.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHarmNationalSecurity;
import com.arjjs.ccm.modules.ccm.house.entity.CcmHouseDrugs;

/**
 * 危害国家安全DAO接口
 * @author liuxue
 * @version 2018-09-26
 */
@MyBatisDao
public interface CcmHarmNationalSecurityDao extends CrudDao<CcmHarmNationalSecurity> {

	/**
	 *  
	 * @param  
	 * @return
	 */
	public CcmHarmNationalSecurity getItemByPeopleId(String id);
	
}