/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.arjjs.ccm.modules.ccm.financial.dao;

import com.arjjs.ccm.common.persistence.CrudDao;
import com.arjjs.ccm.common.persistence.annotation.MyBatisDao;
import com.arjjs.ccm.modules.ccm.financial.entity.CcmPlaceFinancial;

/**
 * 金融机构DAO接口
 * @author lgh
 * @version 2019-04-29
 */
@MyBatisDao
public interface CcmPlaceFinancialDao extends CrudDao<CcmPlaceFinancial> {
	
}